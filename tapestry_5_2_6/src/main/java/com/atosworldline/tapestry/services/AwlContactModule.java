package com.atosworldline.tapestry.services;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;

public class AwlContactModule{
	
	public static void contributeApplicationDefaults(MappedConfiguration<String, String> configuration){
		configuration.add("tapestry.start-page-name", "welcomepage");  
		configuration.add(SymbolConstants.SUPPORTED_LOCALES, "fr,en,de");
		configuration.add(SymbolConstants.PRODUCTION_MODE, "false");
		
    }
}
