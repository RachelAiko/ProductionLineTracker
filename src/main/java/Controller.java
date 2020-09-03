import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class Controller {


    @FXML
    private Label lblOutput;

    @FXML
    private void sayHello() {
        lblOutput.setText("Hello!");
    }

    @FXML
    private void addProduct() {
        System.out.println("Product Added");
    }

    @FXML
    private void recordProduction() {
        System.out.println("Production Recorded");
    }

    @FXML
    private void itemType() {
    }

    @FXML
    private void chooseQuantity() {
        List<String> chooseQuantity = new ArrayList<String>();
        chooseQuantity.add("1");
        chooseQuantity.add("2");
        chooseQuantity.add("3");
    }

    @FXML
    private void initialize() {

    }

}