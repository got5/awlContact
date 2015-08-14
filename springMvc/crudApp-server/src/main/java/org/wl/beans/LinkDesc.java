package org.wl.beans;

import org.wl.constants.ViewData;

public class LinkDesc {

	private ViewData viewData;
	private String toDisplay;

	public LinkDesc(ViewData viewData, String toDisplay) {
		super();
		this.viewData= viewData;
		this.toDisplay= toDisplay;
	}

	public ViewData getViewData() {
		return viewData;
	}

	public String getToDisplay() {
		return toDisplay;
	}
	
	
}
