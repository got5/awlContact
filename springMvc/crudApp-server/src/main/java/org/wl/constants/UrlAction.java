package org.wl.constants;

public enum UrlAction {
	home("/home"),
	list_contacts("/contacts/list"),
	new_contacts("/contacts/new");
	
	public final String url;
	
	private UrlAction(String url){
		this.url= url;
	}

	public String getUrl() {
		return url;
	}
	
	
	

}
