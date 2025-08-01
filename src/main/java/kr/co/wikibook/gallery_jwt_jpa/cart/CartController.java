package kr.co.wikibook.gallery_jwt_jpa.cart;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.wikibook.gallery_jwt_jpa.account.etc.AccountConstants;
import kr.co.wikibook.gallery_jwt_jpa.cart.model.CartDeleteReq;
import kr.co.wikibook.gallery_jwt_jpa.cart.model.CartGetRes;
import kr.co.wikibook.gallery_jwt_jpa.cart.model.CartPostReq;
import kr.co.wikibook.gallery_jwt_jpa.config.util.HttpUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<?> save(HttpServletRequest httpReq, @RequestBody CartPostReq req) {
        log.info("req: {}", req);
        int logginedMemberId = (int) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        req.setMemberId(logginedMemberId);
        int result = cartService.save(req);
        return ResponseEntity.ok(result) ;
    }

    @GetMapping
    public ResponseEntity<List<CartGetRes>> getCart(HttpServletRequest httpReq) {
        int loggedInMemberId = (int) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        log.info("loggedInMemberId: {}", loggedInMemberId);
        List<CartGetRes> result = cartService.findAll(loggedInMemberId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<?> deleteMemberItem(HttpServletRequest httpReq, @PathVariable int cartId) {
        int loggedInMemberId = (int) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        CartDeleteReq req = new CartDeleteReq(cartId, loggedInMemberId);
        int result = cartService.remove(req);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteMemberCart(HttpServletRequest httpReq) {
        int logginedMemberId = (int)HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        int result = cartService.removeAll(logginedMemberId);
        return ResponseEntity.ok(result);
    }

}
