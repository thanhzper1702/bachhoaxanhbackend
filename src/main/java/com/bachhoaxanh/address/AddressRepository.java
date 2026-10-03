package com.bachhoaxanh.address;

import com.bachhoaxanh.common.BaseRepository;
import java.util.List;

/**
 * Data access skeleton interface for Address entity.
 */
public interface AddressRepository extends BaseRepository<Address, Long> {
    List<Address> findByKhachHangMaNguoiDungAndDeletedFalse(String maKhachHang);
}
