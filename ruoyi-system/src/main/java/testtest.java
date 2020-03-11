import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.system.domain.ChooseNumberColumn;
import com.ruoyi.system.domain.mobileResponse.ChooseNumberColumnResponse;
import com.ruoyi.system.domain.mobileResponse.ChooseNumberbusinessResponse;
import com.ruoyi.system.domain.mobileResponse.QueryChooseNumberListResponse;
import com.ruoyi.system.domain.mobileResponse.QueryDiscountNumberListResponse;

import java.util.List;
import java.util.UUID;

public class testtest {

    public static void main(String[] args) {



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




//        String jsonString  = "{\"result\":{\"data\":{\"resultMsg\":\"可以京配\",\"resultCode\":\"A0000\"},\"code\":\"0\",\"msg\":\"成功\"},\"respcode\":\"0\",\"respdesc\":\"可以京配\",\"resptype\":\"0\"}";
//        JSONObject jsonObject = JSONObject.parseObject(jsonString);
//        if(null!=jsonObject && "0".equals(jsonObject.getString("respcode")) && "可以京配".equals(jsonObject.getString("respdesc"))){
//            System.out.println(jsonObject.getString("respcode"));
//            System.out.println(jsonObject.getString("respdesc"));
//        }
    }


}
