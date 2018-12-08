package zw.co.gsm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import zw.co.gsm.domain.*;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    List<Registration> findFirstByStudent(Student student);

    List<Registration> findAllByStudentAndCourse(Student student, Course course);

    @Query("SELECT p FROM  Registration p WHERE p.active=false")
    List<Registration> findAllByActive();



}
