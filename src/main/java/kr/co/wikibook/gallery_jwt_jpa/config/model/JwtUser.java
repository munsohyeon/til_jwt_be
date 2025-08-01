package kr.co.wikibook.gallery_jwt_jpa.config.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class JwtUser {
    private int signedUserId;
    private List<String> roles; // 인가 처리 떄 사용
    /*
       role 이름은 ROLE_아무거나

       ROLE_USER, ROLE_ADMIN, ROLE_MANAGER, ROLE_LEVEL_1...
     */
}
