package com.tuenti.contest.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tuenti.contest.domain.enu.ButtonActionEnum;

public class Button {
	
	private static int idGenerator = 0;
	private int id;
	private List<ButtonActionEnum> actions;
	
	private Button up;
	private Button right;
	private Button down;
	private Button left;
	private List<Button> diagonals;
	
	public static Button createButton(ButtonActionEnum... symbols) {
		return new Button(idGenerator++,symbols);
	}
	
	private Button(int id, ButtonActionEnum... symbols) {
		this.id = id;
		this.actions = new ArrayList<ButtonActionEnum>(Arrays.asList(symbols));
	}
	
	
	
	public List<ButtonActionEnum> getActions() {
		return actions;
	}

	public Button getUp() {
		return up;
	}

	public void setUp(Button up) {
		this.up = up;
	}

	public Button getRight() {
		return right;
	}

	public void setRight(Button right) {
		this.right = right;
	}

	public Button getDown() {
		return down;
	}

	public void setDown(Button down) {
		this.down = down;
	}

	public Button getLeft() {
		return left;
	}

	public void setLeft(Button left) {
		this.left = left;
	}

	public List<Button> getDiagonals() {
		if (diagonals == null) {
			diagonals = new ArrayList<Button>();
		}
		return diagonals;
	}

	public void setDiagonals(List<Button> diagonals) {
		this.diagonals = diagonals;
	}

	public boolean supportsAction(ButtonActionEnum action) {
		return actions.contains(action);
	}
	
	public Integer calculatePressTimesForAction(ButtonActionEnum action) {
		int index = actions.indexOf(action);
		if (index >= 0) {
			return index+1;
		}
		return null;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Button)  {
			Button other = (Button)obj;
			return this.id == other.id;
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return id;
	}
	
	@Override
	public String toString() {
		return getActions().toString();
	}
}
