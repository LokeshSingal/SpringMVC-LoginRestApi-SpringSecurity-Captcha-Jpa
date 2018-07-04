package org.loginmodule.service;

public interface ICaptchaService {
	
	public void processResponse(String response, String ip);

}
