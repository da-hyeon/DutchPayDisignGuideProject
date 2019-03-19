package com.example.mp_3.dutchpaydisignguideproject.ui.register.PaymentPassword;

import android.widget.ImageView;

import com.example.mp_3.dutchpaydisignguideproject.data.db.model.User;

public class Register_PaymentPasswordFormContractPresenter implements Register_PaymentPasswordFormContract.Presenter {

    private Register_PaymentPasswordFormContract.View mView;
    private User user;

    public Register_PaymentPasswordFormContractPresenter(Register_PaymentPasswordFormContract.View mView) {
        this.mView = mView;
        user = User.getInstance();
    }


    @Override
    public void clickNumberButton(String password , String numberText) {
        if (password.length() < 6) {
            password += numberText;
            mView.currentInputNumber(password);
        }
    }

    @Override
    public void inputImageUpdate(String password , ImageView[] imageView) {
        for (int i = 0; i < imageView.length; i++) {
            if (i < password.length()) {
                mView.dotImagesUpdate( imageView[i] , true);
            } else {
                mView.dotImagesUpdate( imageView[i] , false);
            }
        }
    }

    @Override
    public void clickDeleteButton() {
        mView.clearPassword();
    }

    @Override
    public void clickOKButton(String password ) {
        if (password.length() == 6) {
           mView.SuccessFully();
        } else {
            mView.Fail();
        }
    }
}
