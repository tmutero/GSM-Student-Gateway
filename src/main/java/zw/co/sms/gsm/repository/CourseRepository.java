package zw.co.sms.gsm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.sms.gsm.domain.Course;
import zw.co.sms.gsm.domain.User;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {


    Course findByName(String name);

    List<Course> findAllByUser(User user);
}
