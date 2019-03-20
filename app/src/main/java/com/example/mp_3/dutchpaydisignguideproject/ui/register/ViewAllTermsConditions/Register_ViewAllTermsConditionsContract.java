package com.example.mp_3.dutchpaydisignguideproject.ui.register.ViewAllTermsConditions;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

public interface Register_ViewAllTermsConditionsContract {

    interface View {
        void changeTitle(String title);
        void changeContent(String content);
        void changeTOS(boolean state);
    }

    interface Presenter{
        void getData(Bundle bundle);
        void clickTOS(ImageView imageView , @DrawableRes int id);
        void clickBackPressed();
    }
}
