package student;

import game.*;
import java.util.*;

public class Escape {

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
    while(!state.getCurrentNode().equals(state.getExit())) {
      lookForGold();

      moveToNeighbour(
        getRandomNeighbour()
      );
    }
  }

  private  Collection<Node> getNeighbours() {
    return state.getCurrentNode().getNeighbours();
  }

  /**
   * Get a random neighbour on the current state
   *
   * @return random neighbour
   */
  private Node getRandomNeighbour() {
    Collection<Node> neighbours = getNeighbours();

    if(!neighbours.isEmpty()) {
      int randomNeighbour = new Random().nextInt(neighbours.size());
      int neighbourI = 0;

      for (Node neighbour : neighbours) {
        if(neighbourI == randomNeighbour) {
          return neighbour;
        }
        neighbourI++;
      }
    }

    throw new IllegalStateException("We are trapped.");
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

  /**
   * Move to specified neighbour
   *
   * @param Node neighbour to move to
   */
  private void moveToNeighbour(Node neighbour) {
    state.moveTo(neighbour);
  }
}
