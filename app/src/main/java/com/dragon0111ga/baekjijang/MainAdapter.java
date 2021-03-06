package com.dragon0111ga.baekjijang;

import android.app.Activity;


import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainAdapter extends RecyclerView.Adapter<SubjectiveViewHolder> {
    private ArrayList<WriteInfo> mDataset;
    private Activity activity;
    CardView cardView;

    public MainAdapter(Activity activity, ArrayList<WriteInfo> myDataset) {
        mDataset = myDataset;
        this.activity = activity;
    }

    @NonNull
    @Override
    public SubjectiveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item_subjective, parent, false);
        final SubjectiveViewHolder subjectiveViewHolder = new SubjectiveViewHolder(cardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return subjectiveViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final SubjectiveViewHolder holder, int position) {
        TextView titleTextView = cardView.findViewById(R.id.titleTextView);
        titleTextView.setText(mDataset.get(position).getTitle());

        TextView contentText = cardView.findViewById(R.id.contentTextView);
        contentText.setText(mDataset.get(position).getContent());

        TextView creatiedText = cardView.findViewById(R.id.creatiedAtTextView);
        creatiedText.setText(new SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).format(mDataset.get(position).getCreatiedAt()));
/*
        LinearLayout contetnsLayout = cardView.findViewById(R.id.contentsLayout);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ArrayList<String> contentsList = mDataset.get(position).getMultiple();

        if(contetnsLayout.getChildCount() == 0){
            for (int i = 0; i < contentsList.size(); i++ ){
                String contents = contentsList.get(i);
                if(Patterns.WEB_URL.matcher(contents).matches()){
                    ImageView imageView = new ImageView(activity);
                    imageView.setLayoutParams(layoutParams);
                    contetnsLayout.addView(imageView);
                    Glide.with(activity).load(contents).override(1000).into(imageView);
                }else {
                    TextView textView = new TextView(activity);
                    textView.setLayoutParams(layoutParams);
                    textView.setText(contents);
                    contetnsLayout.addView(textView);
                }
            }*/


    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}