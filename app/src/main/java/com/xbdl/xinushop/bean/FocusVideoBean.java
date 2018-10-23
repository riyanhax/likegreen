package com.xbdl.xinushop.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FocusVideoBean {


    /**
     * code : 100
     * msg : 处理成功！
     * extend : {"page":{"pageNum":1,"pageSize":2,"size":2,"startRow":0,"endRow":1,"total":2,"pages":1,"list":[{"create_time":1539880658000,"recommend":null,"userName":"xl_28912798","type":1,"userId":24,"url":"http://jdvodrdjw0c4b.vod.126.net/jdvodrdjw0c4b/f56f405f-5b58-4e11-8ce9-b464cde8249c.mp4","update_time":1539880658000,"number_of_forwards":null,"music":null,"commodity_id":null,"user_id":24,"clickNum":null,"headPortrait":null,"headline":"体来","video_id":4,"status":0},{"create_time":1539881595000,"recommend":null,"userName":"xl_52926565","type":1,"userId":25,"url":"http://jdvodrdjw0c4b.vod.126.net/jdvodrdjw0c4b/8c760666-d99b-4665-ad43-5b8ad55547bd.mp4","update_time":1539881595000,"number_of_forwards":null,"music":null,"commodity_id":null,"user_id":25,"clickNum":null,"headPortrait":"20181018180311664.jpg","headline":"添加","max(v":{"create_time)":1539884068000},"video_id":5,"status":0}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":5,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}}
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
         * page : {"pageNum":1,"pageSize":2,"size":2,"startRow":0,"endRow":1,"total":2,"pages":1,"list":[{"create_time":1539880658000,"recommend":null,"userName":"xl_28912798","type":1,"userId":24,"url":"http://jdvodrdjw0c4b.vod.126.net/jdvodrdjw0c4b/f56f405f-5b58-4e11-8ce9-b464cde8249c.mp4","update_time":1539880658000,"number_of_forwards":null,"music":null,"commodity_id":null,"user_id":24,"clickNum":null,"headPortrait":null,"headline":"体来","video_id":4,"status":0,"max(v":{"create_time)":1539884068000}},{"create_time":1539881595000,"recommend":null,"userName":"xl_52926565","type":1,"userId":25,"url":"http://jdvodrdjw0c4b.vod.126.net/jdvodrdjw0c4b/8c760666-d99b-4665-ad43-5b8ad55547bd.mp4","update_time":1539881595000,"number_of_forwards":null,"music":null,"commodity_id":null,"user_id":25,"clickNum":null,"headPortrait":"20181018180311664.jpg","headline":"添加","max(v":{"create_time)":1539884068000},"video_id":5,"status":0}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":5,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
         */

        private PageBean page;

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public static class PageBean {
            /**
             * pageNum : 1
             * pageSize : 2
             * size : 2
             * startRow : 0
             * endRow : 1
             * total : 2
             * pages : 1
             * list : [{"create_time":1539880658000,"recommend":null,"userName":"xl_28912798","type":1,"userId":24,"url":"http://jdvodrdjw0c4b.vod.126.net/jdvodrdjw0c4b/f56f405f-5b58-4e11-8ce9-b464cde8249c.mp4","update_time":1539880658000,"number_of_forwards":null,"music":null,"commodity_id":null,"user_id":24,"clickNum":null,"headPortrait":null,"headline":"体来","video_id":4,"status":0},{"create_time":1539881595000,"recommend":null,"userName":"xl_52926565","type":1,"userId":25,"url":"http://jdvodrdjw0c4b.vod.126.net/jdvodrdjw0c4b/8c760666-d99b-4665-ad43-5b8ad55547bd.mp4","update_time":1539881595000,"number_of_forwards":null,"music":null,"commodity_id":null,"user_id":25,"clickNum":null,"headPortrait":"20181018180311664.jpg","headline":"添加","max(v":{"create_time)":1539884068000},"video_id":5,"status":0}]
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

            // FIXME generate failure  field _$MaxV191
            public static class ListBean {
                /**
                 * create_time : 1539880658000
                 * recommend : null
                 * userName : xl_28912798
                 * type : 1
                 * userId : 24
                 * url : http://jdvodrdjw0c4b.vod.126.net/jdvodrdjw0c4b/f56f405f-5b58-4e11-8ce9-b464cde8249c.mp4
                 * update_time : 1539880658000
                 * number_of_forwards : null
                 * music : null
                 * commodity_id : null
                 * user_id : 24
                 * clickNum : null
                 * headPortrait : null
                 * headline : 体来
                 * video_id : 4
                 * status : 0
                 * max(v : {"create_time)":1539884068000}
                 */

                private long create_time;
                private int recommend;
                private String userName;
                private int type;
                private int userId;
                private String url;
                private long update_time;
                private int number_of_forwards;  //转发数
                private String music;
                private int commodity_id;
                private int user_id;
                private int clickNum;
                private String headPortrait;
                private String headline;
                private int video_id;
                private int status;

                public long getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(long create_time) {
                    this.create_time = create_time;
                }

                public int getRecommend() {
                    return recommend;
                }

                public void setRecommend(int recommend) {
                    this.recommend = recommend;
                }

                public String getUserName() {
                    return userName;
                }

                public void setUserName(String userName) {
                    this.userName = userName;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public int getUserId() {
                    return userId;
                }

                public void setUserId(int userId) {
                    this.userId = userId;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public long getUpdate_time() {
                    return update_time;
                }

                public void setUpdate_time(long update_time) {
                    this.update_time = update_time;
                }

                public int getNumber_of_forwards() {
                    return number_of_forwards;
                }

                public void setNumber_of_forwards(int number_of_forwards) {
                    this.number_of_forwards = number_of_forwards;
                }

                public String getMusic() {
                    return music;
                }

                public void setMusic(String music) {
                    this.music = music;
                }

                public int getCommodity_id() {
                    return commodity_id;
                }

                public void setCommodity_id(int commodity_id) {
                    this.commodity_id = commodity_id;
                }

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
                }

                public int getClickNum() {
                    return clickNum;
                }

                public void setClickNum(int clickNum) {
                    this.clickNum = clickNum;
                }

                public String getHeadPortrait() {
                    return headPortrait;
                }

                public void setHeadPortrait(String headPortrait) {
                    this.headPortrait = headPortrait;
                }

                public String getHeadline() {
                    return headline;
                }

                public void setHeadline(String headline) {
                    this.headline = headline;
                }

                public int getVideo_id() {
                    return video_id;
                }

                public void setVideo_id(int video_id) {
                    this.video_id = video_id;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }
            }
        }
    }
}
