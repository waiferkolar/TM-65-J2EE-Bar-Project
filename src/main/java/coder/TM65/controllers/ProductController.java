package coder.TM65.controllers;

import coder.TM65.daos.CatDao;
import coder.TM65.daos.ProductDao;
import coder.TM65.models.Category;
import coder.TM65.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductDao productDao;
    @Autowired
    CatDao catDao;

    @GetMapping("")
    public String all(Model model) {
        List<Product> products = productDao.findAll();
        model.addAttribute("products", products);
        return "product/all";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        List<Category> categories = catDao.findAll();
        model.addAttribute("categories", categories);
        return "product/create";
    }

    @PostMapping("/create")
    public String save(@ModelAttribute Product product) {
        Category category = catDao.findById(product.getCat_id()).orElse(null);
        product.setCategory(category);
        productDao.save(product);
        return "redirect:/product";
    }

}
