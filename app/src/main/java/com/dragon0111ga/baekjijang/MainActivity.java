package com.dragon0111ga.baekjijang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.logOutButton).setOnClickListener(onClickListener);
        findViewById(R.id.floatingActionButton).setOnClickListener(onClickListener);
        if(user == null){
            startLoginActivity();
        }else {
            // 회원 정보 가져오기
            DocumentReference docRef = db.collection("users").document(user.getUid());
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null) {
                            if (document.exists()) {
                                // 정보 존재
                                showToast(document.getData().get("name") + "님 어서오세요.");
                            } else {
                                // 정보 없음
                                showToast("회원정보를 입력해주세요.");
                                startUserInfoActivity();
                            }
                        }
                    } else { // 과정 실패
                        showToast("실패!");
                    }
                }
            });
        }




    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.logOutButton:
                    FirebaseAuth.getInstance().signOut();
                    showToast("로그아웃");
                    startLoginActivity();
                    break;
                case R.id.floatingActionButton:
                    startWritePostActivity();
                    break;
                default:
                    break;
            }
        }
    };
    private void showToast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    private void startLoginActivity()
    {
        Intent intent= new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
    private void startWritePostActivity()
    {
        Intent intent= new Intent(this,WritePostActivity.class);
        startActivity(intent);
    }
    private void startUserInfoActivity()
    {
        Intent intent= new Intent(this,WritePostActivity.class);
        startActivity(intent);
    }

}