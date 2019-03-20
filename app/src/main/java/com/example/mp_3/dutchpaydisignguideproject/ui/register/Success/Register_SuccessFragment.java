package com.example.mp_3.dutchpaydisignguideproject.ui.register.Success;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mp_3.dutchpaydisignguideproject.R;
import com.example.mp_3.dutchpaydisignguideproject.data.db.model.User;
import com.example.mp_3.dutchpaydisignguideproject.databinding.FragmentRegisterSuccessBinding;
import com.example.mp_3.dutchpaydisignguideproject.ui.main.MainActivity;
import com.example.mp_3.dutchpaydisignguideproject.ui.main.MainContract;
import com.example.mp_3.dutchpaydisignguideproject.ui.main.MainPresenter;

public class Register_SuccessFragment extends Fragment {

    private FragmentRegisterSuccessBinding mBinding;
    private MainContract.Presenter mMainPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register__success, container, false);
        View view = mBinding.getRoot();
        mMainPresenter = new MainPresenter(getContext(), getFragmentManager());

        mBinding.btnAppStart.setOnClickListener(v -> {
            User user = User.getInstance();
            user.setUserState(true);
            ((MainActivity)getActivity()).updateView();
            ((MainActivity)getActivity()).changeAppBarIcon(true);
            mMainPresenter.allFragmentClose();
        });

        return view;
    }
}
