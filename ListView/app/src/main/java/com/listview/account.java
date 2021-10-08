package com.listview;

public class account {
    private String name;
    public account(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "account{" +
                "name='" + name + '\'' +
                '}';
    }
}
