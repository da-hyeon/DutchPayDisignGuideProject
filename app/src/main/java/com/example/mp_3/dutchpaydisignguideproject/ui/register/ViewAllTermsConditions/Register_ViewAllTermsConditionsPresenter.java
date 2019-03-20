package com.example.mp_3.dutchpaydisignguideproject.ui.register.ViewAllTermsConditions;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.ImageView;

import com.example.mp_3.dutchpaydisignguideproject.ui.main.MainContract;
import com.example.mp_3.dutchpaydisignguideproject.ui.main.MainPresenter;
import com.example.mp_3.dutchpaydisignguideproject.ui.register.TermsConditionsAgreement.Register_TermsConditionsAgreementFragment;

import java.util.Objects;

public class Register_ViewAllTermsConditionsPresenter implements Register_ViewAllTermsConditionsContract.Presenter {

    private Register_ViewAllTermsConditionsContract.View mView;

    private Context mContext;
    private MainContract.Presenter mMainPresenter;

    private String mTermsConditionsTitles[];
    private String mTermsConditionsContents[];

    private int mTermsConditionsNumber;
    private boolean mTermsConditionsChecked[];

    public Register_ViewAllTermsConditionsPresenter(Register_ViewAllTermsConditionsContract.View mView, Context mContext, FragmentManager fragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mMainPresenter =  new MainPresenter(mContext , fragmentManager);

        //제목
        mTermsConditionsTitles = new String[]{
                "서비스 기본 약관 [필수]" ,
                "개인정보 수집 약관 [필수]" ,
                "휴대폰 인증 서비스 약관 [필수]" ,
                "전자거래 이용 약관 [필수]" ,
                "푸시 서비스 약관 [선택]" ,
                "마케팅 이용 동의 약관 [선택]"
        };

        //내용
        mTermsConditionsContents = new String[]{
                "서비스 기본 약관 [필수]" ,
                "개인정보 수집 약관 [필수]" ,
                "휴대폰 인증 서비스 약관 [필수]" ,
                "전자거래 이용 약관 [필수]" ,
                "푸시 서비스 약관 [선택]" ,
                "마케팅 이용 동의 약관 [선택]"
        };
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

    @Override
    public void getData(Bundle bundle) {
        if (bundle != null) {
            mTermsConditionsNumber = bundle.getInt("num");
            mTermsConditionsChecked = bundle.getBooleanArray("checked");

            mView.changeTOS(mTermsConditionsChecked[mTermsConditionsNumber]);

            mView.changeTitle(mTermsConditionsTitles[mTermsConditionsNumber]);
            mView.changeContent(mTermsConditionsContents[mTermsConditionsNumber]);
        }
    }

    @Override
    public void clickTOS(ImageView imageView, int id) {
        if(comparisonObjects(imageView, id)){
            mView.changeTOS(false);
            mTermsConditionsChecked[mTermsConditionsNumber] = false;
        } else {
            mView.changeTOS(true);
            mTermsConditionsChecked[mTermsConditionsNumber] = true;
        }
    }

    @Override
    public void clickBackPressed() {
        Bundle bundle = new Bundle();
        bundle.putInt("num" , mTermsConditionsNumber);
        bundle.putBooleanArray("checked", mTermsConditionsChecked);
        mMainPresenter.moveFragment(new Register_TermsConditionsAgreementFragment(), true , bundle);
    }
}
