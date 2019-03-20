package com.example.mp_3.dutchpaydisignguideproject.ui.register.ViewAllTermsConditions;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mp_3.dutchpaydisignguideproject.R;
import com.example.mp_3.dutchpaydisignguideproject.databinding.FragmentRegisterViewAllTermsConditionsBinding;
import com.example.mp_3.dutchpaydisignguideproject.ui.main.MainContract;
import com.example.mp_3.dutchpaydisignguideproject.ui.main.MainPresenter;
import com.example.mp_3.dutchpaydisignguideproject.ui.register.TermsConditionsAgreement.Register_TermsConditionsAgreementFragment;

import java.util.Objects;


public class Register_ViewAllTermsConditionsFragment extends Fragment implements Register_ViewAllTermsConditionsContract.View {

    private FragmentRegisterViewAllTermsConditionsBinding mBinding;
    private Register_ViewAllTermsConditionsContract.Presenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_view_all_terms_conditions, container, false);
        View view = mBinding.getRoot();

        //객체생성 및 데이터초기화
        initData();

        //체크박스 클릭
        mBinding.checkBox.setOnClickListener(v->
                mPresenter.clickTOS(mBinding.agreeCheck , R.drawable.agree_on)
        );

        return view;
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {
        mPresenter = new Register_ViewAllTermsConditionsPresenter(this , getContext() , getFragmentManager());
        mPresenter.getData(getArguments());
    }

    /**
     * 뒤로가기 클릭
     */
    public void onBackPress(){
        mPresenter.clickBackPressed();
    }

    /**
     * 타이틀 변경
     */
    @Override
    public void changeTitle(String title) {
        mBinding.txtTitle.setText(title);
    }

    /**
     * 내용 변경
     */
    @Override
    public void changeContent(String content) {
        mBinding.txtContent.setText(content);
    }

    /**
     * 약관동의 Image 변경
     */
    @Override
    public void changeTOS(boolean state) {
        if(state) {
            mBinding.agreeCheck.setImageResource(R.drawable.agree_on);
        } else {
            mBinding.agreeCheck.setImageResource(R.drawable.agree_off);
        }
    }
}
