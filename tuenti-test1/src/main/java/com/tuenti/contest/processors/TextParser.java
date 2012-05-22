package com.tuenti.contest.processors;

import java.util.ArrayList;
import java.util.List;

import com.tuenti.contest.domain.enu.ButtonActionEnum;

public class TextParser {
	
	
	public static List<ButtonActionEnum> processText(boolean upperCaseEnabled, String text) {
		if (text == null) {
			throw new IllegalArgumentException("A text must be provided");
		}
		boolean upperCase = upperCaseEnabled;
		List<ButtonActionEnum> actions = new ArrayList<ButtonActionEnum>();
		for (Character ch : text.toCharArray()) {
			ButtonActionEnum action = getMatchingAction(ch);
			//check if must change case first
			if (mustChangeCase(ch, upperCase)) {
				actions.add(ButtonActionEnum.CTRL_CHANGE_CASE);
				upperCase = !upperCase;
			}
			actions.add(action);
		}
		return actions;
	}
	
	private static boolean mustChangeCase(Character ch, boolean upperCaseEnabled) {
		if (Character.isLetter(ch)) {
			if (Character.isUpperCase(ch) && !upperCaseEnabled) {
				return true;
			} else if (Character.isLowerCase(ch) && upperCaseEnabled) {
				return true;
			}
		}
		return false;
	}
	
	public static ButtonActionEnum getMatchingAction(Character ch) {
		if (ch == null) {
			throw new IllegalArgumentException("A character must be specified");
		}
		if (ch == ' ') {
			return ButtonActionEnum.CTRL_SPACE;
		}
		try {
			return ButtonActionEnum.valueOf("SYMBOL_"+Character.toLowerCase(ch));
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid character "+ch);
		}
	}
	
}
