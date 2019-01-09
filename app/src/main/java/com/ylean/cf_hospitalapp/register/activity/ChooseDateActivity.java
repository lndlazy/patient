package com.ylean.cf_hospitalapp.register.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.register.bean.TimeEntry;
import com.ylean.cf_hospitalapp.timesquare.CalendarPickerView;
import com.ylean.cf_hospitalapp.utils.IDateUtils;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

import java.util.Calendar;
import java.util.Date;

/**
 * 选择日期页面
 * Created by linaidao on 2019/1/5.
 */

public class ChooseDateActivity extends BaseActivity {

    private Calendar nextDay;
    private Calendar lastDay;
    protected long defaultTime = 0;// 默认的时间
    private static final int DATE_RESULT_CODE = 0x1024;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_choose_date);

        initWidget();
    }

    private void initWidget() {
        CalendarPickerView calendar_view = findViewById(R.id.calendar_view);
        TitleBackBarView tbv = findViewById(R.id.tbv);
        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initDefaultDate();
        calendar_view.init(lastDay.getTime(), nextDay.getTime())
                .withSelectedDate(new Date()).inMode(CalendarPickerView.SelectionMode.SINGLE);

        calendar_view.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {

                Calendar c = Calendar.getInstance();
                c.setTime(date);

//                Date time = c.getTime();
//                int day = c.get(Calendar.DAY_OF_MONTH);
//                int month = c.get(Calendar.MONTH);
//                LogUtil.d("TAG", "选择的日期:::===>" + (month + 1) + "月" + day + "日");

                TimeEntry timeEntry = new TimeEntry();
                timeEntry.setWeekDesc(IDateUtils.getWeekDay(c));
                timeEntry.setDateDes(IDateUtils.formatCalTime(c));
                timeEntry.setTimeDesc(IDateUtils.formatCalYMD(c));

                Intent data = new Intent();
                data.putExtra("timeEntry", timeEntry);
                setResult(DATE_RESULT_CODE, data);
                finish();
//                Logger.d((c.get(Calendar.MONTH) + 1) + "月" + c.get(Calendar.DAY_OF_MONTH) + "日");
//                setResult(0x34, data);
//                finish();

            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });
    }

    private void initDefaultDate() {

        // 日历 初始化
        nextDay = Calendar.getInstance();
        nextDay.add(Calendar.DAY_OF_WEEK, 30);

        lastDay = Calendar.getInstance();
        lastDay.add(Calendar.DAY_OF_WEEK, 0);
        // 需要一个默认值 当天时间向前推两个小时
        Calendar instance = Calendar.getInstance();

        defaultTime = instance.getTime().getTime();

    }
}