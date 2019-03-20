package com.example.mp_3.dutchpaydisignguideproject.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import com.example.mp_3.dutchpaydisignguideproject.R;
import com.example.mp_3.dutchpaydisignguideproject.data.db.model.User;
import com.example.mp_3.dutchpaydisignguideproject.ui.register.ViewAllTermsConditions.Register_ViewAllTermsConditionsFragment;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private long mLastTime;
    private User mUser;
    private DrawerLayout mDrawerLayout;

    /**
     * 생성자
     */
    public MainPresenter(MainContract.View mView, Context mContext, FragmentManager mFragmentManager, DrawerLayout mDrawerLayout) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        this.mDrawerLayout = mDrawerLayout;
        mUser = User.getInstance();
    }

    /**
     * 생성자
     */
    public MainPresenter(Context mContext, FragmentManager mFragmentManager) {
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
    }

    /**
     * 로그인상태
     */
    @Override
    public void loginState() {
        mView.showUserInfo(mUser.getUserName() , mUser.getUserDutchMoney() , mUser.isUserState());
    }

    /**
     * Home버튼 클릭
     */
    @Override
    public void clickHome() {
        //메뉴를 닫고 , title 바꾸고 , appBarIcon바꾸고, 모든 프래그먼트를 닫는다.
        mView.hideMenu();
        mView.changeTitle("");
        mView.changeAppBarIcon(true);
        allFragmentClose();
    }

    /**
     * Login버튼 클릭
     */
    @Override
    public void clickLogin(Fragment fragment, boolean isMainFragment, Bundle bundle) {
        //메뉴를 닫고 , title 바꾸고 , appBarIcon바꾸고, 프래그먼트 이동
        mView.hideMenu();
        mView.changeTitle("로그인");
        mView.changeAppBarIcon(false);
        moveFragment(fragment, isMainFragment, bundle);
    }

    /**
     * 회원가입버튼 클릭
     */
    @Override
    public void clickRegister(Fragment fragment, boolean isMainFragment, Bundle bundle) {
        //메뉴를 닫고 , title 바꾸고 , appBarIcon바꾸고, 프래그먼트 이동
        mView.hideMenu();
        mView.changeTitle("회원가입");
        mView.changeAppBarIcon(false);
        moveFragment(fragment, isMainFragment, bundle);
    }

    /**
     * 로그아웃버튼 클릭
     */
    @Override
    public void clickLogout() {
        //메뉴를 닫고 ,유저 상태를 로그아웃으로 변경하고 , 홈으로 이동하고,  뷰를 업데이트한다.
        mView.hideMenu();
        mUser.setUserState(false);
        clickHome();
        mView.updateView();
    }

    /**
     * 프래그먼트 이동
     */
    @Override
    public void moveFragment(Fragment fragment, boolean isMainFragment, Bundle bundle) {
        //메인 프래그먼트일때 Fragment 모든 스택 제거.
        if (isMainFragment) {
            mFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.content_main, fragment);
        fragmentTransaction.commit();
    }

    /**
     * 모든 프래그먼트 삭제
     */
    @Override
    public void allFragmentClose() {
        if (!mFragmentManager.getFragments().isEmpty()) {
            mFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    /**
     * 뒤로가기 버튼 클릭
     */
    @Override
    public void onBackPressed() {

        List fragmentList = mFragmentManager.getFragments();
        for (int i = 0; i < fragmentList.size(); i++) {
            if (fragmentList.get(i) instanceof Register_ViewAllTermsConditionsFragment) {
                ((Register_ViewAllTermsConditionsFragment) fragmentList.get(i)).onBackPress();
                return;
            }
        }

        //메뉴가 열려있으면 닫는다.
        if (mDrawerLayout.isDrawerOpen(GravityCompat.END)) {
            mView.hideMenu();
        } else {
            //메뉴가 열려있지 않고 프래그먼트가 비어있다면 2번 눌러 앱 종료 메소드 실행
            if (mFragmentManager.getFragments().isEmpty()) {
                exitApp();
            } else {
                //프래그먼트가 있다면 종료.
                mView.backwardMovement();

                //프래그먼트를 종료헀을때 홈화면이면
                if (mFragmentManager.getFragments().isEmpty()) {
                    //title 바꾸고 , appBarIcon바꾼다.
                    mView.changeTitle("");
                    mView.changeAppBarIcon(true);
                }
            }
        }
    }


    /**
     * 2번 눌러 앱 종료
     */
    @Override
    public void exitApp() {
        if (mLastTime + 2000 < System.currentTimeMillis() || mLastTime == 0) {
            mView.showToast("한 번 더 누르면 종료됩니다.");
            mLastTime = System.currentTimeMillis();
        } else {
            mView.finish();
        }
    }
}
