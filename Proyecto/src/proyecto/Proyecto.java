/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import TDA.Tree;

import java.io.File;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
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
         TilePane flow= new TilePane();
         HBox hb= new HBox();
         //hb.setSpacing(100);
         hb.setPadding(new Insets(400,200,400,200));
         hb.setSpacing(0);
         HBox hb2= new HBox();
         //hb2.setSpacing(100);
         hb2.setSpacing(0);
         hb2.setPadding(new Insets(400,200,400,200)); 
         hb.setStyle("-fx-background-color: #e67e22;");
         hb2.setStyle("-fx-background-color: #3398FF;");
         flow.getChildren().addAll(hb,hb2);
         flow.setVgap(0);
         flow.setHgap(0);
         flow.setMaxSize(700, 700);
         flow.setManaged(true);
//         flow.setVgap(100);
//         flow.setHgap(100);
         Scene scene= new Scene(flow,800,800);
         System.out.println("Heigth:"+hb.getHeight());
         System.out.println("Width:"+hb.getWidth());
         System.out.println(hb.getPadding());
         stage.setScene(scene);
         stage.show();
         NavegarCarpetas();
       
    }
    public void NavegarCarpetas(){
        FileChooser fc= new FileChooser();
        File file=fc.showOpenDialog(null);
        System.out.println(file);
    }
    
    public static Tree<File> crearArbol(String path){
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
