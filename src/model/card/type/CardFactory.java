package model.card.type;

import java.util.HashMap;

public class CardFactory {
  private HashMap<Symbol, ICardBuilder> map;
  private static CardFactory instance;
  
  private CardFactory() {
    map = new HashMap<Symbol, ICardBuilder>();
    for(Symbol symbol : Symbol.getNumeric()) {
      map.put(symbol, new NumberCardBuilder());
    }
    map.put(Symbol.SKIP, new SkipCardBuilder());
    map.put(Symbol.INVERT, new InvertCardBuilder());
    map.put(Symbol.DRAW_TWO, new DrawTwoCardBuilder());
    map.put(Symbol.WILD, new WildCardBuilder());
    map.put(Symbol.WILD_DRAW_FOUR, new WildDrawFourBuilder());
    map.put(Symbol.USED_WILD_CARD, new UsedWildCardBuilder());
    map.put(Symbol.NONE, new NullCardBuilder());
  }
  
  public static CardFactory instance() {
    if(instance == null) instance = new CardFactory();
    return instance;
  }
  
  public static ICard createCard(Color aColor, Symbol aSymbol) {
    return instance().map.get(aSymbol).buildCard(aColor, aSymbol);
  }
}
