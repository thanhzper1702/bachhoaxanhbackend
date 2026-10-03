package com.bachhoaxanh.review;

import com.bachhoaxanh.customer.Customer;
import com.bachhoaxanh.product.Product;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Entity representing DanhGia (Review).
 * Primary key "maDanhGia" is BIGSERIAL.
 * Foreign keys link KhachHang (Customer) and SanPham (Product) via VARCHAR(50).
 * Unique constraint on (maKhachHang, maSanPham).
 */
@Entity
@Table(
    name = "\"DanhGia\"",
    uniqueConstraints = @UniqueConstraint(name = "uq_khachhang_sanpham_danhgia", columnNames = {"\"maKhachHang\"", "\"maSanPham\""})
)
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"maDanhGia\"")
    private Long maDanhGia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"maKhachHang\"", nullable = false)
    private Customer khachHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"maSanPham\"", nullable = false)
    private Product sanPham;

    @Column(name = "\"soSao\"", nullable = false)
    private Integer soSao;

    @Column(name = "\"noiDung\"", columnDefinition = "TEXT")
    private String noiDung;

    @Column(name = "\"ngayDang\"", nullable = false)
    private LocalDateTime ngayDang = LocalDateTime.now();

    @Column(name = "\"Deleted\"", nullable = false)
    private Boolean deleted = false;

    // Constructors
    public Review() {
        this.ngayDang = LocalDateTime.now();
        this.deleted = false;
    }

    public Review(Customer khachHang, Product sanPham, Integer soSao, String noiDung) {
        this.khachHang = khachHang;
        this.sanPham = sanPham;
        this.soSao = soSao;
        this.noiDung = noiDung;
        this.ngayDang = LocalDateTime.now();
        this.deleted = false;
    }

    // Getters and Setters
    public Long getMaDanhGia() {
        return maDanhGia;
    }

    public void setMaDanhGia(Long maDanhGia) {
        this.maDanhGia = maDanhGia;
    }

    public Customer getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(Customer khachHang) {
        this.khachHang = khachHang;
    }

    public Product getSanPham() {
        return sanPham;
    }

    public void setSanPham(Product sanPham) {
        this.sanPham = sanPham;
    }

    public Integer getSoSao() {
        return soSao;
    }

    public void setSoSao(Integer soSao) {
        this.soSao = soSao;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public LocalDateTime getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(LocalDateTime ngayDang) {
        this.ngayDang = ngayDang;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Review{" +
                "maDanhGia=" + maDanhGia +
                ", soSao=" + soSao +
                ", noiDung='" + noiDung + '\'' +
                ", ngayDang=" + ngayDang +
                ", deleted=" + deleted +
                '}';
    }
}
