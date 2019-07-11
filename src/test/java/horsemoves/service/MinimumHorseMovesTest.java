package horsemoves.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Texhnolyze
 */
public class MinimumHorseMovesTest {
    
    public MinimumHorseMovesTest() {
    }

    /**
     * Test of isValid method, of class MinimumHorseMoves.
     */
    @Test
    public void testIsValid() {
        System.out.println("isValid");
        MinimumHorseMoves instance = new MinimumHorseMoves(-1, -1, null, null);
        assertFalse(instance.isValid());
        instance = new MinimumHorseMoves(1, 1, "A1", "A1");
        assertTrue(instance.isValid());
        instance = new MinimumHorseMoves(-1, -1, "A0", "A0");        
        assertFalse(instance.isValid());
        instance = new MinimumHorseMoves(5, 4, "A01", "A02");
        assertFalse(instance.isValid());
        instance = new MinimumHorseMoves(5, 4, "B1", "B2");
        assertTrue(instance.isValid());
        instance = new MinimumHorseMoves(5, 4, "B-1", "B-2");
        assertFalse(instance.isValid());
        instance = new MinimumHorseMoves(99999, 99999, "A1", "A2");
        assertFalse(instance.isValid());
    }
    
}
