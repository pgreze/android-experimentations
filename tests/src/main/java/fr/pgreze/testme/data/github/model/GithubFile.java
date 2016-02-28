package fr.pgreze.testme.data.github.model;

import com.google.gson.annotations.SerializedName;

public class GithubFile {

    @SerializedName("filename")
    public String filename;
    @SerializedName("type")
    public String type;
    @SerializedName("language")
    public String language;
    @SerializedName("raw_url")
    public String rawUrl;
    @SerializedName("size")
    public int size;
    /** Only for full gist */
    @SerializedName("content")
    public String content;
}
