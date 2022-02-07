package com.example.autocomplete.Controller;

import com.example.autocomplete.Service.TrieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TrieController {

    @Autowired
    private TrieService trieService;

    @RequestMapping("/")
    public String homeView(){
        trieService.insertPrefix("Mohamadi");
        trieService.insertPrefix("mohamed");
        trieService.insertPrefix("mahmoud");
        trieService.insertPrefix("Nada");
        trieService.insertPrefix("nadine");
        trieService.insertPrefix("nadin");
        trieService.insertPrefix("Lamis");
        trieService.insertPrefix("lamYaa");

        return "index";
    }


    @PostMapping("/insert")
    @ResponseBody
    public String insertWord(@RequestParam("prefix") String prefix) {
        trieService.insertPrefix(prefix);
        return "this word: " + prefix + " is inserted";

    }

    @PostMapping("/search")
    @ResponseBody
    public List<String> getResults(@RequestParam("prefix") String prefix) {
        if(prefix.equals("")){
            return new ArrayList<>();
        }
        return trieService.getResults(prefix);
    }

}
