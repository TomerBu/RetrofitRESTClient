package rest;

import java.util.List;

import entities.GitContributor;
import entities.GitUser;
import entities.UserResults;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public class RestClient {
    private static final String BASE_URL = "https://api.github.com";
    private static GitAPI singleton;

    public static GitAPI getClient() {
        if (singleton == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            singleton = retrofit.create(GitAPI.class);
        }
        return singleton;
    }

    public interface GitAPI {

        @GET("/search/users")
        Call<UserResults> getUsersNamed(@Query("q") String username);
        //ex: https://api.github.com/search/users?q=tomer

        //More Examples. No usage:
        //Concrete user not search./Path
        @GET("/users/{username}")
        Call<GitUser> getUser(@Path("username") String username);

        @GET("/group/{id}/users")
        Call<UserResults> groupList(@Path("id") int groupId, @Query("sort") String sort);

        @GET("/repos/{owner}/{repo}/contributors")
        Call<List<GitContributor>> contributors(
                @Path("owner") String owner,
                @Path("repo") String repo
        );

        //Example:
        //https://api.github.com/repos/TomerBu/RecyclerViewSwipeToRemoveAndMove/contributors
        //https://api.github.com/repos/iAndroidCollege/FragmentsDemo/contributors
        //
        @POST("/user/create")
        Call<GitUser> createUser(@Body String name, @Body String email);


        @Headers("User-Agent: RetroDemo")
        @POST("/users/new")
        Call<GitUser> createUser(@Body UserResults user);
    }
}
