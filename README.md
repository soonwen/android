# 自定义日期控件datepicker

自己定义一个日期控件，可以根据月或者日进行统计

1.改造android自带的datepicker，可以设置选择年月显示或者年月日显示格式；
2.自定义popwindow，并把datepicker显示到popwindow，并设置背景变暗
3.制作成组件；
  

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        sel_zhouqi = (TextView) findViewById(R.id.text_tfhr_tjzq);
        sel_xzriqi = (TextView) findViewById(R.id.text_tfhr_rqxz);
        sel_tjzq = (LinearLayout) findViewById(R.id.ll_tfhr_tjzq);
        sel_tjzq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker(v);
            }
        });
    }
运行起来效果
http://192.144.129.98:10890/imgsrv/datapicker/1_0.png

控件选择月效果
http://192.144.129.98:10890/imgsrv/datapicker/1_1.png

控件选择日效果
http://192.144.129.98:10890/imgsrv/datapicker/1_2.png
