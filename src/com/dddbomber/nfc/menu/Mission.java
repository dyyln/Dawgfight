package com.dddbomber.nfc.menu;

import java.util.ArrayList;

public class Mission {
	
	public ArrayList<MissionType> type = new ArrayList<MissionType>();
	
	public boolean[] complete = new boolean[3];
	
	public String name = "";
	
	public String[] goals = new String[3];
	
}
