package com.ruoyi.system.service;

import com.ruoyi.system.domain.cuccMobileRequest.*;
import com.ruoyi.system.domain.cuccMobileResponse.CheckCodeResponse;
import com.ruoyi.system.domain.cuccMobileResponse.CheckNumResponse;
import com.ruoyi.system.domain.cuccMobileResponse.CheckUserResponse;
import com.ruoyi.system.domain.cuccMobileResponse.SafeCodeResponse;

import java.util.List;

public interface CuccMobileService {

    String testCucc();

    CheckUserResponse checkUser(CheckUserRequest request);

    CheckNumResponse checkNum(CheckNumRequest request);

    List<String> selectNum(SelectNumRequest request);

    SafeCodeResponse safeCode(SafeCodeRequest request);

    CheckCodeResponse checkCode(CheckCodeRequest request);


}
