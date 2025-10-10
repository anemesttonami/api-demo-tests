package in.reqres.utils;

import io.qameta.allure.restassured.AllureRestAssured;

public class AllureFormatter {

    private static final AllureRestAssured FILTER = new AllureRestAssured();

    public static AllureRestAssured setReqRespTemplates() {
        FILTER.setRequestTemplate("request.ftl");
        FILTER.setResponseTemplate("response.ftl");

        return FILTER;
    }
}
