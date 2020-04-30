package com.ruoyi.system.service.impl;

import com.ruoyi.system.service.CuccMobileService;
import org.springframework.stereotype.Service;


@Service
public class CuccMobileServiceImpl implements CuccMobileService {

    @Override
    public String testCucc() {
        return "欢迎使用中国联通服务";
    }

}
