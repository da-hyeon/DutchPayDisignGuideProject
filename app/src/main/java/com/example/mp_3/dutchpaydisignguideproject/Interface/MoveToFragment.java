package com.example.mp_3.dutchpaydisignguideproject.Interface;

public interface MoveToFragment {

    //회원가입 폼 화면으로 이동
    void moveToFormFragment();

    //결제비밀번호 설정 화면으로 이동
    void moveToPaymentPasswordFragment();

    //회원가입 완료 화면으로 이동
    void moveToSuccessFragment();

    //회원가입 약관동의 화면으로 이동
    void moveToTermsConditionsAgreementFragment();

    //홈화면으로 이동
    void moveToHome(boolean state);
}
