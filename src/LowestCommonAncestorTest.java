import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.junit.Test;

public class LowestCommonAncestorTest {

	@Test
	public void testBST() {
		BST<Integer, Integer> tree = new BST<Integer, Integer>();
		tree.put(10, 10);
		tree.put(5, 5);
		tree.put(2, 2);
		tree.put(20,20);
		Integer expectedResult = 5;
		System.out.println(tree.get(5));
		assertEquals("Check get 5", expectedResult, tree.get(5) );
		expectedResult = null;
		System.out.println(tree.get(19));
		assertEquals("Check get 19 not added", expectedResult, tree.get(19) );
		expectedResult = 20;
		System.out.println(tree.get(20));
		assertEquals("Check get 20", expectedResult, tree.get(20) );
		System.out.println(tree.prettyPrint());
	}
	@Test
	public void testLowestCommonAncestor() {
//		Node expectedResult = null;
//		assertEquals("Both null", expectedResult, LowestCommonAncestor.lowestCommonAncestor(null, null));
//		Node n = new Node(0);
//		assertEquals("One null", expectedResult, LowestCommonAncestor.lowestCommonAncestor(n, null));
//		Node o = new Node(1, n);
//		expectedResult = n;
//		assertEquals("One link", expectedResult, LowestCommonAncestor.lowestCommonAncestor(n, o));
//		
//		/**
//		 * 					 0				7
//		 * 				/	  \	  \
//		 * 			   1	   2   3
//		 * 					  /  \
//		 * 					 6    4
//		 * 						   \
//		 * 							 5
//		 * 
//		 */
//		Node t = new Node(2,n);		
//		
//		Node u = new Node(3,n);
//		Node v = new Node(4,t);
//		Node w = new Node(5, v);
//		Node x = new Node(6, t);
//		Node y = new Node(7, null);
//		expectedResult = t;
//		assertEquals("2 up right 1 up left", expectedResult, LowestCommonAncestor.lowestCommonAncestor(x, w));
//		expectedResult = null;
//		assertEquals("Unconnected", expectedResult, LowestCommonAncestor.lowestCommonAncestor(t, y));		
//
//
//		
		
	}

}
