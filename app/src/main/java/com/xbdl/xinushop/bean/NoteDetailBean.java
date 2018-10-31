package com.xbdl.xinushop.bean;

import java.io.Serializable;
import java.util.List;

public class NoteDetailBean implements Serializable {

    /**
     * code : 100
     * msg : 处理成功！
     * extend : {"diaryRoot":{"diaryRootId":35,"diaryRootTitle":"测试标题","diaryRootUserId":30,"diaryRootNumberOfViews":null,"numberOfFans":1,"fewDays":2,"avatar":null,"userName":"xl_28912798","diarys":[{"diaryId":60,"diaryDynamic":"测试还可以玩手机呢啊嗯","diaryAddressTemperatureWeather":"(null)-(null)","dirayCreateTime":"2018-10-29 16:28:40","dirayToClickTheNumberOfLikes":1,"dirayNumberOfComments":11,"diaryDay":1,"diaryRootId":35,"diaryUserId":30,"isLike":false,"dirayIamge":[{"diaryImageId":null,"diaryId":60,"diaryImageUrl":"/upload/2018-10/2018-10-29/dbdfaa2e217c4d3996366337a81c351d.png","diaryImageHigh":1000,"diaryImageWide":1334},{"diaryImageId":null,"diaryId":60,"diaryImageUrl":"/upload/2018-10/2018-10-29/4709fdca40514428a14e7c2b7902d6d0.png","diaryImageHigh":1000,"diaryImageWide":1334}]},{"diaryId":61,"diaryDynamic":"测试还可以玩手机呢啊嗯","diaryAddressTemperatureWeather":"(null)-(null)","dirayCreateTime":"2018-10-29 16:28:40","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":1,"diaryRootId":35,"diaryUserId":30,"isLike":false,"dirayIamge":[{"diaryImageId":null,"diaryId":61,"diaryImageUrl":"/upload/2018-10/2018-10-29/10cf51d250264e94bea81f7816611049.png","diaryImageHigh":1000,"diaryImageWide":1334},{"diaryImageId":null,"diaryId":61,"diaryImageUrl":"/upload/2018-10/2018-10-29/c8ab246b201a4d87ae8b083cc3b0d633.png","diaryImageHigh":1000,"diaryImageWide":1334},{"diaryImageId":null,"diaryId":61,"diaryImageUrl":"/upload/2018-10/2018-10-29/baaa08aa590a42ac93be772c1a26c699.png","diaryImageHigh":1000,"diaryImageWide":1334},{"diaryImageId":null,"diaryId":61,"diaryImageUrl":"/upload/2018-10/2018-10-29/8532862df1584de3aa93fafaddd7d6d2.png","diaryImageHigh":1000,"diaryImageWide":1334}]},{"diaryId":55,"diaryDynamic":"测试动态","diaryAddressTemperatureWeather":"洛阳市","dirayCreateTime":"2018-10-29 13:53:34","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":1,"diaryRootId":35,"diaryUserId":null,"isLike":false,"dirayIamge":[{"diaryImageId":null,"diaryId":55,"diaryImageUrl":"/upload/2018-10/2018-10-29/044adc0b06ff405e80b99e6e0847f61c.png","diaryImageHigh":715,"diaryImageWide":640},{"diaryImageId":null,"diaryId":55,"diaryImageUrl":"/upload/2018-10/2018-10-29/eea1e8be98fc484bafb8a7da187d9d43.png","diaryImageHigh":1334,"diaryImageWide":1000}]}],"concernState":0}}
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

    public static class ExtendBean implements Serializable{
        /**
         * diaryRoot : {"diaryRootId":35,"diaryRootTitle":"测试标题","diaryRootUserId":30,"diaryRootNumberOfViews":null,"numberOfFans":1,"fewDays":2,"avatar":null,"userName":"xl_28912798","diarys":[{"diaryId":60,"diaryDynamic":"测试还可以玩手机呢啊嗯","diaryAddressTemperatureWeather":"(null)-(null)","dirayCreateTime":"2018-10-29 16:28:40","dirayToClickTheNumberOfLikes":1,"dirayNumberOfComments":11,"diaryDay":1,"diaryRootId":35,"diaryUserId":30,"isLike":false,"dirayIamge":[{"diaryImageId":null,"diaryId":60,"diaryImageUrl":"/upload/2018-10/2018-10-29/dbdfaa2e217c4d3996366337a81c351d.png","diaryImageHigh":1000,"diaryImageWide":1334},{"diaryImageId":null,"diaryId":60,"diaryImageUrl":"/upload/2018-10/2018-10-29/4709fdca40514428a14e7c2b7902d6d0.png","diaryImageHigh":1000,"diaryImageWide":1334}]},{"diaryId":61,"diaryDynamic":"测试还可以玩手机呢啊嗯","diaryAddressTemperatureWeather":"(null)-(null)","dirayCreateTime":"2018-10-29 16:28:40","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":1,"diaryRootId":35,"diaryUserId":30,"isLike":false,"dirayIamge":[{"diaryImageId":null,"diaryId":61,"diaryImageUrl":"/upload/2018-10/2018-10-29/10cf51d250264e94bea81f7816611049.png","diaryImageHigh":1000,"diaryImageWide":1334},{"diaryImageId":null,"diaryId":61,"diaryImageUrl":"/upload/2018-10/2018-10-29/c8ab246b201a4d87ae8b083cc3b0d633.png","diaryImageHigh":1000,"diaryImageWide":1334},{"diaryImageId":null,"diaryId":61,"diaryImageUrl":"/upload/2018-10/2018-10-29/baaa08aa590a42ac93be772c1a26c699.png","diaryImageHigh":1000,"diaryImageWide":1334},{"diaryImageId":null,"diaryId":61,"diaryImageUrl":"/upload/2018-10/2018-10-29/8532862df1584de3aa93fafaddd7d6d2.png","diaryImageHigh":1000,"diaryImageWide":1334}]},{"diaryId":55,"diaryDynamic":"测试动态","diaryAddressTemperatureWeather":"洛阳市","dirayCreateTime":"2018-10-29 13:53:34","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":1,"diaryRootId":35,"diaryUserId":null,"isLike":false,"dirayIamge":[{"diaryImageId":null,"diaryId":55,"diaryImageUrl":"/upload/2018-10/2018-10-29/044adc0b06ff405e80b99e6e0847f61c.png","diaryImageHigh":715,"diaryImageWide":640},{"diaryImageId":null,"diaryId":55,"diaryImageUrl":"/upload/2018-10/2018-10-29/eea1e8be98fc484bafb8a7da187d9d43.png","diaryImageHigh":1334,"diaryImageWide":1000}]}],"concernState":0}
         */

        private DiaryRootBean diaryRoot;

        public DiaryRootBean getDiaryRoot() {
            return diaryRoot;
        }

        public void setDiaryRoot(DiaryRootBean diaryRoot) {
            this.diaryRoot = diaryRoot;
        }

        public static class DiaryRootBean implements Serializable{
            /**
             * diaryRootId : 35
             * diaryRootTitle : 测试标题
             * diaryRootUserId : 30
             * diaryRootNumberOfViews : null
             * numberOfFans : 1
             * fewDays : 2
             * avatar : null
             * userName : xl_28912798
             * diarys : [{"diaryId":60,"diaryDynamic":"测试还可以玩手机呢啊嗯","diaryAddressTemperatureWeather":"(null)-(null)","dirayCreateTime":"2018-10-29 16:28:40","dirayToClickTheNumberOfLikes":1,"dirayNumberOfComments":11,"diaryDay":1,"diaryRootId":35,"diaryUserId":30,"isLike":false,"dirayIamge":[{"diaryImageId":null,"diaryId":60,"diaryImageUrl":"/upload/2018-10/2018-10-29/dbdfaa2e217c4d3996366337a81c351d.png","diaryImageHigh":1000,"diaryImageWide":1334},{"diaryImageId":null,"diaryId":60,"diaryImageUrl":"/upload/2018-10/2018-10-29/4709fdca40514428a14e7c2b7902d6d0.png","diaryImageHigh":1000,"diaryImageWide":1334}]},{"diaryId":61,"diaryDynamic":"测试还可以玩手机呢啊嗯","diaryAddressTemperatureWeather":"(null)-(null)","dirayCreateTime":"2018-10-29 16:28:40","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":1,"diaryRootId":35,"diaryUserId":30,"isLike":false,"dirayIamge":[{"diaryImageId":null,"diaryId":61,"diaryImageUrl":"/upload/2018-10/2018-10-29/10cf51d250264e94bea81f7816611049.png","diaryImageHigh":1000,"diaryImageWide":1334},{"diaryImageId":null,"diaryId":61,"diaryImageUrl":"/upload/2018-10/2018-10-29/c8ab246b201a4d87ae8b083cc3b0d633.png","diaryImageHigh":1000,"diaryImageWide":1334},{"diaryImageId":null,"diaryId":61,"diaryImageUrl":"/upload/2018-10/2018-10-29/baaa08aa590a42ac93be772c1a26c699.png","diaryImageHigh":1000,"diaryImageWide":1334},{"diaryImageId":null,"diaryId":61,"diaryImageUrl":"/upload/2018-10/2018-10-29/8532862df1584de3aa93fafaddd7d6d2.png","diaryImageHigh":1000,"diaryImageWide":1334}]},{"diaryId":55,"diaryDynamic":"测试动态","diaryAddressTemperatureWeather":"洛阳市","dirayCreateTime":"2018-10-29 13:53:34","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":1,"diaryRootId":35,"diaryUserId":null,"isLike":false,"dirayIamge":[{"diaryImageId":null,"diaryId":55,"diaryImageUrl":"/upload/2018-10/2018-10-29/044adc0b06ff405e80b99e6e0847f61c.png","diaryImageHigh":715,"diaryImageWide":640},{"diaryImageId":null,"diaryId":55,"diaryImageUrl":"/upload/2018-10/2018-10-29/eea1e8be98fc484bafb8a7da187d9d43.png","diaryImageHigh":1334,"diaryImageWide":1000}]}]
             * concernState : 0
             */

            private int diaryRootId;
            private String diaryRootTitle;
            private int diaryRootUserId;
            private int diaryRootNumberOfViews;
            private int numberOfFans;
            private int fewDays;
            private String avatar;
            private String userName;
            private int concernState;
            private List<DiarysBean> diarys;

            public int getDiaryRootId() {
                return diaryRootId;
            }

            public void setDiaryRootId(int diaryRootId) {
                this.diaryRootId = diaryRootId;
            }

            public String getDiaryRootTitle() {
                return diaryRootTitle;
            }

            public void setDiaryRootTitle(String diaryRootTitle) {
                this.diaryRootTitle = diaryRootTitle;
            }

            public int getDiaryRootUserId() {
                return diaryRootUserId;
            }

            public void setDiaryRootUserId(int diaryRootUserId) {
                this.diaryRootUserId = diaryRootUserId;
            }



            public int getNumberOfFans() {
                return numberOfFans;
            }

            public void setNumberOfFans(int numberOfFans) {
                this.numberOfFans = numberOfFans;
            }

            public int getFewDays() {
                return fewDays;
            }

            public void setFewDays(int fewDays) {
                this.fewDays = fewDays;
            }

            public Object getAvatar() {
                return avatar;
            }

            public int getDiaryRootNumberOfViews() {
                return diaryRootNumberOfViews;
            }

            public void setDiaryRootNumberOfViews(int diaryRootNumberOfViews) {
                this.diaryRootNumberOfViews = diaryRootNumberOfViews;
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

            public int getConcernState() {
                return concernState;
            }

            public void setConcernState(int concernState) {
                this.concernState = concernState;
            }

            public List<DiarysBean> getDiarys() {
                return diarys;
            }

            public void setDiarys(List<DiarysBean> diarys) {
                this.diarys = diarys;
            }

            public static class DiarysBean implements Serializable{
                /**
                 * diaryId : 60
                 * diaryDynamic : 测试还可以玩手机呢啊嗯
                 * diaryAddressTemperatureWeather : (null)-(null)
                 * dirayCreateTime : 2018-10-29 16:28:40
                 * dirayToClickTheNumberOfLikes : 1
                 * dirayNumberOfComments : 11
                 * diaryDay : 1
                 * diaryRootId : 35
                 * diaryUserId : 30
                 * isLike : false
                 * dirayIamge : [{"diaryImageId":null,"diaryId":60,"diaryImageUrl":"/upload/2018-10/2018-10-29/dbdfaa2e217c4d3996366337a81c351d.png","diaryImageHigh":1000,"diaryImageWide":1334},{"diaryImageId":null,"diaryId":60,"diaryImageUrl":"/upload/2018-10/2018-10-29/4709fdca40514428a14e7c2b7902d6d0.png","diaryImageHigh":1000,"diaryImageWide":1334}]
                 */

                private int diaryId;
                private String diaryDynamic;
                private String diaryAddressTemperatureWeather;
                private String dirayCreateTime;
                private int dirayToClickTheNumberOfLikes;
                private int dirayNumberOfComments;
                private int diaryDay;
                private int diaryRootId;
                private int diaryUserId;
                private boolean isLike;
                private List<DirayIamgeBean> dirayIamge;

                public int getDiaryId() {
                    return diaryId;
                }

                public void setDiaryId(int diaryId) {
                    this.diaryId = diaryId;
                }

                public String getDiaryDynamic() {
                    return diaryDynamic;
                }

                public void setDiaryDynamic(String diaryDynamic) {
                    this.diaryDynamic = diaryDynamic;
                }

                public String getDiaryAddressTemperatureWeather() {
                    return diaryAddressTemperatureWeather;
                }

                public void setDiaryAddressTemperatureWeather(String diaryAddressTemperatureWeather) {
                    this.diaryAddressTemperatureWeather = diaryAddressTemperatureWeather;
                }

                public String getDirayCreateTime() {
                    return dirayCreateTime;
                }

                public void setDirayCreateTime(String dirayCreateTime) {
                    this.dirayCreateTime = dirayCreateTime;
                }

                public int getDirayToClickTheNumberOfLikes() {
                    return dirayToClickTheNumberOfLikes;
                }

                public void setDirayToClickTheNumberOfLikes(int dirayToClickTheNumberOfLikes) {
                    this.dirayToClickTheNumberOfLikes = dirayToClickTheNumberOfLikes;
                }

                public int getDirayNumberOfComments() {
                    return dirayNumberOfComments;
                }

                public void setDirayNumberOfComments(int dirayNumberOfComments) {
                    this.dirayNumberOfComments = dirayNumberOfComments;
                }

                public int getDiaryDay() {
                    return diaryDay;
                }

                public void setDiaryDay(int diaryDay) {
                    this.diaryDay = diaryDay;
                }

                public int getDiaryRootId() {
                    return diaryRootId;
                }

                public void setDiaryRootId(int diaryRootId) {
                    this.diaryRootId = diaryRootId;
                }

                public int getDiaryUserId() {
                    return diaryUserId;
                }

                public void setDiaryUserId(int diaryUserId) {
                    this.diaryUserId = diaryUserId;
                }

                public boolean isIsLike() {
                    return isLike;
                }

                public void setIsLike(boolean isLike) {
                    this.isLike = isLike;
                }

                public List<DirayIamgeBean> getDirayIamge() {
                    return dirayIamge;
                }

                public void setDirayIamge(List<DirayIamgeBean> dirayIamge) {
                    this.dirayIamge = dirayIamge;
                }

                @Override
                public String toString() {
                    return "DiarysBean{" +
                            "diaryId=" + diaryId +
                            ", diaryDynamic='" + diaryDynamic + '\'' +
                            ", diaryAddressTemperatureWeather='" + diaryAddressTemperatureWeather + '\'' +
                            ", dirayCreateTime='" + dirayCreateTime + '\'' +
                            ", dirayToClickTheNumberOfLikes=" + dirayToClickTheNumberOfLikes +
                            ", dirayNumberOfComments=" + dirayNumberOfComments +
                            ", diaryDay=" + diaryDay +
                            ", diaryRootId=" + diaryRootId +
                            ", diaryUserId=" + diaryUserId +
                            ", isLike=" + isLike +
                            ", dirayIamge=" + dirayIamge +
                            '}';
                }

                public static class DirayIamgeBean implements Serializable{
                    /**
                     * diaryImageId : null
                     * diaryId : 60
                     * diaryImageUrl : /upload/2018-10/2018-10-29/dbdfaa2e217c4d3996366337a81c351d.png
                     * diaryImageHigh : 1000
                     * diaryImageWide : 1334
                     */

                    private Object diaryImageId;
                    private int diaryId;
                    private String diaryImageUrl;
                    private int diaryImageHigh;
                    private int diaryImageWide;

                    public Object getDiaryImageId() {
                        return diaryImageId;
                    }

                    public void setDiaryImageId(Object diaryImageId) {
                        this.diaryImageId = diaryImageId;
                    }

                    public int getDiaryId() {
                        return diaryId;
                    }

                    public void setDiaryId(int diaryId) {
                        this.diaryId = diaryId;
                    }

                    public String getDiaryImageUrl() {
                        return diaryImageUrl;
                    }

                    public void setDiaryImageUrl(String diaryImageUrl) {
                        this.diaryImageUrl = diaryImageUrl;
                    }

                    public int getDiaryImageHigh() {
                        return diaryImageHigh;
                    }

                    public void setDiaryImageHigh(int diaryImageHigh) {
                        this.diaryImageHigh = diaryImageHigh;
                    }

                    public int getDiaryImageWide() {
                        return diaryImageWide;
                    }

                    public void setDiaryImageWide(int diaryImageWide) {
                        this.diaryImageWide = diaryImageWide;
                    }
                }
            }
        }
    }
}
