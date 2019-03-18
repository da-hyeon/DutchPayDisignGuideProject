package com.example.mp_3.dutchpaydisignguideproject.Fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mp_3.dutchpaydisignguideproject.Interface.MoveToFragment;
import com.example.mp_3.dutchpaydisignguideproject.R;
import com.example.mp_3.dutchpaydisignguideproject.databinding.FragmentRegisterPaymentPasswordFormBinding;

import java.util.ArrayList;
import java.util.Collections;

public class Register_PaymentPasswordFormFragment extends Fragment {

    private FragmentRegisterPaymentPasswordFormBinding mBinding;
    private MoveToFragment mMoveToFragment;

    private String mPassword;

    private TextView mNumberTextViews[];
    private ImageView mDotImage[];

    /**
     * Activity 참조 얻기
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MoveToFragment) {
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register__payment_password_form, container, false);
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
        mPassword = "";

        //난수생성
        ArrayList<Integer> ranNumber = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ranNumber.add(i);
        }
        Collections.shuffle(ranNumber);

        //숫자데이터
        mNumberTextViews = new TextView[]{
                mBinding.txtPw0,
                mBinding.txtPw1,
                mBinding.txtPw2,
                mBinding.txtPw3,
                mBinding.txtPw4,
                mBinding.txtPw5,
                mBinding.txtPw6,
                mBinding.txtPw7,
                mBinding.txtPw8,
                mBinding.txtPw9
        };

        // 점 표시
        mDotImage = new ImageView[]{
                mBinding.imageDot,
                mBinding.imageDot1,
                mBinding.imageDot2,
                mBinding.imageDot3,
                mBinding.imageDot4,
                mBinding.imageDot5
        };

        for (int i = 0; i < mNumberTextViews.length; i++) {
            mNumberTextViews[i].setText(ranNumber.get(i) + "");
        }
    }

    /**
     * 클릭 이벤트 처리
     */
    private void onClickListener() {

        //숫자 버튼
        for (int i = 0; i < mNumberTextViews.length; i++) {
            int finalI = i;
            mNumberTextViews[i].setOnClickListener(v -> inputPassword(mNumberTextViews[finalI].getText().toString()));
        }

        //삭제 버튼
        mBinding.viewDelete.setOnClickListener(v -> deletePassword());

        //확인 버튼
        mBinding.viewOk.setOnClickListener(v -> {
            if (mPassword.length() == 6) {
                mMoveToFragment.moveToSuccessFragment();
            } else {
                Toast.makeText(getContext(), "결제 비밀번호는 6자리 입니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 비밀번호 입력
     */
    private void inputPassword(String number) {
        if (mPassword.length() < 6) {
            mPassword += number;
            Toast.makeText(getContext(), mPassword, Toast.LENGTH_SHORT).show();
            for (int i = 0; i < mDotImage.length; i++) {
                if (i < mPassword.length()) {
                    mDotImage[i].setImageResource(R.drawable.password_on);
                } else {
                    mDotImage[i].setImageResource(R.drawable.password_off);
                }
            }
        }
    }

    /**
     * 비밀번호 초기화
     */
    private void deletePassword() {
        mPassword = "";
        for (ImageView imageViews : mDotImage) {
            imageViews.setImageResource(R.drawable.password_off);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }
}
