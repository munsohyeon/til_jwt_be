package kr.co.wikibook.gallery_jwt_jpa.config.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

// 쿠키에 데이터 담고 빼고 할 때 사용하는 객체
@Slf4j
@Component // 빈등록(객체 대리 생성 - 싱글톤 객체 하나만)
@RequiredArgsConstructor
public class CookieUtils {
    /*
    response: 쿠키를 담을 때 필요함
    name: 쿠키에 담을 벨류의 레이블(키값)
    value: 쿠키에 담을 벨류
    maxAge: 쿠키에 담긴 벨류의 유효 기간
    path: 설정한 경로에 요청이 갈 때만 쿠키만 전달된다.
     */
    public void setCookie(HttpServletResponse response, String name, String value, int maxAge, String path) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(true); // 보안 쿠키
        response.addCookie(cookie);
    }
    public String getValue(HttpServletRequest request, String name) {
        Cookie cookie = getCookie(request, name);
        if (cookie == null) {return null;}
        return cookie.getValue();
    }

    private Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies(); // 쿠키가 req에 여러개 있을 수 있기 때문에 배열로 리턴
        if (cookies != null && cookies.length > 0) { // 쿠키에 뭔가 담겨져있다면
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) { // 쿠키에 담긴 이름이 같은게 있다면
                    return cookie; // 해당 쿠키를 리턴
                }
            }
        }
        // TODO: 예외처리
        return null;
//        if (cookies == null || cookies.length == 0) { return null; } // 쿠키가 하나도 없으면 return null
//
//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equals(name)) {}
//        }
    }
    public void deleteCookie(HttpServletResponse response, String name) {
        setCookie(response, name, null, 0, null);
    }
}
