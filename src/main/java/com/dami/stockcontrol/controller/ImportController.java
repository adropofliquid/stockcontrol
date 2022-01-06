package com.dami.stockcontrol.controller;

import com.dami.stockcontrol.model.*;
import com.dami.stockcontrol.service.PersonService;
import com.dami.stockcontrol.service.ProcessXML;
import com.dami.stockcontrol.service.ProductService;
import com.dami.stockcontrol.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;


@Controller
public class ImportController {

    private final String UPLOAD_DIR = "src/main/resources/templates/uploads/";

    @Autowired
    PersonService personService;

    @Autowired
    ProductService productService;

    @GetMapping("/import")
    public String importPage(){
        return "import";
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file, @RequestParam("import") String importType, RedirectAttributes attributes) {

        // check if file is empty
        if (file.isEmpty()) {
            //TODO only allow xml or csv files
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/import";
        }

        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // save the file on the local file system
        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            importFile(path.toString(), importType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // return success response
        attributes.addFlashAttribute("message", "You successfully imported " + fileName + '!');

        return "redirect:/import";
    }

    private void importFile(String xmlUrl, String importType) {
        if(importType.equals("Products"))
        {
            List<ProductAddInfo> products = new ProcessXML().readXMLProduct(xmlUrl);
            productService.importProducts(getPrincipalName(), products);
        }
        else if(importType.equals("Users")){
            List<Person> people = new ProcessXML().readXMLPeople(xmlUrl);
            personService.saveNewPeople(getPrincipalName(), people);
        }
    }

    private String getPrincipalName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}

