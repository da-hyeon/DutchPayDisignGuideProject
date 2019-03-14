package com.example.mp_3.dutchpaydisignguideproject.Fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mp_3.dutchpaydisignguideproject.R;
import com.example.mp_3.dutchpaydisignguideproject.databinding.FragmentRegisterTermsConditionsAgreementBinding;

public class Register_TermsConditionsAgreementFragment extends Fragment {

    FragmentRegisterTermsConditionsAgreementBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater , R.layout.fragment_register_terms_conditions_agreement ,container , false);
        View view = mBinding.getRoot();

        return view;
    }
}
