package com.stackroute.spellchecker.domain;

import com.stackroute.spellchecker.domain.TernaryNode;

public class TernarySearchTreeImpl {
    private TernaryNode root;

    public TernarySearchTreeImpl() {
        root = null;
    }

    public TernarySearchTreeImpl(TernaryNode node) {
        root = node;
    }

    public TernaryNode getRoot() {
        return root;
    }

    public void setRoot(TernaryNode node) {
        this.root = node;
    }

    public void insert(String word, String frequency) {
        root = insert(root, word, frequency, 0);
    }

    private TernaryNode insert(TernaryNode node, String word, String frequency, int pos) {
        if (node == null) {
            if (word.length() <= pos) {
                return node;
            }

            node = new TernaryNode(word.charAt(pos));
            if (pos + 1 == word.length()) {
                node.setIsEnd(true);
                node.setFrequency(frequency);
                //System.out.println("implCHECK ...................."+ node.getData());
                return node;
            }
        }

        if (word.charAt(pos) < node.getData()) {
            node.setLeft(insert(node.getLeft(), word, frequency, pos));
        } else if (word.charAt(pos) > node.getData()) {
            node.setRight(insert(node.getRight(), word, frequency, pos));
        } else {
            if (pos + 1 < word.length()) {
                node.setEqual(insert(node.getEqual(), word, frequency, pos + 1));
            } else {
                // we encountered end of a word
                node.setIsEnd(true);
                node.setFrequency(frequency);
            }
        }
        return node;
    }

    public boolean search(TernaryNode node, String word, int pos) {

        while (node != null) {
            if (word.charAt(pos) < node.getData()) {
                node = node.getLeft();
            } else if (word.charAt(pos) > node.getData()) {
                node = node.getRight();
            } else {
                if (node.getIsEnd() == true && pos == word.length() - 1) {
                    return true;
                }
                pos++;
                node = node.getEqual();
            }
        }
        return false;
    }
}
