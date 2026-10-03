package com.bachhoaxanh.product;

import com.bachhoaxanh.category.Category;
import com.bachhoaxanh.supplier.Supplier;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entity representing SanPham (Product).
 * Primary key "maSanPham" is VARCHAR(50).
 * Foreign key column to Category is "maLoai".
 * phiVAT is DOUBLE PRECISION in SQL.
 */
@Entity
@Table(name = "\"SanPham\"")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "\"maSanPham\"", length = 50, nullable = false)
    private String maSanPham;

    @Column(name = "\"tenSanPham\"", nullable = false, length = 255)
    private String tenSanPham;

    @Column(name = "\"hinhAnh\"", length = 500)
    private String hinhAnh;

    @Column(name = "\"hanSuDung\"")
    private LocalDate hanSuDung;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"maLoai\"", nullable = false)
    private Category loaiSanPham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"maNhaCungCap\"", nullable = false)
    private Supplier nhaCungCap;

    @Column(name = "\"giaNhap\"", nullable = false, precision = 12, scale = 2)
    private BigDecimal giaNhap = BigDecimal.ZERO;

    @Column(name = "\"phiVAT\"", nullable = false)
    private Double phiVAT = 0.0;

    @Column(name = "\"giaBan\"", nullable = false, precision = 12, scale = 2)
    private BigDecimal giaBan;

    @Enumerated(EnumType.STRING)
    @Column(name = "\"donViTinh\"", nullable = false, length = 50)
    private Unit donViTinh;

    @Column(name = "\"soLuong\"", nullable = false)
    private Integer soLuong = 0;

    @Column(name = "\"Deleted\"", nullable = false)
    private Boolean deleted = false;

    // Constructors
    public Product() {
        this.deleted = false;
        this.giaNhap = BigDecimal.ZERO;
        this.phiVAT = 0.0;
        this.soLuong = 0;
    }

    public Product(String maSanPham, String tenSanPham, String hinhAnh, LocalDate hanSuDung,
                   Category loaiSanPham, Supplier nhaCungCap, BigDecimal giaNhap,
                   Double phiVAT, BigDecimal giaBan, Unit donViTinh, Integer soLuong) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.hinhAnh = hinhAnh;
        this.hanSuDung = hanSuDung;
        this.loaiSanPham = loaiSanPham;
        this.nhaCungCap = nhaCungCap;
        this.giaNhap = giaNhap != null ? giaNhap : BigDecimal.ZERO;
        this.phiVAT = phiVAT != null ? phiVAT : 0.0;
        this.giaBan = giaBan;
        this.donViTinh = donViTinh;
        this.soLuong = soLuong != null ? soLuong : 0;
        this.deleted = false;
    }

    // Getters and Setters
    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public LocalDate getHanSuDung() {
        return hanSuDung;
    }

    public void setHanSuDung(LocalDate hanSuDung) {
        this.hanSuDung = hanSuDung;
    }

    public Category getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(Category loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }

    public Supplier getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(Supplier nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public BigDecimal getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(BigDecimal giaNhap) {
        this.giaNhap = giaNhap;
    }

    public Double getPhiVAT() {
        return phiVAT;
    }

    public void setPhiVAT(Double phiVAT) {
        this.phiVAT = phiVAT;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public Unit getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(Unit donViTinh) {
        this.donViTinh = donViTinh;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Product{" +
                "maSanPham='" + maSanPham + '\'' +
                ", tenSanPham='" + tenSanPham + '\'' +
                ", giaBan=" + giaBan +
                ", phiVAT=" + phiVAT +
                ", donViTinh=" + donViTinh +
                ", soLuong=" + soLuong +
                ", deleted=" + deleted +
                '}';
    }
}
