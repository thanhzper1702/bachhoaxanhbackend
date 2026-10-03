package com.bachhoaxanh.lineitem;

import com.bachhoaxanh.cart.Cart;
import com.bachhoaxanh.order.Order;
import com.bachhoaxanh.product.Product;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Shared LineItem entity between Cart (GioHang) and Order (DonHang).
 * Primary key "lineItemID" is BIGSERIAL.
 * Foreign keys reference VARCHAR(50) IDs for Order, Cart, and Product.
 * soLuong is DOUBLE PRECISION in SQL.
 * Structural constraint: Must belong to EITHER DonHang OR GioHang, never both simultaneously.
 */
@Entity
@Table(name = "\"LineItem\"")
public class LineItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"lineItemID\"")
    private Long lineItemID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"maDonHang\"", nullable = true)
    private Order donHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"maGioHang\"", nullable = true)
    private Cart gioHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"maSanPham\"", nullable = false)
    private Product sanPham;

    @Column(name = "\"soLuong\"", nullable = false)
    private Double soLuong = 1.0;

    @Column(name = "\"thanhTien\"", nullable = false, precision = 14, scale = 2)
    private BigDecimal thanhTien = BigDecimal.ZERO;

    @Column(name = "\"Deleted\"", nullable = false)
    private Boolean deleted = false;

    // Structural constraint validation
    @PrePersist
    @PreUpdate
    public void validateExclusiveParent() {
        boolean hasOrder = (this.donHang != null);
        boolean hasCart = (this.gioHang != null);
        if (hasOrder == hasCart) {
            throw new IllegalStateException(
                "Structural constraint violation: LineItem must belong to EITHER DonHang OR GioHang, never both simultaneously."
            );
        }
    }

    // Constructors
    public LineItem() {
        this.soLuong = 1.0;
        this.thanhTien = BigDecimal.ZERO;
        this.deleted = false;
    }

    // Constructor for Cart line item
    public LineItem(Cart gioHang, Product sanPham, Double soLuong, BigDecimal thanhTien) {
        this.gioHang = gioHang;
        this.sanPham = sanPham;
        this.soLuong = soLuong != null ? soLuong : 1.0;
        this.thanhTien = thanhTien != null ? thanhTien : BigDecimal.ZERO;
        this.deleted = false;
    }

    // Constructor for Order line item
    public LineItem(Order donHang, Product sanPham, Double soLuong, BigDecimal thanhTien) {
        this.donHang = donHang;
        this.sanPham = sanPham;
        this.soLuong = soLuong != null ? soLuong : 1.0;
        this.thanhTien = thanhTien != null ? thanhTien : BigDecimal.ZERO;
        this.deleted = false;
    }

    // Getters and Setters
    public Long getLineItemID() {
        return lineItemID;
    }

    public void setLineItemID(Long lineItemID) {
        this.lineItemID = lineItemID;
    }

    public Order getDonHang() {
        return donHang;
    }

    public void setDonHang(Order donHang) {
        this.donHang = donHang;
    }

    public Cart getGioHang() {
        return gioHang;
    }

    public void setGioHang(Cart gioHang) {
        this.gioHang = gioHang;
    }

    public Product getSanPham() {
        return sanPham;
    }

    public void setSanPham(Product sanPham) {
        this.sanPham = sanPham;
    }

    public Double getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Double soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "LineItem{" +
                "lineItemID=" + lineItemID +
                ", soLuong=" + soLuong +
                ", thanhTien=" + thanhTien +
                ", deleted=" + deleted +
                '}';
    }
}
