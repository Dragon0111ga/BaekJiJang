package com.dragon0111ga.baekjijang;

public class UserInfo {
    private String name;
    private int age;

    public UserInfo(String name, Integer age){
        this.name = name;
        this.age = age;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Integer getAge(){
        return this.age;
    }
    public void setAge(int age){
        this.age = age;
    }

}
