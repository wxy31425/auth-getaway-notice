package com.auth.get.away.notice.utils;

import java.util.Calendar;

/**
 * 公共类
 * @author wxy
 * 2020-3-10
 */
public class CommonUtils {
    /**
     * 获取当前年份,月份，日期
     * @return
     */
    public static String currentTime(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH) +1;
        int year = calendar.get(Calendar.YEAR);
        if(month < 10){
            String  nowMonth = ""+year+"-0"+month+"";
            return nowMonth;
        } else {
            String  nowMonth = ""+year+"-" +
                    ""+month+"";
            return nowMonth;
        }

    }
}
