package com.dragon0111ga.baekjijang;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;

public class WriteMultipleChoicePostActivity extends AppCompatActivity {
    private LinearLayout linearLayout;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    int num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post_multiplechoice);

        findViewById(R.id.UploadButton).setOnClickListener(onClickListener);
        findViewById(R.id.backtomainButton).setOnClickListener(onClickListener);
        findViewById(R.id.plusButton).setOnClickListener(onClickListener);
        num = 3;
        linearLayout = (LinearLayout)findViewById(R.id.mulityLinearLayout);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.UploadButton:
                    post();
                    break;
                case R.id.backtomainButton:
                    finish();
                    break;
                case R.id.plusButton:
                    plus();
                    break;
                default:
                    break;
            }
        }
    };

    private void plus(){
        if (num < 6){
            num += 1;
            EditText choiceEditText = new EditText(WriteMultipleChoicePostActivity.this);

            LinearLayout.LayoutParams choiceEditTextParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            choiceEditTextParams.setMargins(4, 4, 4, 4);
            choiceEditText.setLayoutParams(choiceEditTextParams);

            choiceEditText.setEms(10);
            choiceEditText.setHint("선택 "+ num);
            choiceEditText.setBackgroundColor(0X004DA8A4FF); // #4DA8A4FF
            choiceEditText.setPadding(3,3,3,3);
            choiceEditText.setSingleLine(false);
            choiceEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
            linearLayout.addView(choiceEditText, choiceEditTextParams);


        }else {
            showToast("선택의 최대 개수는 6개 입니다.");
        }

    }


    private void post()
    {
        String title = ((EditText)findViewById(R.id.editTextTitle)).getText().toString();
        String content = ((EditText)findViewById(R.id.editTextContent)).getText().toString();

        if (title.length() > 0 && content.length() > 10){
            ArrayList<String> choiceList = new ArrayList<>();
            user = FirebaseAuth.getInstance().getCurrentUser();

            for (int i = 0; i <linearLayout.getChildCount(); i++){
                View view = linearLayout.getChildAt(i);
                if (view instanceof EditText){
                    String choiceContent = ((EditText)view).getText().toString();
                    if (choiceContent.length() > 0) {
                        choiceList.add(choiceContent);
                    }else {
                      showToast("비어있는 선택지는 삭제됩니다.");
                    }
                }

            }
             WriteInfo writeInfo = new WriteInfo(title, content, user.getUid(), PostType.MULTIPLECHOISE, choiceList, new Date());
             writer(writeInfo);
             finish();

        }else {
            showToast("제목과 질문을 입력하세요.");
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
