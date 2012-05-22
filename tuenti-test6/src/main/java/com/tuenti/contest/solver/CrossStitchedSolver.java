package com.tuenti.contest.solver;



public class CrossStitchedSolver {
	private static double ZERO = 0.0000001;

	public static int solveProblem(int width, int height, int ct, String text) {
		if (isValidText(text)) {
			String[] words = text.split(" ");
			int widthStitches = width * ct;
			int heightStitches = height * ct;
			int lines = 1;
			int font = 0;
			
			//Initialization phase, here we try to get the maximum pixels to Cover all width or height
			if (widthStitches < heightStitches) {
				int longestWord = calculateLOngestWord(words);
				
				font = widthStitches/longestWord;
				if (font > 0) {
					lines = heightStitches/font; 
				} else {
					return 0; //Does not fit, even if every character is font 1
				}
			} else { 
				lines = 1; 
				font = heightStitches;
			}
			
			//Iterative
			while (font > 0) {
				//Check if fits
				if (textFitsHorizontallyInCloth(widthStitches, lines, font, words)) {
					return calculateThreadLength(text, font, ct);
				} else {
					font--;
					//can we increase lines?
					if (font > 0) {
						lines = heightStitches/font;
					}
				}
			}
			return 0;
		} else {
			throw new IllegalArgumentException("Text has illegal characters");
		}
	}

	private static int calculateThreadLength(String text, int font, int ct) {
		int charactersWithoutSpace = text.replaceAll(" ", "").length();
		double stitchesPerCharacter = (font*font)/2.0;
		double inchesPerStitch = 1.0/ct;
		double length = stitchesPerCharacter * charactersWithoutSpace * inchesPerStitch;
		double decimals = length - (int)length;
		if (decimals > ZERO) {
			return (int)length + 1;
		} else {
			return (int)length;
		}
	}

	private static boolean textFitsHorizontallyInCloth(int widthStitches, int lines, int font, String[] words) {
		int startWord = 0;
		int spaceWidth = font;
		for (int line = 0; line < lines && startWord < words.length; line++) {
			int currentWidth = 0;
			int wordsInLine = 0;
			boolean fits = true;
			for (int j = startWord; j < words.length && fits; j++) {
				int wordWith = words[j].length() * font;
				if (j > startWord) { //if it's not first word in line, space must be taken into account
					wordWith += spaceWidth;
				}
				
				if (currentWidth + wordWith <= widthStitches) {
					wordsInLine++;
					currentWidth += wordWith;
				} else { 
					fits = false;
				}
			}
			if (wordsInLine > 0) {
				startWord += wordsInLine;
			} else {
				return false;
			}
		}
		
		if (startWord == words.length) {
			return true;
		} else {
			return false;
		}
	}

	private static int calculateLOngestWord(String[] words) {
		int max = 0;
		for (String word : words) {
			if (word.length() > max) {
				max = word.length();
			}
		}
		return max;
	}

	private static boolean isValidText(String text) {
		if (text != null) {
			String replaced = text.replaceAll("[a-zA-Z0-9\\!\"#%\\&'()*\\+,\\-./:;<=>?@\\[\\] ^_`{|}]*", "");
			return replaced.length() == 0;
		}
		return false;
	}
	
}
