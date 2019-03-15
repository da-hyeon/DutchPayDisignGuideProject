package com.example.mp_3.dutchpaydisignguideproject;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.mp_3.dutchpaydisignguideproject.Adapter.EventImageSliderAdapter;
import com.example.mp_3.dutchpaydisignguideproject.Fragment.LoginFragment;
import com.example.mp_3.dutchpaydisignguideproject.Fragment.Register_FormFragment;
import com.example.mp_3.dutchpaydisignguideproject.Fragment.Register_PaymentPasswordFormFragment;
import com.example.mp_3.dutchpaydisignguideproject.Fragment.Register_SuccessFragment;
import com.example.mp_3.dutchpaydisignguideproject.Fragment.Register_TermsConditionsAgreementFragment;
import com.example.mp_3.dutchpaydisignguideproject.Interface.MoveToFragment;
import com.example.mp_3.dutchpaydisignguideproject.Model.User;
import com.example.mp_3.dutchpaydisignguideproject.R;
import com.example.mp_3.dutchpaydisignguideproject.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MoveToFragment {

    private ActivityMainBinding mMainBinding;

    private long mLastTime;

    private LoginFragment mLoginFragment;
    private Register_TermsConditionsAgreementFragment mRegister_termsConditionsAgreementFragment;
    private Register_FormFragment mRegister_formFragment;
    private Register_PaymentPasswordFormFragment mRegister_paymentPasswordFormFragment;
    private Register_SuccessFragment mRegister_successFragment;

    // false - 비로그인 , true - 로그인
    private boolean mLoginState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //데이터바인딩 Activity등록
        mMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mMainBinding.setActivity(this);

        //객체생성 및 데이터 초기화
        initData();

        //클릭리스너 모음
        onClickListener();
    }

    /**
     * 객체생성 및 데이터초기화
     */
    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    private void initData() {

        User user = User.getInstance();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mMainBinding.drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mMainBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //슬라이드 이미지 저장
        List<Drawable> imageArray = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            imageArray.add(ContextCompat.getDrawable(this, R.drawable.dutchpay_event));
        }

        //뷰페이저에 어댑터 연결
        EventImageSliderAdapter mEventImageSliderAdapter = new EventImageSliderAdapter(this, imageArray);
        mMainBinding.Appbar.contentMain.vpMainVP.setAdapter(mEventImageSliderAdapter);

        //dot표시
        mMainBinding.Appbar.contentMain.tabMainTL.setupWithViewPager(mMainBinding.Appbar.contentMain.vpMainVP, true);

        //Fragment
        mLoginFragment = new LoginFragment();
        mRegister_termsConditionsAgreementFragment = new Register_TermsConditionsAgreementFragment();
        mRegister_formFragment = new Register_FormFragment();
        mRegister_paymentPasswordFormFragment = new Register_PaymentPasswordFormFragment();
        mRegister_successFragment = new Register_SuccessFragment();

        if (mLoginState) {
            mMainBinding.navigationView.layoutLogin.setVisibility(View.GONE);

            mMainBinding.Appbar.contentMain.txtUserName.setText(user.getUserName() + "님, 안녕하세요!");
            mMainBinding.Appbar.contentMain.txtUserName.setVisibility(View.VISIBLE);

            mMainBinding.Appbar.contentMain.txtUserDutchMoney.setText(String.format("%,d", Integer.parseInt(user.getUserDutchMoney())) + "원");
            mMainBinding.Appbar.contentMain.txtUserDutchMoney.setVisibility(View.VISIBLE);

            mMainBinding.Appbar.contentMain.imageMainLogo.setVisibility(View.GONE);
            mMainBinding.Appbar.contentMain.txtMainLogoTitle.setVisibility(View.GONE);
        } else {
            mMainBinding.navigationView.layoutLogin.setVisibility(View.VISIBLE);

            mMainBinding.Appbar.contentMain.txtUserName.setVisibility(View.GONE);
            mMainBinding.Appbar.contentMain.txtUserDutchMoney.setVisibility(View.INVISIBLE);
            mMainBinding.Appbar.contentMain.imageMainLogo.setVisibility(View.VISIBLE);
            mMainBinding.Appbar.contentMain.txtMainLogoTitle.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 클릭 이벤트 처리
     */
    private void onClickListener() {

        //뒤로가기 버튼
        mMainBinding.Appbar.imageLeftArrow.setOnClickListener(v -> {
            super.onBackPressed();

            if (getSupportFragmentManager().getFragments().isEmpty()) {
                mMainBinding.Appbar.txtAppBar.setText("");
                mMainBinding.Appbar.imageLeftMenu.setVisibility(View.VISIBLE);
                mMainBinding.Appbar.imageLeftArrow.setVisibility(View.GONE);
            }

        });

        //메뉴버튼
        mMainBinding.Appbar.imageMenu.setOnClickListener(v -> mMainBinding.drawerLayout.openDrawer(GravityCompat.END));

        //나가기 버튼
        mMainBinding.navigationView.imageExit.setOnClickListener(v ->
                mMainBinding.drawerLayout.closeDrawer(GravityCompat.END));

        //홈 버튼
        mMainBinding.navigationView.imageHome.setOnClickListener(v -> {
            goMain();
            mMainBinding.drawerLayout.closeDrawer(GravityCompat.END);
        });

        //로그인 버튼
        mMainBinding.navigationView.imageLogin.setOnClickListener(v -> popLoginFragment());

        //회원가입 버튼
        mMainBinding.navigationView.imageRegister.setOnClickListener(v -> moveToTermsConditionsAgreementFragment());

        //단독결제 버튼
        mMainBinding.navigationView.imageSoloPay.setOnClickListener(v ->
                mMainBinding.drawerLayout.closeDrawer(GravityCompat.END));

        //더치페이 버튼
        mMainBinding.navigationView.imageDutchPay.setOnClickListener(v ->
                mMainBinding.drawerLayout.closeDrawer(GravityCompat.END));

        //이벤트 버튼
        mMainBinding.navigationView.imageEvent.setOnClickListener(v ->
                mMainBinding.drawerLayout.closeDrawer(GravityCompat.END));

        //나의그룹 버튼
        mMainBinding.navigationView.imageMyGroup.setOnClickListener(v ->
                mMainBinding.drawerLayout.closeDrawer(GravityCompat.END));

        //나의지갑 버튼
        mMainBinding.navigationView.imageMyWallet.setOnClickListener(v ->
                mMainBinding.drawerLayout.closeDrawer(GravityCompat.END));

        //공지사항 버튼
        mMainBinding.navigationView.imageNotice.setOnClickListener(v ->
                mMainBinding.drawerLayout.closeDrawer(GravityCompat.END));

        //이용안내 버튼
        mMainBinding.navigationView.imageInfo.setOnClickListener(v ->
                mMainBinding.drawerLayout.closeDrawer(GravityCompat.END));

        //고객센터 버튼
        mMainBinding.navigationView.imageCustomerService.setOnClickListener(v ->
                mMainBinding.drawerLayout.closeDrawer(GravityCompat.END));


    }

    /**
     * 로그인 화면 열기
     */
    private void popLoginFragment() {
        mMainBinding.Appbar.txtAppBar.setText("로그인");

        mMainBinding.Appbar.imageLeftMenu.setVisibility(View.GONE);
        mMainBinding.Appbar.imageLeftArrow.setVisibility(View.VISIBLE);

        moveToFragment(mLoginFragment, true);
        mMainBinding.drawerLayout.closeDrawer(GravityCompat.END);
    }

    /**
     * 회원가입(약관동의) 화면 열기
     */
    @Override
    public void moveToTermsConditionsAgreementFragment() {
        mMainBinding.Appbar.txtAppBar.setText("회원가입");

        mMainBinding.Appbar.imageLeftMenu.setVisibility(View.GONE);
        mMainBinding.Appbar.imageLeftArrow.setVisibility(View.VISIBLE);

        moveToFragment(mRegister_termsConditionsAgreementFragment, true);
        mMainBinding.drawerLayout.closeDrawer(GravityCompat.END);
    }

    /**
     * 회원가입 폼 화면 열기
     */
    @Override
    public void moveToFormFragment() {
        moveToFragment(mRegister_formFragment, false);
    }

    /**
     * 결제비밀번호 설정화면 열기
     */
    @Override
    public void moveToPaymentPasswordFragment() {
        moveToFragment(mRegister_paymentPasswordFormFragment, false);
    }

    /**
     * 회원가입 완료화면 열기
     */
    @Override
    public void moveToSuccessFragment() {
        moveToFragment(mRegister_successFragment, true);
    }


    /**
     * 홈 화면 열기
     */
    @Override
    public void moveToHome(boolean state) {
        mMainBinding.Appbar.txtAppBar.setText("");
        mLoginState = state;
        goMain();
        initData();
    }

    /**
     * 프래그먼트 화면 전환
     */
    public void moveToFragment(Fragment fragment, boolean deleteCheck) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        //메인 프래그먼트일때 Fragment 모든 스택 제거.
        if (deleteCheck) {
            fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_main, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 홈으로 가기
     */
    private void goMain() {
        //프래그먼트가 없으면 리턴
        if (getSupportFragmentManager().getFragments().isEmpty()) {
            return;
        }

        mMainBinding.Appbar.imageLeftMenu.setVisibility(View.VISIBLE);
        mMainBinding.Appbar.imageLeftArrow.setVisibility(View.GONE);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    /**
     * 뒤로가기
     */
    @Override
    public void onBackPressed() {
        //Drawer 열려있으면 닫기
        if (mMainBinding.drawerLayout.isDrawerOpen(GravityCompat.END)) {
            mMainBinding.drawerLayout.closeDrawer(GravityCompat.END);
        } else {
            if (getSupportFragmentManager().getFragments().isEmpty()) {
                exitApp();
            } else {
                super.onBackPressed();

                //한번 더 확인
                if (getSupportFragmentManager().getFragments().isEmpty()) {
                    mMainBinding.Appbar.txtAppBar.setText("");
                    mMainBinding.Appbar.imageLeftMenu.setVisibility(View.VISIBLE);
                    mMainBinding.Appbar.imageLeftArrow.setVisibility(View.GONE);
                }
            }
        }
    }


    /**
     * 두번 누르면 앱종료 2sec
     */
    private void exitApp() {
        if (mLastTime + 2000 < System.currentTimeMillis() || mLastTime == 0) {
            Toast.makeText(this, "한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
            mLastTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        initData();
    }
}
