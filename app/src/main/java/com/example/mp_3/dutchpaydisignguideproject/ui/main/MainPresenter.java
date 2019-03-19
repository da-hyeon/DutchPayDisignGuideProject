package com.example.mp_3.dutchpaydisignguideproject.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.mp_3.dutchpaydisignguideproject.R;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private long mLastTime;

    public MainPresenter(MainContract.View mView, Context mContext, FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
    }

    public MainPresenter(Context mContext, FragmentManager mFragmentManager){
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
    }

    @Override
    public void allFragmentClose() {
        if (!mFragmentManager.getFragments().isEmpty()) {
            mFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    @Override
    public void moveFragment( Fragment fragment, boolean isMainFragment , Bundle bundle ) {
        //메인 프래그먼트일때 Fragment 모든 스택 제거.
        if (isMainFragment) {
            mFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if(bundle != null) {
            fragment.setArguments(bundle);
        }

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.content_main, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void titleChange(String title) {
        mView.titleChange(title);
    }

    @Override
    public void isMain(boolean isMain) {
        mView.appBarIconChange(isMain);
        mView.titleChange("");
    }

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
