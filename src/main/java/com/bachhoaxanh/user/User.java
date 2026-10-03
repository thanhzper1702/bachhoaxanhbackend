package com.bachhoaxanh.user;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Base Entity representing NguoiDung (User) in PostgreSQL schema.
 * Utilizes InheritanceType.JOINED for polymorphic user hierarchy (KhachHang, NhanVien).
 * Primary key "maNguoiDung" is VARCHAR(50) (assigned, not generated).
 */
@Entity
@Table(name = "\"NguoiDung\"")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "\"maNguoiDung\"", length = 50, nullable = false)
    private String maNguoiDung;

    @Column(name = "\"tenND\"", nullable = false, length = 150)
    private String tenND;

    @Column(name = "\"ngaySinh\"")
    private LocalDate ngaySinh;

    @Column(name = "\"soDienThoai\"", unique = true, nullable = false, length = 20)
    private String soDienThoai;

    @Column(name = "\"matKhauHashed\"", nullable = false, length = 255)
    private String matKhauHashed;

    @Column(name = "\"Deleted\"", nullable = false)
    private Boolean deleted = false;

    // Constructors
    public User() {
        this.deleted = false;
    }

    public User(String maNguoiDung, String tenND, LocalDate ngaySinh, String soDienThoai, String matKhauHashed) {
        this.maNguoiDung = maNguoiDung;
        this.tenND = tenND;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.matKhauHashed = matKhauHashed;
        this.deleted = false;
    }

    // Getters and Setters
    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getTenND() {
        return tenND;
    }

    public void setTenND(String tenND) {
        this.tenND = tenND;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getMatKhauHashed() {
        return matKhauHashed;
    }

    public void setMatKhauHashed(String matKhauHashed) {
        this.matKhauHashed = matKhauHashed;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "User{" +
                "maNguoiDung='" + maNguoiDung + '\'' +
                ", tenND='" + tenND + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
