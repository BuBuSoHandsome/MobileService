package com.ruoyi.common.enums;

/**
 * Datetime:    2020/3/12   11:45
 * Author:      bjl
 * 移动接口服务地址
 */

public enum  MobileUrl {

    QueryChooseNumberColumn("https://221.179.11.204:443/eaop/rest/BSS/commodity/querychoosenumbercolumn/v1.1.1"),

    JDCheckAddress("https://221.179.11.204:443/eaop/rest/JD/service/jdcheckaddress/v1.1.1"),

    ChooseNumberBusiness("https://221.179.11.204:443/eaop/rest/BSS/commodity/choosenumberbusiness/v1.1.1"),

    QueryDiscountNumberList("https://221.179.11.204:443/eaop/rest/BSS/commodity/querydiscountnumberlist/v1.1.1"),

    QueryChooseNumberList("https://221.179.11.204:443/eaop/rest/BSS/commodity/querychoosenumberlist/v1.1.1"),

    AirpickinstallnewOrder("https://221.179.11.204:443/eaop/rest/OrderCenter/resource/AirpickinstallnewOrder/v1.1.1"),

    DSAirpickinstallQueryOrder("https://221.179.11.204:443/eaop/rest/OrderCenter/resource/dsairpickinstallqueryorder/v1.1.1"),

    QryExpressTrace("https://221.179.11.204:443/eaop/rest/JD/service/qryexpresstrace/v1.1.1");

    private final String url;

    private MobileUrl(String url){
        this.url = url;
    }

    public String getUrl(){
        return url;
    }

}
