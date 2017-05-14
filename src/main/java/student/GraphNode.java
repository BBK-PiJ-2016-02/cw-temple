package student;

import game.*;
import java.util.*;

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
   * Child nodes of this node
   */
  private Map<Long, GraphNode> childNodes;

  /**
   * GraphNode Constructor
   *
   * @param  node The underlying Node instance
   */
  public GraphNode(Node node) {
    this.node = node;
    this.childNodes = new HashMap<>();
  }

  /**
   * Return the unique Identifier of this Node.
   */
  public long getId() {
    return this.node.getId();
  }

  /**
   * Return the underlying Node instance
   *
   * @return node
   */
  public Node getNode() {
    return this.node;
  }

  /**
   * Returns neighbours from the underlying Node instance
   *
   * @return Collection of neighbour nodes
   */
  public Collection<Node> getNeighbours() {
    return this.node.getNeighbours();
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

  /**
   * Attach a child node
   *
   * @param node to attach
   */
  public void addChildNode(GraphNode node) {
    this.childNodes.put(node.getId(), node);
  }

  /**
   * Get all child nodes attached to this node
   *
   * @return collection of child nodes
   */
  public Collection<GraphNode> getChildNodes() {
    Collection<GraphNode> childNodes = new ArrayList<>(
      this.childNodes.values()
    );

    return childNodes;
  }
}
