package tests;

import org.junit.jupiter.api.Test;
import stringUtil.StringUtil;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilTest {
    @Test
    public void testIsPalindrome() {
        StringUtil util = new StringUtil();
        assertTrue(util.isPalindrome("A man a plan a canal Panama"));
    }

    @Test
    public void testCountVowels() {
        StringUtil util = new StringUtil();
        assertEquals(9, util.countVowels("A man a plan a canal Panama"));
    }

    @Test
    public void testCountConsonants() {
        StringUtil util = new StringUtil();
        assertEquals(10, util.countConsonants("A man a plan a canal Panama"));
    }

    @Test
    public void testCountWordOccurrences() {
        StringUtil util = new StringUtil();
        assertEquals(2, util.countWordOccurrences("Hello world, hello everyone", "hello"));
    }
}