package com.dragon0111ga.baekjijang;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;

public class UserInfoActivity extends AppCompatActivity {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static String TAG = "UserInfoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        findViewById(R.id.userInfoCheckButton).setOnClickListener(onClickListener);
        findViewById(R.id.userInfoCancleButton).setOnClickListener(onClickListener);

    }
    // 뒤로가기 누르면 MainActivity로 가는거 방지
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.userInfoCheckButton:
                    userInfoUpdate();
                    break;
                case R.id.userInfoCancleButton:
                    finish();
                    break;
                default:
                    break;
            }
        }
    };

private void userInfoUpdate()
{
    String name = ((EditText)findViewById(R.id.editTextName)).getText().toString();
    int age = Integer.parseInt(((EditText)findViewById(R.id.editTextAge)).getText().toString());

    if (name.length() > 0 && age > 9){
        UserInfo userInfo = new UserInfo(name, age);
        db.collection("users").document(user.getUid()).set(userInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                        showToast("회원정보를 등록했습니다.");
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                        showToast("회원정보 등록을 실패했습니다.");
                    }
                });
    }else {
        showToast("회원 정보를 입력하세요.");
    }
}
    private void showToast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }




}