package com.hiresmart.controller;

import com.hiresmart.constants.Roles;
import com.hiresmart.dao.StudentDao;
import com.hiresmart.daoImpl.StudentDaoImpl;
import com.hiresmart.model.Application;
import com.hiresmart.model.Job;
import com.hiresmart.model.Student;
import com.hiresmart.model.User;
import com.hiresmart.repository.StudentRepository;
import com.hiresmart.repository.UserRepository;
import com.hiresmart.service.ApplicationService;
import com.hiresmart.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private JobService jobService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private View error;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentDaoImpl studentDaoImpl;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/home")
    public String showHomePage(Model model){
        List<Job> jobs = jobService.getAllJobs();
        model.addAttribute("jobs", jobs);
        return "studentHome";
    }

//    @PostMapping("/apply/{jobId}")
//    public String applyForJob(@PathVariable Long jobId, Principal principal) {
//        String username = principal.getName();
//        System.out.println("Fetching student for username: " + username);
//        Student student = (Student) userRepository.findByUsername(username);
//        Job job = jobService.getJobById(jobId);
//        System.out.println("jobId: " + jobId);
//        System.out.println("Username: " + username);
//        System.out.println("Student: " + student);
//        System.out.println("Job: " + job);
//
//        if (student != null && job != null) {
//            System.out.println("studentId: " + student.getId());
//            Application application = new Application();
//            application.setStudent(student);
//            application.setJob(job);
//            application.setStatus("Applied");
//            applicationService.saveApplication(application);
//            System.out.println("Application saved: " + application);
//        } else {
//            System.out.println("Student or job not found");
//        }
@PostMapping("/apply/{jobId}")
public String applyForJob(@PathVariable Long jobId, Principal principal) {
    String username = principal.getName();
    System.out.println("Fetching student for username: " + username);

    // Fetch the user by username
    User student = userRepository.findByUsername(username);

    System.out.println(student.getClass());

    Job job = jobService.getJobById(jobId);

    System.out.println("jobId: " + jobId);
    System.out.println("Username: " + username);
    System.out.println("Student: " + student);
    System.out.println("Job: " + job);

    if (job != null) {
        System.out.println("studentId: " + student.getId());
        Application application = new Application();
        System.out.println("applicationId: " + application.getId());
        application.setStudent(student);
        System.out.println("set student: " + student);
        application.setJob(job);
        application.setDescription(job.getDescription());
        application.setName(job.getName());
        System.out.println("set job: " + job);
        application.setStatus("Applied");
        System.out.println("set status: " + application.getStatus());
        applicationService.saveApplication(application);
        System.out.println("Application saved: " + application);
    } else {
            System.out.println("Job not found");
        }
    return "redirect:/student/home"; // Or any other appropriate response
}


    @GetMapping("/applications")
    public String viewApplications(Model model, Principal principal){
        String username = principal.getName();
        System.out.println("Fetching student for username: " + username);
        User student = studentDao.findByUsername(username);
        List<Application> applications = applicationService.getApplicationsByStudent(student);
        model.addAttribute("applications", applications);
        return "student-applications";
    }
}
