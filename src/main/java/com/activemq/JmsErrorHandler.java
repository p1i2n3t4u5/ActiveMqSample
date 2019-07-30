package com.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

@Service
public class JmsErrorHandler implements ErrorHandler {
	  private static final Logger LOGGER =
		      LoggerFactory.getLogger(Sender.class);
 
    @Override
    public void handleError(Throwable t) {
    	LOGGER.error("Error occured in JMS");
    }
 
}
