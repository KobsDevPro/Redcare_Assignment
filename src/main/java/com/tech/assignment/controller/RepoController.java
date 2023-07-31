package com.tech.assignment.controller;

import com.tech.assignment.pojo.Item;
import com.tech.assignment.service.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RepoController {

    @Autowired
    RepoService repoService;

    @GetMapping(value = "/sortByStar")
    public @ResponseBody List<Item> sortByStar(){
        return repoService.sortByStars();
    }

    @GetMapping(value = "/sortByTopNumber")
    public ResponseEntity<Object> sortByTopNumber(@RequestParam int number){
        List<Item> items = repoService.sortByTopNumber(number);
        if (items == null ) {
            return new ResponseEntity<> (HttpStatus.BAD_REQUEST); // Or any other error status
        } else {
            return new ResponseEntity<> (items, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/filterByLanguage")
    public ResponseEntity<Object> filterByLanguage(@RequestParam String language){
        List<Item> items = repoService.filterByLanguage(language);
        if (items == null ) {
            return new ResponseEntity<> (HttpStatus.BAD_REQUEST); // Or any other error status
        } else {
            return new ResponseEntity<> (items, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/filterByDate")
    public ResponseEntity<Object> filterByDate(@RequestParam String date){
        List<Item> items = repoService.filterByDate(date);
        if (items == null ) {
            return new ResponseEntity<> (HttpStatus.BAD_REQUEST); // Or any other error status
        } else {
            return new ResponseEntity<> (items, HttpStatus.OK);
        }
    }
}
