package com.xbdl.xinushop.bean;

import java.util.List;

public class PlantCommentBean {

    /**
     * code : 100
     * msg : 处理成功！
     * extend : {"diaryCommentLayers":[{"diaryCommentLayerId":1,"diaryCommentLayerUserId":25,"diaryCommentLayerContent":"沙发","diaryCommentLayerCreateTime":"2018-10-27 10:53:20","diaryId":25,"avatar":"20181018180311664.jpg","userName":"xl_52926565","diaryLayerComments":[{"diaryLayerCommentId":1,"diaryLayerCommentUserId":25,"diaryLayerCommentBeUserId":null,"diaryLayerCommentContent":"沙发你大爷","diaryCommentLayerId":1,"diaryLayerCommentUserName":"xl_52926565","diaryLayerCommentBeUserName":null}]}]}
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
        private List<DiaryCommentLayersBean> diaryCommentLayers;

        public List<DiaryCommentLayersBean> getDiaryCommentLayers() {
            return diaryCommentLayers;
        }

        public void setDiaryCommentLayers(List<DiaryCommentLayersBean> diaryCommentLayers) {
            this.diaryCommentLayers = diaryCommentLayers;
        }

        public static class DiaryCommentLayersBean {
            /**
             * diaryCommentLayerId : 1
             * diaryCommentLayerUserId : 25
             * diaryCommentLayerContent : 沙发
             * diaryCommentLayerCreateTime : 2018-10-27 10:53:20
             * diaryId : 25
             * avatar : 20181018180311664.jpg
             * userName : xl_52926565
             * diaryLayerComments : [{"diaryLayerCommentId":1,"diaryLayerCommentUserId":25,"diaryLayerCommentBeUserId":null,"diaryLayerCommentContent":"沙发你大爷","diaryCommentLayerId":1,"diaryLayerCommentUserName":"xl_52926565","diaryLayerCommentBeUserName":null}]
             */

            private int diaryCommentLayerId;
            private int diaryCommentLayerUserId;
            private String diaryCommentLayerContent;
            private String diaryCommentLayerCreateTime;
            private int diaryId;
            private String avatar;
            private String userName;
            private List<DiaryLayerCommentsBean> diaryLayerComments;

            public int getDiaryCommentLayerId() {
                return diaryCommentLayerId;
            }

            public void setDiaryCommentLayerId(int diaryCommentLayerId) {
                this.diaryCommentLayerId = diaryCommentLayerId;
            }

            public int getDiaryCommentLayerUserId() {
                return diaryCommentLayerUserId;
            }

            public void setDiaryCommentLayerUserId(int diaryCommentLayerUserId) {
                this.diaryCommentLayerUserId = diaryCommentLayerUserId;
            }

            public String getDiaryCommentLayerContent() {
                return diaryCommentLayerContent;
            }

            public void setDiaryCommentLayerContent(String diaryCommentLayerContent) {
                this.diaryCommentLayerContent = diaryCommentLayerContent;
            }

            public String getDiaryCommentLayerCreateTime() {
                return diaryCommentLayerCreateTime;
            }

            public void setDiaryCommentLayerCreateTime(String diaryCommentLayerCreateTime) {
                this.diaryCommentLayerCreateTime = diaryCommentLayerCreateTime;
            }

            public int getDiaryId() {
                return diaryId;
            }

            public void setDiaryId(int diaryId) {
                this.diaryId = diaryId;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public List<DiaryLayerCommentsBean> getDiaryLayerComments() {
                return diaryLayerComments;
            }

            public void setDiaryLayerComments(List<DiaryLayerCommentsBean> diaryLayerComments) {
                this.diaryLayerComments = diaryLayerComments;
            }

            public static class DiaryLayerCommentsBean {
                /**
                 * diaryLayerCommentId : 1
                 * diaryLayerCommentUserId : 25
                 * diaryLayerCommentBeUserId : null
                 * diaryLayerCommentContent : 沙发你大爷
                 * diaryCommentLayerId : 1
                 * diaryLayerCommentUserName : xl_52926565
                 * diaryLayerCommentBeUserName : null
                 */

                private int diaryLayerCommentId;
                private int diaryLayerCommentUserId;
                private int diaryLayerCommentBeUserId;
                private String diaryLayerCommentContent;
                private int diaryCommentLayerId;
                private String diaryLayerCommentUserName;
                private String diaryLayerCommentBeUserName;

                public int getDiaryLayerCommentId() {
                    return diaryLayerCommentId;
                }

                public void setDiaryLayerCommentId(int diaryLayerCommentId) {
                    this.diaryLayerCommentId = diaryLayerCommentId;
                }

                public int getDiaryLayerCommentUserId() {
                    return diaryLayerCommentUserId;
                }

                public void setDiaryLayerCommentUserId(int diaryLayerCommentUserId) {
                    this.diaryLayerCommentUserId = diaryLayerCommentUserId;
                }



                public String getDiaryLayerCommentContent() {
                    return diaryLayerCommentContent;
                }

                public void setDiaryLayerCommentContent(String diaryLayerCommentContent) {
                    this.diaryLayerCommentContent = diaryLayerCommentContent;
                }

                public int getDiaryCommentLayerId() {
                    return diaryCommentLayerId;
                }

                public void setDiaryCommentLayerId(int diaryCommentLayerId) {
                    this.diaryCommentLayerId = diaryCommentLayerId;
                }

                public String getDiaryLayerCommentUserName() {
                    return diaryLayerCommentUserName;
                }

                public void setDiaryLayerCommentUserName(String diaryLayerCommentUserName) {
                    this.diaryLayerCommentUserName = diaryLayerCommentUserName;
                }

                public int getDiaryLayerCommentBeUserId() {
                    return diaryLayerCommentBeUserId;
                }

                public void setDiaryLayerCommentBeUserId(int diaryLayerCommentBeUserId) {
                    this.diaryLayerCommentBeUserId = diaryLayerCommentBeUserId;
                }

                public String getDiaryLayerCommentBeUserName() {
                    return diaryLayerCommentBeUserName;
                }

                public void setDiaryLayerCommentBeUserName(String diaryLayerCommentBeUserName) {
                    this.diaryLayerCommentBeUserName = diaryLayerCommentBeUserName;
                }
            }
        }
    }
}
