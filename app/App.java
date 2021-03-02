package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;


public class App extends Application
{
    public static void main(String[] args)
    {
        Application.launch(args);
    }
    @Override
    public void start(javafx.stage.Stage primaryStage)
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainframe.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("test-window");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e){
            e.printStackTrace();
        }

    }
}
