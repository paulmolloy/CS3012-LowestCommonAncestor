import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
// Paul Molloy 15323050 based on CS2010 BST assignment 
public class LowestCommonAncestor {

	

/*
 * Modifying to work for Directed Acyclic Graphs
 * LCA(a , b)
 * 
 * 1. Mark all ancestors of a with A
 * 2. Mark all ancestors of b that are ancestors of a too with B
 * 3. Mark parent of all B as C
 * 4. Any node marked B is lowest common ancestor of the two.
 * 
 * 
 * TODO: create addEdge(Key a, Key b)
 * TODO: modify lowestCommonAncestor()
 * 
 */

}
class DAG<Key extends Comparable<Key>, Value> {
	private Node root;
	Map<Key, Node> nodes;
	
	private class Node{
		private Key key;
		private Value val;
		private Node left, right;
		Map<Key, Node> parents;
		Map<Key, Node> children;

		
		public Node(Key key, Value value){
			this.val = value;
			this.key = key;
			this.parents = new HashMap<Key, Node>();
			this.children = new HashMap<Key, Node>();

		}
		
	    public StringBuilder prettyPrint(StringBuilder prefix, boolean isTail, StringBuilder sb) {
	        if(right!=null) {
	            right.prettyPrint(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
	        }
	        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(val.toString()).append("\n");
	        if(left!=null) {
	            left.prettyPrint(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
	        }
	        return sb;
	    }

	    public String prettyPrint() {
	        return this.prettyPrint(new StringBuilder(), true, new StringBuilder()).toString();
	    }

	}
	
	public DAG() {
		this.nodes = new HashMap<Key, Node>();


	}
	
	public Value get(Key k){
		if (!nodes.containsKey(k)) return null;
		return nodes.get(k).val;
	}
	
	public boolean isEmpty() {
		return size() ==0;
	}
	public int size() {
		return nodes.size();
	}
	
	
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return nodes.containsKey(key);
    }
    
    
    /* connect adds a directed edge from a to b
     * 
     */
    private void connect(Node a, Node b){
    	if(a!= null && b != null){
        	a.children.put(b.key, b);
        	b.parents.put(a.key, a);
    	}
    }
   
    /*
     * Parent can be set to null for an unconnected node. If the child key already exists the 
     */
    public void addChildNode(Key parent, Key childK, Value childVal ){
    	Node temp;
    	if(nodes.containsKey(childK)){
    		temp = nodes.get(childK);
    		temp.val = childVal;
    	}
		temp = new Node(childK, childVal);
		nodes.put(childK, temp);
    	if (nodes.containsKey(parent)) connect(nodes.get(parent), temp);
    }
    
    /*
     * Parent can be set to null for an unconnected node. If the child key already exists the 
     */
    public void makeChild(Key parentK, Key childK){
    	if(!nodes.containsKey(childK) || !nodes.containsKey(parentK));
    	connect(nodes.get(parentK), nodes.get(childK));
    }
    

    // lowestCommonAncestor gets the key that is the lowest common ancestor of two given keys
	public Key lowestCommonAncestor(Key i, Key j) {
		if(!contains(i) || !contains(j)) return null;
		
		Map<Node, Character> marker = new HashMap<Node, Character>();
		Node nodeI = nodes.get(i);
		Node nodeJ = nodes.get(j);
		markNodes(marker, nodeI); // 1. Mark all ancestors of a with A
		markNodesB(marker, nodeJ);// 2. Mark all ancestors of b that are ancestors of a too with B
		for(Map.Entry<Node, Character> e : marker.entrySet()){
			System.out.println("Marker: " + e.getKey().val);
			if(e.getValue() == 'B'){
				System.out.println("is B");
				for(Map.Entry<Key, Node> p : e.getKey().parents.entrySet()){
					marker.put(p.getValue(), 'C'); // 3. Mark parent of all B as C
					System.out.println("Adding C: " + p.getValue().key);
				}
			}
		}
		for(Map.Entry<Node, Character> e : marker.entrySet()){
			if(e.getValue().charValue() == 'B'){
				return e.getKey().key; // 4. Any node marked B is lowest common ancestor of the two.
			}
		}
		return null;
	}
	
	private void markNodes(Map<Node, Character> marker, Node node){
		if(node == null) return;
		marker.put(node, 'A');
		System.out.println("Adding A: " + node.key);

		for(Map.Entry<Key, Node> e : node.parents.entrySet()){
			markNodes(marker, e.getValue());
		}
	}
	
	private void markNodesB(Map<Node, Character> marker, Node node){
		if(node == null) return;
		for(Map.Entry<Key, Node> e : node.parents.entrySet()){
			System.out.println("Marker mark bs: " + e.getValue().key);
			if(marker.containsKey(e.getValue()) ){
				System.out.println("Marker mark is A: " +( marker.get(e.getValue()).charValue()=='A'));
				if(marker.get(e.getValue()).charValue()=='A') {
					System.out.println("Adding B: " + node.key);

					marker.put(node, 'B');

				}
			}


			markNodes(marker, e.getValue());
		}
	}

    

}

