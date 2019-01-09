package com.ylean.cf_hospitalapp.widget;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import java.util.Calendar;

public class MyDatePicker extends DatePickerDialog {

    public MyDatePicker(Context context, int theme, OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth) {
        super(context, theme, callBack, year, monthOfYear, dayOfMonth);
    }

    public MyDatePicker(Context context, OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth) {
        super(context, callBack, year, monthOfYear, dayOfMonth);
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int month, int day) {
        super.onDateChanged(view, year, month, day);

//      LogUtil.e("tag", "===onDateChanged===year:" + year + ",month:" + month + ",day" + day);

        Calendar calendar = Calendar.getInstance();
//        calendar.set(year, month, day);
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

//        LogUtil.e("tag", "当前的时间: year:" + currentYear + ",month:" + currentMonth + ",day:" + currentDay);

        if (year > currentYear) {
            //选择的年份大于当前的年份, 设置为当前的年份
            updateDate(currentYear,month,day);
        }

        if (month > currentMonth && year >= currentYear) {
            //如果月份大于当前的月份, 并且年份大于等于当前的年份则修改月份
            updateDate(year, currentMonth, day);
        }

        if (day > currentDay && month >= currentMonth && year >= currentYear) {
            updateDate(year, month, currentDay);
        }

    }
}