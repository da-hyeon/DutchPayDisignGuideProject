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

    private MainContract.Presenter mMainPresenter;
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
                mPresenter.allTermsConditionsButtonCheck(
                        mPresenter.comparisonObjects(null, mBinding.imageCompleteAgreement, R.drawable.agree_round_on))
        );

        //약관 클릭
        for(int i = 0; i < image_termsConditions.length; i++){
            int finalI = i;
            image_termsConditions[i].setOnClickListener(v-> {
                mPresenter.termsConditionsButtonCheck(finalI , image_termsConditions[finalI], mPresenter.comparisonObjects(null, image_termsConditions[finalI], R.drawable.agree_on));
                mPresenter.allChecked(mPresenter.comparisonObjects(image_termsConditions, null, R.drawable.agree_on));
            });
        }

        //전체보기 클릭
        for (int i = 0; i < mAllViewImages.length; i++) {
            int finalI = i;
            mAllViewImages[i].setOnClickListener(v -> {
                mPresenter.allViewButtonClick(finalI);
            });
        }

        //회원가입 클릭
        mBinding.imageViewRegister.setOnClickListener(v ->
                mPresenter.nextButtonClick(
                        mPresenter.comparisonObjects(null, mBinding.imageCompleteAgreement, R.drawable.agree_round_on)
                )
        );

        //카카오 클릭
        mBinding.imageViewKakaogo.setOnClickListener(v ->
                mPresenter.kakaoButtonClick(mPresenter.comparisonObjects(null, mBinding.imageCompleteAgreement, R.drawable.agree_round_on))
        );

        return view;
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {

        mMainPresenter = new MainPresenter(getContext(), getFragmentManager());
        mPresenter = new Register_TermsConditionsAgreementPresenter(this, getContext(), getActivity());

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

        //Check정보 받아오고 , Check 그려주고 , 모두 체크됨을 체크하기.
        mPresenter.refreshData(getArguments());
        mPresenter.initTermsConditionsButtonCheck(image_termsConditions);
        mPresenter.allChecked(mPresenter.comparisonObjects(image_termsConditions, null, R.drawable.agree_on));
    }

    /**
     * 약관 전체 동의 버튼 Update
     */
    @Override
    public void changeAllTermsConditionsButton(boolean state) {
        if (state) {
            mBinding.imageCompleteAgreement.setImageResource(R.drawable.agree_round_on);
        } else {
            mBinding.imageCompleteAgreement.setImageResource(R.drawable.agree_round_off);
        }
    }

    /**
     * 약관 동의 버튼 Update
     */
    @Override
    public void changeTermsConditionsButton(ImageView imageView, boolean state) {
        if (state) {
            imageView.setImageResource(R.drawable.agree_on);
        } else {
            imageView.setImageResource(R.drawable.agree_off);
        }
    }

    /**
     * 약관 동의 전체 Update
     */
    @Override
    public void changeAllTermsConditions(boolean state) {
        if (state) {
            for (ImageView imageView : image_termsConditions) {
                imageView.setImageResource(R.drawable.agree_on);
            }
        } else {
            for (ImageView imageView : image_termsConditions) {
                imageView.setImageResource(R.drawable.agree_off);
            }
        }
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

    /**
     * Fragment 이동
     */
    @Override
    public void moveFragment() {
        mMainPresenter.moveFragment(new Register_FormFragment(), false, null);
    }

    @Override
    public void moveAllView(Bundle bundle) {
        mMainPresenter.moveFragment(new Register_ViewAllTermsConditionsFragment(), false, bundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }
}
