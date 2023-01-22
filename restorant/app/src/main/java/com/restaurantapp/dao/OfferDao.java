package com.restaurantapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.restaurantapp.models.Offer;

import java.util.List;

@Dao
public interface OfferDao {
    @Insert
    void createOffer(Offer offer) throws Exception;

    @Query("SELECT * FROM offer where offer.id = :id")
    Offer readOffer(Long id) throws Exception;

    @Query("SELECT * FROM offer")
    List<Offer> readAllOffer() throws Exception;

}
