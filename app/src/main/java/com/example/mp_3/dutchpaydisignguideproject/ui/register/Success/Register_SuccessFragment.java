package com.example.mp_3.dutchpaydisignguideproject.ui.register.Success;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mp_3.dutchpaydisignguideproject.R;
import com.example.mp_3.dutchpaydisignguideproject.databinding.FragmentRegisterSuccessBinding;

public class Register_SuccessFragment extends Fragment implements Register_SuccessContract.View {

    private FragmentRegisterSuccessBinding mBinding;
    private Register_SuccessContract.Presenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register__success, container, false);
        View view = mBinding.getRoot();
        mPresenter = new Register_SuccessPresenter( getActivity() , getContext() , getFragmentManager());

        mBinding.btnAppStart.setOnClickListener(v ->
            mPresenter.clickAppStart()
        );

        return view;
    }
}
