package student;

import game.*;
import java.util.*;

public class Escape {

  EscapeState state;

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

      goToNeighbour(
        getRandomNeighbour()
      );
    }
  }

  public Collection<Node> getNeighbours() {
    return state.getCurrentNode().getNeighbours();
  }

  /**
   * Get a random neighbour on the current state
   *
   * @return random neighbour
   */
  public Node getRandomNeighbour() {
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
  public void lookForGold() {
    Tile tile = state.getCurrentNode().getTile();

    if(tile.getGold() > 0) {
      state.pickUpGold();
    }
  }

  /**
   * Go to specified neighbour
   *
   * @param Node neighbour to go to
   */
  private void goToNeighbour(Node neighbour) {
    state.moveTo(neighbour);
  }
}
