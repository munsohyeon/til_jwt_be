package kr.co.wikibook.gallery_jwt_jpa.cart;

import kr.co.wikibook.gallery_jwt_jpa.cart.model.CartDeleteReq;
import kr.co.wikibook.gallery_jwt_jpa.cart.model.CartGetRes;
import kr.co.wikibook.gallery_jwt_jpa.cart.model.CartPostReq;
import kr.co.wikibook.gallery_jwt_jpa.config.model.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<?> save(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody CartPostReq req) {
        log.info("req: {}", req);
        req.setMemberId(userPrincipal.getMemberId());
        int result = cartService.save(req);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<?> getCart(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<CartGetRes> result = cartService.findAll(userPrincipal.getMemberId());
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<?> deleteMemberItem(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable int cartId) {
        CartDeleteReq req = new CartDeleteReq(cartId, userPrincipal.getMemberId());
        int result = cartService.remove(req);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteMemberCart(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        int logginedMemberId = userPrincipal.getMemberId();
        int result = cartService.removeAll(logginedMemberId);
        return ResponseEntity.ok(result);
    }
}