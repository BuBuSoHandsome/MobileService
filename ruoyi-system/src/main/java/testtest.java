import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.ChooseNumberColumn;
import com.ruoyi.system.domain.mobileResponse.ChooseNumberColumnResponse;

import java.util.List;

public class testtest {

    public static void main(String[] args) {


        String jsonString = "{\n" +
                "    \"result\": {\n" +
                "        \"infos\": [\n" +
                "            {\n" +
                "                \"position\": 1,\n" +
                "                \"sid\": 1000040002,\n" +
                "                \"text\": \"18元移动王卡\",\n" +
                "                \"updatetime\": 1580635490000,\n" +
                "                \"status\": 1,\n" +
                "                \"describe\": \"享100M国内通用流量，3GB咪咕定向流量\",\n" +
                "                \"constraint\": \"14:\",\n" +
                "                \"pic\": \"/upfile/uploader/itemPushicon/2019-12/20191210103808839-976.png\",\n" +
                "                \"pack\": \"prod.10086000010513\",\n" +
                "                \"channel\": \"newWap\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"position\": 2,\n" +
                "                \"sid\": 1000000003,\n" +
                "                \"text\": \"24元移动王卡\",\n" +
                "                \"updatetime\": 1580635565000,\n" +
                "                \"status\": 1,\n" +
                "                \"describe\": \"享1GB国内通用流量，40GB国内定向流量（27款APP）\",\n" +
                "                \"constraint\": \"14:\",\n" +
                "                \"pic\": \"/upfile/uploader/itemPushicon/2019-12/20191210103851046-718.png\",\n" +
                "                \"pack\": \"prod.10086000014560\",\n" +
                "                \"channel\": \"newWap\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"position\": 3,\n" +
                "                \"sid\": 1000010002,\n" +
                "                \"text\": \"39元优享卡\",\n" +
                "                \"updatetime\": 1580635555000,\n" +
                "                \"status\": 1,\n" +
                "                \"describe\": \"享国内流量5GB，国内通话30分钟\",\n" +
                "                \"constraint\": \"14:\",\n" +
                "                \"pic\": \"/upfile/uploader/itemPushicon/2019-12/20191209231147680-254.png\",\n" +
                "                \"pack\": \"prod.10086000027957\",\n" +
                "                \"channel\": \"newWap\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"position\": 4,\n" +
                "                \"sid\": 1000015002,\n" +
                "                \"text\": \"59元优享卡\",\n" +
                "                \"updatetime\": 1580635541000,\n" +
                "                \"status\": 1,\n" +
                "                \"describe\": \"享国内流量5GB，国内通话150分钟\",\n" +
                "                \"constraint\": \"14:\",\n" +
                "                \"pic\": \"/upfile/uploader/itemPushicon/2019-12/20191209235428892-279.png\",\n" +
                "                \"pack\": \"prod.10086000027958\",\n" +
                "                \"channel\": \"newWap\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"position\": 5,\n" +
                "                \"sid\": 1000035002,\n" +
                "                \"text\": \"新89元畅享卡\",\n" +
                "                \"updatetime\": 1580635500000,\n" +
                "                \"status\": 1,\n" +
                "                \"describe\": \"畅享国内流量10GB，国内通话300分钟\",\n" +
                "                \"constraint\": \"14:\",\n" +
                "                \"pic\": \"/upfile/uploader/itemPushicon/2019-12/20191209232158958-960.png\",\n" +
                "                \"pack\": \"prod.10086000027959\",\n" +
                "                \"channel\": \"newWap\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"position\": 7,\n" +
                "                \"sid\": 1000030002,\n" +
                "                \"text\": \"爱奇艺卡\",\n" +
                "                \"updatetime\": 1580635519000,\n" +
                "                \"status\": 1,\n" +
                "                \"describe\": \"享40G爱奇艺定向流量+1G国内流量+爱奇艺会员权益\",\n" +
                "                \"constraint\": \"14:\",\n" +
                "                \"pic\": \"/upfile/uploader/itemPushicon/2019-12/20191210103923617-429.png\",\n" +
                "                \"pack\": \"aiqiyi\",\n" +
                "                \"channel\": \"newWap\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"position\": 8,\n" +
                "                \"sid\": 1000030003,\n" +
                "                \"text\": \"优酷卡\",\n" +
                "                \"updatetime\": 1580635509000,\n" +
                "                \"status\": 1,\n" +
                "                \"describe\": \"享40G优酷定向流量+1G国内流量+优酷会员权益\",\n" +
                "                \"constraint\": \"14:\",\n" +
                "                \"pic\": \"/upfile/uploader/itemPushicon/2019-12/20191210103941137-893.png\",\n" +
                "                \"pack\": \"youkuka\",\n" +
                "                \"channel\": \"newWap\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"position\": 9,\n" +
                "                \"sid\": 1000000000,\n" +
                "                \"text\": \"万能副卡\",\n" +
                "                \"updatetime\": 1575953341000,\n" +
                "                \"status\": 1,\n" +
                "                \"describe\": \"58元以上主套餐可办理万能副卡，月租10元\",\n" +
                "                \"pic\": \"/upfile/uploader/itemPushicon/2019-12/20191210124900360-314.png\",\n" +
                "                \"pack\": \"res.W310001100182573\",\n" +
                "                \"channel\": \"newWap\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"position\": 10,\n" +
                "                \"sid\": 1000000002,\n" +
                "                \"text\": \"全球通预约\",\n" +
                "                \"updatetime\": 1575953366000,\n" +
                "                \"status\": 1,\n" +
                "                \"describe\": \"线上预约，携本人身份证亲临营业厅办理开户\",\n" +
                "                \"pic\": \"/upfile/uploader/itemPushicon/2019-12/20191210124925270-106.png\",\n" +
                "                \"pack\": \"0\",\n" +
                "                \"channel\": \"newWap\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"position\": 11,\n" +
                "                \"sid\": 1000045002,\n" +
                "                \"text\": \"58元学而思学霸卡\",\n" +
                "                \"updatetime\": 1580635475000,\n" +
                "                \"status\": 1,\n" +
                "                \"describe\": \"学而思轻课APP运营商专区内学前至六年级全量教学视频31天任看\",\n" +
                "                \"constraint\": \"14:\",\n" +
                "                \"pic\": \"/upfile/uploader/itemPushicon/2019-12/20191210104112770-922.png\",\n" +
                "                \"pack\": \"prod.10086000026870\",\n" +
                "                \"channel\": \"newWap\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"position\": 12,\n" +
                "                \"sid\": 1000050002,\n" +
                "                \"text\": \"59元10G优享卡\",\n" +
                "                \"updatetime\": 1580635458000,\n" +
                "                \"status\": 1,\n" +
                "                \"describe\": \"享国内流量10G，国内通话30分钟\",\n" +
                "                \"constraint\": \"14:\",\n" +
                "                \"pic\": \"/upfile/uploader/itemPushicon/2019-12/20191209233754564-219.png\",\n" +
                "                \"pack\": \"prod.10086000027971\",\n" +
                "                \"channel\": \"newWap\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"position\": 14,\n" +
                "                \"sid\": 1000060002,\n" +
                "                \"text\": \"139元5G畅享卡\",\n" +
                "                \"updatetime\": 1580635421000,\n" +
                "                \"status\": 1,\n" +
                "                \"describe\": \"享国内流量30G+国内通话400分钟+融合权益\",\n" +
                "                \"constraint\": \"14:\",\n" +
                "                \"pic\": \"/upfile/uploader/itemPushicon/2019-12/20191209232922839-167.png\",\n" +
                "                \"pack\": \"prod.10086000027962\",\n" +
                "                \"channel\": \"newWap\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"position\": 15,\n" +
                "                \"sid\": 1000060003,\n" +
                "                \"text\": \"199元5G畅享卡\",\n" +
                "                \"updatetime\": 1580635404000,\n" +
                "                \"status\": 1,\n" +
                "                \"describe\": \"享国内流量60G+国内通话700分钟+融合权益\",\n" +
                "                \"constraint\": \"14:\",\n" +
                "                \"pic\": \"/upfile/uploader/itemPushicon/2019-12/20191209232752928-861.png\",\n" +
                "                \"pack\": \"prod.10086000027966\",\n" +
                "                \"channel\": \"newWap\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"respcode\": \"0\",\n" +
                "    \"respdesc\": \"成功\",\n" +
                "    \"resptype\": \"0\"\n" +
                "}";


//        JSONObject jsonObj = JSONObject.parseObject(jsonString);//转JSONObject对象

//        String respcode = jsonObj.getString("respcode");
//        String respdesc = jsonObj.getString("respdesc");
//        String resptype = jsonObj.getString("resptype");
//
//        System.out.println(respcode);
//        System.out.println(respdesc);
//        System.out.println(resptype);
//        String infos = JSONObject.parseObject(jsonObj.getString("result")).getString("infos")  ;
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
