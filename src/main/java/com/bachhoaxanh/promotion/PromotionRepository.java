package com.bachhoaxanh.promotion;

import com.bachhoaxanh.common.BaseRepository;
import java.util.Optional;

/**
 * Data access skeleton interface for Promotion entity with VARCHAR(50) PK.
 */
public interface PromotionRepository extends BaseRepository<Promotion, String> {
    Optional<Promotion> findByTenKhuyenMaiAndDeletedFalse(String tenKhuyenMai);
}
