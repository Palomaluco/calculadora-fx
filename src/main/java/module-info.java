module cl.nicolas.prueba1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens cl.nicolas.prueba1 to javafx.fxml;
    exports cl.nicolas.prueba1;
}