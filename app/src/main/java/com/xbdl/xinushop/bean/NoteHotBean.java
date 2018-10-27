package com.xbdl.xinushop.bean;

import java.util.List;

public class NoteHotBean {


    /**
     * code : 100
     * msg : 处理成功！
     * extend : {"diaryRoots":{"pageNum":1,"pageSize":10,"size":4,"startRow":1,"endRow":4,"total":4,"pages":1,"list":[{"diaryRootId":16,"diaryRootTitle":"haha ","diaryRootUserId":5,"diaryRootNumberOfViews":null,"isConcerned":false,"avatar":"5","userName":"5","diarys":[{"diaryId":28,"diaryDynamic":"天天 不想 上班","diaryAddressTemperatureWeather":"洛阳-晴天-32","dirayCreateTime":"2018-12-22 15:24:30","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":3,"diaryRootId":16,"isLike":true,"dirayIamge":[]},{"diaryId":25,"diaryDynamic":"天天 不想 上班","diaryAddressTemperatureWeather":"洛阳-晴天-32","dirayCreateTime":"2018-12-20 15:21:34","dirayToClickTheNumberOfLikes":1,"dirayNumberOfComments":2,"diaryDay":1,"diaryRootId":16,"isLike":true,"dirayIamge":[]}]},{"diaryRootId":17,"diaryRootTitle":"haha ","diaryRootUserId":5,"diaryRootNumberOfViews":null,"isConcerned":false,"avatar":"5","userName":"5","diarys":[{"diaryId":26,"diaryDynamic":"天天 不想 上班","diaryAddressTemperatureWeather":"洛阳-晴天-32","dirayCreateTime":"2018-12-21 15:22:34","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":1,"diaryRootId":17,"isLike":true,"dirayIamge":[]},{"diaryId":29,"diaryDynamic":"天天 不想 上班","diaryAddressTemperatureWeather":"洛阳-晴天-32","dirayCreateTime":"2018-12-20 15:25:40","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":0,"diaryRootId":17,"isLike":true,"dirayIamge":[]}]},{"diaryRootId":15,"diaryRootTitle":"haha ","diaryRootUserId":5,"diaryRootNumberOfViews":null,"isConcerned":false,"avatar":"5","userName":"5","diarys":[{"diaryId":27,"diaryDynamic":"天天 不想 上班","diaryAddressTemperatureWeather":"洛阳-晴天-32","dirayCreateTime":"2018-12-20 15:23:34","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":1,"diaryRootId":15,"isLike":true,"dirayIamge":[]}]},{"diaryRootId":20,"diaryRootTitle":"有","diaryRootUserId":25,"diaryRootNumberOfViews":null,"isConcerned":false,"avatar":"20181018180311664.jpg","userName":"xl_52926565","diarys":[{"diaryId":32,"diaryDynamic":"有","diaryAddressTemperatureWeather":"广州市-晴-23","dirayCreateTime":"2018-10-27 14:32:31","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":1,"diaryRootId":20,"isLike":true,"dirayIamge":[]}]}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":5,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}}
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
         * diaryRoots : {"pageNum":1,"pageSize":10,"size":4,"startRow":1,"endRow":4,"total":4,"pages":1,"list":[{"diaryRootId":16,"diaryRootTitle":"haha ","diaryRootUserId":5,"diaryRootNumberOfViews":null,"isConcerned":false,"avatar":"5","userName":"5","diarys":[{"diaryId":28,"diaryDynamic":"天天 不想 上班","diaryAddressTemperatureWeather":"洛阳-晴天-32","dirayCreateTime":"2018-12-22 15:24:30","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":3,"diaryRootId":16,"isLike":true,"dirayIamge":[]},{"diaryId":25,"diaryDynamic":"天天 不想 上班","diaryAddressTemperatureWeather":"洛阳-晴天-32","dirayCreateTime":"2018-12-20 15:21:34","dirayToClickTheNumberOfLikes":1,"dirayNumberOfComments":2,"diaryDay":1,"diaryRootId":16,"isLike":true,"dirayIamge":[]}]},{"diaryRootId":17,"diaryRootTitle":"haha ","diaryRootUserId":5,"diaryRootNumberOfViews":null,"isConcerned":false,"avatar":"5","userName":"5","diarys":[{"diaryId":26,"diaryDynamic":"天天 不想 上班","diaryAddressTemperatureWeather":"洛阳-晴天-32","dirayCreateTime":"2018-12-21 15:22:34","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":1,"diaryRootId":17,"isLike":true,"dirayIamge":[]},{"diaryId":29,"diaryDynamic":"天天 不想 上班","diaryAddressTemperatureWeather":"洛阳-晴天-32","dirayCreateTime":"2018-12-20 15:25:40","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":0,"diaryRootId":17,"isLike":true,"dirayIamge":[]}]},{"diaryRootId":15,"diaryRootTitle":"haha ","diaryRootUserId":5,"diaryRootNumberOfViews":null,"isConcerned":false,"avatar":"5","userName":"5","diarys":[{"diaryId":27,"diaryDynamic":"天天 不想 上班","diaryAddressTemperatureWeather":"洛阳-晴天-32","dirayCreateTime":"2018-12-20 15:23:34","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":1,"diaryRootId":15,"isLike":true,"dirayIamge":[]}]},{"diaryRootId":20,"diaryRootTitle":"有","diaryRootUserId":25,"diaryRootNumberOfViews":null,"isConcerned":false,"avatar":"20181018180311664.jpg","userName":"xl_52926565","diarys":[{"diaryId":32,"diaryDynamic":"有","diaryAddressTemperatureWeather":"广州市-晴-23","dirayCreateTime":"2018-10-27 14:32:31","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":1,"diaryRootId":20,"isLike":true,"dirayIamge":[]}]}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":5,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
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
             * size : 4
             * startRow : 1
             * endRow : 4
             * total : 4
             * pages : 1
             * list : [{"diaryRootId":16,"diaryRootTitle":"haha ","diaryRootUserId":5,"diaryRootNumberOfViews":null,"isConcerned":false,"avatar":"5","userName":"5","diarys":[{"diaryId":28,"diaryDynamic":"天天 不想 上班","diaryAddressTemperatureWeather":"洛阳-晴天-32","dirayCreateTime":"2018-12-22 15:24:30","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":3,"diaryRootId":16,"isLike":true,"dirayIamge":[]},{"diaryId":25,"diaryDynamic":"天天 不想 上班","diaryAddressTemperatureWeather":"洛阳-晴天-32","dirayCreateTime":"2018-12-20 15:21:34","dirayToClickTheNumberOfLikes":1,"dirayNumberOfComments":2,"diaryDay":1,"diaryRootId":16,"isLike":true,"dirayIamge":[]}]},{"diaryRootId":17,"diaryRootTitle":"haha ","diaryRootUserId":5,"diaryRootNumberOfViews":null,"isConcerned":false,"avatar":"5","userName":"5","diarys":[{"diaryId":26,"diaryDynamic":"天天 不想 上班","diaryAddressTemperatureWeather":"洛阳-晴天-32","dirayCreateTime":"2018-12-21 15:22:34","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":1,"diaryRootId":17,"isLike":true,"dirayIamge":[]},{"diaryId":29,"diaryDynamic":"天天 不想 上班","diaryAddressTemperatureWeather":"洛阳-晴天-32","dirayCreateTime":"2018-12-20 15:25:40","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":0,"diaryRootId":17,"isLike":true,"dirayIamge":[]}]},{"diaryRootId":15,"diaryRootTitle":"haha ","diaryRootUserId":5,"diaryRootNumberOfViews":null,"isConcerned":false,"avatar":"5","userName":"5","diarys":[{"diaryId":27,"diaryDynamic":"天天 不想 上班","diaryAddressTemperatureWeather":"洛阳-晴天-32","dirayCreateTime":"2018-12-20 15:23:34","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":1,"diaryRootId":15,"isLike":true,"dirayIamge":[]}]},{"diaryRootId":20,"diaryRootTitle":"有","diaryRootUserId":25,"diaryRootNumberOfViews":null,"isConcerned":false,"avatar":"20181018180311664.jpg","userName":"xl_52926565","diarys":[{"diaryId":32,"diaryDynamic":"有","diaryAddressTemperatureWeather":"广州市-晴-23","dirayCreateTime":"2018-10-27 14:32:31","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":1,"diaryRootId":20,"isLike":true,"dirayIamge":[]}]}]
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

            public static class ListBean {
                /**
                 * diaryRootId : 16
                 * diaryRootTitle : haha
                 * diaryRootUserId : 5
                 * diaryRootNumberOfViews : null
                 * isConcerned : false
                 * avatar : 5
                 * userName : 5
                 * diarys : [{"diaryId":28,"diaryDynamic":"天天 不想 上班","diaryAddressTemperatureWeather":"洛阳-晴天-32","dirayCreateTime":"2018-12-22 15:24:30","dirayToClickTheNumberOfLikes":null,"dirayNumberOfComments":null,"diaryDay":3,"diaryRootId":16,"isLike":true,"dirayIamge":[]},{"diaryId":25,"diaryDynamic":"天天 不想 上班","diaryAddressTemperatureWeather":"洛阳-晴天-32","dirayCreateTime":"2018-12-20 15:21:34","dirayToClickTheNumberOfLikes":1,"dirayNumberOfComments":2,"diaryDay":1,"diaryRootId":16,"isLike":true,"dirayIamge":[]}]
                 */

                private int diaryRootId;
                private String diaryRootTitle;
                private int diaryRootUserId;
                private int diaryRootNumberOfViews;
                private boolean isConcerned;
                private String avatar;
                private String userName;
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

                public int getDiaryRootNumberOfViews() {
                    return diaryRootNumberOfViews;
                }

                public void setDiaryRootNumberOfViews(int diaryRootNumberOfViews) {
                    this.diaryRootNumberOfViews = diaryRootNumberOfViews;
                }

                public boolean isConcerned() {
                    return isConcerned;
                }

                public void setConcerned(boolean concerned) {
                    isConcerned = concerned;
                }

                public boolean isIsConcerned() {
                    return isConcerned;
                }

                public void setIsConcerned(boolean isConcerned) {
                    this.isConcerned = isConcerned;
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

                public List<DiarysBean> getDiarys() {
                    return diarys;
                }

                public void setDiarys(List<DiarysBean> diarys) {
                    this.diarys = diarys;
                }

                public static class DiarysBean {
                    /**
                     * diaryId : 28
                     * diaryDynamic : 天天 不想 上班
                     * diaryAddressTemperatureWeather : 洛阳-晴天-32
                     * dirayCreateTime : 2018-12-22 15:24:30
                     * dirayToClickTheNumberOfLikes : null
                     * dirayNumberOfComments : null
                     * diaryDay : 3
                     * diaryRootId : 16
                     * isLike : true
                     * dirayIamge : []
                     */

                    private int diaryId;
                    private String diaryDynamic;
                    private String diaryAddressTemperatureWeather;
                    private String dirayCreateTime;
                    private Object dirayToClickTheNumberOfLikes;
                    private Object dirayNumberOfComments;
                    private int diaryDay;
                    private int diaryRootId;
                    private boolean isLike;
                    private List<String> dirayIamge;

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

                    public boolean isLike() {
                        return isLike;
                    }

                    public void setLike(boolean like) {
                        isLike = like;
                    }

                    public List<String> getDirayIamge() {
                        return dirayIamge;
                    }

                    public void setDirayIamge(List<String> dirayIamge) {
                        this.dirayIamge = dirayIamge;
                    }
                }
            }
        }
    }
}
