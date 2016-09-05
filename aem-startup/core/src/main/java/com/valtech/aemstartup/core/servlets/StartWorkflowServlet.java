package com.valtech.aemstartup.core.servlets;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Session;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.workflow.WorkflowService;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkflowData;
import com.day.cq.workflow.model.WorkflowModel;


@SlingServlet(
		paths="/bin/startworkflow",
		methods = "GET",
		metatype=true
		)
@Service
public class StartWorkflowServlet extends SlingAllMethodsServlet {
	//private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	//	@Property(name = "sling.auth.requirements", propertyPrivate = false)
	//	private static final String[] AUTH_REQUIREMENT = { "+" + SERVLET_PATH };

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	private Session session;

	@Reference
	private WorkflowService workflowService;


	@Reference
	private ResourceResolverFactory resolverFactory;

	@Override
	protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws ServletException, IOException {

		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put(ResourceResolverFactory.SUBSERVICE, "readService");

		ResourceResolver resourceResolver = null;
		try{

			resourceResolver = resolverFactory.getAdministrativeResourceResolver(null);
			session = resourceResolver.adaptTo(Session.class);

			//Create a workflow session
			WorkflowSession wfSession = workflowService.getWorkflowSession(session);

			// Get the workflow model
			WorkflowModel wfModel = wfSession.getModel("/etc/workflow/models/sensor/jcr:content/model");


			// Get the workflow data. None needed at this point
			WorkflowData wfData = wfSession.newWorkflowData("JCR_PATH", "/dam/yourasset");

			// Run the Workflow.
			wfSession.startWorkflow(wfModel, wfData);
			wfSession.getSession().save();

		}catch(Exception e){
			log.error("Error in servlet: "+ e.getMessage());
			resp.setStatus(500);
		}
	}
}