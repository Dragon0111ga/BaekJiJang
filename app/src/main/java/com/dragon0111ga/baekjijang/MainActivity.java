package com.dragon0111ga.baekjijang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (FirebaseAuth.getInstance().getCurrentUser() == null)
        {
            startLoginActivity();
        }

        findViewById(R.id.logOutButton).setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.logOutButton:
                    FirebaseAuth.getInstance().signOut();
                    startLoginActivity();
                    break;
                default:
                    break;
            }
        }
    };

    private void startLoginActivity()
    {
        onPause();
        Intent intent= new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
    @Override
    public void onPause() {
        super.onPause();
        // Remove the activity when its off the screen
        finish();
    }

}