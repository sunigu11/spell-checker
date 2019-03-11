package com.stackroute.spellchecker.service;

import com.stackroute.spellchecker.domain.TernaryNode;
import com.stackroute.spellchecker.domain.TernarySearchTreeImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.logging.Logger;

public class WordTree {
    private static Logger log = Logger.getLogger(WordTree.class.getName());

    public static TernaryNode createTree(TernarySearchTreeImpl tst) {
        long startTime = System.currentTimeMillis();

        try {
            //InputStream inputStream = getWordListFile();
            BufferedReader br = new BufferedReader(new FileReader("./dictionary/common-words.txt"));
            String line = "";
            String token[] = null;
            while ((line = br.readLine()) != null) {
                token = line.split("\t");
                // token[0] = actual word.
                // token[1] = frequency of the word.
                //System.out.println("shree ram ..............................");
                System.out.println(token[0]);
                //System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println(token[1]);
               // System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                tst.insert(token[0], token[1]);
            }
            //System.out.println();
            //inputStream.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("Tree creadted in : " + ((System.currentTimeMillis() - startTime)) + " ms");
        System.out.println("helii@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+tst.getRoot());
        return tst.getRoot();
    }

    private static InputStream getWordListFile() throws URISyntaxException {
        //System.out.println("accesing111%%%%%%%%%%%%%%%%%%%%%");
        return WordTree.class.getClassLoader().getResourceAsStream("resources/common-words.txt");
    }
}
