package com.bachhoaxanh.order;

/**
 * Order status enum strictly restricted to:
 * CHUATHANHTOAN (Unpaid / Pending), DATHANHTOAN (Paid / Completed)
 */
public enum OrderStatus {
    CHUATHANHTOAN,
    DATHANHTOAN
}
