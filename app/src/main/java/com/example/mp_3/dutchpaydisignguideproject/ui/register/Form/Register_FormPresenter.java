package com.example.mp_3.dutchpaydisignguideproject.ui.register.Form;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.example.mp_3.dutchpaydisignguideproject.ui.main.MainContract;
import com.example.mp_3.dutchpaydisignguideproject.ui.main.MainPresenter;
import com.example.mp_3.dutchpaydisignguideproject.ui.register.PaymentPassword.Register_PaymentPasswordFormFragment;

public class Register_FormPresenter implements Register_FormContract.Presenter {

    private MainContract.Presenter mMainPresenter;

    public Register_FormPresenter(Context context , FragmentManager fragmentManager) {
        this.mMainPresenter =  new MainPresenter(context , fragmentManager);
    }

    @Override
    public void nextClick() {
        //프래그먼트 이동
        mMainPresenter.moveFragment(new Register_PaymentPasswordFormFragment()
                , false, null);
    }
}
