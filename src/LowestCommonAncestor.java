import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
// Paul Molloy 15323050 based on CS2010 BST assignment 
public class LowestCommonAncestor {

	

//	
//	public static BST lowestCommonAncestor(Node a, Node b) {
//		return new Node(0);
//	}

}
class BST<Key extends Comparable<Key>, Value> {
	private Node root;
	
	private class Node{
		private Node parent;
		private Key key;
		private Value val;
		private Node left, right;
		private int size;
		
		public Node(Key key, Value value, int size){
			this.parent = parent;
			this.val = value;
			this.size = size;
			this.key = key;
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
	
	public BST() {

	}
	
	public boolean isEmpty() {
		return size() ==0;
	}
	public int size() {
		return size(root);
	}
	
	public int size(Node n) {
		if ( n== null) return 0;
		else return n.size;
	}
	
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }
    
    public Value get(Key key) {
    	return get(root, key);
    }
    
    private Value get(Node x, Key key) {
    	if(key == null) throw new IllegalArgumentException("get() on a null key");
    	if(x == null) return null;
    	int cmp = key.compareTo(x.key);
    	if(cmp == 0) return x.val;
    	else if (cmp < 0 ) return get(x.left, key);
    	else 				return get(x.right, key);
    }
    
    public List<Node> getPath(Key key) {
    	List<Node> path = new ArrayList<Node>();
    	return getPath(root, key, path);
    }
    
    private  List<Node> getPath(Node x, Key key, List<Node> path) {
    	if(key == null) throw new IllegalArgumentException("get() on a null key");
    	if(x == null) return null;
    	int cmp = key.compareTo(x.key);
    	if(cmp == 0) {
    		path.add(x);
    		return path;
    	}
    	else if (cmp < 0 ){
    		path.add(x);
    		return getPath(x.left, key, path);
    	}
    	else {
    		path.add(x);
    		return getPath(x.right, key, path);
    	}
    }
    
    
    public void put(Key key, Value val) {

    	if (key == null) throw new IllegalArgumentException("put() on a null key");
    	if (val == null) {
    		//delete(key);
    	}
    	root = put(root, key, val);    	
    }
    private Node put(Node x, Key key, Value val) {
    	if( x == null) return new Node(key, val, 1);
    	int cmp = key.compareTo(x.key);
    	if(cmp < 0) x.left = put(x.left, key, val);
    	else if(cmp > 0) x.right = put(x.right, key, val);
    	else x.val = val;
    	x.size = 1 + size(x.left) + size(x.right);


    	return x;

    }
    
    public String prettyPrint() {
    	return root.prettyPrint();
    }

	public Key lowestCommonAncestor(Key i, Key j) {
		List<Node> iPath = getPath(i);
		List<Node> jPath = getPath(j);
		int posI = 0;
		int posJ = 0;
		boolean foundI = false;
		boolean foundJ = false;
		System.out.println("i: " + i + "j: " + j);

		while((posI < iPath.size() && posJ <jPath.size() && 
				iPath.get(posI).key == jPath.get(posJ).key)
				&& (!(foundI && foundJ))) {
			System.out.println("CURI: " + iPath.get(posI).key);
			System.out.println("CURJ: " + jPath.get(posJ).key);
			System.out.println("FI: " + foundI + "FJ: " + foundJ + "  " + (!foundI || !foundJ));
			if(iPath.get(posI).key!=i) { 
				posI++;
			}else{
				foundI = true;
			}
			if(jPath.get(posJ).key!=j) {
				posJ++;
			}else {
				foundJ = true;
			}
			
		}
		System.out.println("endCURI: " + iPath.get(posI).key);
		System.out.println("endCURJ: " + jPath.get(posJ).key);
		
		System.out.println("Same; " +iPath.get(posI).key == jPath.get(posJ).key);
		System.out.println(iPath.toString());
		System.out.println(jPath.toString());
		if(foundI && foundJ) return iPath.get(posI).key;
		if(posI<posJ && posI>1) return iPath.get(posI-1).key;
		else if ( posJ>1) return jPath.get(posJ-1).key;
		else if (iPath.get(posI).key == jPath.get(posJ).key ) return iPath.get(posI).key;
		else if (posI<iPath.size() && posI>0) return iPath.get(posI-1).key;
		else if (posJ<jPath.size() && posJ>0) return jPath.get(posJ-1).key;

		return null; // The BST can't have unconnected nodes but just in case.
	}

    

}

