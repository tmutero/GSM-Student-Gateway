package zw.co.gsm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.gsm.domain.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {


}
