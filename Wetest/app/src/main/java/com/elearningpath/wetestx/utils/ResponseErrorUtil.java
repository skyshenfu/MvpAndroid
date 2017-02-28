package com.elearningpath.wetestx.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 创建者：韦小宝
 * 创建日期：16/11/8
 * 版本号：1.0.0
 * 功能说明：拦截rxjava最外层的错误请求
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
