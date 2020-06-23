package com.dragon0111ga.baekjijang;

import android.os.Bundle;
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

import java.util.Date;

public class WriteSubjectivePostActivity extends AppCompatActivity {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post_subjective);

        findViewById(R.id.UploadButton).setOnClickListener(onClickListener);
        findViewById(R.id.backtomainButton).setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.UploadButton:
                    post();
                    finish();
                    break;
                case R.id.backtomainButton:
                    finish();
                    break;
                default:
                    break;
            }
        }
    };


    private void post()
    {
        String title = ((EditText)findViewById(R.id.editTextTitle)).getText().toString();
        String content = ((EditText)findViewById(R.id.editTextContent)).getText().toString();

        if (title.length() > 0 && content.length() > 10){
            WriteInfo writeInfo = new WriteInfo(title, content, user.getUid(), PostType.SUBJECTIVE, new Date());
            writer(writeInfo);

        }else {
            showToast("제목을 입력하세요.");
        }
    }

    private void writer(WriteInfo writeInfo){
        db.collection("posts")
                .add(writeInfo)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        showToast("성공적으로 질문을 올렸습니다.");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showToast("질문 등록을 실패했습니다.");
                    }
                });
    }

    private void showToast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
