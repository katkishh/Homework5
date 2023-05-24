package com.example.homework5.data.remote

import com.example.homework5.data.model.ApiImage
import com.example.homework5.data.model.ApiPost
import com.example.homework5.data.model.ApiProfile
import com.example.homework5.data.model.PagedDataResponse
import com.example.homework5.data.model.remote.ResultResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface NanoPostApiService {
    @GET("/api/v1/images/{profileId}")
    suspend fun getProfileImages(
        @Path("profileId") profileId: String,
        @Query("count") count:Int? = 30,
        @Query("offset") offset:String?,
    ): PagedDataResponse<ApiImage>

    @PUT("/api/v1/image")
    @Multipart
    suspend fun uploadImage(
        @Part image: MultipartBody.Part,
    ): ApiImage

    @GET("/api/v1/image/{imageId}")
    suspend fun getImage(
        @Path("imageId") imageId:String,
    ): ApiImage

    @DELETE("/api/v1/image/{imageId}")
    suspend fun deleteImage(
        @Path("imageId") imageId: String,
    ): ResultResponse

    @GET("/api/v1/profile/{profileId}")
    suspend fun getProfile(
        @Path("profileId")profileId:String,
    ): ApiProfile

    @PATCH("/api/v1/profile/{profileId}")
    @Multipart
    suspend fun editProfile(
        @Path("profileId") profileId:String?,
        @Part("displayName") displayName:String?,
        @Part("bio") bio:String?,
        @Part avatar: MultipartBody.Part?,
    )

    @PUT("/api/v1/profile/{profileId}/subscribe")
    suspend fun subscribe(
        @Path("profileId")  profileID:String,
    ): ResultResponse

    @DELETE("/api/v1/profile/{profileId}/subscribe")
    suspend fun unsubscribe(
        @Path("profileId") profileId:String,
    ): ResultResponse

    @GET("/api/v1/feed")
    suspend fun getFeed(
        @Query("count") count:Int? = 30,
        @Query("offset") offset:String?,
    ): PagedDataResponse<ApiPost>

    @PUT("/api/v1/post")
    @Multipart
    suspend fun createPost(
        @Part("text") text: RequestBody?,
        @Part image1: MultipartBody.Part?,
        @Part image2: MultipartBody.Part?,
        @Part image3: MultipartBody.Part?,
        @Part image4: MultipartBody.Part?
    ): ApiPost

    @GET("/api/v1/posts/{profileId}")
    suspend fun getProfilePosts(
        @Path("profileId")profileId:String,
        @Query("count") count:Int? = 30,
        @Query("offset") offset:String?,
    ): PagedDataResponse<ApiPost>

    @GET("/api/v1/post/{postId}")
    suspend fun getPost(
        @Path("postId") postId:String,
    ): ApiPost

    @DELETE("/api/v1/post/{postId}")
    suspend fun deletePost(
        @Path("postId") postId:String,
    ): ResultResponse

}