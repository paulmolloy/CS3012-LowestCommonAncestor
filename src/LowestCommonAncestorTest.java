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
	public void testDAG() {
		BST<Integer, Integer> tree = new BST<Integer, Integer>();
		assertTrue("Check is empty", tree.isEmpty() );
		int expectedSize = 0 ;
		assertEquals("tree.size() want: "+ expectedSize+ " got: " + tree.size(), expectedSize, tree.size() );
		tree.addChildNode(null, 10, 10);
		expectedSize = 1;
		assertEquals("tree.size() want: "+ expectedSize+ " got: " + tree.size(), expectedSize, tree.size() );
		tree.addChildNode(10, 5, 5);
		assertEquals("Check is not empty", false, tree.isEmpty());
		tree.addChildNode(5, 2, 2);
		expectedSize = 3;
		assertEquals("tree.size() want: "+ expectedSize+ " got: " + tree.size(), expectedSize, tree.size() );
		tree.addChildNode(10, 20, 20);
		Integer expectedResult = 5;
		assertEquals("Check get 5", expectedResult, tree.get(5) );
		expectedResult = null;
		assertEquals("Check get 19 not added", expectedResult, tree.get(19) );
		expectedResult = 20;
		assertEquals("Check get 20", expectedResult, tree.get(20) );
		assertTrue("Check contains 20", tree.contains(20) );
		assertFalse("Check contains 200000", tree.contains(200000) );
		tree.addChildNode(10, 20,20); //put 20 again
		expectedResult = 20;
		assertEquals("Check get 20 still there", expectedResult, tree.get(20) );
		expectedSize = 4;
		assertEquals("Same size after reading key.tree.size() want: "+ expectedSize+ " got: " + tree.size(), expectedSize, tree.size() );
/*
		│   ┌── 20
		└── 10
		    └── 5
		        └── 2
*/
	}
	@Test
	public void testLowestCommonAncestor() {
		BST<Integer, Integer> tree = new BST<Integer, Integer>();
		tree.addChildNode(null, 10, 10); // (parentKey, Key, value)
		tree.addChildNode(10, 5, 5);
		tree.addChildNode(5, 2, 2);
		tree.addChildNode(10, 20, 20);
		tree.addChildNode(20, 35, 35);
		tree.addChildNode(35, 36, 36);
		tree.addChildNode(35, 34, 34);


		Integer expectedResult = 35;
		assertEquals("LowestCommonAncestor:", expectedResult, tree.lowestCommonAncestor(36, 34) );
		 expectedResult = 34;
		assertEquals("LowestCommonAncestor:", expectedResult, tree.lowestCommonAncestor(34, 34) );
		 expectedResult = 10;
		assertEquals("LowestCommonAncestor:", expectedResult, tree.lowestCommonAncestor(36, 5) );
		 expectedResult = 10;
		assertEquals("LowestCommonAncestor:", expectedResult, tree.lowestCommonAncestor(5, 36) );
		 expectedResult = 10;
		assertEquals("LowestCommonAncestor:", expectedResult, tree.lowestCommonAncestor(2, 36) );
		 expectedResult = 10;
		assertEquals("LowestCommonAncestor:", expectedResult, tree.lowestCommonAncestor(36, 2) );
		 expectedResult = 10;
		assertEquals("LowestCommonAncestor:", expectedResult, tree.lowestCommonAncestor(20, 2) );
		 expectedResult = 2;
		assertEquals("LowestCommonAncestor:", expectedResult, tree.lowestCommonAncestor(2, 2) );
		 expectedResult = 10;
		assertEquals("LowestCommonAncestor:", expectedResult, tree.lowestCommonAncestor(2, 10) );
		 expectedResult = 2;
		assertEquals("LowestCommonAncestor:", expectedResult, tree.lowestCommonAncestor(2, 2) );
		 expectedResult = 20;
		assertEquals("LowestCommonAncestor:", expectedResult, tree.lowestCommonAncestor(20, 34) );
		 expectedResult = 10;
		assertEquals("LowestCommonAncestor:", expectedResult, tree.lowestCommonAncestor(10, 10) );
		 expectedResult = 5;
		assertEquals("LowestCommonAncestor:", expectedResult, tree.lowestCommonAncestor(2, 5) );
		 expectedResult = 20;
		assertEquals("LowestCommonAncestor:", expectedResult, tree.lowestCommonAncestor(20, 20) );
		 expectedResult = null;
		assertEquals("LowestCommonAncestor:", expectedResult, tree.lowestCommonAncestor(20, 50) );



/*│           ┌── 36
│       ┌── 35
│       │   └── 34
│   ┌── 20
└── 10
    └── 5
        └── 2
 */
		
	}

}
