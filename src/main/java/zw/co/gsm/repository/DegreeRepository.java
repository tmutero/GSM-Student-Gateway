package zw.co.gsm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.gsm.domain.Degree;
import zw.co.gsm.domain.Student;


@Repository
public interface DegreeRepository extends JpaRepository<Degree, Long> {
    Degree findAllByDegreeCode(String degreeCode);


}
