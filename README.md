# 自定义日期控件datepicker

自己定义一个日期控件，可以根据月或者日进行统计

1.改造android自带的datepicker，可以设置选择年月显示或者年月日显示格式；
2.自定义popwindow，并把datepicker显示到popwindow，并设置背景变暗
3.制作成组件；
  

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tfhr_sel_zhouqi = (TextView) findViewById(R.id.text_tfhr_tjzq);
        tfhr_sel_xzriqi = (TextView) findViewById(R.id.text_tfhr_rqxz);
        tfhr_sel_tjzq = (LinearLayout) findViewById(R.id.ll_tfhr_tjzq);
        tfhr_sel_tjzq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker(v);
            }
        });
    }
