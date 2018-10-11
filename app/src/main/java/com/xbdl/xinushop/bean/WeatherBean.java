package com.xbdl.xinushop.bean;

import java.util.List;

public class WeatherBean {

    /**
     * code : 10000
     * charge : false
     * msg : 查询成功
     * result : {"HeWeather5":[{"aqi":{"city":{"aqi":"30","qlty":"优","pm25":"20","pm10":"30","no2":"21","so2":"7","co":"0.8","o3":"92"}},"basic":{"city":"广州","cnty":"中国","id":"CN101280101","lat":"23.12517738","lon":"113.28063965","update":{"loc":"2018-10-11 13:45","utc":"2018-10-11 05:45"}},"daily_forecast":[{"astro":{"mr":"08:09","ms":"19:52","sr":"06:22","ss":"18:03"},"cond":{"code_d":"104","code_n":"104","txt_d":"阴","txt_n":"阴"},"date":"2018-10-11","hum":"70","pcpn":"4.0","pop":"71","pres":"1019","tmp":{"max":"24","min":"18"},"uv":"1","vis":"18","wind":{"deg":"356","dir":"北风","sc":"3-4","spd":"15"}},{"astro":{"mr":"09:06","ms":"20:34","sr":"06:22","ss":"18:02"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2018-10-12","hum":"49","pcpn":"0.0","pop":"8","pres":"1020","tmp":{"max":"26","min":"20"},"uv":"3","vis":"20","wind":{"deg":"-1","dir":"无持续风向","sc":"1-2","spd":"10"}},{"astro":{"mr":"10:02","ms":"21:19","sr":"06:23","ss":"18:02"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2018-10-13","hum":"58","pcpn":"0.0","pop":"24","pres":"1019","tmp":{"max":"28","min":"21"},"uv":"4","vis":"19","wind":{"deg":"-1","dir":"无持续风向","sc":"1-2","spd":"3"}},{"astro":{"mr":"10:57","ms":"22:05","sr":"06:23","ss":"18:01"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2018-10-14","hum":"62","pcpn":"0.0","pop":"25","pres":"1017","tmp":{"max":"29","min":"22"},"uv":"4","vis":"20","wind":{"deg":"-1","dir":"无持续风向","sc":"1-2","spd":"11"}},{"astro":{"mr":"11:49","ms":"22:54","sr":"06:24","ss":"18:00"},"cond":{"code_d":"101","code_n":"306","txt_d":"多云","txt_n":"中雨"},"date":"2018-10-15","hum":"71","pcpn":"1.0","pop":"55","pres":"1015","tmp":{"max":"28","min":"20"},"uv":"4","vis":"16","wind":{"deg":"-1","dir":"无持续风向","sc":"1-2","spd":"9"}},{"astro":{"mr":"12:38","ms":"23:43","sr":"06:24","ss":"17:59"},"cond":{"code_d":"306","code_n":"300","txt_d":"中雨","txt_n":"阵雨"},"date":"2018-10-16","hum":"83","pcpn":"1.0","pop":"55","pres":"1015","tmp":{"max":"25","min":"19"},"uv":"2","vis":"10","wind":{"deg":"10","dir":"北风","sc":"3-4","spd":"15"}},{"astro":{"mr":"13:24","ms":"00:00","sr":"06:25","ss":"17:58"},"cond":{"code_d":"101","code_n":"104","txt_d":"多云","txt_n":"阴"},"date":"2018-10-17","hum":"78","pcpn":"0.0","pop":"18","pres":"1015","tmp":{"max":"27","min":"19"},"uv":"7","vis":"15","wind":{"deg":"-1","dir":"无持续风向","sc":"1-2","spd":"9"}}],"hourly_forecast":[{"cond":{"code":"104","txt":"阴"},"date":"2018-10-11 16:00","hum":"72","pop":"7","pres":"1017","tmp":"23","wind":{"deg":"9","dir":"北风","sc":"1-2","spd":"9"}},{"cond":{"code":"104","txt":"阴"},"date":"2018-10-11 19:00","hum":"79","pop":"43","pres":"1018","tmp":"22","wind":{"deg":"2","dir":"北风","sc":"1-2","spd":"6"}},{"cond":{"code":"104","txt":"阴"},"date":"2018-10-11 22:00","hum":"84","pop":"55","pres":"1021","tmp":"21","wind":{"deg":"54","dir":"东北风","sc":"4-5","spd":"30"}},{"cond":{"code":"104","txt":"阴"},"date":"2018-10-12 01:00","hum":"84","pop":"20","pres":"1020","tmp":"19","wind":{"deg":"44","dir":"东北风","sc":"3-4","spd":"17"}},{"cond":{"code":"104","txt":"阴"},"date":"2018-10-12 04:00","hum":"82","pop":"20","pres":"1020","tmp":"18","wind":{"deg":"64","dir":"东北风","sc":"3-4","spd":"19"}},{"cond":{"code":"104","txt":"阴"},"date":"2018-10-12 07:00","hum":"81","pop":"7","pres":"1021","tmp":"19","wind":{"deg":"65","dir":"东北风","sc":"3-4","spd":"20"}},{"cond":{"code":"104","txt":"阴"},"date":"2018-10-12 10:00","hum":"69","pop":"7","pres":"1021","tmp":"22","wind":{"deg":"75","dir":"东北风","sc":"1-2","spd":"7"}},{"cond":{"code":"101","txt":"多云"},"date":"2018-10-12 13:00","hum":"68","pop":"5","pres":"1019","tmp":"25","wind":{"deg":"24","dir":"东北风","sc":"1-2","spd":"5"}}],"now":{"cond":{"code":"104","txt":"阴"},"fl":"23","hum":"62","pcpn":"0.0","pres":"1019","tmp":"22","vis":"10","wind":{"deg":"181","dir":"南风","sc":"1","spd":"4"}},"status":"ok","suggestion":{"air":{"brf":"良","txt":"气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"},"comf":{"brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"},"cw":{"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},"drsg":{"brf":"舒适","txt":"建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。"},"flu":{"brf":"少发","txt":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。"},"sport":{"brf":"较适宜","txt":"阴天，较适宜进行各种户内外运动。"},"trav":{"brf":"适宜","txt":"天气较好，风稍大，但温度适宜，总体来说还是好天气。这样的天气适宜旅游，您可以尽情享受大自然的风光。"},"uv":{"brf":"最弱","txt":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"}}}]}
     */

    private String code;
    private boolean charge;
    private String msg;
    private ResultBean result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isCharge() {
        return charge;
    }

    public void setCharge(boolean charge) {
        this.charge = charge;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<HeWeather5Bean> HeWeather5;

        public List<HeWeather5Bean> getHeWeather5() {
            return HeWeather5;
        }

        public void setHeWeather5(List<HeWeather5Bean> HeWeather5) {
            this.HeWeather5 = HeWeather5;
        }

        public static class HeWeather5Bean {
            /**
             * aqi : {"city":{"aqi":"30","qlty":"优","pm25":"20","pm10":"30","no2":"21","so2":"7","co":"0.8","o3":"92"}}
             * basic : {"city":"广州","cnty":"中国","id":"CN101280101","lat":"23.12517738","lon":"113.28063965","update":{"loc":"2018-10-11 13:45","utc":"2018-10-11 05:45"}}
             * daily_forecast : [{"astro":{"mr":"08:09","ms":"19:52","sr":"06:22","ss":"18:03"},"cond":{"code_d":"104","code_n":"104","txt_d":"阴","txt_n":"阴"},"date":"2018-10-11","hum":"70","pcpn":"4.0","pop":"71","pres":"1019","tmp":{"max":"24","min":"18"},"uv":"1","vis":"18","wind":{"deg":"356","dir":"北风","sc":"3-4","spd":"15"}},{"astro":{"mr":"09:06","ms":"20:34","sr":"06:22","ss":"18:02"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2018-10-12","hum":"49","pcpn":"0.0","pop":"8","pres":"1020","tmp":{"max":"26","min":"20"},"uv":"3","vis":"20","wind":{"deg":"-1","dir":"无持续风向","sc":"1-2","spd":"10"}},{"astro":{"mr":"10:02","ms":"21:19","sr":"06:23","ss":"18:02"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2018-10-13","hum":"58","pcpn":"0.0","pop":"24","pres":"1019","tmp":{"max":"28","min":"21"},"uv":"4","vis":"19","wind":{"deg":"-1","dir":"无持续风向","sc":"1-2","spd":"3"}},{"astro":{"mr":"10:57","ms":"22:05","sr":"06:23","ss":"18:01"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2018-10-14","hum":"62","pcpn":"0.0","pop":"25","pres":"1017","tmp":{"max":"29","min":"22"},"uv":"4","vis":"20","wind":{"deg":"-1","dir":"无持续风向","sc":"1-2","spd":"11"}},{"astro":{"mr":"11:49","ms":"22:54","sr":"06:24","ss":"18:00"},"cond":{"code_d":"101","code_n":"306","txt_d":"多云","txt_n":"中雨"},"date":"2018-10-15","hum":"71","pcpn":"1.0","pop":"55","pres":"1015","tmp":{"max":"28","min":"20"},"uv":"4","vis":"16","wind":{"deg":"-1","dir":"无持续风向","sc":"1-2","spd":"9"}},{"astro":{"mr":"12:38","ms":"23:43","sr":"06:24","ss":"17:59"},"cond":{"code_d":"306","code_n":"300","txt_d":"中雨","txt_n":"阵雨"},"date":"2018-10-16","hum":"83","pcpn":"1.0","pop":"55","pres":"1015","tmp":{"max":"25","min":"19"},"uv":"2","vis":"10","wind":{"deg":"10","dir":"北风","sc":"3-4","spd":"15"}},{"astro":{"mr":"13:24","ms":"00:00","sr":"06:25","ss":"17:58"},"cond":{"code_d":"101","code_n":"104","txt_d":"多云","txt_n":"阴"},"date":"2018-10-17","hum":"78","pcpn":"0.0","pop":"18","pres":"1015","tmp":{"max":"27","min":"19"},"uv":"7","vis":"15","wind":{"deg":"-1","dir":"无持续风向","sc":"1-2","spd":"9"}}]
             * hourly_forecast : [{"cond":{"code":"104","txt":"阴"},"date":"2018-10-11 16:00","hum":"72","pop":"7","pres":"1017","tmp":"23","wind":{"deg":"9","dir":"北风","sc":"1-2","spd":"9"}},{"cond":{"code":"104","txt":"阴"},"date":"2018-10-11 19:00","hum":"79","pop":"43","pres":"1018","tmp":"22","wind":{"deg":"2","dir":"北风","sc":"1-2","spd":"6"}},{"cond":{"code":"104","txt":"阴"},"date":"2018-10-11 22:00","hum":"84","pop":"55","pres":"1021","tmp":"21","wind":{"deg":"54","dir":"东北风","sc":"4-5","spd":"30"}},{"cond":{"code":"104","txt":"阴"},"date":"2018-10-12 01:00","hum":"84","pop":"20","pres":"1020","tmp":"19","wind":{"deg":"44","dir":"东北风","sc":"3-4","spd":"17"}},{"cond":{"code":"104","txt":"阴"},"date":"2018-10-12 04:00","hum":"82","pop":"20","pres":"1020","tmp":"18","wind":{"deg":"64","dir":"东北风","sc":"3-4","spd":"19"}},{"cond":{"code":"104","txt":"阴"},"date":"2018-10-12 07:00","hum":"81","pop":"7","pres":"1021","tmp":"19","wind":{"deg":"65","dir":"东北风","sc":"3-4","spd":"20"}},{"cond":{"code":"104","txt":"阴"},"date":"2018-10-12 10:00","hum":"69","pop":"7","pres":"1021","tmp":"22","wind":{"deg":"75","dir":"东北风","sc":"1-2","spd":"7"}},{"cond":{"code":"101","txt":"多云"},"date":"2018-10-12 13:00","hum":"68","pop":"5","pres":"1019","tmp":"25","wind":{"deg":"24","dir":"东北风","sc":"1-2","spd":"5"}}]
             * now : {"cond":{"code":"104","txt":"阴"},"fl":"23","hum":"62","pcpn":"0.0","pres":"1019","tmp":"22","vis":"10","wind":{"deg":"181","dir":"南风","sc":"1","spd":"4"}}
             * status : ok
             * suggestion : {"air":{"brf":"良","txt":"气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"},"comf":{"brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"},"cw":{"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},"drsg":{"brf":"舒适","txt":"建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。"},"flu":{"brf":"少发","txt":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。"},"sport":{"brf":"较适宜","txt":"阴天，较适宜进行各种户内外运动。"},"trav":{"brf":"适宜","txt":"天气较好，风稍大，但温度适宜，总体来说还是好天气。这样的天气适宜旅游，您可以尽情享受大自然的风光。"},"uv":{"brf":"最弱","txt":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"}}
             */

            private AqiBean aqi;
            private BasicBean basic;
            private NowBean now;
            private String status;
            private SuggestionBean suggestion;
            private List<DailyForecastBean> daily_forecast;
            private List<HourlyForecastBean> hourly_forecast;

            public AqiBean getAqi() {
                return aqi;
            }

            public void setAqi(AqiBean aqi) {
                this.aqi = aqi;
            }

            public BasicBean getBasic() {
                return basic;
            }

            public void setBasic(BasicBean basic) {
                this.basic = basic;
            }

            public NowBean getNow() {
                return now;
            }

            public void setNow(NowBean now) {
                this.now = now;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public SuggestionBean getSuggestion() {
                return suggestion;
            }

            public void setSuggestion(SuggestionBean suggestion) {
                this.suggestion = suggestion;
            }

            public List<DailyForecastBean> getDaily_forecast() {
                return daily_forecast;
            }

            public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
                this.daily_forecast = daily_forecast;
            }

            public List<HourlyForecastBean> getHourly_forecast() {
                return hourly_forecast;
            }

            public void setHourly_forecast(List<HourlyForecastBean> hourly_forecast) {
                this.hourly_forecast = hourly_forecast;
            }

            public static class AqiBean {
                /**
                 * city : {"aqi":"30","qlty":"优","pm25":"20","pm10":"30","no2":"21","so2":"7","co":"0.8","o3":"92"}
                 */

                private CityBean city;

                public CityBean getCity() {
                    return city;
                }

                public void setCity(CityBean city) {
                    this.city = city;
                }

                public static class CityBean {
                    /**
                     * aqi : 30
                     * qlty : 优
                     * pm25 : 20
                     * pm10 : 30
                     * no2 : 21
                     * so2 : 7
                     * co : 0.8
                     * o3 : 92
                     */

                    private String aqi;
                    private String qlty;
                    private String pm25;
                    private String pm10;
                    private String no2;
                    private String so2;
                    private String co;
                    private String o3;

                    public String getAqi() {
                        return aqi;
                    }

                    public void setAqi(String aqi) {
                        this.aqi = aqi;
                    }

                    public String getQlty() {
                        return qlty;
                    }

                    public void setQlty(String qlty) {
                        this.qlty = qlty;
                    }

                    public String getPm25() {
                        return pm25;
                    }

                    public void setPm25(String pm25) {
                        this.pm25 = pm25;
                    }

                    public String getPm10() {
                        return pm10;
                    }

                    public void setPm10(String pm10) {
                        this.pm10 = pm10;
                    }

                    public String getNo2() {
                        return no2;
                    }

                    public void setNo2(String no2) {
                        this.no2 = no2;
                    }

                    public String getSo2() {
                        return so2;
                    }

                    public void setSo2(String so2) {
                        this.so2 = so2;
                    }

                    public String getCo() {
                        return co;
                    }

                    public void setCo(String co) {
                        this.co = co;
                    }

                    public String getO3() {
                        return o3;
                    }

                    public void setO3(String o3) {
                        this.o3 = o3;
                    }
                }
            }

            public static class BasicBean {
                /**
                 * city : 广州
                 * cnty : 中国
                 * id : CN101280101
                 * lat : 23.12517738
                 * lon : 113.28063965
                 * update : {"loc":"2018-10-11 13:45","utc":"2018-10-11 05:45"}
                 */

                private String city;
                private String cnty;
                private String id;
                private String lat;
                private String lon;
                private UpdateBean update;

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public String getCnty() {
                    return cnty;
                }

                public void setCnty(String cnty) {
                    this.cnty = cnty;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getLat() {
                    return lat;
                }

                public void setLat(String lat) {
                    this.lat = lat;
                }

                public String getLon() {
                    return lon;
                }

                public void setLon(String lon) {
                    this.lon = lon;
                }

                public UpdateBean getUpdate() {
                    return update;
                }

                public void setUpdate(UpdateBean update) {
                    this.update = update;
                }

                public static class UpdateBean {
                    /**
                     * loc : 2018-10-11 13:45
                     * utc : 2018-10-11 05:45
                     */

                    private String loc;
                    private String utc;

                    public String getLoc() {
                        return loc;
                    }

                    public void setLoc(String loc) {
                        this.loc = loc;
                    }

                    public String getUtc() {
                        return utc;
                    }

                    public void setUtc(String utc) {
                        this.utc = utc;
                    }
                }
            }

            public static class NowBean {
                /**
                 * cond : {"code":"104","txt":"阴"}
                 * fl : 23
                 * hum : 62
                 * pcpn : 0.0
                 * pres : 1019
                 * tmp : 22
                 * vis : 10
                 * wind : {"deg":"181","dir":"南风","sc":"1","spd":"4"}
                 */

                private CondBean cond;
                private String fl;
                private String hum;
                private String pcpn;
                private String pres;
                private String tmp;
                private String vis;
                private WindBean wind;

                public CondBean getCond() {
                    return cond;
                }

                public void setCond(CondBean cond) {
                    this.cond = cond;
                }

                public String getFl() {
                    return fl;
                }

                public void setFl(String fl) {
                    this.fl = fl;
                }

                public String getHum() {
                    return hum;
                }

                public void setHum(String hum) {
                    this.hum = hum;
                }

                public String getPcpn() {
                    return pcpn;
                }

                public void setPcpn(String pcpn) {
                    this.pcpn = pcpn;
                }

                public String getPres() {
                    return pres;
                }

                public void setPres(String pres) {
                    this.pres = pres;
                }

                public String getTmp() {
                    return tmp;
                }

                public void setTmp(String tmp) {
                    this.tmp = tmp;
                }

                public String getVis() {
                    return vis;
                }

                public void setVis(String vis) {
                    this.vis = vis;
                }

                public WindBean getWind() {
                    return wind;
                }

                public void setWind(WindBean wind) {
                    this.wind = wind;
                }

                public static class CondBean {
                    /**
                     * code : 104
                     * txt : 阴
                     */

                    private String code;
                    private String txt;

                    public String getCode() {
                        return code;
                    }

                    public void setCode(String code) {
                        this.code = code;
                    }

                    public String getTxt() {
                        return txt;
                    }

                    public void setTxt(String txt) {
                        this.txt = txt;
                    }
                }

                public static class WindBean {
                    /**
                     * deg : 181
                     * dir : 南风
                     * sc : 1
                     * spd : 4
                     */

                    private String deg;
                    private String dir;
                    private String sc;
                    private String spd;

                    public String getDeg() {
                        return deg;
                    }

                    public void setDeg(String deg) {
                        this.deg = deg;
                    }

                    public String getDir() {
                        return dir;
                    }

                    public void setDir(String dir) {
                        this.dir = dir;
                    }

                    public String getSc() {
                        return sc;
                    }

                    public void setSc(String sc) {
                        this.sc = sc;
                    }

                    public String getSpd() {
                        return spd;
                    }

                    public void setSpd(String spd) {
                        this.spd = spd;
                    }
                }
            }

            public static class SuggestionBean {
                /**
                 * air : {"brf":"良","txt":"气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"}
                 * comf : {"brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"}
                 * cw : {"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"}
                 * drsg : {"brf":"舒适","txt":"建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。"}
                 * flu : {"brf":"少发","txt":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。"}
                 * sport : {"brf":"较适宜","txt":"阴天，较适宜进行各种户内外运动。"}
                 * trav : {"brf":"适宜","txt":"天气较好，风稍大，但温度适宜，总体来说还是好天气。这样的天气适宜旅游，您可以尽情享受大自然的风光。"}
                 * uv : {"brf":"最弱","txt":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"}
                 */

                private AirBean air;
                private ComfBean comf;
                private CwBean cw;
                private DrsgBean drsg;
                private FluBean flu;
                private SportBean sport;
                private TravBean trav;
                private UvBean uv;

                public AirBean getAir() {
                    return air;
                }

                public void setAir(AirBean air) {
                    this.air = air;
                }

                public ComfBean getComf() {
                    return comf;
                }

                public void setComf(ComfBean comf) {
                    this.comf = comf;
                }

                public CwBean getCw() {
                    return cw;
                }

                public void setCw(CwBean cw) {
                    this.cw = cw;
                }

                public DrsgBean getDrsg() {
                    return drsg;
                }

                public void setDrsg(DrsgBean drsg) {
                    this.drsg = drsg;
                }

                public FluBean getFlu() {
                    return flu;
                }

                public void setFlu(FluBean flu) {
                    this.flu = flu;
                }

                public SportBean getSport() {
                    return sport;
                }

                public void setSport(SportBean sport) {
                    this.sport = sport;
                }

                public TravBean getTrav() {
                    return trav;
                }

                public void setTrav(TravBean trav) {
                    this.trav = trav;
                }

                public UvBean getUv() {
                    return uv;
                }

                public void setUv(UvBean uv) {
                    this.uv = uv;
                }

                public static class AirBean {
                    /**
                     * brf : 良
                     * txt : 气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。
                     */

                    private String brf;
                    private String txt;

                    public String getBrf() {
                        return brf;
                    }

                    public void setBrf(String brf) {
                        this.brf = brf;
                    }

                    public String getTxt() {
                        return txt;
                    }

                    public void setTxt(String txt) {
                        this.txt = txt;
                    }
                }

                public static class ComfBean {
                    /**
                     * brf : 舒适
                     * txt : 白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。
                     */

                    private String brf;
                    private String txt;

                    public String getBrf() {
                        return brf;
                    }

                    public void setBrf(String brf) {
                        this.brf = brf;
                    }

                    public String getTxt() {
                        return txt;
                    }

                    public void setTxt(String txt) {
                        this.txt = txt;
                    }
                }

                public static class CwBean {
                    /**
                     * brf : 较适宜
                     * txt : 较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。
                     */

                    private String brf;
                    private String txt;

                    public String getBrf() {
                        return brf;
                    }

                    public void setBrf(String brf) {
                        this.brf = brf;
                    }

                    public String getTxt() {
                        return txt;
                    }

                    public void setTxt(String txt) {
                        this.txt = txt;
                    }
                }

                public static class DrsgBean {
                    /**
                     * brf : 舒适
                     * txt : 建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。
                     */

                    private String brf;
                    private String txt;

                    public String getBrf() {
                        return brf;
                    }

                    public void setBrf(String brf) {
                        this.brf = brf;
                    }

                    public String getTxt() {
                        return txt;
                    }

                    public void setTxt(String txt) {
                        this.txt = txt;
                    }
                }

                public static class FluBean {
                    /**
                     * brf : 少发
                     * txt : 各项气象条件适宜，无明显降温过程，发生感冒机率较低。
                     */

                    private String brf;
                    private String txt;

                    public String getBrf() {
                        return brf;
                    }

                    public void setBrf(String brf) {
                        this.brf = brf;
                    }

                    public String getTxt() {
                        return txt;
                    }

                    public void setTxt(String txt) {
                        this.txt = txt;
                    }
                }

                public static class SportBean {
                    /**
                     * brf : 较适宜
                     * txt : 阴天，较适宜进行各种户内外运动。
                     */

                    private String brf;
                    private String txt;

                    public String getBrf() {
                        return brf;
                    }

                    public void setBrf(String brf) {
                        this.brf = brf;
                    }

                    public String getTxt() {
                        return txt;
                    }

                    public void setTxt(String txt) {
                        this.txt = txt;
                    }
                }

                public static class TravBean {
                    /**
                     * brf : 适宜
                     * txt : 天气较好，风稍大，但温度适宜，总体来说还是好天气。这样的天气适宜旅游，您可以尽情享受大自然的风光。
                     */

                    private String brf;
                    private String txt;

                    public String getBrf() {
                        return brf;
                    }

                    public void setBrf(String brf) {
                        this.brf = brf;
                    }

                    public String getTxt() {
                        return txt;
                    }

                    public void setTxt(String txt) {
                        this.txt = txt;
                    }
                }

                public static class UvBean {
                    /**
                     * brf : 最弱
                     * txt : 属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。
                     */

                    private String brf;
                    private String txt;

                    public String getBrf() {
                        return brf;
                    }

                    public void setBrf(String brf) {
                        this.brf = brf;
                    }

                    public String getTxt() {
                        return txt;
                    }

                    public void setTxt(String txt) {
                        this.txt = txt;
                    }
                }
            }

            public static class DailyForecastBean {
                /**
                 * astro : {"mr":"08:09","ms":"19:52","sr":"06:22","ss":"18:03"}
                 * cond : {"code_d":"104","code_n":"104","txt_d":"阴","txt_n":"阴"}
                 * date : 2018-10-11
                 * hum : 70
                 * pcpn : 4.0
                 * pop : 71
                 * pres : 1019
                 * tmp : {"max":"24","min":"18"}
                 * uv : 1
                 * vis : 18
                 * wind : {"deg":"356","dir":"北风","sc":"3-4","spd":"15"}
                 */

                private AstroBean astro;
                private CondBeanX cond;
                private String date;
                private String hum;
                private String pcpn;
                private String pop;
                private String pres;
                private TmpBean tmp;
                private String uv;
                private String vis;
                private WindBeanX wind;

                public AstroBean getAstro() {
                    return astro;
                }

                public void setAstro(AstroBean astro) {
                    this.astro = astro;
                }

                public CondBeanX getCond() {
                    return cond;
                }

                public void setCond(CondBeanX cond) {
                    this.cond = cond;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getHum() {
                    return hum;
                }

                public void setHum(String hum) {
                    this.hum = hum;
                }

                public String getPcpn() {
                    return pcpn;
                }

                public void setPcpn(String pcpn) {
                    this.pcpn = pcpn;
                }

                public String getPop() {
                    return pop;
                }

                public void setPop(String pop) {
                    this.pop = pop;
                }

                public String getPres() {
                    return pres;
                }

                public void setPres(String pres) {
                    this.pres = pres;
                }

                public TmpBean getTmp() {
                    return tmp;
                }

                public void setTmp(TmpBean tmp) {
                    this.tmp = tmp;
                }

                public String getUv() {
                    return uv;
                }

                public void setUv(String uv) {
                    this.uv = uv;
                }

                public String getVis() {
                    return vis;
                }

                public void setVis(String vis) {
                    this.vis = vis;
                }

                public WindBeanX getWind() {
                    return wind;
                }

                public void setWind(WindBeanX wind) {
                    this.wind = wind;
                }

                public static class AstroBean {
                    /**
                     * mr : 08:09
                     * ms : 19:52
                     * sr : 06:22
                     * ss : 18:03
                     */

                    private String mr;
                    private String ms;
                    private String sr;
                    private String ss;

                    public String getMr() {
                        return mr;
                    }

                    public void setMr(String mr) {
                        this.mr = mr;
                    }

                    public String getMs() {
                        return ms;
                    }

                    public void setMs(String ms) {
                        this.ms = ms;
                    }

                    public String getSr() {
                        return sr;
                    }

                    public void setSr(String sr) {
                        this.sr = sr;
                    }

                    public String getSs() {
                        return ss;
                    }

                    public void setSs(String ss) {
                        this.ss = ss;
                    }
                }

                public static class CondBeanX {
                    /**
                     * code_d : 104
                     * code_n : 104
                     * txt_d : 阴
                     * txt_n : 阴
                     */

                    private String code_d;
                    private String code_n;
                    private String txt_d;
                    private String txt_n;

                    public String getCode_d() {
                        return code_d;
                    }

                    public void setCode_d(String code_d) {
                        this.code_d = code_d;
                    }

                    public String getCode_n() {
                        return code_n;
                    }

                    public void setCode_n(String code_n) {
                        this.code_n = code_n;
                    }

                    public String getTxt_d() {
                        return txt_d;
                    }

                    public void setTxt_d(String txt_d) {
                        this.txt_d = txt_d;
                    }

                    public String getTxt_n() {
                        return txt_n;
                    }

                    public void setTxt_n(String txt_n) {
                        this.txt_n = txt_n;
                    }
                }

                public static class TmpBean {
                    /**
                     * max : 24
                     * min : 18
                     */

                    private String max;
                    private String min;

                    public String getMax() {
                        return max;
                    }

                    public void setMax(String max) {
                        this.max = max;
                    }

                    public String getMin() {
                        return min;
                    }

                    public void setMin(String min) {
                        this.min = min;
                    }
                }

                public static class WindBeanX {
                    /**
                     * deg : 356
                     * dir : 北风
                     * sc : 3-4
                     * spd : 15
                     */

                    private String deg;
                    private String dir;
                    private String sc;
                    private String spd;

                    public String getDeg() {
                        return deg;
                    }

                    public void setDeg(String deg) {
                        this.deg = deg;
                    }

                    public String getDir() {
                        return dir;
                    }

                    public void setDir(String dir) {
                        this.dir = dir;
                    }

                    public String getSc() {
                        return sc;
                    }

                    public void setSc(String sc) {
                        this.sc = sc;
                    }

                    public String getSpd() {
                        return spd;
                    }

                    public void setSpd(String spd) {
                        this.spd = spd;
                    }
                }
            }

            public static class HourlyForecastBean {
                /**
                 * cond : {"code":"104","txt":"阴"}
                 * date : 2018-10-11 16:00
                 * hum : 72
                 * pop : 7
                 * pres : 1017
                 * tmp : 23
                 * wind : {"deg":"9","dir":"北风","sc":"1-2","spd":"9"}
                 */

                private CondBeanXX cond;
                private String date;
                private String hum;
                private String pop;
                private String pres;
                private String tmp;
                private WindBeanXX wind;

                public CondBeanXX getCond() {
                    return cond;
                }

                public void setCond(CondBeanXX cond) {
                    this.cond = cond;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getHum() {
                    return hum;
                }

                public void setHum(String hum) {
                    this.hum = hum;
                }

                public String getPop() {
                    return pop;
                }

                public void setPop(String pop) {
                    this.pop = pop;
                }

                public String getPres() {
                    return pres;
                }

                public void setPres(String pres) {
                    this.pres = pres;
                }

                public String getTmp() {
                    return tmp;
                }

                public void setTmp(String tmp) {
                    this.tmp = tmp;
                }

                public WindBeanXX getWind() {
                    return wind;
                }

                public void setWind(WindBeanXX wind) {
                    this.wind = wind;
                }

                public static class CondBeanXX {
                    /**
                     * code : 104
                     * txt : 阴
                     */

                    private String code;
                    private String txt;

                    public String getCode() {
                        return code;
                    }

                    public void setCode(String code) {
                        this.code = code;
                    }

                    public String getTxt() {
                        return txt;
                    }

                    public void setTxt(String txt) {
                        this.txt = txt;
                    }
                }

                public static class WindBeanXX {
                    /**
                     * deg : 9
                     * dir : 北风
                     * sc : 1-2
                     * spd : 9
                     */

                    private String deg;
                    private String dir;
                    private String sc;
                    private String spd;

                    public String getDeg() {
                        return deg;
                    }

                    public void setDeg(String deg) {
                        this.deg = deg;
                    }

                    public String getDir() {
                        return dir;
                    }

                    public void setDir(String dir) {
                        this.dir = dir;
                    }

                    public String getSc() {
                        return sc;
                    }

                    public void setSc(String sc) {
                        this.sc = sc;
                    }

                    public String getSpd() {
                        return spd;
                    }

                    public void setSpd(String spd) {
                        this.spd = spd;
                    }
                }
            }
        }
    }
}
