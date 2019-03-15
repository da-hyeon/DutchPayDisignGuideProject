package com.example.mp_3.dutchpaydisignguideproject.Fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mp_3.dutchpaydisignguideproject.Interface.MoveToFragment;
import com.example.mp_3.dutchpaydisignguideproject.R;
import com.example.mp_3.dutchpaydisignguideproject.databinding.FragmentLoginBinding;

import java.util.Objects;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding mBinding;

    private MoveToFragment mMoveToFragment;
    /**
     * Activity참조 얻기
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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater , R.layout.fragment_login ,container , false);
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

    }

    /**
     * 클릭 이벤트 처리
     */
    private void onClickListener() {
        mBinding.imageAutoLoginCheck.setOnClickListener( v->{
            Bitmap tmpBitmap = ((BitmapDrawable) mBinding.imageAutoLoginCheck.getDrawable()).getBitmap();
            Drawable agree_on = Objects.requireNonNull(getContext()).getResources().getDrawable(R.drawable.agree_on);
            Bitmap tmpBitmap1 = ((BitmapDrawable) agree_on).getBitmap();
            if (tmpBitmap.equals(tmpBitmap1)) {
                mBinding.imageAutoLoginCheck.setImageResource(R.drawable.agree_off);
            } else {
                mBinding.imageAutoLoginCheck.setImageResource(R.drawable.agree_on);
            }
        });

        mBinding.imageUserRegister.setOnClickListener(v-> mMoveToFragment.moveToTermsConditionsAgreementFragment());

        mBinding.btnLogin.setOnClickListener(v-> mMoveToFragment.moveToHome(true));
    }
}
