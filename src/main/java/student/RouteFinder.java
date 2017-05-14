package student;

import game.Node;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class RouteFinder {

  /**
   * The source Node Id.
   */
  private Long sourceNodeId;

  /**
   * The target Node Id.
   */
  private Long targetNodeId;

  /**
   * Graph instance containing the nodes and links.
   */
  private Graph graph;

  /**
   * Graph Constructor.
   *
   * @param  sourceNode   The source Node instance
   * @param  targetNode The target Node instance
   * @param  allNodes   All Node instances in graph
   */
  public RouteFinder(Node sourceNode, Node targetNode, Collection<Node> allNodes) {
    this.graph = new Graph(allNodes);
    this.sourceNodeId = sourceNode.getId();
    this.targetNodeId = targetNode.getId();
  }

  /**
   * Get the shortest route to the exit using Dijkstra's Algorithm.
   *
   * @return shortest route
   */
  public Stack<GraphNode> getShortestRoute() {

    // Nodes to visit
    Set<GraphNode> visitQueue = new HashSet<>();
    // Nodes that have been visited
    Set<GraphNode> visitedNodes = new HashSet<>();

    // Start with the source
    GraphNode sourceNode = this.graph.getNodeById(this.sourceNodeId);
    sourceNode.setDistance(0);
    visitQueue.add(sourceNode);

    // Visit nodes
    do {
      GraphNode node = this.graph.getClosestNode(visitQueue);
      visitedNodes.add(node);
      visitQueue.remove(node);

      // Distance between nodes is hardcoded to 1, as we're working with a uniform grid
      Integer distance = node.getDistance() + 1;

      for (GraphNode childNode : node.getChildNodes()) {
        // Skip visited nodes
        if (!visitedNodes.contains(childNode)) {

          // Have we found a shorter route to this particular node?
          if (childNode.getDistance() > distance) {
            childNode.setDistance(distance);

            // Queue up to check/recheck the children of this childNode
            visitQueue.add(childNode);
          }
        }
      }
    } while (!visitQueue.isEmpty());

    // Calculate shortest path
    Stack<GraphNode> route = new Stack<>();
    GraphNode currentNode = this.graph.getNodeById(this.targetNodeId);

    do {
      route.push(currentNode);

      currentNode = this.graph.getClosestNode(
        this.graph.getNodeNeighbours(currentNode)
        );

      if (currentNode == null) {
        throw new IllegalStateException("Cannot locate path to target node");
      }

    } while (currentNode.getId() != this.sourceNodeId);

    return route;
  }
}
