package com.bachhoaxanh.lineitem;

import java.util.List;
import java.util.Optional;

/**
 * Business logic service skeleton class for LineItem operations.
 */
public class LineItemService {

    private final LineItemRepository lineItemRepository;

    public LineItemService(LineItemRepository lineItemRepository) {
        this.lineItemRepository = lineItemRepository;
    }

    public LineItem createLineItem(LineItem lineItem) {
        return lineItemRepository.save(lineItem);
    }

    public Optional<LineItem> getLineItemById(Long lineItemId) {
        return lineItemRepository.findById(lineItemId);
    }

    public List<LineItem> getLineItemsByOrderId(String orderId) {
        return lineItemRepository.findByDonHangMaDonHangAndDeletedFalse(orderId);
    }

    public List<LineItem> getLineItemsByCartId(String cartId) {
        return lineItemRepository.findByGioHangMaGioHangAndDeletedFalse(cartId);
    }

    public LineItem updateQuantity(Long lineItemId, Double quantity) {
        return null;
    }

    public void deleteLineItem(Long lineItemId) {
        lineItemRepository.softDeleteById(lineItemId);
    }
}
