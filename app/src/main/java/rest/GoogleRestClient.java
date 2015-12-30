package rest;

import com.squareup.okhttp.OkHttpClient;

import entities.GoogleAccessToken;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Created by iTomer on 30/12/2015.
 * Licence GPLv3
 * Copyright (C) 2015  iTomer
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
public class GoogleRestClient {
    private static final String BASE_URL = "https://accounts.google.com";
    private static OkHttpClient httpClient = new OkHttpClient();
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.baseUrl(BASE_URL).client(httpClient).build();
        return retrofit.create(serviceClass);
    }

    /**
     * client_id=" +
     MainActivity.CLIENT_ID + "&client_secret=" +
     MainActivity.CLIENT_SECRET + "&redirect_uri=" + MainActivity.REDIRECT_URI +
     "&code=" + code + "&grant_type
     */
    public interface GoogleRESTAPI {
        @FormUrlEncoded
        @Headers({ "Content-Type: application/x-www-form-urlencoded;charset=UTF-8"})
        @POST("/o/oauth2/token")
        Call<GoogleAccessToken> getAccessToken(
                @Field("client_id") String clientID,
                @Field("client_secret") String clientSecret,
                @Field("redirect_uri") String redirectURI,
                @Field("code") String code,
                @Field("grant_type") String grantType);
    }
    /*
            params.add(new BasicNameValuePair("code", token));
            params.add(new BasicNameValuePair("client_id", client_id));
            params.add(new BasicNameValuePair("client_secret", client_secret));
            params.add(new BasicNameValuePair("redirect_uri", redirect_uri));
            params.add(new BasicNameValuePair("grant_type", grant_type));

            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
    */

}