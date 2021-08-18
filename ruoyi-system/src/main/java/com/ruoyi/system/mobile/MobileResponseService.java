package com.ruoyi.system.mobile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.CmccConstant;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Datetime:    2020/3/11   14:13
 * Author:      bjl
 */


@Service
@Transactional(rollbackFor = Exception.class)
public class MobileResponseService {

    private static final Logger log = LoggerFactory.getLogger(MobileResponseService.class);


    @Value("${channel.wayid}")
    private String wayId;

    @Value("${channel.operatorId}")
    private String operatorId;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderLogisticsMapper orderLogisticsMapper;

    @Autowired
    private AddressResolutionService addressResolutionService;

    @Autowired
    private RedisUtil redisUtil;

    private static String SUCCESS_CODE = "0";

    public QueryChooseNumberListResponse getQueryChooseNumberList(QueryChooseNumberListRequest request){
        JSONObject queryChooseNumberListString = JSONObject.parseObject(
                MobileUtil.getResponse(
                        MobileUrl.QueryChooseNumberList.getUrl(),
                        MobileUtil.getBodyByClass(request)));
        //获取选号号码列表失败
        if(null==queryChooseNumberListString||null==queryChooseNumberListString.getString(CmccConstant.RESPCODE)
                ||!SUCCESS_CODE.equals(queryChooseNumberListString.getString(CmccConstant.RESPCODE))){
            log.info("查询选号号码列表异常:"+queryChooseNumberListString.toJSONString());
        }
        List<QueryChooseNumberListResponse> numberLists = JSONArray.parseArray(JSONObject.parseObject(
                queryChooseNumberListString.getString("result")).getString( "infos"), QueryChooseNumberListResponse.class);
        //成功状态下，可能没有号码选
        if(null==numberLists||numberLists.isEmpty()){
            throw new RuntimeException("下单失败，该卡选号号码列表为空--卡编码为（"+request.getPackagecode()+")");
        }
        QueryChooseNumberListResponse response = new QueryChooseNumberListResponse();
        //倒序list集合 尽量拿到后面的号码不会被抢
        Collections.reverse(numberLists);
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

    public JDCheckAddressResponse JDCheckAddress(JDCheckAddressRequest request){
        JDCheckAddressResponse response = new JDCheckAddressResponse();
        String jsonString = MobileUtil.getResponse(
                MobileUrl.JDCheckAddress.getUrl(),
                MobileUtil.getBodyByClass(request));
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        //0 可以配送 1 无法配送
        if(null!=jsonObject && CmccConstant.JDCheckAddress.SUCCESS_CODE.equals(jsonObject.getString(CmccConstant.RESPCODE))){
            response.setCode(CmccConstant.JDCheckAddress.ALLOW_DELIVERY);
            response.setMsg(jsonObject.getString(CmccConstant.RESPDESC));
            return response;
        }
        response.setCode(CmccConstant.JDCheckAddress.NOT_ALLOW_DELIVERY);
        response.setMsg(jsonObject.getString(CmccConstant.RESPDESC));
        return response;
    }

    public String getExpressTrace(QryExpressTraceRequest request){
        String url = MobileUrl.QryExpressTrace.getUrl();
        String body = MobileUtil.getBodyByClass(request);
        String jsonString = MobileUtil.getResponse(url, body);
        return jsonString;
    }

    public  DSAirpickinstallQueryOrderResponse getOrderMsg(DSAirpickinstallQueryOrderRequest request){
        String url = MobileUrl.DSAirpickinstallQueryOrder.getUrl();
        String body = MobileUtil.getBodyByClass(request);
        String jsonString = MobileUtil.getResponse(url, body);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        if(null==jsonObject  || !SUCCESS_CODE.equals(jsonObject.getString(CmccConstant.RESPCODE)) || !SUCCESS_CODE.equals(jsonObject.getString("resptype"))){
             log.info("查询物流信息异常:"+jsonObject.toJSONString());
        }
        JSONArray jsonArray = JSONArray.parseArray(jsonObject.getJSONObject("result").getString("order"));
        //取最新的订单信息， 号码可能以前曾经注销过
        JSONObject jsonObject1 = jsonArray.getJSONObject(jsonArray.size()-1);
        DSAirpickinstallQueryOrderResponse dsAirpickinstallQueryOrderResponse = new DSAirpickinstallQueryOrderResponse();
        if(null!=jsonObject1.getString("createTime")){
            String createTime = jsonObject1.getString("createTime").replaceAll("年", "-").replaceAll("月", "-").replaceAll("日","")
                    .replaceAll("时", ":").replaceAll("分", ":").replaceAll("秒", "");
            dsAirpickinstallQueryOrderResponse.setCreateTime(createTime);
        }
        if(null!=jsonObject1.getString("finishTime")){
            String finishTime = jsonObject1.getString("finishTime").replaceAll("年", "-").replaceAll("月", "-").replaceAll("日","")
                    .replaceAll("时", ":").replaceAll("分", ":").replaceAll("秒", "");
            dsAirpickinstallQueryOrderResponse.setFinishTime(finishTime);
        }
        dsAirpickinstallQueryOrderResponse.setOperatorId(jsonObject1.getString("operatorId")==null ? "":jsonObject1.getString("operatorId"));
        dsAirpickinstallQueryOrderResponse.setOrderType(jsonObject1.getString("orderType")==null ? "":jsonObject1.getString("orderType"));
        dsAirpickinstallQueryOrderResponse.setAreaCode(jsonObject1.getString("areaCode")==null ? "":jsonObject1.getString("areaCode"));
        dsAirpickinstallQueryOrderResponse.setOrderRemark(jsonObject1.getString("orderRemark")==null ? "":jsonObject1.getString("orderRemark"));
        dsAirpickinstallQueryOrderResponse.setOrderStatus(jsonObject1.getString("orderStatus")==null ? "":jsonObject1.getString("orderStatus"));
        dsAirpickinstallQueryOrderResponse.setWayId(jsonObject1.getString("wayid")==null ? "":jsonObject1.getString("wayid"));
        dsAirpickinstallQueryOrderResponse.setAreaName(jsonObject1.getString("areaName")==null ? "":jsonObject1.getString("areaName"));
        dsAirpickinstallQueryOrderResponse.setUsername(jsonObject1.getString("userName")==null ? "":jsonObject1.getString("userName"));
        dsAirpickinstallQueryOrderResponse.setChnlCode(jsonObject1.getString("chnlCode")==null ? "":jsonObject1.getString("chnlCode"));
        dsAirpickinstallQueryOrderResponse.setOrderBigType(jsonObject1.getString("orderBigType") == null ? "":jsonObject1.getString("orderBigType"));
        dsAirpickinstallQueryOrderResponse.setServnumber(jsonObject1.getString("servnumber") == null ? "":jsonObject1.getString("servnumber"));
        dsAirpickinstallQueryOrderResponse.setOrderId(jsonObject1.getString("orderId") ==null ? "" : jsonObject1.getString("orderId"));
        dsAirpickinstallQueryOrderResponse.setExpressno(jsonObject1.getString("expressno")==null ? "暂无物流单号" : jsonObject1.getString("expressno"));
        return dsAirpickinstallQueryOrderResponse;
    }


    public Boolean getAirpickinstallnewOrder(Order order){
        AirpickinstallnewOrderRequest request = new AirpickinstallnewOrderRequest();
        newOrderParams newOrderParams = new newOrderParams();

        //首先对京东地址校验是否配送 顺便决定产品编码是跟线上自助还是京东上门
        JDCheckAddressRequest jdCheckAddressRequest = addressResolutionService.addressResolution(order);
        JDCheckAddressResponse jdCheck = this.JDCheckAddress(jdCheckAddressRequest);
        /**
         * 此处判断是否支持京东配送  不支持京东配送则使用省仓调用
         * 支持   ReceiveType = 4  OfflineCard = 3
         */
        if(null!=jdCheck&&SUCCESS_CODE.equals(jdCheck.getCode())){
            newOrderParams.setReceiveType("4");
            newOrderParams.setOfflineCard("3");
            newOrderParams.setAcceptType("2");
        }else{
            newOrderParams.setReceiveType("1");
            newOrderParams.setOfflineCard("0");
            newOrderParams.setAcceptType("1");
        }

        String packageCode = (String) redisUtil.get(RedisEnum.PRODUCT+"|"+order.getSid());
        //查询选号列表
        QueryChooseNumberListRequest queryChooseNumberListRequest = new QueryChooseNumberListRequest();
        queryChooseNumberListRequest.setRegion("200");
        queryChooseNumberListRequest.setTag("0");
        queryChooseNumberListRequest.setPackagecode(packageCode);
        QueryChooseNumberListResponse queryChooseNumberListResponse = this.getQueryChooseNumberList(queryChooseNumberListRequest);

        //渠道编码 工号必传
        newOrderParams.setWayid(wayId);
        newOrderParams.setOperatorId(operatorId);

        //号码归属地市编码
        newOrderParams.setAreaCode("200");
        //号码归属地市名称
        newOrderParams.setAreaName("广州");
        //新购号码
        newOrderParams.setServnumber(queryChooseNumberListResponse.getMobileno());
        //支付方式
        newOrderParams.setPayWay(CmccConstant.AirpickinstallnewOrder.PAY_WAY);

        //配送省份 省内不用填 省外要填省份和区域
        if(!"200".equals(jdCheckAddressRequest.getProvinceCode())){
            newOrderParams.setProvince(jdCheckAddressRequest.getAddressProvince());
            newOrderParams.setAddressArea(jdCheckAddressRequest.getAddressArea());
        }
        //真实姓名
        newOrderParams.setUserName(order.getRealname());
        //配送地址
        newOrderParams.setAddress(jdCheckAddressRequest.getAddress());
        //配送地市中文
        newOrderParams.setAddressCity(jdCheckAddressRequest.getAddrssCity());
        //联系方式
        newOrderParams.setTelno(order.getPhone());
        //证件类型
        newOrderParams.setCerType(order.getCardtype());
        //证件号码
        newOrderParams.setCerNo(order.getCardid());
        //商品编码
        newOrderParams.setMainprodid(packageCode);
        //商品名称
        newOrderParams.setMainprodname(order.getPackageName());
        //订单id
        newOrderParams.setOrderId(order.getFdId());
        //商品编码
        newOrderParams.setOfferId(queryChooseNumberListResponse.getCommid());

        request.setNewOrderParams(newOrderParams);

        String jsonString = MobileUtil.getResponse(
                MobileUrl.AirpickinstallnewOrder.getUrl(),
                MobileUtil.getBodyByClass(request));
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        if(null==jsonObject || !SUCCESS_CODE.equals(jsonObject.getString(CmccConstant.RESPCODE))){
            log.info("线上下单异常response："+jsonObject);
            //更新订单表状态
            orderMapper.updateOrder(new Order(){{
                setFdId(newOrderParams.getOrderId());
                setStatus("2");
                setCreateTime(DateUtils.getNowTime());
                setRemark(jsonObject.getString(CmccConstant.RESPDESC));
                setProvince(jdCheckAddressRequest.getAddressProvince());
                setAddressCity(jdCheckAddressRequest.getAddrssCity());
            }});
            return false;
        }
        //更新订单表状态
        orderMapper.updateOrder(new Order(){{
            setFdId(newOrderParams.getOrderId());
            setStatus("1");
            setCreateTime(DateUtils.getNowTime());
            setRemark("新购卡号："+queryChooseNumberListResponse.getMobileno());
            setProvince(jdCheckAddressRequest.getAddressProvince());
            setAddressCity(jdCheckAddressRequest.getAddrssCity());
            setServnumber(queryChooseNumberListResponse.getMobileno());
            setOrderId(jsonObject.getString(CmccConstant.AirpickinstallnewOrder.ORDERID));
        }});
        //新增物流表数据  删掉，因为订单详情不会实时更新，用自动job替代
//        DSAirpickinstallQueryOrderResponse response = this.getOrderMsg(new DSAirpickinstallQueryOrderRequest(){{setServnumber(queryChooseNumberListResponse.getMobileno());}});
//        OrderLogistics orderLogistics = new OrderLogistics();
//        BeanUtils.copyProperties(response, orderLogistics);
//        orderLogistics.setFdId(order.getFdId());
//        orderLogisticsMapper.insertOrderLogistics(orderLogistics);
        return true;
    }

}
