package com.yiche.baselibrary.javabean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/23.
 */

public class JsUrlBean implements Serializable {


    /**
     * oss : https://master-api-ycy-beta.saasyc.com/yiche/oss/config
     * url : https://master-api-ycy-beta.saasyc.com/
     * ocr : https://master-api-ycy-beta.saasyc.com/yiche/ocr/idcard
     * h5 : http://master-h5-ycy.beta.saasyc.com
     */

    private String oss;
    private String url;
    private String ocr;
    private String h5;

    public String getOss() {
        return oss;
    }

    public void setOss(String oss) {
        this.oss = oss;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOcr() {
        return ocr;
    }

    public void setOcr(String ocr) {
        this.ocr = ocr;
    }

    public String getH5() {
        return h5;
    }

    public void setH5(String h5) {
        this.h5 = h5;
    }
}


