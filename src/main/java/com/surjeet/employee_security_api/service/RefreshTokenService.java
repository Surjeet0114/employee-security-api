package com.surjeet.employee_security_api.service;

import com.surjeet.employee_security_api.entity.RefreshToken;
import com.surjeet.employee_security_api.entity.User;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(User user);

    RefreshToken verifyRefreshToken(String token);

    void deleteRefreshToken(User user);
}
