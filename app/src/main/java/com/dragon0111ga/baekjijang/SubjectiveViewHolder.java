package com.dragon0111ga.baekjijang;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SubjectiveViewHolder extends RecyclerView.ViewHolder {

    public TextView title, content, clickCount;
    public RadioButton choisebtn1, choisebtn2;

    public SubjectiveViewHolder(@NonNull View itemView) {
        super(itemView);
        title = (TextView)itemView.findViewById(R.id.titleTextView);
        content = (TextView)itemView.findViewById(R.id.contentTextView);
        clickCount = (TextView)itemView.findViewById(R.id.clickCountView);
        choisebtn1 = (RadioButton)itemView.findViewById(R.id.choice1Button);
        choisebtn2 = (RadioButton)itemView.findViewById(R.id.choice2Button);

    }
}
