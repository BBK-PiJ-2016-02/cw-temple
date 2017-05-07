package student;

import game.*;
import java.util.*;

public class Explore {

  ExplorationState state;

  /**
   * Init Explore instance
   *
   * @param  state Starting state to explore from
   */
  public Explore(ExplorationState state) {
    this.state = state;
  }

  /**
   * Explore the cavern
   */
  public void go() {
    while(state.getDistanceToTarget() != 0) {
      goToNeighbour(
        getRandomNeighbour()
      );
    }
  }

  /**
   * Get a random neighbour on the current state
   *
   * @return random neighbour
   */
  public NodeStatus getRandomNeighbour() {
    Collection<NodeStatus> neighbours = state.getNeighbours();

    if(!neighbours.isEmpty()) {
      int randomNeighbour = new Random().nextInt(neighbours.size());
      int neighbourI = 0;

      for (NodeStatus neighbour : neighbours) {
        if(neighbourI == randomNeighbour) {
          return neighbour;
        }
        neighbourI++;
      }
    }

    throw new IllegalStateException("We are trapped.");
  }

  /**
   * Go to specified neighbour
   *
   * @param NodeStatus neighbour to go to
   */
  private void goToNeighbour(NodeStatus neighbour) {
    state.moveTo(neighbour.getId());
  }
}
