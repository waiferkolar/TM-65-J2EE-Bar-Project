package coder.TM65.controllers;

import coder.TM65.daos.BookDao;
import coder.TM65.daos.CatDao;
import coder.TM65.models.Book;
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
@RequestMapping("book")
public class BookController {

    @Autowired
    BookDao bookDao;
    @Autowired
    CatDao catDao;

    @GetMapping("")
    public String all(Model model) {
        List<Book> books = bookDao.findAll();
        model.addAttribute("books", books);
        return "book/all";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", catDao.findAll());
        return "book/create";
    }

    @PostMapping("/create")
    public String save(@ModelAttribute Book book) {
        Category category = catDao.findById(book.getCat_id()).orElse(null);
        book.setCategory(category);
        bookDao.save(book);
        return "redirect:/book";
    }
}
