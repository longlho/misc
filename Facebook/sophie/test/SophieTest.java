import junit.framework.TestCase;


public class SophieTest extends TestCase {
	/**
	 * Different test cases in separate methods so that timings
	 * are automatically reported as well
	 */
	
	public void test1() {
		assertEquals(sophie.findSophie("input1.txt"), "6.00"); //4 nodes
	}
	
	public void test2() {
		assertEquals(sophie.findSophie("input2.txt"), "38.20");//17 nodes
	}
	
	public void test3() {
		assertEquals(sophie.findSophie("input3.txt"), "115.86"); // 20 nodes
	}
	
	public void test4() {
		assertEquals(sophie.findSophie("input4.txt"), "76.18"); //0 probability connected node 10 nodes
	}
	
	public void test5() {
		assertEquals(sophie.findSophie("input5.txt"), "76.18"); //0 probability unconnected node 10 nodes
	}
	
	public void test6() {
		assertEquals(sophie.findSophie("input6.txt"), "183.04"); //20 nodes
	}
	
	public void test7() {
		assertEquals(sophie.findSophie("input7.txt"), "9.95"); // 7 nodes
	}
	
	public void test8() {
		assertEquals(sophie.findSophie("input8.txt"), "5.42"); // 4 nodes
	}
	
	public void test9() {
		assertEquals(sophie.findSophie("input9.txt"), "-1.00"); // 4 nodes
	}
	
	public void test10() {
		assertEquals(sophie.findSophie("input10.txt"), "5.42"); // 4 nodes
	}
	
	public void test11() {
		assertEquals(sophie.findSophie("input11.txt"), "1.40"); // 3 nodes
	}
	
	public void test12() {
		assertEquals(sophie.findSophie("input12.txt"), "11.20"); // 7 nodes
	}
	
	public void test13() {
		assertEquals(sophie.findSophie("input13.txt"), "-1.00"); // 0 nodes
	}
	
	public void test14() {
		assertEquals(sophie.findSophie("input14.txt"), "0.00"); // 2 nodes, found in initial node
	}
	
	public void test15() {
		assertEquals(sophie.findSophie("input15.txt"), "3.00"); // 4 nodes, floating point distance
	}
	
}
