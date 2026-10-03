package com.bachhoaxanh.supplier;

import java.util.List;
import java.util.Optional;

/**
 * Business logic service skeleton class for Supplier operations with VARCHAR(50) PK.
 */
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Optional<Supplier> getSupplierById(String maNhaCungCap) {
        return supplierRepository.findById(maNhaCungCap);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAllActive();
    }

    public Supplier updateSupplier(String maNhaCungCap, Supplier supplier) {
        return null;
    }

    public void deleteSupplier(String maNhaCungCap) {
        supplierRepository.softDeleteById(maNhaCungCap);
    }
}
