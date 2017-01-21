package com.yuyu.utaitebox.rest;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Search {

    @SerializedName("music")
    private ArrayList<SearchMusic> music;

    @SerializedName("artist")
    private ArrayList<SearchArtist> artist;

    @SerializedName("tag")
    private ArrayList<SearchTag> tag;

}
