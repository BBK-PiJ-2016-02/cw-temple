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
   * Distance between this node and the root node
   */
  private int distance = Integer.MAX_VALUE;

  /**
   * GraphNode Constructor
   *
   * @param  node The underlying Node instance
   */
  public GraphNode(Node node) {
    this.node = node;
  }

  /**
   * Return the unique Identifier of this Node.
   */
  public long getId() {
    return this.node.getId();
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

  /**
   * Get the distance between this node and the root node
   *
   * @return distance
   */
  public int getDistance() {
    return this.distance;
  }

  /**
   * Set the distance between this node and the root node
   *
   * @param distance
   */
  public void setDistance(int distance) {
    this.distance = distance;
  }
}
