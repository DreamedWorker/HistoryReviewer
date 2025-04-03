package icu.bluedream.historior.backend.web

import de.jensklingenberg.ktorfit.http.*

interface Requests {
    /**
     * @param account 用户名
     * @param password MD5加密后的密码
     * @return 测试数据结构
     * */
    @POST("account/login/ANDROID/4.1.8")
    @Headers("Accept-Encoding: json", "Connection: close")
    @FormUrlEncoded
    suspend fun testLogin(
        @Query("device_code") deviceCode: String = "[d]9d77f167-7b58-4ab1-ae5e-0c9a5d316508",
        @Query("voice_code") voiceCode: String = "",
        @Field("account") account: String,
        @Field("login_type") loginType: Int = 2,
        @Field("password") password: String,
        @Field("sign") sign: String,
        @Query("platform") platform: Int = 2,
        @Query("gkey") gkey: String = "000000",
        @Query("app_version") version: String = "4.3.0.8",
        @Query("versioncode") vc: String = "20141505",
        @Query("market_id") mi: String = "floor_web"
    ) : String
}