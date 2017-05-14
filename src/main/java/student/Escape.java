package student;

import game.*;
import java.util.*;

public class Escape {

  /**
   * Contains the current game state
   */
  private EscapeState state;

  /**
   * Init Escape instance
   *
   * @param  state Starting state to explore from
   */
  public Escape(EscapeState state) {
    this.state = state;
  }

  /**
   * Escape the cavern
   */
  public void go() {
    RouteFinder routeFinder = new RouteFinder(
      state.getCurrentNode(),
      state.getExit(),
      state.getVertices()
    );

    Stack<GraphNode> shortestRoute = routeFinder.getShortestRoute();
    GraphNode nextGraphNode;

    while(!state.getCurrentNode().equals(state.getExit())) {
      lookForGold();

      nextGraphNode = shortestRoute.pop();
      state.moveTo(nextGraphNode.getNode());
    }
  }

  /**
   * Looks for (and takes) any gold on the current node
   */
  private void lookForGold() {
    Tile tile = state.getCurrentNode().getTile();

    if(tile.getGold() > 0) {
      state.pickUpGold();
    }
  }
}
