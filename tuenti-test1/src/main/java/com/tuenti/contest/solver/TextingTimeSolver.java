package com.tuenti.contest.solver;

import static com.tuenti.contest.domain.enu.ButtonActionEnum.CTRL_CHANGE_CASE;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.CTRL_SPACE;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_0;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_1;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_2;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_3;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_4;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_5;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_6;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_7;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_8;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_9;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_a;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_b;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_c;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_d;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_e;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_f;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_g;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_h;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_i;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_j;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_k;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_l;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_m;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_n;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_o;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_p;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_q;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_r;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_s;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_t;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_u;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_v;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_w;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_x;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_y;
import static com.tuenti.contest.domain.enu.ButtonActionEnum.SYMBOL_z;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.tuenti.contest.domain.Button;
import com.tuenti.contest.domain.enu.ButtonActionEnum;
import com.tuenti.contest.processors.TextParser;

public class TextingTimeSolver {
	private static List<Button> buttons;
	private static Map<Button,Map<Button,Integer>> paths;
	private static Map<ButtonActionEnum,Button> actionMap;
	private static Button startButton; 
	private static final Integer LEFT = 200;
	private static final Integer RIGHT = 200;
	private static final Integer UP = 300;
	private static final Integer DOWN = 300;
	private static final Integer DIAGONAL = 350;
	private static final Integer PRESS = 100;
	private static final Integer WAIT_FOR_PRESS = 500;
	
	static {
		initializeButtons();
	}
	
	private static void initializeButtons() {
		buttons = new ArrayList<Button>();
		Button but1 = Button.createButton(CTRL_SPACE, SYMBOL_1);
		buttons.add(but1);
		Button but2 = Button.createButton(SYMBOL_a,SYMBOL_b,SYMBOL_c,SYMBOL_2);
		buttons.add(but2);
		Button but3 = Button.createButton(SYMBOL_d,SYMBOL_e,SYMBOL_f,SYMBOL_3);
		buttons.add(but3);
		Button but4 = Button.createButton(SYMBOL_g,SYMBOL_h,SYMBOL_i,SYMBOL_4);
		buttons.add(but4);
		Button but5 = Button.createButton(SYMBOL_j,SYMBOL_k,SYMBOL_l,SYMBOL_5);
		buttons.add(but5);
		Button but6 = Button.createButton(SYMBOL_m,SYMBOL_n,SYMBOL_o,SYMBOL_6);
		buttons.add(but6);
		Button but7 = Button.createButton(SYMBOL_p,SYMBOL_q,SYMBOL_r,SYMBOL_s,SYMBOL_7);
		buttons.add(but7);
		Button but8 = Button.createButton(SYMBOL_t,SYMBOL_u,SYMBOL_v,SYMBOL_8);
		buttons.add(but8);
		Button but9 = Button.createButton(SYMBOL_w,SYMBOL_x,SYMBOL_y,SYMBOL_z,SYMBOL_9);
		buttons.add(but9);
		Button but0 = Button.createButton(SYMBOL_0);
		buttons.add(but0);
		Button butCase = Button.createButton(CTRL_CHANGE_CASE);
		buttons.add(butCase);
		
		//Conections
		but1.setRight(but2);
		but1.setDown(but4);
		but1.setDiagonals(Arrays.asList(but5));
		
		but2.setRight(but3);
		but2.setDown(but5);
		but2.setLeft(but1);
		but2.setDiagonals(Arrays.asList(but4,but6));
		
		but3.setDown(but6);
		but3.setLeft(but2);
		but3.setDiagonals(Arrays.asList(but5));
		
		but4.setUp(but1);
		but4.setRight(but5);
		but4.setDown(but7);
		but4.setDiagonals(Arrays.asList(but2,but8));
		
		but5.setUp(but2);
		but5.setRight(but6);
		but5.setDown(but8);
		but5.setLeft(but4);
		but5.setDiagonals(Arrays.asList(but1,but3,but7,but9));
		
		but6.setUp(but3);
		but6.setDown(but9);
		but6.setLeft(but5);
		but6.setDiagonals(Arrays.asList(but2,but8));
		
		but7.setUp(but4);
		but7.setRight(but8);
		but7.setDiagonals(Arrays.asList(but5,but0));
		
		but8.setUp(but5);
		but8.setRight(but9);
		but8.setDown(but0);
		but8.setLeft(but7);
		but8.setDiagonals(Arrays.asList(but4,but6,butCase));
		
		but9.setUp(but6);
		but9.setDown(butCase);
		but9.setLeft(but8);
		but9.setDiagonals(Arrays.asList(but5,but0));
		
		but0.setUp(but8);
		but0.setRight(butCase);
		but0.setDiagonals(Arrays.asList(but7,but9));
		
		butCase.setUp(but9);
		butCase.setLeft(but0);
		butCase.setDiagonals(Arrays.asList(but8));
			
		startButton = but0;
		paths = calculatePaths(buttons);
		actionMap = calculateActionMapping(buttons);
	}
	
	private static Map<ButtonActionEnum,Button> calculateActionMapping(List<Button> buttons) {
		Map<ButtonActionEnum,Button> actionMap = new HashMap<ButtonActionEnum, Button>();
		for (Button button : buttons) {
			for (ButtonActionEnum action : button.getActions()) {
				actionMap.put(action,button);
			}
		}
		return actionMap;
	}
	
	private static Map<Button,Map<Button,Integer>> calculatePaths(List<Button> buttons) {
		Map<Button, Map<Button,Integer>> paths = new HashMap<Button, Map<Button,Integer>>();
		for (Button buttonSource : buttons) {
			Map<Button,Integer> partialPath = new HashMap<Button, Integer>();
			for (Button buttonTarget: buttons) {
				if (buttonTarget.equals(buttonSource.getLeft())) {
					partialPath.put(buttonTarget, LEFT);
				} else if (buttonTarget.equals(buttonSource.getRight())) {
					partialPath.put(buttonTarget, RIGHT);
				} else if (buttonTarget.equals(buttonSource.getUp())) {
					partialPath.put(buttonTarget, UP);
				} else if (buttonTarget.equals(buttonSource.getDown())) {
					partialPath.put(buttonTarget, DOWN);
				} else if (buttonSource.getDiagonals().contains(buttonTarget)) {
					partialPath.put(buttonTarget, DIAGONAL);
				} else {
					partialPath.put(buttonTarget, Integer.MAX_VALUE);
				}
			}
			paths.put(buttonSource, partialPath);
		}
		
		for (Button middle : buttons) {
			for (Button source: buttons) {
				int source2Middle = paths.get(source).get(middle);
				for (Button target : buttons) {
					int source2Target = paths.get(source).get(target);
					int middle2Target = paths.get(middle).get(target);
					int source2TargetWithMiddle = source2Middle + middle2Target;
					if (source2Middle == Integer.MAX_VALUE || middle2Target == Integer.MAX_VALUE) {
						source2TargetWithMiddle = Integer.MAX_VALUE;
					}
					int value = Math.min(source2Target, source2TargetWithMiddle);
					paths.get(source).put(target, value);
				}
			}
		}
		return paths;
	}
	
	public static Integer solveProblem(String text) {
		List<ButtonActionEnum> actions = TextParser.processText(false, text);
		Button currentButton = startButton;
		Integer time = 0;
		for (ButtonActionEnum action : actions) {
			Button targetButton = actionMap.get(action);
			//Move if needed
			if (!currentButton.equals(targetButton)) {
				time += paths.get(currentButton).get(targetButton);
				currentButton = targetButton;
			} else {
				//We have to wait if we have pressed the same button before
				if (time > 0) { //starting point does not count
					time += WAIT_FOR_PRESS;
				}
			}
			
			//press buttons
			int pressTimes = targetButton.calculatePressTimesForAction(action);
			time += PRESS * pressTimes;
		}
		return time;
	}
}
