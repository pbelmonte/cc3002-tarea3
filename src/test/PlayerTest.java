package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.card.ICardPilesManager;
import model.card.UnoCardPilesManager;
import model.card.deck.NormalUnoDeck;
import model.card.type.ICard;
import model.card.type.NullCard;
import model.player.type.IPlayer;
import model.player.type.RandomPlayer;

public class PlayerTest {
  IPlayer p1;
  IPlayer p2;
  ICardPilesManager manager;

  @Before
  public void setUp() throws Exception {
    p1 = new RandomPlayer("CPU 1");
    p2 = new RandomPlayer("CPU 2");
    manager = new UnoCardPilesManager(new NormalUnoDeck());
  }

  @Test
  public void test() {
    assertTrue(p1.hasWon());
    assertTrue(p1.needsToDrawCard(NullCard.instance()));
    ArrayList<ICard> drawnCards = manager.drawCards(5);
    p1.addToHand(drawnCards);
    assertEquals(drawnCards, p1.getHand());
    assertFalse(p1.hasWon());
    assertEquals(5, p1.getHandSize());
    assertFalse(p1.hasOneCard());
    assertFalse(p1.hasSaidUNO());
    p1.setSaidUNO(true);
    assertTrue(p1.hasSaidUNO());
    drawnCards.remove(0);
    for(ICard card : drawnCards) {
      p1.removeCardFromHand(card);
    }
    assertTrue(p1.hasOneCard());
    p1.getCardFromHand(0);
    assertFalse(p1.hasWon());
    
    assertEquals(NullCard.instance(), p2.getCardFromHand(0));
    assertNotEquals(p1, p2);
  }

}
