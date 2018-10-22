package com.xbdl.xinushop.bean;

public class VideoIconBean {

    /**
     * code : 100
     * msg : 处理成功！
     * extend : {"icon":{"thunbUpFor":1,"whetherSomeParise":0,"numberOfComments":3}}
     * object : null
     */

    private int code;
    private String msg;
    private ExtendBean extend;
    private Object object;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ExtendBean getExtend() {
        return extend;
    }

    public void setExtend(ExtendBean extend) {
        this.extend = extend;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public static class ExtendBean {
        /**
         * icon : {"thunbUpFor":1,"whetherSomeParise":0,"numberOfComments":3}
         */

        private IconBean icon;

        public IconBean getIcon() {
            return icon;
        }

        public void setIcon(IconBean icon) {
            this.icon = icon;
        }

        public static class IconBean {
            /**
             * thunbUpFor : 1
             * whetherSomeParise : 0
             * numberOfComments : 3
             */

            private int thunbUpFor;
            private int whetherSomeParise;
            private int numberOfComments;

            public int getThunbUpFor() {
                return thunbUpFor;
            }

            public void setThunbUpFor(int thunbUpFor) {
                this.thunbUpFor = thunbUpFor;
            }

            public int getWhetherSomeParise() {
                return whetherSomeParise;
            }

            public void setWhetherSomeParise(int whetherSomeParise) {
                this.whetherSomeParise = whetherSomeParise;
            }

            public int getNumberOfComments() {
                return numberOfComments;
            }

            public void setNumberOfComments(int numberOfComments) {
                this.numberOfComments = numberOfComments;
            }
        }
    }
}
