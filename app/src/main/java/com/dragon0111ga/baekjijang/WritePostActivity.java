package com.dragon0111ga.baekjijang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class WritePostActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post);

        findViewById(R.id.gotoWriteAlternativeButton).setOnClickListener(onClickListener);
        findViewById(R.id.gotoWriteMultipleChoiceButton).setOnClickListener(onClickListener);
        findViewById(R.id.gotoWriteSubjectiveButton).setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.gotoWriteAlternativeButton:
                    startWriteAlternativePostActivity();
                    break;
                case R.id.gotoWriteMultipleChoiceButton:
                    startWriteMultipleChoisePostActivity();
                    break;
                case R.id.gotoWriteSubjectiveButton:
                    startWriteSubjectivePostActivity();
                    break;
                case R.id.backtomainButton:
                    finish();
                    break;
                default:
                    break;
            }
        }
    };


    private void startWriteAlternativePostActivity()
    {
        Intent intent= new Intent(this,WriteAlternativePostActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    private void startWriteMultipleChoisePostActivity()
    {
        Intent intent= new Intent(this,WriteMultipleChoicePostActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    private void startWriteSubjectivePostActivity()
    {
        Intent intent= new Intent(this,WriteSubjectivePostActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }



}
