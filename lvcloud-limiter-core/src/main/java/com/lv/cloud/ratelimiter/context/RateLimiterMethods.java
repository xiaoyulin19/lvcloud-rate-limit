package com.lv.cloud.ratelimiter.context;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "methods")
public class RateLimiterMethods implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8482276875990073871L;
	
	private List<RateLimiterMethod> rateLimiterMethodList;

	@XmlElements(value = { @XmlElement(name = "method", type = RateLimiterMethod.class) })
	public List<RateLimiterMethod> getRateLimiterMethodList() {
		return rateLimiterMethodList;
	}

	public void setRateLimiterMethodList(
			List<RateLimiterMethod> rateLimiterMethodList) {
		this.rateLimiterMethodList = rateLimiterMethodList;
	}

	
	
}
