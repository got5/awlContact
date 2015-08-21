package com.atosworldline.jsf2.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("commaSeparatedConverter")
public class CommaSeparatedConverter implements Converter {

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        System.out.println("###################### value 1 : "+value);
        String[] strings = (String[]) value;
        StringBuilder builder = new StringBuilder();

        for (String string : strings) {
            if (builder.length() > 0) {
                builder.append(",");
            }

            builder.append(string);
        }

        return builder.toString();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
System.out.println("###################### value 2 : "+value);
        return value.split(",");
    }

}