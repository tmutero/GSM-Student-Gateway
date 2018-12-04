package zw.co.sms.gsm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zw.co.sms.gsm.domain.Role;


import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {


    Role findRoleByName(String name);


    @Query("select r from Role r where r.id >1")
    List<Role> findAllExpertCutAdmin();
}
