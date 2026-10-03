package com.bachhoaxanh.product;

import com.bachhoaxanh.common.BaseRepository;
import java.util.List;

/**
 * Data access skeleton interface for Product entity with VARCHAR(50) PK.
 */
public interface ProductRepository extends BaseRepository<Product, String> {
    List<Product> findByLoaiSanPhamMaLoaiSanPhamAndDeletedFalse(String maLoai);
    List<Product> searchByName(String keyword);
}
