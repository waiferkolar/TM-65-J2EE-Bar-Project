package coder.TM65.controllers;

import coder.TM65.daos.TableDao;
import coder.TM65.models.Tables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("tables")
public class TableController {

    @Autowired
    TableDao tableDao;

    @GetMapping(value = "/")
    public String all(Model model) {
        List<Tables> tables = tableDao.findAll();
        model.addAttribute("tables", tables);
        return "tables/all";
    }

    @GetMapping(value = "/create")
    public String create() {
        return "tables/create";
    }

    @PostMapping(value = "/create")
    public String createNow(HttpServletRequest request) {
        String name = request.getParameter("name");
        Tables table = new Tables();
        table.setName(name);
        tableDao.save(table);
        return "redirect:/tables/";
    }

    @GetMapping(value = "/status/update/{id}")
    public String updateStatus(@PathVariable int id) {
        Tables table = tableDao.findById(id).orElse(null);
        System.out.println(table.getStatus());
        if(table.getStatus() == 1){
            table.setStatus(0);
        }else{
            table.setStatus(1);
        }

        tableDao.save(table);
        return "redirect:/tables/";
    }
}
