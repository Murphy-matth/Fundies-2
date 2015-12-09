package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import tester.Tester;

public abstract class search<T> {
    T state;
    T goal;
    
    search(T state,  T goal, Comparator<T> comp) { 
      this.state = state;
      this.goal = goal;
    }
    
    public abstract Pair<T, ArrayList<T>> sucessor();
    

    public ArrayList<T> treeSearch(Comparator<Pair<T, ArrayList<T>>> comp) {
      PriorityQueue<Pair<T, ArrayList<T>>> searchTree = new PriorityQueue<Pair<T, ArrayList<T>>>(0, comp);
      searchTree.add(new Pair<T, ArrayList<T>>(this.state, new ArrayList<T>(Arrays.asList(this.state))));
      Pair<T, ArrayList<T>> elem;
      searchTree.add(this.sucessor());
      while(!searchTree.isEmpty()) {
        elem = searchTree.poll();
        if (elem.first() == this.goal) {
          return new ArrayList<T>(elem.second());
        }
        searchTree.add(this.sucessor());
      }
      return new ArrayList<T>();
    }
   
}

class DFS<T> extends search<T> {
    
    DFS(T state, T goal, Comparator<T> comp) {
      super(state, goal, comp);
    }
    
    public Pair<T, ArrayList<T>> sucessor() {
      return new Pair<T, ArrayList<T>>(this.state,new ArrayList<T>(Arrays.asList(this.state)));
    }
}

class BFS<T> extends search<T> {
    
    BFS(T state, T goal, Comparator<T> comp) {
      super(state, goal, comp);
    }
    
    public Pair<T, ArrayList<T>> sucessor() {
      return new Pair<T, ArrayList<T>>(this.state,new ArrayList<T>(Arrays.asList(this.state)));
    }
}

class ExampleSearch {
    
    ExampleSearch() {}
    
    
}


class Pair<T,X> {
    T first;
    X second;
    
    Pair(T first, X second) {
      this.first = first;
      this.second = second;
    }
    
    public T first() {
      return this.first;
    }
    
    public X second() {
      return this.second;
    }
}



