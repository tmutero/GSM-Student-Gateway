package zw.co.sms.gsm.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zw.co.sms.gsm.domain.Course;
import zw.co.sms.gsm.repository.CourseRepository;
import zw.co.sms.gsm.repository.DepartmentRepository;
import zw.co.sms.gsm.repository.UserRepository;


import java.util.List;

/**
 * Created by zinzombe on Oct
 */
@Controller
@RequestMapping("/admin/course")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = {"/add", "/add/{id}"}, method = RequestMethod.GET)
    public String add(@RequestParam(required = false) Long id, Model model) {
        final Course course;
        if (id != null) {
            course = courseRepository.findById(id).get();
        } else {
            course = new Course();
        }
        model.addAttribute("course", course);
        model.addAttribute("title", "Create/ Edit Course");
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "admin/course/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("course") @Validated Course course,
                       BindingResult result, SessionStatus status,
                       final RedirectAttributes redirectAttributes) throws Exception {
        //Check validation errors
        if (result.hasErrors()) {
            return "admin/course/add";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
        }
        status.setComplete();
        courseRepository.save(course);
        return "redirect:/admin/course/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "admin/course/list";
    }

    @RequestMapping("/update/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseRepository.findById(id).get());
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "admin/course/edit";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        Long subjectId = courseRepository.findById(id).get().getId();
        courseRepository.deleteById(subjectId);
        return "redirect:/admin/course/list";
    }

}
