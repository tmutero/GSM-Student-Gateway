package zw.co.gsm.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zw.co.gsm.domain.Student;
import zw.co.gsm.domain.StudentAccount;
import zw.co.gsm.repository.StudentAccountRepository;
import zw.co.gsm.repository.StudentRepository;


import javax.inject.Inject;
import java.util.Optional;

@Controller
@RequestMapping("/studentAccount")
public class StudentAccountController {


    private final Logger logger = (Logger) LoggerFactory.getLogger(StudentAccountController.class);

    @Inject
    private StudentAccountRepository studentAccountRepository;

    @Inject
    private StudentRepository studentRepository;


    @RequestMapping(value = "/save/{id}", method = RequestMethod.POST)
    public String save(@ModelAttribute("newGrade") @Validated StudentAccount studentAccount, @PathVariable("id") Long id,
                       BindingResult result, Model model) {
        if (result.hasErrors()) {

            model.addAttribute("student", studentAccount.getStudent());
            model.addAttribute("studentAccount", studentAccount);
            return "grades/add";
        }
        studentAccount.setStudent(studentRepository.findById(id).get());
        studentAccountRepository.save(studentAccount);
        return "redirect:admin/student/view/" + studentAccount.getStudent().getId();
    }


    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        Long studentID = studentAccountRepository.findById(id).get().getStudent().getId();
        studentAccountRepository.deleteById(studentID);
        return "redirect:admin/student/view/" + id;
    }

    @RequestMapping(value = "/exam/{courseID}/{studentId}", method = RequestMethod.GET)
    public String exam(@PathVariable("courseID") Long courseID,
                       @PathVariable("studentId") Long studentId,
                       Model model) {


        System.out.println("-----------------------"+studentId);
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        System.out.println("----------------------------student"+studentOptional);


        return "redirect:admin/student/view/" + studentId;
    }



}