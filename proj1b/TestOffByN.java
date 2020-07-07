import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByFour = new OffByN(4);

    // Your tests go here.
    @Test
    public void testEqualChars() {
        assertTrue(offByFour.equalChars('a', 'e'));
        assertTrue(offByFour.equalChars('b', 'f'));
        assertFalse(offByFour.equalChars('a', 'b'));
        assertFalse(offByFour.equalChars('a', 'A'));
    }
}
