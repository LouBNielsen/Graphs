/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.algorithm.examples.graphs;

import dk.cphbusiness.algorithm.examples.graphs.Graph.Edge;
import dk.cphbusiness.algorithm.examples.graphs.Graph.Vertex;
import dk.cphbusiness.algorithm.examples.queues.LinkedQueue;
import dk.cphbusiness.algorithm.examples.queues.Queue;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class Graphs {
    public static void main(String[] args) {
        LinkedGraph<String,Integer> friends = new LinkedGraph<>(
        "Jonnas",
        "Lily",
        "Carol",
        "Adam",
        "Phillip",
        "Mark",
        "Laura",
        "Peter",
        "Jack",
        "Jacob",
        "Emma",
        "Emil",
        "Mathias",
        "Mikkel"
        );
    friends.addEdge(1, "Lily", "Jack", true);
    friends.addEdge(1, "Peter", "Jack", true);
    friends.addEdge(1, "Emil", "Jack", true);
    friends.addEdge(1, "Carol", "Jack", true);
    friends.addEdge(1, "Mathias", "Jack", true);
    friends.addEdge(1, "Jacob", "Jack", true);
    friends.addEdge(1, "Carol", "Adam", true);
    friends.addEdge(1, "Carol", "Jonnas", true);
    friends.addEdge(1, "Carol", "Laura", true);
    friends.addEdge(1, "Jacob", "Laura", true);
    friends.addEdge(1, "Jacob", "Emma", true);
    friends.addEdge(1, "Jacob", "Mikkel", true);
    friends.addEdge(1, "Jacob", "Mathias", true);
    friends.addEdge(1, "Emma", "Laura", true);
    friends.addEdge(1, "Jonnas", "Adam", true);
    friends.addEdge(1, "Phillip", "Adam", true);
    friends.addEdge(1, "Phillip", "Mark", true);
    friends.addEdge(1, "Mikkel", "Mark", true);
    Vertex root= friends.vertexOf("Emma");
    RootedTree tree= depthFirst(friends, root);
    }

    public static <D, W> RootedTree<D, W> depthFirst(Graph<D, W> graph, Vertex<D, W> root) {
        Set<Vertex<D, W>> marked = new HashSet<>();
        RootedTree<D, W> edgesTo = new SimpleRootedTree<>(graph, root);
        Deque<Vertex<D, W>> stack = new ArrayDeque<>();
        
        stack.push(root);
        marked.add(root);
        
        while(!stack.isEmpty())
        {
            Collection tempEdges = edgesTo.getEdgesFrom(stack.poll());
            for (int i = 0; i < tempEdges.size(); i++) {
                Edge<D, W> edge = (Edge<D, W>) tempEdges.iterator().next();
                Vertex<D, W> vertex = edge.getHeadVertex();
                if (!marked.contains(vertex)){
                    marked.add(vertex);
                    stack.add(vertex);
                }
            }
        }

        return edgesTo;
    }

    public static <D, W> RootedTree<D, W> breadthFirst(Graph<D, W> graph, Vertex<D, W> root) {
        Set<Vertex<D, W>> marked = new HashSet<>();
        Queue<Vertex<D, W>> queue = new LinkedQueue<>();
        RootedTree<D, W> edgesTo = new SimpleRootedTree<>(graph, root);

        queue.enqueue(root); //tilføjer startnoden til queuen
        marked.add(root); //markerer at vi har været på noden

        while (!queue.isEmpty()) {
            Collection tempEdges = edgesTo.getEdgesFrom(queue.dequeue()); //collection af den node vi står på's edges - samtidig fjernes noden fra queuen
            for (int i = 0; i < tempEdges.size(); i++) { //kører nodens edges igennem
                Edge<D, W> edge = (Edge<D, W>) tempEdges.iterator().next(); //finde én edge
                Vertex<D, W> vertex = edge.getHeadVertex(); //finder vi edgens hovedVertex/noden i den anden ende
                if (!marked.contains(vertex)) { //hvis noden ikke allerede har været tilføjet til queuen -->
                    marked.add(vertex); //markerer at vi har været på noden
                    queue.enqueue(vertex); // bliver noden så tilføjet til queuen
                }
            }
        }

        return edgesTo;
        
    }
}
