package com.bachhoaxanh.cart;

import com.bachhoaxanh.customer.Customer;
import com.bachhoaxanh.lineitem.LineItem;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing GioHang (Cart).
 * Primary key "maGioHang" is VARCHAR(50).
 * One-to-One unique relationship with KhachHang (Customer).
 */
@Entity
@Table(name = "\"GioHang\"")
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "\"maGioHang\"", length = 50, nullable = false)
    private String maGioHang;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"maKhachHang\"", unique = true, nullable = false)
    private Customer khachHang;

    @Column(name = "\"tongTien\"", nullable = false, precision = 14, scale = 2)
    private BigDecimal tongTien = BigDecimal.ZERO;

    @Column(name = "\"Deleted\"", nullable = false)
    private Boolean deleted = false;

    @OneToMany(mappedBy = "gioHang", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineItem> lineItems = new ArrayList<>();

    // Constructors
    public Cart() {
        this.tongTien = BigDecimal.ZERO;
        this.deleted = false;
    }

    public Cart(String maGioHang, Customer khachHang) {
        this.maGioHang = maGioHang;
        this.khachHang = khachHang;
        this.tongTien = BigDecimal.ZERO;
        this.deleted = false;
    }

    // Getters and Setters
    public String getMaGioHang() {
        return maGioHang;
    }

    public void setMaGioHang(String maGioHang) {
        this.maGioHang = maGioHang;
    }

    public Customer getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(Customer khachHang) {
        this.khachHang = khachHang;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
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

    public void addLineItem(LineItem item) {
        lineItems.add(item);
        item.setGioHang(this);
    }

    public void removeLineItem(LineItem item) {
        lineItems.remove(item);
        item.setGioHang(null);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "maGioHang='" + maGioHang + '\'' +
                ", tongTien=" + tongTien +
                ", deleted=" + deleted +
                '}';
    }
}
