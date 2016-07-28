package com.valtech.aemstartup.core.models;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.adobe.cq.sightly.WCMUse;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.commons.jcr.JcrUtil;

public class CounterModel extends WCMUse{

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void activate() {
		log.info("Entering the Counter Model");
		Resource componentResource = getResourceResolver().getResource("/content/aemstartup/data");
		if(componentResource == null)
		{
			log.info("Data node not found. Start creating it");
	
			Session session = getResourceResolver().adaptTo(Session.class);

			//Create the Node
			try {
				Node node = JcrUtil.createPath("/content/aemstartup/data", JcrConstants.NT_UNSTRUCTURED,session);
				node.setProperty("value", 1);
				log.info("Node created successfully. Node info: " + node.toString());
				session.save();
			} catch (RepositoryException e) {
				log.info("Creating node not successful: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
}