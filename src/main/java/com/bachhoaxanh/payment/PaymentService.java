package com.bachhoaxanh.payment;

import java.util.List;
import java.util.Optional;

/**
 * Business logic service skeleton class for Payment operations with VARCHAR(50) PK.
 */
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment processPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Optional<Payment> getPaymentById(String maTT) {
        return paymentRepository.findById(maTT);
    }

    public List<Payment> getPaymentsByOrder(String maDonHang) {
        return paymentRepository.findByDonHangMaDonHangAndDeletedFalse(maDonHang);
    }

    public Payment updatePaymentStatus(String maTT, PaymentStatus status) {
        return null;
    }
}
