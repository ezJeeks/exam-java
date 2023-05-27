package com.example.exam.domain.services;

import com.example.exam.domain.dao.identity.IRoleRepository;
import com.example.exam.domain.dao.identity.IUserRepository;
import com.example.exam.domain.models.identity.AppRole;
import com.example.exam.domain.models.identity.AppUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private IUserRepository _userRepo;
    @Autowired
    private IRoleRepository _roleRepo;
    private final BCryptPasswordEncoder _passEncoder;

    public UserService() {
        _passEncoder = new BCryptPasswordEncoder();
    }
    public boolean registerUser(AppUser user){
        try{
            user.setFirstName(user.getFirstName());
            user.setLastName(user.getLastName());
            user.setEmail(user.getEmail());
            user.setPassword(_passEncoder.encode(user.getPassword()));
            user.setRoles(Collections.singleton(_roleRepo.findByName("USER")));
            user.setConfirmPassword(user.getPassword());
            user.setRoles(Collections.singleton(_roleRepo.findByName("USER")));
            _userRepo.save(user);
            return true;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public AppUser loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = _userRepo.findByName(username);
        if(user == null){
            System.out.printf("Not found:%s\n",username);
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println(user.getUsername());
        return user;
    }

    private boolean emailExists(String email) {
        return _userRepo.findByEmail(email) != null;
    }

    public AppUser findUserById(Long id){

        Optional<AppUser> user = _userRepo.findById(id);
        return user.orElse(new AppUser());
    }
    public Iterable<AppUser> allUsers(){
        return _userRepo.findAll();
    }
    public boolean saveUser(AppUser user){

        if(_userRepo.findByName(user.getUsername())!=null){
            return  false;
        }
        user.setRoles(Collections.singleton(new AppRole(1L,"USER")));
        try{
            _userRepo.save(user);
            return true;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public boolean deleteUser(Long id){
        boolean exists = _userRepo.existsById(id);
        if(exists)
            _userRepo.deleteById(id);

        return !exists;
    }
    public List<AppUser> getUses_GT(Long minId){
        Query query = entityManager.createQuery("SELECT u FROM AppUser u WHERE u.id > :minId");
        return query.getResultList();
    }

}
