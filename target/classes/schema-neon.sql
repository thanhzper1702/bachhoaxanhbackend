-- =====================================================================
-- DATABASE: BACH HOA XANH ONLINE (PostgreSQL DDL)
-- =====================================================================

-- Xóa bảng theo thứ tự ràng buộc phụ thuộc (nếu đã tồn tại)
DROP TABLE IF EXISTS ThanhToan CASCADE;
DROP TABLE IF EXISTS DanhGia CASCADE;
DROP TABLE IF EXISTS LineItem CASCADE;
DROP TABLE IF EXISTS DonHang CASCADE;
DROP TABLE IF EXISTS GioHang CASCADE;
DROP TABLE IF EXISTS SanPham CASCADE;
DROP TABLE IF EXISTS LoaiSanPham CASCADE;
DROP TABLE IF EXISTS NhaCungCap CASCADE;
DROP TABLE IF EXISTS KhuyenMai CASCADE;
DROP TABLE IF EXISTS DiaChi CASCADE;
DROP TABLE IF EXISTS KhachHang CASCADE;
DROP TABLE IF EXISTS NhanVien CASCADE;
DROP TABLE IF EXISTS NguoiDung CASCADE;

-- Xóa các kiểu ENUM tùy chỉnh nếu đã tồn tại
DROP TYPE IF EXISTS enum_donhang_trangthai CASCADE;
DROP TYPE IF EXISTS enum_thanhtoan_trangthai CASCADE;
DROP TYPE IF EXISTS enum_donvitinh CASCADE;
DROP TYPE IF EXISTS enum_loai_khuyenmai CASCADE;

-- ---------------------------------------------------------------------
-- TẠO CÁC KIỂU ENUM DỮ LIỆU
-- ---------------------------------------------------------------------
CREATE TYPE enum_donhang_trangthai AS ENUM ('CHUATHANHTOAN', 'DATHANHTOAN');
CREATE TYPE enum_thanhtoan_trangthai AS ENUM ('THANHCONG', 'DANGXULY', 'THATBAI');
CREATE TYPE enum_loai_khuyenmai AS ENUM ('THEO_PHANTRAM', 'THEO_TIENCODINH');
CREATE TYPE enum_donvitinh AS ENUM (
    'LON', 'CHAI', 'LOC4', 'LOC6', 'THUNG24', 
    'THUNG30', 'THUNG48', 'GOI', 'CAI', 'BAO', 'KG'
);

-- ---------------------------------------------------------------------
-- 1. BẢNG NGUOIDUNG (Bảng cha kế thừa)
-- ---------------------------------------------------------------------
CREATE TABLE NguoiDung (
    maNguoiDung VARCHAR(50) PRIMARY KEY,
    tenND VARCHAR(150) NOT NULL,
    ngaySinh DATE NULL,
    soDienThoai VARCHAR(20) NOT NULL UNIQUE,
    matKhauHashed VARCHAR(255) NOT NULL,
    Deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- ---------------------------------------------------------------------
-- 2. BẢNG NHANVIEN (Kế thừa NguoiDung)
-- ---------------------------------------------------------------------
CREATE TABLE NhanVien (
    maNhanVien VARCHAR(50) PRIMARY KEY,
    ngayVaoLam DATE NOT NULL DEFAULT CURRENT_DATE,
    Deleted BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_nv_nguoidung FOREIGN KEY (maNhanVien) 
        REFERENCES NguoiDung(maNguoiDung) ON DELETE CASCADE
);

-- ---------------------------------------------------------------------
-- 3. BẢNG KHACHHANG (Kế thừa NguoiDung)
-- ---------------------------------------------------------------------
CREATE TABLE KhachHang (
    maKhachHang VARCHAR(50) PRIMARY KEY,
    Deleted BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_kh_nguoidung FOREIGN KEY (maKhachHang) 
        REFERENCES NguoiDung(maNguoiDung) ON DELETE CASCADE
);

-- ---------------------------------------------------------------------
-- 4. BẢNG DIACHI (Địa chỉ giao hàng của Khách Hàng)
-- ---------------------------------------------------------------------
CREATE TABLE DiaChi (
    maDiaChi BIGSERIAL PRIMARY KEY,
    maKhachHang VARCHAR(50) NOT NULL,
    tenNguoiNhan VARCHAR(100) NOT NULL,
    soDienThoai VARCHAR(20) NOT NULL,
    soNha VARCHAR(255) NOT NULL,
    phuong VARCHAR(100) NULL,
    tinh VARCHAR(100) NOT NULL,
    laMacDinh BOOLEAN NOT NULL DEFAULT FALSE,
    Deleted BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_diachi_khachhang FOREIGN KEY (maKhachHang) 
        REFERENCES KhachHang(maKhachHang) ON DELETE CASCADE
);

CREATE INDEX idx_diachi_khachhang ON DiaChi(maKhachHang) WHERE Deleted = FALSE;

-- ---------------------------------------------------------------------
-- 5. BẢNG NHACUNGCAP
-- ---------------------------------------------------------------------
CREATE TABLE NhaCungCap (
    maNhaCungCap VARCHAR(50) PRIMARY KEY,
    tenNhaCungCap VARCHAR(200) NOT NULL,
    email VARCHAR(150) NULL,
    soDienThoai VARCHAR(20) NULL,
    dangHopTac BOOLEAN NOT NULL DEFAULT TRUE,
    Deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- ---------------------------------------------------------------------
-- 6. BẢNG LOAISANPHAM
-- ---------------------------------------------------------------------
CREATE TABLE LoaiSanPham (
    maLoaiSanPham VARCHAR(50) PRIMARY KEY,
    tenLoaiSanPham VARCHAR(150) NOT NULL,
    phanTramLoiNhuan DOUBLE PRECISION NOT NULL CHECK (phanTramLoiNhuan >= 0),
    Deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- ---------------------------------------------------------------------
-- 7. BẢNG SANPHAM (Đã bổ sung cột hinhAnh dạng URL)
-- ---------------------------------------------------------------------
CREATE TABLE SanPham (
    maSanPham VARCHAR(50) PRIMARY KEY,
    tenSanPham VARCHAR(255) NOT NULL,
    hinhAnh VARCHAR(500) NULL, -- URL ảnh sản phẩm (có thể null nếu chưa cập nhật ảnh)
    hanSuDung DATE NULL,
    maLoai VARCHAR(50) NOT NULL,
    maNhaCungCap VARCHAR(50) NOT NULL,
    giaNhap NUMERIC(12, 2) NOT NULL CHECK (giaNhap >= 0),
    phiVAT DOUBLE PRECISION NOT NULL DEFAULT 0.0 CHECK (phiVAT >= 0),
    giaBan NUMERIC(12, 2) NOT NULL CHECK (giaBan >= 0),
    donViTinh enum_donvitinh NOT NULL,
    soLuong INT NOT NULL DEFAULT 0 CHECK (soLuong >= 0),
    Deleted BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_sp_loaisp FOREIGN KEY (maLoai) 
        REFERENCES LoaiSanPham(maLoaiSanPham),
    CONSTRAINT fk_sp_nhacungcap FOREIGN KEY (maNhaCungCap) 
        REFERENCES NhaCungCap(maNhaCungCap),
    CONSTRAINT chk_sp_giaban CHECK (giaBan >= giaNhap)
);

CREATE INDEX idx_sp_loai ON SanPham(maLoai) WHERE Deleted = FALSE;

-- ---------------------------------------------------------------------
-- 8. BẢNG GIOHANG (1 Khách hàng có 1 Giỏ hàng duy nhất)
-- ---------------------------------------------------------------------
CREATE TABLE GioHang (
    maGioHang VARCHAR(50) PRIMARY KEY,
    maKhachHang VARCHAR(50) NOT NULL UNIQUE,
    tongTien NUMERIC(14, 2) NOT NULL DEFAULT 0.00 CHECK (tongTien >= 0),
    Deleted BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_giohang_khachhang FOREIGN KEY (maKhachHang) 
        REFERENCES KhachHang(maKhachHang) ON DELETE CASCADE
);

-- ---------------------------------------------------------------------
-- 9. BẢNG DONHANG (Chỉ gồm 2 trạng thái: CHUATHANHTOAN, DATHANHTOAN)
-- ---------------------------------------------------------------------
CREATE TABLE DonHang (
    maDonHang VARCHAR(50) PRIMARY KEY,
    maKhachHang VARCHAR(50) NOT NULL,
    ngayLap TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ngayHetHanThanhToan TIMESTAMP NOT NULL,
    ghiChu TEXT NULL,
    trangThai enum_donhang_trangthai NOT NULL DEFAULT 'CHUATHANHTOAN',
    tongTien NUMERIC(14, 2) NOT NULL DEFAULT 0.00 CHECK (tongTien >= 0),
    tongTienSauGiamGia NUMERIC(14, 2) NOT NULL DEFAULT 0.00 CHECK (tongTienSauGiamGia >= 0),
    Deleted BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_donhang_khachhang FOREIGN KEY (maKhachHang) 
        REFERENCES KhachHang(maKhachHang),
    CONSTRAINT chk_donhang_giamgia CHECK (tongTienSauGiamGia <= tongTien),
    CONSTRAINT chk_donhang_thoigian CHECK (ngayHetHanThanhToan >= ngayLap)
);

CREATE INDEX idx_donhang_khachhang ON DonHang(maKhachHang);
CREATE INDEX idx_donhang_trangthai ON DonHang(trangThai) WHERE Deleted = FALSE;

-- ---------------------------------------------------------------------
-- 10. BẢNG LINEITEM (Dùng chung cho Giỏ Hàng hoặc Đơn Hàng)
-- ---------------------------------------------------------------------
CREATE TABLE LineItem (
    lineItemID BIGSERIAL PRIMARY KEY,
    maDonHang VARCHAR(50) NULL,
    maGioHang VARCHAR(50) NULL,
    maSanPham VARCHAR(50) NOT NULL,
    soLuong DOUBLE PRECISION NOT NULL CHECK (soLuong > 0),
    thanhTien NUMERIC(14, 2) NOT NULL DEFAULT 0.00 CHECK (thanhTien >= 0),
    Deleted BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_lineitem_donhang FOREIGN KEY (maDonHang) 
        REFERENCES DonHang(maDonHang) ON DELETE CASCADE,
    CONSTRAINT fk_lineitem_giohang FOREIGN KEY (maGioHang) 
        REFERENCES GioHang(maGioHang) ON DELETE CASCADE,
    CONSTRAINT fk_lineitem_sanpham FOREIGN KEY (maSanPham) 
        REFERENCES SanPham(maSanPham),
    -- Ràng buộc loại trừ: Chỉ thuộc 1 trong 2
    CONSTRAINT chk_lineitem_exclusive CHECK (
        (maDonHang IS NOT NULL AND maGioHang IS NULL) OR 
        (maDonHang IS NULL AND maGioHang IS NOT NULL)
    )
);

-- Một sản phẩm chỉ xuất hiện 1 dòng trong cùng 1 giỏ hàng (Partial Unique Index)
CREATE UNIQUE INDEX uq_lineitem_giohang_sanpham 
ON LineItem(maGioHang, maSanPham) 
WHERE maGioHang IS NOT NULL AND Deleted = FALSE;

-- ---------------------------------------------------------------------
-- 11. BẢNG THANHTOAN (Thanh toán 1 hoặc nhiều lần cho 1 đơn hàng)
-- ---------------------------------------------------------------------
CREATE TABLE ThanhToan (
    maTT VARCHAR(50) PRIMARY KEY,
    maDonHang VARCHAR(50) NOT NULL,
    soTien NUMERIC(14, 2) NOT NULL CHECK (soTien > 0),
    ngayThanhToan TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    trangThai enum_thanhtoan_trangthai NOT NULL DEFAULT 'DANGXULY',
    Deleted BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_thanhtoan_donhang FOREIGN KEY (maDonHang) 
        REFERENCES DonHang(maDonHang) ON DELETE CASCADE
);

CREATE INDEX idx_thanhtoan_donhang ON ThanhToan(maDonHang);

-- ---------------------------------------------------------------------
-- 12. BẢNG DANHGIA (Mỗi khách hàng đánh giá 1 sản phẩm)
-- ---------------------------------------------------------------------
CREATE TABLE DanhGia (
    maDanhGia BIGSERIAL PRIMARY KEY,
    maKhachHang VARCHAR(50) NOT NULL,
    maSanPham VARCHAR(50) NOT NULL,
    soSao INT NOT NULL CHECK (soSao BETWEEN 1 AND 5),
    noiDung TEXT NULL,
    ngayDang TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    Deleted BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_danhgia_khachhang FOREIGN KEY (maKhachHang) 
        REFERENCES KhachHang(maKhachHang) ON DELETE CASCADE,
    CONSTRAINT fk_danhgia_sanpham FOREIGN KEY (maSanPham) 
        REFERENCES SanPham(maSanPham) ON DELETE CASCADE,
    CONSTRAINT uq_khachhang_sanpham_danhgia UNIQUE (maKhachHang, maSanPham)
);

-- ---------------------------------------------------------------------
-- 13. BẢNG KHUYENMAI (Tách biệt độc lập)
-- ---------------------------------------------------------------------
CREATE TABLE KhuyenMai (
    maKhuyenMai VARCHAR(50) PRIMARY KEY,
    tenKhuyenMai VARCHAR(200) NOT NULL,
    loaiGiamGia enum_loai_khuyenmai NOT NULL,
    giaTriGiam NUMERIC(12, 2) NOT NULL CHECK (giaTriGiam > 0),
    ngayBatDau TIMESTAMP NOT NULL,
    ngayKetThuc TIMESTAMP NOT NULL,
    gioiHanLuotDung INT NOT NULL DEFAULT 100 CHECK (gioiHanLuotDung >= 0),
    giaTriDonHangToiThieu NUMERIC(12, 2) NOT NULL DEFAULT 0.00 CHECK (giaTriDonHangToiThieu >= 0),
    trangThai BOOLEAN NOT NULL DEFAULT TRUE,
    moTa TEXT NULL,
    Deleted BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT chk_khuyenmai_ngay CHECK (ngayKetThuc >= ngayBatDau)
);
