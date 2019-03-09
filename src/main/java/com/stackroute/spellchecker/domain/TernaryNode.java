package com.stackroute.spellchecker.domain;

public class TernaryNode {
    private char data = ' ';
    private boolean isEnd = false; // end of a word?
    private TernaryNode left = null, equal = null, right = null;
    private String frequency = null;

    public TernaryNode() {
    }

    public TernaryNode(char data) {
        this.data = data;
    }

    public void setData(char data) {
        this.data = data;
    }

    public char getData() {
        return this.data;
    }

    public void setIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    public boolean getIsEnd() {
        return this.isEnd;
    }

    public void setLeft(TernaryNode left) {
        this.left = left;
    }

    public TernaryNode getLeft() {
        return this.left;
    }

    public TernaryNode setEqual(TernaryNode equal) {
        return (this.equal = equal);
    }

    public TernaryNode getEqual() {
        return this.equal;
    }

    public void setRight(TernaryNode right) {
        this.right = right;
    }

    public TernaryNode getRight() {
        return this.right;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getFrequency() {
        return this.frequency;
    }

}
