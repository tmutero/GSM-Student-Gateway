package zw.co.gsm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.gsm.domain.Degree;


@Repository
public interface DegreeRepository extends JpaRepository<Degree, Long> {


}
