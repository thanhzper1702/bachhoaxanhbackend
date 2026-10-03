package com.bachhoaxanh.promotion;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity representing KhuyenMai (Promotion).
 * Primary key "maKhuyenMai" is VARCHAR(50).
 * loaiGiamGia mapped to PromotionType enum (THEO_PHANTRAM, THEO_TIENCODINH).
 * trangThai is BOOLEAN NOT NULL DEFAULT TRUE in SQL.
 * Includes moTa TEXT.
 */
@Entity
@Table(name = "\"KhuyenMai\"")
public class Promotion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "\"maKhuyenMai\"", length = 50, nullable = false)
    private String maKhuyenMai;

    @Column(name = "\"tenKhuyenMai\"", nullable = false, length = 200)
    private String tenKhuyenMai;

    @Enumerated(EnumType.STRING)
    @Column(name = "\"loaiGiamGia\"", nullable = false, length = 50)
    private PromotionType loaiGiamGia;

    @Column(name = "\"giaTriGiam\"", nullable = false, precision = 12, scale = 2)
    private BigDecimal giaTriGiam = BigDecimal.ZERO;

    @Column(name = "\"ngayBatDau\"", nullable = false)
    private LocalDateTime ngayBatDau;

    @Column(name = "\"ngayKetThuc\"", nullable = false)
    private LocalDateTime ngayKetThuc;

    @Column(name = "\"gioiHanLuotDung\"", nullable = false)
    private Integer gioiHanLuotDung = 100;

    @Column(name = "\"giaTriDonHangToiThieu\"", nullable = false, precision = 12, scale = 2)
    private BigDecimal giaTriDonHangToiThieu = BigDecimal.ZERO;

    @Column(name = "\"trangThai\"", nullable = false)
    private Boolean trangThai = true;

    @Column(name = "\"moTa\"", columnDefinition = "TEXT")
    private String moTa;

    @Column(name = "\"Deleted\"", nullable = false)
    private Boolean deleted = false;

    // Constructors
    public Promotion() {
        this.trangThai = true;
        this.gioiHanLuotDung = 100;
        this.giaTriDonHangToiThieu = BigDecimal.ZERO;
        this.deleted = false;
    }

    public Promotion(String maKhuyenMai, String tenKhuyenMai, PromotionType loaiGiamGia,
                     BigDecimal giaTriGiam, LocalDateTime ngayBatDau, LocalDateTime ngayKetThuc,
                     Integer gioiHanLuotDung, BigDecimal giaTriDonHangToiThieu, Boolean trangThai, String moTa) {
        this.maKhuyenMai = maKhuyenMai;
        this.tenKhuyenMai = tenKhuyenMai;
        this.loaiGiamGia = loaiGiamGia;
        this.giaTriGiam = giaTriGiam != null ? giaTriGiam : BigDecimal.ZERO;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.gioiHanLuotDung = gioiHanLuotDung != null ? gioiHanLuotDung : 100;
        this.giaTriDonHangToiThieu = giaTriDonHangToiThieu != null ? giaTriDonHangToiThieu : BigDecimal.ZERO;
        this.trangThai = trangThai != null ? trangThai : true;
        this.moTa = moTa;
        this.deleted = false;
    }

    // Getters and Setters
    public String getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public void setMaKhuyenMai(String maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        this.tenKhuyenMai = tenKhuyenMai;
    }

    public PromotionType getLoaiGiamGia() {
        return loaiGiamGia;
    }

    public void setLoaiGiamGia(PromotionType loaiGiamGia) {
        this.loaiGiamGia = loaiGiamGia;
    }

    public BigDecimal getGiaTriGiam() {
        return giaTriGiam;
    }

    public void setGiaTriGiam(BigDecimal giaTriGiam) {
        this.giaTriGiam = giaTriGiam;
    }

    public LocalDateTime getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDateTime ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDateTime getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDateTime ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public Integer getGioiHanLuotDung() {
        return gioiHanLuotDung;
    }

    public void setGioiHanLuotDung(Integer gioiHanLuotDung) {
        this.gioiHanLuotDung = gioiHanLuotDung;
    }

    public BigDecimal getGiaTriDonHangToiThieu() {
        return giaTriDonHangToiThieu;
    }

    public void setGiaTriDonHangToiThieu(BigDecimal giaTriDonHangToiThieu) {
        this.giaTriDonHangToiThieu = giaTriDonHangToiThieu;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "maKhuyenMai='" + maKhuyenMai + '\'' +
                ", tenKhuyenMai='" + tenKhuyenMai + '\'' +
                ", loaiGiamGia=" + loaiGiamGia +
                ", giaTriGiam=" + giaTriGiam +
                ", trangThai=" + trangThai +
                ", deleted=" + deleted +
                '}';
    }
}
