package kr.co.wikibook.gallery_jwt_jpa.account;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.wikibook.gallery_jwt_jpa.account.etc.AccountConstants;
import kr.co.wikibook.gallery_jwt_jpa.account.model.AccountJoinReq;
import kr.co.wikibook.gallery_jwt_jpa.account.model.AccountLoginReq;
import kr.co.wikibook.gallery_jwt_jpa.account.model.AccountLoginRes;
import kr.co.wikibook.gallery_jwt_jpa.config.jwt.JwtTokenManager;
import kr.co.wikibook.gallery_jwt_jpa.config.util.HttpUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j // log
@RestController// 서비스와 연동
@RequiredArgsConstructor // final @nonnull 붙은 필드에 생성자 생성해 주는 역할
@RequestMapping("/api/v1/account")
public class AccountController {
    private final AccountService accountService;
    private final JwtTokenManager jwtTokenManager;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody AccountJoinReq req) {
        // hasLength()는 static메소드 클래스에 속한 메서드
        if(!StringUtils.hasLength(req.getName())
                                  || !StringUtils.hasLength(req.getLoginId())
                                  || !StringUtils.hasLength(req.getLoginPw())) {
            return ResponseEntity.badRequest().build(); //state: 400
        }
        int result = accountService.join(req);
        return ResponseEntity.ok(result); // state: 200
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletResponse respones, @RequestBody AccountLoginReq req) {
        AccountLoginRes result = accountService.login(req);
        if(result == null) {
            return ResponseEntity.notFound().build();
        }
        jwtTokenManager.issue(respones, result.getJwtUser());
        // 세션 처리
        // HttpUtils.setSession(httpReq, AccountConstants.MEMBER_ID_NAME, result.getId());
        return ResponseEntity.ok(result);
    }

//    @GetMapping("/check")
//    public ResponseEntity<?> check(HttpServletRequest httpReq) {
//        Integer id = (Integer)HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
//        log.info("id: {}", id);
//        return ResponseEntity.ok(id);
//    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse respones) {
        jwtTokenManager.reissue(request, respones);
        return ResponseEntity.ok(1);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse respones) {
        //HttpUtils.removeSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        jwtTokenManager.logout(respones);
        return ResponseEntity.ok(1);
    }


}




