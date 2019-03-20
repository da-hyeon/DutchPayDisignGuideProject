package com.example.mp_3.dutchpaydisignguideproject.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

public interface MainContract {


    interface View{
        void showToast(String content);
        void showUserInfo(String userName , String userDutchMoney , boolean state);

        void hideMenu();
        void changeTitle(String title);
        void changeAppBarIcon(boolean isMain);
        void finish();
        void updateView();

        void backwardMovement();
    }

    interface Presenter{
        void loginState();

        void clickHome();
        void clickLogin(Fragment fragment, boolean isMainFragment, Bundle bundle);
        void clickRegister(Fragment fragment, boolean isMainFragment, Bundle bundle);
        void clickLogout();

        void moveFragment(Fragment fragment, boolean isMainFragment, Bundle bundle);
        void allFragmentClose();

        void onBackPressed();
        void exitApp();
    }
}