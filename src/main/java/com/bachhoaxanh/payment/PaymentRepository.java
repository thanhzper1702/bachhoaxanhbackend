package com.bachhoaxanh.payment;

import com.bachhoaxanh.common.BaseRepository;
import java.util.List;

/**
 * Data access skeleton interface for Payment entity with VARCHAR(50) PK.
 */
public interface PaymentRepository extends BaseRepository<Payment, String> {
    List<Payment> findByDonHangMaDonHangAndDeletedFalse(String maDonHang);
}
