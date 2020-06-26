package com.dragon0111ga.baekjijang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.floatingActionButton).setOnClickListener(onClickListener);
        if (user == null) {
            startLoginActivity();
        } else {
            firebaseFirestore = FirebaseFirestore.getInstance();
            DocumentReference documentReference = firebaseFirestore.collection("users").document(user.getUid());
            documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

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
        recyclerView = findViewById(R.id.recyclerView);
        findViewById(R.id.floatingActionButton).setOnClickListener(onClickListener);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

        protected void onResume(){
            super.onResume();

            if (user != null) {
                CollectionReference collectionReference = firebaseFirestore.collection("posts");
                collectionReference.orderBy("createdAt", Query.Direction.DESCENDING).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<WriteInfo> postList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                postList.add(new WriteInfo(
                                        document.getData().get("title").toString(),
                                        document.getData().get("contents").toString(),
                                        document.getData().get("publisher").toString(),
                                        (PostType)document.getData().get("posttype"),
                                        new Date(document.getDate("createdAt").getTime())));
                            }
                            RecyclerView.Adapter mAdapter = new MainAdapter(MainActivity.this, postList);
                            recyclerView.setAdapter(mAdapter);

                        } else {
                            showToast("task 가져오기 실패");
                        }
                    }
                });


    }}

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                /*case R.id.logOutButton:
                    FirebaseAuth.getInstance().signOut();
                    showToast("로그아웃");
                    startLoginActivity();
                    break;*/
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
        Intent intent= new Intent(this,UserInfoActivity.class);
        startActivity(intent);
    }

}