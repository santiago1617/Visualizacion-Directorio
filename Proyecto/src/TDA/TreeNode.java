/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDA;

import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

/**
 *
 * @author Tago
 */
public class TreeNode<E> {
    private E content;
    private LinkedList<Tree<E>> hijos;
    private double peso;
    private double width;
    private double height;
    private Pane panel;
    
    public TreeNode(){
        content=null;
        hijos=new LinkedList();
        width=0;
        height=0;
        peso=0;
        panel=new TilePane();
    }
    public TreeNode(E content){
        this.content=content;
        hijos=new LinkedList();
        width=0;
        height=0;
        peso=0;
        panel=new TilePane();
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

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Pane getPanel() {
        return panel;
    }

    public void setPanel(Pane panel) {
        this.panel = panel;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
    
    
}
