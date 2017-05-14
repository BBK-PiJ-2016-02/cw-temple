package student;

import game.Node;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Graph {

    /**
     * All nodes
     */
    private Map<Long, GraphNode> nodes;

    /**
     * Graph Constructor
     *
     * @param  sourceNode   The source Node instance
     * @param  targetNode The target Node instance
     * @param  nodes   All Node instances in graph
     */
    public Graph(Collection<Node> nodes) {
        this.nodes = new HashMap<>();
        this.buildGraph(nodes);
    }

    /**
     * Retrieve a particular node via ID
     *
     * @param  id The ID of the desired node
     *
     * @return The node instance
     */
    public GraphNode getNodeById(Long id) {
        return this.nodes.get(id);
    }

    /**
     * Returns the neighbours of the GraphNode by looking at the underlying Node instance
     *
     * @param  node The GraphNode to insspect
     *
     * @return The neighbours of the node
     */
    public Collection<GraphNode> getNodeNeighbours(GraphNode node) {
        Collection<GraphNode> neighbours = new ArrayList<>();

        for(Node neighbour : node.getNeighbours()) {
            neighbours.add(
                this.nodes.get(neighbour.getId())
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
    public GraphNode getClosestNode(Collection<GraphNode> nodes) {
        GraphNode closestNode = null;

        for(GraphNode node : nodes) {
            if(closestNode == null || closestNode.getDistance() > node.getDistance()) {
                closestNode = node;
            }
        }

        return closestNode;
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
            this.nodes.put(graphNode.getId(), graphNode);
        }

        // Link the children of each GraphNode to it's parent
        for(GraphNode node : this.nodes.values()) {
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
            GraphNode childNode = this.nodes.get(neighbour.getId());
            node.addChildNode(childNode);
        }
    }
}
