package lk.ijse.backend.repository;


import lk.ijse.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    User findByEmail(String userName);

    boolean existsByEmail(String userName);
    User getUserByEmail(String email);

    int deleteByEmail(String userName);
//    User findAllByUserRole(String userRole);
//    List<User> findAllByUserId(String userId);
}
