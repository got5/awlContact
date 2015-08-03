package org.wl.constants;

public enum ViewData {
	home("welcome page", UrlAction.home.getUrl()),
	list_contacts("list contacts", UrlAction.list_contacts.getUrl()),
	new_contacts("create contacts", UrlAction.new_contacts.getUrl());
	
	private String desc;
	private String path;
	
	private ViewData(String desc, String path){
		this.desc= desc;
		this.path= path;
	}

	public String getDesc() {
		return desc;
	}
	
	public String getPath() {
		return path;
	}

	public static ViewData getForString(String viewName){
		for (ViewData current: ViewData.values()){
			if (current.toString().equals(viewName)){
				return current;
			}
		}
		
		return null;
	}

}
