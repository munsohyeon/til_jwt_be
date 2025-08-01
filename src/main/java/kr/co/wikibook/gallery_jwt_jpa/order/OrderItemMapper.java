package kr.co.wikibook.gallery_jwt_jpa.order;

import kr.co.wikibook.gallery_jwt_jpa.order.model.OrderDetailDto;
import kr.co.wikibook.gallery_jwt_jpa.order.model.OrderItemPostDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderItemMapper {
    int save(OrderItemPostDto req);
    List<OrderDetailDto> findAllByOrderId(int orderId);
}
