/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.algorithm.examples.graphs;

/**
 *
 * @author LouiseB
 */
public interface RootedTree<D,W> extends Graph<D,W> {
  Vertex<D,W> getRoot();
  void add(Edge<D,W> edge);
    
}
