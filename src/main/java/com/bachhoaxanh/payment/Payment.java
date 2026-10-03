package com.bachhoaxanh.payment;

import com.bachhoaxanh.order.Order;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity representing ThanhToan (Payment).
 * Primary key "maTT" is VARCHAR(50).
 * Status enum: THANHCONG, DANGXULY, THATBAI.
 */
@Entity
@Table(name = "\"ThanhToan\"")
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "\"maTT\"", length = 50, nullable = false)
    private String maTT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"maDonHang\"", nullable = false)
    private Order donHang;

    @Column(name = "\"soTien\"", nullable = false, precision = 14, scale = 2)
    private BigDecimal soTien;

    @Column(name = "\"ngayThanhToan\"", nullable = false)
    private LocalDateTime ngayThanhToan = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "\"trangThai\"", nullable = false, length = 50)
    private PaymentStatus trangThai = PaymentStatus.DANGXULY;

    @Column(name = "\"Deleted\"", nullable = false)
    private Boolean deleted = false;

    // Constructors
    public Payment() {
        this.ngayThanhToan = LocalDateTime.now();
        this.trangThai = PaymentStatus.DANGXULY;
        this.deleted = false;
    }

    public Payment(String maTT, Order donHang, BigDecimal soTien, PaymentStatus trangThai) {
        this.maTT = maTT;
        this.donHang = donHang;
        this.soTien = soTien;
        this.ngayThanhToan = LocalDateTime.now();
        this.trangThai = trangThai != null ? trangThai : PaymentStatus.DANGXULY;
        this.deleted = false;
    }

    // Getters and Setters
    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public Order getDonHang() {
        return donHang;
    }

    public void setDonHang(Order donHang) {
        this.donHang = donHang;
    }

    public BigDecimal getSoTien() {
        return soTien;
    }

    public void setSoTien(BigDecimal soTien) {
        this.soTien = soTien;
    }

    public LocalDateTime getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(LocalDateTime ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public PaymentStatus getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(PaymentStatus trangThai) {
        this.trangThai = trangThai;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "maTT='" + maTT + '\'' +
                ", soTien=" + soTien +
                ", ngayThanhToan=" + ngayThanhToan +
                ", trangThai=" + trangThai +
                ", deleted=" + deleted +
                '}';
    }
}
