	
public class LowestCommonAncestor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	

	
	public static Node lowestCommonAncestor(Node a, Node b) {
		return new Node(0);
	}

}

class Node{
	Node parent;
	int value;
	
	public Node(int value, Node parent){
		this.parent = parent;
		this.value = value;
	}

	public Node(int value) {
		this.value = value;
	}
}
