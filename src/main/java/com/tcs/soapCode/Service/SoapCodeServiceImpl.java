package com.tcs.soapCode.Service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tcs.soapCode.Request.Request;
@Service
public class SoapCodeServiceImpl implements SoapCodeService{
	@Inject
	private RestTemplate restTemplate;
	@Value("${soapCodeGenerationUrl}")
	private String soapCodeGenerationUrl;
	@Value("${soapClientUrl}")
	private String soapClientUrl;
	@Value("${soapResponseCustomizationUrl}")
	private String soapResponseCustomizationUrl;
	@Override
	public Object getResponse(Request request) {
		 restTemplate.postForObject(soapCodeGenerationUrl,request, String.class );
		Object response =restTemplate.postForObject(soapClientUrl,request, Object.class );
		request.setServiceResponse(response);
		  restTemplate.postForObject(soapResponseCustomizationUrl,request, String.class );
		  return response;
		 
	}

}
