package student;

import game.*;
import java.util.*;

public class Graph {

    /**
     * The source Node Id
     */
    private Long sourceNodeId;

    /**
     * The target Node Id
     */
    private Long targetNodeId;

    /**
     * All nodes
     */
    private Map<Long, GraphNode> allNodes;

    /**
     * Graph Constructor
     *
     * @param  sourceNode   The source Node instance
     * @param  targetNode The target Node instance
     * @param  allNodes   All Node instances in graph
     */
    public Graph(Node sourceNode, Node targetNode, Collection<Node> allNodes) {
        this.sourceNodeId = sourceNode.getId();
        this.targetNodeId = targetNode.getId();
        this.allNodes = new HashMap<>();
        this.buildGraph(allNodes);
    }

    /**
     * Constructs a graph
     *
     * @param nodes All the nodes in the graph
     */
    private void buildGraph(Collection<Node> nodes) {

        // Create and store a GraphNode for all nodes
        for(Node node : nodes) {
            GraphNode graphNode = new GraphNode(node);
            this.allNodes.put(graphNode.getId(), graphNode);
        }

        // Link the children of each GraphNode to it's parent
        for(GraphNode node : this.allNodes.values()) {
            this.linkChildren(node);
        }
    }

    /**
     * Find and link the children of a GraphNode
     *
     * @param node The GraphNode that is looking for it's children
     */
    private void linkChildren(GraphNode node) {
        Collection<Node> neighbours = node.getNeighbours();
        for(Node neighbour : neighbours) {
            GraphNode childNode = this.allNodes.get(neighbour.getId());
            node.addChildNode(childNode);
        }
    }

    /**
     * Get the shortest route to the exit using Dijkstra's Algorithm
     *
     * @return shortest route
     */
    public Stack<GraphNode> getShortestRoute() {

        // Nodes to visit
        Set<GraphNode> visitQueue = new HashSet<>();
        // Nodes that have been visited
        Set<GraphNode> visitedNodes = new HashSet<>();

        // Start with the source
        GraphNode sourceNode = this.allNodes.get(this.sourceNodeId);
        sourceNode.setDistance(0);
        visitQueue.add(sourceNode);

        // Visit nodes
        do {
            GraphNode node = this.getClosestNode(visitQueue);
            visitedNodes.add(node);
            visitQueue.remove(node);

            // Distance between nodes is hardcoded to 1, as we're working with a uniform grid
            Integer distance = node.getDistance() + 1;

            for(GraphNode childNode : node.getChildNodes()) {
                // Skip visited nodes
                if(!visitedNodes.contains(childNode)) {

                    // Have we found a shorter route to this particular node?
                    if(childNode.getDistance() > distance) {
                        childNode.setDistance(distance);

                        // Queue up to check/recheck the children of this childNode
                        visitQueue.add(childNode);
                    }
                }
            }
        } while (!visitQueue.isEmpty());

        // Calculate shortest path
        Stack<GraphNode> route = new Stack<>();
        GraphNode currentNode = this.allNodes.get(this.targetNodeId);

        do {
            route.push(currentNode);

            currentNode = this.getClosestNode(
                this.getNeighbours(currentNode)
            );

            if(currentNode == null) {
                throw new IllegalStateException("Cannot locate path to target node");
            }

        } while(currentNode.getId() != this.sourceNodeId);

        return route;
    }

    /**
     * Returns the neighbours of the GraphNode by looking at the underlying Node instance
     *
     * @param  node The GraphNode to insspect
     *
     * @return The neighbours of the node
     */
    private Collection<GraphNode> getNeighbours(GraphNode node) {
        Collection<GraphNode> neighbours = new ArrayList<>();

        for(Node neighbour : node.getNeighbours()) {
            neighbours.add(
                this.allNodes.get(neighbour.getId())
            );
        }

        return neighbours;
    }

    /**
     * Returns the GraphNode with the lowest distance unit
     *
     * @param  nodes GraphNodes to filter for the lowest distance unit
     *
     * @return closest node
     */
    private GraphNode getClosestNode(Collection<GraphNode> nodes) {
        GraphNode closestNode = null;

        for(GraphNode node : nodes) {
            if(closestNode == null || closestNode.getDistance() > node.getDistance()) {
                closestNode = node;
            }
        }

        return closestNode;
    }
}
