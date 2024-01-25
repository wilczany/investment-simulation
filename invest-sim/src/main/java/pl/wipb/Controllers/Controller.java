package pl.wipb.Controllers;

import javafx.fxml.FXML;

public abstract class Controller {
    MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void back() {
        mainController.goToMenu();
    }


}
