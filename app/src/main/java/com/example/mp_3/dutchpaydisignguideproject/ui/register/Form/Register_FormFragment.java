package com.example.mp_3.dutchpaydisignguideproject.ui.register.Form;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mp_3.dutchpaydisignguideproject.ui.main.MainContract;
import com.example.mp_3.dutchpaydisignguideproject.ui.main.MainPresenter;
import com.example.mp_3.dutchpaydisignguideproject.R;
import com.example.mp_3.dutchpaydisignguideproject.ui.register.PaymentPassword.Register_PaymentPasswordFormFragment;
import com.example.mp_3.dutchpaydisignguideproject.databinding.FragmentRegisterFormBinding;

public class Register_FormFragment extends Fragment {

    private MainContract.Presenter mMainPresenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentRegisterFormBinding mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register__form, container, false);
        View view = mBinding.getRoot();
        mMainPresenter = new MainPresenter(getContext(), getFragmentManager());

        mBinding.btnNext.setOnClickListener(v ->
                mMainPresenter.moveFragment(new Register_PaymentPasswordFormFragment()
                        , false, null, true)
        );
        return view;
    }
}
