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

    public Tree() {
        raiz = new TreeNode();
    }

    public Tree(E content) {
        raiz = new TreeNode(content);
    }

    public void addNode(E content) {
        Tree<E> arbol = new Tree();
        TreeNode<E> nodo = new TreeNode(content);
        arbol.setRaiz(nodo);
        this.raiz.getHijos().addLast(arbol);
    }

    public void addFile(File file) {
        Tree<E> arbol = new Tree();
        TreeNode<E> nodo = new TreeNode(file);
        if (!file.isDirectory()) {
            nodo.setPeso(file.length());
        }
        arbol.setRaiz(nodo);
        if (file.isDirectory()) {
            this.raiz.getHijos().addLast(arbol);
            File[] lista = file.listFiles();
            //NUEVOOOOO CAMBIO CONFIRMAR ESTA VALIDACION
            if(lista!=null){
            for (File archivo : lista) {
                
                arbol.addFile(archivo);
            }}
            this.raiz.setPeso(this.raiz.getPeso() + arbol.raiz.getPeso());
        } else {

            this.raiz.getHijos().addLast(arbol);
            this.raiz.setPeso(this.raiz.getPeso() + file.length());
        }
    }

    

    public void ImprimirArbol() {

        LinkedList<Tree<E>> hijos = this.raiz.getHijos();
        Iterator<Tree<E>> iterator = hijos.iterator();
        if (this.isLeaf()) {
            System.out.println("Hijo:" + this.getRaiz().getContent() + "peso: " + this.raiz.getPeso());
        } else {
            System.out.println("Padre: " + this.getRaiz().getContent() + "peso: " + this.raiz.getPeso());
        }
        while (iterator.hasNext()) {
            Tree<E> hijo = iterator.next();

            hijo.ImprimirArbol();

        }
    }

    public boolean isLeaf() {
        return this.raiz.getHijos().isEmpty();
    }

    public TreeNode<E> getRaiz() {
        return raiz;
    }

    public void setRaiz(TreeNode<E> raiz) {
        this.raiz = raiz;
    }

}
