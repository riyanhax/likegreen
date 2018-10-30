package com.xbdl.xinushop.bean;

import java.io.Serializable;
import java.util.List;

public class NoteHotBean {


    /**
     * code : 100
     * msg : 处理成功！
     * extend : {"diaryRoots":{"pageNum":1,"pageSize":10,"size":3,"startRow":1,"endRow":3,"total":3,"pages":1,"list":[{"diaryRootId":36,"diaryRootTitle":"测试标题","diaryRootUserId":30,"diaryRootNumberOfViews":null,"fewDays":1,"avatar":null,"userName":"xl_28912798","diarys":[{"diaryId":56,"diaryDynamic":"测试动态","diaryAddressTemperatureWeather":"洛阳市","dirayCreateTime":"2018-10-29 13:53:34","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":1,"diaryRootId":36,"diaryUserId":null,"isLike":true,"dirayIamge":[{"diaryImageId":null,"diaryId":56,"diaryImageUrl":"/upload/2018-10/2018-10-29/62856f9db6ba4959abd6b0ecd47155d4.png","diaryImageHigh":715,"diaryImageWide":640},{"diaryImageId":null,"diaryId":56,"diaryImageUrl":"/upload/2018-10/2018-10-29/7e52cad7eea3498da12293407937317c.png","diaryImageHigh":1334,"diaryImageWide":1000},{"diaryImageId":null,"diaryId":56,"diaryImageUrl":"/upload/2018-10/2018-10-29/1c79f0fddc364327add32c599ca3de0a.png","diaryImageHigh":1001,"diaryImageWide":1334},{"diaryImageId":null,"diaryId":56,"diaryImageUrl":"/upload/2018-10/2018-10-29/0e42cc74ea5648039828d7279fd5d553.png","diaryImageHigh":1334,"diaryImageWide":1000}]}],"concernState":0},{"diaryRootId":35,"diaryRootTitle":"测试标题","diaryRootUserId":30,"diaryRootNumberOfViews":null,"fewDays":1,"avatar":null,"userName":"xl_28912798","diarys":[{"diaryId":55,"diaryDynamic":"测试动态","diaryAddressTemperatureWeather":"洛阳市","dirayCreateTime":"2018-10-29 13:53:34","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":1,"diaryRootId":35,"diaryUserId":null,"isLike":true,"dirayIamge":[{"diaryImageId":null,"diaryId":55,"diaryImageUrl":"/upload/2018-10/2018-10-29/044adc0b06ff405e80b99e6e0847f61c.png","diaryImageHigh":715,"diaryImageWide":640},{"diaryImageId":null,"diaryId":55,"diaryImageUrl":"/upload/2018-10/2018-10-29/eea1e8be98fc484bafb8a7da187d9d43.png","diaryImageHigh":1334,"diaryImageWide":1000}]}],"concernState":0},{"diaryRootId":34,"diaryRootTitle":"图","diaryRootUserId":25,"diaryRootNumberOfViews":null,"fewDays":1,"avatar":"20181018180311664.jpg","userName":"xl_52926565","diarys":[{"diaryId":54,"diaryDynamic":"日记","diaryAddressTemperatureWeather":"广州市-晴-26","dirayCreateTime":"2018-10-29 12:01:33","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":1,"diaryRootId":34,"diaryUserId":null,"isLike":true,"dirayIamge":[{"diaryImageId":null,"diaryId":54,"diaryImageUrl":"/upload/2018-10/2018-10-29/20756b51573943eb9bf7479418d85a44.png","diaryImageHigh":960,"diaryImageWide":540},{"diaryImageId":null,"diaryId":54,"diaryImageUrl":"/upload/2018-10/2018-10-29/e8ceb8441ca34fa98ce7b2de6b82b662.png","diaryImageHigh":960,"diaryImageWide":540}]}],"concernState":0}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":5,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}}
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
         * diaryRoots : {"pageNum":1,"pageSize":10,"size":3,"startRow":1,"endRow":3,"total":3,"pages":1,"list":[{"diaryRootId":36,"diaryRootTitle":"测试标题","diaryRootUserId":30,"diaryRootNumberOfViews":null,"fewDays":1,"avatar":null,"userName":"xl_28912798","diarys":[{"diaryId":56,"diaryDynamic":"测试动态","diaryAddressTemperatureWeather":"洛阳市","dirayCreateTime":"2018-10-29 13:53:34","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":1,"diaryRootId":36,"diaryUserId":null,"isLike":true,"dirayIamge":[{"diaryImageId":null,"diaryId":56,"diaryImageUrl":"/upload/2018-10/2018-10-29/62856f9db6ba4959abd6b0ecd47155d4.png","diaryImageHigh":715,"diaryImageWide":640},{"diaryImageId":null,"diaryId":56,"diaryImageUrl":"/upload/2018-10/2018-10-29/7e52cad7eea3498da12293407937317c.png","diaryImageHigh":1334,"diaryImageWide":1000},{"diaryImageId":null,"diaryId":56,"diaryImageUrl":"/upload/2018-10/2018-10-29/1c79f0fddc364327add32c599ca3de0a.png","diaryImageHigh":1001,"diaryImageWide":1334},{"diaryImageId":null,"diaryId":56,"diaryImageUrl":"/upload/2018-10/2018-10-29/0e42cc74ea5648039828d7279fd5d553.png","diaryImageHigh":1334,"diaryImageWide":1000}]}],"concernState":0},{"diaryRootId":35,"diaryRootTitle":"测试标题","diaryRootUserId":30,"diaryRootNumberOfViews":null,"fewDays":1,"avatar":null,"userName":"xl_28912798","diarys":[{"diaryId":55,"diaryDynamic":"测试动态","diaryAddressTemperatureWeather":"洛阳市","dirayCreateTime":"2018-10-29 13:53:34","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":1,"diaryRootId":35,"diaryUserId":null,"isLike":true,"dirayIamge":[{"diaryImageId":null,"diaryId":55,"diaryImageUrl":"/upload/2018-10/2018-10-29/044adc0b06ff405e80b99e6e0847f61c.png","diaryImageHigh":715,"diaryImageWide":640},{"diaryImageId":null,"diaryId":55,"diaryImageUrl":"/upload/2018-10/2018-10-29/eea1e8be98fc484bafb8a7da187d9d43.png","diaryImageHigh":1334,"diaryImageWide":1000}]}],"concernState":0},{"diaryRootId":34,"diaryRootTitle":"图","diaryRootUserId":25,"diaryRootNumberOfViews":null,"fewDays":1,"avatar":"20181018180311664.jpg","userName":"xl_52926565","diarys":[{"diaryId":54,"diaryDynamic":"日记","diaryAddressTemperatureWeather":"广州市-晴-26","dirayCreateTime":"2018-10-29 12:01:33","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":1,"diaryRootId":34,"diaryUserId":null,"isLike":true,"dirayIamge":[{"diaryImageId":null,"diaryId":54,"diaryImageUrl":"/upload/2018-10/2018-10-29/20756b51573943eb9bf7479418d85a44.png","diaryImageHigh":960,"diaryImageWide":540},{"diaryImageId":null,"diaryId":54,"diaryImageUrl":"/upload/2018-10/2018-10-29/e8ceb8441ca34fa98ce7b2de6b82b662.png","diaryImageHigh":960,"diaryImageWide":540}]}],"concernState":0}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":5,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
         */

        private DiaryRootsBean diaryRoots;

        public DiaryRootsBean getDiaryRoots() {
            return diaryRoots;
        }

        public void setDiaryRoots(DiaryRootsBean diaryRoots) {
            this.diaryRoots = diaryRoots;
        }

        public static class DiaryRootsBean {
            /**
             * pageNum : 1
             * pageSize : 10
             * size : 3
             * startRow : 1
             * endRow : 3
             * total : 3
             * pages : 1
             * list : [{"diaryRootId":36,"diaryRootTitle":"测试标题","diaryRootUserId":30,"diaryRootNumberOfViews":null,"fewDays":1,"avatar":null,"userName":"xl_28912798","diarys":[{"diaryId":56,"diaryDynamic":"测试动态","diaryAddressTemperatureWeather":"洛阳市","dirayCreateTime":"2018-10-29 13:53:34","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":1,"diaryRootId":36,"diaryUserId":null,"isLike":true,"dirayIamge":[{"diaryImageId":null,"diaryId":56,"diaryImageUrl":"/upload/2018-10/2018-10-29/62856f9db6ba4959abd6b0ecd47155d4.png","diaryImageHigh":715,"diaryImageWide":640},{"diaryImageId":null,"diaryId":56,"diaryImageUrl":"/upload/2018-10/2018-10-29/7e52cad7eea3498da12293407937317c.png","diaryImageHigh":1334,"diaryImageWide":1000},{"diaryImageId":null,"diaryId":56,"diaryImageUrl":"/upload/2018-10/2018-10-29/1c79f0fddc364327add32c599ca3de0a.png","diaryImageHigh":1001,"diaryImageWide":1334},{"diaryImageId":null,"diaryId":56,"diaryImageUrl":"/upload/2018-10/2018-10-29/0e42cc74ea5648039828d7279fd5d553.png","diaryImageHigh":1334,"diaryImageWide":1000}]}],"concernState":0},{"diaryRootId":35,"diaryRootTitle":"测试标题","diaryRootUserId":30,"diaryRootNumberOfViews":null,"fewDays":1,"avatar":null,"userName":"xl_28912798","diarys":[{"diaryId":55,"diaryDynamic":"测试动态","diaryAddressTemperatureWeather":"洛阳市","dirayCreateTime":"2018-10-29 13:53:34","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":1,"diaryRootId":35,"diaryUserId":null,"isLike":true,"dirayIamge":[{"diaryImageId":null,"diaryId":55,"diaryImageUrl":"/upload/2018-10/2018-10-29/044adc0b06ff405e80b99e6e0847f61c.png","diaryImageHigh":715,"diaryImageWide":640},{"diaryImageId":null,"diaryId":55,"diaryImageUrl":"/upload/2018-10/2018-10-29/eea1e8be98fc484bafb8a7da187d9d43.png","diaryImageHigh":1334,"diaryImageWide":1000}]}],"concernState":0},{"diaryRootId":34,"diaryRootTitle":"图","diaryRootUserId":25,"diaryRootNumberOfViews":null,"fewDays":1,"avatar":"20181018180311664.jpg","userName":"xl_52926565","diarys":[{"diaryId":54,"diaryDynamic":"日记","diaryAddressTemperatureWeather":"广州市-晴-26","dirayCreateTime":"2018-10-29 12:01:33","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":1,"diaryRootId":34,"diaryUserId":null,"isLike":true,"dirayIamge":[{"diaryImageId":null,"diaryId":54,"diaryImageUrl":"/upload/2018-10/2018-10-29/20756b51573943eb9bf7479418d85a44.png","diaryImageHigh":960,"diaryImageWide":540},{"diaryImageId":null,"diaryId":54,"diaryImageUrl":"/upload/2018-10/2018-10-29/e8ceb8441ca34fa98ce7b2de6b82b662.png","diaryImageHigh":960,"diaryImageWide":540}]}],"concernState":0}]
             * prePage : 0
             * nextPage : 0
             * isFirstPage : true
             * isLastPage : true
             * hasPreviousPage : false
             * hasNextPage : false
             * navigatePages : 5
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
                 * diaryRootId : 36
                 * diaryRootTitle : 测试标题
                 * diaryRootUserId : 30
                 * diaryRootNumberOfViews : null
                 * fewDays : 1
                 * avatar : null
                 * userName : xl_28912798
                 * diarys : [{"diaryId":56,"diaryDynamic":"测试动态","diaryAddressTemperatureWeather":"洛阳市","dirayCreateTime":"2018-10-29 13:53:34","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":1,"diaryRootId":36,"diaryUserId":null,"isLike":true,"dirayIamge":[{"diaryImageId":null,"diaryId":56,"diaryImageUrl":"/upload/2018-10/2018-10-29/62856f9db6ba4959abd6b0ecd47155d4.png","diaryImageHigh":715,"diaryImageWide":640},{"diaryImageId":null,"diaryId":56,"diaryImageUrl":"/upload/2018-10/2018-10-29/7e52cad7eea3498da12293407937317c.png","diaryImageHigh":1334,"diaryImageWide":1000},{"diaryImageId":null,"diaryId":56,"diaryImageUrl":"/upload/2018-10/2018-10-29/1c79f0fddc364327add32c599ca3de0a.png","diaryImageHigh":1001,"diaryImageWide":1334},{"diaryImageId":null,"diaryId":56,"diaryImageUrl":"/upload/2018-10/2018-10-29/0e42cc74ea5648039828d7279fd5d553.png","diaryImageHigh":1334,"diaryImageWide":1000}]}]
                 * concernState : 0
                 */

                private int diaryRootId;
                private String diaryRootTitle;
                private int diaryRootUserId;
                private int diaryRootNumberOfViews;
                private int fewDays;
                private String  avatar;
                private String userName;
                private int concernState;
                private List<DiarysBean> diarys;
                private int numberOfFans;

                public int getNumberOfFans() {
                    return numberOfFans;
                }

                public void setNumberOfFans(int numberOfFans) {
                    this.numberOfFans = numberOfFans;
                }

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

                public int getDiaryRootNumberOfViews() {
                    return diaryRootNumberOfViews;
                }

                public void setDiaryRootNumberOfViews(int diaryRootNumberOfViews) {
                    this.diaryRootNumberOfViews = diaryRootNumberOfViews;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public int getFewDays() {
                    return fewDays;
                }

                public void setFewDays(int fewDays) {
                    this.fewDays = fewDays;
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
                     * diaryId : 56
                     * diaryDynamic : 测试动态
                     * diaryAddressTemperatureWeather : 洛阳市
                     * dirayCreateTime : 2018-10-29 13:53:34
                     * dirayToClickTheNumberOfLikes : null
                     * dirayNumberOfComments : null
                     * diaryDay : 1
                     * diaryRootId : 36
                     * diaryUserId : null
                     * isLike : true
                     * dirayIamge : [{"diaryImageId":null,"diaryId":56,"diaryImageUrl":"/upload/2018-10/2018-10-29/62856f9db6ba4959abd6b0ecd47155d4.png","diaryImageHigh":715,"diaryImageWide":640},{"diaryImageId":null,"diaryId":56,"diaryImageUrl":"/upload/2018-10/2018-10-29/7e52cad7eea3498da12293407937317c.png","diaryImageHigh":1334,"diaryImageWide":1000},{"diaryImageId":null,"diaryId":56,"diaryImageUrl":"/upload/2018-10/2018-10-29/1c79f0fddc364327add32c599ca3de0a.png","diaryImageHigh":1001,"diaryImageWide":1334},{"diaryImageId":null,"diaryId":56,"diaryImageUrl":"/upload/2018-10/2018-10-29/0e42cc74ea5648039828d7279fd5d553.png","diaryImageHigh":1334,"diaryImageWide":1000}]
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

                    public static class DirayIamgeBean {
                        /**
                         * diaryImageId : null
                         * diaryId : 56
                         * diaryImageUrl : /upload/2018-10/2018-10-29/62856f9db6ba4959abd6b0ecd47155d4.png
                         * diaryImageHigh : 715.0
                         * diaryImageWide : 640.0
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
    }
}
