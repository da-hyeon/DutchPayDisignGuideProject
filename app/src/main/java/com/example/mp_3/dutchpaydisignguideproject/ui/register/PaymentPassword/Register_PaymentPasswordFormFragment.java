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

import com.example.mp_3.dutchpaydisignguideproject.ui.main.MainContract;
import com.example.mp_3.dutchpaydisignguideproject.ui.register.Success.Register_SuccessFragment;
import com.example.mp_3.dutchpaydisignguideproject.ui.register.TermsConditionsAgreement.Register_TermsConditionsAgreementFragment;
import com.example.mp_3.dutchpaydisignguideproject.ui.main.MainPresenter;
import com.example.mp_3.dutchpaydisignguideproject.R;
import com.example.mp_3.dutchpaydisignguideproject.databinding.FragmentRegisterPaymentPasswordFormBinding;

import java.util.ArrayList;
import java.util.Collections;

public class Register_PaymentPasswordFormFragment extends Fragment implements Register_PaymentPasswordFormContract.View {

    private FragmentRegisterPaymentPasswordFormBinding mBinding;

    private String mPassword;

    private TextView mNumberTextViews[];
    private ImageView mDotImage[];


    private Register_PaymentPasswordFormContract.Presenter mPresenter;
    private MainContract.Presenter mMainPresenter;


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
            mNumberTextViews[i].setOnClickListener(v->{
                mPresenter.clickNumberButton(mPassword, mNumberTextViews[finalI].getText().toString());
                mPresenter.inputImageUpdate(mPassword , mDotImage);
            });
        }

        //삭제 버튼
        mBinding.viewDelete.setOnClickListener(v -> mPresenter.clickDeleteButton());

        //확인 버튼
        mBinding.viewOk.setOnClickListener(v -> mPresenter.clickOKButton(mPassword));

        return view;
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {

        mMainPresenter = new MainPresenter(getContext(), getFragmentManager());

        mPassword = "";

        mPresenter = new Register_PaymentPasswordFormContractPresenter(this);

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

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void currentInputNumber(String number) {
        mPassword = number;
       // Toast.makeText(getContext(), mPassword, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dotImagesUpdate(ImageView imageView, boolean checkState) {
        if(checkState){
            imageView.setImageResource(R.drawable.password_on);
        } else {
            imageView.setImageResource(R.drawable.password_off);
        }
    }

    @Override
    public void clearPassword() {
        mPassword = "";
        mPresenter.inputImageUpdate(mPassword, mDotImage);
    }

    @Override
    public void SuccessFully() {
        mMainPresenter.moveFragment(new Register_SuccessFragment(), true, null);
    }

    @Override
    public void Fail() {
        Toast.makeText(getContext(), "결제 비밀번호는 6자리 입니다.", Toast.LENGTH_SHORT).show();
    }
}
