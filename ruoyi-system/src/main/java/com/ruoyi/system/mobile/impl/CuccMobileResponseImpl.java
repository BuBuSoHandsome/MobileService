package com.ruoyi.system.mobile.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.system.domain.NumberCucc;
import com.ruoyi.system.domain.OrderCucc;
import com.ruoyi.system.domain.cuccMobileRequest.CheckNumRequest;
import com.ruoyi.system.domain.cuccMobileRequest.CheckUserRequest;
import com.ruoyi.system.domain.cuccMobileRequest.CreateOrderRequest;
import com.ruoyi.system.domain.cuccMobileRequest.OccupationNumberRequest;
import com.ruoyi.system.domain.cuccMobileResponse.*;
import com.ruoyi.system.mapper.NumberCuccMapper;
import com.ruoyi.system.mapper.OrderCuccMapper;
import com.ruoyi.system.mobile.CuccMobileResponseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Author Alan
 * @Date 2020/5/4 13:56
 * @Version 1.0
 */


@Service
public class CuccMobileResponseImpl implements CuccMobileResponseService {

    @Resource
    private NumberCuccMapper numberCuccMapper;

    @Resource
    private OrderCuccMapper orderCuccMapper;

    private String SUCCESS_CODE = "0";

    @Override
    public List<String> selectNumber(SelectNumResponse response) {
        List<String> numList = new ArrayList<>();
        if(response.getCode() != "0" && null == response.getData()){
            return numList;
        }
        JSONObject jsonObject = JSONObject.parseObject(response.getData());
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.getString("data"));
        numList = Arrays.asList(jsonObject1.getString("numArray").replace("[", "").replaceAll("]", "").split(","))
                .stream().filter(s -> s.length() >= 11).collect(Collectors.toList());
        return numList;
    }

    @Override
    public CheckOrderResponse checkOrder(CheckUserResponse response1, CheckNumResponse response2) {
        if(null!= response1 && null!=response2){
            if(SUCCESS_CODE.equals(response1.getCode())&&SUCCESS_CODE.equals(response2.getCode())){
                return new CheckOrderResponse(){{
                    setCode("0");
                    setMessage("校验成功");
                }};
            }
        }
        return new CheckOrderResponse(){{
            setCode("90");
            setMessage("校验失败");
        }};
    }

    @Override
    public LockNumAndApplyCodeResponse lockNumAndApplyCode(OccupationNumberResponse response1, SafeCodeResponse response2,String custId) {
        if(null!= response1 && null!=response2){
            if(SUCCESS_CODE.equals(response1.getCode())&&SUCCESS_CODE.equals(response2.getCode())){
                return new LockNumAndApplyCodeResponse(){{
                    setCode("0");
                    setMessage("号码选占并且申请验证码成功");
                    setCustId(custId);
                }};
            }
        }
        return new LockNumAndApplyCodeResponse(){{
            setCode("90");
            setMessage("号码选占或者申请验证码失败");
        }};
    }

    @Override
    public void insertCuccNum(OccupationNumberRequest request, OccupationNumberResponse response) {
        if(null!=response&&SUCCESS_CODE.equals(response.getCode())){
            NumberCucc numberCucc = new NumberCucc();
            BeanUtils.copyProperties(request, numberCucc);
            numberCucc.setCustId(request.getProKey());
            numberCuccMapper.insertNumberCucc(numberCucc);
        }
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest request, CheckCodeResponse response) {
        if(null!=request && null!=response){
            if(SUCCESS_CODE.equals(response.getCode())){
                JSONObject jsonObject = JSONObject.parseObject(response.getData());
                JSONObject resultJson = JSONObject.parseObject(jsonObject.getString("data"));
                OrderCucc orderCucc = new OrderCucc();
                BeanUtils.copyProperties(request, orderCucc);
                orderCucc.setFdId(UUID.randomUUID().toString().replaceAll("-", ""));
                orderCucc.setCaptchaId(resultJson.getString("ID"));
                orderCucc.setStatus("0");
                orderCuccMapper.insertOrderCucc(orderCucc);
                return new CreateOrderResponse(){{
                    setCode("0");
                    setMessage("创建订单成功");
                }};
            }
        }
        return new CreateOrderResponse(){{
            setCode("90");
            setMessage("订单保存失败");
        }};
    }


}
