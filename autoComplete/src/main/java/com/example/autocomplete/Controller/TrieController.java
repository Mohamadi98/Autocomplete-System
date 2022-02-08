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
        trieService.insertPrefix("hello");
        trieService.insertPrefix("good morning");
        trieService.insertPrefix("good evening");
        trieService.insertPrefix("how are you ?");
        trieService.insertPrefix("cairo");
        trieService.insertPrefix("alexandria");
        trieService.insertPrefix("kafr elsheikh");
        trieService.insertPrefix("Mansoura");
        trieService.insertPrefix("java");
        trieService.insertPrefix("python");
        trieService.insertPrefix("javascript");
        trieService.insertPrefix("c++");
        trieService.insertPrefix("php");
        trieService.insertPrefix("Go");
        trieService.insertPrefix("Giza");

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
