package zw.co.gsm.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zw.co.gsm.domain.*;
import zw.co.gsm.dto.UserRegistrationDto;
import zw.co.gsm.repository.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private DegreeRepository degreeRepository;
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private StudentAccountRepository studentAccountRepository;
    @Autowired
    private CourseRepository courseRepository;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "admin/student/list";
    }

    @RequestMapping(value = {"/add", "/add/{id}"}, method = RequestMethod.GET)
    public String add(@RequestParam(required = false) Long id, Model model) {
        Student student=new Student();
        model.addAttribute("student", student);
        model.addAttribute("title", "Create/ Edit User");
        model.addAttribute("degrees",degreeRepository.findAll());
        model.addAttribute("levels", Level.values());
        model.addAttribute("gender", Gender.values());


        return "admin/student/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("student") @Validated Student student,
                       BindingResult result, SessionStatus status,
                       final RedirectAttributes redirectAttributes) throws Exception {
        //Check validation errors
        if (result.hasErrors()) {
            return "admin/student/add";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
        }
        status.setComplete();
        studentRepository.save(student);
        StudentAccount studentAccount =new StudentAccount();
        studentAccount.setStudent(student);
        studentAccount.setAmount(600.0);
        studentAccountRepository.save(studentAccount);
        return "redirect:/admin/student/list";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, Model model) {

        Optional<Student> studentOptional = studentRepository.findById(id);

        if (!studentOptional.isPresent()) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Student not found");
        } else {

            final Student student = studentOptional.get();
            model.addAttribute("title", "Student Detail");
            model.addAttribute("student",student);
            model.addAttribute("studentAccount", new StudentAccount());


            model.addAttribute("paymentType", PaymentType.values());

            List<Registration> registrations=registrationRepository.findFirstByStudentAndActive(student,true);

            model.addAttribute("registration",registrations);
            model.addAttribute("account",studentAccountRepository.findByStudent(student));
        }

        return "admin/student/view";
    }

}
