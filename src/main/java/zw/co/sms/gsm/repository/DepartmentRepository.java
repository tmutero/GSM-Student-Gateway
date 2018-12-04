package zw.co.sms.gsm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.sms.gsm.domain.Department;
import zw.co.sms.gsm.domain.Faculty;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {


}
