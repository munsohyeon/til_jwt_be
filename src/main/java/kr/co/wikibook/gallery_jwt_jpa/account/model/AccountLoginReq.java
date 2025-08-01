package kr.co.wikibook.gallery_jwt_jpa.account.model;

import lombok.Getter;
import lombok.ToString;

@Getter

public class AccountLoginReq {
    private String loginId;
    private String loginPw;
}
