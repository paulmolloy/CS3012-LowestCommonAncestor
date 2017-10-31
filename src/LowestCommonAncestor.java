import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
// Paul Molloy 15323050

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
 */

}
class DAG<Key extends Comparable<Key>, Value> {
	private Node root;
	Map<Key, Node> nodes;
	
	private class Node{
		private Key key;
		private Value val;
		Map<Key, Node> parents;
		Map<Key, Node> children;
		
		public Node(Key key, Value value){
			this.val = value;
			this.key = key;
			this.parents = new HashMap<Key, Node>();
			this.children = new HashMap<Key, Node>();

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
    
    /* makeChild makes a directed edge between parentK and childK in the DAG.
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
		markAncestorNodes(marker, nodeI); // 1. Mark all ancestors of i with A
		markCommonAncestorNodes(marker, nodeJ);// 2. Mark all ancestors of j that are ancestors of i too with B

		for(Map.Entry<Node, Character> e : marker.entrySet()){
			if(e.getValue() == 'B'){
				for(Map.Entry<Key, Node> p : e.getKey().parents.entrySet()){
					marker.put(p.getValue(), 'C'); // 3. Mark parent of all Nodes marked B as C
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
	
	// markAncestorNodes marks all ancestors of a node with A in the inputted map.
	private void markAncestorNodes(Map<Node, Character> marker, Node node){
		if(node == null) return;
		if(!marker.containsKey(node)) {
			marker.put(node, 'A');
		}
		for(Map.Entry<Key, Node> e : node.parents.entrySet()){
			markAncestorNodes(marker, e.getValue());
		}
	}
	
	/* markAncestorNodes marks all ancestors of a node that have already been marked a B in the inputed map.
	 * i.e. marks all shared ancestors of the two nodes that are being checked with a B
	 */
	private void markCommonAncestorNodes(Map<Node, Character> marker, Node node){
		if(node == null) return;
		if(marker.containsKey(node) && marker.get(node).charValue()=='A' ) {
			marker.put(node, 'B');
		}
		for(Map.Entry<Key, Node> e : node.parents.entrySet()){
			if(marker.containsKey(e.getValue()) && marker.get(e.getValue()).charValue()=='A'){
				marker.put(e.getValue(), 'B');
			}
			markCommonAncestorNodes(marker, e.getValue());
		}
	}
	
	// printAncestorsList lists the Ancestors that each Node has.
	// used to help understand the structure of the Graph.
	public void printAncestorsList() {
		System.out.println("Ancestors List:" );
		for(Node n: nodes.values()) {
        	System.out.println("Key: " + n.key + " num ancestors: " +  genNumAncestors(n));
		}
	}
	
	private int genNumAncestors(Node n) {
		Map<Key, Node> isVisited = new HashMap<Key, Node>();
		return genNumAncestors(n, isVisited);
		
	}
	private int genNumAncestors(Node n, Map<Key, Node> isVisited) {
		int numAncestors = 0;
		isVisited = new HashMap<Key, Node>();
		for(Node curNode : n.parents.values()) {
			numAncestors++;
			numAncestors += genNumAncestors(curNode);
		}
		return numAncestors;
	}

	

}

