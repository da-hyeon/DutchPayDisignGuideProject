package com.example.mp_3.dutchpaydisignguideproject.Fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mp_3.dutchpaydisignguideproject.Interface.MoveToFragment;
import com.example.mp_3.dutchpaydisignguideproject.R;
import com.example.mp_3.dutchpaydisignguideproject.databinding.FragmentRegisterFormBinding;

public class Register_FormFragment extends Fragment {

    private MoveToFragment mMoveToFragment;

    /**
     * Activity참조 얻기
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof MoveToFragment) {
            mMoveToFragment = (MoveToFragment) context;
        } else {
            throw new RuntimeException(context.toString());
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentRegisterFormBinding mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register__form, container, false);
        View view = mBinding.getRoot();
        mBinding.btnNext.setOnClickListener(v -> mMoveToFragment.moveToPaymentPasswordFragment());
        return view;
    }
}
