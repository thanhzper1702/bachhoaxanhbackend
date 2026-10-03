package com.bachhoaxanh.user;

import com.bachhoaxanh.common.BaseRepository;
import java.util.Optional;

/**
 * Data access skeleton interface for User entity with VARCHAR(50) PK.
 */
public interface UserRepository extends BaseRepository<User, String> {
    Optional<User> findBySoDienThoai(String soDienThoai);
    boolean existsBySoDienThoai(String soDienThoai);
}
