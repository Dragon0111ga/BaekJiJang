package com.dragon0111ga.baekjijang;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth; // Firebase 인증

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.signUpButton).setOnClickListener(onClickListener);
        findViewById(R.id.gotoLogInButton).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.signUpButton:
                    signUp();
                    break;
                case R.id.gotoLogInButton:
                    startLoginActivity();
                    break;
                default:
                    break;
            }
        }
    };

    private void signUp()
    {
        String email = ((EditText)findViewById(R.id.editTextTextEmailAddress)).getText().toString();
        String password = ((EditText)findViewById(R.id.editTextTextPassword)).getText().toString();
        String passwordCheck = ((EditText)findViewById(R.id.editTextTextPasswordCheck)).getText().toString();

        if (email.length() > 0 && password.length() > 0 && passwordCheck.length() > 0){ //공란 찾기
            if (password.equals(passwordCheck)) //비밀번호 확인 체크
            {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    showToast("회원가입에 성공하였습니다.");
                                    startMainActivity();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    if (task.getException() != null){
                                        showToast(task.getException().toString()); //시간남으면 예외처리 switch로 toast쏘기!
                                    }
                                }

                                // ...
                            }
                        });
            }else{
                showToast("비밀번호 확인이 일치하지 않습니다.");
            }
        }else {
            showToast("이메일 또는 비밀번호를 확인해주세요.");
        }




    }

    private void showToast(String msg)
    {
        Toast.makeText(SignUpActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
    private void startLoginActivity()
    {
        Intent intent= new Intent(this,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    private void startMainActivity()
    {
        Intent intent= new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    @Override
    public void onPause() { //뒤로가기 눌렀을 때 다시 화면 뜨는거 방지
        super.onPause(); //Activity 멈추기
        finish(); //Activity 종료
        //https://hashcode.co.kr/questions/1885/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EC%95%A1%ED%8B%B0%EB%B9%84%ED%8B%B0-%EC%A2%85%EB%A3%8C
    }



}
