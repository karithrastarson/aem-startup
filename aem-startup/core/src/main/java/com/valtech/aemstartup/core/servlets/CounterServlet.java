package com.valtech.aemstartup.core.servlets;

import java.io.IOException;
import javax.jcr.Node;
import javax.jcr.Session;
import javax.servlet.ServletException;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(metatype = true)
@SlingServlet(
		methods = {"POST"},
		generateComponent = false
		)
@Service
public class CounterServlet extends SlingAllMethodsServlet {
	private static final long serialVersionUID = 1L;

	@Property(name = "sling.servlet.paths")
	public static final String SERVLET_PATH = "/services/counterServlet";

	@Property(name = "sling.auth.requirements", propertyPrivate = false)
	private static final String[] AUTH_REQUIREMENT = { "+" + SERVLET_PATH };

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void doPost(SlingHttpServletRequest req, SlingHttpServletResponse res) throws ServletException, IOException {

		res.setContentType("application/json");
		JSONArray dataArray = new JSONArray();

		log.debug("Post method in servlet");

		String sign = req.getParameter("sign");

		log.info("Sign: " + sign);

		try {

			String resourcePath = "/content/aemstartup/data";

			ResourceResolver obj2 = req.getResourceResolver();

			Resource res2 = obj2.getResource(resourcePath);

			Node node=res2.adaptTo(Node.class);

			log.info("Node: " + node.toString());

			Session session=node.getSession();

			String oldVal = node.getProperty("value").getValue().toString();

			long newVal;

			if(sign.equals("plus")){
				newVal = Integer.parseInt(oldVal) + 1;
			}
			else if(sign.equals("minus")){
				newVal = Integer.parseInt(oldVal) - 1;
			}
			else{
				newVal = 0;
			}

			node.setProperty("value",  newVal);

			session.save();

			dataArray.put(new JSONObject().put("newVal",newVal));

			dataArray.write(res.getWriter());

		} catch (Exception e){
			log.info("Exception: " + e.getMessage());
			throw new ServletException();
		}
	}
}