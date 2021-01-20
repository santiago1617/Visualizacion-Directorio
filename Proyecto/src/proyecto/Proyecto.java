/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import TDA.LinkedList;
import TDA.Tree;

import java.io.File;
import java.util.Iterator;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.TilePane;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 *
 * @author Tago
 */
public class Proyecto extends Application {

    private String[] colores = {"#55efc4", "#81ecec", "#74b9ff", "#74b9ff", "#a29bfe", "#dfe6e9", "#00b894", "#00cec9", "#0984e3", "#6c5ce7", "#b2bec3", "#ffeaa7", "#fab1a0", "#ff7675", "#fd79a8",
         "#636e72", "#fdcb6e", "#e17055", "#d63031", "#e84393"};

    @Override
    public void start(Stage stage) {
        //FileChooser fc= new FileChooser();
        //File file=fc.showOpenDialog(null);
        //System.out.println(file.getAbsoluteFile());
        TilePane flow = new TilePane();
        //flow.setPadding(new Insets(400, 400, 400, 400));
        flow.setStyle("-fx-background-color: #f5f6fa" + ";"+"-fx-border-insets: 1 1 1 1;"+"-fx-border-color:#2f3640;");
        flow.setAlignment(Pos.CENTER);
        
        flow.setPrefSize(800, 100);
        Tree<File> file=crearArbol("C:\\Users\\Tago\\Documents\\NetBeansProjects");
        file.getRaiz().setHeight(600);
        file.getRaiz().setWidth(800);
        file.getRaiz().setPanel(flow);
        dispersarDimensiones(file);
//        HBox hb = new HBox();
//        //hb.setSpacing(100);
//        hb.setPadding(new Insets(400, 200, 400, 200));
//        hb.setSpacing(0);
//        HBox hb2 = new HBox();
//        //hb2.setSpacing(100);
//        hb2.setSpacing(0);
//        hb2.setPadding(new Insets(400, 200, 400, 200));
//        hb.setStyle("-fx-background-color: #e67e22;");
//        hb2.setStyle("-fx-background-color: #3398FF;");
//        flow.getChildren().addAll(hb, hb2);
//        flow.setVgap(0);
//        flow.setHgap(0);
//        flow.setMaxSize(700, 700);
//        flow.setManaged(true);
//         flow.setVgap(100);
//         flow.setHgap(100);
        ScrollPane scroll=new ScrollPane(flow);
        
        
            Scene scene = new Scene(scroll, 800, 800);
        
        stage.setScene(scene);
        stage.show();
        //crearArbol("sa");

    }

    public void NavegarCarpetas() {
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(null);
        System.out.println(file);
    }

    public static Tree<File> crearArbol(String path) {
        File carpeta = new File(path);
//        System.out.println(carpeta.isDirectory());
        Tree<File> archivos = new Tree(carpeta);
        File lista[] = carpeta.listFiles();
//        System.out.println("Length:" + lista.length);
        for (File file : lista) {
//            System.out.println(file.getAbsoluteFile());
//            System.out.println(file.length() + "\n");
            archivos.addDirectory(file);
        }

        System.out.println("Impresion arbol*******");
        archivos.ImprimirArbol();
        return archivos;
    }

    public void dispersarDimensiones(Tree<File> raiz) {
        LinkedList<Tree<File>> hijos = raiz.getRaiz().getHijos();
        Iterator<Tree<File>> iterator = hijos.iterator();
        int cantidad = hijos.size();
        double alto = raiz.getRaiz().getHeight();
        double ancho = raiz.getRaiz().getWidth();
        while (iterator.hasNext()) {

            Tree<File> hijo = iterator.next();
            if (hijo.getRaiz().getContent().isDirectory()) { 
                TilePane pane = new TilePane();
                Label lb= new Label(hijo.getRaiz().getContent().getName()+" :"+hijo.getRaiz().getPeso()+" kb");
                pane.getChildren().add(lb);
//                Rectangle rect= new Rectangle(ancho/cantidad,alto/cantidad);
//                pane.getChildren().add(rect);
                //pane.setPadding(new Insets(alto / (3 * cantidad), ancho / (3 * cantidad), alto / (3 * cantidad), ancho / (3 * cantidad)));
                pane.setPrefSize(ancho/(cantidad), alto/(cantidad));
                
                Tooltip tool = new Tooltip(hijo.getRaiz().getContent().getName());
                
                tool.setTextAlignment(TextAlignment.LEFT);
                lb.setTooltip(tool);
                System.out.println("Carpeta: "+hijo.getRaiz().getContent().getName()+"alto: "+alto/(cantidad)+"ancho: "+ancho/(cantidad));
                //pane.maxWidth(ancho/cantidad);
                //pane.maxHeight(alto/cantidad);
                int ind = (int) (Math.random() * (colores.length - 1));
                pane.setStyle("-fx-background-color: " + colores[ind] + ";"+"-fx-border-insets: 1 1 1 1;"+"-fx-border-color:#2f3640;");
                //pane.setStyle("-fx-background-color: #f5f6fa" + ";"+"-fx-border-insets: 1 1 1 1;"+"-fx-border-color:#2f3640;");
                pane.setVgap(10);
                pane.setHgap(10);
                hijo.getRaiz().setHeight(alto / cantidad);
                hijo.getRaiz().setWidth(ancho / cantidad);
                hijo.getRaiz().setPanel(pane);
                raiz.getRaiz().getPanel().getChildren().add(pane);
                dispersarDimensiones(hijo);
            }
        }
    }
    public void deleteFile(File file){
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

        // System.out.println(file.getAbsoluteFile());
    }


}
