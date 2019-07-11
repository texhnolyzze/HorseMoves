package horsemoves.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Texhnolyze
 */
public class MinimumHorseMovesCalculatorTest {
    
    public MinimumHorseMovesCalculatorTest() {
    }

    /**
     * Test of compute method, of class MinimumHorseMovesCalculator.
     */
    @Test
    public void testCompute() {
        System.out.println("compute");
        int w = 1;
        int h = 1;
        int[] start = {1, 1};
        int[] end = {1, 1};
        int moves = MinimumHorseMovesCalculator.compute(w, h, start, end);
        assertEquals(moves, 0);
        w = 8; h = 8;
        start[0] = 1; start[1] = 1;
        end[0] = 6; end[1] = 6;
        moves = MinimumHorseMovesCalculator.compute(w, h, start, end);
        assertEquals(moves, 4);
        w = 1; h = 8;
        start[0] = 1; start[1] = 1;
        end[0] = 1; end[1] = 5;
        moves = MinimumHorseMovesCalculator.compute(w, h, start, end);
        assertEquals(moves, -1);
    }
    
}
