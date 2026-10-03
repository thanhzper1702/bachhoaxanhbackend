package com.bachhoaxanh.cart;

import com.bachhoaxanh.common.BaseRepository;
import java.util.Optional;

/**
 * Data access skeleton interface for Cart entity with VARCHAR(50) PK.
 */
public interface CartRepository extends BaseRepository<Cart, String> {
    Optional<Cart> findByKhachHangMaNguoiDung(String maKhachHang);
}
