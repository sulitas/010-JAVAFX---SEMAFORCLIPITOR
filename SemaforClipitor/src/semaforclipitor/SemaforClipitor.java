
package semaforclipitor;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author sulitas
 */
public class SemaforClipitor extends Application {
    
    public int n=0;//n intre 0 si 3
    double x0=20, y0=20 , Pspatiu, Dbec, Lsemafor=200, Hsemafor=500; 
    Color culoare[][] = {
                        {Color.DARKRED,Color.GOLDENROD,Color.DARKGREEN},
                        {Color.RED,Color.GOLDENROD,Color.DARKGREEN},
                        {Color.DARKRED,Color.YELLOW,Color.DARKGREEN},
                        {Color.DARKRED,Color.GOLDENROD,Color.LIMEGREEN}
    };
    String semnal[] = {"SEMAFOR NEFUNCTIONAL", "ROSU", "GALBEN", "VERDE"};
    String semnalizare[] = {"-fx-background-color: lightgrey;", "-fx-background-color: red;", "-fx-background-color: yellow;", "-fx-background-color: limegreen"};
    String mesaj[] = {"PORNESTE SEMAFOR", "APASA", "APASA", "APASA"}; 
    
    @Override
    public void start(Stage primaryStage) {
        
        Canvas canvas = new Canvas(230,530);
        canvas.setLayoutX(90);
        canvas.setLayoutY(0);
        GraphicsContext gc = canvas.getGraphicsContext2D();
         
        Pspatiu=Hsemafor/28;
        Dbec=8*Pspatiu;
        Lsemafor=10*Pspatiu; // in functie de inaltime semafor
        
        //deseneaza cadrul exterior al semaforului
        gc.setStroke(Color.BLACK);  
        gc.setLineWidth(20);
        gc.strokeRect(x0,y0,Lsemafor,Hsemafor);
        gc.setFill(Color.GREY); 
        gc.fillRect(x0,y0,Lsemafor,Hsemafor);
        
        //deseneaza becurile semaforului
        for(int i=0;i<3;i++) {
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(5);
            gc.strokeOval(x0+Pspatiu,y0+Pspatiu+i*(Pspatiu+Dbec),Dbec,Dbec);
            gc.setFill(culoare[0][i]);
            gc.fillOval(x0+Pspatiu,y0+Pspatiu+i*(Pspatiu+Dbec),Dbec,Dbec);
        } 
        
        Label eticheta = new Label("SEMAFOR NEFUNCTIONAL");
        eticheta.setLayoutX(100);
        eticheta.setLayoutY(550);
        eticheta.setPrefHeight(25);
        eticheta.setPrefWidth(200);
        eticheta.setAlignment(Pos.CENTER);
        eticheta.setStyle("-fx-background-color: lightgrey;");
        
        
        Button pornestesemafor = new Button();
        pornestesemafor.setText("PORNESTE SEMAFOR");
        pornestesemafor.setLayoutX(100);
        pornestesemafor.setLayoutY(600);
        pornestesemafor.setPrefHeight(25);
        pornestesemafor.setPrefWidth(200);
        pornestesemafor.setOnAction((ActionEvent event) -> {
            n=++n%4;
            eticheta.setStyle(semnalizare[n]);
            eticheta.setText(semnal[n]);
            pornestesemafor.setText(mesaj[n]);
            //porneste becuri semafor
            for(int i=0;i<3;i++) {
                gc.setFill(culoare[n][i]);
                gc.fillOval(x0+Pspatiu,y0+Pspatiu+i*(Pspatiu+Dbec),Dbec,Dbec);
            } 
        }); 
           
        Pane root = new Pane(); // pentru a putea da coordonate absolute controalelor
        root.getChildren().add(canvas);
        root.getChildren().add(eticheta);
        root.getChildren().add(pornestesemafor);
        
        Scene scene = new Scene(root, 400, 650);
        
        primaryStage.setTitle("Silviu Sulita - JavaFX - Semafor Clipitor");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
