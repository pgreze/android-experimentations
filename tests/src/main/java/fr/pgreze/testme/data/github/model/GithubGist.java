package fr.pgreze.testme.data.github.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class GithubGist {

    @SerializedName("id")
    public String id;
    @SerializedName("url")
    public String url;
    @SerializedName("forks_url")
    public String forksUrl;
    @SerializedName("commits_url")
    public String commitsUrl;
    @SerializedName("description")
    public String description;
    @SerializedName("public")
    public boolean isPublic;
    @SerializedName("owner")
    public GithubUser owner;
    @SerializedName("user")
    public GithubUser user;
    @SerializedName("files")
    public Map<String, GithubFile> files;
    @SerializedName("truncated")
    public boolean truncated;
    @SerializedName("comments")
    public int comments;
    @SerializedName("comments_url")
    public String commentsUrl;
    @SerializedName("html_url")
    public String htmlUrl;
    @SerializedName("git_pull_url")
    public String gitPullUrl;
    @SerializedName("git_push_url")
    public String gitPushUrl;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("updated_at")
    public String updatedAt;
}
