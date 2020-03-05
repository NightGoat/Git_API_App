package ru.nightgoat.gitrepositoriesapp.data.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RepModel {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("owner")
    @Expose
    public Owner owner;
    @SerializedName("html_url")
    @Expose
    public String htmlUrl;
    @SerializedName("description")
    @Expose
    public String description;

    public class Owner {

        @SerializedName("login")
        @Expose
        public String login;
        @SerializedName("avatar_url")
        @Expose
        public String avatarUrl;
        @SerializedName("html_url")
        @Expose
        public String htmlUrl;
    }
}