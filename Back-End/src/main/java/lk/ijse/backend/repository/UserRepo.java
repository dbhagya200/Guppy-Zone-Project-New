package lk.ijse.backend.repository;


import lk.ijse.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    User findByEmail(String email);
    User findByEmailAndRole(String email, String role);
    boolean existsByEmail(String email);
    User getUserByEmail(String email);

    int deleteByEmail(String userName);

//    User findByEmailAndRole(String email, String role);

//    User findAllByUserRole(String userRole);
//    List<User> findAllByUserId(String userId);
}
