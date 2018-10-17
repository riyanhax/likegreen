package com.xbdl.xinushop.bean;

public class NetBean {

    /**
     * ret : {"isUsed":0,"token":"110c105bcbd2c18404313b8fcad1d98754633eac","accid":"1113533419133"}
     * requestId : vod9b712c54-ed57-49d2-9ccf-6a55e3282145
     * code : 200
     */

    private RetBean ret;
    private String requestId;
    private int code;

    public RetBean getRet() {
        return ret;
    }

    public void setRet(RetBean ret) {
        this.ret = ret;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class RetBean {
        /**
         * isUsed : 0
         * token : 110c105bcbd2c18404313b8fcad1d98754633eac
         * accid : 1113533419133
         */

        private int isUsed;
        private String token;
        private String accid;

        public int getIsUsed() {
            return isUsed;
        }

        public void setIsUsed(int isUsed) {
            this.isUsed = isUsed;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getAccid() {
            return accid;
        }

        public void setAccid(String accid) {
            this.accid = accid;
        }
    }
}
