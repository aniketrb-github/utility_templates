package com.services.user;

import com.dao.password.reset.token.EmailVO;
import com.utility.exception.AppException;
import com.utility.vo.response.RestResponse;

/**
 * @author Aniket Bharsakale
 *
 */
public interface IUserServices {

	RestResponse sendEmail(EmailVO emailVO) throws AppException;

}
