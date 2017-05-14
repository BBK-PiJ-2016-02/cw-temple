package student;

import game.*;
import java.util.*;

public class Graph {

    /**
     * The root GraphNode
     */
    private GraphNode rootNode;

    /**
     * The target Node
     */
    private GraphNode targetNode;

    /**
     * Nodes we have visited
     */
    private Map<Long, GraphNode> visitedNodes;

    /**
     * Graph Constructor
     *
     * @param  rootNode   The root Node instance
     * @param  targetNode The target Node instance
     */
    public Graph(Node rootNode, Node targetNode) {
        this.rootNode = new GraphNode(rootNode);
        this.targetNode = new GraphNode(targetNode);
        this.visitedNodes = new HashMap<>();

        this.targetNode.setDistance(0);
        this.buildGraph(this.targetNode);
        // this.calculateShortestRoute();
    }

    /**
     * Constructs a graph implementing Dijkstra's Algorithm
     *
     * @param startNode GraphNode to start building from
     */
    private void buildGraph(GraphNode startNode) {

        if(!startNode.isVisited()) {
            // Build up child GraphNodes on startNode
            Collection<Node> neighbours = startNode.getNeighbours();

            for (Node neighbour : neighbours) {
                GraphNode nextNode = this.visitNode(neighbour);
                startNode.addChildNode(nextNode);
            }

            startNode.markVisited();
        }

        // Distance between nodes is hardcoded to 1, as we're working with a uniform grid
        Integer distance = startNode.getDistance() + 1;
        Collection<GraphNode> childNodes = startNode.getChildNodes();

        // Update the shortest distance for each child node
        for (GraphNode childNode : childNodes) {
            if(distance < childNode.getDistance()) {
                childNode.setDistance(distance);
            }

            buildGraph(childNode);
        }

        this.visitedNodes.put(startNode.getId(), startNode);
    }

    /**
     * Visits a node, returns existing associated GraphNode or creates a new one
     *
     * @param  node Node to visit
     *
     * @return Associated GraphNode for Node instance
     */
    private GraphNode visitNode(Node node) {
        Long nodeId = node.getId();

        if(this.visitedNodes.containsKey(nodeId)) {
            return this.visitedNodes.get(nodeId);
        }

        GraphNode graphNode = new GraphNode(node);
        this.visitedNodes.put(nodeId, graphNode);

        return graphNode;
    }

    /**
     * Get the shortest route to the exit
     *
     * @return shortest route
     */
    public Stack<GraphNode> getShortestRoute() {
        Stack<GraphNode> route = new Stack<>();
        GraphNode currentNode = this.rootNode;
        Collection<GraphNode> childNodes;

        do {
            childNodes = currentNode.getChildNodes();
            currentNode = null;

            for (GraphNode childNode : childNodes) {
                if(
                    currentNode != null &&
                    childNode.getDistance() < currentNode.getDistance()
                ) {
                    currentNode = childNode;
                }
            }

            if(currentNode == null) {
                throw new IllegalStateException("Cannot locate path to target node");
            }

            route.push(currentNode);
        } while (!currentNode.equals(this.targetNode));

        return route;
    }

    // private void calculateShortestRoute() {

    // }
}
