package com.web.controllers.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dao.password.reset.token.EmailVO;
import com.utility.config.UnauthenticatedRestAction;
import com.utility.constant.ApplicationConstants;
import com.utility.vo.response.RestResponse;
import com.web.central.handler.IApplicationServiceHandler;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/resetPassword")
@Api(value = "/ResetPassword")
public class PasswordController {
	
	@Autowired
	private IApplicationServiceHandler applicationServiceHandler;
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Sends Reset Password Link", notes = "Sends Reset Password Link")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
	})
	public ResponseEntity<RestResponse> resetPasswordRequest(@CookieValue(value = ApplicationConstants.APPLICATION_SESSIONID, defaultValue = "") final String sessionId,
			@RequestBody(required = true) EmailVO emailVO, HttpServletRequest request, HttpServletResponse response) {
		
		return applicationServiceHandler.process(UnauthenticatedRestAction.SEND_RESET_PASSWORD_LINK, null, emailVO, request, response);
	}

}
