package zw.co.sms.gsm.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zw.co.sms.gsm.domain.Department;
import zw.co.sms.gsm.repository.DepartmentRepository;
import zw.co.sms.gsm.repository.FacultyRepository;


import java.util.List;

/**
 * Created by zinzombe on Oct
 */
@Controller
@RequestMapping("/admin/department")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;
@Autowired
private FacultyRepository facultyRepository;
    @RequestMapping(value = {"/add", "/add/{id}"}, method = RequestMethod.GET)
    public String add(@RequestParam(required = false) Long id, Model model) {
        final Department department;
        if (id != null) {
            department = departmentRepository.findById(id).get();
        } else {
            department = new Department();
        }
        model.addAttribute("department", department);
        model.addAttribute("title", "Create/ Edit Department");
        model.addAttribute("faculties", facultyRepository.findAll());
        return "admin/department/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("department") @Validated Department department,
                       BindingResult result, SessionStatus status, Model model,
                       final RedirectAttributes redirectAttributes) throws Exception {
        //Check validation errors
        if (result.hasErrors()) {
            model.addAttribute("faculties", facultyRepository.findAll());
            return "admin/department/add";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
        }
        status.setComplete();
        departmentRepository.save(department);
        return "redirect:/admin/department/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Department> departments = departmentRepository.findAll();
        model.addAttribute("departments", departments);
        return "admin/department/list";
    }

    @RequestMapping("/update/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("department", departmentRepository.findById(id).get());
        model.addAttribute("faculties", facultyRepository.findAll());
        return "admin/department/edit";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        Long subjectId = departmentRepository.findById(id).get().getId();
        departmentRepository.deleteById(subjectId);
        return "redirect:/admin/department/list";
    }

}
