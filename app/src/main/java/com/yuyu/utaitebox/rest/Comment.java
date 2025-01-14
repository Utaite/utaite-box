package com.yuyu.utaitebox.rest;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Comment {

    @SerializedName("id")
    private String id;

    @SerializedName("_aid")
    private String _aid;

    @SerializedName("_sid")
    private String _sid;

    @SerializedName("_mid")
    private String _mid;

    @SerializedName("type")
    private String type;

    @SerializedName("content")
    private String content;

    @SerializedName("ip")
    private String ip;

    @SerializedName("date")
    private String date;

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("description")
    private String description;

    @SerializedName("websites")
    private String websites;

    @SerializedName("lastfm")
    private String lastfm;

    @SerializedName("skype")
    private String skype;

    @SerializedName("twitter")
    private String twitter;

    @SerializedName("facebook")
    private String facebook;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("cover")
    private String cover;

    @SerializedName("lastedit")
    private String lastedit;

}
