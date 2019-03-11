package com.stackroute.spellchecker.controller;

import com.stackroute.spellchecker.service.Checker;
import com.stackroute.spellchecker.domain.TernarySearchTreeImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@CrossOrigin
@RestController
@RequestMapping("api/v1")
public class SpellCheckerController {
    Checker checker = new Checker();
    TernarySearchTreeImpl ternarySearchTree = new TernarySearchTreeImpl();
    ArrayList<String> arrayList = new ArrayList<>();
    LinkedHashMap<String,Integer> linkedHashMap = new LinkedHashMap<>();
    String query = "" ;
    @GetMapping("wordlist")
    public ResponseEntity<?> check(){
        String input = "ABSTRACTION";
        String inputArr[] = input.split(" ");
        for(int i=0;i<inputArr.length;i++){
            linkedHashMap = checker.correct(inputArr[i]);
            query+= linkedHashMap.keySet();
        }

        return new ResponseEntity<>(query, HttpStatus.OK);
    }
}
