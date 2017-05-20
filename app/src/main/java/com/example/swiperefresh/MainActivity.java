package com.example.swiperefresh;

import android.os.CountDownTimer;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout mSwipeRefresh;
    private TextView mTvCurrentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvCurrentText = (TextView) findViewById(R.id.tv_current_time);
        mTvCurrentText.setText("current time : " + getCurrentTime());

        mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        mSwipeRefresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                delayWorksForSwipeRefresh(2000);
            }
        });

    }

    private void delayWorksForSwipeRefresh(final int delayMillis){
        new CountDownTimer(delayMillis, delayMillis) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                mTvCurrentText.setText("current time : " + getCurrentTime());
                mSwipeRefresh.setRefreshing(false);
            }
        }.start();
    }

    private String getCurrentTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String formatDate = sdf.format(date);

        return formatDate;
    }

}
