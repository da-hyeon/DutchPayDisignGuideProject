package com.example.mp_3.dutchpaydisignguideproject.ui.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mp_3.dutchpaydisignguideproject.data.db.model.User;
import com.example.mp_3.dutchpaydisignguideproject.ui.main.MainActivity;
import com.example.mp_3.dutchpaydisignguideproject.ui.main.MainContract;
import com.example.mp_3.dutchpaydisignguideproject.ui.register.TermsConditionsAgreement.Register_TermsConditionsAgreementFragment;
import com.example.mp_3.dutchpaydisignguideproject.ui.main.MainPresenter;
import com.example.mp_3.dutchpaydisignguideproject.R;
import com.example.mp_3.dutchpaydisignguideproject.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment implements LoginContract.View {

    private FragmentLoginBinding mBinding;

    private MainContract.Presenter mMainPresenter;
    private LoginContract.Presenter mPresenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        View view = mBinding.getRoot();

        //객체생성 및 데이터 초기화
        initData();

        //자동로그인 체크
        mBinding.imageAutoLoginCheck.setOnClickListener(v -> mPresenter.currentCheckState(mBinding.imageAutoLoginCheck));

        //회원가입 버튼
        mBinding.imageUserRegister.setOnClickListener(v -> mMainPresenter.moveFragment(new Register_TermsConditionsAgreementFragment(), true, null)
        );

        //로그인버튼
        mBinding.btnLogin.setOnClickListener(v ->
                mPresenter.handleLogin(mBinding.editUserID.getText().toString(), mBinding.editUserPW.getText().toString())
        );
        return view;
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {
        mPresenter = new LoginPresenter(this, getContext() , getActivity());
        mMainPresenter = new MainPresenter(getContext(), getFragmentManager());
    }

    @Override
    public void loginSuccessFully() {
        Toast.makeText(getContext(), "로그인성공", Toast.LENGTH_SHORT).show();
        mMainPresenter.allFragmentClose();
    }

    @Override
    public void loginFail() {
        Toast.makeText(getContext(), "로그인실패", Toast.LENGTH_SHORT).show();
    }
}
