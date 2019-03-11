package com.stackroute.spellchecker.controller;

import com.stackroute.spellchecker.service.Checker;
import com.stackroute.spellchecker.domain.TernarySearchTreeImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("api/v1")
public class SpellCheckerController {
    Checker checker = new Checker();
    TernarySearchTreeImpl ternarySearchTree = new TernarySearchTreeImpl();
    @GetMapping("wordlist")
    public ResponseEntity<?> check(){

        //System.out.println(checker.correct("helo"));
        return new ResponseEntity<>(checker.correct("ABSTACTION"), HttpStatus.OK);
    }
}
