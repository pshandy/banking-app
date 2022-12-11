package com.rinate.bankingapp.service;

import com.rinate.bankingapp.domain.model.User;
import com.rinate.bankingapp.domain.model.UserRole;
import com.rinate.bankingapp.domain.repository.RoleDao;
import com.rinate.bankingapp.domain.repository.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	private final UserDao userDao;

    private final RoleDao roleDao;

    private final BCryptPasswordEncoder passwordEncoder;

    private final AccountService accountService;

    public UserServiceImpl(
            UserDao userDao,
            RoleDao roleDao,
            BCryptPasswordEncoder passwordEncoder,
            AccountService accountService
    ) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
        this.accountService = accountService;
    }

    public User createUser(User user, Set<UserRole> userRoles) {
        User localUser = userDao.findByUsername(user.getUsername());

        if (localUser != null) {

            LOG.info("User with username {} already exist. Nothing will be done. ", user.getUsername());

        } else {

            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);

            for (UserRole ur : userRoles) {
                roleDao.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);
            user.setPrimaryAccount(accountService.createPrimaryAccount());
            user.setSavingsAccount(accountService.createSavingsAccount());
            localUser = userDao.save(user);

        }

        return localUser;

    }
    
    public boolean checkUserExists(String username, String email){
        return checkUsernameExists(username) || checkEmailExists(username);
    }

    public boolean checkUsernameExists(String username) {
        return null != userDao.findByUsername(username);
    }
    
    public boolean checkEmailExists(String email) {
        return null != userDao.findByEmail(email);
    }

    public void enableUser (String username) {
        User user = userDao.findByUsername(username);
        user.setEnabled(true);
        userDao.save(user);
    }

    public void disableUser (String username) {
        User user = userDao.findByUsername(username);
        user.setEnabled(false);
        System.out.println(user.isEnabled());
        userDao.save(user);
        System.out.println(username + " is disabled.");
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public User saveUser (User user) {
        return userDao.save(user);
    }

}
