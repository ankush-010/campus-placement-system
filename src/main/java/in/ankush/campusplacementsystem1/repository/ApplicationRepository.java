package in.ankush.campusplacementsystem1.repository;

import in.ankush.campusplacementsystem1.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    boolean existsByStudentIdAndPlacementDriveId(
            Long studentId,
            Long driveId
    );

    Long countByStatus(String status);
}
