/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yiche.baselibrary.javabean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ================================================
 * User 实体类
 * <p>
 * Created by JessYan on 04/09/2016 17:14
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class UserBean implements Parcelable {

    private String mobile;
    private String name;
    private String enterprise_certification_status;
    private String personal_auth_status;
    private String senior_status;
    private String members;
    private String ycs_user_id;
    private String im_alias;
    private String im_token;
    private UserInfoBean user_info;

    public String getSenior_status() {
        return senior_status;
    }

    public void setSenior_status(String senior_status) {
        this.senior_status = senior_status;
    }

    public UserInfoBean getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfoBean user_info) {
        this.user_info = user_info;
    }

    public static class UserInfoBean implements Parcelable {

        String head_url;
        String nick_name;

        public String getHead_url() {
            return head_url;
        }

        public void setHead_url(String head_url) {
            this.head_url = head_url;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.head_url);
            dest.writeString(this.nick_name);
        }

        public UserInfoBean() {
        }

        protected UserInfoBean(Parcel in) {
            this.head_url = in.readString();
            this.nick_name = in.readString();
        }

        public static final Creator<UserInfoBean> CREATOR = new Creator<UserInfoBean>() {
            @Override
            public UserInfoBean createFromParcel(Parcel source) {
                return new UserInfoBean(source);
            }

            @Override
            public UserInfoBean[] newArray(int size) {
                return new UserInfoBean[size];
            }
        };
    }

    public String getIm_alias() {
        return im_alias;
    }

    public void setIm_alias(String im_alias) {
        this.im_alias = im_alias;
    }

    public String getIm_token() {
        return im_token;
    }

    public void setIm_token(String im_token) {
        this.im_token = im_token;
    }

    public static Creator<UserBean> getCREATOR() {
        return CREATOR;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnterprise_certification_status() {
        return enterprise_certification_status;
    }

    public void setEnterprise_certification_status(String enterprise_certification_status) {
        this.enterprise_certification_status = enterprise_certification_status;
    }

    public String getPersonal_auth_status() {
        return personal_auth_status;
    }

    public void setPersonal_auth_status(String personal_auth_status) {
        this.personal_auth_status = personal_auth_status;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getYcs_user_id() {
        return ycs_user_id;
    }

    public void setYcs_user_id(String ycs_user_id) {
        this.ycs_user_id = ycs_user_id;
    }

    public UserBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mobile);
        dest.writeString(this.name);
        dest.writeString(this.enterprise_certification_status);
        dest.writeString(this.personal_auth_status);
        dest.writeString(this.senior_status);
        dest.writeString(this.members);
        dest.writeString(this.ycs_user_id);
        dest.writeString(this.im_alias);
        dest.writeString(this.im_token);
        dest.writeParcelable(this.user_info, flags);
    }

    protected UserBean(Parcel in) {
        this.mobile = in.readString();
        this.name = in.readString();
        this.enterprise_certification_status = in.readString();
        this.personal_auth_status = in.readString();
        this.senior_status = in.readString();
        this.members = in.readString();
        this.ycs_user_id = in.readString();
        this.im_alias = in.readString();
        this.im_token = in.readString();
        this.user_info = in.readParcelable(UserInfoBean.class.getClassLoader());
    }

    public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel source) {
            return new UserBean(source);
        }

        @Override
        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };
}
