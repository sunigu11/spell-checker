package com.stackroute.spellchecker.service;

import com.stackroute.spellchecker.domain.PriorityQueeImpl;
import com.stackroute.spellchecker.domain.TernaryNode;
import com.stackroute.spellchecker.domain.TernarySearchTreeImpl;

import java.util.LinkedHashMap;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Checker {
    private static Logger log = Logger.getLogger(Checker.class.getName());
    private static TernarySearchTreeImpl tst = new TernarySearchTreeImpl();
    private static TernaryNode ternaryNode = new TernaryNode();
    private int EDIT_LIMIT = 3;
    private int SUGGESTED_WORD_LIST_LIMIT = 10;
    private String inputString = "";
    private static PriorityQueue<PriorityQueeImpl> suggestedWords = new PriorityQueue<PriorityQueeImpl>(10);
   // LinkedHashMap<String, Integer> outputMap = new LinkedHashMap<String, Integer>();

    // create the ternary search tree and populate with words.
    static {
        WordTree.createTree(tst);
        //System.out.println("*******************************");
       // System.out.println("hello this is ------"+);
    }

    /**
     * Get the current value of EDIT_LIMIT
     *
     */
    public int getEditLimit() {
        return this.EDIT_LIMIT;
    }

    /**
     * Set the max distance of output words. Default is 3.
     *
     *
     */
    public void setEditLimit(int edit_limit) {
        if (edit_limit < 0) {
            log.log(Level.WARNING, "Word edit limit cannot be negative.");
            return;
        }
        this.EDIT_LIMIT = edit_limit;
    }

    /**
     * return the current SUGGESTED_WORD_LIST_LIMIT value as integer
     */
    public int getSuggestedWordListLimit() {
        return this.SUGGESTED_WORD_LIST_LIMIT;
    }

    /**
     * Set the max number of suggested words in output. Default is 10.
     *
     * @param word_list_limit
     */
    public void setSuggestedWordListLimit(int word_list_limit) {
        if (word_list_limit <= 0) {
            log.log(Level.WARNING, "word list limit cannot be less than 1.");
            return;
        }
        this.SUGGESTED_WORD_LIST_LIMIT = word_list_limit;
    }

    /**
     * Returns a linkedHashMap where key is the suggested word and value is its
     * Levenshtein Distance from input word. Result is sorted ascending by edit
     * distance and then by its frequency of occurrence in the language.
     *
     * @param str
     * @return
     * @throws IllegalArgumentException
     */
    public LinkedHashMap<String, Integer> correct(String str) throws IllegalArgumentException {
        if (str == null || str.equals("")) {
            log.log(Level.FINE, "Input word is empty.");
            throw new IllegalArgumentException("Input string is blank.");
        }
        inputString = str;
        System.out.println(str+"stringcheck<<<<<<<<<<<<<<<<<<<<<<<<");
        traverse(tst.getRoot(), "");
        System.out.println("after................................");
        // adding words to linkedHashMap for output.
        LinkedHashMap<String, Integer> outputMap = new LinkedHashMap<String, Integer>();
        for (int i = 0; suggestedWords.isEmpty() == false && i < SUGGESTED_WORD_LIST_LIMIT; i++) {
            System.out.println("checking outmap");
            PriorityQueeImpl element = suggestedWords.poll();
            outputMap.put(element.getWord(), element.getDistance());
            System.out.println("check element "+element.getWord()+element.getFrequency());
        }

        return outputMap;
    }

    private void traverse(TernaryNode root, String str) {
        System.out.println("checking traverse ..........."+ str);
        System.out.println(suggestedWords);
        if (root == null)
            return;
        //System.out.println("checking traverse .........................");
        int distance = getEditDistance(inputString, str + root.getData());
        System.out.println("checking root data traverse " + root.getData());
        // skip traversing the nodes below which distance is grater than
        // EDIT_LIMIT.
        if ((str.length() < inputString.length())
                && (getEditDistance(str, inputString.substring(0, str.length() + 1)) > EDIT_LIMIT)) {
            return;
        } else if (str.length() > inputString.length() + EDIT_LIMIT) {
            return;
        } else if (Math.abs(str.length() - inputString.length()) <= EDIT_LIMIT && distance > EDIT_LIMIT) {
            return;
        }

        // recursively traverse through the nodes for words
        traverse(root.getLeft(), str);
        if (root.getIsEnd() == true && distance <= EDIT_LIMIT) {
            System.out.println( "checking editlimit,,,,,,,,,,,,,,,");
            suggestedWords.add(new PriorityQueeImpl(str + root.getData(), distance, root.getFrequency()));
        }
        traverse(root.getEqual(), str + root.getData());
        traverse(root.getRight(), str);
    }

    // Levenshtein distance
    private int getEditDistance(String a, String b) {
        System.out.println("checking edit distance ... ");
        int[] costs = new int[b.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= a.length(); i++) {
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]),
                        a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        System.out.println(costs[b.length()]);
        return costs[b.length()];
    }
}
