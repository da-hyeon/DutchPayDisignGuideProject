package com.example.mp_3.dutchpaydisignguideproject.ui.login;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.example.mp_3.dutchpaydisignguideproject.ui.login.LoginContract;
import com.example.mp_3.dutchpaydisignguideproject.data.db.model.User;
import com.example.mp_3.dutchpaydisignguideproject.R;
import com.example.mp_3.dutchpaydisignguideproject.ui.main.MainActivity;

import java.util.Objects;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;
    private User user;
    private Context mContext;
    private Activity mActivity;


    public LoginPresenter(LoginContract.View mView, Context mContext , Activity Activity) {
        this.mView = mView;
        this.mContext = mContext;
        this.mActivity = Activity;
        user = User.getInstance();
    }

    @Override
    public void handleLogin(String userID, String userPassword) {
        if(userID.equals("") || userPassword.equals("")){
            mView.loginFail();
        } else{
            if(userID.equals(user.getUserEmail()) && userPassword.equals(user.getUserPassword())) {
                User user = User.getInstance();
                user.setUserState(true);
                ((MainActivity)mActivity).updateView();
                ((MainActivity)mActivity).changeAppBarIcon(true);
                mView.loginSuccessFully();
            } else {
                mView.loginFail();
            }
        }
    }

    @Override
    public void currentCheckState(ImageView imageView) {
        Bitmap tmpBitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        Drawable agree_on = Objects.requireNonNull(mContext).getResources().getDrawable(R.drawable.agree_on);
        Bitmap tmpBitmap1 = ((BitmapDrawable) agree_on).getBitmap();

        if (tmpBitmap.equals(tmpBitmap1)) {
            imageView.setImageResource(R.drawable.agree_on);
        } else {
            imageView.setImageResource(R.drawable.agree_off);
        }
    }
}
