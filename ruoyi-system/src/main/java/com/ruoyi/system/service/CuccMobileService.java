package com.ruoyi.system.service;

import com.ruoyi.system.domain.cuccMobileRequest.CheckNumRequest;
import com.ruoyi.system.domain.cuccMobileRequest.CheckUserRequest;
import com.ruoyi.system.domain.cuccMobileRequest.SelectNumRequest;
import com.ruoyi.system.domain.cuccMobileResponse.CheckNumResponse;
import com.ruoyi.system.domain.cuccMobileResponse.CheckUserResponse;

import java.util.List;

public interface CuccMobileService {

    String testCucc();

    CheckUserResponse checkUser(CheckUserRequest request);

    CheckNumResponse checkNum(CheckNumRequest request);

    List<String> selectNum(SelectNumRequest request);



}
