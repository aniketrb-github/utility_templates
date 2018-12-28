package com.web.central.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.utility.config.UnauthenticatedRestAction;
import com.utility.vo.response.RestResponse;

public interface IApplicationServiceHandler {

	public ResponseEntity<RestResponse> process(UnauthenticatedRestAction action, final String sessionId, Object param,
			HttpServletRequest httpRequest, HttpServletResponse response);

}
