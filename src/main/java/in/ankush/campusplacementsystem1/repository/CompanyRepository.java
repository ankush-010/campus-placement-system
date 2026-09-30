package in.ankush.campusplacementsystem1.repository;

import in.ankush.campusplacementsystem1.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}