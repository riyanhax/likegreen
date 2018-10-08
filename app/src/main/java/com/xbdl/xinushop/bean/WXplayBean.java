package com.xbdl.xinushop.bean;

import com.google.gson.annotations.SerializedName;

public class WXplayBean {


    /**
     * msg : 支付成功
     * code : 1
     * json : {"appid":"wx3283ad5062a19c73","noncestr":"e4a6ede6e79e4053ab3e4601825c4f4e","package":"Sign=WXPay","partnerid":"1401955602","prepayid":"wx08134428194288c6f2cbba043876550998","sign":"859B4176D3DCD857705E19A9F19A4ADA","timestamp":1538977468}
     */

    private String msg;
    private int code;
    private JsonBean json;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public JsonBean getJson() {
        return json;
    }

    public void setJson(JsonBean json) {
        this.json = json;
    }

    public static class JsonBean {
        /**
         * appid : wx3283ad5062a19c73
         * noncestr : e4a6ede6e79e4053ab3e4601825c4f4e
         * package : Sign=WXPay
         * partnerid : 1401955602
         * prepayid : wx08134428194288c6f2cbba043876550998
         * sign : 859B4176D3DCD857705E19A9F19A4ADA
         * timestamp : 1538977468
         */

        private String appid;
        private String noncestr;
        @SerializedName("package")
        private String packageX;
        private String partnerid;
        private String prepayid;
        private String sign;
        private int timestamp;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }
    }
}
