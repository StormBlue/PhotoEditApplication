package com.yintech.ytx_gao.photoeditapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.yintech.ytx_gao.photoeditapplication.widget.ZoomableImageView;

public class MainActivity extends AppCompatActivity {

    private ZoomableImageView imageView;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mContext = this;
        initView();
    }

    private void initView() {
        imageView = (ZoomableImageView) findViewById(R.id.imageView);
        Glide.with(mContext).load("http://image.tianjimedia.com/uploadImages/2012/355/KZ2X68FL53RU_1024x768.jpg").into(imageView);
    }
}
