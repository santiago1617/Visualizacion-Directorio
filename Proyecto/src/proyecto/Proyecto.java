/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import TDA.Tree;
import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Tago
 */
public class Proyecto extends Application {
    
    
    @Override
    public void start(Stage stage) {
        //FileChooser fc= new FileChooser();
        //File file=fc.showOpenDialog(null);
         //System.out.println(file.getAbsoluteFile());
         HBox hb= new HBox();
         hb.setStyle("-fx-background-color: #e67e22;");
         Scene scene= new Scene(hb,400,400);
         System.out.println("Heigth:"+hb.getHeight());
         System.out.println("Width:"+hb.getWidth());
         stage.setScene(scene);
         stage.show();
         
    }
    public Tree<File> crearArbol(String path){
        File carpeta = new File("C:\\Users\\Tago\\Pictures");
         System.out.println(carpeta.isDirectory());
         Tree<File> archivos= new Tree(carpeta);
         File lista[]= carpeta.listFiles();
         System.out.println("Length:"+lista.length);
         for(File file: lista){
             System.out.println(file.getAbsoluteFile());
             System.out.println(file.length()+"\n");
             archivos.addFile(file);
         }
         
         System.out.println("Impresion arbol*******");
         archivos.ImprimirArbol();
        return archivos;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
       // System.out.println(file.getAbsoluteFile());
    }
    
}
