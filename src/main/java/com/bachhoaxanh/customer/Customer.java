package com.bachhoaxanh.customer;

import com.bachhoaxanh.address.Address;
import com.bachhoaxanh.cart.Cart;
import com.bachhoaxanh.order.Order;
import com.bachhoaxanh.review.Review;
import com.bachhoaxanh.user.User;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing KhachHang (Customer), extending NguoiDung (User) via JOINED inheritance.
 * Primary key "maKhachHang" is VARCHAR(50), serving as PK and FK referencing "maNguoiDung".
 */
@Entity
@Table(name = "\"KhachHang\"")
@PrimaryKeyJoinColumn(name = "\"maKhachHang\"", referencedColumnName = "\"maNguoiDung\"")
public class Customer extends User {

    private static final long serialVersionUID = 1L;

    @Column(name = "\"Deleted\"", nullable = false)
    private Boolean deleted = false;

    // Customer Relationships
    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> diaChis = new ArrayList<>();

    @OneToOne(mappedBy = "khachHang", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart gioHang;

    @OneToMany(mappedBy = "khachHang")
    private List<Order> donHangs = new ArrayList<>();

    @OneToMany(mappedBy = "khachHang")
    private List<Review> danhGias = new ArrayList<>();

    // Constructors
    public Customer() {
        super();
        this.deleted = false;
    }

    public Customer(String maKhachHang, String tenND, LocalDate ngaySinh, String soDienThoai, String matKhauHashed) {
        super(maKhachHang, tenND, ngaySinh, soDienThoai, matKhauHashed);
        this.deleted = false;
    }

    // Getters and Setters
    @Override
    public Boolean getDeleted() {
        return deleted;
    }

    @Override
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public List<Address> getDiaChis() {
        return diaChis;
    }

    public void setDiaChis(List<Address> diaChis) {
        this.diaChis = diaChis;
    }

    public Cart getGioHang() {
        return gioHang;
    }

    public void setGioHang(Cart gioHang) {
        this.gioHang = gioHang;
    }

    public List<Order> getDonHangs() {
        return donHangs;
    }

    public void setDonHangs(List<Order> donHangs) {
        this.donHangs = donHangs;
    }

    public List<Review> getDanhGias() {
        return danhGias;
    }

    public void setDanhGias(List<Review> danhGias) {
        this.danhGias = danhGias;
    }
}
