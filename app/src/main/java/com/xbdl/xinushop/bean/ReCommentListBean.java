package com.xbdl.xinushop.bean;

import java.util.List;

/**
 * 首页推荐 列表
 */
public class ReCommentListBean  {

    /**
     * returnComment : []
     * comment : {"comId":16,"content":"爱丽丝的房间卡萨电话费","time":1538971482000,"conType":1,"noteId":4,"userId":9,"type":null,"userName":"xl_16298763","userHeadPortrait":null}
     */

    private CommentBean comment;
    private List<?> returnComment;

    public CommentBean getComment() {
        return comment;
    }

    public void setComment(CommentBean comment) {
        this.comment = comment;
    }

    public List<?> getReturnComment() {
        return returnComment;
    }

    public void setReturnComment(List<?> returnComment) {
        this.returnComment = returnComment;
    }

    public static class CommentBean {
        /**
         * comId : 16
         * content : 爱丽丝的房间卡萨电话费
         * time : 1538971482000
         * conType : 1
         * noteId : 4
         * userId : 9
         * type : null
         * userName : xl_16298763
         * userHeadPortrait : null
         */

        private int comId;
        private String content;
        private long time;
        private int conType;
        private int noteId;
        private int userId;
        private Object type;
        private String userName;
        private Object userHeadPortrait;

        public int getComId() {
            return comId;
        }

        public void setComId(int comId) {
            this.comId = comId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getConType() {
            return conType;
        }

        public void setConType(int conType) {
            this.conType = conType;
        }

        public int getNoteId() {
            return noteId;
        }

        public void setNoteId(int noteId) {
            this.noteId = noteId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Object getUserHeadPortrait() {
            return userHeadPortrait;
        }

        public void setUserHeadPortrait(Object userHeadPortrait) {
            this.userHeadPortrait = userHeadPortrait;
        }
    }
}
