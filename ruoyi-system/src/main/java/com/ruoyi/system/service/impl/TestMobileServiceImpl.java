package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.moblie.MobileUtil;
import com.ruoyi.system.domain.ChooseNumberColumn;
import com.ruoyi.system.domain.mobileRequest.ChooseNumberbusinessRequest;
import com.ruoyi.system.domain.mobileRequest.JDCheckAddressRequest;
import com.ruoyi.system.domain.mobileResponse.ChooseNumberColumnResponse;
import com.ruoyi.system.mapper.ChooseNumberColumnMapper;
import com.ruoyi.system.mapper.MobileUrlMapper;
import com.ruoyi.system.service.TestMobileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;

/**
 * Datetime:    2020/2/27   15:37
 * Author:      bjl
 */

@Service
public class TestMobileServiceImpl implements TestMobileService {

    private static final Logger log = LoggerFactory.getLogger(TestMobileServiceImpl.class);

    @Resource
    private ChooseNumberColumnMapper chooseNumberColumnMapper;

    @Resource
    private MobileUrlMapper mobileUrlMapper;

    @Override
    @Transactional
    public String queryChooseNumberColumn() {
        String url = mobileUrlMapper.selectMobileUrlByEumn("QueryChooseNumberColumn").getUrl();
        Map<String,String> map = new HashMap<>();
        map.put("channel","newWap");
        JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(map));
        String body = itemJSONObj.toString();

        JSONObject responsejsonObject = JSONObject.parseObject(MobileUtil.getResponse(url,body));
        //返回状态为0
        if("0".equals(responsejsonObject.getString("respcode"))){
            String infos = JSONObject.parseObject(responsejsonObject.getString("result")).getString("infos")  ;
            List<ChooseNumberColumnResponse> lists = JSONArray.parseArray(infos, ChooseNumberColumnResponse.class);

           if(lists==null||lists.isEmpty()){
               return "接口查询数据为空";
           }

            //每次insert前把表数据清空
            chooseNumberColumnMapper.deleteAll();
            //循环赋值更新
            lists.stream().forEach(list->{
                ChooseNumberColumn chooseNumberColumn = new ChooseNumberColumn();
                chooseNumberColumn.setChannel(list.getChannel()==null ? "":list.getChannel());
                chooseNumberColumn.setConstraint(list.getConstraint() == null ? "":list.getConstraint());
                chooseNumberColumn.setDescribe(list.getDescribe() ==null ? "": list.getDescribe()) ;
                chooseNumberColumn.setPack(list.getPack() ==null ? "": list.getPack() );
                chooseNumberColumn.setPic(list.getPic() ==null ? "": list.getPic());
                chooseNumberColumn.setStatus(list.getStatus() ==null ? "": list.getStatus() );
                chooseNumberColumn.setPosition(list.getPosition() ==null ? "": list.getPosition());
                chooseNumberColumn.setText(list.getText() ==null ? "": list.getText());
                chooseNumberColumn.setSid(list.getSid() ==null ? 00000 :list.getSid());
                chooseNumberColumn.setupdatetime(DateUtils.getDateFromString(list.getUpdatetime()));
                chooseNumberColumnMapper.insertChooseNumberColumn(chooseNumberColumn);
            });
            return "选项卡类更新成功";
        }
        return "接口调用失败";
    }

    @Override
    public String chooseNumberBusiness(ChooseNumberbusinessRequest request) {
        String url = mobileUrlMapper.selectMobileUrlByEumn("ChooseNumberBusiness").getUrl();
        String body = MobileUtil.getBodyByClass(request);
        return MobileUtil.getResponse(url,body);
    }

    @Override
    public String JDCheckAddress(JDCheckAddressRequest request) {
        String url = mobileUrlMapper.selectMobileUrlByEumn("JDCheckAddress").getUrl();
        String body = MobileUtil.getBodyByClass(request);
        return MobileUtil.getResponse(url,body);
    }


    @Override
    public String testGetUrl(String eumn) {
        System.out.println("标识："+eumn);
        return mobileUrlMapper.selectMobileUrlByEumn(eumn).getUrl();
    }



}
