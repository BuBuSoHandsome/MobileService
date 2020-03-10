import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.system.domain.ChooseNumberColumn;
import com.ruoyi.system.domain.mobileResponse.ChooseNumberColumnResponse;
import com.ruoyi.system.domain.mobileResponse.QueryChooseNumberListResponse;

import java.util.List;
import java.util.UUID;

public class testtest {

    public static void main(String[] args) {


        String jsonString = "{\n" +
                "    \"result\": {\n" +
                "        \"infos\": [\n" +
                "            {\n" +
                "                \"region\": \"200\",\n" +
                "                \"commid\": \"14679625893_19872975419\",\n" +
                "                \"commtype\": \"50\",\n" +
                "                \"tag\": \"0\",\n" +
                "                \"cityname\": \"广州\",\n" +
                "                \"mobileno\": \"19872975419\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"region\": \"200\",\n" +
                "                \"commid\": \"14679625893_19872637035\",\n" +
                "                \"commtype\": \"50\",\n" +
                "                \"tag\": \"0\",\n" +
                "                \"cityname\": \"广州\",\n" +
                "                \"mobileno\": \"19872637035\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"region\": \"200\",\n" +
                "                \"commid\": \"14679625893_19872579136\",\n" +
                "                \"commtype\": \"50\",\n" +
                "                \"tag\": \"0\",\n" +
                "                \"cityname\": \"广州\",\n" +
                "                \"mobileno\": \"19872579136\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"region\": \"200\",\n" +
                "                \"commid\": \"14679625893_19830877050\",\n" +
                "                \"commtype\": \"50\",\n" +
                "                \"tag\": \"0\",\n" +
                "                \"cityname\": \"广州\",\n" +
                "                \"mobileno\": \"19830877050\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"region\": \"200\",\n" +
                "                \"commid\": \"14679625893_19898457193\",\n" +
                "                \"commtype\": \"50\",\n" +
                "                \"tag\": \"0\",\n" +
                "                \"cityname\": \"广州\",\n" +
                "                \"mobileno\": \"19898457193\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"region\": \"200\",\n" +
                "                \"commid\": \"14679625893_19872570535\",\n" +
                "                \"commtype\": \"50\",\n" +
                "                \"tag\": \"0\",\n" +
                "                \"cityname\": \"广州\",\n" +
                "                \"mobileno\": \"19872570535\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"region\": \"200\",\n" +
                "                \"commid\": \"14679625893_19872570493\",\n" +
                "                \"commtype\": \"50\",\n" +
                "                \"tag\": \"0\",\n" +
                "                \"cityname\": \"广州\",\n" +
                "                \"mobileno\": \"19872570493\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"region\": \"200\",\n" +
                "                \"commid\": \"14679625893_19872636930\",\n" +
                "                \"commtype\": \"50\",\n" +
                "                \"tag\": \"0\",\n" +
                "                \"cityname\": \"广州\",\n" +
                "                \"mobileno\": \"19872636930\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"region\": \"200\",\n" +
                "                \"commid\": \"14679625893_19872570568\",\n" +
                "                \"commtype\": \"50\",\n" +
                "                \"tag\": \"0\",\n" +
                "                \"cityname\": \"广州\",\n" +
                "                \"mobileno\": \"19872570568\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"region\": \"200\",\n" +
                "                \"commid\": \"14679625893_19872603405\",\n" +
                "                \"commtype\": \"50\",\n" +
                "                \"tag\": \"0\",\n" +
                "                \"cityname\": \"广州\",\n" +
                "                \"mobileno\": \"19872603405\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"region\": \"200\",\n" +
                "                \"commid\": \"14679625893_19872603481\",\n" +
                "                \"commtype\": \"50\",\n" +
                "                \"tag\": \"0\",\n" +
                "                \"cityname\": \"广州\",\n" +
                "                \"mobileno\": \"19872603481\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"region\": \"200\",\n" +
                "                \"commid\": \"14679625893_19872643978\",\n" +
                "                \"commtype\": \"50\",\n" +
                "                \"tag\": \"0\",\n" +
                "                \"cityname\": \"广州\",\n" +
                "                \"mobileno\": \"19872643978\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"region\": \"200\",\n" +
                "                \"commid\": \"14679625893_19872603586\",\n" +
                "                \"commtype\": \"50\",\n" +
                "                \"tag\": \"0\",\n" +
                "                \"cityname\": \"广州\",\n" +
                "                \"mobileno\": \"19872603586\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"region\": \"200\",\n" +
                "                \"commid\": \"14679625893_19872570468\",\n" +
                "                \"commtype\": \"50\",\n" +
                "                \"tag\": \"0\",\n" +
                "                \"cityname\": \"广州\",\n" +
                "                \"mobileno\": \"19872570468\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"region\": \"200\",\n" +
                "                \"commid\": \"14679625893_19872643930\",\n" +
                "                \"commtype\": \"50\",\n" +
                "                \"tag\": \"0\",\n" +
                "                \"cityname\": \"广州\",\n" +
                "                \"mobileno\": \"19872643930\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"region\": \"200\",\n" +
                "                \"commid\": \"14679625893_19872570592\",\n" +
                "                \"commtype\": \"50\",\n" +
                "                \"tag\": \"0\",\n" +
                "                \"cityname\": \"广州\",\n" +
                "                \"mobileno\": \"19872570592\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"region\": \"200\",\n" +
                "                \"commid\": \"14679625893_19872579074\",\n" +
                "                \"commtype\": \"50\",\n" +
                "                \"tag\": \"0\",\n" +
                "                \"cityname\": \"广州\",\n" +
                "                \"mobileno\": \"19872579074\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"region\": \"200\",\n" +
                "                \"commid\": \"14679625893_19872570482\",\n" +
                "                \"commtype\": \"50\",\n" +
                "                \"tag\": \"0\",\n" +
                "                \"cityname\": \"广州\",\n" +
                "                \"mobileno\": \"19872570482\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"region\": \"200\",\n" +
                "                \"commid\": \"14679625893_19872577448\",\n" +
                "                \"commtype\": \"50\",\n" +
                "                \"tag\": \"0\",\n" +
                "                \"cityname\": \"广州\",\n" +
                "                \"mobileno\": \"19872577448\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"region\": \"200\",\n" +
                "                \"commid\": \"14679625893_19872579190\",\n" +
                "                \"commtype\": \"50\",\n" +
                "                \"tag\": \"0\",\n" +
                "                \"cityname\": \"广州\",\n" +
                "                \"mobileno\": \"19872579190\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"respcode\": \"0\",\n" +
                "    \"respdesc\": \"成功\",\n" +
                "    \"resptype\": \"0\"\n" +
                "}";


        JSONObject jsonObj = JSONObject.parseObject(jsonString);//转JSONObject对象

        String respcode = jsonObj.getString("respcode");
        String respdesc = jsonObj.getString("respdesc");
        String resptype = jsonObj.getString("resptype");

        System.out.println(respcode);
        System.out.println(respdesc);
        System.out.println(resptype);

        System.out.println(UUID.randomUUID().toString().replaceAll("-",""));

        String infos = JSONObject.parseObject(jsonObj.getString("result")).getString("infos")  ;

        List<QueryChooseNumberListResponse> lists = JSONArray.parseArray(infos, QueryChooseNumberListResponse.class);

        lists.stream().forEach(numberList->{
            if(numberList.getCommtype()!=null && "50".equals(numberList.getCommtype())){
                System.out.println(numberList.getCommid());
                System.out.println(numberList.getMobileno());
            }
        });



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


    }

}
