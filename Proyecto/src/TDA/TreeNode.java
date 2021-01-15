/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDA;

import TDA.Tree;
import TDA.LinkedList;

/**
 *
 * @author Tago
 */
public class TreeNode<E> {
    private E content;
    private LinkedList<Tree<E>> hijos;
    public TreeNode(){
        content=null;
        hijos=new LinkedList();
    }
    public TreeNode(E content){
        this.content=content;
        hijos=new LinkedList();
    }
    

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public LinkedList<Tree<E>> getHijos() {
        return hijos;
    }

    public void setHijos(LinkedList<Tree<E>> hijos) {
        this.hijos = hijos;
    }
    
}
