package zw.co.gsm.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zw.co.gsm.domain.Faculty;
import zw.co.gsm.repository.FacultyRepository;


import java.util.List;

/**
 * Created by zinzombe on Oct
 */
@Controller
@RequestMapping("/admin/faculty")
public class FacultyController {

    @Autowired
    private FacultyRepository facultyRepository;

    @RequestMapping(value = {"/add", "/add/{id}"}, method = RequestMethod.GET)
    public String add(@RequestParam(required = false) Long id, Model model) {
        final Faculty faculty;
        if (id != null) {
            faculty = facultyRepository.findById(id).get();
        } else {
            faculty = new Faculty();
        }
        model.addAttribute("faculty", faculty);
        model.addAttribute("title", "Create/ Edit Faculty");
        return "admin/faculty/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("faculty") @Validated Faculty faculty,
                       BindingResult result, SessionStatus status,
                       final RedirectAttributes redirectAttributes) throws Exception {
        //Check validation errors
        if (result.hasErrors()) {
            return "admin/faculty/add";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
        }
        status.setComplete();
        facultyRepository.save(faculty);
        return "redirect:/admin/faculty/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Faculty> faculties = facultyRepository.findAll();
        model.addAttribute("faculties", faculties);
        return "admin/faculty/list";
    }

    @RequestMapping("/update/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("faculty", facultyRepository.findById(id).get());
        return "admin/faculty/edit";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        Long subjectId = facultyRepository.findById(id).get().getId();
        facultyRepository.deleteById(subjectId);
        return "redirect:/admin/faculty/list";
    }

}
