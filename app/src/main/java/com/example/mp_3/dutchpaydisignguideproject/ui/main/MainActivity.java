package com.example.mp_3.dutchpaydisignguideproject.ui.main;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.mp_3.dutchpaydisignguideproject.R;
import com.example.mp_3.dutchpaydisignguideproject.adapter.EventImageSliderAdapter;
import com.example.mp_3.dutchpaydisignguideproject.databinding.ActivityMainBinding;
import com.example.mp_3.dutchpaydisignguideproject.ui.login.LoginFragment;
import com.example.mp_3.dutchpaydisignguideproject.ui.register.TermsConditionsAgreement.Register_TermsConditionsAgreementFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private ActivityMainBinding mMainBinding;

    private MainContract.Presenter mPresenter;

    private LoginFragment mLoginFragment;
    private Register_TermsConditionsAgreementFragment mRegister_termsConditionsAgreementFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //데이터바인딩 Activity등록
        mMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mMainBinding.setActivity(this);

        //객체생성 및 데이터 초기화
        initData();

        //뒤로가기 버튼
        mMainBinding.Appbar.imageLeftArrow.setOnClickListener(v ->
                mPresenter.onBackPressed()
        );

        //메뉴버튼
        mMainBinding.Appbar.imageMenu.setOnClickListener(v ->
                mMainBinding.drawerLayout.openDrawer(GravityCompat.END)
        );

        //나가기 버튼
        mMainBinding.navigationView.imageExit.setOnClickListener(v ->
                mMainBinding.drawerLayout.closeDrawer(GravityCompat.END));

        //홈 버튼
        mMainBinding.navigationView.imageHome.setOnClickListener(v ->
                mPresenter.clickHome()
        );

        //로그인 버튼
        mMainBinding.navigationView.imageLogin.setOnClickListener(v ->
                mPresenter.clickLogin(mLoginFragment, true, null)
        );

        //회원가입 버튼
        mMainBinding.navigationView.imageRegister.setOnClickListener(v ->
                mPresenter.clickRegister(mRegister_termsConditionsAgreementFragment, true, null)
        );
/*
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
            mMainBinding.drawerLayout.closeDrawer(GravityCompat.END)
        );
*/
        //고객센터 버튼
        mMainBinding.navigationView.imageCustomerService.setOnClickListener(v ->
            mPresenter.clickLogout()
        );
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {

        mPresenter = new MainPresenter(this, this, getSupportFragmentManager() , mMainBinding.drawerLayout);

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

        //Fragment 객체 생성
        mLoginFragment = new LoginFragment();
        mRegister_termsConditionsAgreementFragment = new Register_TermsConditionsAgreementFragment();

        //로그인 상태
        mPresenter.loginState();
    }

    /**
     * 뒤로가기 버튼 클릭
     */
    @Override
    public void onBackPressed() {
        mPresenter.onBackPressed();
    }

    /**
     * 뒤로가기
     */
    @Override
    public void backwardMovement() {
        super.onBackPressed();
    }

    /**
     * Toast 보여주기
     */
    @Override
    public void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    /**
     * 로그인정보 보여주기
     */
    @Override
    public void showUserInfo(String userName , String userDutchMoney  , boolean state) {
        if (state) {
            mMainBinding.navigationView.layoutLogin.setVisibility(View.GONE);

            mMainBinding.Appbar.contentMain.txtUserName.setText(userName + "님, 안녕하세요!");
            mMainBinding.Appbar.contentMain.txtUserName.setVisibility(View.VISIBLE);

            mMainBinding.Appbar.contentMain.txtUserDutchMoney.setText(String.format("%,d", Integer.parseInt(userDutchMoney)) + "원");
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
     * 액티비티 닫기
     */
    @Override
    public void finish() {
        super.finish();
    }

    /**
     * 메뉴  닫기
     */
    @Override
    public void hideMenu() {
        mMainBinding.drawerLayout.closeDrawer(GravityCompat.END);
    }

    /**
     * 타이틀 변경하기
     */
    @Override
    public void changeTitle(String title) {
        mMainBinding.Appbar.txtAppBar.setText(title);
    }

    /**
     * appBar Icon 변경하기.
     */
    @Override
    public void changeAppBarIcon(boolean isMain) {
        if (isMain) {
            mMainBinding.Appbar.imageLeftMenu.setVisibility(View.VISIBLE);
            mMainBinding.Appbar.imageLeftArrow.setVisibility(View.GONE);
        } else {
            mMainBinding.Appbar.imageLeftMenu.setVisibility(View.GONE);
            mMainBinding.Appbar.imageLeftArrow.setVisibility(View.VISIBLE);
        }
    }

    /**
     * view 새로고침.
     */
    @Override
    public void updateView() {
        initData();
    }
}
