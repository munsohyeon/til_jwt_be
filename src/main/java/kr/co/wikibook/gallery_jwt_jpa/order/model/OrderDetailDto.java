package kr.co.wikibook.gallery_jwt_jpa.order.model;

import lombok.Getter;

@Getter

public class OrderDetailDto {
    private int id;
    private String name;
    private String imgPath;
    private int price;
    private int discountPer;
}
