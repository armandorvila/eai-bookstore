package com.armandorv.miw.eai.bookstore.impl.component;


public class CorrlationGroupSize {
	
	private static CorrlationGroupSize INSTANCE = new CorrlationGroupSize();
	
	private int groupSize = 0;
	
	private CorrlationGroupSize(){}
	
	public static synchronized CorrlationGroupSize getINSTANCE(){
		return INSTANCE;
	}
	
	public int getGroupSize(){
		return groupSize;
	}
	
	public void increment(){
		groupSize ++;
	}
	
	public void decrement(){
		groupSize --;
	}
	
	public void reset(){
		groupSize = 0;
	}
}
