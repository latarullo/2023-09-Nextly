package test;

/*
 * 1 - A palindrome is a word that is symmetric: 
 * if we write it backward, the result word is the same. 
 * For example, “HANNAH” is a palindrome, but “GAGA” is not. 
 * Write a short program that determines whether a word is a palindrome.
 */
public class PalindromeCheck {
	/*
	 * Another lazy approach could be reversing the word and checking it against itself
	 * 
	 */
	public boolean isPalindrome(String word) {
		if (word == null || word.isEmpty()) {
			throw new IllegalArgumentException("word parameter is required!");
		}

		boolean isPalindrome = true;

		for (int i = 0; i < word.length() / 2; i++) {
			if (word.charAt(i) != word.charAt(word.length() - 1 - i)) {
				isPalindrome = false;
				break;
			}
		}

		return isPalindrome;
	}

	public static void main(String[] args) {
		PalindromeCheck palindromeCheck = new PalindromeCheck();

		String[] words = { "HANNAH", "GAGA", "ANYTHINGELSE" };

		for (String word : words) {
			System.out.println(String.format("Checking if %s is palindrome...\nResult: %b\n----------------", word,
					palindromeCheck.isPalindrome(word)));
		}
	}
}
