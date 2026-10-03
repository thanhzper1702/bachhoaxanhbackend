package com.bachhoaxanh.supplier;

import com.bachhoaxanh.product.Product;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing NhaCungCap (Supplier).
 * Primary key "maNhaCungCap" is VARCHAR(50).
 */
@Entity
@Table(name = "\"NhaCungCap\"")
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "\"maNhaCungCap\"", length = 50, nullable = false)
    private String maNhaCungCap;

    @Column(name = "\"tenNhaCungCap\"", nullable = false, length = 200)
    private String tenNhaCungCap;

    @Column(name = "\"email\"", length = 150)
    private String email;

    @Column(name = "\"soDienThoai\"", length = 20)
    private String soDienThoai;

    @Column(name = "\"dangHopTac\"", nullable = false)
    private Boolean dangHopTac = true;

    @Column(name = "\"Deleted\"", nullable = false)
    private Boolean deleted = false;

    @OneToMany(mappedBy = "nhaCungCap")
    private List<Product> products = new ArrayList<>();

    // Constructors
    public Supplier() {
        this.dangHopTac = true;
        this.deleted = false;
    }

    public Supplier(String maNhaCungCap, String tenNhaCungCap, String email, String soDienThoai, Boolean dangHopTac) {
        this.maNhaCungCap = maNhaCungCap;
        this.tenNhaCungCap = tenNhaCungCap;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.dangHopTac = dangHopTac != null ? dangHopTac : true;
        this.deleted = false;
    }

    // Getters and Setters
    public String getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public Boolean getDangHopTac() {
        return dangHopTac;
    }

    public void setDangHopTac(Boolean dangHopTac) {
        this.dangHopTac = dangHopTac;
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
        return "Supplier{" +
                "maNhaCungCap='" + maNhaCungCap + '\'' +
                ", tenNhaCungCap='" + tenNhaCungCap + '\'' +
                ", email='" + email + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", dangHopTac=" + dangHopTac +
                ", deleted=" + deleted +
                '}';
    }
}
