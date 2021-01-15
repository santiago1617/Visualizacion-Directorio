/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDA;

import java.io.File;
import java.util.Iterator;

/**
 *
 * @author Tago
 */
public class Tree<E> {
    private TreeNode<E> raiz;
    public Tree(){
        raiz= new TreeNode();
    }
    public Tree(E content){
        raiz= new TreeNode(content);
    }
    public void addNode(E content){
        Tree<E> arbol= new Tree();
        TreeNode<E> nodo= new TreeNode(content);
        arbol.setRaiz(nodo);
        this.raiz.getHijos().addLast(arbol);}
    
    public void addFile(File file){
        Tree<E> arbol= new Tree();
        TreeNode<E> nodo= new TreeNode(file);
        arbol.setRaiz(nodo);
            if(file.isDirectory()){
                this.raiz.getHijos().addLast(arbol);
                File[] lista=file.listFiles();
                for(File archivo: lista){
                    arbol.addFile(archivo);
                    
                }
            }
            else{
                this.raiz.getHijos().addLast(arbol);
            }
        
    }
    
    public void ImprimirArbol(){
        
       LinkedList<Tree<E>> hijos=this.raiz.getHijos();
       Iterator<Tree<E>> iterator = hijos.iterator();
        while (iterator.hasNext()) {
            Tree<E> hijo=iterator.next();
            if(hijo.isLeaf()){
                System.out.println("Hijo:"+hijo.getRaiz().getContent());
            }
            else{
                System.out.println("Padre: "+hijo.getRaiz().getContent());
                hijo.ImprimirArbol();
            }
            
        }
    }
    public boolean isLeaf(){
        return this.raiz.getHijos().isEmpty();
    }
    public TreeNode<E> getRaiz() {
        return raiz;
    }

    public void setRaiz(TreeNode<E> raiz) {
        this.raiz = raiz;
    }
    
}
