package com.bachhoaxanh.customer;

import com.bachhoaxanh.common.BaseRepository;
import java.util.Optional;

/**
 * Data access skeleton interface for Customer entity with VARCHAR(50) PK.
 */
public interface CustomerRepository extends BaseRepository<Customer, String> {
    Optional<Customer> findBySoDienThoai(String soDienThoai);
}
