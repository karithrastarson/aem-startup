package com.valtech.aemstartup.core.models;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.ValueFormatException;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.version.VersionException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.commons.jcr.JcrUtil;

public class CounterModel extends WCMUse{

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	private int value = 0;

	@Override
	public void activate() {
		log.info("Entering the Counter Model");
		Resource componentResource = getResourceResolver().getResource("/content/aemstartup/data");
		if(componentResource==null)
		{
			log.info("Data node not found. Start creating it");
			//create the data node
			//Get the session
			Session session = getResourceResolver().adaptTo(Session.class);


			//Create the Node
			try {
				Node node = JcrUtil.createPath("/content/aemstartup/data", JcrConstants.NT_UNSTRUCTURED,session);
				node.setProperty("value", 1);
				log.info("Node created successfully. Node info: " + node.toString());
				session.save();
			} catch (RepositoryException e) {
				log.info("Creating node not successful: " + e.getMessage());
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else{
			log.info("Here I have found the data node");
			try {
				Node node=componentResource.adaptTo(Node.class);
				Session session;
				session = node.getSession();
				value = Integer.parseInt(node.getProperty("value").getValue().toString());
				session.save();
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	public int getValue(){
		return value;
	}
}
