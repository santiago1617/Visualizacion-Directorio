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
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 *
 * @author Tago
 */
public class Proyecto extends Application {

    private String[] colores = {"#55efc4", "#81ecec", "#74b9ff", "#74b9ff", "#a29bfe", "#dfe6e9", "#00b894", "#00cec9", "#0984e3", "#6c5ce7", "#b2bec3", "#ffeaa7", "#fab1a0", "#ff7675", "#fd79a8",
         "#636e72", "#fdcb6e", "#e17055", "#d63031", "#e84393"};
    private int contador=1;

    @Override
    public void start(Stage stage) {
        //FileChooser fc= new FileChooser();
        //File file=fc.showOpenDialog(null);
        //System.out.println(file.getAbsoluteFile());
        FlowPane flow = new FlowPane();
        //flow.setPadding(new Insets(400, 400, 400, 400));
        flow.setPrefSize(1000, 1000);
        Tree<File> file=crearArbol("D:\\Desktop");
        file.getRaiz().setHeight(1000);
        file.getRaiz().setWidth(1000);
        file.getRaiz().setPanel(flow);
        dispersarDimensiones(file);
        Label lb= new Label("Click en la carpeta que desee examinar"+"\n"+file.getRaiz().getContent().getName()+": "+conversor(file.getRaiz().getPeso()));
        lb.setFont(new Font("Arial Black",18));
        VBox vb= new VBox(lb,flow);
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(20);
        flow.setStyle("-fx-background-color: #f5f6fa" + ";"+"-fx-border-insets: 1 1 1 1;"+"-fx-border-color:#2f3640;");
        flow.setAlignment(Pos.CENTER);
        
        
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
        ScrollPane scroll=new ScrollPane(vb);
        
        
            Scene scene = new Scene(scroll, 1000, 1000);
        
        stage.setScene(scene);
        stage.show();
        //crearArbol("sa");
        //NavegarCarpetas();
    }

    public File NavegarCarpetas() {
        DirectoryChooser fc = new DirectoryChooser();
        File file = fc.showDialog(null);
        if(file!=null) return file;
        return null;
    }
    public void AbrirCarpeta(String path){
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(path));
        File file = fc.showOpenDialog(null);
        
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
                FlowPane pane = new FlowPane();
                Label lb= new Label(hijo.getRaiz().getContent().getName()+"\nPeso:"+conversor(hijo.getRaiz().getPeso()));
                lb.setFont(new Font("Arial",18));
                pane.getChildren().add(lb);
//                Rectangle rect= new Rectangle(ancho/cantidad,alto/cantidad);
//                pane.getChildren().add(rect);
                //pane.setPadding(new Insets(alto / (3 * cantidad), ancho / (3 * cantidad), alto / (3 * cantidad), ancho / (3 * cantidad)));
                pane.setPrefSize(ancho/(cantidad), alto/(cantidad));
                int ind = (int) (Math.random() * (colores.length - 1));
                String color=colores[ind];
                pane.setOnMouseEntered(new EventHandler <MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {
                        System.out.println("Hola dice : "+hijo.getRaiz().getContent().getName()+" con size: "+pane.getWidth()+", "+pane.getHeight());
                       pane.setPrefSize(pane.getWidth()+40, pane.getHeight()+40);
                       contador=1;
                       pane.setStyle("-fx-background-color: " + color + ";"+"-fx-border-insets: 2 2 2 2;"+"-fx-border-color:#2f3640;");
                    }
                });
                pane.setOnMouseExited(new EventHandler <MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {
                        System.out.println("ADIOS dice : "+hijo.getRaiz().getContent().getName()+" con size: "+pane.getWidth()+", "+pane.getHeight());
                       pane.setPrefSize(ancho/(cantidad), alto/(cantidad));
                       contador=1;
                       pane.setStyle("-fx-background-color: " + color + ";"+"-fx-border-insets: 1 1 1 1;"+"-fx-border-color:#2f3640;");
                      
                    }
                });
                pane.setOnMousePressed(new EventHandler <MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {
                        if(contador==1){
                        AbrirCarpeta(hijo.getRaiz().getContent().getAbsolutePath());
                        contador--;
                        }
                    }
                });
                
                
                
                Tooltip tool = new Tooltip(hijo.getRaiz().getContent().getName());
                
                tool.setTextAlignment(TextAlignment.LEFT);
                lb.setTooltip(tool);
                System.out.println("Carpeta: "+hijo.getRaiz().getContent().getName()+"alto: "+alto/(cantidad)+"ancho: "+ancho/(cantidad));
                //pane.maxWidth(ancho/cantidad);
                //pane.maxHeight(alto/cantidad);
                
                pane.setStyle("-fx-background-color: " + color + ";"+"-fx-border-insets: 1 1 1 1;"+"-fx-border-color:#2f3640;");
                //pane.setStyle("-fx-background-color: #f5f6fa" + ";"+"-fx-border-insets: 1 1 1 1;"+"-fx-border-color:#2f3640;");
                pane.setVgap(20);
                pane.setHgap(20);
                hijo.getRaiz().setHeight(alto / cantidad);
                hijo.getRaiz().setWidth(ancho / cantidad);
                hijo.getRaiz().setPanel(pane);
                raiz.getRaiz().getPanel().getChildren().add(pane);
                dispersarDimensiones(hijo);
            }
        }
    }
    public String conversor(double peso){
        if(peso<1024){
            return peso+" b";
        }
        else if(peso <1048576 ){
            return Math.round(((peso/1024)*100d))/100d+" Kb"; 
        }
        else if(peso<1073741824){
            return Math.round(((peso/1048576)*100d))/100d+" Mb";
        }
        else{
            return Math.round(((peso/1073741824)*100d))/100d+" Gb";
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

        // System.out.println(file.getAbsoluteFile());
    }

}
