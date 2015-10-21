package simongame;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author olga
 */
public class PlayerTest {
    
    public PlayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of name method, of class Player.
     */
    @Test
    public void testName() {
        System.out.println("name");
        Player instance = new Player("Olga", 5);
        String expResult = "Olga";
        String result = instance.name();
        System.out.println(instance.name());
        assertEquals(expResult, result);
    }

    /**
     * Test of score method, of class Player.
     */
    @Test
    public void testScore() {
        System.out.println("score");
        Player instance = new Player("Olga", 5);
        int expResult = 5;
        int result = instance.score();
        System.out.println(instance.score());
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Player.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Player p = new Player("Olga", 5);
        Player instance = new Player("Olga", 5);
        int expResult = 1;
        int result = instance.compareTo(p);
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Player.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object o = new Player("Frank", 7);
        Player instance = new Player("Olga", 5);
        boolean expResult = false;
        boolean result = instance.equals(o);
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Player.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Player instance = new Player("Olga", 5);
        System.out.println(instance.hashCode());
        int expResult = 56596749;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Player.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Player instance = new Player("Olga", 0);
        String expResult = "Olga-0";
        String result = instance.toString();
        System.out.println(instance.toString());
        assertEquals(expResult, result);
    }
    
}
