package in.ankush.campusplacementsystem1.controller;

import in.ankush.campusplacementsystem1.dto.DashboardResponse;
import in.ankush.campusplacementsystem1.repository.ApplicationRepository;
import in.ankush.campusplacementsystem1.repository.CompanyRepository;
import in.ankush.campusplacementsystem1.repository.PlacementDriveRepository;
import in.ankush.campusplacementsystem1.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final PlacementDriveRepository placementDriveRepository;
    private final ApplicationRepository applicationRepository;

    public DashboardController(
            StudentRepository studentRepository,
            CompanyRepository companyRepository,
            PlacementDriveRepository placementDriveRepository,
            ApplicationRepository applicationRepository) {

        this.studentRepository = studentRepository;
        this.companyRepository = companyRepository;
        this.placementDriveRepository = placementDriveRepository;
        this.applicationRepository = applicationRepository;
    }

    @GetMapping
    public DashboardResponse getDashboard() {

        long totalApplications = applicationRepository.count();

        long selectedApplications = applicationRepository.countByStatus("SELECTED");

        double selectionRate = 0.0;

        if (totalApplications > 0) {
            selectionRate = (selectedApplications * 100.0) / totalApplications;
        }

        return new DashboardResponse(
                studentRepository.count(),
                companyRepository.count(),
                placementDriveRepository.count(),
                applicationRepository.count(),

                applicationRepository.countByStatus("APPLIED"),
                applicationRepository.countByStatus("SHORTLISTED"),
                selectedApplications,
                applicationRepository.countByStatus("REJECTED"),

                selectionRate
        );
    }
}