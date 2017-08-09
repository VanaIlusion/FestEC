package com.wind.latte.net;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by theWind on 2017/8/9.
 */

public interface RestService {

    @GET
    Call<String> get(@Url String url, @QueryMap Map<String, Object> params);

    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap Map<String, Object> params);

    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url, @FieldMap Map<String, Object> params);

    @DELETE
    Call<String> delete(@Url String url, @QueryMap Map<String, Object> params);

    /**
     * @param url
     * @param params
     * @return
     * @Streaming： 不加该注解时，下载时先加载进内存，完成后在写入文件，这样如果下载文件过大，会导致内存溢出，
     * 加上表示边下载边写入文件
     */
    @Streaming
    @GET
    Call<ResponseBody> downlaod(@Url String url, @QueryMap Map<String, Object> params);

    @Multipart
    @POST
    Call<String> upload(@Url String url, @Part MultipartBody.Part files);
}
