package model.player.type;

import controller.IController;
import model.IGameLogic;
import model.card.type.Color;
import model.card.type.ICard;

/**
 * Class that relies on chance to retrieve the correct card from the hand.
 * 
 * @author danno
 *
 */
public class RandomPlayer extends AbstractPlayer {

  /**
   * Initializes this player with the given name.
   * 
   * @param name name to give this player.
   */
  public RandomPlayer(String name) {
    super(name);
  }

  @Override
  public ICard getCardToPlay(IGameLogic game, IController ctrl) {
    if (needsToDrawCard(game.getCurrentPlayedCard())) {
      return game.drawOneCard(this);
    } else {
      return getCardFromHand((int) (Math.random() * getHandSize()));
    }
  }

  @Override
  public Color selectColor(IGameLogic game, IController ctrl) {
    return Color.getColors()[(int) (Math.random() * 4)];
  }
}
