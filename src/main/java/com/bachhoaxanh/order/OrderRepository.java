package com.bachhoaxanh.order;

import com.bachhoaxanh.common.BaseRepository;
import java.util.List;

/**
 * Data access skeleton interface for Order entity with VARCHAR(50) PK.
 */
public interface OrderRepository extends BaseRepository<Order, String> {
    List<Order> findByKhachHangMaNguoiDungAndDeletedFalse(String maKhachHang);
    List<Order> findByTrangThaiAndDeletedFalse(OrderStatus status);
}
