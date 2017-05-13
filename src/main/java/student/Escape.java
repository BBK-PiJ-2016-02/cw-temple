package student;

import game.*;
import java.util.*;

public class Escape {

  /**
   * Contains the current game state
   */
  private EscapeState state;

  /**
   * Keeps track of all visited nodes
   */
  private HashSet<Node> visitedNodes;

  /**
   * Keeps track of current path
   */
  private Stack<Node> pathHistory;

  /**
   * Init Escape instance
   *
   * @param  state Starting state to explore from
   */
  public Escape(EscapeState state) {
    this.state = state;
    this.visitedNodes = new HashSet<>();
    this.pathHistory = new Stack<>();
  }

  /**
   * Escape the cavern
   */
  public void go() {
    Node targetNode;

    while(!state.getCurrentNode().equals(state.getExit())) {
      lookForGold();

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
  private Node getRandomUnvisitedNeighbour() {
    Collection<Node> neighbours = this.getNeighbours();

    // Filter out visited neighbours
    neighbours.removeIf(
      (Node neighbour) -> this.visitedNodes.contains(neighbour)
    );

    return randomNeighbour(neighbours);
  }

  /**
   * Backtrack until we get to a tile with an unvisited neighbour
   *
   * @return random unvisited neighbour
   */
  private Node backtrackToUnvisitedNeighbour() {
    Node unvisitedNeighbour = null;

    do {
      this.state.moveTo(this.pathHistory.pop());
      unvisitedNeighbour = getRandomUnvisitedNeighbour();
    } while(unvisitedNeighbour == null);

    return unvisitedNeighbour;
  }

  /**
   * Retrieve a copy of the neightbours on the current node
   *
   * @return neighbours for the current node
   */
  private Collection<Node> getNeighbours() {
    Collection<Node> neighbours = new ArrayList<>(
        state.getCurrentNode().getNeighbours()
    );

    return neighbours;
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
    this.pathHistory.push(this.state.getCurrentNode());
    state.moveTo(neighbour);
    this.visitedNodes.add(neighbour);
  }

  /**
   * Returns a random neighbour from the provided collection
   *
   * @param  neighbours Collection of neighbours to select from
   *
   * @return a random neighbour from the provided collection
   */
  private Node randomNeighbour(Collection<Node> neighbours) {
    if (!neighbours.isEmpty()) {
      int randomNeighbour = new Random().nextInt(neighbours.size());
      int neighbourI = 0;

      for (Node neighbour : neighbours) {
        if(neighbourI == randomNeighbour) {
          return neighbour;
        }
        neighbourI++;
      }
    }

    return null;
  }
}
