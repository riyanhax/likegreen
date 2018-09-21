package com.xbdl.xinushop.bean;

import java.util.List;

/**
 * 日记 列表 bean
 */
public class NoteListBean {
    /**
     * usericon : http://img1.imgtn.bdimg.com/it/u=4097697694,3797357635&fm=11&gp=0.jpg
     * username : 马云
     * topic : 植物不够广等
     * viewcount : 1
     * location : 湖北.武汉
     * temp : 20℃
     * weather : Rain
     * attention : 1
     * images : [{"image":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1537444321960&di=a3a657c959ff58ce28f7c3bdd23c7e68&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fc75c10385343fbf2618015f7ba7eca8064388fb2.jpg","imagecreatetime":"2017.12.24","imagetitle":"6.Day"},{"image":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1537444321960&di=b2361eb0f9b9578f452f75075d9c7fa0&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F7acb0a46f21fbe09334115c061600c338644adc3.jpg","imagecreatetime":"2017.11.24","imagetitle":"5.Day"},{"image":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1537444321959&di=b345a40185e9d9e33f41ebe532dd8c58&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fbf096b63f6246b60553a62a0e1f81a4c510fa22a.jpg","imagecreatetime":"2017.12.24","imagetitle":"4.Day"},{"image":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1537444321959&di=6c7dadb190fb4e8b0584d8cbdae08c4e&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fe1fe9925bc315c602050233b87b1cb1348547718.jpg","imagecreatetime":"2017.12.24","imagetitle":"3.Day"},{"image":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1537444321959&di=e9eb4144d0ff619405db7c67fcdc9413&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F8b82b9014a90f6037cb445933312b31bb151edda.jpg","imagecreatetime":"2018.9.21","imagetitle":"2.Day"},{"image":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1537444321959&di=b460f392c13c19a8ec2dd3237d55e622&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Ffd039245d688d43fc7682126771ed21b0ff43b24.jpg","imagecreatetime":"2017.12.24","imagetitle":"1.Day"}]
     */

    private String usericon;
    private String username;
    private String topic;
    private int viewcount;
    private String location;
    private String temp;
    private String weather;
    private int attention;
    private List<Images> images;

    public String getUsericon() {
        return usericon;
    }

    public void setUsericon(String usericon) {
        this.usericon = usericon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getViewcount() {
        return viewcount;
    }

    public void setViewcount(int viewcount) {
        this.viewcount = viewcount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public int getAttention() {
        return attention;
    }

    public void setAttention(int attention) {
        this.attention = attention;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }


}
