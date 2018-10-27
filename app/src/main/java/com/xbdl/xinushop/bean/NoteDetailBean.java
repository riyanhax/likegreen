package com.xbdl.xinushop.bean;

import java.util.List;

public class NoteDetailBean {

    /**
     * code : 100
     * msg : 处理成功！
     * extend : {"diary":[{"diaryId":28,"diaryDynamic":"天天 不想 上班","diaryAddressTemperatureWeather":"洛阳-晴天-32","dirayCreateTime":"2018-12-22 15:24:30","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryUserId":null,"diaryDay":3,"diaryRootId":16,"isLike":null,"dirayIamge":[]},{"diaryId":25,"diaryDynamic":"天天 不想 上班","diaryAddressTemperatureWeather":"洛阳-晴天-32","dirayCreateTime":"2018-12-20 15:21:34","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryUserId":null,"diaryDay":1,"diaryRootId":16,"isLike":null,"dirayIamge":[]}]}
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
             * diaryId : 28
             * diaryDynamic : 天天 不想 上班
             * diaryAddressTemperatureWeather : 洛阳-晴天-32
             * dirayCreateTime : 2018-12-22 15:24:30
             * dirayToClickTheNumberOfLikes : null
             * dirayNumberOfComments : null
             * diaryUserId : null
             * diaryDay : 3
             * diaryRootId : 16
             * isLike : null
             * dirayIamge : []
             */

            private int diaryId;
            private String diaryDynamic;
            private String diaryAddressTemperatureWeather;
            private String dirayCreateTime;
            private Object dirayToClickTheNumberOfLikes;
            private Object dirayNumberOfComments;
            private Object diaryUserId;
            private int diaryDay;
            private int diaryRootId;
            private Object isLike;
            private List<?> dirayIamge;

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

            public Object getDirayToClickTheNumberOfLikes() {
                return dirayToClickTheNumberOfLikes;
            }

            public void setDirayToClickTheNumberOfLikes(Object dirayToClickTheNumberOfLikes) {
                this.dirayToClickTheNumberOfLikes = dirayToClickTheNumberOfLikes;
            }

            public Object getDirayNumberOfComments() {
                return dirayNumberOfComments;
            }

            public void setDirayNumberOfComments(Object dirayNumberOfComments) {
                this.dirayNumberOfComments = dirayNumberOfComments;
            }

            public Object getDiaryUserId() {
                return diaryUserId;
            }

            public void setDiaryUserId(Object diaryUserId) {
                this.diaryUserId = diaryUserId;
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

            public Object getIsLike() {
                return isLike;
            }

            public void setIsLike(Object isLike) {
                this.isLike = isLike;
            }

            public List<?> getDirayIamge() {
                return dirayIamge;
            }

            public void setDirayIamge(List<?> dirayIamge) {
                this.dirayIamge = dirayIamge;
            }
        }
    }
}
