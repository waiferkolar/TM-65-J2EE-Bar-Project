package coder.TM65.controllers;

import coder.TM65.daos.CatDao;
import coder.TM65.daos.CookDao;
import coder.TM65.models.Category;
import coder.TM65.models.Cook;
import com.sun.org.apache.xpath.internal.operations.Mult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("cook")
public class CookController {

    @Autowired
    CookDao cookDao;
    @Autowired
    CatDao catDao;

    @GetMapping("")
    public String all(Model model) {
        List<Cook> cooks = cookDao.findAll();
        for(Cook cook : cooks){
            String coookOrUrl = cook.getUrl();
            String [] urlArys = coookOrUrl.split(",");
            System.out.println(urlArys);
            cook.setUrl(urlArys[0]);
        }
        model.addAttribute("cooks", cooks);
        return "cook/all";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("cook", new Cook());
        model.addAttribute("categories", catDao.findAll());
        return "cook/create";
    }

    @PostMapping("/create")
    public String save(@ModelAttribute Cook cook) {
        Category category = catDao.findById(cook.getCat_id()).orElse(null);
        List<MultipartFile> files = cook.getFiles();

        String url = saveMultipleImage(files);

        cook.setUrl(url);
        cook.setCategory(category);

        cookDao.save(cook);

        return "redirect:/cook";
    }


    private String saveMultipleImage(List<MultipartFile> files) {

        List<String> filenames = new ArrayList<>();

        for (MultipartFile file : files) {
            filenames.add(saveSingleFile(file));
        }
        String fnames = String.join(",", filenames);
        return fnames;
    }

    private String saveSingleFile(MultipartFile file) {
        Path relativePath = Paths.get("");
        String absolutePath = relativePath.toAbsolutePath().toString();
        String realPath = absolutePath + "/src/main/resources/static/imgs/uploads/";
        String filename = file.getOriginalFilename();
        Path uploadDir = Paths.get(realPath + filename);
        try {
            byte[] bytes = file.getBytes();
            Files.write(uploadDir, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }
}
