package fr.pgreze.testme.data.github;

import java.util.List;

import fr.pgreze.testme.data.github.model.GithubGist;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface GithubService {

    String BASE_URL = "https://api.github.com";

    // Gist https://developer.github.com/v3/gists/

    /**
     * See https://developer.github.com/v3/gists/#list-all-public-gists
     * @return all public gists sorted by most recently updated to least recently updated
     */
    @GET("/gists/public")
    Observable<List<GithubGist>> publicGists();

    /**
     * See https://developer.github.com/v3/gists/#get-a-single-gist
     * @param id gist id
     * @return a single gist
     */
    @GET("/gists/{id}")
    Observable<GithubGist> gist(@Path("id") String id);
}
