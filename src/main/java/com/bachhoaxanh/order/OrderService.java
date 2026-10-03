package com.bachhoaxanh.order;

import java.util.List;
import java.util.Optional;

/**
 * Business logic service skeleton class for Order operations with VARCHAR(50) PK.
 */
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Optional<Order> getOrderById(String maDonHang) {
        return orderRepository.findById(maDonHang);
    }

    public List<Order> getOrdersByCustomer(String maKhachHang) {
        return orderRepository.findByKhachHangMaNguoiDungAndDeletedFalse(maKhachHang);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAllActive();
    }

    public Order markAsPaid(String maDonHang) {
        return null;
    }

    public void cancelOrder(String maDonHang) {
        orderRepository.softDeleteById(maDonHang);
    }
}
