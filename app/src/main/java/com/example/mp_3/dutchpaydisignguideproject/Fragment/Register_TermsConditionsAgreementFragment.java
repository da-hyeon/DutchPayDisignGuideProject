package com.example.mp_3.dutchpaydisignguideproject.Fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mp_3.dutchpaydisignguideproject.Interface.MoveToFragment;
import com.example.mp_3.dutchpaydisignguideproject.R;
import com.example.mp_3.dutchpaydisignguideproject.databinding.FragmentRegisterTermsConditionsAgreementBinding;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;

import java.util.Objects;

public class Register_TermsConditionsAgreementFragment extends Fragment {

    private FragmentRegisterTermsConditionsAgreementBinding mBinding;

    private ImageView image_termsConditions[];
    private MoveToFragment mMoveToFragment;

    /**
     * Activity 참조 얻기
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof MoveToFragment) {
            mMoveToFragment = (MoveToFragment) context;
        } else {
            throw new RuntimeException(context.toString());
        }
    }

    /**
     * onCreateView
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //데이터바인딩 Fragment 등록
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_terms_conditions_agreement, container, false);
        View view = mBinding.getRoot();

        //객체생성 및 데이터 초기화
        initData();

        //클릭 메소드 등록
        onClickListener();


        return view;
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {

        SessionCallback mSessionCallback = new SessionCallback();
        Session.getCurrentSession().addCallback(mSessionCallback);
        Session.getCurrentSession().checkAndImplicitOpen();

        image_termsConditions = new ImageView[]{
                mBinding.imageTermsConditions1,
                mBinding.imageTermsConditions2,
                mBinding.imageTermsConditions3,
                mBinding.imageTermsConditions4,
                mBinding.imageTermsConditions5,
                mBinding.imageTermsConditions6};
    }

    /**
     * 클릭 이벤트 처리
     */
    private void onClickListener() {

        //전체 동의 클릭
        mBinding.imageCompleteAgreement.setOnClickListener(v -> {
            Bitmap tmpBitmap = ((BitmapDrawable) mBinding.imageCompleteAgreement.getDrawable()).getBitmap();
            Drawable agreeAll_on = Objects.requireNonNull(getContext()).getResources().getDrawable(R.drawable.agree_round_on);
            Bitmap tmpBitmap1 = ((BitmapDrawable) agreeAll_on).getBitmap();

            if (tmpBitmap.equals(tmpBitmap1)) {
                mBinding.imageCompleteAgreement.setImageResource(R.drawable.agree_round_off);
                for (ImageView imageView : image_termsConditions) {
                    imageView.setImageResource(R.drawable.agree_off);
                }
            } else {
                mBinding.imageCompleteAgreement.setImageResource(R.drawable.agree_round_on);
                for (ImageView imageView : image_termsConditions) {
                    imageView.setImageResource(R.drawable.agree_on);
                }
            }
        });

        //약관 클릭
        for (ImageView imageView : image_termsConditions)
            imageView.setOnClickListener(v -> {
                Bitmap tmpBitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                Drawable agree_on = Objects.requireNonNull(getContext()).getResources().getDrawable(R.drawable.agree_on);
                Bitmap tmpBitmap1 = ((BitmapDrawable) agree_on).getBitmap();
                if (tmpBitmap.equals(tmpBitmap1)) {
                    imageView.setImageResource(R.drawable.agree_off);
                } else {
                    imageView.setImageResource(R.drawable.agree_on);
                }
                clickAllCheck();
            });

        //회원가입 클릭
        mBinding.imageViewRegister.setOnClickListener(v -> {
            Bitmap tmpBitmap = ((BitmapDrawable) mBinding.imageCompleteAgreement.getDrawable()).getBitmap();
            Drawable agreeAll_on = Objects.requireNonNull(getContext()).getResources().getDrawable(R.drawable.agree_round_on);
            Bitmap tmpBitmap1 = ((BitmapDrawable) agreeAll_on).getBitmap();

            //전체동의가 켜져있으면 넘어가기
            if( tmpBitmap.equals(tmpBitmap1)) {
                mMoveToFragment.moveToFormFragment();
            } else {
                Toast.makeText(getContext(), "약관에 모두 동의하셔야 합니다.", Toast.LENGTH_SHORT).show();
            }
        });


    }

    /**
     * 약관이 모두 체크되었나?
     */
    private void clickAllCheck() {
        for (ImageView imageView : image_termsConditions) {
            Bitmap tmpBitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            Drawable agree_on = Objects.requireNonNull(getContext()).getResources().getDrawable(R.drawable.agree_on);
            Bitmap tmpBitmap1 = ((BitmapDrawable) agree_on).getBitmap();
            if (!tmpBitmap.equals(tmpBitmap1)) {
                mBinding.imageCompleteAgreement.setImageResource(R.drawable.agree_round_off);
                return;
            }
        }
        mBinding.imageCompleteAgreement.setImageResource(R.drawable.agree_round_on);
    }


    @Override
    public void onResume() {
        super.onResume();

        for (ImageView imageView : image_termsConditions) {
            imageView.setImageResource(R.drawable.agree_off);
        }
        mBinding.imageCompleteAgreement.setImageResource(R.drawable.agree_round_off);

    }

    /**
     * 카카오톡 정보 받기
     */
    public void request(){
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.d("error", "Session Closed Error is " + errorResult.toString());
            }

            @Override
            public void onNotSignedUp() {

            }

            @Override
            public void onSuccess(UserProfile result) {
                Bitmap tmpBitmap = ((BitmapDrawable) mBinding.imageCompleteAgreement.getDrawable()).getBitmap();
                Drawable agreeAll_on = Objects.requireNonNull(getContext()).getResources().getDrawable(R.drawable.agree_round_on);
                Bitmap tmpBitmap1 = ((BitmapDrawable) agreeAll_on).getBitmap();

                //전체동의가 켜져있으면 넘어가기
                if( tmpBitmap.equals(tmpBitmap1)) {
                    Toast.makeText(getContext(), "사용자 이름은 " + result.getNickname(), Toast.LENGTH_SHORT).show();
                    mMoveToFragment.moveToFormFragment();
                } else {
                    Toast.makeText(getContext(), "약관에 모두 동의하셔야 합니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 카카오톡 콜백 메소드
     */
    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            request();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Log.d("error", "Session Fail Error is " + exception.getMessage());
        }
    }

}
