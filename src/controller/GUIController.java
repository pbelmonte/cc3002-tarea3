package controller;

import java.util.Scanner;
import javafx.stage.Stage;
import model.IGameLogic;
import model.card.type.Color;
import model.player.type.IPlayer;
import view.ConsoleView;
import view.GameGUI;

public class GUIController implements IController {
  
  IGameLogic game;
  GameGUI view;
  Stage stage = new Stage();

  /**
   * Controller constructor. Initializes model, view, and input method.
   * Also, it plays the card in discard pile.
   * @param game
   * @param viewand
   * @throws Exception 
   */
  public GUIController(IGameLogic game, GameGUI view) throws Exception {
    this.view = view;
    this.game = game;
    view.start(stage);
    showMessage("¡Bienvenido a JavaUNO!");
    game.getCurrentPlayedCard().executeAction(game, this);
  }

  @Override
  public Color askForColor() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int AskForCardFromHand(IPlayer player) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void showMessage(String message) {
    // TODO Auto-generated method stub

  }

  @Override
  public void playTurn() {
    // TODO Auto-generated method stub

  }

  @Override
  public void updatePlayedCard() {
    // TODO Auto-generated method stub

  }

}
