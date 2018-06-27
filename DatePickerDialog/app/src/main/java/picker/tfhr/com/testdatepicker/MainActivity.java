package picker.tfhr.com.testdatepicker;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Calendar;

import picker.tfhr.com.testdatepicker.Utils.MyDateUtils;

/**
 * Created by huangsf on 2018/6/11.
 */
public class MainActivity extends AppCompatActivity {


    private Button btn_open;
    private Context context;

    private LayoutInflater mLayoutInflater;
    private View view;
    private MyDatePicker mydp;
    private TextView sel_Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        btn_open = findViewById(R.id.btn_Open);
        sel_Date = findViewById(R.id.sel_date);
        mLayoutInflater=LayoutInflater.from(context);
        view=mLayoutInflater.inflate(R.layout.mylayout_singleitem, null);

        mydp= findViewById(R.id.my_datepicker);
        mydp.setAct(this);
        mydp.setSelriqi("2018-06-26");
        mydp.setTVXuanZeRQ("2018-06-26");
        mydp.setOnSelectOKListener(new MyDatePicker.OnSelectOKListener() {
            @Override
            public void onSelectOK(String selRiqi, String selType) {
                Log.i("myDatePicker--:","sqlriqi-------"+selRiqi);
                Log.i("myDatePicker--:","sqltype------"+selType);
                sel_Date.setText("选择的日期是："+selRiqi);
            }
        });

        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //openPicker();
                //openDP2();
                //openDP3();
            }
        });
    }

    private void OpenMyDatePicker(){

    }

    private void openDP3(){
        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        //AlertDialog.THEME_HOLO_DARK,
        DatePickerDialog dialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        //占位符可以使用0和#两种，当使用0的时候会严格按照样式来进行匹配，
                        //不够的时候会补0，而使用#时会将前后的0进行忽略
                        DecimalFormat df = new DecimalFormat("00");
                        String strMonth = df.format(monthOfYear + 1);
                        String strDay = df.format(dayOfMonth);
                        btn_open.setText(year + "-" + strMonth + "-" + strDay);
                    }
                }, yy, mm, dd){

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                this.addContentView(view, new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                LinearLayout mSpinners = (LinearLayout) findViewById(getContext().getResources().getIdentifier("android:id/pickers", null, null));
                if (mSpinners != null) {
                    NumberPicker mMonthSpinner = (NumberPicker) findViewById(getContext().getResources().getIdentifier("android:id/month", null, null));
                    NumberPicker mYearSpinner = (NumberPicker) findViewById(getContext().getResources().getIdentifier("android:id/year", null, null));
                    mSpinners.removeAllViews();
                    if (mYearSpinner != null) {
                        mSpinners.addView(mYearSpinner);
                    }

                    if (mMonthSpinner != null) {
                        mSpinners.addView(mMonthSpinner);
                    }
                }
                View dayPickerView = findViewById(getContext().getResources().getIdentifier("android:id/day", null, null));
                if(dayPickerView != null){
                    dayPickerView.setVisibility(View.GONE);
                }

            }
        };

        dialog.setTitle("请选择日期");
        dialog.show();
    }
    private void openDP2(){
        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog dlg = new DatePickerDialog(context, AlertDialog.THEME_HOLO_DARK, null, yy, mm, dd) {
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                LinearLayout mSpinners = (LinearLayout) findViewById(getContext().getResources().getIdentifier("android:id/pickers", null, null));
                if (mSpinners != null) {
                    NumberPicker mMonthSpinner = (NumberPicker) findViewById(getContext().getResources().getIdentifier("android:id/month", null, null));
                    NumberPicker mYearSpinner = (NumberPicker) findViewById(getContext().getResources().getIdentifier("android:id/year", null, null));
                    mSpinners.removeAllViews();
                    if (mMonthSpinner != null) {
                        mSpinners.addView(mMonthSpinner);
                    }
                    if (mYearSpinner != null) {
                        mSpinners.addView(mYearSpinner);
                    }
                }
                View dayPickerView = findViewById(getContext().getResources().getIdentifier("android:id/day", null, null));
                if(dayPickerView != null){
                    dayPickerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onDateChanged(DatePicker view, int year, int month, int day) {
                super.onDateChanged(view, year, month, day);
                setTitle("请选择信用卡有效期");
            }
        };
        dlg.setTitle("请选择信用卡有效期");
    }
    private void openPicker(){
        final Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(MyDateUtils.strToDate("yyyy-MM", btn_open.getText().toString()));
        new MonPickerDialog(context,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,int monthOfYear, int dayOfMonth) {
                localCalendar.set(Calendar.YEAR, year);
                localCalendar.set(Calendar.MONTH, monthOfYear);
                btn_open.setText(MyDateUtils.clanderTodatetime(localCalendar, "yyyy-MM"));
            }
        }, localCalendar.get(Calendar.YEAR), localCalendar.get(Calendar.MONTH),localCalendar.get(Calendar.DATE)).show();
    }



    public class MonPickerDialog extends DatePickerDialog {
        public MonPickerDialog(Context context, OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth) {
            super(context, callBack, year, monthOfYear, dayOfMonth);
            this.setTitle(year + "年" + (monthOfYear + 1) + "月");
            ((ViewGroup) ((ViewGroup) this.getDatePicker().getChildAt(0)).getChildAt(0)).getChildAt(2).setVisibility(View.GONE);
        }

        @Override
        public void onDateChanged(DatePicker view, int year, int month, int day) {
            super.onDateChanged(view, year, month, day);
            this.setTitle(year + "年" + (month + 1) + "月");
        }

    }
}


