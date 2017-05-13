package student;

import game.*;
import java.util.*;

public class Explore {

  /**
   * Contains the current game state
   */
  private ExplorationState state;

  /**
   * Keeps track of all visited nodes
   */
  private HashSet<NodeStatus> visitedNodes;

  /**
   * Keeps track of current path
   */
  private Stack<NodeStatus> pathHistory;

  /**
   * Init Explore instance
   *
   * @param  state Starting state to explore from
   */
  public Explore(ExplorationState state) {
    this.state = state;
    this.visitedNodes = new HashSet<>();
    this.pathHistory = new Stack<>();
  }

  /**
   * Explore the cavern
   */
  public void go() {
    NodeStatus targetNode;

    while(state.getDistanceToTarget() != 0) {
      targetNode = getRandomUnvisitedNeighbour();

      if(targetNode == null) {
        targetNode = backtrackToUnvisitedNeighbour();
      }

      moveToNeighbour(targetNode);
    }
  }

  /**
   * Get a random neighbour on the current state that we haven't yet visited
   *
   * @return random unvisited neighbour or null if there aren't any
   */
  private NodeStatus getRandomUnvisitedNeighbour() {
    Collection<NodeStatus> neighbours = state.getNeighbours();

    // Filter out visited neighbours
    neighbours.removeIf(
      (NodeStatus neighbour) -> this.visitedNodes.contains(neighbour)
    );

    return randomNeighbour(neighbours);
  }

  /**
   * Backtrack until we get to a tile with an unvisited neighbour
   *
   * @return random unvisited neighbour
   */
  private NodeStatus backtrackToUnvisitedNeighbour() {
    NodeStatus previousNode = this.pathHistory.pop();
    NodeStatus unvisitedNeighbour = null;

    do {
      previousNode = this.pathHistory.pop();
      this.state.moveTo(previousNode.getId());
      unvisitedNeighbour = getRandomUnvisitedNeighbour();
    } while(unvisitedNeighbour == null);

    // Restore the last node, as we're sticking put for the mean time
    this.pathHistory.push(previousNode);

    return unvisitedNeighbour;
  }

  /**
   * Move to specified neighbour
   *
   * @param NodeStatus neighbour to move to
   */
  private void moveToNeighbour(NodeStatus neighbour) {
    state.moveTo(neighbour.getId());
    this.visitedNodes.add(neighbour);
    this.pathHistory.push(neighbour);
  }

  /**
   * Returns a random neighbour from the provided collection
   *
   * @param  neighbours Collection of neighbours to select from
   *
   * @return a random neighbour from the provided collection
   */
  private NodeStatus randomNeighbour(Collection<NodeStatus> neighbours) {
    if (!neighbours.isEmpty()) {
      int randomNeighbour = new Random().nextInt(neighbours.size());
      int neighbourI = 0;

      for (NodeStatus neighbour : neighbours) {
        if(neighbourI == randomNeighbour) {
          return neighbour;
        }
        neighbourI++;
      }
    }

    return null;
  }
}
