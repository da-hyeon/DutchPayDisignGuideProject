package com.example.mp_3.dutchpaydisignguideproject.ui.register.TermsConditionsAgreement;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

public interface Register_TermsConditionsAgreementContract {

    interface View{
        void changeAllTermsConditionsButton(boolean state);
        void changeTermsConditionsButton(ImageView imageView , boolean state);
        void changeAllTermsConditions(boolean state);
        void showToast(String content);
        void showLog(String tag, String content);
        void moveFragment();
        void moveAllView(Bundle bundle);
    }

    interface Presenter{
        void refreshData(Bundle bundle);

        void allTermsConditionsButtonCheck(boolean state);
        void termsConditionsButtonCheck(int index , ImageView imageView , boolean state);
        void initTermsConditionsButtonCheck(ImageView[] imageViews);
        void allChecked(boolean state);
        void allViewButtonClick(int index);
        void kakaoRequest(boolean state);
        void nextButtonClick(boolean state);
        void kakaoButtonClick(boolean state);

        boolean comparisonObjects(ImageView[] imageViews , ImageView imageView , @DrawableRes int id);
    }
}
