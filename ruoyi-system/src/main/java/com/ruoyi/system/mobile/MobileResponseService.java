package com.ruoyi.system.mobile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.enums.MobileUrl;
import com.ruoyi.common.utils.moblie.MobileUtil;
import com.ruoyi.system.domain.Order;
import com.ruoyi.system.domain.mobileRequest.*;
import com.ruoyi.system.domain.mobileResponse.AirpickinstallnewOrderResponse;
import com.ruoyi.system.domain.mobileResponse.ChooseNumberbusinessResponse;
import com.ruoyi.system.domain.mobileResponse.QueryChooseNumberListResponse;
import com.ruoyi.system.domain.mobileResponse.QueryDiscountNumberListResponse;
import com.ruoyi.system.mapper.MobileUrlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * Datetime:    2020/3/11   14:13
 * Author:      bjl
 */


@Service
public class MobileResponseService {

    private static final Logger log = LoggerFactory.getLogger(MobileResponseService.class);

    @Resource
    private MobileUrlMapper mobileUrlMapper;


    public QueryDiscountNumberListResponse getQueryDiscountNumberList(QueryDiscountNumberListRequest request){
        String jsonString = MobileUtil.getResponse(
                MobileUrl.QueryDiscountNumberList.getUrl(),
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
        String productid =infosJson.getString("productid");
        QueryDiscountNumberListResponse response = new QueryDiscountNumberListResponse();
        response.setBrand(brand == null ? "" : brand);
        response.setPrice(price ==null ? "":price);
        response.setAccount(account == null ? "" : account);
        response.setSolution_id(solution_id == null ? "" :solution_id);
        response.setSolution_name(solution_name == null ? "" :solution_name);
        response.setProductid(productid == null ?  "":productid);
        return  response;
    }

    public QueryChooseNumberListResponse getQueryChooseNumberList(QueryChooseNumberListRequest request){
        JSONObject queryChooseNumberListString = JSONObject.parseObject(
                MobileUtil.getResponse(
                        MobileUrl.QueryChooseNumberList.getUrl(),
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
                MobileUrl.JDCheckAddress.getUrl(),
                MobileUtil.getBodyByClass(request));
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        if(null!=jsonObject && "0".equals(jsonObject.getString("respcode")) && "可以京配".equals(jsonObject.getString("respdesc"))){
            return true;
        }
        return false;
    }



    public ChooseNumberbusinessResponse getChooseNumberbusiness (ChooseNumberbusinessRequest request){
        String jsonString = MobileUtil.getResponse(
                MobileUrl.ChooseNumberBusiness.getUrl(),
                MobileUtil.getBodyByClass(request));
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
//        System.out.println("状态码："+jsonObject.getString("respcode"));
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






    public AirpickinstallnewOrderResponse getAirpickinstallnewOrder(Order order){

        //查询选号列表
        QueryChooseNumberListRequest queryChooseNumberListRequest = new QueryChooseNumberListRequest();
        queryChooseNumberListRequest.setSid(order.getSid());
        queryChooseNumberListRequest.setRegion(order.getProvincecode());
        queryChooseNumberListRequest.setTag("0");
        queryChooseNumberListRequest.setPackagecode(order.getPack());
        QueryChooseNumberListResponse queryChooseNumberListResponse = this.getQueryChooseNumberList(queryChooseNumberListRequest);

        //查询放号单品业务信息能力
        ChooseNumberbusinessRequest chooseNumberbusinessRequest = new ChooseNumberbusinessRequest();
        chooseNumberbusinessRequest.setTypecode(order.getPack());
        ChooseNumberbusinessResponse chooseNumberbusinessResponse = this.getChooseNumberbusiness(chooseNumberbusinessRequest);

        //京东地址校验是否配送
        JDCheckAddressRequest jdCheckAddressRequest = new JDCheckAddressRequest();
        jdCheckAddressRequest.setAddress(order.getAddress());
        jdCheckAddressRequest.setProvinceCode(order.getProvincecode());
        jdCheckAddressRequest.setEparchyCode(order.getEparchycode());
        jdCheckAddressRequest.setCityCode(order.getCitycode());
        boolean jdCheck = this.JDCheakAddress(jdCheckAddressRequest);

        //选号查询号码可选优惠
        QueryDiscountNumberListRequest queryDiscountNumberListRequest = new QueryDiscountNumberListRequest();
        queryDiscountNumberListRequest.setMobileno(queryChooseNumberListResponse.getMobileno());
        QueryDiscountNumberListResponse queryDiscountNumberListResponse = this.getQueryDiscountNumberList(queryDiscountNumberListRequest);

        AirpickinstallnewOrderRequest request = new AirpickinstallnewOrderRequest();
        newOrderParams newOrderParams = new newOrderParams();
        //渠道编码 工号必传
        newOrderParams.setWayid("GZ08EC200043");
        newOrderParams.setOperatorid("AGZC00000BYJ");
        //号码归属地市编码
        newOrderParams.setAreacode(queryChooseNumberListResponse.getRegion());
        //号码归属地市名称
        newOrderParams.setAreaname(queryChooseNumberListResponse.getCityname());
        //新购号码
        newOrderParams.setServnumber(queryChooseNumberListResponse.getMobileno());
        //品牌
        newOrderParams.setBrand(queryDiscountNumberListResponse.getBrand());
        //受理方式 代客下单
        newOrderParams.setAccepttype("2");
        //此处判断是否支持京东配送 支持4 不支持则0
        if(jdCheck){
            newOrderParams.setReceivetype("4");
        }else{
            newOrderParams.setReceivetype("0");
        }
        //订单金额
        newOrderParams.setOrderamount(queryDiscountNumberListResponse.getPrice());
        //支付方式
        newOrderParams.setPayway("1");
        //配送省份
        newOrderParams.setProvince(order.getProvince());
        //真实姓名
        newOrderParams.setUsername(order.getRealname());
        //配送地址
        newOrderParams.setAddress(order.getAddress());
        //配送地市中文
        newOrderParams.setAddresscity(order.getAddressCity());
        //联系方式
        newOrderParams.setTelno(order.getPhone());
        //证件类型
        newOrderParams.setCertype(order.getCardtype());
        //证件号码
        newOrderParams.setCerno(order.getCardid());
        //主套餐id
        newOrderParams.setMainprodid(chooseNumberbusinessResponse.getPackageCode());
        //主套餐名称
        newOrderParams.setMainprodname(chooseNumberbusinessResponse.getPackageName());
        //营销方案编码
        newOrderParams.setGoodsid(queryDiscountNumberListResponse.getProductid());
        //营销方案名称
        newOrderParams.setGoodsname(queryDiscountNumberListResponse.getSolution_name());
        //内含话费
        newOrderParams.setCharge(queryDiscountNumberListResponse.getAccount());
        //活动id
        newOrderParams.setOffercompid(queryDiscountNumberListResponse.getSolution_id());
        //订单id
        newOrderParams.setOrderid(UUID.randomUUID().toString().replaceAll("-",""));
        //商品编码
        newOrderParams.setOfferId(queryChooseNumberListResponse.getCommid());
        //配卡方式
        newOrderParams.setOfflinecard("0");
        request.setNewOrderParams(newOrderParams);
        String jsonString = MobileUtil.getResponse(
                MobileUrl.AirpickinstallnewOrder.getUrl(),
                MobileUtil.getBodyByClass(request));
        JSONObject jsonObject = JSONObject.parseObject(jsonString);

        AirpickinstallnewOrderResponse response = new AirpickinstallnewOrderResponse();
        return response;
    }

}
