package ui.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import domain.GameServices;
import java.util.ArrayList;
import ui.JuomapeliUI;

/**
 * FXML Controller class
 *
 * @author Eemeli
 */
public class StartSceneController implements Initializable {

    private GameServices game;
    private JuomapeliUI application;

    public void setGameDomain(GameServices gameServices) {
        this.game = gameServices;
    }

    public void setApplication(JuomapeliUI application) {
        this.application = application;
    }

    public void update(String warningMsg) {
        int playerCount = this.game.getPlayerCount();
        ArrayList<String> players = this.game.getPlayers();
        for (int i = 0; i < playerCount; i++) {
            this.playerLabels[i].setText(players.get(i));
            this.removeButtons[i].setDisable(false);
        }
        for (int i = playerCount; i < 8; i++) {
            this.playerLabels[i].setText("tyhjä");
            this.removeButtons[i].setDisable(true);
        }
        this.playerTextField.setText("");
        this.warningLabel.setText(warningMsg);
    }

    @FXML
    private TextField playerTextField;

    @FXML
    private Label warningLabel;

    @FXML
    private Label player1;

    @FXML
    private Label player2;

    @FXML
    private Label player3;

    @FXML
    private Label player4;

    @FXML
    private Label player5;

    @FXML
    private Label player6;

    @FXML
    private Label player7;

    @FXML
    private Label player8;
    
    @FXML
    private Button b0;
    
    @FXML
    private Button b1;
    
    @FXML
    private Button b2;
    
    @FXML
    private Button b3;
    
    @FXML
    private Button b4;
    
    @FXML
    private Button b5;
    
    @FXML
    private Button b6;
    
    @FXML
    private Button b7;

    private Label[] playerLabels;
    
    private Button[] removeButtons;

    @FXML
    private void addPlayer(ActionEvent event) {
        String playerName = this.playerTextField.getText();
        this.update(this.game.addPlayer(playerName));
    }

    @FXML
    private void removePlayer(ActionEvent event) {
        int playerNo = Integer.parseInt("" + event.getSource().toString().charAt(11));
        this.update(this.game.removePlayer(playerNo));
    }

    @FXML
    private void startGame(ActionEvent event) {
        if (this.game.getPlayerCount() >= 3) {
            this.application.setGameScene();
        } else {
            this.update("Liian vähän pelaajia!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Label[] labels = {this.player1, this.player2, this.player3, this.player4,
            this.player5, this.player6, this.player7, this.player8};
        this.playerLabels = labels;
        Button[] buttons = {this.b0, this.b1, this.b2, this.b3, this.b4, this.b5, this.b6, this.b7};
        this.removeButtons = buttons;
        for (Button button : removeButtons) {
            button.setDisable(true);
        }
    }
}
