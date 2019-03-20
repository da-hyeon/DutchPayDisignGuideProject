package com.example.mp_3.dutchpaydisignguideproject.ui.register.PaymentPassword;

public interface Register_PaymentPasswordFormContract {

    interface View{
        void showRandomNumber(int index ,String randomNumber);

        void dotImagesUpdate(int index, boolean checkState);
        void Fail();
    }

    interface Presenter{
        void initRandomNumber();

        void clickNumber(String numberText);
        void clickDeleteButton();
        void clickOKButton();
    }
}
