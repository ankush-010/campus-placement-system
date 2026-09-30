package in.ankush.campusplacementsystem1.controller;

import in.ankush.campusplacementsystem1.model.Application;
import in.ankush.campusplacementsystem1.model.PlacementDrive;
import in.ankush.campusplacementsystem1.model.Student;
import in.ankush.campusplacementsystem1.repository.ApplicationRepository;
import in.ankush.campusplacementsystem1.repository.PlacementDriveRepository;
import in.ankush.campusplacementsystem1.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
    private final ApplicationRepository applicationRepository;
    private final StudentRepository studentRepository;
    private final PlacementDriveRepository placementDriveRepository;

    public ApplicationController(
            ApplicationRepository applicationRepository,
            StudentRepository studentRepository,
            PlacementDriveRepository placementDriveRepository) {
        this.applicationRepository = applicationRepository;
        this.studentRepository = studentRepository;
        this.placementDriveRepository = placementDriveRepository;
    }

    @GetMapping
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    @PutMapping("/{applicationId}/status")
    public Application updateStatus(
            @PathVariable Long applicationId,
            @RequestParam String status) {

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        application.setStatus(status.toUpperCase());

        return applicationRepository.save(application);
    }

    @PostMapping("/student/{studentId}/drive/{driveId}")
    public Application applyForDrive(
            @PathVariable Long studentId,
            @PathVariable Long driveId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        PlacementDrive drive = placementDriveRepository.findById(driveId)
                .orElseThrow(() -> new RuntimeException("Placement drive not found"));

        boolean alreadyApplied =
                applicationRepository.existsByStudentIdAndPlacementDriveId(
                        studentId, driveId);

        if(alreadyApplied) {
            throw new RuntimeException(
                    "Student has already applied for this placement drive");
        }

        Application application = new Application();

        application.setStudent(student);
        application.setPlacementDrive(drive);
        application.setStatus("APPLIED");

        return  applicationRepository.save(application);
    }
}
