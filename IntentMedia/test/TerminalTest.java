import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Unit test for simple Terminal.
 */
public class TerminalTest {

  private Terminal terminal;
  private static final String prices = "A,1,2.00\nA,4,7.00\nB,1,12.00\nC,1,1.25\nC,6,6.00\nD,1,0.15";

  /**
   * Since Terminal is stateful, better off initiating a new instance every test.
   * @throws Exception if prices is poorly formatted.
   */
  @Before
  public void setUp() throws Exception {
    terminal = new Terminal();
    terminal.setPricing(prices);
  }

  @Test
  public void testBasic1() {
    terminal.scan("A");
    terminal.scan("B");
    terminal.scan("C");
    terminal.scan("D");
    terminal.scan("A");
    terminal.scan("B");
    terminal.scan("A");
    terminal.scan("A");
    assertEquals("$32.40", terminal.getTotal());
  }



  @Test
  public void testBasic2() {
    terminal.scan("C");
    terminal.scan("C");
    terminal.scan("C");
    terminal.scan("C");
    terminal.scan("C");
    terminal.scan("C");
    terminal.scan("C");
    assertEquals("$7.25", terminal.getTotal());
  }

  @Test
  public void testBasic3() {
    terminal.scan("A");
    terminal.scan("B");
    terminal.scan("C");
    terminal.scan("D");
    assertEquals("$15.40", terminal.getTotal());
  }

  @Test
  public void testBulk1() {
    terminal.scan("A");
    terminal.scan("A");
    terminal.scan("A");
    assertEquals("$6.00", terminal.getTotal());
    terminal.scan("A");
    assertEquals("$7.00", terminal.getTotal());
  }

  @Test
  public void testBulk2() {
    terminal.scan("C");
    terminal.scan("C");
    terminal.scan("C");
    terminal.scan("C");
    terminal.scan("C");
    assertEquals("$6.25", terminal.getTotal());
    terminal.scan("C");
    assertEquals("$6.00", terminal.getTotal());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testItemNotExist() {
    terminal.scan("nothing");
    terminal.getTotal();
  }
}
