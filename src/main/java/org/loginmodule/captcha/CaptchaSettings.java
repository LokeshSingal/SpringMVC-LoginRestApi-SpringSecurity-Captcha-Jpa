package org.loginmodule.captcha;

import org.springframework.stereotype.Component;

@Component
public class CaptchaSettings {

	
	private String secretKey;
	private String siteKey;
	
	public CaptchaSettings() {
		this.secretKey = "6LeVS2IUAAAAAGQhRI_LB5eeJslmTxYJAhpn74bI";
		this.siteKey = "6LeVS2IUAAAAAEzJSiyeGw5zIg7FAraFF34LShmq";
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getSiteKey() {
		return siteKey;
	}
	public void setSiteKey(String siteKey) {
		this.siteKey = siteKey;
	}
	
	
}
