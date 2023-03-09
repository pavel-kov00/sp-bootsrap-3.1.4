package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDaoImpl;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.security.PersonDetails;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final UserDaoImpl userDao;

    @Autowired
    public PersonDetailsService( UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userDao.getUserByName(username);

       if (user == null){
           throw new UsernameNotFoundException("User not found!");
       }
        return new PersonDetails(user);
    }
}
