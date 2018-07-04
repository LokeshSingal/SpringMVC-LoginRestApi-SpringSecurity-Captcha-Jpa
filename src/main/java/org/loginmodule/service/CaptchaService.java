package org.loginmodule.service;

import java.net.URI;

import org.loginmodule.captcha.CaptchaSettings;
import org.loginmodule.captcha.GoogleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Service
public class CaptchaService implements ICaptchaService {

	@Autowired
	RestOperations restTemplate;

	@Autowired
	CaptchaSettings captchaSettings;

	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Override
	public void processResponse(String response, String ip) {
		URI verifyUri = URI
				.create(String
						.format("https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s&remoteip=%s",
								getReCaptchaSecret(), response, ip));
		
		GoogleResponse googleResponse = (GoogleResponse) restTemplate.getForObject(verifyUri,
				GoogleResponse.class);

		if (!googleResponse.isSuccess()) {
			throw new RuntimeException(
					"reCaptcha was not successfully validated");
		}

	}

	private Object getReCaptchaSecret() {
		return captchaSettings.getSecretKey();
	}

}
