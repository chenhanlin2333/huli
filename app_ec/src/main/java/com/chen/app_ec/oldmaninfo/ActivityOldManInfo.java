package com.chen.app_ec.oldmaninfo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.chen.app_core.app.AppCore;
import com.chen.app_core.app.BaseActivity;
import com.chen.app_core.app.ConfigType;
import com.chen.app_core.net.RestCilent;
import com.chen.app_core.net.callback.IError;
import com.chen.app_core.net.callback.ISuccess;
import com.chen.app_core.storage.AppSharedpreference;
import com.chen.app_ec.R;
import com.chen.app_ec.R2;
import com.chen.app_ec.app_main_activity.MainContentActivity;
import com.chen.app_ec.loginandregister.FragmentRegister;
import com.chen.app_ec.loginandregister.javabean.UserLogin;
import com.chen.app_ec.loginandregister.javabean.UserOldman;
import com.chen.app_ec.oldmaninfo.data.UpateOld;
import com.chen.app_ec.user.User;

import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.chen.app_ec.loginandregister.FragmentLogin.OLDFAMILY;
import static com.chen.app_ec.loginandregister.FragmentLogin.OLDHELP;
import static com.chen.app_ec.loginandregister.FragmentLogin.OLDMAN;

public class ActivityOldManInfo extends BaseActivity {

    @BindView(R2.id.cim_head)
    CircleImageView mCircleImageView;

    @BindView(R2.id.tv_id_user)
    TextView tv_oldid;

    @BindView(R2.id.tv_name)
    TextView tv_name;

    @BindView(R2.id.et_roomid)
    TextView tv_roomid;

    @BindView(R2.id.tv_marry_type)
    TextView tv_marred;


    @BindView(R2.id.tv_birday_num)
    TextView tv_birth;

    @BindView(R2.id.tv_grade)
    TextView tv_huli_grade;

    @BindView(R2.id.tv_zu_type)
    TextView tv_zu;

    @BindView(R2.id.im_huiligrade)
    ImageView im_huigrade;

    @BindView(R2.id.im_marry)
    ImageView im_marry;

    @BindView(R2.id.im_birday)
    ImageView im_birday;

    @BindView(R2.id.im_zu)
    ImageView im_zu;

    private boolean isedit;

    @BindView(R2.id.tv_edit)
    TextView tv_edit;

    int zu ;
    int marry;
    int huiligrade;
    String birthday;
    User user = User.getInstance();

    @OnClick(R2.id.tv_edit)
    void edit(){
        isedit = !isedit;
        if (isedit){
            tv_edit.setText("保存");
            im_birday.setVisibility(View.VISIBLE);
            im_huigrade.setVisibility(View.VISIBLE);
            im_marry.setVisibility(View.VISIBLE);
            im_zu.setVisibility(View.VISIBLE);

            UpateOld upateOld = new UpateOld();
            upateOld.setId(user.getOldmanid());
            upateOld.setName(user.getNameold());
            upateOld.setRoomid(user.getRoomid());

            upateOld.setZu(zu);
            upateOld.setBirth(birthday);
            upateOld.setMarry(marry);
            upateOld.setCareLevel(huiligrade);
            String raw = JSON.toJSONString(upateOld);

            RestCilent.builder()
                    .url( AppCore.getConfigurations().get(ConfigType.API_HOST.name())+"older/update")
                    .raw(raw)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String result) {
                            Toast.makeText(ActivityOldManInfo.this,"成功"+result, Toast.LENGTH_SHORT).show();
                            updatenurse();
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int id, String mesg) {
                            Toast.makeText(ActivityOldManInfo.this,"失败"+id,Toast.LENGTH_SHORT).show();
                        }
                    })
                    .build()
                    .post();
        }else {
            tv_edit.setText("修改");
            im_birday.setVisibility(View.INVISIBLE);
            im_huigrade.setVisibility(View.INVISIBLE);
            im_marry.setVisibility(View.INVISIBLE);
            im_zu.setVisibility(View.INVISIBLE);
        }
    }



    private void updatenurse(){

        String phone = user.getID()+"";
        final String password = user.getPwd();
        final int id = Integer.valueOf(phone.substring(phone.length()-4));
        UserLogin userLogin = new UserLogin();
        userLogin.setUserId(id);
        userLogin.setPassword(password);
        String raw = JSON.toJSONString(userLogin);


        RestCilent.builder()
                .url( AppCore.getConfigurations().get(ConfigType.API_HOST.name())+"login/login")
                .raw(raw)
                .addcontextandload(this)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String result) {
                        String mesage = JSON.parseObject(result).getString("message");
//                        Toast.makeText(ActivityOldManInfo.this,"成功"+mesage, Toast.LENGTH_SHORT).show();
                        if (!mesage.equals("ok")){
//                            Toast.makeText(ActivityOldManInfo.this,mesage,Toast.LENGTH_SHORT);
                            return;
                        }
                        //TODO type 联网得到

                        int type = JSON.parseObject(result).getInteger("type");

                        if (type == OLDMAN){

                            String  trueresult =  JSON.parseObject(result).getString("result");
                            JSONObject resultObject = JSONObject.parseObject(trueresult);

                            JSONObject help = resultObject.getJSONObject("care");
                            JSONObject relative = resultObject.getJSONObject("family");
                            User user = User.getInstance();
                            user.setType(type);

                            // 老人
                            if (resultObject != null){
                                user.setRoomid(resultObject.getInteger("roomId"));
                                user.setMarry(resultObject.getInteger("marry"));
                                user.setZu(resultObject.getInteger("zu"));
                                user.setBirth(resultObject.getString("birth"));
                                user.setCarelevel(resultObject.getInteger("careLevel"));
                                user.setOldmanid(resultObject.getInteger("id"));
                                user.setNameold(resultObject.getString("name"));
                                user.setPwd(resultObject.getString("pwd"));
                                user.setHeadurl(resultObject.getString("portrait"));
                            }

                            // 护工id
                            if (help !=null){
                                user.setHelpid(help.getInteger("id"));
                                user.setNamehelp(help.getString("name"));
                            }
                            if (relative != null){
                                // 家属
                                user.setRelativeid(relative.getInteger("id"));
                                user.setNamefamily(relative.getString("name"));
                            }


                        }else if (type == OLDFAMILY){



                            String  trueresult =  JSON.parseObject(result).getString("result");
                            JSONObject resultObject = JSONObject.parseObject(trueresult);



                            JSONObject oldman = resultObject.getJSONObject("older");
                            JSONObject care = resultObject.getJSONObject("care");
                            User user = User.getInstance();
                            user.setType(type);

                            if (resultObject != null){
                                // 家属
                                user.setNamefamily(resultObject.getString("name"));
                                user.setRelativeid(resultObject.getInteger("id"));
                                user.setHeadurl(resultObject.getString("portrait"));
                            }

                            if (oldman != null){
                                // 老人
                                user.setOldmanid(oldman.getInteger("id"));
                                user.setRoomid(oldman.getInteger("roomId"));
                                user.setMarry(oldman.getInteger("marry"));
                                user.setZu(oldman.getInteger("zu"));
                                user.setBirth(oldman.getString("birth"));
                                user.setCarelevel(oldman.getInteger("careLevel"));
                                user.setOldmanid(oldman.getInteger("id"));
                                user.setNameold(oldman.getString("name"));
                            }

                            // 护工
                            if (care != null){
                                user.setHelpid(care.getInteger("id"));
                                user.setNamefamily(care.getString("name"));
                            }


                        }else if (type == OLDHELP){
                            String  trueresult =  JSON.parseObject(result).getString("result");
                            JSONObject resultObject = JSONObject.parseObject(trueresult);

                            JSONObject oldman = resultObject.getJSONObject("older");
                            JSONObject famiy = resultObject.getJSONObject("family");
                            User user = User.getInstance();
                            user.setType(type);
                            if (resultObject!=null){
                                user.setCarelevelhelp(resultObject.getInteger("careLevel"));
                                user.setCaretimehelp(resultObject.getInteger("careTime"));
                                user.setNamehelp(resultObject.getString("name"));
                                user.setHeadurl(resultObject.getString("portrait"));
                            }

                            // 设置老人;
                            if (oldman != null){
                                user.setOldmanid(oldman.getInteger("id"));
                                user.setRoomid(oldman.getInteger("roomId"));
                                user.setMarry(oldman.getInteger("marry"));
                                user.setZu(oldman.getInteger("zu"));
                                user.setBirth(oldman.getString("birth"));
                                user.setCarelevel(oldman.getInteger("careLevel"));
                                user.setOldmanid(oldman.getInteger("id"));
                                user.setNameold(oldman.getString("name"));
                            }

                            if (famiy != null){
                                user.setRelativeid(famiy.getInteger("id"));
                                user.setNamefamily(famiy.getString("name"));
                            }
                        }



                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int id, String mesg) {
                        Toast.makeText(ActivityOldManInfo.this,"失败"+id,Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .post();
    }





    @OnClick(R2.id.im_zu)
    void zu(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this)
                .setSingleChoiceItems(R.array.zu, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String[] array = getResources().getStringArray(R.array.zu);
                        String result = array[i];
                        String s = result.split("\\s+")[1];
                        tv_zu.setText(s);
                        zu = Integer.valueOf(s);
                    }
                });
        alertDialog.show();
    }


    @OnClick(R2.id.im_marry)
    void marry(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this)
                .setSingleChoiceItems(R.array.marry, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String[] array = getResources().getStringArray(R.array.marry);
                        String result = array[i];
                        String s = result.split("\\s+")[1];
                        tv_marred.setText(s);
                        marry = Integer.valueOf(s);
                    }
                });
        alertDialog.show();
    }

    @OnClick(R2.id.im_huiligrade)
    void huiligrade(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this)
                .setSingleChoiceItems(R.array.hu_li_grade, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String[] array = getResources().getStringArray(R.array.hu_li_grade);
                        String result = array[i];
                        String s = result.split("\\s+")[1];
                        tv_huli_grade.setText(s);
                        huiligrade = Integer.valueOf(s);
                    }
                });
        alertDialog.show();
    }

    DatePickerDialog mDatePickerDialog;
    @OnClick(R2.id.im_birday)
    void birth(){
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        final int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH);
        final int day = mCalendar.get(Calendar.DAY_OF_MONTH);

        mDatePickerDialog  =new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String date = FragmentRegister.getDate(i,i1,i2);
                tv_birth.setText(date);
                birthday = date;
            }
        },year,month,day);
        mDatePickerDialog.show();
    }




    @Override
    public int getLayoutid() {
        return R.layout.activity_old_man_info;
    }

    @Override
    public void havebind() {

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("id",user.getID());

        RestCilent.builder()
                .url((String) AppCore.getConfigurations().get(ConfigType.API_HOST.name())+  "portrait/load")
                .params(hashMap)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String result) {
                        if (result != null){
                            String url = "http://192.168.115.59:8080/"+result;
                            Glide.with(ActivityOldManInfo.this).load(url).into(mCircleImageView);
                        }
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int id, String mesg) {

                    }
                })
                .build()
                .get();


        tv_oldid.setText(user.getOldmanid()+"");
        tv_name.setText(user.getNameold());
        tv_roomid.setText(user.getRoomid()+"");

        String[] grade = getResources().getStringArray(R.array.hu_li_grade);
        tv_huli_grade.setText(grade[user.getCarelevel()].split("\\s+")[1]);

        String[] marryx = getResources().getStringArray(R.array.marry);
        tv_marred.setText(marryx[user.getMarry()].split("\\s+")[1]);
        tv_birth.setText(user.getBirth());
        String[] zux = getResources().getStringArray(R.array.zu);
        tv_zu.setText(zux[user.getZu()].split("\\s+")[1]);


        birthday = user.getBirth();
        zu = user.getZu();
        marry = user.getMarry();
        huiligrade = user.getCarelevel();

    }


    public static void start(Activity activity){
        Intent intent = new Intent(activity,ActivityOldManInfo.class);
        activity.startActivity(intent);
    }

}
