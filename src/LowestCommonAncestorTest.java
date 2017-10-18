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
		assertTrue("Check is empty", tree.isEmpty() );
		int expectedSize = 0 ;
		assertEquals("tree.size() want: "+ expectedSize+ " got: " + tree.size(), expectedSize, tree.size() );
		tree.put(10, 10);
		expectedSize = 1;
		assertEquals("tree.size() want: "+ expectedSize+ " got: " + tree.size(), expectedSize, tree.size() );
		tree.put(5, 5);
		assertEquals("Check is not empty", false, tree.isEmpty());
		tree.put(2, 2);
		expectedSize = 3;
		assertEquals("tree.size() want: "+ expectedSize+ " got: " + tree.size(), expectedSize, tree.size() );
		tree.put(20,20);
		Integer expectedResult = 5;
		assertEquals("Check get 5", expectedResult, tree.get(5) );
		expectedResult = null;
		assertEquals("Check get 19 not added", expectedResult, tree.get(19) );
		expectedResult = 20;
		assertEquals("Check get 20", expectedResult, tree.get(20) );
		assertTrue("Check contains 20", tree.contains(20) );
		assertFalse("Check contains 200000", tree.contains(200000) );
		tree.put(20,20); //put 20 again
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
		System.out.println(tree.prettyPrint());
	}
	@Test
	public void testLowestCommonAncestor() {
		BST<Integer, Integer> tree = new BST<Integer, Integer>();
		tree.put(10, 10);
		tree.put(5, 5);
		tree.put(2, 2);
		tree.put(20, 20);
		tree.put(35, 35);
		tree.put(36, 36);
		tree.put(34, 34);

		Integer expectedResult = 35;
		assertEquals("LowestCommonAncestor:", expectedResult, tree.lowestCommonAncestor(36, 34) );

		System.out.println(tree.prettyPrint());

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
