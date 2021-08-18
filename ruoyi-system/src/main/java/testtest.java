import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.MobileUrl;
import com.ruoyi.common.json.JSON;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.common.utils.moblie.MobileUtil;
import com.ruoyi.common.utils.moblie.RSASignature;
import com.ruoyi.common.utils.moblie.StringUtil;
import com.ruoyi.common.utils.security.Md5Utils;
import com.ruoyi.system.domain.ChooseNumberColumn;
import com.ruoyi.system.domain.Order;
import com.ruoyi.system.domain.cuccMobileRequest.CheckUserRequest;
import com.ruoyi.system.domain.cuccMobileResponse.CheckUserResponse;
import com.ruoyi.system.domain.mobileRequest.DSAirpickinstallQueryOrderRequest;
import com.ruoyi.system.domain.mobileResponse.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

public class testtest {





    public static void main(String[] args) throws Exception {


//
//        String jsonStr = "{\"rspCode\":\"0000\",\"rspDesc\":\"操作成功\",\"uuid\":\"a50e37ed-ac6a-4706-90a4-f0daabe775b4\",\"body\":\"{\\\"ID\\\":\\\"20051322290314531654\\\"}\",\"ok\":true,\"message\":\"操作成功\",\"data\":\"{\\\"ID\\\":\\\"20051322290314531654\\\"}\"}";
//        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
//        JSONObject resultJson = JSONObject.parseObject(jsonObject.getString("data"));
//        System.out.println(resultJson.getString("ID"));
//



//        CheckUserRequest checkUserRequest = new CheckUserRequest();
//        checkUserRequest.setCertName("钟玉兰");
//        checkUserRequest.setCertNum("440125196306282146");
//        checkUserRequest.setProvinceCode("440000");
//        checkUserRequest.setCityCode("440100");
//
//        Class superClass = checkUserRequest.getClass();
//        Field[] fields = superClass.getDeclaredFields();
//        for(Field f:fields){
//            f.setAccessible(true);
//            System.out.println(f.getName()+f.get(checkUserRequest));
//        }


//
//        String resultJson = "";
//        try {
//             resultJson = doPost("https://m.75510010.com/mall/api/unicom/zop-mall/check-user", checkUserRequest);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if(!"".equals(resultJson)){
//            JSONObject jsStr = JSONObject.parseObject(resultJson);
//            CheckUserResponse checkUserResponse = JSONObject.toJavaObject(jsStr,CheckUserResponse.class);
//            Field[] fields = checkUserResponse.getClass().getDeclaredFields();
//            for(Field f:fields){
//                f.setAccessible(true);
//                System.out.println("属性名："+f.getName()+"----属性值："+f.get(checkUserResponse));
//            }
//
//        }


//        try {
//
//            System.out.println("加密前："+reflect(checkUserRequest));
//            System.out.println("加密后："+RSASignature.getSHA256StrJava(reflect(checkUserRequest)));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }




//          String jsonString = "{\n" +
//                  "    \"result\": {\n" +
//                  "        \"brandname\": \"神州行\",\n" +
//                  "        \"infos\": [\n" +
//                  "            {\n" +
//                  "                \"sid\": \"MARKETING_SOLUTIONS_CARDPACK-20200311051940135-700049143300\",\n" +
//                  "                \"solution_name\": \"内含25元话费\",\n" +
//                  "                \"price\": \"0\",\n" +
//                  "                \"productid\": \"prod.10086000012988\",\n" +
//                  "                \"account\": \"25\",\n" +
//                  "                \"solution_id\": \"14679625923_19880999408\"\n" +
//                  "            }\n" +
//                  "        ],\n" +
//                  "        \"pkginfos\": [\n" +
//                  "            {\n" +
//                  "                \"details\": \"<p>\\r\\n\\t<img alt=\\\"\\\" src=\\\"http://image.gd.10086.cn/upfile/editor/image_cdn/image/2018-11/20181123161447685-429.jpg\\\" />&nbsp;\\r\\n</p>\",\n" +
//                  "                \"instructions\": \"<p>\\r\\n\\t<img alt=\\\"\\\" src=\\\"http://image.gd.10086.cn/upfile/editor/image_cdn/image/2019-02/20190211120456132-533.jpg\\\" /> \\r\\n</p>\\r\\n<p>\\r\\n\\t&nbsp;\\r\\n</p>\\r\\n<p>\\r\\n\\t<br />\\r\\n</p>\",\n" +
//                  "                \"faq\": \"&nbsp;<img alt=\\\"\\\" src=\\\"http://image.gd.10086.cn/upfile/editor/image_cdn/image/2018-09/20180930185231267-340.jpg\\\" />\",\n" +
//                  "                \"pkgid\": \"prod.10086000010513\",\n" +
//                  "                \"pkgname\": \"18元移动王卡\",\n" +
//                  "                \"brief\": \"月享100M国内流量+3G咪咕视频定向流量，每日1元3G王卡专属流量（1G国内流量+2G定向流量）\",\n" +
//                  "                \"introduction\": \"<p>\\r\\n\\t&nbsp;<img alt=\\\"\\\" src=\\\"http://image.gd.10086.cn/upfile/editor/image_cdn/image/2019-09/20190926154834107-465.jpg\\\" /> \\r\\n</p>\\r\\n<p>\\r\\n\\t<br />\\r\\n</p>\",\n" +
//                  "                \"remind\": \"<p>\\r\\n\\t<br />\\r\\n</p>\\r\\n<p>\\r\\n\\t<img alt=\\\"\\\" src=\\\"http://image.gd.10086.cn/upfile/editor/image_cdn/image/2018-08/20180831171156479-246.jpg\\\" /> \\r\\n</p>\\r\\n<p>\\r\\n\\t&nbsp;<img alt=\\\"\\\" src=\\\"http://image.gd.10086.cn/upfile/editor/image_cdn/image/2018-09/20180930180905363-979.jpg\\\" /> \\r\\n</p>\\r\\n<p>\\r\\n\\t<br />\\r\\n</p>\"\n" +
//                  "            },\n" +
//                  "            {\n" +
//                  "                \"details\": \"<p>\\r\\n\\t&nbsp;<img alt=\\\"\\\" src=\\\"http://image.gd.10086.cn/upfile/editor/image_cdn/image/2019-04/20190423101357663-118.png\\\" /> \\r\\n</p>\",\n" +
//                  "                \"instructions\": \"<img alt=\\\"\\\" src=\\\"http://image.gd.10086.cn/upfile/editor/image_cdn/image/2019-02/20190211120426300-506.jpg\\\" /> \\r\\n<p>\\r\\n\\t&nbsp;\\r\\n</p>\\r\\n<p>\\r\\n\\t<br />\\r\\n</p>\",\n" +
//                  "                \"faq\": \"<img alt=\\\"\\\" src=\\\"http://image.gd.10086.cn/upfile/editor/image_cdn/image/2018-09/20180930185313295-813.jpg\\\" /> \\r\\n<p>\\r\\n\\t<br />\\r\\n</p>\",\n" +
//                  "                \"pkgid\": \"prod.10086000014560\",\n" +
//                  "                \"pkgname\": \"24元移动王卡\",\n" +
//                  "                \"brief\": \"24元移动王卡\",\n" +
//                  "                \"introduction\": \"<img alt=\\\"\\\" src=\\\"http://image.gd.10086.cn/upfile/editor/image_cdn/image/2019-09/20190929175500385-289.jpg\\\" />&nbsp;\",\n" +
//                  "                \"remind\": \"<p>\\r\\n\\t&nbsp;<img alt=\\\"\\\" src=\\\"http://image.gd.10086.cn/upfile/editor/image_cdn/image/2019-12/20191203104612026-269.jpg\\\" /> \\r\\n</p>\\r\\n<p>\\r\\n\\t&nbsp;<img alt=\\\"\\\" src=\\\"http://image.gd.10086.cn/upfile/editor/image_cdn/image/2018-09/20180930181005219-576.jpg\\\" /> \\r\\n</p>\"\n" +
//                  "            }\n" +
//                  "        ],\n" +
//                  "        \"brand\": \"2\"\n" +
//                  "    },\n" +
//                  "    \"respcode\": \"0\",\n" +
//                  "    \"respdesc\": \"成功\",\n" +
//                  "    \"resptype\": \"0\"\n" +
//                  "}";

//        String jsonString = "{\n" +
//                "    \"result\": {\n" +
//                "        \"infos\": [\n" +
//                "            {\n" +
//                "                \"region\": \"200\",\n" +
//                "                \"commid\": \"14679625893_19872975419\",\n" +
//                "                \"commtype\": \"50\",\n" +
//                "                \"tag\": \"0\",\n" +
//                "                \"cityname\": \"广州\",\n" +
//                "                \"mobileno\": \"19872975419\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "                \"region\": \"200\",\n" +
//                "                \"commid\": \"14679625893_19872637035\",\n" +
//                "                \"commtype\": \"50\",\n" +
//                "                \"tag\": \"0\",\n" +
//                "                \"cityname\": \"广州\",\n" +
//                "                \"mobileno\": \"19872637035\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "                \"region\": \"200\",\n" +
//                "                \"commid\": \"14679625893_19872579136\",\n" +
//                "                \"commtype\": \"50\",\n" +
//                "                \"tag\": \"0\",\n" +
//                "                \"cityname\": \"广州\",\n" +
//                "                \"mobileno\": \"19872579136\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "                \"region\": \"200\",\n" +
//                "                \"commid\": \"14679625893_19830877050\",\n" +
//                "                \"commtype\": \"50\",\n" +
//                "                \"tag\": \"0\",\n" +
//                "                \"cityname\": \"广州\",\n" +
//                "                \"mobileno\": \"19830877050\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "                \"region\": \"200\",\n" +
//                "                \"commid\": \"14679625893_19898457193\",\n" +
//                "                \"commtype\": \"50\",\n" +
//                "                \"tag\": \"0\",\n" +
//                "                \"cityname\": \"广州\",\n" +
//                "                \"mobileno\": \"19898457193\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "                \"region\": \"200\",\n" +
//                "                \"commid\": \"14679625893_19872570535\",\n" +
//                "                \"commtype\": \"50\",\n" +
//                "                \"tag\": \"0\",\n" +
//                "                \"cityname\": \"广州\",\n" +
//                "                \"mobileno\": \"19872570535\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "                \"region\": \"200\",\n" +
//                "                \"commid\": \"14679625893_19872570493\",\n" +
//                "                \"commtype\": \"50\",\n" +
//                "                \"tag\": \"0\",\n" +
//                "                \"cityname\": \"广州\",\n" +
//                "                \"mobileno\": \"19872570493\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "                \"region\": \"200\",\n" +
//                "                \"commid\": \"14679625893_19872636930\",\n" +
//                "                \"commtype\": \"50\",\n" +
//                "                \"tag\": \"0\",\n" +
//                "                \"cityname\": \"广州\",\n" +
//                "                \"mobileno\": \"19872636930\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "                \"region\": \"200\",\n" +
//                "                \"commid\": \"14679625893_19872570568\",\n" +
//                "                \"commtype\": \"50\",\n" +
//                "                \"tag\": \"0\",\n" +
//                "                \"cityname\": \"广州\",\n" +
//                "                \"mobileno\": \"19872570568\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "                \"region\": \"200\",\n" +
//                "                \"commid\": \"14679625893_19872603405\",\n" +
//                "                \"commtype\": \"50\",\n" +
//                "                \"tag\": \"0\",\n" +
//                "                \"cityname\": \"广州\",\n" +
//                "                \"mobileno\": \"19872603405\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "                \"region\": \"200\",\n" +
//                "                \"commid\": \"14679625893_19872603481\",\n" +
//                "                \"commtype\": \"50\",\n" +
//                "                \"tag\": \"0\",\n" +
//                "                \"cityname\": \"广州\",\n" +
//                "                \"mobileno\": \"19872603481\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "                \"region\": \"200\",\n" +
//                "                \"commid\": \"14679625893_19872643978\",\n" +
//                "                \"commtype\": \"50\",\n" +
//                "                \"tag\": \"0\",\n" +
//                "                \"cityname\": \"广州\",\n" +
//                "                \"mobileno\": \"19872643978\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "                \"region\": \"200\",\n" +
//                "                \"commid\": \"14679625893_19872603586\",\n" +
//                "                \"commtype\": \"50\",\n" +
//                "                \"tag\": \"0\",\n" +
//                "                \"cityname\": \"广州\",\n" +
//                "                \"mobileno\": \"19872603586\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "                \"region\": \"200\",\n" +
//                "                \"commid\": \"14679625893_19872570468\",\n" +
//                "                \"commtype\": \"50\",\n" +
//                "                \"tag\": \"0\",\n" +
//                "                \"cityname\": \"广州\",\n" +
//                "                \"mobileno\": \"19872570468\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "                \"region\": \"200\",\n" +
//                "                \"commid\": \"14679625893_19872643930\",\n" +
//                "                \"commtype\": \"50\",\n" +
//                "                \"tag\": \"0\",\n" +
//                "                \"cityname\": \"广州\",\n" +
//                "                \"mobileno\": \"19872643930\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "                \"region\": \"200\",\n" +
//                "                \"commid\": \"14679625893_19872570592\",\n" +
//                "                \"commtype\": \"50\",\n" +
//                "                \"tag\": \"0\",\n" +
//                "                \"cityname\": \"广州\",\n" +
//                "                \"mobileno\": \"19872570592\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "                \"region\": \"200\",\n" +
//                "                \"commid\": \"14679625893_19872579074\",\n" +
//                "                \"commtype\": \"50\",\n" +
//                "                \"tag\": \"0\",\n" +
//                "                \"cityname\": \"广州\",\n" +
//                "                \"mobileno\": \"19872579074\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "                \"region\": \"200\",\n" +
//                "                \"commid\": \"14679625893_19872570482\",\n" +
//                "                \"commtype\": \"50\",\n" +
//                "                \"tag\": \"0\",\n" +
//                "                \"cityname\": \"广州\",\n" +
//                "                \"mobileno\": \"19872570482\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "                \"region\": \"200\",\n" +
//                "                \"commid\": \"14679625893_19872577448\",\n" +
//                "                \"commtype\": \"50\",\n" +
//                "                \"tag\": \"0\",\n" +
//                "                \"cityname\": \"广州\",\n" +
//                "                \"mobileno\": \"19872577448\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "                \"region\": \"200\",\n" +
//                "                \"commid\": \"14679625893_19872579190\",\n" +
//                "                \"commtype\": \"50\",\n" +
//                "                \"tag\": \"0\",\n" +
//                "                \"cityname\": \"广州\",\n" +
//                "                \"mobileno\": \"19872579190\"\n" +
//                "            }\n" +
//                "        ]\n" +
//                "    },\n" +
//                "    \"respcode\": \"0\",\n" +
//                "    \"respdesc\": \"成功\",\n" +
//                "    \"resptype\": \"0\"\n" +
//                "}";


//        JSONObject jsonObj = JSONObject.parseObject(jsonString);//转JSONObject对象
//        String respcode = jsonObj.getString("respcode");
//        String brand = JSONObject.parseObject(jsonObj.getString("result")).getString("brand")  ;
//
//
//        if(null==respcode||!"0".equals(respcode)){
//
//        }
//        String infos = JSONObject.parseObject(jsonObj.getString("result")).getString("infos").replaceAll("\\[","").replaceAll("\\]","");
//        JSONObject infosJson = JSONObject.parseObject(infos);
//        String price = infosJson.getString("price");
//        String account=infosJson.getString("account");
//        String solution_id=infosJson.getString("solution_id");
//        String solution_name =infosJson.getString("solution_name");
//
//        QueryDiscountNumberListResponse response = new QueryDiscountNumberListResponse();
//        response.setBrand(brand == null ? "" : brand);
//        response.setPrice(price ==null ? "":price);
//        response.setAccount(account == null ? "" : account);
//        response.setSolution_id(solution_id == null ? "" :solution_id);
//        response.setSolution_name(solution_name == null ? "" :solution_name);
//
//        System.out.println("brand:"+response.getBrand());
//        System.out.println("price:"+response.getAccount());
//        System.out.println("account:"+response.getPrice());
//        System.out.println("solution_id:"+response.getSolution_id());
//        System.out.println("solution_name:"+response.getSolution_name());






//        String price= JSONObject.parseObject(jsonObj.get("result")).getString("infos");




//   \"sid\": \"MARKETING_SOLUTIONS_CARDPACK-20200311051940135-700049143300\",\n" +
//                "                \"solution_name\": \"内含25元话费\",\n" +
//                "                \"price\": \"0\",\n" +
//                "                \"productid\": \"prod.10086000012988\",\n" +
//                "                \"account\": \"25\",\n" +
//                "                \"solution_id\": \"14679625923_19880999408\"\n" +



//        List<QueryChooseNumberListResponse> lists = JSONArray.parseArray(infos, QueryChooseNumberListResponse.class);
//        lists.stream().forEach(numberList->{
//            if(numberList.getCommtype()!=null && "50".equals(numberList.getCommtype())){
//                System.out.println(numberList.getCommid());
//                System.out.println(numberList.getMobileno());
//            }
//        });



//        List<ChooseNumberColumnResponse> lists = JSONArray.parseArray(infos, ChooseNumberColumnResponse.class);
//
//        System.out.println(lists.size());
//
//        lists.stream().forEach(list->{
//            ChooseNumberColumn chooseNumberColumn = new ChooseNumberColumn();
//            chooseNumberColumn.setChannel(list.getChannel()==null ? "":list.getChannel());
//            chooseNumberColumn.setConstraint(list.getConstraint() == null ? "":list.getConstraint());
//            chooseNumberColumn.setDescribe(list.getDescribe() ==null ? "": list.getDescribe()) ;
//            chooseNumberColumn.setPack(list.getPack() ==null ? "": list.getPack() );
//            chooseNumberColumn.setPic(list.getPic() ==null ? "": list.getPic());
//            chooseNumberColumn.setStatus(list.getStatus() ==null ? "": list.getStatus() );
//            chooseNumberColumn.setPosition(list.getPosition() ==null ? "": list.getPosition());
//            chooseNumberColumn.setText(list.getText() ==null ? "": list.getText());
//            chooseNumberColumn.setSid(list.getSid() ==null ? 00000 :list.getSid());
//            chooseNumberColumn.setupdatetime(DateUtils.getDateFromString(list.getUpdatetime()));
//
//            System.out.println(chooseNumberColumn.getConstraint());
//       });



//          String jsonString = "{\n" +
//                  "    \"result\": {\n" +
//                  "        \"orderid\": \"4d1b00e86c8a4de688197c1295a31565\",\n" +
//                  "        \"ecporderid\": \"113976640\",\n" +
//                  "        \"retmsg\": \"成功\",\n" +
//                  "        \"retcode\": \"0\"\n" +
//                  "    },\n" +
//                  "    \"respdesc\": \"113976640\",\n" +
//                  "    \"resptype\": \"0\",\n" +
//                  "    \"respcode\": \"0\"\n" +
//                  "}";
//        JSONObject jsonObject = JSONObject.parseObject(jsonString);
//        if(null==jsonObject && !"0".equals(jsonObject.getString("respcode"))){
//            //抛异常
//        }
//        String retcode = JSONObject.parseObject(jsonObject.getString("result")).getString("retcode")  ;
//        String retmsg = JSONObject.parseObject(jsonObject.getString("result")).getString("retmsg")  ;



//        String jsonString1 = "{\n" +
//                "\"result\": {\n" +
//                "\"ecporderid\": \"\",\n" +
//                "\"retmsg\": \"接口异常错误\",\n" +
//                "\"retcode\": \"106\"\n" +
//                "},\n" +
//                "\"respdesc\": \"调订单校验不通过，原因：姓名与身份证号不符：姓名：叶慧娟,身份证号：441324198705170322，请核实后重新输入。 \",\n" +
//                "\"resptype\": \"成功\",\n" +
//                "\"respcode\": \"E001221\"\n" +
//                "}";
//        JSONObject jsonObject1 = JSONObject.parseObject(jsonString1);
//        if(null==jsonObject1 || !"0".equals(jsonObject1.getString("respcode"))){
//            //抛异常
//            System.out.println(jsonObject1.getString("respdesc"));
//        }

//          String jsonString = "{\n" +
//                  "    \"result\": {\n" +
//                  "        \"order\": [\n" +
//                  "            {\n" +
//                  "                \"createTime\": \"2020年03月13日 10时28分58秒\",\n" +
//                  "                \"operatorId\": \"AGZC00000BYJ\",\n" +
//                  "                \"orderType\": \"2\",\n" +
//                  "                \"areaCode\": \"200\",\n" +
//                  "                \"subsShopId\": \"null\",\n" +
//                  "                \"orderRemark\": \"和商汇已接单\",\n" +
//                  "                \"orderStatus\": \"已接单\",\n" +
//                  "                \"wayid\": \"GZ08EC200043\",\n" +
//                  "                \"areaName\": \"广州\",\n" +
//                  "                \"userName\": \"叶惠娟\",\n" +
//                  "                \"finishTime\": \"\",\n" +
//                  "                \"chnlCode\": \"GZ08EC200043\",\n" +
//                  "                \"orderBigType\": \"C\",\n" +
//                  "                \"servnumber\": \"19898540616\",\n" +
//                  "                \"orderId\": \"4d1b00e86c8a4de688197c1295a31565\"\n" +
//                  "            }\n" +
//                  "        ]\n" +
//                  "    },\n" +
//                  "    \"respcode\": \"0\",\n" +
//                  "    \"respdesc\": \"成功！\",\n" +
//                  "    \"resptype\": \"0\"\n" +
//                  "}";
//
//
//        JSONObject jsonObject = JSONObject.parseObject(jsonString);
//        if(null==jsonObject  || !"0".equals(jsonObject.getString("respcode")) || !"0".equals(jsonObject.getString("resptype"))){
//            throw new RuntimeException(jsonObject.getString("respdesc"));
//        }
//        JSONArray jsonArray = JSONArray.parseArray(jsonObject.getJSONObject("result").getString("order"));
//        JSONObject jsonObject1 = jsonArray.getJSONObject(0);

//        System.out.println(jsonObject1.getString("orderRemark"));
//        System.out.println(jsonObject1.getString("servnumber"));
//        System.out.println(jsonObject1.getString("createTime"));


//        DSAirpickinstallQueryOrderResponse dsAirpickinstallQueryOrderResponse = new DSAirpickinstallQueryOrderResponse();
//        dsAirpickinstallQueryOrderResponse.setCreateTime(jsonObject1.getString("createTime")==null ? "":jsonObject1.getString("createTime"));
//        dsAirpickinstallQueryOrderResponse.setOperatorId(jsonObject1.getString("operatorId")==null ? "":jsonObject1.getString("operatorId"));
//        dsAirpickinstallQueryOrderResponse.setOrderType(jsonObject1.getString("orderType")==null ? "":jsonObject1.getString("orderType"));
//        dsAirpickinstallQueryOrderResponse.setAreaCode(jsonObject1.getString("areaCode")==null ? "":jsonObject1.getString("areaCode"));
//        dsAirpickinstallQueryOrderResponse.setOrderRemark(jsonObject1.getString("orderRemark")==null ? "":jsonObject1.getString("orderRemark"));
//        dsAirpickinstallQueryOrderResponse.setOrderStatus(jsonObject1.getString("orderStatus")==null ? "":jsonObject1.getString("orderStatus"));
//        dsAirpickinstallQueryOrderResponse.setWayid(jsonObject1.getString("wayid")==null ? "":jsonObject1.getString("wayid"));
//        dsAirpickinstallQueryOrderResponse.setAreaName(jsonObject1.getString("areaName")==null ? "":jsonObject1.getString("areaName"));
//        dsAirpickinstallQueryOrderResponse.setUserName(jsonObject1.getString("userName")==null ? "":jsonObject1.getString("userName"));
//        dsAirpickinstallQueryOrderResponse.setFinishTime(jsonObject1.getString("finishTime")==null ? "":jsonObject1.getString("finishTime"));
//        dsAirpickinstallQueryOrderResponse.setChnlCode(jsonObject1.getString("chnlCode")==null ? "":jsonObject1.getString("chnlCode"));
//        dsAirpickinstallQueryOrderResponse.setOrderBigType(jsonObject1.getString("orderBigType") == null ? "":jsonObject1.getString("orderBigType"));
//        dsAirpickinstallQueryOrderResponse.setServnumber(jsonObject1.getString("servnumber") == null ? "":jsonObject1.getString("servnumber"));
//        dsAirpickinstallQueryOrderResponse.setOrderId(jsonObject1.getString("orderId") ==null ? "" : jsonObject1.getString("orderId"));
//        dsAirpickinstallQueryOrderResponse.setExpressno(jsonObject1.getString("expressno")==null ? "暂无物流单号" : jsonObject1.getString("expressno"));


//        Class cls = dsAirpickinstallQueryOrderResponse.getClass();
//        Field[] fields = cls.getDeclaredFields();
//        for(int i=0; i<fields.length; i++){
//            Field f = fields[i];
//            f.setAccessible(true);
//            try {
//                System.out.println("属性名:" + f.getName() + " 属性值:" + f.get(dsAirpickinstallQueryOrderResponse));
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }

//        String[] idS = Convert.toStrArray("123123,1231234");
//
//        for (int i =0;i<idS.length;i++){
//
//            int finalI = i;
//            DSAirpickinstallQueryOrderRequest request = new DSAirpickinstallQueryOrderRequest(){{
//                setOrderId(idS[finalI]);
//            }};
//
//            System.out.println(request.getOrderId());

//                Order order = new Order();
//
//                order.setSid("1000000019");
//                order.setPack("prod.10086000025892");
//                order.setRealname("小明");
//                order.setPhone("13922376556");
//                order.setCardid("441324198711170909");
//                order.setAddress("广东省广州市增城区滨海花园");
//
//                System.out.println(MobileUtil.getBodyByClass(order));
//
//        AjaxResult successResult = AjaxResult.success("成功");
//
//        AjaxResult warnResult = AjaxResult.warn("失败");
//
//
//        System.out.println(MobileUtil.getBodyByClass(successResult));
//
//        System.out.println(MobileUtil.getBodyByClass(warnResult));


//        集合倒序
//        List<String> stringList = new LinkedList<>();
//
//        stringList.add("1");
//        stringList.add("2");
//        stringList.add("3");
//        stringList.add("4");
//        stringList.add("15");
//        stringList.add("16");
//        stringList.add("99");
//
//        Collections.reverse(stringList);
//
//        for (int i=0;i<stringList.size();i++){
//            System.out.println(stringList.get(i));
//        }








//        String jsonString = "{\"result\":{\"data\":{\"resultMsg\":\"始发地址信息及发货仓无效\",\"resultCode\":\"E0001\"},\"code\":\"0\",\"msg\":\"成功\"},\"respcode\":\"E0001\",\"respdesc\":\"始发地址信息及发货仓无效\",\"resptype\":\"600\"}";
//        JSONObject jsonObject = JSONObject.parseObject(jsonString);
//        System.out.println(jsonObject.getString("respdesc"));


    }


//        String jsonString  = "{\"result\":{\"data\":{\"resultMsg\":\"可以京配\",\"resultCode\":\"A0000\"},\"code\":\"0\",\"msg\":\"成功\"},\"respcode\":\"0\",\"respdesc\":\"可以京配\",\"resptype\":\"0\"}";
//        JSONObject jsonObject = JSONObject.parseObject(jsonString);
//        if(null!=jsonObject && "0".equals(jsonObject.getString("respcode")) && "可以京配".equals(jsonObject.getString("respdesc"))){
//            System.out.println(jsonObject.getString("respcode"));
//            System.out.println(jsonObject.getString("respdesc"));
//        }

//        Order order = new Order();
//        order.setSid("1000000003");
//        order.setPack("prod.10086000014560");
//        order.setRealname("叶慧娟");
//        order.setPhone("13922376556");
//        order.setCardtype("01");
//        order.setCardid("441324198705170322");
//        order.setProvincecode("200");
//        order.setCitycode("020");
//        order.setEparchycode("");
//        order.setAddress("广东省广州市增城区滨海花园");
//        order.setProvince("广东省");
//        order.setAddressCity("广州市");
//        System.out.println(MobileUtil.getBodyByClass(order));





}
