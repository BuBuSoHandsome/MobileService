package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.MobileUrl;
import java.util.List;


public interface MobileUrlMapper 
{

    public MobileUrl selectMobileUrlByEumn(String eumn);


    public List<MobileUrl> selectMobileUrlList(MobileUrl mobileUrl);


    public int insertMobileUrl(MobileUrl mobileUrl);


    public int updateMobileUrl(MobileUrl mobileUrl);


    public int deleteMobileUrlById(String fdId);


    public int deleteMobileUrlByIds(String[] fdIds);
}
