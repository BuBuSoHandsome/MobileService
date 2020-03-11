package com.ruoyi.system.mobile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.moblie.MobileUtil;
import com.ruoyi.system.domain.mobileRequest.ChooseNumberbusinessRequest;
import com.ruoyi.system.domain.mobileRequest.JDCheckAddressRequest;
import com.ruoyi.system.domain.mobileRequest.QueryChooseNumberListRequest;
import com.ruoyi.system.domain.mobileRequest.QueryDiscountNumberListRequest;
import com.ruoyi.system.domain.mobileResponse.ChooseNumberbusinessResponse;
import com.ruoyi.system.domain.mobileResponse.QueryChooseNumberListResponse;
import com.ruoyi.system.domain.mobileResponse.QueryDiscountNumberListResponse;
import com.ruoyi.system.mapper.MobileUrlMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Datetime:    2020/3/11   14:13
 * Author:      bjl
 */


@Service
public class MobileResponseService {

    @Resource
    private MobileUrlMapper mobileUrlMapper;


    public QueryDiscountNumberListResponse getQueryDiscountNumberList(QueryDiscountNumberListRequest request){
        String jsonString = MobileUtil.getResponse(
                mobileUrlMapper.selectMobileUrlByEumn("QueryDiscountNumberList").getUrl(),
                MobileUtil.getBodyByClass(request));
        JSONObject jsonObj = JSONObject.parseObject(jsonString);//转JSONObject对象
        String brand = JSONObject.parseObject(jsonObj.getString("result")).getString("brand")  ;
        if(null==jsonObj.getString("respcode")||!"0".equals(jsonObj.getString("respcode"))){
            throw new RuntimeException("查询选号查询号码可选优惠异常");
        }
        String infos = JSONObject.parseObject(jsonObj.getString("result")).getString("infos").replaceAll("\\[","").replaceAll("\\]","");
        JSONObject infosJson = JSONObject.parseObject(infos);
        String price = infosJson.getString("price");
        String account=infosJson.getString("account");
        String solution_id=infosJson.getString("solution_id");
        String solution_name =infosJson.getString("solution_name");
        QueryDiscountNumberListResponse response = new QueryDiscountNumberListResponse();
        response.setBrand(brand == null ? "" : brand);
        response.setPrice(price ==null ? "":price);
        response.setAccount(account == null ? "" : account);
        response.setSolution_id(solution_id == null ? "" :solution_id);
        response.setSolution_name(solution_name == null ? "" :solution_name);
        return  response;
    }

    public QueryChooseNumberListResponse getQueryChooseNumberList(QueryChooseNumberListRequest request){
        JSONObject queryChooseNumberListString = JSONObject.parseObject(
                MobileUtil.getResponse(
                        mobileUrlMapper.selectMobileUrlByEumn("QueryChooseNumberList").getUrl(),
                        MobileUtil.getBodyByClass(request)));
        //获取选号号码列表失败
        if(null==queryChooseNumberListString||null==queryChooseNumberListString.getString("respcode")
                ||!"0".equals(queryChooseNumberListString.getString("respcode"))){
            throw new RuntimeException("查询选号号码列表异常");
        }
        List<QueryChooseNumberListResponse> numberLists = JSONArray.parseArray(JSONObject.parseObject(
                queryChooseNumberListString.getString("result")).getString( "infos"), QueryChooseNumberListResponse.class);
        QueryChooseNumberListResponse response = new QueryChooseNumberListResponse();
        for(QueryChooseNumberListResponse listResponse:numberLists){
            if(null!=listResponse&&"50".equals(listResponse.getCommtype())){
                response = listResponse;
                break;
            }
        }
        return response;
    }

    public boolean JDCheakAddress(JDCheckAddressRequest request){
        String jsonString = MobileUtil.getResponse(
                mobileUrlMapper.selectMobileUrlByEumn("QueryDiscountNumberList").getUrl(),
                MobileUtil.getBodyByClass(request));
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        if(null!=jsonObject && "0".equals(jsonObject.getString("respcode")) && "可以京配".equals(jsonObject.getString("respdesc"))){
            return true;
        }
        return false;
    }



    public ChooseNumberbusinessResponse getChooseNumberbusiness (ChooseNumberbusinessRequest request){
        String jsonString = MobileUtil.getResponse(
                mobileUrlMapper.selectMobileUrlByEumn("ChooseNumberBusiness").getUrl(),
                MobileUtil.getBodyByClass(request));
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        System.out.println("状态码："+jsonObject.getString("respcode"));

        if(null == jsonObject.getString("respcode") || !"0".equals(jsonObject.getString("respcode"))){
            throw new RuntimeException("查询放号单品业务信息能力异常");
        }
        String infos = jsonObject.getJSONObject("result").getString("businessinfo").replaceAll("\\[","").replaceAll("\\]","");
        JSONObject jsonInfos = JSONObject.parseObject(infos);
        ChooseNumberbusinessResponse response = new ChooseNumberbusinessResponse();
        String packageCode = jsonInfos.getString("packagecode");
        String packageName = jsonInfos.getString("packagename");
        response.setPackageCode(packageCode == null ? "" : packageCode);
        response.setPackageName(packageName == null ? "" : packageName);
        return  response;
    }

}
