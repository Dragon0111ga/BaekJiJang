package com.dragon0111ga.baekjijang;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordResetActivity extends AppCompatActivity {
    private FirebaseAuth mAuth; // Firebase 인증

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.pwChangeButton).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.pwChangeButton:
                    pwChange();
                    break;
                default:
                    break;
            }
        }
    };

    public void pwChange()
    {
        String email = ((EditText)findViewById(R.id.editTextTextEmailAddress)).getText().toString();
        showToast(email);

        if (email.length() > 0){ //공란 찾기
            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                showToast("이메일을 보냈습니다.");
                            }else{
                                if (task.getException() != null){
                                    showToast(task.getException().toString()); //시간남으면 예외처리 switch로 toast쏘기!
                                }
                            }
                        }
                    });

        }else {
            showToast("이메일을 입력해주세요");
        }




    }

    private void showToast(String msg)
    {
        Toast.makeText(PasswordResetActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

}
