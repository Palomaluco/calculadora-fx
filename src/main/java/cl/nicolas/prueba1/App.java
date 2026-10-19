package cl.nicolas.prueba1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader load = new FXMLLoader(App.class.getResource("vista.fxml"));
        Scene scene = new Scene(load.load(), 300, 400);

        // 1. Cargar el CSS desde la subcarpeta css del paquete actual
        String css = Objects.requireNonNull(App.class.getResource("css/estilos.css")).toExternalForm();

        // 2. Agregarlo a la escena
        scene.getStylesheets().add(css);

        stage.setTitle("Calculadora");
        stage.setScene(scene);
        stage.show();
    }
}
