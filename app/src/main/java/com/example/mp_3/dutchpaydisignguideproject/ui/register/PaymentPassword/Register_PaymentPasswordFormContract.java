package com.example.mp_3.dutchpaydisignguideproject.ui.register.PaymentPassword;

import android.widget.ImageView;

public interface Register_PaymentPasswordFormContract {

    interface View{
        void currentInputNumber(String number);
        void dotImagesUpdate(ImageView imageView , boolean checkState);
        void clearPassword();
        void SuccessFully();
        void Fail();
    }

    interface Presenter{
        void clickNumberButton(String password , String numberText);
        void clickDeleteButton();
        void clickOKButton(String password);
        void inputImageUpdate(String password , ImageView[] imageView);

    }
}
