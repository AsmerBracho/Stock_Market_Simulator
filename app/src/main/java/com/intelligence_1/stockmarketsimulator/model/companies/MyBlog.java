package com.intelligence_1.stockmarketsimulator.model.companies;


import java.util.ArrayList;
import java.util.List;

public class MyBlog implements Subject {
	
    private List<Observer> observers;
    private String message;
    private boolean changed;
	
	public MyBlog() {
		this.observers=new ArrayList<>();
	
	}
	
	@Override
	public void register(Observer obj) {
		
		if(obj == null) {
			
			throw new NullPointerException("Null Observer");
		}

		if(!observers.contains(obj)) {
			observers.add(obj);
		}
		
	}

	@Override
	public void unregister(Observer obj) {
		
		observers.remove(obj);
	}

	@Override
	public void notifyObservers() {
		
		List<Observer> observersLocal = null;
                
        if (!changed) {
            return;
        }
          
        observersLocal = new ArrayList<>(this.observers);
          
        this.changed = false;
        
        for (Observer obj : observersLocal) {
                 
        	obj.update();
            
        }

	}

	@Override
	public Object getUpdate() {

		return this.message;
	}
	
	//method to post message to the topic
	public void postMessage(String msg) {
		
		System.out.println("Message Posted to Blog: "+msg);
        
		this.message = msg;
        
		this.changed = true;
        
		notifyObservers();
	}
}
