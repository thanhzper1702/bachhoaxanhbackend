package com.bachhoaxanh.order;

import com.bachhoaxanh.customer.Customer;
import com.bachhoaxanh.lineitem.LineItem;
import com.bachhoaxanh.payment.Payment;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing DonHang (Order).
 * Primary key "maDonHang" is VARCHAR(50).
 * Status strictly restricted to OrderStatus (CHUATHANHTOAN, DATHANHTOAN).
 */
@Entity
@Table(name = "\"DonHang\"")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "\"maDonHang\"", length = 50, nullable = false)
    private String maDonHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"maKhachHang\"", nullable = false)
    private Customer khachHang;

    @Column(name = "\"ngayLap\"", nullable = false)
    private LocalDateTime ngayLap = LocalDateTime.now();

    @Column(name = "\"ngayHetHanThanhToan\"", nullable = false)
    private LocalDateTime ngayHetHanThanhToan;

    @Column(name = "\"ghiChu\"", columnDefinition = "TEXT")
    private String ghiChu;

    @Enumerated(EnumType.STRING)
    @Column(name = "\"trangThai\"", nullable = false, length = 50)
    private OrderStatus trangThai = OrderStatus.CHUATHANHTOAN;

    @Column(name = "\"tongTien\"", nullable = false, precision = 14, scale = 2)
    private BigDecimal tongTien = BigDecimal.ZERO;

    @Column(name = "\"tongTienSauGiamGia\"", nullable = false, precision = 14, scale = 2)
    private BigDecimal tongTienSauGiamGia = BigDecimal.ZERO;

    @Column(name = "\"Deleted\"", nullable = false)
    private Boolean deleted = false;

    @OneToMany(mappedBy = "donHang", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineItem> lineItems = new ArrayList<>();

    @OneToMany(mappedBy = "donHang", cascade = CascadeType.ALL)
    private List<Payment> thanhToans = new ArrayList<>();

    // Constructors
    public Order() {
        this.ngayLap = LocalDateTime.now();
        this.trangThai = OrderStatus.CHUATHANHTOAN;
        this.tongTien = BigDecimal.ZERO;
        this.tongTienSauGiamGia = BigDecimal.ZERO;
        this.deleted = false;
    }

    public Order(String maDonHang, Customer khachHang, BigDecimal tongTien,
                 BigDecimal tongTienSauGiamGia, LocalDateTime ngayHetHanThanhToan, String ghiChu) {
        this.maDonHang = maDonHang;
        this.khachHang = khachHang;
        this.tongTien = tongTien != null ? tongTien : BigDecimal.ZERO;
        this.tongTienSauGiamGia = tongTienSauGiamGia != null ? tongTienSauGiamGia : BigDecimal.ZERO;
        this.ngayLap = LocalDateTime.now();
        this.ngayHetHanThanhToan = ngayHetHanThanhToan;
        this.ghiChu = ghiChu;
        this.trangThai = OrderStatus.CHUATHANHTOAN;
        this.deleted = false;
    }

    // Getters and Setters
    public String getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public Customer getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(Customer khachHang) {
        this.khachHang = khachHang;
    }

    public LocalDateTime getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDateTime ngayLap) {
        this.ngayLap = ngayLap;
    }

    public LocalDateTime getNgayHetHanThanhToan() {
        return ngayHetHanThanhToan;
    }

    public void setNgayHetHanThanhToan(LocalDateTime ngayHetHanThanhToan) {
        this.ngayHetHanThanhToan = ngayHetHanThanhToan;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public OrderStatus getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(OrderStatus trangThai) {
        this.trangThai = trangThai;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public BigDecimal getTongTienSauGiamGia() {
        return tongTienSauGiamGia;
    }

    public void setTongTienSauGiamGia(BigDecimal tongTienSauGiamGia) {
        this.tongTienSauGiamGia = tongTienSauGiamGia;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public List<Payment> getThanhToans() {
        return thanhToans;
    }

    public void setThanhToans(List<Payment> thanhToans) {
        this.thanhToans = thanhToans;
    }

    public void addLineItem(LineItem item) {
        lineItems.add(item);
        item.setDonHang(this);
    }

    public void removeLineItem(LineItem item) {
        lineItems.remove(item);
        item.setDonHang(null);
    }

    public void addPayment(Payment payment) {
        thanhToans.add(payment);
        payment.setDonHang(this);
    }

    @Override
    public String toString() {
        return "Order{" +
                "maDonHang='" + maDonHang + '\'' +
                ", ngayLap=" + ngayLap +
                ", tongTien=" + tongTien +
                ", tongTienSauGiamGia=" + tongTienSauGiamGia +
                ", trangThai=" + trangThai +
                ", deleted=" + deleted +
                '}';
    }
}
