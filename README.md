# Bách Hóa Xanh - Backend (Java 10 & Jakarta Persistence)

Clean Java 10 Backend refactored from monolith, utilizing **Jakarta Persistence (JPA 3.1)** and integrated with **Neon.tech Serverless PostgreSQL** via SSL.

## Technical Specifications
- **Java Platform**: Java 10 (`<maven.compiler.source>10</maven.compiler.source>`, `<maven.compiler.target>10</maven.compiler.target>`)
- **Persistence Standard**: Jakarta Persistence API (`jakarta.persistence.*`)
- **Database Engine**: PostgreSQL on Neon.tech (requires SSL: `sslmode=require`)
- **Architecture**: Package-by-Feature (modular layout containing Controller, Service, Repository, Model)
- **Connection Pool**: HikariCP configured for serverless PostgreSQL

## Modular Package Layout
```text
backend/
├── pom.xml
└── src/main/
    ├── resources/
    │   ├── application.properties
    │   ├── db.properties
    │   ├── schema-neon.sql
    │   └── META-INF/
    │       └── persistence.xml
    └── java/com/bachhoaxanh/
        ├── common/
        │   └── BaseRepository.java
        ├── user/
        │   ├── User.java                   # Base entity (NguoiDung) with JOINED inheritance
        │   ├── UserController.java         # REST endpoints (@Path("/users"))
        │   ├── UserRepository.java
        │   └── UserService.java
        ├── customer/
        │   ├── Customer.java               # KhachHang extends User (maKhachHang PK/FK)
        │   ├── CustomerController.java
        │   ├── CustomerRepository.java
        │   └── CustomerService.java
        ├── staff/
        │   ├── Staff.java                  # NhanVien extends User (maNhanVien PK/FK)
        │   ├── StaffController.java
        │   ├── StaffRepository.java
        │   └── StaffService.java
        ├── address/
        │   ├── Address.java                # DiaChi (Many-to-One KhachHang)
        │   ├── AddressController.java
        │   ├── AddressRepository.java
        │   └── AddressService.java
        ├── cart/
        │   ├── Cart.java                   # GioHang (One-to-One unique KhachHang)
        │   ├── CartController.java
        │   ├── CartRepository.java
        │   └── CartService.java
        ├── category/
        │   ├── Category.java               # LoaiSanPham
        │   ├── CategoryController.java
        │   ├── CategoryRepository.java
        │   └── CategoryService.java
        ├── supplier/
        │   ├── Supplier.java               # NhaCungCap
        │   ├── SupplierController.java
        │   ├── SupplierRepository.java
        │   └── SupplierService.java
        ├── product/
        │   ├── Product.java                # SanPham (FKs to LoaiSanPham & NhaCungCap)
        │   ├── Unit.java                   # Enum: LON, CHAI, LOC4, LOC6, THUNG24, THUNG30, THUNG48, GOI, CAI, BAO, KG
        │   ├── ProductController.java
        │   ├── ProductRepository.java
        │   └── ProductService.java
        ├── order/
        │   ├── Order.java                  # DonHang (Belongs to KhachHang)
        │   ├── OrderStatus.java            # Enum: CHUATHANHTOAN, DATHANHTOAN
        │   ├── OrderController.java
        │   ├── OrderRepository.java
        │   └── OrderService.java
        ├── payment/
        │   ├── Payment.java                # ThanhToan (Belongs to DonHang)
        │   ├── PaymentStatus.java          # Enum: THANHCONG, DANGXULY, THATBAI
        │   ├── PaymentController.java
        │   ├── PaymentRepository.java
        │   └── PaymentService.java
        ├── lineitem/
        │   ├── LineItem.java               # Shared between Cart and Order (exclusive constraint)
        │   ├── LineItemController.java
        │   ├── LineItemRepository.java
        │   └── LineItemService.java
        ├── review/
        │   ├── Review.java                 # DanhGia (Links KhachHang & SanPham, 1-5 stars)
        │   ├── ReviewController.java
        │   ├── ReviewRepository.java
        │   └── ReviewService.java
        └── promotion/
            ├── Promotion.java              # KhuyenMai standalone entity
            ├── PromotionController.java
            ├── PromotionRepository.java
            └── PromotionService.java
```

## Database Schema (Neon PostgreSQL)
The complete DDL is located in [`backend/src/main/resources/schema-neon.sql`](src/main/resources/schema-neon.sql).
To initialize your Neon PostgreSQL database:
```bash
psql "postgres://[user]:[password]@[neon-endpoint]/[database]?sslmode=require" -f src/main/resources/schema-neon.sql
```

## Building the Project
From the repository root or `backend/` directory:
```bash
mvn clean compile
```
