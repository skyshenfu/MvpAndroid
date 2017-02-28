-optimizationpasses 5

#When not preverifing in a case-insensitive filing system, such as Windows. Because this tool unpacks your processed jars, you should then use:
-dontusemixedcaseclassnames

#Specifies not to ignore non-public library classes. As of version 4.5, this is the default setting
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers

#Preverification is irrelevant for the dex compiler and the Dalvik VM, so we can switch it off with the -dontpreverify option.
-dontpreverify

#Specifies to write out some more information during processing. If the program terminates with an exception, this option will print out the entire stack trace, instead of just the exception message.
-verbose
-printmapping proguardMapping.txt

-dontwarn org.apache.**
-dontwarn android.support.v4.**
-dontwarn com.google.**
-dontwarn com.alibaba.fastjson.**
-dontwarn android.webkit.WebView
-dontwarn com.jungly.gridpasswordview.**
-dontwarn android.content.**

-dontwarn com.ut.mini.internal.UTOriginalCustomHitBuilder
-dontwarn com.ut.mini.**
-dontwarn java.nio.**
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

#The -optimizations option disables some arithmetic simplifications that Dalvik 1.0 and 1.5 can't handle. Note that the Dalvik VM also can't handle aggressive overloading (of static fields).
#To understand or change this check http://proguard.sourceforge.net/index.html#/manual/optimizations.html
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*,!code/allocation/variable

#To repackage classes on a single package
-repackageclasses ''

#Uncomment if using annotations to keep them.
-keepattributes *Annotation*
-keepattributes *JavascriptInterface*

# GreeRobot eventbus
-keepclassmembers class ** {
    public void onEvent*(**);
}

-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.** { *; }
#-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}


#-libraryjars libs/android-support-v4.jar
#-libraryjars libs/com.umeng.message.lib.jar
#-libraryjars libs/httpmime-4.1.3.jar
#-libraryjars libs/SocialSDK_QQZone_1.jar
#-libraryjars libs/SocialSDK_QQZone_2.jar
#-libraryjars libs/SocialSDK_QQZone_3.jar
#-libraryjars libs/SocialSDK_WeiXin_1.jar
#-libraryjars libs/SocialSDK_WeiXin_2.jar
#-libraryjars libs/umeng_social_sdk.jar
#-libraryjars libs/umeng-analytics-v5.2.4.jar
#-libraryjars libs/umeng-feedback-v4.3.jar
#-libraryjars libs/umeng-update-v2.4.2.jar

-keepattributes Exceptions,InnerClasses,Signature
-keepattributes SourceFile,LineNumberTable

# 极光推送
-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }

#==================gson==========================
-dontwarn com.google.**
-keep class com.google.gson.** {*;}
-keepattributes EnclosingMethod
-keep class org.xz_sale.entity.**{*;} #这是你定义的实体类


#==================protobuf======================
-dontwarn com.google.**
-keep class com.google.protobuf.** {*;}


-keep public interface com.tencent.**

#Keep 信鸽推送用的class
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep class com.tencent.android.tpush.**  {* ;}
-keep class com.tencent.mid.**  {* ;}

#Keep classes that are referenced on the AndroidManifest
-keep public class javax.**
-keep public class java.util.**
-keep public class android.webkit.**
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep public class org.slf4j.** { *; }


-keep public class com.tencent.** {*;}
-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}

-keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}

-keep class im.yixin.sdk.api.YXMessage {*;}
-keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}

#-keep class com.huilian.binhai.common.utils.**
-keep public class com.ut.mini.internal.UTOriginalCustomHitBuilder
-keep public class com.ut.mini.**
-keep public class java.nio.**


-keep class com.jungly.gridpasswordview.demo.MainActivity$*{
    *;
}

#-keep class com.huilian.binhai.common.entities.** {
#    *;
#}

#JS与app交互用
#-keep class com.huilian.binhai.usercenter.usersetting.activities.WebMoreActivity

#-keep class com.huilian.binhai.usercenter.usersetting.activities.WebMoreActivity.**{
#    *;
#}

#-keep class com.huilian.binhai.usercenter.usersetting.activities.JsInterface.**{
#    *;
#}

#-keepclassmembers public class com.huilian.binhai.usercenter.usersetting.activities.WebMoreActivity{
#    *;
#}

#-keepclassmembers public class com.huilian.binhai.usercenter.usersetting.activities.JsInterface{
#    *;
#}

-keep class app286.firstaid.activities.OtherHealthActivity.**{
    *;
}

-keep class app286.firstaid.activities.HealthActivity.**{
    *;
}

-keep class app286.firstaid.fragments.HealthDayFragment.**{
    *;
}

-keep class com.app286.firstaid.activities.*Web*.**{
    *;
}

-keep public class [com.app286.firstaid].R$*{
    public static final int *;
}
-keep public class cn.m15.gotransfer.R$*{
    public static final int *;
}

-keep class com.jiubang.data.** { *; }

#To remove debug logs:
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
}

#To avoid changing names of methods invoked on layout's onClick.
-keepclassmembers class * {
   public <init>(org.json.JSONObject);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# 保留Activity中的方法参数是view的方法，
# 从而我们在layout里面编写onClick就不会影响
-keepclassmembers class * extends android.app.Activity {
    public void * (android.view.View);
}

-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String);
}

#Maintain java native methods
-keepclasseswithmembernames class * {
    native <methods>;
}

# Start bugtags
-keepattributes LineNumberTable,SourceFile

-keep class com.bugtags.library.** {*;}

-dontwarn com.bugtags.library.**
-keep class io.bugtags.** {*;}
-dontwarn io.bugtags.**

#-dontwarn org.apache.http.**
-dontwarn android.net.http.AndroidHttpClient

# End bugtags

# ProGuard configurations for NetworkBench Lens
-keep class com.networkbench.** { *; }
-dontwarn com.networkbench.**
-keepattributes Exceptions, Signature, InnerClasses
# End NetworkBench Lens

-dontwarn org.apache.commons.**
-keep class org.apache.http.impl.client.**
-dontwarn org.apache.commons.**
-keep class com.blueware.** { *; }
-dontwarn com.blueware.**
-keepattributes Exceptions, Signature, InnerClasses

#To maintain custom components names that are used on layouts XML.
#Uncomment if having any problem with the approach below
#-keep public class custom.components.package.and.name.**
-keep public class * extends android.view.View
-keep public class * extends android.view.ViewGroup


#-keep public class com.huilian.binhai.homepage.moredetail.fragments.** {*;}

# 如果有引用android-support-v4.jar包，可以添加下面这行
-keep class android.support.** { *; }
#-keep class android.support.v4.app.** { *; }
-keep interface android.support.v4.app.** { *; }

-dontwarn net.sf.**
-keep class net.sf.** { *;}

#极光 5.0以上
-dontwarn com.igexin.**
-keep class com.igexin.**{*;}
-keep class org.json.** { *; }


-dontwarn org.apache.**
-keep class org.apache.**{*;}

-dontwarn jodd.**
-keep class jodd.**{*;}


#To maintain custom components names that are used on layouts XML:
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
    *** get* ();
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

#Maintain enums
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

#To keep parcelable classes (to serialize - deserialize objects to sent through Intents)
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

#Keep the R
-keepclassmembers class **.R$* {
    *;
}

# 对于带有回调函数onXXEvent的，不能混淆
-keepclassmembers class * {
    void *(**On*Event);
}

-keep public class com.app286.firstaid.R$*{
    public static final int *;
}

###### ADDITIONAL OPTIONS NOT USED NORMALLY

#To keep callback calls. Uncomment if using any
#http://proguard.sourceforge.net/index.html#/manual/examples.html#callback
#-keep class mypackage.MyCallbackClass {
#   void myCallbackMethod(java.lang.String);
#}

#Uncomment if using Serializable
-keepclassmembers class * implements java.io.Serializable {
   static final long serialVersionUID;
   private static final java.io.ObjectStreamField[] serialPersistentFields;
   !static !transient <fields>;
   private void writeObject(java.io.ObjectOutputStream);
   private void readObject(java.io.ObjectInputStream);
   java.lang.Object writeReplace();
   java.lang.Object readResolve();
}

-keep class com.tencent.mm.sdk.openapi.WXMediaMessage {*;}
-keep class com.tencent.mm.sdk.openapi.** implements com.tencent.mm.sdk.openapi.WXMediaMessage$IMediaObject {*;}

#Google play service jar
-keep public class com.google.android.gms.ads.** {*;}
-keep public class com.google.ads.** {*;}

#fastJson
-keep public class com.alibaba.fastjson.** {*;}

-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

# 友盟推送
-keep,allowshrinking class org.android.agoo.service.* {
    public <fields>;
    public <methods>;
}


#友盟统计
 -keepclassmembers class * {
   public <init> (org.json.JSONObject);
}

#ButterKnife混淆
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#rxjava+retrofit2+okhttp
-dontwarn javax.annotation.**
-dontwarn javax.inject.**
# OkHttp3
-dontwarn okhttp3.logging.**
-keep class okhttp3.internal.**{*;}
-keep class okhttp3.**{*;}
-keep class okio.** { *;}
-dontwarn okio.**
# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
# RxJava RxAndroid
-dontwarn sun.misc.**
-keep class rx.** { *;}
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
#banner
# glide 的混淆代码
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
# banner 的混淆代码
-keep class com.youth.banner.** {
    *;
 }

#高德
#定位
-keep class com.amap.api.location.**{*;}
-keep class com.amap.api.fence.**{*;}
-keep class com.autonavi.aps.amapapi.model.**{*;}

#搜索
-keep   class com.amap.api.services.**{*;}

#2D地图
-keep class com.amap.api.maps2d.**{*;}
-keep class com.amap.api.mapcore2d.**{*;}
-dontwarn com.amap.api.mapcore2d.**

#导航（目前没有）
-keep class com.amap.api.navi.**{*;}
-keep class com.autonavi.**{*;}

#友盟社会化分享
-dontusemixedcaseclassnames
-dontshrink
-dontoptimize
-dontwarn com.google.android.maps.**
-dontwarn android.webkit.WebView
-dontwarn com.umeng.**
-dontwarn com.tencent.weibo.sdk.**
-dontwarn com.facebook.**
-keep public class javax.**
-keep public class android.webkit.**
-dontwarn android.support.v4.**
-keep enum com.facebook.**
-keepattributes Exceptions,InnerClasses,Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

-keep public interface com.facebook.**
-keep public interface com.tencent.**
-keep public interface com.umeng.socialize.**
-keep public interface com.umeng.socialize.sensor.**
-keep public interface com.umeng.scrshot.**

-keep public class com.umeng.socialize.* {*;}


-keep class com.facebook.**
-keep class com.facebook.** { *; }
-keep class com.umeng.scrshot.**
-keep public class com.tencent.** {*;}
-keep class com.umeng.socialize.sensor.**
-keep class com.umeng.socialize.handler.**
-keep class com.umeng.socialize.handler.*
-keep class com.umeng.weixin.handler.**
-keep class com.umeng.weixin.handler.*
-keep class com.umeng.qq.handler.**
-keep class com.umeng.qq.handler.*
-keep class UMMoreHandler{*;}
-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}
-keep class com.tencent.mm.sdk.modelmsg.** implements   com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}
-keep class im.yixin.sdk.api.YXMessage {*;}
-keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}
-keep class com.tencent.mm.sdk.** {
 *;
}
-dontwarn twitter4j.**
-keep class twitter4j.** { *; }

-keep class com.tencent.** {*;}
-dontwarn com.tencent.**
-keep public class com.umeng.com.umeng.soexample.R$*{
public static final int *;
}
-keep public class com.linkedin.android.mobilesdk.R$*{
public static final int *;
    }
-keepclassmembers enum * {
public static **[] values();
public static ** valueOf(java.lang.String);
}

-keep class com.tencent.open.TDialog$*
-keep class com.tencent.open.TDialog$* {*;}
-keep class com.tencent.open.PKDialog
-keep class com.tencent.open.PKDialog {*;}
-keep class com.tencent.open.PKDialog$*
-keep class com.tencent.open.PKDialog$* {*;}

-keep class com.sina.** {*;}
-dontwarn com.sina.**
-keep class  com.alipay.share.sdk.** {
   *;
}
-keepnames class * implements android.os.Parcelable {
public static final ** CREATOR;
}

-keep class com.linkedin.** { *; }
-keepattributes Signature

# For retrolambda
-dontwarn java.lang.invoke.*

-keep class com.youzan.** {*;}
-dontwarn com.youzan.**

-keep class com.app286.firstaid.beans.**{*;}

#新的RecyclerView
-keep class com.chad.library.adapter.** {
   *;
}






