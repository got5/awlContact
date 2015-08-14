package custom.tag;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.tag.common.core.ImportSupport;
import org.apache.taglibs.standard.tag.common.core.UrlSupport;
import org.wl.beans.LinkDesc;

public class MenuList extends TagSupport {

	private LinkDesc[] links;
	private String classes;
	private String currentView;

	public void setLinks(LinkDesc[] links) {
		this.links = links;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}
	
	public void setCurrentView(String currentView) {
		this.currentView = currentView;
	}

	public int doEndTag() throws JspException {

		String resTemplate = "<ul class=\"%s\">%s</ul>";
		String classes= ( this.classes == null? "":  this.classes);
		
		String elementTemplate= "<li class=\"%s\"><a href=\"%s\"> %s </a></li>"; 
		String allElements= "";

		if (this.links != null) {
			for (LinkDesc currentLink : this.links) {

				String initUrl = currentLink.getViewData().getPath();
				String theUrl= UrlSupport.resolveUrl(initUrl, null, pageContext);
				String specialElement= (currentLink.getViewData().toString().equals(this.currentView))? "special-element":"";

				// if the URL is relative, rewrite it
				if (!ImportSupport.isAbsoluteUrl(theUrl)) {
					HttpServletResponse response = ((HttpServletResponse) pageContext
							.getResponse());
					theUrl = response.encodeURL(theUrl);
				}
				
				allElements+= String.format(elementTemplate, specialElement, theUrl, currentLink.getToDisplay()); 
			}
		}
		
		try {
			pageContext.getOut().print(String.format(resTemplate, classes, allElements));
		} catch (java.io.IOException ex) {
			throw new JspTagException(ex.toString(), ex);
		}

		return EVAL_PAGE;
	}

}
