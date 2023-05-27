package com.example.exam.domain.dao.identity;

import com.example.exam.domain.models.identity.AppUser;
import org.springframework.security.core.userdetails.User;

public interface IUserService {
    User registerUser(AppUser user);
}
