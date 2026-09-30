package in.ankush.campusplacementsystem1.controller;

import in.ankush.campusplacementsystem1.model.Company;
import in.ankush.campusplacementsystem1.model.PlacementDrive;
import in.ankush.campusplacementsystem1.repository.CompanyRepository;
import in.ankush.campusplacementsystem1.repository.PlacementDriveRepository;
import org.springframework.web.bind.annotation.*;
import in.ankush.campusplacementsystem1.model.Student;
import in.ankush.campusplacementsystem1.repository.StudentRepository;

import java.util.List;

@RestController
@RequestMapping("/api/drives")
public class PlacementDriveController {

    private final PlacementDriveRepository placementDriveRepository;
    private final CompanyRepository companyRepository;
    private final StudentRepository studentRepository;

    public PlacementDriveController(
            PlacementDriveRepository placementDriveRepository,
            CompanyRepository companyRepository,
            StudentRepository studentRepository) {
        this.placementDriveRepository = placementDriveRepository;
        this.companyRepository = companyRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<PlacementDrive> getAllDrives() {
        return placementDriveRepository.findAll();
    }

    @GetMapping("/{driveId}/eligibility/{studentId}")
    public String checkEligibility(
            @PathVariable Long driveId,
            @PathVariable Long studentId) {

        PlacementDrive drive = placementDriveRepository.findById(driveId)
                .orElseThrow(() -> new RuntimeException("Placement drive not found"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        boolean cgpaEligible =
                student.getCgpa() >= drive.getMinimumCgpa();

        boolean backlogEligible =
                student.getActiveBacklogs() <= drive.getMaximumBacklogs();

        if (cgpaEligible && backlogEligible) {
            return "Eligible for this placement drive";
        }

        return "Not eligible for this placement drive";
    }

    @PostMapping("/company/{companyId}")
    public PlacementDrive createDrive(
            @PathVariable Long companyId,
            @RequestBody PlacementDrive drive) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        drive.setCompany(company);

        return placementDriveRepository.save(drive);
    }
}