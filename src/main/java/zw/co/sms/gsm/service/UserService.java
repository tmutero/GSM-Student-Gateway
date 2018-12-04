package zw.co.sms.gsm.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import zw.co.sms.gsm.domain.User;
import zw.co.sms.gsm.dto.UserRegistrationDto;


public interface UserService extends UserDetailsService, zw.co.sms.gms.service.IService<User> {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
