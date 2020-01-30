package coder.TM65.controllers;

import coder.TM65.daos.CatDao;
import coder.TM65.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cat")
public class CategoryController {

    @Autowired
    CatDao catDao;

    @GetMapping("")
    public String all(Model model) {
        List<Category> categories = catDao.findAll();
        model.addAttribute("categories", categories);
        return "cat/all";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("category", new Category());
        return "cat/create";
    }

    @PostMapping("/create")
    public String createNow(@ModelAttribute Category category){
        catDao.save(category);
        return "redirect:/cat";
    }

}
