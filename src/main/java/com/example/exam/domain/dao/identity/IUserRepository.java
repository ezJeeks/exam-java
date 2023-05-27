package com.example.exam.domain.dao.identity;

import com.example.exam.domain.models.identity.AppUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<AppUser,Long> {

    @Query("SELECT u FROM AppUser u where u.username = :username")
    AppUser findByName(String username);

    @Query("SELECT u FROM AppUser u where u.email = :email")
    AppUser findByEmail(String email);
}
