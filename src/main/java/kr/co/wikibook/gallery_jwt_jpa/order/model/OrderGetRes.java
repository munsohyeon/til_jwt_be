package kr.co.wikibook.gallery_jwt_jpa.order.model;

import lombok.Getter;

@Getter

public class OrderGetRes {
    private int id;
    private String name;
    private String payment;
    private long amount;
    private String created;
}
