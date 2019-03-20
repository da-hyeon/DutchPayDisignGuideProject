package com.example.mp_3.dutchpaydisignguideproject.ui.register.TermsConditionsAgreement;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mp_3.dutchpaydisignguideproject.R;
import com.example.mp_3.dutchpaydisignguideproject.databinding.FragmentRegisterTermsConditionsAgreementBinding;
import com.example.mp_3.dutchpaydisignguideproject.ui.main.MainContract;
import com.example.mp_3.dutchpaydisignguideproject.ui.main.MainPresenter;
import com.example.mp_3.dutchpaydisignguideproject.ui.register.Form.Register_FormFragment;
import com.example.mp_3.dutchpaydisignguideproject.ui.register.ViewAllTermsConditions.Register_ViewAllTermsConditionsFragment;

public class Register_TermsConditionsAgreementFragment extends Fragment implements Register_TermsConditionsAgreementContract.View {

    private FragmentRegisterTermsConditionsAgreementBinding mBinding;

    private ImageView image_termsConditions[];
    private ImageView mAllViewImages[];


    private Register_TermsConditionsAgreementContract.Presenter mPresenter;

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

        //전체 동의 클릭
        mBinding.imageCompleteAgreement.setOnClickListener(v ->
            mPresenter.clickAllTOS(mBinding.imageCompleteAgreement, R.drawable.agree_round_on)
        );

        //약관 클릭
        for (int i = 0; i < image_termsConditions.length; i++) {
            int finalI = i;

            image_termsConditions[i].setOnClickListener(v ->
                mPresenter.clickTOS(finalI, image_termsConditions[finalI], R.drawable.agree_on)
            );
        }

        //전체보기 클릭
        for (int i = 0; i < mAllViewImages.length; i++) {
            int finalI = i;
            mAllViewImages[i].setOnClickListener(v ->
                mPresenter.clickAllView(finalI)
            );
        }

        //회원가입 클릭
        mBinding.imageViewRegister.setOnClickListener(v ->
            mPresenter.clickRegister()
        );

        //카카오 클릭
        mBinding.imageViewKakaogo.setOnClickListener(v ->
                mPresenter.clickKakao()
        );

        return view;
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {
        mPresenter = new Register_TermsConditionsAgreementPresenter(this, getContext(), getActivity() , getFragmentManager());

        image_termsConditions = new ImageView[]{
                mBinding.imageTermsConditions1,
                mBinding.imageTermsConditions2,
                mBinding.imageTermsConditions3,
                mBinding.imageTermsConditions4,
                mBinding.imageTermsConditions5,
                mBinding.imageTermsConditions6};


        mAllViewImages = new ImageView[]{
                mBinding.viewAll1,
                mBinding.viewAll2,
                mBinding.viewAll3,
                mBinding.viewAll4,
                mBinding.viewAll5,
                mBinding.viewAll6,
        };

        //Check 정보 받아옴.
        mPresenter.refreshData(getArguments());
    }

    /**
     * Toast 띄우기
     */
    @Override
    public void showToast(String content) {
        Toast.makeText(getContext(), content, Toast.LENGTH_SHORT).show();
    }

    /**
     * Log 띄우기
     */
    @Override
    public void showLog(String tag, String content) {
        Log.d(tag, content);
    }

    @Override
    public void changeAllTOS(boolean state) {
        if (state) {
            mBinding.imageCompleteAgreement.setImageResource(R.drawable.agree_round_on);
        } else {
            mBinding.imageCompleteAgreement.setImageResource(R.drawable.agree_round_off);
        }
    }

    @Override
    public void changeTOS(int index, boolean state) {
        if (state) {
            image_termsConditions[index].setImageResource(R.drawable.agree_on);
        } else {
            image_termsConditions[index].setImageResource(R.drawable.agree_off);
        }
    }
}
