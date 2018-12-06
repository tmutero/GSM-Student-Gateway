package zw.co.sms.gsm.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zw.co.sms.gsm.domain.Level;
import zw.co.sms.gsm.repository.CourseRepository;
import zw.co.sms.gsm.repository.DepartmentRepository;
import zw.co.sms.gsm.repository.LevelRepository;
import zw.co.sms.gsm.repository.UserRepository;

import java.util.List;

/**
 * Created by zinzombe on Oct
 */
@Controller
@RequestMapping("/admin/level")
public class LevelController {

    @Autowired
    private LevelRepository levelRepository; 
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = {"/add", "/add/{id}"}, method = RequestMethod.GET)
    public String add(@RequestParam(required = false) Long id, Model model) {
        final Level level;
        if (id != null) {
            level = levelRepository.findById(id).get();
        } else {
            level = new Level();
        }
        model.addAttribute("level", level);
        model.addAttribute("title", "Create/ Edit Level");
        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "admin/level/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("level") @Validated Level level,
                       BindingResult result, SessionStatus status,
                       final RedirectAttributes redirectAttributes) throws Exception {
        //Check validation errors
        if (result.hasErrors()) {
            return "admin/level/add";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
        }
        status.setComplete();
        levelRepository.save(level);
        return "redirect:/admin/level/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Level> levels = levelRepository.findAll();
        model.addAttribute("levels", levels);
        return "admin/level/list";
    }

    @RequestMapping("/update/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("level", levelRepository.findById(id).get());
        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "admin/level/edit";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        Long subjectId = levelRepository.findById(id).get().getId();
        levelRepository.deleteById(subjectId);
        return "redirect:/admin/level/list";
    }

}
