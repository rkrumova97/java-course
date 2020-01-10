package com.restaurantapp.dao.impl;

import com.restaurantapp.dao.OfferDao;
import com.restaurantapp.models.Category;
import com.restaurantapp.models.Offer;
import com.restaurantapp.models.Restaurant;

import org.junit.Test;

import java.util.List;

public class OfferDaoImplUnitTest {
    @Test
    public void createOffer_isCorrect() throws Exception {
        Restaurant restaurant = Restaurant.builder()
                .id(3L)
                .address("")
                .name("Rest")
                .build();
        Category category = new Category(1L, "something");
        Offer offer = Offer.builder()
                .id(2L)
                .restaurant(restaurant)
                .price(10L)
                .category(category)
                .text("dsfdsds")
                .build();
        OfferDao offerDao = new OfferDaoImpl();
        Offer offer1 = offerDao.createOffer(offer);
        assert offer1.getPrice().equals(offer.getPrice());
    }

    @Test
    public void readAll_isCorrect() throws Exception {
        OfferDao offerDao = new OfferDaoImpl();
        assert !offerDao.readAllOffer().isEmpty();
    }

    @Test
    public void readOne_isCorrect() throws Exception {
        OfferDao offerDao = new OfferDaoImpl();
        assert offerDao.readOffer(1L).getPrice().equals(10L);
    }


    @Test
    public void update_isCorrect() throws Exception {
        OfferDao offerDao = new OfferDaoImpl();
        Restaurant restaurant = Restaurant.builder()
                .id(1L)
                .address("")
                .name("Rest")
                .build();
        Category category = new Category(1L, "something");
        Offer offer = Offer.builder()
                .id(1L)
                .restaurant(restaurant)
                .price(10L)
                .category(category)
                .text("wqwq")
                .build();

        offerDao.updateOffer(offer, "text", "wqwq");
        assert offerDao.readOffer(1L).getText().equals("wqwq");

    }

    @Test
    public void delete_isCorrect() throws Exception {

        OfferDao offerDao = new OfferDaoImpl();
        List<Offer> offers = offerDao.readAllOffer();
        offerDao.deleteOffer(2L);
        assert offers.size() != offerDao.readAllOffer().size();

    }

}
