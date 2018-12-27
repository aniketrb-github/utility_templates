package com.services.user;

import com.dao.password.reset.token.EmailVO;
import com.utility.exception.AppException;
import com.utility.vo.response.RestResponse;

public interface IUserServices {

	RestResponse sendResetPasswordLink(EmailVO emailVO) throws AppException;

}
