package com.xbdl.xinushop.bean;

import java.io.Serializable;
import java.util.List;

/*
* 收货地址
* */
public class AddressBean {

    /**
     * msg : 查询成功
     * code : 1
     * addressList : [{"consignee":"222","createdDate":"2018-09-28 10:20:12","province":"111","city":"222","user_name":"哦哦","district":"333","contactWay":"15626202636","contactAddress":"444","isDefaultAddress":0,"updatedDate":"2018-09-28 10:20:12","UserAddressID":3,"userId":11},{"consignee":"静鸡鸡","createdDate":"2018-09-28 10:36:18","province":"北京市","city":"北京市","user_name":"哦哦","district":"东城区","contactWay":"15626202636","contactAddress":"投简历","isDefaultAddress":0,"updatedDate":"2018-09-28 10:36:18","UserAddressID":4,"userId":11}]
     * count : 2
     * userId : 11
     */

    private String msg;
    private int code;
    private int count;
    private String userId;
    private List<AddressListBean> addressList;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<AddressListBean> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<AddressListBean> addressList) {
        this.addressList = addressList;
    }

    public static class AddressListBean implements Serializable {
        /**
         * consignee : 222
         * createdDate : 2018-09-28 10:20:12
         * province : 111
         * city : 222
         * user_name : 哦哦
         * district : 333
         * contactWay : 15626202636
         * contactAddress : 444
         * isDefaultAddress : 0
         * updatedDate : 2018-09-28 10:20:12
         * UserAddressID : 3
         * userId : 11
         */

        private String consignee;
        private String createdDate;
        private String province;
        private String city;
        private String user_name;
        private String district;
        private String contactWay;
        private String contactAddress;
        private int isDefaultAddress;
        private String updatedDate;
        private int UserAddressID;
        private int userId;

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getContactWay() {
            return contactWay;
        }

        public void setContactWay(String contactWay) {
            this.contactWay = contactWay;
        }

        public String getContactAddress() {
            return contactAddress;
        }

        public void setContactAddress(String contactAddress) {
            this.contactAddress = contactAddress;
        }

        public int getIsDefaultAddress() {
            return isDefaultAddress;
        }

        public void setIsDefaultAddress(int isDefaultAddress) {
            this.isDefaultAddress = isDefaultAddress;
        }

        public String getUpdatedDate() {
            return updatedDate;
        }

        public void setUpdatedDate(String updatedDate) {
            this.updatedDate = updatedDate;
        }

        public int getUserAddressID() {
            return UserAddressID;
        }

        public void setUserAddressID(int UserAddressID) {
            this.UserAddressID = UserAddressID;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        @Override
        public String toString() {
            return "AddressListBean{" +
                    "consignee='" + consignee + '\'' +
                    ", createdDate='" + createdDate + '\'' +
                    ", province='" + province + '\'' +
                    ", city='" + city + '\'' +
                    ", user_name='" + user_name + '\'' +
                    ", district='" + district + '\'' +
                    ", contactWay='" + contactWay + '\'' +
                    ", contactAddress='" + contactAddress + '\'' +
                    ", isDefaultAddress=" + isDefaultAddress +
                    ", updatedDate='" + updatedDate + '\'' +
                    ", UserAddressID=" + UserAddressID +
                    ", userId=" + userId +
                    '}';
        }
    }
}
