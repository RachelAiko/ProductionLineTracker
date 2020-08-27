import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {
    @FXML
    private Label lblOutput;

    @FXML
    private void sayHello() {
        lblOutput.setText("Hello!");
    }

}