import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class LowestCommonAncestorTest {
	
	@Test
	public void testLowestCommonAncestor() {
		Node expectedResult = null;
		assertEquals("Both null", expectedResult, LowestCommonAncestor.lowestCommonAncestor(null, null));
		Node n = new Node(0);
		assertEquals("One null", expectedResult, LowestCommonAncestor.lowestCommonAncestor(n, null));
		Node o = new Node(1, n);
		expectedResult = n;
		assertEquals("One link", expectedResult, LowestCommonAncestor.lowestCommonAncestor(n, o));
		
		/**
		 * 					 0				7
		 * 				/	  \	  \
		 * 			   1	   2   3
		 * 					  /  \
		 * 					 6    4
		 * 						   \
		 * 							 5
		 * 
		 */
		Node t = new Node(2,n);		
		
		Node u = new Node(3,n);
		Node v = new Node(4,t);
		Node w = new Node(5, v);
		Node x = new Node(6, t);
		Node y = new Node(7, null);
		expectedResult = t;
		assertEquals("2 up right 1 up left", expectedResult, LowestCommonAncestor.lowestCommonAncestor(x, w));
		expectedResult = null;
		assertEquals("Unconnected", expectedResult, LowestCommonAncestor.lowestCommonAncestor(t, y));		


		
		
	}

}
