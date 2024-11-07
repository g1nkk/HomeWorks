package stringUtil;

public class StringUtil {
    public boolean isPalindrome(String str) {
        String cleanStr = str.replaceAll("[^a-zA-Z]", "").toLowerCase();
        return new StringBuilder(cleanStr).reverse().toString().equals(cleanStr);
    }

    public int countVowels(String str) {
        return (int) str.toLowerCase().chars().filter(c -> "aeiou".indexOf(c) >= 0).count();
    }

    public int countConsonants(String str) {
        return (int) str.toLowerCase().chars().filter(c -> "bcdfghjklmnpqrstvwxyz".indexOf(c) >= 0).count();
    }

    public int countWordOccurrences(String str, String word) {
        String[] words = str.toLowerCase().split("\\W+");
        return (int) java.util.Arrays.stream(words).filter(w -> w.equals(word.toLowerCase())).count();
    }
}
