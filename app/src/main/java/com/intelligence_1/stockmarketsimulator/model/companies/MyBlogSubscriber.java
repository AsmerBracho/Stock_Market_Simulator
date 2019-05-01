package com.intelligence_1.stockmarketsimulator.model.companies;


public class MyBlogSubscriber implements Observer {
    
	private String name;
    private Subject blog;
	
	public MyBlogSubscriber(String name) {

		this.name = name;
	}
	
	@Override
	public void update() {
		
		String msg = (String) blog.getUpdate();
        
		if(msg == null) {
			
			System.out.println(name + " :: No new message");
        }
		else {
			
			System.out.println(name + " :: Consuming message :: " + msg);
        }
	}

	@Override
	public void setSubject(Subject sub) {
		
		this.blog = sub;
	}
}
