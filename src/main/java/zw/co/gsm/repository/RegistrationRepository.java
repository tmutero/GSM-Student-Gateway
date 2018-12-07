package zw.co.gsm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.gsm.domain.Faculty;
import zw.co.gsm.domain.Registration;
import zw.co.gsm.domain.Student;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    List<Registration> findFirstByStudent(Student student);
}
