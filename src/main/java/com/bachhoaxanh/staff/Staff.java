package com.bachhoaxanh.staff;

import com.bachhoaxanh.user.User;
import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entity representing NhanVien (Staff), extending NguoiDung (User) via JOINED inheritance.
 * Primary key "maNhanVien" is VARCHAR(50), serving as PK and FK referencing "maNguoiDung".
 */
@Entity
@Table(name = "\"NhanVien\"")
@PrimaryKeyJoinColumn(name = "\"maNhanVien\"", referencedColumnName = "\"maNguoiDung\"")
public class Staff extends User {

    private static final long serialVersionUID = 1L;

    @Column(name = "\"ngayVaoLam\"", nullable = false)
    private LocalDate ngayVaoLam = LocalDate.now();

    @Column(name = "\"Deleted\"", nullable = false)
    private Boolean deleted = false;

    // Constructors
    public Staff() {
        super();
        this.ngayVaoLam = LocalDate.now();
        this.deleted = false;
    }

    public Staff(String maNhanVien, String tenND, LocalDate ngaySinh, String soDienThoai,
                 String matKhauHashed, LocalDate ngayVaoLam) {
        super(maNhanVien, tenND, ngaySinh, soDienThoai, matKhauHashed);
        this.ngayVaoLam = ngayVaoLam != null ? ngayVaoLam : LocalDate.now();
        this.deleted = false;
    }

    // Getters and Setters
    public LocalDate getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(LocalDate ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    @Override
    public Boolean getDeleted() {
        return deleted;
    }

    @Override
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
