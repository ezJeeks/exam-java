package com.example.exam.domain.dao.identity;

import com.example.exam.domain.models.identity.AppRole;
import com.example.exam.domain.models.identity.AppUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IRoleRepository extends CrudRepository<AppRole,Long> {

    @Query("SELECT r FROM AppRole r where r.name = :name")
    AppRole findByName(String name);
}
