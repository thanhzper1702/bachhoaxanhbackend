package com.bachhoaxanh.address;

import com.bachhoaxanh.customer.Customer;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 * Entity representing DiaChi (Address).
 * Primary key "maDiaChi" is BIGSERIAL.
 * Many-to-One relationship with KhachHang (Customer, VARCHAR(50) PK).
 */
@Entity
@Table(name = "\"DiaChi\"")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"maDiaChi\"")
    private Long maDiaChi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"maKhachHang\"", nullable = false)
    private Customer khachHang;

    @Column(name = "\"tenNguoiNhan\"", nullable = false, length = 100)
    private String tenNguoiNhan;

    @Column(name = "\"soDienThoai\"", nullable = false, length = 20)
    private String soDienThoai;

    @Column(name = "\"soNha\"", nullable = false, length = 255)
    private String soNha;

    @Column(name = "\"phuong\"", length = 100)
    private String phuong;

    @Column(name = "\"tinh\"", nullable = false, length = 100)
    private String tinh;

    @Column(name = "\"laMacDinh\"", nullable = false)
    private Boolean laMacDinh = false;

    @Column(name = "\"Deleted\"", nullable = false)
    private Boolean deleted = false;

    // Constructors
    public Address() {
        this.laMacDinh = false;
        this.deleted = false;
    }

    public Address(Customer khachHang, String tenNguoiNhan, String soDienThoai,
                   String soNha, String phuong, String tinh, Boolean laMacDinh) {
        this.khachHang = khachHang;
        this.tenNguoiNhan = tenNguoiNhan;
        this.soDienThoai = soDienThoai;
        this.soNha = soNha;
        this.phuong = phuong;
        this.tinh = tinh;
        this.laMacDinh = laMacDinh != null ? laMacDinh : false;
        this.deleted = false;
    }

    // Getters and Setters
    public Long getMaDiaChi() {
        return maDiaChi;
    }

    public void setMaDiaChi(Long maDiaChi) {
        this.maDiaChi = maDiaChi;
    }

    public Customer getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(Customer khachHang) {
        this.khachHang = khachHang;
    }

    public String getTenNguoiNhan() {
        return tenNguoiNhan;
    }

    public void setTenNguoiNhan(String tenNguoiNhan) {
        this.tenNguoiNhan = tenNguoiNhan;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getSoNha() {
        return soNha;
    }

    public void setSoNha(String soNha) {
        this.soNha = soNha;
    }

    public String getPhuong() {
        return phuong;
    }

    public void setPhuong(String phuong) {
        this.phuong = phuong;
    }

    public String getTinh() {
        return tinh;
    }

    public void setTinh(String tinh) {
        this.tinh = tinh;
    }

    public Boolean getLaMacDinh() {
        return laMacDinh;
    }

    public void setLaMacDinh(Boolean laMacDinh) {
        this.laMacDinh = laMacDinh;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Address{" +
                "maDiaChi=" + maDiaChi +
                ", tenNguoiNhan='" + tenNguoiNhan + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", soNha='" + soNha + '\'' +
                ", phuong='" + phuong + '\'' +
                ", tinh='" + tinh + '\'' +
                ", laMacDinh=" + laMacDinh +
                ", deleted=" + deleted +
                '}';
    }
}
