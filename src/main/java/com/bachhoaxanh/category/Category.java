package com.bachhoaxanh.category;

import com.bachhoaxanh.product.Product;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing LoaiSanPham (Category).
 * Primary key "maLoaiSanPham" is VARCHAR(50).
 * phanTramLoiNhuan is DOUBLE PRECISION in SQL.
 */
@Entity
@Table(name = "\"LoaiSanPham\"")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "\"maLoaiSanPham\"", length = 50, nullable = false)
    private String maLoaiSanPham;

    @Column(name = "\"tenLoaiSanPham\"", nullable = false, length = 150)
    private String tenLoaiSanPham;

    @Column(name = "\"phanTramLoiNhuan\"", nullable = false)
    private Double phanTramLoiNhuan = 0.0;

    @Column(name = "\"Deleted\"", nullable = false)
    private Boolean deleted = false;

    @OneToMany(mappedBy = "loaiSanPham")
    private List<Product> products = new ArrayList<>();

    // Constructors
    public Category() {
        this.phanTramLoiNhuan = 0.0;
        this.deleted = false;
    }

    public Category(String maLoaiSanPham, String tenLoaiSanPham, Double phanTramLoiNhuan) {
        this.maLoaiSanPham = maLoaiSanPham;
        this.tenLoaiSanPham = tenLoaiSanPham;
        this.phanTramLoiNhuan = phanTramLoiNhuan != null ? phanTramLoiNhuan : 0.0;
        this.deleted = false;
    }

    // Getters and Setters
    public String getMaLoaiSanPham() {
        return maLoaiSanPham;
    }

    public void setMaLoaiSanPham(String maLoaiSanPham) {
        this.maLoaiSanPham = maLoaiSanPham;
    }

    public String getTenLoaiSanPham() {
        return tenLoaiSanPham;
    }

    public void setTenLoaiSanPham(String tenLoaiSanPham) {
        this.tenLoaiSanPham = tenLoaiSanPham;
    }

    public Double getPhanTramLoiNhuan() {
        return phanTramLoiNhuan;
    }

    public void setPhanTramLoiNhuan(Double phanTramLoiNhuan) {
        this.phanTramLoiNhuan = phanTramLoiNhuan;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "maLoaiSanPham='" + maLoaiSanPham + '\'' +
                ", tenLoaiSanPham='" + tenLoaiSanPham + '\'' +
                ", phanTramLoiNhuan=" + phanTramLoiNhuan +
                ", deleted=" + deleted +
                '}';
    }
}
