package org.wl.constants;

public enum UrlAction {
	index("/"),
	home("/home"),
	list_contacts("/contacts/list"),
	new_contacts("/contacts/new"),
	contacts_update("/contacts/update");
	
	public final String url;
	
	private UrlAction(String url){
		this.url= url;
	}

	public String getUrl() {
		return url;
	}
	
	public static UrlAction getForString(String actionName){
		for (UrlAction current: UrlAction.values()){
			if (current.toString().equals(actionName)){
				return current;
			}
		}
		
		return null;
	}	
	
	
	

}
