package view;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.IGameLogic;
import model.UnoLogic;
import model.card.deck.NormalUnoDeck;
import model.card.type.ICard;
import model.player.IPlayerListBuilder;
import model.player.UnoPlayerListBuilder;
import model.player.type.HumanPlayer;
import model.player.type.IPlayer;
import model.player.type.RandomPlayer;

public class GameGUI extends Application {
  
  private ImageView discardView;
  private ImageView deckView;
  private IGameLogic game;
  private IPlayer p1;

  @Override
  public void start(Stage stage) throws Exception {
    game = makeGame();
    stage.setTitle("JavaUNO");
    BorderPane borderpane = new BorderPane();
    borderpane.setTop(makePlayers());
    borderpane.setCenter(makeCenter());
    borderpane.setBottom(makeHand());
    Scene scene = new Scene(borderpane, 990, 600);
    stage.setScene(scene);
    stage.show();
  }

  private IGameLogic makeGame() {
    IPlayerListBuilder playerBuilder = new UnoPlayerListBuilder();
    p1 = new HumanPlayer("Jugador 1");
    IPlayer p2 = new RandomPlayer("CPU 1");
    IPlayer p3 = new RandomPlayer("CPU 2");
    IPlayer p4 = new RandomPlayer("CPU 3");
    playerBuilder.addPlayer(p1);
    playerBuilder.addPlayer(p2);
    playerBuilder.addPlayer(p3);
    playerBuilder.addPlayer(p4);
    IGameLogic game = new UnoLogic(playerBuilder, new NormalUnoDeck());
    return game;
  }

  private Node makeHand() {
    GridPane hand = new GridPane();
    hand.setHgap(16.0);
    Button right = new Button(">");
    Button left = new Button("<");
    right.setPrefHeight(150);
    left.setPrefHeight(150);
    hand.addColumn(0, left);
    hand.addColumn(8, right);
    List<ICard> mano = p1.getHand();
    List<Button> botones = new ArrayList<Button>();
    for (int i = 0; i < p1.getHandSize(); i++) {
      Image card = new Image(mano.get(i).path());
      ImageView cardView = new ImageView(card);
      cardView.setSmooth(true);
      cardView.setCache(true);
      cardView.setFitHeight(150); 
      cardView.setFitWidth(250);
      cardView.setPreserveRatio(true);
      Button btn = new Button();
      btn.setGraphic(cardView);
      botones.add(btn);
      hand.addColumn(i + 1, btn);
    }
    return hand;
  }

  private Node makeCenter() {
    String playedCardPath = game.getCurrentPlayedCard().path();
    Image discard = new Image(playedCardPath);
    discardView = new ImageView(discard);
    discardView.setSmooth(true);
    discardView.setCache(true);
    discardView.setFitHeight(150); 
    discardView.setFitWidth(250);
    discardView.setPreserveRatio(true);
    String deckPath = "file:assets/UnoCards/none/back.png";
    Image deck = new Image(deckPath);
    deckView = new ImageView(deck);
    deckView.setSmooth(true);
    deckView.setCache(true);
    deckView.setFitHeight(150); 
    deckView.setFitWidth(250);
    deckView.setPreserveRatio(true);
    Button deckButton = new Button();
    deckButton.setGraphic(deckView);
    GridPane grid = new GridPane();
    grid.addColumn(0, discardView);
    grid.addColumn(1, deckButton);
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(150.0);
    return grid;
  }

  private Node makePlayers() {
    BorderPane borderpane = new BorderPane();
    GridPane current = new GridPane();
    current.setHgap(70.0);
    Text indicador = new Text();
    indicador.setFont(new Font(20));
    indicador.setText("Current Player");
    indicador.setStyle("-fx-underline: true;");
    current.addColumn(1, indicador);
    borderpane.setTop(current);
    GridPane players = new GridPane();
    players.setHgap(50.0);
    for (int i = 1; i <= 4; i++) {
      BorderPane player = new BorderPane();
      Text text = new Text();
      text.setFont(new Font(20));
      text.setText("Player " + i);
      player.setTop(text);
      Text cards = new Text();
      cards.setFont(new Font(20));
      cards.setText("x cards");
      player.setBottom(cards);
      players.addColumn(2 * i, player);
      if (i != 4) {
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(new Double[]{
            120.0 + i * 200, 15.0,
            140.0 + i * 200, 25.0,
            120.0 + i * 200, 35.0 });
        players.addColumn(2 * i + 1, polygon);
      }
    }
    borderpane.setBottom(players);
    return borderpane;
  }

  public static void main(String[] args) {
    launch(args);
  }

}
