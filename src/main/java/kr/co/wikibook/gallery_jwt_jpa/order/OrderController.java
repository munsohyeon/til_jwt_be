package kr.co.wikibook.gallery_jwt_jpa.order;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.wikibook.gallery_jwt_jpa.account.etc.AccountConstants;
import kr.co.wikibook.gallery_jwt_jpa.config.util.HttpUtils;
import kr.co.wikibook.gallery_jwt_jpa.order.model.OrderDetailGetReq;
import kr.co.wikibook.gallery_jwt_jpa.order.model.OrderDetailGetRes;
import kr.co.wikibook.gallery_jwt_jpa.order.model.OrderGetRes;
import kr.co.wikibook.gallery_jwt_jpa.order.model.OrderPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> add(HttpServletRequest httpReq, @RequestBody OrderPostReq req) {
        log.info("req: {}", req);
        int loggindeMemberId = (int) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        int result = orderService.saveOrder(req, loggindeMemberId);
        return  ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<?> findAllMemberId(HttpServletRequest httpReq) {
        log.info("Httpreq: {}", httpReq);
        int loggindeMemberId = (int) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        List<OrderGetRes> result = orderService.findAllByMemberId(loggindeMemberId);
        return  ResponseEntity.ok(result);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> findById(@PathVariable int orderId, HttpServletRequest httpReq) {
        int loggedInMemberId = (int) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        OrderDetailGetReq req = new OrderDetailGetReq();
        req.setOrderId(orderId);
        req.setMemberId(loggedInMemberId);
        OrderDetailGetRes result = orderService.detail(req);
        return ResponseEntity.ok(result);
    }

}
