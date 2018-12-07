package zw.co.gsm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.gsm.domain.Course;
import zw.co.gsm.domain.Degree;
import zw.co.gsm.domain.Level;
import zw.co.gsm.domain.User;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<String> findAllByDegree(Degree degree);

    Course findAllByCourseCode(String courseCode);

}
