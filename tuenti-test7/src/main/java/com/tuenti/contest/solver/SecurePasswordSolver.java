package com.tuenti.contest.solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;



public class SecurePasswordSolver {
	
	private Map<Character,Set<Character>> requisites;
	private Set<Character> allSymbols;
	
	public SecurePasswordSolver(List<String> codes) {
		requisites = new HashMap<Character, Set<Character>>();
		allSymbols = new HashSet<Character>();
		for (String subCode : codes) {
			if (isValidCode(subCode)) {
				//Add symbols
				for (Character symbol : subCode.toCharArray()) {
					allSymbols.add(symbol);
				}
				
				for (int i = subCode.length()-1; i > 0; i--) {
					Character symbol = subCode.charAt(i);
					Set<Character> symbolReqs = requisites.get(symbol);
					if (symbolReqs == null) {
						symbolReqs = new HashSet<Character>();
					}
					for (int j = 0; j < i; j++) {
						symbolReqs.add(subCode.charAt(j));
					}
					requisites.put(symbol, symbolReqs);
				}
			} else {
				throw new IllegalArgumentException("Illegal subcode "+subCode);
			}
		}
	}
	
	public List<String> solveProblem() {
		
		List<String> passwords = buildPasswords("");
		Collections.sort(passwords);
		return passwords;
	}
	
	private List<String> buildPasswords(String password) {
		int totalSymbols = allSymbols.size();
		List<String> passwords = new ArrayList<String>();
		if (password.length() == totalSymbols) {
			passwords.add(password);
		} else {
			Set<Character> nextSymbols = getNextValidSymbols(password);
			for (Character nextSymbol : nextSymbols) {
				passwords.addAll(buildPasswords(password+nextSymbol));
			}
		}
		return passwords;
	}
	
	private Set<Character> getNextValidSymbols(String password) {
		Set<Character> unusedSymbols = new HashSet<Character>(allSymbols);
		Set<Character> usedSymbols = getUsedSymbols(password);
		unusedSymbols.removeAll(usedSymbols);
		
		Set<Character> validSymbols = new HashSet<Character>();
		for (Character symbol : unusedSymbols) {
			Set<Character> symbolReq = requisites.get(symbol);
			if (symbolReq == null || usedSymbols.containsAll(symbolReq)) {
				validSymbols.add(symbol);
			}
		}
		return validSymbols;
	}
	
	private Set<Character> getUsedSymbols(String password) {
		Set<Character> usedSymbols = new HashSet<Character>();
		for (Character charac : password.toCharArray()) {
			usedSymbols.add(charac);
		}
		return usedSymbols;
	}


	private static boolean isValidCode(String code) {
		if (code != null) {
			return code.replaceAll("[a-zA-Z0-9]","").length() == 0;
		}
		return false;
	}
	
}
