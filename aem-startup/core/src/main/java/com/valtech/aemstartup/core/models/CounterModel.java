package com.valtech.aemstartup.core.models;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.ValueFormatException;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.version.VersionException;

import org.apache.sling.api.resource.Resource;
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
    }
    
    public void increase(){
    	log.info("Entering the INCREASE METHOD");
    	Resource componentResource = getResourceResolver().getResource("/content/aemstartup/data");
    	if(componentResource==null)
    	{
    		log.info("Data node not found.");
    		//create the data node
    		//Get the session
    		Session session = getResourceResolver().adaptTo(Session.class);

    		log.info("Creating session: " + session.toString());
    		//Create the Node
    		try {
				Node node = JcrUtil.createPath("/content/aemstartup/data", JcrConstants.NT_UNSTRUCTURED,session);
				node.setProperty("value", 1);
				log.info("Create node succeeded: " + node.toString());
				session.save();
			} catch (RepositoryException e) {
				log.info("Creating node not successful: " + e.getMessage());
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	else{
    		Session session = getResourceResolver().adaptTo(Session.class);
    		Node dataNode = componentResource.adaptTo(Node.class);
    		try {
    	 		
    			log.info("Try and change value node");
    			String oldValue = dataNode.getProperty("value").toString();
    			int ov = Integer.parseInt(oldValue);
				dataNode.setProperty("value", ov++);
				
				session.save();
			} catch (ValueFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (VersionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LockException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ConstraintViolationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
