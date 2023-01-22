package com.restaurantapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.time.LocalDateTime;

@Entity
@TypeConverters(OrderConverter.class)
public class Order {
    @PrimaryKey
    private Long id;

    @ColumnInfo(name = "user_id")
    private User user;

    @ColumnInfo(name = "offer_id")
    private Offer offer;

    @ColumnInfo
    private LocalDateTime localDateTime;

    public Order() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order id(Long id){
        setId(id);
        return this;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order user(User user){
        setUser(user);
        return this;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Order offer(Offer offer){
        setOffer(offer);
        return this;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Order localDateTime(LocalDateTime localDateTime){
        setLocalDateTime(localDateTime);
        return this;
    }
}
