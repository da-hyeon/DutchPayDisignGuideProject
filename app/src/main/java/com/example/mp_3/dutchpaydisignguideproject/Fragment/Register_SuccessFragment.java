package com.example.mp_3.dutchpaydisignguideproject.Fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mp_3.dutchpaydisignguideproject.Interface.MoveToFragment;
import com.example.mp_3.dutchpaydisignguideproject.R;
import com.example.mp_3.dutchpaydisignguideproject.databinding.FragmentRegisterSuccessBinding;

public class Register_SuccessFragment extends Fragment {

    private FragmentRegisterSuccessBinding mBinding;

    private MoveToFragment mMoveToFragment;

    /**
     * Activity 참조 얻기
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MoveToFragment) {
            mMoveToFragment = (MoveToFragment) context;
        } else {
            throw new RuntimeException(context.toString());
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register__success, container, false);
        View view = mBinding.getRoot();

        //객체생성 및 데이터 초기화
        initData();

        //클릭 메소드 등록
        onClickListener();

        return view;
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {

    }

    /**
     * 클릭 이벤트 처리
     */
    private void onClickListener() {
        mBinding.btnAppStart.setOnClickListener(v -> {
            mMoveToFragment.moveToHome(true);
        });
    }
}
