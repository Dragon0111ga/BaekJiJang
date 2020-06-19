package com.dragon0111ga.baekjijang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance(); // Firebase 인증

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null){
            startMainActivity();
        }

        findViewById(R.id.logInButton).setOnClickListener(onClickListener);
        findViewById(R.id.gotoSignUpButton).setOnClickListener(onClickListener);
        findViewById(R.id.gotoPwChangeButton).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.logInButton:
                    logIn();
                    break;
                case R.id.gotoSignUpButton:
                    startSignUpActivity();
                    break;
                case R.id.gotoPwChangeButton:
                    startPasswordResetActivity();
                    break;
                default:
                    break;
            }
        }
    };

    public void logIn()
    {
        String email = ((EditText)findViewById(R.id.editTextTextEmailAddress)).getText().toString();
        String password = ((EditText)findViewById(R.id.editTextTextPassword)).getText().toString();

        if (email.length() > 0 && password.length() > 0){ //공란 찾기
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    showToast("로그인 성공");
                                    startMainActivity();
                                    //성공 UI
                                } else {
                                    showToast("로그인 실패");
                                    if (task.getException() != null){
                                        showToast(task.getException().toString()); //시간남으면 예외처리 switch로 toast쏘기!
                                    }
                                }
                            }
                        });

        }else {
            showToast("이메일 또는 비밀번호를 확인해주세요.");
        }




    }

    private void showToast(String msg)
    {
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    //범용적으로 쓸수 있는 방법 알아보기..
//    private  void startMyActivity(Class c){ //Class로 크게? 쓰지 말라고 안되는듯... Activity로 해서 .Getclass했는데 될리가 없다..
//        onPause();
//        Intent intent= new Intent(this,c);
//        startActivity(intent);
//    }
    private void startSignUpActivity()
    {
        Intent intent= new Intent(this,SignUpActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    private void startPasswordResetActivity()
    {
        Intent intent= new Intent(this,PasswordResetActivity.class);
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
    public void onPause() {
        super.onPause();
        // Remove the activity when its off the screen
        finish();
    }
}
