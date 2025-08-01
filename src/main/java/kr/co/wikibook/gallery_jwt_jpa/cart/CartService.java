package kr.co.wikibook.gallery_jwt_jpa.cart;

import kr.co.wikibook.gallery_jwt_jpa.cart.model.CartDeleteReq;
import kr.co.wikibook.gallery_jwt_jpa.cart.model.CartGetRes;
import kr.co.wikibook.gallery_jwt_jpa.cart.model.CartPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {
    private final CartMapper cartMapper;

    public int save(CartPostReq req) {
        return cartMapper.save(req);
    }

    public List<CartGetRes> findAll(int memberId) {
        return cartMapper.findAllWithItemByMemberId(memberId);
    }

    public int remove(CartDeleteReq req) {
        return cartMapper.deleteByCartIdAndMemberId(req);
    }

    public int removeAll(int memberId) {
        return cartMapper.deleteByMemberId(memberId);
    }
}
