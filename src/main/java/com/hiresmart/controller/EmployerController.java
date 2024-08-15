package com.hiresmart.controller;

import com.hiresmart.dao.EmployerDao;
import com.hiresmart.model.User;
import com.hiresmart.repository.UserRepository;
import com.hiresmart.service.ApplicationService;
import com.hiresmart.service.EmployerService;
import com.hiresmart.service.UserService;
import org.springframework.ui.Model;
import com.hiresmart.model.Job;
import com.hiresmart.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/employer")
public class EmployerController {

    @Autowired
    private JobService jobService;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private EmployerDao employerDao;

    @Autowired
    private EmployerService employerService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/home")
    public String viewJobs(Model model, Principal principal){

//        String username = principal.getName();
//        Employer employer = employerService.findByUsername(username);
//
//        if (employer != null) {
//            List<Job> jobs = jobService.getJobsByEmployer(employer);
//            model.addAttribute("jobs", jobs);
//            return "employer-job-list";
//        } else {
//            return "redirect:/error";
//        }

        String username = principal.getName();
        User employer = employerService.findByUsername(username);
        System.out.println(username);
        List<Job> jobs = jobService.getJobsByEmployer(employer);
        System.out.println("jobs posted");
        model.addAttribute("jobs", jobs);
        return "employer-job-list";
    }

    @GetMapping("/postJob")
    public String showPostJobForm(){
        return "jobPosting";
    }

    @PostMapping("/postJob")
    public String postJob(@RequestParam String name, @RequestParam String description, @RequestParam String location, @RequestParam String status, @RequestParam String salary, Principal principal){
        String username = principal.getName();
        User employer = employerService.findByUsername(username);

        Job job = new Job();
        job.setName(name);
        job.setDescription(description);
        job.setLocation(location);
        job.setStatus(status);
        job.setSalary(salary);
        job.setEmployer(employer);

        jobService.saveJob(job);
        return "redirect:/employer/home";

    }

    @GetMapping("/editJob/{jobId}")
    public String showEditJobForm(Model model, @PathVariable Long jobId){
        Job job = jobService.getJobById(jobId);
        model.addAttribute("job", job);
        return "edit-job";
    }

    @PostMapping("/editJob/{jobId}")
    public String editJob (@PathVariable Long jobId, @RequestParam String name, @RequestParam String description, @RequestParam String location, @RequestParam String status, @RequestParam String salary){
        Job job = jobService.getJobById(jobId);
        job.setName(name);
        job.setDescription(description);
        job.setLocation(location);
        job.setStatus(status);
        job.setSalary(salary);
        jobService.saveJob(job);
        return "redirect:/employer/home";
    }

    @PostMapping("/deleteJob/{jobId}")
    public String deleteJob(@PathVariable Long jobId){
        jobService.deleteJob(jobId);
        return "redirect:/employer/home";
    }

    @GetMapping("/applications")
    public String viewApplications(Model model, Principal principal){

        String username = principal.getName();
        System.out.println("Fetching student for username: " + username);
        User employer = employerDao.findByUsername(username);
        List<Job> applications = jobService.getJobsByEmployer(employer);
        System.out.println("applications posted");
        for (Job application : applications) {
            System.out.println(application.getName());
        }
        model.addAttribute("applications", applications);
        return "employer-applications";
    }

    @GetMapping("/applications/{jobId}")
    public String getApplicants(@PathVariable Long jobId, Model model) {
        List<User> applicants = userService.findUsersByJob(jobId);
        model.addAttribute("applicants", applicants);
        return "job-applicants";
    }

}
