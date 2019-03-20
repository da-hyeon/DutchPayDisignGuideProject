package com.example.mp_3.dutchpaydisignguideproject.ui.register.PaymentPassword;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.example.mp_3.dutchpaydisignguideproject.ui.main.MainContract;
import com.example.mp_3.dutchpaydisignguideproject.ui.main.MainPresenter;
import com.example.mp_3.dutchpaydisignguideproject.ui.register.Success.Register_SuccessFragment;

import java.util.ArrayList;
import java.util.Collections;

public class Register_PaymentPasswordFormContractPresenter implements Register_PaymentPasswordFormContract.Presenter {

    private Register_PaymentPasswordFormContract.View mView;
    private MainContract.Presenter mMainPresenter;
    private String mPassword;

    public Register_PaymentPasswordFormContractPresenter(Register_PaymentPasswordFormContract.View mView , Context mContext, FragmentManager mFragmentManager) {
        this.mView = mView;
        mMainPresenter = new MainPresenter(mContext , mFragmentManager);
        mPassword = "";
    }


    @Override
    public void initRandomNumber() {
        //난수생성
        ArrayList<Integer> randomNumber = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            randomNumber.add(i);
        }
        Collections.shuffle(randomNumber);
        for (int i = 0; i < 10; i++) {
            mView.showRandomNumber(i , randomNumber.get(i).toString());
        }
    }

    @Override
    public void clickNumber(String numberText) {

        if (mPassword.length() < 6) {
            mPassword += numberText;
        }

        for (int i = 0; i < 6; i++) {
            if (i < mPassword.length()) {
                mView.dotImagesUpdate(i, true);
            } else {
                mView.dotImagesUpdate(i, false);
            }
        }
    }

    @Override
    public void clickDeleteButton() {
        mPassword = "";
        for (int i = 0; i < 6; i++) {
            mView.dotImagesUpdate(i,   false);
        }
    }

    @Override
    public void clickOKButton() {
        if (mPassword.length() == 6) {
            mMainPresenter.moveFragment(new Register_SuccessFragment(), true, null);
        } else {
            mView.Fail();
        }
    }
}
