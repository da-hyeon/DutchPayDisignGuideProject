package com.example.mp_3.dutchpaydisignguideproject.ui.register.ViewAllTermsConditions;

import android.annotation.SuppressLint;
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
import com.example.mp_3.dutchpaydisignguideproject.ui.main.MainContract;
import com.example.mp_3.dutchpaydisignguideproject.ui.main.MainPresenter;
import com.example.mp_3.dutchpaydisignguideproject.ui.register.TermsConditionsAgreement.Register_TermsConditionsAgreementFragment;
import com.example.mp_3.dutchpaydisignguideproject.databinding.FragmentRegisterViewAllTermsConditionsBinding;

import java.util.Objects;


public class Register_ViewAllTermsConditionsFragment extends Fragment {

    private FragmentRegisterViewAllTermsConditionsBinding mBinding;
    private String mTermsConditionsTitles[];
    private String mTermsConditionsContents[];

    private MainContract.Presenter mMainPresenter;

    private int mTermsConditionsNumber;
    private boolean mTermsConditionsChecked[];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_view_all_terms_conditions, container, false);
        View view = mBinding.getRoot();

        mMainPresenter = new MainPresenter(getContext(), getFragmentManager());

        initData();

        onClickListener();

        return view;
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {
        mTermsConditionsNumber = getArguments().getInt("num");
        mTermsConditionsChecked = getArguments().getBooleanArray("checked");

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

        mBinding.txtTitle.setText(mTermsConditionsTitles[mTermsConditionsNumber]);
        mBinding.txtContent.setText(mTermsConditionsTitles[mTermsConditionsNumber]);

        if(mTermsConditionsChecked[mTermsConditionsNumber]){
            mBinding.agreeCheck.setImageResource(R.drawable.agree_on);
        } else {
            mBinding.agreeCheck.setImageResource(R.drawable.agree_off);
        }
    }

    /**
     * 클릭 이벤트 처리
     */
    private void onClickListener() {
        mBinding.checkBox.setOnClickListener(v->{
            Bitmap tmpBitmap = ((BitmapDrawable) mBinding.agreeCheck.getDrawable()).getBitmap();
            Drawable agree_on = Objects.requireNonNull(getContext()).getResources().getDrawable(R.drawable.agree_on);
            Bitmap tmpBitmap1 = ((BitmapDrawable) agree_on).getBitmap();
            if (tmpBitmap.equals(tmpBitmap1)) {
                mBinding.agreeCheck.setImageResource(R.drawable.agree_off);
                mTermsConditionsChecked[mTermsConditionsNumber] = false;
                //mRegister_termsConditionsAgreementFragment.setmChecked(mTermsConditionsNumber , false);
            } else {
                mBinding.agreeCheck.setImageResource(R.drawable.agree_on);
                mTermsConditionsChecked[mTermsConditionsNumber] = true;
                //mRegister_termsConditionsAgreementFragment.setmChecked(mTermsConditionsNumber , true);
            }
        });
    }

    public void onBackPress(){
        Bundle bundle = new Bundle();
        bundle.putInt("num" , mTermsConditionsNumber);
        bundle.putBooleanArray("checked", mTermsConditionsChecked);
        mMainPresenter.moveFragment(new Register_TermsConditionsAgreementFragment(), true , bundle);
    }
}
