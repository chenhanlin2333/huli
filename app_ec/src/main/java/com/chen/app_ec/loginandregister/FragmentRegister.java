package com.chen.app_ec.loginandregister;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chen.app_core.app.AppCore;
import com.chen.app_core.app.BaseFragment;
import com.chen.app_core.app.ConfigType;
import com.chen.app_core.net.RestCilent;
import com.chen.app_core.net.callback.IError;
import com.chen.app_core.net.callback.ISuccess;
import com.chen.app_ec.R;
import com.chen.app_ec.R2;
import com.chen.app_ec.loginandregister.javabean.UserFamily;
import com.chen.app_ec.loginandregister.javabean.UserOldman;
import com.chen.app_ec.loginandregister.javabean.Userhelp;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

public class FragmentRegister extends BaseFragment {

    public static final int HAN_ZHU = 1;
    public static final int HUI_ZHU = 2;
    public static final int QITA_ZHU = 3;
    public static final int HAVED_MARRY = 1;
    public static final int NOTHAVED_MARRY = 2;
    public static final int DIED_MARRY = 3;

    @BindView(R2.id.rg_user_type)
    RadioGroup rg_user_type;

    @BindView(R2.id.ll_oldpeople)
    LinearLayout ll_oldpeople;

    @BindView(R2.id.ll_helppeople)
    LinearLayout ll_helppeole;



    @BindView(R2.id.ll_relativepeolpe)
    LinearLayout ll_relativepeople;


    @BindView(R2.id.txregist_name)
    TextInputEditText tx_name;


    @BindView(R2.id.txregist_phone)
    TextInputEditText tx_phone;

    @BindView(R2.id.txregist_password)
    TextInputEditText tx_password;

    @BindView(R2.id.txregist_repassword)
    TextInputEditText tx_repassword;

    @BindView(R2.id.rg_zu)
    RadioGroup rg_zu;

    @BindView(R2.id.rg_hunyin)
    RadioGroup rg_hunyin;


    @BindView(R2.id.tv_grade)
    TextView tv_grade;

    @BindView(R2.id.tv_birday_num)
    TextView tv_birday_num;

    @BindView(R2.id.tv_grade_help)
    TextView tv_grade_help;

    @BindView(R2.id.tv_grade_help_time)
    TextView tv_grade_help_time;

    @BindView(R2.id.tv_huli_grade_relative)
    TextView tv_huili_grade_relative;

    @BindView(R2.id.txregist_core_of_oldpeople)
    TextInputEditText careoldid;


    //都有的信息
    private String name;
    private int id;
    private String pwd;
    private String more = "no";

    // 老人注册需要的数据
    private int huiligrade;
    private String birthday;
    private int zu;
    private int marry;

    // 护理人员需要的数据
    private int carelevel = 1;
    private int caretime =  1;
    private int useinfo = 0;

    // 家属都有的信息
    private int relation =1;
    private int oldid ;



    public static final int OLDMAN = 0;
    public static final int OLDHELP = 1;
    public static final int OLDFAMILY = 2;


    private int type;



    private DatePickerDialog mDatePickerDialog;


    @OnClick(R2.id.bt_regist)
    void regist(){
        if (checkForm()){
            String phone = tx_phone.getText().toString();
            int id = Integer.valueOf(phone.substring(phone.length()-4));
            String name = tx_name.getText().toString();
            String pwd = tx_password.getText().toString();



            if (type == OLDMAN){
                UserOldman userOldman = new UserOldman();
                userOldman.setId(id);
                userOldman.setName(name);
                userOldman.setPwd(pwd);
                userOldman.setBirth(birthday);
                userOldman.setCareLevel(huiligrade);
                userOldman.setMarry(marry);
                userOldman.setMore(more);
                userOldman.setZu(zu);

                String raw = JSON.toJSONString(userOldman);

                RestCilent.builder()
                        .url((String) AppCore.getConfigurations().get(ConfigType.API_HOST.name())+"older/register")
                        .raw(raw)
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String result) {
                                Toast.makeText(getActivity(),"成功"+result, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .error(new IError() {
                            @Override
                            public void onError(int id, String mesg) {
                                Toast.makeText(getActivity(),"失败"+id,Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build()
                        .post();


            }else if (type == OLDHELP){
                Userhelp userhelp = new Userhelp();
                userhelp.setId(id);
                userhelp.setName(name);
                userhelp.setPwd(pwd);
                userhelp.setCareLevel(carelevel);
                userhelp.setCareTime(caretime);
                userhelp.setUseInfo(useinfo);

                String raw = JSON.toJSONString(userhelp);

                RestCilent.builder()
                        .url((String) AppCore.getConfigurations().get(ConfigType.API_HOST.name())+"care/register")
                        .raw(raw)
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String result) {
                                Toast.makeText(getActivity(),"成功"+result, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .error(new IError() {
                            @Override
                            public void onError(int id, String mesg) {
                                Toast.makeText(getActivity(),"失败"+id,Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build()
                        .post();



            }else if (type == OLDFAMILY){
                UserFamily userFamily = new UserFamily();
                userFamily.setId(id);
                oldid = Integer.valueOf(careoldid.getText().toString());
//                userFamily.setName(name);
                userFamily.setPwd(pwd);
                userFamily.setOlderId(oldid);
                userFamily.setRelation(relation);


                String raw = JSON.toJSONString(userFamily);

                RestCilent.builder()
                        .url((String) AppCore.getConfigurations().get(ConfigType.API_HOST.name())+"family/register")
                        .raw(raw)
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String result) {
                                Toast.makeText(getActivity(),"成功"+result, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .error(new IError() {
                            @Override
                            public void onError(int id, String mesg) {
                                Toast.makeText(getActivity(),"失败"+id,Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build()
                        .post();
            }
        }
    }



    @OnClick(R2.id.im_birday)
    void setBirthday(){
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        final int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH);
        final int day = mCalendar.get(Calendar.DAY_OF_MONTH);
        mDatePickerDialog  =new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String date = getDate(i,i1,i2);
                tv_birday_num.setText(date);
                birthday = date;

            }
        },year,month,day);
        mDatePickerDialog.show();
    }





    public static String getDate(int year ,int month,int day){
        StringBuilder result = new StringBuilder();
        if (month == 0){
            result.append("一月");
        } else if (month == 1) {
            result.append("二月");
        }else if (month == 2){
            result.append("三月");
        }else if (month == 3){
            result.append("四月");
        }else if (month == 4){
            result.append("五月");
        }else if (month == 5){
            result.append("六月");
        }else if (month == 6){
            result.append("七月");
        }else if (month == 7){
            result.append("八月");
        }else if (month == 8){
            result.append("九月");
        }else if (month == 9){
            result.append("十月");
        }else if (month == 10){
            result.append("十一月");
        }else if (month == 11){
            result.append("十二月");
        }
        result.append(" "+day+", "+year);
        return result.toString();
    }


    @OnClick(R2.id.im_huiligrade)
    void huiligrade(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity())
                .setSingleChoiceItems(R.array.hu_li_grade, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String[] array = getResources().getStringArray(R.array.hu_li_grade);
                        String result = array[i];
                        String s = result.split("\\s+")[0];
                        tv_grade.setText(s);
                        huiligrade = Integer.valueOf(s);
                    }
                });
        alertDialog.show();
    }

    @OnClick(R2.id.im_huiligrade_help)
    void carelevel(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity())
                .setSingleChoiceItems(R.array.hu_li_help_grade, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String[] array = getResources().getStringArray(R.array.hu_li_help_grade);
                        String result = array[i];
                        String s = result.split("\\s+")[0];
                        tv_grade_help.setText(s);
                        carelevel = Integer.valueOf(s);
                    }
                });
        alertDialog.show();
    }


    @OnClick(R2.id.im_huiligrade_help_time)
    void time(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity())
                .setSingleChoiceItems(R.array.hu_li_help_richen, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String[] array = getResources().getStringArray(R.array.hu_li_help_richen);
                        String result = array[i];
                        String s = result.split("\\s+")[0];
                        tv_grade_help_time.setText(s);
                        caretime = Integer.valueOf(s);
                    }
                });
        alertDialog.show();
    }

    @OnClick(R2.id.im_huiligrade_relative)
    void relative(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity())
                .setSingleChoiceItems(R.array.huli_relative, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String[] array = getResources().getStringArray(R.array.huli_relative);
                        String result = array[i];
                        String s = result.split("\\s+")[0];
                        tv_huili_grade_relative.setText("关系是"+s);
                        relation = Integer.valueOf(s);
                    }
                });
        alertDialog.show();
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        choicetype();
        getzu();
        getmarry();

    }

    @OnClick(R2.id.tv_gotoLogin)
    void gotologin(){
        ((ActivityLogin)getActivity()).onBackPressed();
    }

    private void choicetype(){
        rg_user_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                ll_relativepeople.setVisibility(View.GONE);
                ll_helppeole.setVisibility(View.GONE);
                ll_oldpeople.setVisibility(View.GONE);
                if (R.id.rb_help == i){
                    ll_helppeole.setVisibility(View.VISIBLE);
                    type = OLDHELP;
                }else if (R.id.rb_oldpeople == i){
                    ll_oldpeople.setVisibility(View.VISIBLE);
                    type = OLDMAN;
                }else if (R.id.rb_relative == i){
                    type  = OLDFAMILY;
                    ll_relativepeople.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void getzu(){
        rg_zu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_han){
                    zu = HAN_ZHU;
                }else if (i == R.id.rb_huihui){
                    zu = HUI_ZHU;
                }else if (i == R.id.rb_qita){
                    zu = QITA_ZHU;
                }
            }
        });
    }



    private void getmarry(){
        rg_hunyin.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_haved){
                    marry = HAVED_MARRY;
                }else if (i == R.id.rb_nothaved){
                    marry = NOTHAVED_MARRY;
                }else if (i == R.id.rb_died){
                    marry = DIED_MARRY;
                }
            }
        });
    }


    @Override
    public void bindview(Bundle savedInstance, View rootview) {

    }



    @Override
    public Object setlayout() {
        return R.layout.fragment_register;
    }

    private boolean checkForm(){
        boolean isok = true;
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9])|(16[6]))\\d{8}$";
        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(tx_phone.getText().toString());
        boolean isMatch = m.matches();
//        if (!isMatch){
//            tx_phone.setError("手机号格式错误");
//            isok = false;
//        }

        if(tx_password.getText().toString().length() < 6){
            tx_password.setError("密码大于六位");
            isok =false;
        }

        if(!tx_password.getText().toString().equals(tx_repassword.getText().toString())){
            tx_repassword.setError("再次输入的不同");
            isok =false;
        }
        return isok;
    }



}
