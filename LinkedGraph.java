/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.algorithm.examples.graphs;

import dk.cphbusiness.algorithm.examples.graphs.Graph.Edge;
import dk.cphbusiness.algorithm.examples.graphs.Graph.Vertex;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author LouiseB
 */
public class LinkedGraph<D, W> implements Graph<D, W> {

    private Collection<Vertex<D, W>> vertices = new ArrayList(); //liste af noder, liste af fx personer

    public LinkedGraph(D... data) { //constructor, som når man kalader constructoren, så laves der en liste af vertices
        addVertex(data);
    }

    @Override
    public final void addVertex(D... data) { //
        for (D d : data) {
            vertices.add(new LinkedVertex(d)); //LinkedVertex kender til edges, forholdene mellem nodes, altså personerne
        }
    }

    @Override
    public void addEdge(W weight, Vertex<D, W> tail, Vertex<D, W> head, boolean directed) { //tilføjer et forhold mellem to nodes
        Edge<D, W> edge = new LinkedEdge(weight, head);
        LinkedVertex t = (LinkedVertex) tail;
        t.addEdge(edge);
        if (directed) {
            addEdge(weight, head, tail, false);
        }
    }

    @Override
    public Vertex<D, W> vertexOf(D data) { //finder en nodes tilhørende node - dem der har en edge imellem sig
        for (Vertex<D, W> v : vertices) {
            if (v.getData().equals(data)) {
                return v;
            }
        }
        return null;
    }

    @Override
    public Collection<Vertex<D, W>> getVertices() {
        return vertices;
    }

    @Override
    public Collection<Edge<D, W>> getEdges() { //returnerer en liste af edges
        List<Edge<D, W>> edges = new ArrayList();

        for (Vertex<D, W> v : vertices) {
            for (Edge<D, W> e : v.getAdjacentEdges()) {
                edges.add(e);
            }
        }
        return edges;
    }

    @Override
    public Collection<Edge<D, W>> getEdgesFrom(Vertex<D, W> vertex) {
        return vertex.getAdjacentEdges();
    }

    private class LinkedVertex implements Vertex<D, W> { //kendskab til edges

        private D data;
        private Collection<Edge<D, W>> adjacentEdges = new LinkedList();

        public LinkedVertex(D data) {
            this.data = data;
        }

        @Override
        public D getData() {
            return data;
        }

        @Override
        public Collection<Edge<D, W>> getAdjacentEdges() {
            return adjacentEdges;
        }

        public void addEdge(Edge<D, W> edge) {
            adjacentEdges.add(edge);
        }
    }

    private class LinkedEdge implements Edge<D, W> { //omhandler / detaljer om egde

        private W weight;
        private Vertex<D, W> headVertex;

        public LinkedEdge(W weight, Vertex<D, W> headVertex) {
            this.weight = weight;
            this.headVertex = headVertex;
        }

        @Override
        public W getWeight() {
            return weight;
        }

        @Override
        public Vertex<D, W> getHeadVertex() {
            return headVertex;
        }

    }

}
