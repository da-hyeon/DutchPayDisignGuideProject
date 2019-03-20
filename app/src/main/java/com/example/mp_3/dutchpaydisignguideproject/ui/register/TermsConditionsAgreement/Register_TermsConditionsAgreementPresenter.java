package com.example.mp_3.dutchpaydisignguideproject.ui.register.TermsConditionsAgreement;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.FragmentManager;
import android.widget.ImageView;

import com.example.mp_3.dutchpaydisignguideproject.ui.main.MainContract;
import com.example.mp_3.dutchpaydisignguideproject.ui.main.MainPresenter;
import com.example.mp_3.dutchpaydisignguideproject.ui.register.Form.Register_FormFragment;
import com.example.mp_3.dutchpaydisignguideproject.ui.register.ViewAllTermsConditions.Register_ViewAllTermsConditionsFragment;
import com.kakao.auth.AuthType;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;

import java.util.Objects;

public class Register_TermsConditionsAgreementPresenter implements Register_TermsConditionsAgreementContract.Presenter {

    private Register_TermsConditionsAgreementContract.View mView;
    private MainContract.Presenter mMainPresenter;

    private Context mContext;
    private Activity mActivity;

    private boolean mAllTOS;
    private boolean mTOSCheckArray[];

    public Register_TermsConditionsAgreementPresenter(Register_TermsConditionsAgreementContract.View mView, Context mContext, Activity mActivity, FragmentManager fragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mActivity = mActivity;

        mMainPresenter = new MainPresenter(mContext, fragmentManager);

        mTOSCheckArray = new boolean[6];
        mAllTOS = false;

        SessionCallback mSessionCallback = new SessionCallback();
        Session.getCurrentSession().addCallback(mSessionCallback);
        Session.getCurrentSession().checkAndImplicitOpen();
    }

    @Override
    public void refreshData(Bundle bundle) {
        if (bundle != null) {
            boolean allCheck = true;
            mTOSCheckArray = bundle.getBooleanArray("checked");

            for (int i = 0; i < mTOSCheckArray.length; i++) {
                mView.changeTOS(i, mTOSCheckArray[i]);
                if ( !mTOSCheckArray[i] ){
                    allCheck = false;
                }
            }
            mAllTOS = allCheck;
        }

        mView.changeAllTOS(mAllTOS);
    }

    /**
     * 전체동의 클릭
     * id -> agree_round_on
     */
    @Override
    public void clickAllTOS(ImageView imageView, @DrawableRes int id) {

        if (comparisonObjects(imageView, id)) {
            for (int i = 0; i < 6; i++) {
                mAllTOS = false;

                mView.changeTOS(i, mAllTOS);
                mTOSCheckArray[i] = mAllTOS;
            }

        } else {
            mAllTOS = true;

            for (int i = 0; i < 6; i++) {
                mView.changeTOS(i, mAllTOS);
                mTOSCheckArray[i] = mAllTOS;
            }
        }
        mView.changeAllTOS(mAllTOS);
    }

    /**
     * 약관동의 클릭
     * id -> agree_on
     */
    @Override
    public void clickTOS(int index, ImageView imageView, @DrawableRes int id) {
        if (comparisonObjects(imageView, id)) {
            mAllTOS = false;

            mView.changeTOS(index, mAllTOS);
            mTOSCheckArray[index] = mAllTOS;
        } else {
            mView.changeTOS(index, true);
            mTOSCheckArray[index] = true;
        }

        for (int i = 0; i < mTOSCheckArray.length; i++) {
            if (!mTOSCheckArray[i]) {

                mView.changeAllTOS(false);
                return;
            }
        }
        //낱개 동의항목이 모두 true일때 전체동의 이미지를 바꿈.
        mAllTOS = true;
        mView.changeAllTOS(mAllTOS);
    }

    /**
     * 전체보기 클릭
     * index -> 클릭한 약관동의 index
     */
    @Override
    public void clickAllView(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt("num", index);
        bundle.putBooleanArray("checked", mTOSCheckArray);

        mMainPresenter.moveFragment(new Register_ViewAllTermsConditionsFragment(), false, bundle);
    }

    /**
     * 회원가입 클릭
     */
    @Override
    public void clickRegister() {
        if (mAllTOS) {
            mMainPresenter.moveFragment(new Register_FormFragment(), false, null);
        } else {
            mView.showToast("약관에 모두 동의하셔야 합니다.");
        }
    }

    /**
     * 카카오 클릭
     */
    @Override
    public void clickKakao() {
        if (mAllTOS) {
            Session.getCurrentSession().open(AuthType.KAKAO_LOGIN_ALL, mActivity);
        } else {
            mView.showToast("약관에 모두 동의하셔야 합니다.");
        }
    }

    /**
     * 이미지 비교하여 boolean 값 리턴
     */
    private boolean comparisonObjects(ImageView imageView, int id) {
        Drawable agree_on = Objects.requireNonNull(mContext).getResources().getDrawable(id);
        Bitmap tmpBitmap1 = ((BitmapDrawable) agree_on).getBitmap();

        Bitmap tmpBitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

        if (tmpBitmap.equals(tmpBitmap1)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 카카오톡 요청 메소드
     */
    private void requestKakao() {
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                mView.showLog("error", "Session Closed Error is " + errorResult.toString());
            }

            @Override
            public void onNotSignedUp() {

            }

            @Override
            public void onSuccess(UserProfile result) {
                mView.showToast("사용자 이름은 " + result.getNickname());
                mMainPresenter.moveFragment(new Register_FormFragment(), false, null);
            }
        });
    }


    /**
     * 카카오톡 콜백 class
     */
    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            requestKakao();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            mView.showLog("error", "Session Fail Error is " + exception.getMessage());

        }
    }
}
