package com.bachhoaxanh.staff;

import java.util.List;
import java.util.Optional;

/**
 * Business logic service skeleton class for Staff operations with VARCHAR(50) PK.
 */
public class StaffService {

    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public Staff createStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public Optional<Staff> getStaffById(String maNhanVien) {
        return staffRepository.findById(maNhanVien);
    }

    public List<Staff> getAllActiveStaff() {
        return staffRepository.findAllActive();
    }

    public Staff updateStaff(String maNhanVien, Staff staff) {
        return null;
    }

    public void deactivateStaff(String maNhanVien) {
        staffRepository.softDeleteById(maNhanVien);
    }
}
