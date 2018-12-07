package zw.co.gsm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import zw.co.gsm.domain.Student;
import zw.co.gsm.domain.StudentAccount;

import java.util.List;

@Repository
public interface StudentAccountRepository extends JpaRepository<StudentAccount, Long> {
    List<StudentAccount> findByStudent(Student student);

    @Query("SELECT p FROM  StudentAccount p WHERE p.student =:student ORDER BY p.dateCreated DESC  ")
    List<StudentAccount> findStudentAccountByDateCreated(@Param("student") Student student);


}
