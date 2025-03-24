package lk.ijse.backend.repository;

import lk.ijse.backend.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, String> {
    Profile findByUserEmail(String email);
}
