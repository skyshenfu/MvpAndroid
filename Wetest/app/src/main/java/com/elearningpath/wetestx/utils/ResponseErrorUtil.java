package com.elearningpath.wetestx.utils;

import android.content.Context;
import android.widget.Toast;
/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/3/16
 * 版本号：1.0.0
 * 描述：
 */

public class ResponseErrorUtil {
    public static void errorAssert(Throwable e, Context context){
        if (e!=null){
            String s=e.getMessage();
            if (s!=null){
                if (s.equals("HTTP 403 ")){
/*
                    context.startActivity(new Intent(context,LoginActivity.class));
*/
                    Toast.makeText(context.getApplicationContext(), "重新登录", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "网络故障", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
}
