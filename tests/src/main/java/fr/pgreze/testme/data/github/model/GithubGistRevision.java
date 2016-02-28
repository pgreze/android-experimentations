package fr.pgreze.testme.data.github.model;

import com.google.gson.annotations.SerializedName;

public class GithubGistRevision {

    @SerializedName("url")
    public String url;
    @SerializedName("version")
    public String version;
    @SerializedName("user")
    public GithubUser user;
    @SerializedName("change_status")
    public GithubChangeStatus changeStatus;
    @SerializedName("committed_at")
    public String committedAt;

    public static class GithubChangeStatus {

        @SerializedName("deletions")
        public int deletions;
        @SerializedName("additions")
        public int additions;
        @SerializedName("total")
        public int total;
    }
}
