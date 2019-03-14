package com.example.mp_3.dutchpaydisignguideproject;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.mp_3.dutchpaydisignguideproject.Adapter.EventImageSliderAdapter;
import com.example.mp_3.dutchpaydisignguideproject.Fragment.LoginFragment;
import com.example.mp_3.dutchpaydisignguideproject.Fragment.Register_TermsConditionsAgreementFragment;
import com.example.mp_3.dutchpaydisignguideproject.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mMainBinding;

    private long mLastTime;

    private LoginFragment mLoginFragment;
    private Register_TermsConditionsAgreementFragment mRegister_termsConditionsAgreementFragment;

    //홈 화면 체크변수
    private boolean mIsHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    private void initData() {

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
        mMainBinding.Appbar.contentMain.tabMainTL.setupWithViewPager( mMainBinding.Appbar.contentMain.vpMainVP, true);

        //Fragment
        mLoginFragment = new LoginFragment();
        mRegister_termsConditionsAgreementFragment = new Register_TermsConditionsAgreementFragment();

    }

    /**
     * 클릭 이벤트 처리
     */
    private void onClickListener() {

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
        mMainBinding.navigationView.imageRegister.setOnClickListener(v -> popRegisterFragment());

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
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (mLoginFragment.isAdded()) {
            fragmentTransaction.show(mLoginFragment);
        } else {
            fragmentTransaction.add(R.id.content_main, mLoginFragment, LoginFragment.class.getName());
        }

        if (mRegister_termsConditionsAgreementFragment.isAdded()) {
            fragmentTransaction.hide(mRegister_termsConditionsAgreementFragment);
        }
        fragmentTransaction.commit();
        mMainBinding.drawerLayout.closeDrawer(GravityCompat.END);
    }

    /**
     * 회원가입(약관동의) 화면 열기
     */
    private void popRegisterFragment() {
        mMainBinding.Appbar.txtAppBar.setText("회원가입");
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (mRegister_termsConditionsAgreementFragment.isAdded()) {
            fragmentTransaction.show(mRegister_termsConditionsAgreementFragment);
        } else {
            fragmentTransaction.add(R.id.content_main, mRegister_termsConditionsAgreementFragment, Register_TermsConditionsAgreementFragment.class.getName());
        }

        if (mLoginFragment.isAdded()) {
            fragmentTransaction.hide(mLoginFragment);
        }
        fragmentTransaction.commit();
        mMainBinding.drawerLayout.closeDrawer(GravityCompat.END);
    }

    /**
     * 홈으로 가기
     */
    private void goMain() {
        mMainBinding.Appbar.txtAppBar.setText("");
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (!fragment.isAdded() || fragment.isHidden())
                continue;

            fragmentTransaction.remove(fragment).commit();
            mIsHome = false;
        }
    }

    /**
     * 뒤로가기
     */
    @Override
    public void onBackPressed() {
        //메인화면 체크
        mIsHome = true;

        //Drawer 열려있으면 닫기
        if (mMainBinding.drawerLayout.isDrawerOpen(GravityCompat.END)) {
            mMainBinding.drawerLayout.closeDrawer(GravityCompat.END);
            mIsHome = false;
        } else {
            //메인으로가기
            goMain();
        }

        //앱 종료
        if (mIsHome) {
            exitApp();
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

}
