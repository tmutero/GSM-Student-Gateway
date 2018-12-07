package zw.co.gsm.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import zw.co.gsm.domain.Degree;
import zw.co.gsm.repository.DegreeRepository;
import zw.co.gsm.repository.FacultyRepository;


import java.util.List;

/**
 * Created by zinzombe on Oct
 */
@Controller
@RequestMapping("/admin/degree")
public class DegreeController {

    @Autowired
    private DegreeRepository degreeRepository;
@Autowired
private FacultyRepository facultyRepository;

    @RequestMapping(value = {"/add", "/add/{id}"}, method = RequestMethod.GET)
    public String add(@RequestParam(required = false) Long id, Model model) {
        final Degree degree;
        if (id != null) {
            degree = degreeRepository.findById(id).get();
        } else {
            degree = new Degree();
        }
        model.addAttribute("degree", degree);
        model.addAttribute("title", "Create/ Edit Degree");
        model.addAttribute("faculties", facultyRepository.findAll());
        return "admin/degree/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("degree") @Validated Degree degree,
                       BindingResult result, SessionStatus status, Model model,
                       final RedirectAttributes redirectAttributes) throws Exception {
        //Check validation errors
        if (result.hasErrors()) {
            model.addAttribute("faculties", facultyRepository.findAll());
            return "admin/degree/add";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
        }
        status.setComplete();
        degreeRepository.save(degree);
        return "redirect:/admin/degree/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Degree> degrees = degreeRepository.findAll();
        model.addAttribute("degrees", degrees);
        return "admin/degree/list";
    }

    @RequestMapping("/update/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("degree", degreeRepository.findById(id).get());
        model.addAttribute("faculties", facultyRepository.findAll());
        return "admin/degree/edit";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        Long subjectId = degreeRepository.findById(id).get().getId();
        degreeRepository.deleteById(subjectId);
        return "redirect:/admin/degree/list";
    }

}
