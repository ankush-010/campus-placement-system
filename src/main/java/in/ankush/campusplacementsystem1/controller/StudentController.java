package in.ankush.campusplacementsystem1.controller;

import in.ankush.campusplacementsystem1.model.Student;
import in.ankush.campusplacementsystem1.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/search")
    public List<Student> searchStudents(
            @RequestParam String branch,
            @RequestParam Double cgpa) {

        return studentRepository.findByBranchAndCgpaGreaterThanEqual(
                branch,
                cgpa
        );
    }

    @GetMapping("/{id:\\d+}")
    public Student getStudentId(@PathVariable Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @GetMapping("/branch/{branch}")
    public List<Student> getStudentsByBranch(@PathVariable String branch) {
        return studentRepository.findByBranch(branch);
    }

    @GetMapping("/cgpa/{cgpa}")
    public List<Student> getStudentsByCgpa(@PathVariable Double cgpa) {
        return studentRepository.findByCgpaGreaterThanEqual(cgpa);
    }


    @PostMapping
    public Student addStudent(@Valid @RequestBody Student student) {
        return studentRepository.save(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @Valid @RequestBody Student updatedStudent) {

        return studentRepository.findById(id)
                .map(student -> {
                    student.setName(updatedStudent.getName());
                    student.setEmail(updatedStudent.getEmail());
                    student.setPhone(updatedStudent.getPhone());
                    student.setBranch(updatedStudent.getBranch());
                    student.setCgpa(updatedStudent.getCgpa());
                    student.setGraduationYear(updatedStudent.getGraduationYear());
                    student.setActiveBacklogs(updatedStudent.getActiveBacklogs());

                    return studentRepository.save(student);
                })
                .orElseThrow(() -> new RuntimeException("Student not found"));

    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id){
        if (studentRepository.existsById(id)){
            studentRepository.deleteById(id);

            return "Student deleted successfully";
        }

        return "Student not found";
    }
}