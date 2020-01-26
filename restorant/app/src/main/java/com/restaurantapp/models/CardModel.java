package com.restaurantapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardModel {
    private int image;
    private String title;
    private String text;
    private String desc;

    public CardModel(int image, String title, String desc){
        this.image=image;
        this.title=title;
        this.desc=desc;
    }

    public int getImage(){
        return image;
    }

    public void setImage(int image){
        this.image=image;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title) {
        this.title=title;
    }

    public String getDesc(){
        return desc;
    }

    public void setDesc(String desc){
        this.desc=desc;
    }
}
