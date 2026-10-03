package com.bachhoaxanh.address;

import java.util.List;
import java.util.Optional;

/**
 * Business logic service skeleton class for Address operations.
 */
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    public Optional<Address> getAddressById(Long maDiaChi) {
        return addressRepository.findById(maDiaChi);
    }

    public List<Address> getAddressesByCustomer(String maKhachHang) {
        return addressRepository.findByKhachHangMaNguoiDungAndDeletedFalse(maKhachHang);
    }

    public Address updateAddress(Long maDiaChi, Address updatedAddress) {
        return null;
    }

    public void deleteAddress(Long maDiaChi) {
        addressRepository.softDeleteById(maDiaChi);
    }
}
