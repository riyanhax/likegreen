package com.xbdl.xinushop.bean;

import java.io.Serializable;
import java.util.List;

public class MyFansBean {

    /**
     * msg : 查询成功
     * code : 100
     * pd : {"pageNum":1,"pageSize":1,"size":1,"startRow":0,"endRow":0,"total":1,"pages":1,"list":[{"clientId":null,"signature":"也做了","user_name":"静鸡鸡","sex":null,"background_img":null,"wechat":null,"xl_id":"29538429","register_time":"2018-09-25 16:27:44","realName":null,"password":"12345678","user_id":11,"user_phone":"13533419133","accid":null,"login_token":"f0caa16f9d1640bcba2872026dcca32f","toekn":null,"accountBalance":0,"head_portrait":"20181010221410723.jpg"}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
     */

    private String msg;
    private int code;
    private PdBean pd;

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

    public PdBean getPd() {
        return pd;
    }

    public void setPd(PdBean pd) {
        this.pd = pd;
    }

    public static class PdBean {
        /**
         * pageNum : 1
         * pageSize : 1
         * size : 1
         * startRow : 0
         * endRow : 0
         * total : 1
         * pages : 1
         * list : [{"clientId":null,"signature":"也做了","user_name":"静鸡鸡","sex":null,"background_img":null,"wechat":null,"xl_id":"29538429","register_time":"2018-09-25 16:27:44","realName":null,"password":"12345678","user_id":11,"user_phone":"13533419133","accid":null,"login_token":"f0caa16f9d1640bcba2872026dcca32f","toekn":null,"accountBalance":0,"head_portrait":"20181010221410723.jpg"}]
         * prePage : 0
         * nextPage : 0
         * isFirstPage : true
         * isLastPage : true
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 8
         * navigatepageNums : [1]
         * navigateFirstPage : 1
         * navigateLastPage : 1
         * firstPage : 1
         * lastPage : 1
         */

        private int pageNum;
        private int pageSize;
        private int size;
        private int startRow;
        private int endRow;
        private int total;
        private int pages;
        private int prePage;
        private int nextPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private boolean hasPreviousPage;
        private boolean hasNextPage;
        private int navigatePages;
        private int navigateFirstPage;
        private int navigateLastPage;
        private int firstPage;
        private int lastPage;
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNavigateFirstPage() {
            return navigateFirstPage;
        }

        public void setNavigateFirstPage(int navigateFirstPage) {
            this.navigateFirstPage = navigateFirstPage;
        }

        public int getNavigateLastPage() {
            return navigateLastPage;
        }

        public void setNavigateLastPage(int navigateLastPage) {
            this.navigateLastPage = navigateLastPage;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean implements Serializable{
            /**
             * clientId : null
             * signature : 也做了
             * user_name : 静鸡鸡
             * sex : null
             * background_img : null
             * wechat : null
             * xl_id : 29538429
             * register_time : 2018-09-25 16:27:44
             * realName : null
             * password : 12345678
             * user_id : 11
             * user_phone : 13533419133
             * accid : null
             * login_token : f0caa16f9d1640bcba2872026dcca32f
             * toekn : null
             * accountBalance : 0
             * head_portrait : 20181010221410723.jpg
             */

            private Object clientId;
            private String signature;
            private String user_name;
            private Object sex;
            private Object background_img;
            private Object wechat;
            private String xl_id;
            private String register_time;
            private Object realName;
            private String password;
            private int user_id;
            private String user_phone;
            private Object accid;
            private String login_token;
            private Object toekn;
            private int accountBalance;
            private String head_portrait;

            public Object getClientId() {
                return clientId;
            }

            public void setClientId(Object clientId) {
                this.clientId = clientId;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public Object getSex() {
                return sex;
            }

            public void setSex(Object sex) {
                this.sex = sex;
            }

            public Object getBackground_img() {
                return background_img;
            }

            public void setBackground_img(Object background_img) {
                this.background_img = background_img;
            }

            public Object getWechat() {
                return wechat;
            }

            public void setWechat(Object wechat) {
                this.wechat = wechat;
            }

            public String getXl_id() {
                return xl_id;
            }

            public void setXl_id(String xl_id) {
                this.xl_id = xl_id;
            }

            public String getRegister_time() {
                return register_time;
            }

            public void setRegister_time(String register_time) {
                this.register_time = register_time;
            }

            public Object getRealName() {
                return realName;
            }

            public void setRealName(Object realName) {
                this.realName = realName;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getUser_phone() {
                return user_phone;
            }

            public void setUser_phone(String user_phone) {
                this.user_phone = user_phone;
            }

            public Object getAccid() {
                return accid;
            }

            public void setAccid(Object accid) {
                this.accid = accid;
            }

            public String getLogin_token() {
                return login_token;
            }

            public void setLogin_token(String login_token) {
                this.login_token = login_token;
            }

            public Object getToekn() {
                return toekn;
            }

            public void setToekn(Object toekn) {
                this.toekn = toekn;
            }

            public int getAccountBalance() {
                return accountBalance;
            }

            public void setAccountBalance(int accountBalance) {
                this.accountBalance = accountBalance;
            }

            public String getHead_portrait() {
                return head_portrait;
            }

            public void setHead_portrait(String head_portrait) {
                this.head_portrait = head_portrait;
            }
        }
    }
}
