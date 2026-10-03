package com.bachhoaxanh.lineitem;

import com.bachhoaxanh.common.BaseRepository;
import java.util.List;

/**
 * Data access skeleton interface for LineItem entity.
 */
public interface LineItemRepository extends BaseRepository<LineItem, Long> {
    List<LineItem> findByDonHangMaDonHangAndDeletedFalse(String maDonHang);
    List<LineItem> findByGioHangMaGioHangAndDeletedFalse(String maGioHang);
}
