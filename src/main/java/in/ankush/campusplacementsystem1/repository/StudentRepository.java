package in.ankush.campusplacementsystem1.repository;

import in.ankush.campusplacementsystem1.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByBranch(String branch);

    List<Student> findByCgpaGreaterThanEqual(Double cgpa);

    List<Student> findByBranchAndCgpaGreaterThanEqual(
            String branch,
            Double cgpa
    );
}