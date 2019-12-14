package com.restaurantapp.dao;

import com.restaurantapp.models.*;

import java.util.List;

public interface OfferDao {
    public Offer createOffer(Offer offer) throws Exception;

    public Offer readOffer(Long id) throws Exception;

    public List<Offer> readAllOffer() throws Exception;

    void updateOffer(Offer offer, String changedAttribute, Object changeValue);

    public void deleteOffer(Long id);
}
