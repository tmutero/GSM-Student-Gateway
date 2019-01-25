package zw.co.gsm.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import zw.co.gsm.domain.Faculty;
import zw.co.gsm.domain.Registration;
import zw.co.gsm.repository.RegistrationRepository;
import zw.co.gsm.repository.StudentAccountRepository;

import java.util.List;

@Controller
@RequestMapping("/admin/report")
public class ReportController {

    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private StudentController studentController;
    @Autowired
    private StudentAccountRepository studentAccountRepository;




    @RequestMapping(value = "/activeReg", method = RequestMethod.GET)
    public String activeReg(Model model) {
        List<Registration> registrations = registrationRepository.findAllByActive(true);
        model.addAttribute("registrations", registrations);
        return "admin/report/activeReg";
    }

    @RequestMapping(value = "/notActive", method = RequestMethod.GET)
    public  String notActive(Model model){
        List<Registration> registrations = registrationRepository.findAllByActive(false);
        model.addAttribute("registrations", registrations);
        return "admin/report/notActive";
    }
}
