package kr.co.wikibook.gallery_jwt_jpa.order;

import kr.co.wikibook.gallery_jwt_jpa.cart.CartMapper;
import kr.co.wikibook.gallery_jwt_jpa.item.ItemMapper;
import kr.co.wikibook.gallery_jwt_jpa.item.model.ItemGetRes;
import kr.co.wikibook.gallery_jwt_jpa.order.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor

public class OrderService {
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final ItemMapper itemMapper;
    private final CartMapper cartMapper;

    @Transactional //
    public int saveOrder(OrderPostReq req, int loggindeMemberId) {
        // 주문하고자 하는 상품 정보 DB로 부터 가져온다.
        List<ItemGetRes> itemList = itemMapper.findAllByIdIn(req.getItemIds());
        log.info("itemList={}", itemList);
        // 총 구매가격 콘솔에 출력!!

        int result = 0;
        for (ItemGetRes item : itemList) {
            result += item.getPrice() - (item.getPrice() * item.getDiscountPer()) / 100;
        }
        log.info("result={}", result);
        // 총 구매가격 콘솔에 출력!!

        // 만드세요! OrderPostDto 객체화하시고 데이터를 넣어주세요!
        // @Builder라서 생성자도 넣어야함!
        OrderPostDto dto = OrderPostDto.builder()
                .memberId(loggindeMemberId)
                .name(req.getName())
                .address(req.getAddress())
                .payment(req.getPayment())
                .cardNumber(req.getCardNumber())
                .amount(result)
                .build();
        log.info("before-dto = {}", dto);
        int amount = orderMapper.save(dto);
        log.info("after-dto = {}", dto);

        // OrderItemPostDto 객체화 하시면서 데이터 넣어주세요.
        OrderItemPostDto orderItemPostDto = new OrderItemPostDto(dto.getOrderId(), req.getItemIds());
        log.info("orderItemPostDto = {}", orderItemPostDto);
        orderItemMapper.save(orderItemPostDto);

        cartMapper.deleteByMemberId(loggindeMemberId);
        return 1;
    }
//    public List<OrderGetRes> getOrderAll(int id) {
//        return OrderMapper.findAllByMemberIdOrderByIdDesc(id);
//    }
    public List<OrderGetRes> findAllByMemberId(int memberId) {
        /*
        List<OrderGetRes> list = orderMapper.findAllByMemberIdOrderByIdDesc(memberId);
        for (OrderGetRes order : list) {

        }
        */
        return orderMapper.findAllByMemberIdOrderByIdDesc(memberId);
    }

    public OrderDetailGetRes detail(OrderDetailGetReq req) {
        OrderDetailGetRes result = orderMapper.findByOrderIdAndMemberId(req);
        List<OrderDetailDto> items = orderItemMapper.findAllByOrderId(req.getOrderId());
        result.setItems(items);
        log.info("result={}", result);
        return result;
    }

}



