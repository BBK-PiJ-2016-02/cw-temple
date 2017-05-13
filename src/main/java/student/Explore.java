package student;

import game.*;
import java.util.*;

public class Explore {

  private ExplorationState state;

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
      moveToNeighbour(
        getRandomNeighbour()
      );
    }
  }

  /**
   * Get a random neighbour on the current state
   *
   * @return random neighbour
   */
  private NodeStatus getRandomNeighbour() {
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
   * Move to specified neighbour
   *
   * @param NodeStatus neighbour to move to
   */
  private void moveToNeighbour(NodeStatus neighbour) {
    state.moveTo(neighbour.getId());
  }
}
