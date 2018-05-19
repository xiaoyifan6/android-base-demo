package com.oyz.www.andoidexample2018.model;

/**
 * Created by ousir on 2018/1/27.
 */

public class Item {

    private Integer id;
    private String name;
    private String icon;
    private String activityName;

    public Item(){}

    public Item(String content,String packageStr){
        if(content == null)return;
        String[] attr = content.split(",");
        if(attr.length > 0){
            this.name = attr[0];
        }
        if(attr.length > 1){
            this.activityName = packageStr+attr[1];
        }

        if(attr.length > 2){
            this.icon = attr[2];
        }
    }

    public String getName(){
        return  this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityName() {
        return activityName;
    }

}
