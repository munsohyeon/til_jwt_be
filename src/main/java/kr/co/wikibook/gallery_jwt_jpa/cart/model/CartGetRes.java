package kr.co.wikibook.gallery_jwt_jpa.cart.model;

import lombok.Getter;

@Getter
public class CartGetRes {
    private int id;
    private int itemId;
    private int price;
    private String imgPath;
    private String name;
    private String discountPer;


}
