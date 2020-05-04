package com.ruoyi.system.mobile;

import com.ruoyi.system.domain.cuccMobileResponse.SelectNumResponse;

import java.util.List;

/**
 * @Author Alan
 * @Date 2020/5/4 13:54
 * @Version 1.0
 */

public interface CuccMobileResponseService {

    List<String> selectNumber(SelectNumResponse response);

}
