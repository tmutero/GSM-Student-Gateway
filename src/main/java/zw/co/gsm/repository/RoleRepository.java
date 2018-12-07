package zw.co.gsm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zw.co.gsm.domain.Role;


import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {


    Role findRoleByName(String name);


}
