package com.lv.cloud.ratelimiter.context;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(name = "method")
public class RateLimiterMethod implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 92623783447679065L;

	private String name;
	
	private int type;
	
	private Long permits;
	
	private String timeunit;
	
	private String distributorChannel;

	@XmlElement(name = "name", required = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "type", required = true)
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@XmlElement(name = "permits", required = true)
	public Long getPermits() {
		return permits;
	}

	public void setPermits(Long permits) {
		this.permits = permits;
	}

	@XmlElement(name = "timeunit", required = false)
	public String getTimeunit() {
		return timeunit;
	}

	public void setTimeunit(String timeunit) {
		this.timeunit = timeunit;
	}

	@XmlElement(name = "distributorChannel", required = false)
	public String getDistributorChannel() {
		return distributorChannel;
	}

	public void setDistributorChannel(String distributorChannel) {
		this.distributorChannel = distributorChannel;
	}
	
}
