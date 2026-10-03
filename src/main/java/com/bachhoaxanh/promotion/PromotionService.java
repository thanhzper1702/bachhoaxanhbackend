package com.bachhoaxanh.promotion;

import java.util.List;
import java.util.Optional;

/**
 * Business logic service skeleton class for Promotion operations with VARCHAR(50) PK.
 */
public class PromotionService {

    private final PromotionRepository promotionRepository;

    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public Promotion createPromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    public Optional<Promotion> getPromotionById(String maKhuyenMai) {
        return promotionRepository.findById(maKhuyenMai);
    }

    public Optional<Promotion> getPromotionByName(String name) {
        return promotionRepository.findByTenKhuyenMaiAndDeletedFalse(name);
    }

    public List<Promotion> getAllActivePromotions() {
        return promotionRepository.findAllActive();
    }

    public void deletePromotion(String maKhuyenMai) {
        promotionRepository.softDeleteById(maKhuyenMai);
    }
}
