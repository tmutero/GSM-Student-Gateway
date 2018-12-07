package zw.co.gsm.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import zw.co.gsm.domain.User;
import zw.co.gsm.dto.UserRegistrationDto;
import zw.co.gsm.service.IService;


public interface UserService extends UserDetailsService, IService<User> {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
