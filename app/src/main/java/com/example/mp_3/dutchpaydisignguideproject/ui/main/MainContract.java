package com.example.mp_3.dutchpaydisignguideproject.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

public interface MainContract {


    interface View{
        void titleChange(String title);
        void appBarIconChange(boolean isMain);
        void showToast(String content);
        void finish();
        void viewUpdate();
    }

    interface Presenter{
        void moveFragment(Fragment fragment, boolean isMainFragment, Bundle bundle , boolean isAddToStack);
        void titleChange(String title);
        void isMain( boolean isMain );
        void exitApp();

        //모든 프래그먼트 닫기
        void allFragmentClose();
    }
}
