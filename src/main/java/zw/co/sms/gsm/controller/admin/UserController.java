package zw.co.sms.gsm.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zw.co.sms.gsm.domain.Role;
import zw.co.sms.gsm.domain.User;
import zw.co.sms.gsm.dto.UserRegistrationDto;
import zw.co.sms.gsm.repository.*;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zinzombe on Oct
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(value = {"/add", "/add/{id}"}, method = RequestMethod.GET)
    public String add(@RequestParam(required = false) Long id, Model model) {
   UserRegistrationDto user = new UserRegistrationDto();
        model.addAttribute("user", user);
        model.addAttribute("title", "Create/ Edit User");
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("faculties", facultyRepository.findAll());
        model.addAttribute("courses", courseRepository.findAll());
        return "admin/user/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("user") @Validated UserRegistrationDto userDto, Model model,
                       BindingResult result, SessionStatus status,
                       final RedirectAttributes redirectAttributes) throws Exception {
        User user = new User();
//        Student student = new Student();
        User existing = userRepository.findByEmail(userDto.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            model.addAttribute("departments", departmentRepository.findAll());
            model.addAttribute("faculties", facultyRepository.findAll());
            model.addAttribute("courses", courseRepository.findAll());
            return "admin/user/add";
        }
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setFaculty(userDto.getFaculty());
        user.setDepartment(userDto.getDepartment());
        user.setCourses(userDto.getCourses());
        user.setApproved(true);
        Set<Role> roleSet = new HashSet<>();
        String roleName = "LECTURER";
        Role role = roleRepository.findRoleByName(roleName);
        roleSet.add(role);
        user.setRoleName(roleName);
        user.setRoles(roleSet);
        userRepository.save(user);
        return "redirect:/admin/user/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin/user/list";
    }

    @RequestMapping("/update/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("user", userRepository.findById(id).get());
        return "admin/user/edit";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        Long subjectId = userRepository.findById(id).get().getId();
        userRepository.deleteById(subjectId);
        return "redirect:/admin/user/list";
    }

}
