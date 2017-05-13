package student;

import game.*;

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
     * Graph Constructor
     *
     * @param  rootNode   The root Node instance
     * @param  targetNode The target Node instance
     */
    public Graph(Node rootNode, Node targetNode) {
        this.rootNode = new GraphNode(rootNode);
        this.targetNode = new GraphNode(targetNode);
    }
}
