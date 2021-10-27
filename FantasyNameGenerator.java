import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class FantasyNameGenerator {
	
	static final int numberOfLettersForNames = 6; // adjust according to your needs
	static final int numberOfNamesNeeded = 100; // adjust according to your needs
	static final char[] letters = {
		'a', 'b', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
		'm', 'n', 'o', 'p', 'r', 's', 't', 'u', 'v', 'w', 'z',
		/* 'c', 'q', 'x', 'y' */
	}; // removing [c + q + x + y] might yield more 'realistic' names

	public static void main(String[] args) {
		HashSet<String> names = new HashSet<>();
		
		while (names.size() < numberOfNamesNeeded) {
			String name = generateRandomName(letters);
			if (!containsVowel(name)) continue;
			if (hasTooManyConsecutiveConsonantsOrVowels(name)) continue;
			if (hasEnoughVowels(name)) names.add(convertFirstLetterToUpper(name));
		}
		
		printSortedNames(names);
	}
	
	private static boolean containsVowel(String name) {
		return name.contains("a")
			|| name.contains("e")
			|| name.contains("i")
			|| name.contains("o")
			|| name.contains("u");
	}

	private static String convertFirstLetterToUpper(String name) {
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}
	
	private static String generateRandomName(char[] letters) {
		String name = "";
		for (int i = 0; i < numberOfLettersForNames; i++) {
			int randomNumber = (int) (Math.random() * letters.length);
			name += letters[randomNumber];
		}
		return name;
	}

	private static boolean hasEnoughVowels(String name) {
		char[] chars = name.toCharArray();
		int countVowels = 0;
		
		for (char c : chars) {
			if (isVowel(c)) countVowels++;
		}
		return countVowels >= numberOfLettersForNames/2 && countVowels < numberOfLettersForNames;
	}

	private static boolean hasTooManyConsecutiveConsonantsOrVowels(String name) {
		char[] chars = name.toCharArray();
		int countConsecutiveVowels = 0;
		int countConsecutiveConsonants = 0;
		
		for (char c : chars) {
			if (isVowel(c)) {
				countConsecutiveVowels++;
				countConsecutiveConsonants = 0;
			} else {
				countConsecutiveConsonants++;
				countConsecutiveVowels = 0;
			}
			
			if (countConsecutiveConsonants > 2 || countConsecutiveVowels > 2) return true;
		}

		return false;
	}
	
	private static boolean isVowel(char c) {
		return "aeiou".contains(c + "");
	}

	private static void printSortedNames(HashSet<String> names) {
		ArrayList<String> sortedNames = sortNames(names);
		for (String name : sortedNames) {
			System.out.println(name);
		}
	}

	private static ArrayList<String> sortNames(HashSet<String> names) {
		ArrayList<String> sortedNames = new ArrayList<String>(names);
		Collections.sort(sortedNames);
		return sortedNames;
	}
	
}
