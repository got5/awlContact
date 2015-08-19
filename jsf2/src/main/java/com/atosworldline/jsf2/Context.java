package com.atosworldline.jsf2;



public enum Context {
	PRIMEFACES("primefaces"), RICHFACES("richfaces"), BOOTSFACES("bootsfaces");

	private final String label;

	private Context(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}
}
