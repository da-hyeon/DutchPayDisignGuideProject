package com.example.mp_3.dutchpaydisignguideproject.ui.register.PaymentPassword;

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

import com.example.mp_3.dutchpaydisignguideproject.R;
import com.example.mp_3.dutchpaydisignguideproject.databinding.FragmentRegisterPaymentPasswordFormBinding;

public class Register_PaymentPasswordFormFragment extends Fragment implements Register_PaymentPasswordFormContract.View {

    private FragmentRegisterPaymentPasswordFormBinding mBinding;
    private Register_PaymentPasswordFormContract.Presenter mPresenter;

    private TextView mNumberTextViews[];
    private ImageView mDotImage[];

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

        //숫자 버튼
        for (int i = 0; i < mNumberTextViews.length; i++) {
            int finalI = i;
            mNumberTextViews[i].setOnClickListener(v->
                mPresenter.clickNumber(mNumberTextViews[finalI].getText().toString())
            );
        }

        //삭제 버튼
        mBinding.viewDelete.setOnClickListener(v ->
                mPresenter.clickDeleteButton()
        );

        //확인 버튼
        mBinding.viewOk.setOnClickListener(v ->
                mPresenter.clickOKButton()
        );

        return view;
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {

        mPresenter = new Register_PaymentPasswordFormContractPresenter(this , getContext(), getFragmentManager());


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

        mPresenter.initRandomNumber();
    }

    /**
     * 버튼 숫자 보여주기
     */
    @Override
    public void showRandomNumber(int index , String randomNumber) {
        mNumberTextViews[index].setText(randomNumber);
    }

    /**
     * dot Image 업데이트
     */
    @Override
    public void dotImagesUpdate(int index, boolean checkState) {
        if(checkState){
            mDotImage[index].setImageResource(R.drawable.password_on);
        } else {
            mDotImage[index].setImageResource(R.drawable.password_off);
        }
    }

    /**
     * 실패메세지 보여주기
     */
    @Override
    public void Fail() {
        Toast.makeText(getContext(), "결제 비밀번호는 6자리 입니다.", Toast.LENGTH_SHORT).show();
    }
}
