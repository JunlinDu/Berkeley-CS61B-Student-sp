import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator offByOne = new OffByOne();
    static CharacterComparator offByFour = new OffByN(4);

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("abcba"));
        assertTrue(palindrome.isPalindrome("abccba"));
        assertFalse(palindrome.isPalindrome("abc"));
        assertFalse(palindrome.isPalindrome("Abcba"));

        assertTrue(palindrome.genericIsPalindrome("flake", offByOne));
        assertTrue(palindrome.genericIsPalindrome("ab", offByOne));
        assertFalse(palindrome.genericIsPalindrome("meow", offByOne));

        assertTrue(palindrome.genericIsPalindrome("abcfe", offByFour));
        assertTrue(palindrome.genericIsPalindrome("ae", offByFour));
        assertFalse(palindrome.genericIsPalindrome("meow", offByFour));
    }
}
