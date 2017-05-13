package student;

import game.*;

public class GraphNode {

  /**
   * The underlying Node instance
   */
  private Node node;

  /**
   * Has this node been visited
   */
  private boolean isVisited = false;

  /**
   * GraphNode Constructor
   *
   * @param  node The underlying Node instance
   */
  public GraphNode(Node node) {
    this.node = node;
  }

  /**
   * Mark the node as visited
   */
  public void markVisited() {
    this.isVisited = true;
  }

  /**
   * Has the node been visited
   */
  public boolean isVisited() {
    return this.isVisited;
  }
}
