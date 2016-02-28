package fr.pgreze.testme.data.github.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/** Ignored field(s): forks */
public class GithubFullGist extends GithubGist {

    @SerializedName("history")
    public List<GithubGistRevision> history;
}
