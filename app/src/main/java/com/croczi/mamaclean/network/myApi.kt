package com.croczi.mamaclean.network

import com.croczi.mamaclean.data.auth.changePasswordRes
import com.croczi.mamaclean.data.auth.profile.myPackage.myPackages
import com.croczi.mamaclean.data.auth.profile.myPackegeItems.myPackegeItemRes
import com.croczi.mamaclean.data.auth.profile.myRequests.MyRequests
import com.croczi.mamaclean.data.auth.profile.profile
import com.croczi.mamaclean.data.auth.signUp.signUpResponse
import com.croczi.mamaclean.data.auth.verification.reSendCode.reSendRes
import com.croczi.mamaclean.data.auth.verification.verficationRes
import com.croczi.mamaclean.data.categore.categoreRes
import com.croczi.mamaclean.data.categore.categoryItems.categoryItemsRes
import com.croczi.mamaclean.data.categore.categoryItems.itemDetails.itemsDetailsRes
import com.croczi.mamaclean.data.citys.cities
import com.croczi.mamaclean.data.orders.categoryOrder.categoryOrderDetails.categoryOrderDetailsRes
import com.croczi.mamaclean.data.orders.categoryOrder.categoryOrdersRes.categoryOrderRes
import com.croczi.mamaclean.data.orders.packageOrder.packageOrderDetails.packageOrderDetailsRes
import com.croczi.mamaclean.data.orders.packageOrder.packageOrderRes.packageOrderRes
import com.croczi.mamaclean.data.packege.PackageRes
import com.croczi.mamaclean.data.packege.items.itemsInPackage
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import java.util.*
import java.util.concurrent.TimeUnit


interface myApi {

 @GET("city")
 suspend fun getCity():Response<cities>

    @FormUrlEncoded
    @POST("auth/singup")
    suspend fun SignUp(
        @Field("name") name:String,@Field("phone") phone:String,
        @Field("password") passward:String,@Field("city") cityId:Int,
        @Field("player_id") player_id:String
    ):Response<signUpResponse>

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun LogIn(
        @Field("phone") phone:String,
        @Field("password") passward:String,
        @Field("player_id") player_id:String
    ):Response<signUpResponse>

    @GET("auth/profile")
    suspend fun getProfile(
        @Header("Authorization") token:String
    ):Response<profile>

    @FormUrlEncoded
    @PUT("auth/changepassword")
    suspend fun changePassword(
        @Header("Authorization") token:String,@Field("password")passward: String
        ,@Field("newpassword")new:String
    ):Response<changePasswordRes>

    @FormUrlEncoded
    @POST("auth/verification")
     suspend fun verficationUser(@Field("code") code:String,
                                 @Field("phone") phone:String
    ):Response<verficationRes>

    @FormUrlEncoded
    @POST("auth/checkPhone")
    suspend fun reSendCode(@Field("phone") phone:String
    ):Response<reSendRes>
//06202520154872521054872/41587/
    @FormUrlEncoded
    @PUT("auth/reset")
    suspend fun reSetPassword(@Field("password") Password:String,
                              @Field("reset_Code") reset_Code:String,
                              @Field("phone") phone:String

    ):Response<reSendRes>

    @FormUrlEncoded
    @PUT("auth/profile")
    suspend fun UpdateProfile(  @Header("Authorization") token:String
                                ,@Field("name") Password:String,
                              @Field("city") cityId: Int?

    ):Response<reSendRes>


    @Multipart
    @PUT("auth/photo")
    suspend fun updateProfilePhoto(
        @Header("Authorization") token:String,
        @Part image: MultipartBody.Part
    ):Response<reSendRes>

    @GET("userpackage?")
    suspend fun getMyPackage(
        @Header("Authorization") token:String
        , @Query("lon") lon:Double,@Query("lat") lat:Double
    ):Response<myPackages>

    @GET("userpackage/{id}")
    suspend fun getUserPackageItems(
        @Path("id") id:Int
    ):Response<myPackegeItemRes>

    @GET("package?")
    suspend fun getPackages(
        @Query("lon") lon:Double,@Query("lat") lat:Double
    ):Response<PackageRes>

    @GET("package/{id}")
    suspend fun getPackageItems(
        @Path("id") id:Int
    ):Response<itemsInPackage>


    @GET("category")
    suspend fun getGategore(
    ):Response<categoreRes>

    @GET("item/category/{id}?")
    suspend fun getItemsGategore(
        @Path("id") id:Int,@Query("lon") lon:Double,@Query("lat") lat:Double
    ):Response<categoryItemsRes>

    @GET("item/{id}")
    suspend fun getItemsDetails(
        @Path("id") id:Int
    ):Response<itemsDetailsRes>

    @FormUrlEncoded
    @POST("package/buy")
    suspend fun BuyPacage(
        @Header("Authorization") token: String,@Field("PackageId") packageId:Int
    ):Response<reSendRes>


    @POST("package/requests")
    suspend fun getBuyPackageRequest(
        @Header("Authorization") token: String
    ):Response<MyRequests>



    @FormUrlEncoded
    @POST("orders")
    suspend fun MakeOrder(
        @Header("Authorization")token: String,
        @Field("latitude") lat: Double,
        @Field("longitude") lon: Double,
        @Field("price") price: Int,
        @Field("type") type: String,
        @Field("itemsCount") itemsCount: Int,
        @Field("items[]") itemsIds:List<@JvmSuppressWildcards Int>,
        @Field("counts[]") counts:List<@JvmSuppressWildcards Int>,
        @Field("types[]") types:List<@JvmSuppressWildcards String>
    ):Response<reSendRes>

    @FormUrlEncoded
    @POST("package-orders")
    suspend fun PackageOrder(
        @Header("Authorization")token: String,
        @Field("latitude") lat: Double,
        @Field("longitude") lon: Double,

        @Field("packageId") packageId: Int,
        @Field("itemsCount") itemsCount: Int,
        @Field("items[]") itemsIds:List<@JvmSuppressWildcards Int>,
        @Field("counts[]") counts:List<@JvmSuppressWildcards Int>,
        @Field("Newcounts[]") Newcounts:List<@JvmSuppressWildcards Int>
    ):Response<reSendRes>

    @GET("package-orders")
    suspend fun getPackageOrders(
        @Header("Authorization")token: String
    ):Response<packageOrderRes>

    @GET("package-orders/{id}")
    suspend fun getPackageOrdersDetails(
        @Header("Authorization")token: String
    ,@Path("id") id:Int
    ):Response<packageOrderDetailsRes>


    @DELETE("package-orders/{id}")
    suspend fun CancelPackageOrder(
        @Header("Authorization")token: String,
        @Path("id") id:Int
    ):Response<reSendRes>




    @GET("orders")
    suspend fun getCategoryOrders(
        @Header("Authorization")token: String
    ):Response<categoryOrderRes>

    @GET("orders/{id}")
    suspend fun getCategoryOrderDetails(
        @Header("Authorization")token: String,
        @Path("id") id:Int
    ):Response<categoryOrderDetailsRes>


    @DELETE("orders/{id}")
    suspend fun CancelOrder(
        @Header("Authorization")token: String,
        @Path("id") id:Int
    ):Response<reSendRes>

    companion object{

        operator fun invoke(
            networkIntercepter:networkIntercepter
        ):myApi{
            val spec = ConnectionSpec.Builder ( ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2)
                .cipherSuites(
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256
                )
                .build()
            val spec1 = ConnectionSpec.Builder ( ConnectionSpec.CLEARTEXT)
                .build()

            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .connectTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50,TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .addInterceptor(networkIntercepter).addInterceptor(loggingInterceptor)
                .connectionSpecs(Collections.singletonList(spec))
//                .connectionSpecs(Collections.singletonList(spec1))
                .build()
            return Retrofit.Builder()
                .client(client)
                .baseUrl("https://api.maamclean.com/")
               // .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(myApi::class.java)
        }
    }
}
