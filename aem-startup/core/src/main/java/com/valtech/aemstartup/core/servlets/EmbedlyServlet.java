package com.valtech.aemstartup.core.servlets;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.embedly.api.Api;

@Component(metatype = true)
@SlingServlet(
		methods = {"GET"},
		generateComponent = false
		)
@Service
public class EmbedlyServlet extends SlingAllMethodsServlet {
	private static final long serialVersionUID = 1L;

	@Property(name = "sling.servlet.paths")
	public static final String SERVLET_PATH = "/services/embedly";

	@Property(name = "sling.auth.requirements", propertyPrivate = false)
	private static final String[] AUTH_REQUIREMENT = { "+" + SERVLET_PATH };

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse res) throws ServletException, IOException {
		log.info("Welcome to the servlet!");
		res.setContentType("application/json");
		JSONArray dataArray = new JSONArray();
		String url = "http://www.dr.dk/nyheder/kultur/historie/10-historikere-vurderer-er-vi-paa-vej-mod-endnu-en-kold-krig";

		log.info("URL: " +  url);
		try {
			log.info("try");
			
		      Api api = new Api("Mozilla/5.0 (compatible; demo; kari.thrastarson@valtech.com)",
	                    "1418d7faaf074bdf954da4cf8049731b"); // <-- put key here
//		      HashMap<String, Object> params = new HashMap<String, Object>();
//	      params = new HashMap<String, Object>();
//	      params.put("url", "http://www.guardian.co.uk/media/2011/jan/21/andy-coulson-phone-hacking-statement");
//
//	      dataArray = api.oembed(params);
//		      
			dataArray.put(new JSONObject().put("url",url));
			dataArray.write(res.getWriter());
		} catch (org.json.JSONException e) {
	
			e.printStackTrace();
		}
	}
}