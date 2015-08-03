package org.wl.constants;

public enum ViewData {
	index("main page", UrlAction.index.getUrl(), false),
	home("welcome page", UrlAction.home.getUrl(), true),
	list_contacts("list contacts", UrlAction.list_contacts.getUrl(), true),
	new_contacts("create contacts", UrlAction.new_contacts.getUrl(), true);
	
	private String desc;
	private String path;
	private boolean isPartial;
	
	private ViewData(String desc, String path, boolean isPartial){
		this.desc= desc;
		this.path= path;
		this.isPartial= isPartial;
	}
	
	public String getViewName(){
		return (this.isPartial? "partials/": "") + this.toString();
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
