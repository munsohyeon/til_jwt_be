package kr.co.wikibook.gallery_jwt_jpa.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.co.wikibook.gallery_jwt_jpa.config.model.JwtUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountLoginRes {
    private int id;
    @JsonIgnore // json으로 만들어질 때 알아서 빠진다
    private String loginPw;
    @JsonIgnore
    private JwtUser jwtUser;
}
