package secciónA;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class test extends Calculation{
	@Test
	public static void testFindMax() {
		assertEquals(4,findMax(new int[] {1,3,4,2}));
		assertEquals(-1,findMax(new int[] {-12,-1,-3,-4,-2}));
	}
	
}
