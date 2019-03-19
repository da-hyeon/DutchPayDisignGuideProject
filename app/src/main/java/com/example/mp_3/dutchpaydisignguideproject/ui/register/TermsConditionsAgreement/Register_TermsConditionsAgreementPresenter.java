package com.example.mp_3.dutchpaydisignguideproject.ui.register.TermsConditionsAgreement;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

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
    private Context mContext;
    private Activity mActivity;

    private boolean mTermsConditionsAgreementChecked[];

    public Register_TermsConditionsAgreementPresenter(Register_TermsConditionsAgreementContract.View mView, Context mContext, Activity mActivity) {
        this.mView = mView;
        this.mContext = mContext;
        this.mActivity = mActivity;

        mTermsConditionsAgreementChecked = new boolean[6];

        SessionCallback mSessionCallback = new SessionCallback();
        Session.getCurrentSession().addCallback(mSessionCallback);
        Session.getCurrentSession().checkAndImplicitOpen();
    }

    @Override
    public void refreshData(Bundle bundle) {
        if(bundle != null) {
            mTermsConditionsAgreementChecked = bundle.getBooleanArray("checked");
        }
    }

    /**
     * 약관 전체 동의
     */
    @Override
    public void allTermsConditionsButtonCheck(boolean state) {

        if (state) {
            mView.changeAllTermsConditionsButton(false);
            mView.changeAllTermsConditions(false);
        } else {
            mView.changeAllTermsConditionsButton(true);
            mView.changeAllTermsConditions(true);
        }
    }

    /**
     * 약관 동의
     */
    @Override
    public void termsConditionsButtonCheck(int index , ImageView imageView, boolean state) {

        if (state) {
            mView.changeTermsConditionsButton(imageView, false);
            mTermsConditionsAgreementChecked[index] = false;
        } else {
            mView.changeTermsConditionsButton(imageView, true);
            mTermsConditionsAgreementChecked[index] = true;
        }
    }

    @Override
    public void initTermsConditionsButtonCheck(ImageView[] imageViews) {
        int i = 0 ;
        for(ImageView imageView : imageViews){
            if(mTermsConditionsAgreementChecked[i]){
                mView.changeTermsConditionsButton(imageView, true);
            } else {
                mView.changeTermsConditionsButton(imageView, false);
            }
            i++;
        }
    }

    /**
     * 전부 체크되었는지 확인
     */
    @Override
    public void allChecked(boolean state) {

        if (state) {
            mView.changeAllTermsConditionsButton(true);
        } else {
            mView.changeAllTermsConditionsButton(false);
        }
    }

    @Override
    public void allViewButtonClick(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt("num", index);
        bundle.putBooleanArray("checked", mTermsConditionsAgreementChecked);

        mView.moveAllView(bundle);
    }


    @Override
    public void kakaoRequest(boolean state) {
        if (state) {
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
                    mView.moveFragment();
                }
            });
        } else {
            mView.showToast("약관에 모두 동의하셔야 합니다.");
        }
    }

    @Override
    public void nextButtonClick(boolean state) {
        if (state) {
            mView.moveFragment();
        } else {
            mView.showToast("약관에 모두 동의하셔야 합니다.");
        }
    }

    @Override
    public void kakaoButtonClick(boolean state) {
        if (state) {
            Session.getCurrentSession().open(AuthType.KAKAO_LOGIN_ALL, mActivity);
        } else {
            mView.showToast("약관에 모두 동의하셔야 합니다.");
        }
    }

    /**
     * 이미지 비교하여 결과값 리턴
     */
    @Override
    public boolean comparisonObjects(ImageView[] imageViews, ImageView imageView, int id) {
        Drawable agree_on = Objects.requireNonNull(mContext).getResources().getDrawable(id);
        Bitmap tmpBitmap1 = ((BitmapDrawable) agree_on).getBitmap();

        if (imageViews == null) {
            Bitmap tmpBitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

            if (tmpBitmap.equals(tmpBitmap1)) {
                return true;
            } else {
                return false;
            }
        } else {
            for (ImageView image : imageViews) {
                Bitmap tmpBitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
                if (!tmpBitmap.equals(tmpBitmap1)) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 카카오톡 콜백 메소드
     */
    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            kakaoRequest(true);
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            mView.showLog("error", "Session Fail Error is " + exception.getMessage());

        }
    }
}
