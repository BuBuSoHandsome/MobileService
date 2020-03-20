package com.ruoyi.system.mobile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.enums.MobileUrl;
import com.ruoyi.common.enums.RedisEnum;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.moblie.MobileUtil;
import com.ruoyi.common.utils.redis.RedisUtil;
import com.ruoyi.system.domain.Order;
import com.ruoyi.system.domain.OrderLogistics;
import com.ruoyi.system.domain.mobileRequest.*;
import com.ruoyi.system.domain.mobileResponse.*;
import com.ruoyi.system.mapper.OrderLogisticsMapper;
import com.ruoyi.system.mapper.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    private OrderMapper orderMapper;

    @Resource
    private OrderLogisticsMapper orderLogisticsMapper;

    @Autowired
    private RedisUtil redisUtil;

    public QueryDiscountNumberListResponse getQueryDiscountNumberList(QueryDiscountNumberListRequest request){
        String jsonString = MobileUtil.getResponse(
                MobileUrl.QueryDiscountNumberList.getUrl(),
                MobileUtil.getBodyByClass(request));
        JSONObject jsonObj = JSONObject.parseObject(jsonString);//转JSONObject对象
        String brand = JSONObject.parseObject(jsonObj.getString("result")).getString("brand") ;
        if(null==jsonObj.getString("respcode")||!"0".equals(jsonObj.getString("respcode"))){
            log.info("查询选号查询号码可选优惠异常:"+jsonObj.toJSONString());
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
            log.info("查询选号号码列表异常:"+queryChooseNumberListString.toJSONString());
        }
        List<QueryChooseNumberListResponse> numberLists = JSONArray.parseArray(JSONObject.parseObject(
                queryChooseNumberListString.getString("result")).getString( "infos"), QueryChooseNumberListResponse.class);
        //成功状态下，可能没有号码选
        if(null==numberLists||numberLists.isEmpty()){
            throw new RuntimeException("下单失败，该卡选号号码列表为空--卡id为（"+request.getSid()+")");
        }
        QueryChooseNumberListResponse response = new QueryChooseNumberListResponse();
        for(QueryChooseNumberListResponse listResponse:numberLists){
            if(null!=listResponse && "50".equals(listResponse.getCommtype())){
                if(redisUtil.hasKey(RedisEnum.SERVNUMBER+":"+listResponse.getMobileno())){
                    continue;
                }else{
                    redisUtil.set(RedisEnum.SERVNUMBER+":"+listResponse.getMobileno(), request.getPackagecode(), 86400);
                    response = listResponse;
                    break;
                }
            }else {
                throw new RuntimeException("下单失败，该卡选号号码列表为空--卡id为（"+request.getSid()+")");
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
        log.info("京东地址校验异常:"+jsonObject.toJSONString());
        return false;
    }



    public ChooseNumberbusinessResponse getChooseNumberbusiness (ChooseNumberbusinessRequest request){
        String jsonString = MobileUtil.getResponse(
                MobileUrl.ChooseNumberBusiness.getUrl(),
                MobileUtil.getBodyByClass(request));
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
//        System.out.println("状态码："+jsonObject.getString("respcode"));
        if(null == jsonObject.getString("respcode") || !"0".equals(jsonObject.getString("respcode"))){
            log.info("查询放号单品业务信息能力异常:"+jsonObject.toJSONString());
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


    public  DSAirpickinstallQueryOrderResponse getOrderMsg(DSAirpickinstallQueryOrderRequest request){
        String url = MobileUrl.DSAirpickinstallQueryOrder.getUrl();
        String body = MobileUtil.getBodyByClass(request);
        String jsonString = MobileUtil.getResponse(url, body);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        if(null==jsonObject  || !"0".equals(jsonObject.getString("respcode")) || !"0".equals(jsonObject.getString("resptype"))){
             log.info("查询物流信息异常:"+jsonObject.toJSONString());
        }
        JSONArray jsonArray = JSONArray.parseArray(jsonObject.getJSONObject("result").getString("order"));
        JSONObject jsonObject1 = jsonArray.getJSONObject(0);
        DSAirpickinstallQueryOrderResponse dsAirpickinstallQueryOrderResponse = new DSAirpickinstallQueryOrderResponse();
        dsAirpickinstallQueryOrderResponse.setCreateTime(jsonObject1.getString("createTime")==null ? "":jsonObject1.getString("createTime"));
        dsAirpickinstallQueryOrderResponse.setOperatorId(jsonObject1.getString("operatorId")==null ? "":jsonObject1.getString("operatorId"));
        dsAirpickinstallQueryOrderResponse.setOrderType(jsonObject1.getString("orderType")==null ? "":jsonObject1.getString("orderType"));
        dsAirpickinstallQueryOrderResponse.setAreaCode(jsonObject1.getString("areaCode")==null ? "":jsonObject1.getString("areaCode"));
        dsAirpickinstallQueryOrderResponse.setOrderRemark(jsonObject1.getString("orderRemark")==null ? "":jsonObject1.getString("orderRemark"));
        dsAirpickinstallQueryOrderResponse.setOrderStatus(jsonObject1.getString("orderStatus")==null ? "":jsonObject1.getString("orderStatus"));
        dsAirpickinstallQueryOrderResponse.setWayId(jsonObject1.getString("wayid")==null ? "":jsonObject1.getString("wayid"));
        dsAirpickinstallQueryOrderResponse.setAreaName(jsonObject1.getString("areaName")==null ? "":jsonObject1.getString("areaName"));
        dsAirpickinstallQueryOrderResponse.setUsername(jsonObject1.getString("userName")==null ? "":jsonObject1.getString("userName"));
        dsAirpickinstallQueryOrderResponse.setFinishTime(jsonObject1.getString("finishTime")==null ? "":jsonObject1.getString("finishTime"));
        dsAirpickinstallQueryOrderResponse.setChnlCode(jsonObject1.getString("chnlCode")==null ? "":jsonObject1.getString("chnlCode"));
        dsAirpickinstallQueryOrderResponse.setOrderBigType(jsonObject1.getString("orderBigType") == null ? "":jsonObject1.getString("orderBigType"));
        dsAirpickinstallQueryOrderResponse.setServnumber(jsonObject1.getString("servnumber") == null ? "":jsonObject1.getString("servnumber"));
        dsAirpickinstallQueryOrderResponse.setOrderId(jsonObject1.getString("orderId") ==null ? "" : jsonObject1.getString("orderId"));
        dsAirpickinstallQueryOrderResponse.setExpressno(jsonObject1.getString("expressno")==null ? "暂无物流单号" : jsonObject1.getString("expressno"));
        return dsAirpickinstallQueryOrderResponse;
    }



    public Boolean getAirpickinstallnewOrder(Order order){

        //查询选号列表
        QueryChooseNumberListRequest queryChooseNumberListRequest = new QueryChooseNumberListRequest();
//        queryChooseNumberListRequest.setSid(order.getSid()); 非必填
        queryChooseNumberListRequest.setRegion("200");
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

        /**
         * 此处判断是否支持京东配送  不支持京东配送则不再调用
         * 支持   ReceiveType = 4  OfflineCard = 3
         */
        if(jdCheck){
            newOrderParams.setReceiveType("4");
            newOrderParams.setOfflineCard("3");
        }else{
            throw new RuntimeException("不支持京东配送地址："+order.getAddress());
        }
        //渠道编码 工号必传
        newOrderParams.setWayid("GZ08EC200043");
        newOrderParams.setOperatorId("AGZC00000BYJ");
        //号码归属地市编码
        newOrderParams.setAreaCode("200");
        //号码归属地市名称
        newOrderParams.setAreaName("广州");
        //新购号码
        newOrderParams.setServnumber(queryChooseNumberListResponse.getMobileno());
        //品牌
        newOrderParams.setBrand(queryDiscountNumberListResponse.getBrand());
        //受理方式 代客下单
        newOrderParams.setAcceptType("2");
        //订单金额
        newOrderParams.setOrderAmount(queryDiscountNumberListResponse.getPrice());
        //支付方式
        newOrderParams.setPayWay("1");
        //配送省份 省内不用填
        if(!"200".equals(order.getProvincecode())){
            newOrderParams.setProvince(order.getProvince());
        }
        //真实姓名
        newOrderParams.setUserName(order.getRealname());
        //配送地址
        newOrderParams.setAddress(order.getAddress());
        //配送地市中文
        newOrderParams.setAddressCity(order.getAddressCity());
        //联系方式
        newOrderParams.setTelno(order.getPhone());
        //证件类型
        newOrderParams.setCerType(order.getCardtype());
        //证件号码
        newOrderParams.setCerNo(order.getCardid());
        //主套餐id
        newOrderParams.setMainprodid(chooseNumberbusinessResponse.getPackageCode());
        //主套餐名称
        newOrderParams.setMainprodname(chooseNumberbusinessResponse.getPackageName());
        //营销方案编码
        newOrderParams.setGoodsId(queryDiscountNumberListResponse.getProductid());
        //营销方案名称
        newOrderParams.setGoodsName(queryDiscountNumberListResponse.getSolution_name());
        //内含话费
        newOrderParams.setCharge(queryDiscountNumberListResponse.getAccount());
        //活动id
        newOrderParams.setOfferCompId(queryDiscountNumberListResponse.getSolution_id());
        //运行商
        newOrderParams.setOrderId(order.getFdId());
        //商品编码
        newOrderParams.setOfferId(queryChooseNumberListResponse.getCommid());

        request.setNewOrderParams(newOrderParams);
        String jsonString = MobileUtil.getResponse(
                MobileUrl.AirpickinstallnewOrder.getUrl(),
                MobileUtil.getBodyByClass(request));
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        if(null==jsonObject || !"0".equals(jsonObject.getString("respcode"))){
            //抛异常
            log.info("线上下单异常response："+jsonObject);
            //更新订单表状态
            orderMapper.updateOrder(new Order(){{
                setFdId(newOrderParams.getOrderId());
                setStatus("3");
                setCreateTime(DateUtils.getNowTime());
            }});
            throw new  RuntimeException(jsonObject.getString("respdesc"));
        }
        //更新订单表状态
        orderMapper.updateOrder(new Order(){{
            setFdId(newOrderParams.getOrderId());
            setStatus("1");
            setCreateTime(DateUtils.getNowTime());
        }});
        //新增物流表数据
        DSAirpickinstallQueryOrderResponse response = this.getOrderMsg(new DSAirpickinstallQueryOrderRequest(){{ setOrderId(order.getFdId());}});
        OrderLogistics orderLogistics = new OrderLogistics();
        BeanUtils.copyProperties(response, orderLogistics);
        orderLogistics.setFdId(order.getFdId());
        orderLogisticsMapper.insertOrderLogistics(orderLogistics);
        return true;
    }

}
