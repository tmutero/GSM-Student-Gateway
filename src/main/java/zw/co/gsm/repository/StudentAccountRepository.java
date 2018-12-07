package zw.co.gsm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.gsm.domain.Student;
import zw.co.gsm.domain.StudentAccount;

import java.util.List;

@Repository
public interface StudentAccountRepository extends JpaRepository<StudentAccount, Long> {
    List<StudentAccount> findByStudent(Student student);
}
