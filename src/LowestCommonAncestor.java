	
public class LowestCommonAncestor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	

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
    
    private void put(Key key, Value val) {
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
    	x.size = 1 + size(x.left) + size(x.left.right);
    	return x;

    }
}

