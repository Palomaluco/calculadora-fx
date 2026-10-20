package cl.nicolas.prueba1;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField txtPantalla;
    private double num1 = 0;
    private String operador = "";
    private boolean inicioNuevoNumero = true;

    //1 . Maneja la pulsacion de digitos (0-9) y el punto (.)

    @FXML
    private void procesarNumero(ActionEvent event){
        //Sirve para saber que boton de la interfaz presiono el usuario
        System.out.println(event.toString());
        Button btn = (Button) event.getSource();
        String textoBoton = btn.getText();

        //Evita agregar mas de un punto decimal
        if(textoBoton.equals(".") && txtPantalla.getText().contains(".") && !inicioNuevoNumero){
            return;
        }

        if(inicioNuevoNumero){
            txtPantalla.setText(textoBoton.equals(".") ? "0.":textoBoton);
            inicioNuevoNumero = false;
        }else{
            txtPantalla.setText(txtPantalla.getText() + textoBoton);
        }

    }

    //2 . Maneja los operadores (+ , - , X , % , / )
    @FXML
    private void procesarOperador(ActionEvent event){
        Button btn = (Button) event.getSource();
        operador = btn.getText();
        num1 = Double.parseDouble(txtPantalla.getText());
        inicioNuevoNumero = true;
    }

    @FXML
    private void procesarAccion(ActionEvent event){
        Button btn = (Button) event.getSource();
        String accion = btn.getText();

        switch (accion){
            case "=":
                calcularResultado();
                break;
            case "C":
                num1 = 0;
                operador = "";
                txtPantalla.setText("0");
                inicioNuevoNumero = true;
                break;
            case "+/-":
                double actual = Double.parseDouble(txtPantalla.getText());
                actual *= -1;
                formatearResultado(actual);
                break;

        }
    }

    private void calcularResultado(){
        if(operador.isEmpty()) return;

        double num2 = Double.parseDouble(txtPantalla.getText());
        double resultado = 0;

        switch (operador){
            case "+" -> resultado = num1 + num2;
            case "-" -> resultado = num1 - num2;
            case "X" -> resultado = num1 * num2;
            case "/" -> {
                if(num2 == 0){
                    txtPantalla.setText("Error");
                    inicioNuevoNumero = true;
                    operador = "";
                    return;
                }
                resultado = num1 / num2;
            }
            case "%" -> resultado = num1 * ( num2 / 100.0 );
        }

        formatearResultado(resultado);
        operador = "";
        inicioNuevoNumero = true;

    }


    private void formatearResultado(double valor){
        if(valor == (long) valor){
            txtPantalla.setText(String.format("%d",(long) valor));
        }else{
            txtPantalla.setText(String.valueOf(valor));
        }
    }

}