package com.tiba.tiba.Repositories;
import com.tiba.tiba.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserSignUpRepository extends JpaRepository<com.tiba.tiba.Entities.User, Long>, JpaSpecificationExecutor<User> {

    Optional<com.tiba.tiba.Entities.User> findByEmail(String email);

}
