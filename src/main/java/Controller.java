import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
}