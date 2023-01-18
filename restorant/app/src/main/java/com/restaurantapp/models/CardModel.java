package com.restaurantapp.models;

public class CardModel {
    private int image;
    private String title;
    private String text;
    private String desc;
    private Offer offer;
    private User user;

    public CardModel() {
    }

    public CardModel(int image, String title, String text, String desc, Offer offer, User user) {
        this.image = image;
        this.title = title;
        this.text = text;
        this.desc = desc;
        this.offer = offer;
        this.user = user;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
