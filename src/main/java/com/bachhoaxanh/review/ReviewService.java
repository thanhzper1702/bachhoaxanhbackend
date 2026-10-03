package com.bachhoaxanh.review;

import java.util.List;
import java.util.Optional;

/**
 * Business logic service skeleton class for Review operations.
 */
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review submitReview(Review review) {
        return reviewRepository.save(review);
    }

    public Optional<Review> getReviewById(Long maDanhGia) {
        return reviewRepository.findById(maDanhGia);
    }

    public List<Review> getReviewsByProduct(String maSanPham) {
        return reviewRepository.findBySanPhamMaSanPhamAndDeletedFalse(maSanPham);
    }

    public List<Review> getReviewsByCustomer(String maKhachHang) {
        return reviewRepository.findByKhachHangMaNguoiDungAndDeletedFalse(maKhachHang);
    }

    public void deleteReview(Long maDanhGia) {
        reviewRepository.softDeleteById(maDanhGia);
    }
}
