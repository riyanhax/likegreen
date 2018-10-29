package com.xbdl.xinushop.bean;

import java.util.List;

public class NoteDetailBean {


    /**
     * code : 100
     * msg : 处理成功！
     * extend : {"diary":[{"diaryId":59,"diaryDynamic":"测试后续发布222","diaryAddressTemperatureWeather":"(null)-(null)","dirayCreateTime":"2018-10-29 16:27:08","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":1,"diaryRootId":36,"diaryUserId":30,"isLike":null,"dirayIamge":[{"diaryImageId":null,"diaryId":59,"diaryImageUrl":"/upload/2018-10/2018-10-29/ccb677c8c7984f0caf0c0efad1edf2d5.png","diaryImageHigh":469,"diaryImageWide":495},{"diaryImageId":null,"diaryId":59,"diaryImageUrl":"/upload/2018-10/2018-10-29/ac443346176f4f2b853bee2b12863566.png","diaryImageHigh":471,"diaryImageWide":491}]},{"diaryId":56,"diaryDynamic":"测试动态","diaryAddressTemperatureWeather":"洛阳市","dirayCreateTime":"2018-10-29 13:53:34","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":1,"diaryRootId":36,"diaryUserId":null,"isLike":null,"dirayIamge":[{"diaryImageId":null,"diaryId":56,"diaryImageUrl":"/upload/2018-10/2018-10-29/62856f9db6ba4959abd6b0ecd47155d4.png","diaryImageHigh":715,"diaryImageWide":640},{"diaryImageId":null,"diaryId":56,"diaryImageUrl":"/upload/2018-10/2018-10-29/7e52cad7eea3498da12293407937317c.png","diaryImageHigh":1334,"diaryImageWide":1000},{"diaryImageId":null,"diaryId":56,"diaryImageUrl":"/upload/2018-10/2018-10-29/1c79f0fddc364327add32c599ca3de0a.png","diaryImageHigh":1001,"diaryImageWide":1334},{"diaryImageId":null,"diaryId":56,"diaryImageUrl":"/upload/2018-10/2018-10-29/0e42cc74ea5648039828d7279fd5d553.png","diaryImageHigh":1334,"diaryImageWide":1000}]}]}
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
        private List<DiaryBean> diary;

        public List<DiaryBean> getDiary() {
            return diary;
        }

        public void setDiary(List<DiaryBean> diary) {
            this.diary = diary;
        }

        public static class DiaryBean {
            /**
             * diaryId : 59
             * diaryDynamic : 测试后续发布222
             * diaryAddressTemperatureWeather : (null)-(null)
             * dirayCreateTime : 2018-10-29 16:27:08
             * dirayToClickTheNumberOfLikes : null
             * dirayNumberOfComments : null
             * diaryDay : 1
             * diaryRootId : 36
             * diaryUserId : 30
             * isLike : null
             * dirayIamge : [{"diaryImageId":null,"diaryId":59,"diaryImageUrl":"/upload/2018-10/2018-10-29/ccb677c8c7984f0caf0c0efad1edf2d5.png","diaryImageHigh":469,"diaryImageWide":495},{"diaryImageId":null,"diaryId":59,"diaryImageUrl":"/upload/2018-10/2018-10-29/ac443346176f4f2b853bee2b12863566.png","diaryImageHigh":471,"diaryImageWide":491}]
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

            public boolean isLike() {
                return isLike;
            }

            public void setLike(boolean like) {
                isLike = like;
            }

            public List<DirayIamgeBean> getDirayIamge() {
                return dirayIamge;
            }

            public void setDirayIamge(List<DirayIamgeBean> dirayIamge) {
                this.dirayIamge = dirayIamge;
            }

            public static class DirayIamgeBean {
                /**
                 * diaryImageId : null
                 * diaryId : 59
                 * diaryImageUrl : /upload/2018-10/2018-10-29/ccb677c8c7984f0caf0c0efad1edf2d5.png
                 * diaryImageHigh : 469.0
                 * diaryImageWide : 495.0
                 */

                private int diaryImageId;
                private int diaryId;
                private String diaryImageUrl;
                private double diaryImageHigh;
                private double diaryImageWide;

                public int getDiaryImageId() {
                    return diaryImageId;
                }

                public void setDiaryImageId(int diaryImageId) {
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

                public double getDiaryImageHigh() {
                    return diaryImageHigh;
                }

                public void setDiaryImageHigh(double diaryImageHigh) {
                    this.diaryImageHigh = diaryImageHigh;
                }

                public double getDiaryImageWide() {
                    return diaryImageWide;
                }

                public void setDiaryImageWide(double diaryImageWide) {
                    this.diaryImageWide = diaryImageWide;
                }
            }
        }
    }
}
