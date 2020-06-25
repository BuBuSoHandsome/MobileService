package com.ruoyi.system.mobile;

import com.ruoyi.common.utils.redis.RedisUtil;
import com.ruoyi.system.domain.AddressCode;
import com.ruoyi.system.domain.Order;
import com.ruoyi.system.domain.mobileRequest.JDCheckAddressRequest;
import com.ruoyi.system.mapper.AddressCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AddressResolutionService {


    @Resource
    private AddressCodeMapper addressCodeMapper;

    @Autowired
    private RedisUtil redisUtil;

    public JDCheckAddressRequest addressResolution(Order order) {
        JDCheckAddressRequest request = new JDCheckAddressRequest();
        //通过api落地页解析
        if(null!=order.getCitycode()&&!"".equals(order.getCitycode())){
//            AddressCode addressCode = addressCodeMapper.selectAddressCodeByName(order.getAddressCity());
//            if(null==addressCode){
//                return request;
//            }
//            request.setProvinceCode(addressCode.getParentCode());
//            request.setEparchyCode(addressCode.getCode());
            request.setProvinceCode(order.getProvincecode());
            request.setEparchyCode(order.getEparchycode());
            request.setAddressProvince(order.getProvince());
            request.setAddrssCity(order.getAddressCity());
            request.setAddressArea( (String)redisUtil.get("3|"+order.getCitycode()));
            request.setAddress(order.getAddress());
        }else{
            //只通过一个地址解析
            String regex="(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
            Matcher m = Pattern.compile(regex).matcher(order.getAddress());
            while (m.find()) {
                AddressCode addressCode = addressCodeMapper.selectAddressCodeByName(m.group("city"));
                    if(null==addressCode){
                    return request;
                }
                request.setProvinceCode(addressCode.getParentCode());
                request.setEparchyCode(addressCode.getCode());
                request.setAddressProvince(m.group("province"));
                request.setAddrssCity(m.group("city"));
                if(order.getAddress().indexOf("北京市")>-1){
                    request.setProvinceCode("100");
                    request.setEparchyCode("102");
                    request.setAddressProvince("北京市");
                    request.setAddrssCity("北京市");
                }
                if(order.getAddress().indexOf("上海市")>-1){
                    request.setProvinceCode("210");
                    request.setEparchyCode("120");
                    request.setAddressProvince("上海市");
                    request.setAddrssCity("上海市");
                }
                if(order.getAddress().indexOf("天津市")>-1){
                    request.setProvinceCode("220");
                    request.setEparchyCode("220");
                    request.setAddressProvince("天津市");
                    request.setAddrssCity("天津市");
                }
                if(order.getAddress().indexOf("重庆市")>-1){
                    request.setProvinceCode("230");
                    request.setEparchyCode("230");
                    request.setAddressProvince("重庆市");
                    request.setAddrssCity("重庆市");
                }
                request.setAddressArea(m.group("county"));
                request.setAddress(order.getAddress());
            }
          }
             return request;
        }

        public static void main(String[] args) {

                String address = "北京市丰台区北京西客站南广场";
                String regex="(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
                Matcher m = Pattern.compile(regex).matcher(address);
                while (m.find()) {
                System.out.println(m.group("province"));
                System.out.println(m.group("city"));
                System.out.println(m.group("county"));
                }
                }

        }
