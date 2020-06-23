package com.dragon0111ga.baekjijang;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class WriteInfo {
    private String title;
    private String content;
    private String publisher;
    private PostType posttype;
    private ArrayList<String> multiple;
    private Date creatiedAt;
    private String choice1;
    private String choice2;

    public WriteInfo(String title, String content, String publisher, PostType posttype, Date creatiedAt){ // 주관식 질문
        this.title = title;
        this.content = content;
        this.publisher = publisher;
        this.posttype = posttype;
        this.creatiedAt = creatiedAt;
    }

    public WriteInfo(String title, String content, String publisher, PostType postType, ArrayList<String> multiple, Date creatiedAt) { // 양자택일 질문
        this.title = title;
        this.content = content;
        this.publisher = publisher;
        this.posttype = postType;
        this.multiple = multiple;
        this.creatiedAt = creatiedAt;
    }

    public WriteInfo(String title, String content, String publisher, PostType postType, String choice1, String choice2, Date creatiedAt) { // 양자택일 질문
        this.title = title;
        this.content = content;
        this.publisher = publisher;
        this.posttype = postType;
        this.creatiedAt = creatiedAt;
        this.choice1 = choice1;
        this.choice2 = choice2;
    }

    public String getTitle(){
        return this.title;
    }
    public void setTitle(String name){
        this.title = name;
    }

    public String getContent(){
        return this.content;
    }
    public void setContent(String content){
        this.content = content;
    }

    public  String getPublisher(){
        return this.publisher;
    }
    public void setPublisher(String publisher){
        this.publisher = publisher;
    }
    public Enum<PostType> getPosttype() {
        return posttype;
    }
    public void setPosttype(PostType posttype) {
        this.posttype = posttype;
    }

    public ArrayList<String> getMultiple() {
        return multiple;
    }
    public void setMultiple(ArrayList<String> multiple) {
        this.multiple = multiple;
    }

    public void setCreatiedAt(Date creatiedAt) {
        this.creatiedAt = creatiedAt;
    }
    public Date getCreatiedAt() {
        return creatiedAt;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice2() {
        return choice2;
    }
}
