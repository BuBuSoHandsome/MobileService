package com.ruoyi.system.mobile;

import com.ruoyi.system.domain.AddressCode;
import com.ruoyi.system.domain.mobileRequest.JDCheckAddressRequest;
import com.ruoyi.system.mapper.AddressCodeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AddressResolutionService {


    @Resource
    private AddressCodeMapper addressCodeMapper;

    public JDCheckAddressRequest addressResolution(String address) {

        JDCheckAddressRequest request = new JDCheckAddressRequest();
        String regex="(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
        Matcher m = Pattern.compile(regex).matcher(address);
        while (m.find()) {
            AddressCode addressCode = addressCodeMapper.selectAddressCodeByName(m.group("city"));
            request.setProvinceCode(addressCode.getParentCode()==null ? "":addressCode.getParentCode());
            request.setEparchyCode(addressCode.getCode()==null ? "":addressCode.getCode());
            request.setAddressProvince(m.group("province"));
            request.setAddrssCity(m.group("city"));
            request.setAddressArea(m.group("county"));
            request.setAddress(address);
        }
        return request;
    }

    public static void main(String[] args) {

        String address = "湖南省岳阳市华容县插旗镇引河村4组";
        String regex="(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
        Matcher m = Pattern.compile(regex).matcher(address);
        while (m.find()) {
            System.out.println(m.group("province"));
            System.out.println(m.group("city"));
            System.out.println(m.group("county"));
        }


    }

}
