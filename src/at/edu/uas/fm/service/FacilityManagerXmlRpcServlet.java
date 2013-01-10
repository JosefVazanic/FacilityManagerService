package at.edu.uas.fm.service;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcHandlerMapping;
import org.apache.xmlrpc.webserver.XmlRpcServlet;

public class FacilityManagerXmlRpcServlet extends XmlRpcServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected XmlRpcHandlerMapping newXmlRpcHandlerMapping()
			throws XmlRpcException {
		PropertyHandlerMapping mapping = (PropertyHandlerMapping) super
				.newXmlRpcHandlerMapping();
		mapping.addHandler(FacilityManagerService.class.getName(),
				FacilityManagerService.class);
		return mapping;
	}
}
