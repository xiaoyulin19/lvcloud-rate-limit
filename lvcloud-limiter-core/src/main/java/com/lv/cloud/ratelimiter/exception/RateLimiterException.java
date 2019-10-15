package com.lv.cloud.ratelimiter.exception;

public class RateLimiterException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7520394061973221911L;
	

	public RateLimiterException(String message) {
		super(message);
	}

	public RateLimiterException() {
		super();
	}

	public RateLimiterException(String message, Throwable cause,
                                boolean enableSuppression, boolean writableStackTrace) {
//		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RateLimiterException(String message, Throwable cause) {
		super(message, cause);
	}

	public RateLimiterException(Throwable cause) {
		super(cause);
	}
	
}
