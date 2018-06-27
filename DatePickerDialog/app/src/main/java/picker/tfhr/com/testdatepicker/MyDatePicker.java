package picker.tfhr.com.testdatepicker;


import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import picker.tfhr.com.testdatepicker.Utils.DensityUtil;
import picker.tfhr.com.testdatepicker.Utils.MyDateUtils;
import picker.tfhr.com.testdatepicker.Utils.StringUtils;
/**
 * Created by huangsf on 2018/6/11.
 */
public class MyDatePicker extends RelativeLayout {

    private TextView tfhr_sel_zhouqi;
    private TextView tfhr_sel_xzriqi;
    private LinearLayout tfhr_sel_tjzq;
    private Context context;
    private Activity act;
    private String selriqi;
    private String selType;

    public void setTVXuanZeRQ(String rq) {
        tfhr_sel_xzriqi.setText(rq);
    }

    public String getSelriqi() {
        return selriqi;
    }

    public void setSelriqi(String selriqi) {
        this.selriqi = selriqi;
    }

    public String getSelType() {
        return selType;
    }

    public void setSelType(String selType) {
        this.selType = selType;
    }

    public Activity getAct() {
        return act;
    }

    public void setAct(Activity act) {
        this.act = act;
    }


    public MyDatePicker(Context context) {
        super(context);
        this.context = context;
    }

    public MyDatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        //加载视图的布局
        LayoutInflater.from(context).inflate(R.layout.mydatapicker_layout, this, true);
    }

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

    private MyDatePicker.OnSelectOKListener listener;

    //获得接口对象的方法。
    public void setOnSelectOKListener(MyDatePicker.OnSelectOKListener listener) {
        this.listener = listener;
    }

    //定义一个接口
    public interface OnSelectOKListener {
        public void onSelectOK(String selRiqi, String selType);
    }

    private void openDatePicker(View v) {
        if (getAct() == null) return;
        final PopupWindow p = new PopupWindow(getAct());
        View view = LayoutInflater.from(getAct()).inflate(R.layout.popupwindow_datepicker_layout, null);
        p.setContentView(view);
        WindowManager.LayoutParams lp = getAct().getWindow().getAttributes();
        lp.alpha = 0.4f;
        getAct().getWindow().setAttributes(lp);
        p.setFocusable(true);
        Button yes, no;
        final Button month, day;
        final DatePicker dp;
        yes = (Button) view.findViewById(R.id.popupwindow_datepicker_yes);
        no = (Button) view.findViewById(R.id.popupwindow_datepicker_no);
        dp = (DatePicker) view.findViewById(R.id.popupwindow_datepicker);
        month = (Button) view.findViewById(R.id.popupwindow_datepicker_choose_month);
        day = (Button) view.findViewById(R.id.popupwindow_datepicker_choose_day);
        //final View rootView = LayoutInflater.from(context).inflate(R.layout.fragment_board, null);
        p.setWidth(DensityUtil.dip2px(context, 300));
        p.setHeight(DensityUtil.dip2px(context, 400));
        p.setBackgroundDrawable(null);
        p.setOnDismissListener(new PopupWindow.OnDismissListener() {
            //在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = getAct().getWindow().getAttributes();
                lp.alpha = 1f;
                getAct().getWindow().setAttributes(lp);
            }
        });
        p.showAtLocation(v, Gravity.CENTER, 0, 0);
        p.setOutsideTouchable(false);
        //String[] split = GlobleManager.kbSelectRQ.split("-");
        String[] split = getSelriqi().split("-");
        if (tfhr_sel_zhouqi.getText().toString().equals("月")) {
            month.setBackgroundColor(context.getResources().getColor(R.color.skyblue2));
            day.setBackgroundColor(0);
            ((ViewGroup) ((ViewGroup) dp.getChildAt(0)).getChildAt(0)).getChildAt(2).setVisibility(View.GONE);
        } else if (tfhr_sel_zhouqi.getText().toString().equals("日")) {
            month.setBackgroundColor(0);
            day.setBackgroundColor(context.getResources().getColor(R.color.skyblue2));
            ((ViewGroup) ((ViewGroup) dp.getChildAt(0)).getChildAt(0)).getChildAt(2).setVisibility(View.VISIBLE);
        }
        if (View.VISIBLE == ((ViewGroup) ((ViewGroup) dp.getChildAt(0)).getChildAt(0)).getChildAt(2).getVisibility()) {
            dp.updateDate(Integer.valueOf(split[0]), Integer.valueOf(split[1]) - 1, Integer.valueOf(split[2]));
        } else {
            dp.updateDate(Integer.valueOf(split[0]), Integer.valueOf(split[1]) - 1, Integer.valueOf(MyDateUtils.getCurrentDate(2)));
        }
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (View.VISIBLE == ((ViewGroup) ((ViewGroup) dp.getChildAt(0)).getChildAt(0)).getChildAt(2).getVisibility()) {
                    setSelriqi(dp.getYear() + "-" + StringUtils.getString((dp.getMonth() + 1)) + "-" + StringUtils.getString(dp.getDayOfMonth()));
                    tfhr_sel_zhouqi.setText("日");
                } else {
                    setSelriqi(dp.getYear() + "-" + StringUtils.getString((dp.getMonth() + 1)));
                    tfhr_sel_zhouqi.setText("月");
                }
                tfhr_sel_xzriqi.setText(selriqi);
                listener.onSelectOK(getSelriqi(), getSelType());
                p.dismiss();
            }
        });
        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                month.setBackgroundColor(context.getResources().getColor(R.color.skyblue2));
                day.setBackgroundColor(0);
                selType = "0";
                ((ViewGroup) ((ViewGroup) dp.getChildAt(0)).getChildAt(0)).getChildAt(2).setVisibility(View.GONE);
            }

        });
        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                month.setBackgroundColor(0);
                selType = "1";
                day.setBackgroundColor(context.getResources().getColor(R.color.skyblue2));
                ((ViewGroup) ((ViewGroup) dp.getChildAt(0)).getChildAt(0)).getChildAt(2).setVisibility(View.VISIBLE);
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (p.isShowing()) {
                    p.dismiss();
                } else {
                    //产生背景变暗效果
                    WindowManager.LayoutParams lp = getAct().getWindow().getAttributes();
                    lp.alpha = 0.4f;
                    getAct().getWindow().setAttributes(lp);
                    p.showAtLocation(v, Gravity.CENTER, 0, 0);
                }
            }
        });
    }
}
