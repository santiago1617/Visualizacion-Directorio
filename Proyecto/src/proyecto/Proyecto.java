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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
        PanelUsuario();
    }
    public void PanelPrincipal(){
        Stage stage= new Stage();
        Label lb= new Label("Elija la carpeta a representar");
        lb.setTextFill(Color.web("#3498db"));
        lb.setFont(new Font("Arial Black",18));
        Button bt= new Button("Navegar");
        bt.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-padding: 20px 25px;-fx-background-color: #9b59b6;"/*+"-fx-border-color: #ED4C67"*/);
        bt.setTextFill(Color.web("#ffffff"));
        
        //aviso.setTextFill(Color.web("#e74c3c"));
        Label aviso= new Label();
        aviso.setTextFill(Color.web("#e74c3c"));
        aviso.setFont(new Font("Arial Black",18));
        aviso.setVisible(false);
        VBox vb= new VBox(lb,aviso,bt);
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(40);
        vb.setStyle("-fx-background-color: #bdc3c7;");
        bt.setOnAction(new EventHandler<ActionEvent>(){
             @Override
             public void handle(ActionEvent event) {
                 aviso.setVisible(false);
                 File file=NavegarCarpetas();
                 if(file!=null){
                     //Preguntar el nombre de la carpeta y agregar archivos
                     RepesentacionGrafica(file);
                 }
                 else{
                     aviso.setText("No a escogido ningun directorio a examinar");
                     aviso.setVisible(true);
                 }
             }
         });
        Scene scene= new Scene(vb,600,300);
        stage.setScene(scene);
        stage.show();
    }
    public void PanelUsuario(){
         Stage stage= new Stage();
        GridPane grid= new GridPane();
        Label nombre= new Label("Usuario: ");
        //#F00F29
        nombre.setTextFill(Color.web("#ffffff"));
        nombre.setFont(new Font("Arial Black",18));
        TextField tfnombre= new TextField();
        Label contra= new Label("Contraseña: ");
        
        contra.setTextFill(Color.web("#ffffff"));
        contra.setFont(new Font("Arial Black",18));
        StackPane st= new StackPane();
        TextField tfcontra= new TextField();
        grid.add(nombre, 0, 0);
        grid.add(tfnombre, 1, 0);
        grid.add(contra, 0, 1);
        grid.add(tfcontra, 1, 1);
        Button ingresar= new Button("Ingresar");
        Button registrar= new Button("Registrar");
        ingresar.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-padding: 20px 25px;-fx-background-color: #5C99BA");
        ingresar.setTextFill(Color.web("#ffffff"));
        registrar.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-padding: 20px 25px;-fx-background-color: #5C99BA");
        registrar.setTextFill(Color.web("#ffffff"));
        
        Label mensaje= new Label("");
        mensaje.setTextFill(Color.web("#ffffff"));
        mensaje.setFont(new Font("Arial Black",18));
        VBox vb= new VBox();
        vb.getChildren().addAll(grid,ingresar,registrar,mensaje);
        vb.setSpacing(20);
        vb.setAlignment(Pos.CENTER);
        Image img= new Image("/Image/fondo.jpg");
        ImageView img1= new ImageView(img);
        img1.setFitHeight(506);
        img1.setFitWidth(900);
        st.getChildren().addAll(img1,vb);
        ingresar.setOnAction(new EventHandler<ActionEvent>(){
             @Override
             public void handle(ActionEvent event) {
                 mensaje.setVisible(false);
                 if(tfnombre.getText().equals("") || tfcontra.getText().equals("")){
                     mensaje.setText("No a ingresado todos los datos");
                     mensaje.setVisible(true);
                 }
                 else{
                     if(Usuario.ComprobarExistencia(tfnombre.getText(),tfcontra.getText())){
                         mensaje.setVisible(false);
                         stage.close();
                         PanelPrincipal();
                     }
                     else{
                         mensaje.setText("Este usuario no a sido registrado");
                     mensaje.setVisible(true);
                     }
                 }
             }
        });
        registrar.setOnAction(new EventHandler<ActionEvent>(){
             @Override
             public void handle(ActionEvent event) {
                 mensaje.setVisible(false);
                 if(tfnombre.getText().equals("") || tfcontra.getText().equals("")){
                     mensaje.setText("No a ingresado todos los datos");
                     mensaje.setVisible(true);
                 }
                 else{
                     if(!Usuario.ComprobarExistencia(tfnombre.getText(),tfcontra.getText())){
                         Usuario us= new Usuario(tfnombre.getText(),tfcontra.getText());
                         LinkedList<Usuario> usuarios=Usuario.Deserializar("usuarios.txt");
                         usuarios.addFirst(us);
                         Usuario.Serializar("usuarios.txt", usuarios);
                         mensaje.setVisible(false);
                         stage.close();
                         PanelPrincipal();
                     }
                     else{
                         mensaje.setText("Este usuario ya a sido creado");
                     mensaje.setVisible(true);
                     }
                 }
             }
         });
        Scene scene= new Scene(st,900,506);
       
        stage.setScene(scene);
        stage.setTitle("Bienvenid@");
        stage.show();
    }
    public void CrearCarpeta(){
         Label lb= new Label("Elija la direccion de la carpeta a crear");
         Button bt= new Button("Navegar");
         bt.setOnAction(new EventHandler<ActionEvent>(){
             @Override
             public void handle(ActionEvent event) {
                 File file=NavegarCarpetas();
                 if(file!=null){
                     //Preguntar el nombre de la carpeta y agregar archivos
                 }
             }
         });
    }
    public File NavegarCarpetas() {
        DirectoryChooser fc = new DirectoryChooser();
        File file = fc.showDialog(null);
        if(file!=null) return file;
        return null;
    }
    public File AbrirArchivo(String path){
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(path));
        File file = fc.showOpenDialog(null);
        if(file!=null) return file;
        return null;
    }
    public void ArchivoMasPesado(File carpeta,Label lb){
        File lista[] = carpeta.listFiles();
        String name="None";
        double peso=0;
        if(lista!=null){
        for (File file : lista) {
            if(!file.isDirectory()){
                if(file.length()>peso){
                    name=file.getName();
                    peso=file.length();
                }
            }
            
        }
        Tooltip tool = new Tooltip(name+": "+conversor(peso));
                
        tool.setTextAlignment(TextAlignment.LEFT);
        lb.setTooltip(tool);
        }
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
    public void RepesentacionGrafica(File carpeta){
        Stage stage= new Stage();
        HBox flow = new HBox();
        //flow.setPadding(new Insets(400, 400, 400, 400));
        flow.setPrefSize(850, 850);
        flow.setAlignment(Pos.TOP_CENTER);
        Tree<File> file=crearArbol(carpeta.getAbsolutePath());
        file.getRaiz().setHeight(800);
        file.getRaiz().setWidth(800);
        file.getRaiz().setPanel(flow);
        dispersarDimensiones(file);
        Label lb= new Label("Click en la carpeta que desee examinar"+"\n"+file.getRaiz().getContent().getName()+": "+conversor(file.getRaiz().getPeso()));
        ArchivoMasPesado(file.getRaiz().getContent(),lb);
        lb.setFont(new Font("Arial Black",18));
        VBox vb= new VBox(lb,flow);
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(20);
        flow.setStyle("-fx-background-color: #686de0" + ";"+"-fx-border-insets: 1 1 1 1;"+"-fx-border-color:#2f3640;");

        ScrollPane scroll=new ScrollPane(vb);
        
        
            Scene scene = new Scene(scroll, 850, 850);
        
        stage.setScene(scene);
        stage.show();
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
                
                if(hijo.isLeaf()){
                    pane.setPrefSize(60, 60);
                    pane.setMaxHeight(60);
                    pane.setMaxWidth(60);
                }
                else{
                pane.setPrefSize(ancho/(cantidad), alto/(cantidad));
                pane.setMaxHeight(alto/(cantidad));
                    pane.setMaxWidth(ancho/(cantidad));
                }
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
                        //AbrirArchivo(hijo.getRaiz().getContent().getAbsolutePath());
                        RepesentacionGrafica(hijo.getRaiz().getContent());
                        contador--;
                        }
                    }
                });
                
                
                ArchivoMasPesado(hijo.getRaiz().getContent(),lb);

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
