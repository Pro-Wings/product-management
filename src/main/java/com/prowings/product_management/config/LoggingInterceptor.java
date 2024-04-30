package com.prowings.product_management.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoggingInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println(">>>>>> Incoming request: ");
		System.out.println(">>>>>> request Method : " + request.getMethod());
		System.out.println(">>>>>> request URI  : " + request.getRequestURI());
		System.out.println(">>>>>> request Body : " + getRequestBody(request));
		
		// Wrap the response to capture the response body
        ResponseLoggingWrapper responseWrapper = new ResponseLoggingWrapper(response);
        request.setAttribute("responseWrapper", responseWrapper);

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
//		System.out.println(">>>>>> Outgoing Response : ");
//		System.out.println(">>>>>> Response Body : " + getResponseBody(response));
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	       ResponseLoggingWrapper responseWrapper = (ResponseLoggingWrapper) request.getAttribute("responseWrapper");
			System.out.println(">>>>>> Outgoing Response : ");
			System.out.println(">>>>>> request Body : " + responseWrapper.getContent());

	}

	private String getRequestBody(HttpServletRequest request) {
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
			return bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
		} catch (IOException e) {
			System.out.println("Error reading request body:" + e.getMessage());
			return "Error reading request body";
		}
	}

	private String getResponseBody(HttpServletResponse response) {
		 if (response instanceof ResponseLoggingWrapper) {
	            return ((ResponseLoggingWrapper) response).getContent();
	        }
	        return "Response body logging not implemented";
	}
}
