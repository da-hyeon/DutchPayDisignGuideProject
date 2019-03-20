package com.example.mp_3.dutchpaydisignguideproject.ui.register.Success;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.example.mp_3.dutchpaydisignguideproject.data.db.model.User;
import com.example.mp_3.dutchpaydisignguideproject.ui.main.MainActivity;
import com.example.mp_3.dutchpaydisignguideproject.ui.main.MainContract;
import com.example.mp_3.dutchpaydisignguideproject.ui.main.MainPresenter;

public class Register_SuccessPresenter implements Register_SuccessContract.Presenter {

    private MainContract.Presenter mMainPresenter;
    private Activity mActivity;
    private User user;

    public Register_SuccessPresenter(Context context , FragmentManager fragmentManager , Activity mActivity) {
        mMainPresenter = new MainPresenter(context , fragmentManager);
        user = User.getInstance();
        this.mActivity = mActivity;
    }

    @Override
    public void clickAppStart() {
        user.setUserState(true);
        ((MainActivity)mActivity).updateView();
        ((MainActivity)mActivity).changeAppBarIcon(true);
        mMainPresenter.allFragmentClose();
    }
}
