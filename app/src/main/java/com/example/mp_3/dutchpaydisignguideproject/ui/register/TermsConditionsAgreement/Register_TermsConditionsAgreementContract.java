package com.example.mp_3.dutchpaydisignguideproject.ui.register.TermsConditionsAgreement;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

public interface Register_TermsConditionsAgreementContract {

    interface View{
        void changeAllTOS(boolean state);
        void changeTOS(int index, boolean state);
        void showToast(String content);
        void showLog(String tag, String content);
    }

    interface Presenter{
        void refreshData(Bundle bundle);

        void clickAllTOS(ImageView imageView , @DrawableRes int id);
        void clickTOS(int index , ImageView imageView , @DrawableRes int id);
        void clickAllView(int index);
        void clickRegister();
        void clickKakao();
    }
}
