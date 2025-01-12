package seccionA;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CalculationTest {
	@Test
	void testFindMax1() {
		assertEquals(0,Calculation.findMax(new int[] {0,0,0,0}));
	}
	@Test
	void testFindMax2() {
		assertEquals(-1,Calculation.findMax(new int[] {-12,-1,-3,-4,-2}));
	}
	@Test
	void testFindMax3() {
		assertEquals(100, Calculation.findMax(new int[] {15, -20, 0, -5, 100}));
	}

}
