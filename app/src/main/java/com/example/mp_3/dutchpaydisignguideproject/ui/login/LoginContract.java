package com.example.mp_3.dutchpaydisignguideproject.ui.login;

import android.widget.ImageView;

public interface LoginContract {

    interface View{
        void loginSuccessFully();
        void loginFail();
    }

    interface Presenter{
        void handleLogin(String userID , String userPassword);
        void currentCheckState(ImageView imageView);
    }
}
