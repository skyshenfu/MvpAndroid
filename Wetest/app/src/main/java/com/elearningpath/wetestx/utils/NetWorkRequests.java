package com.elearningpath.wetestx.utils;

import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 创建者：韦小宝
 * 创建日期：16/10/27
 * 版本号：1.0.0
 * 功能说明：网络请求的接口用法和Spring Data类似由于本app中使用了
 * retrofit2和Rxjava所以返回形式如下
 */

public interface NetWorkRequests {


/*
    @Multipart
    @POST("/account/updateAvatar.json")
    public Observable<ApiResponse<UploadPicBean>> getUploadResult(@Part("file\"; filename=\"png") RequestBody file);

    //以下接口为好友血压,血糖做准备的接口
    @POST("/mobile/friend/getBPAndBS.json")
    public Observable<ApiResponse<FriendSugarAndPressBean>> getFriendSugarAndPressBean(@Query("uid") long uid);
*/

}
