package com.bachhoaxanh.review;

import com.bachhoaxanh.common.BaseRepository;
import java.util.List;

/**
 * Data access skeleton interface for Review entity.
 */
public interface ReviewRepository extends BaseRepository<Review, Long> {
    List<Review> findBySanPhamMaSanPhamAndDeletedFalse(String maSanPham);
    List<Review> findByKhachHangMaNguoiDungAndDeletedFalse(String maKhachHang);
}
