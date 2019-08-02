package com.chen.app_ec.loginandregister;


import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.chen.app_core.app.AppCore;
import com.chen.app_core.app.BaseFragment;
import com.chen.app_core.app.ConfigType;
import com.chen.app_core.net.RestCilent;
import com.chen.app_core.net.callback.IError;
import com.chen.app_core.net.callback.ISuccess;
import com.chen.app_core.storage.AppSharedpreference;
import com.chen.app_ec.R;
import com.chen.app_ec.R2;
import com.chen.app_ec.app_main_activity.MainContentActivity;
import com.chen.app_ec.loginandregister.javabean.UserLogin;
import com.chen.app_ec.user.User;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentLogin extends BaseFragment {


    public static final int OLDMAN = 0;
    public static final int OLDFAMILY = 1;
    public static final int OLDHELP = 2;

    private int type;

    @BindView(R2.id.txlogin_phone)
    TextInputEditText te_phone;

    @BindView(R2.id.txlogin_password)
    TextInputEditText te_password;


    @BindView(R2.id.tv_gotoregist)
    TextView tv_gtregisit;

    @BindView(R2.id.rg_user_type)
    RadioGroup rg_user_type;

    @BindView(R2.id.civ_head)
    CircleImageView civ_head;




    @OnClick(R2.id.tv_gotoregist)
    void replacetoregist(){
        ((ActivityLogin)getActivity()).replacetoregist();
    }


    @BindView(R2.id.bt_login)
    Button bt_login;

    @OnClick(R2.id.bt_login)
    void login(){
        if (check()){
//            String phone = te_phone.getText().toString();
//            final String password = te_password.getText().toString();
//            final int id = Integer.valueOf(phone.substring(phone.length()-4));
//            UserLogin userLogin = new UserLogin();
//            userLogin.setUserId(id);
//            userLogin.setPassword(password);
//            String raw = JSON.toJSONString(userLogin);
//
//            if (type == OLDMAN){
//                RestCilent.builder()
//                        .url((String) AppCore.getConfigurations().get(ConfigType.API_HOST.name())+"login/login")
//                        .raw(raw)
//                        .success(new ISuccess() {
//                            @Override
//                            public void onSuccess(String result) {
////                                Toast.makeText(getActivity(),"成功"+result, Toast.LENGTH_SHORT).show();
//                                String mesage = JSON.parseObject(result).getString("message");
//                                if (!mesage.equals("ok")){
//                                    Toast.makeText(getActivity(),mesage,Toast.LENGTH_SHORT);
//                                    return;
//                                }
//                                JSONObject resultObject =  JSON.parseObject(result).getJSONObject("result");
//                                JSONObject help = resultObject.getJSONObject("care");
//                                JSONObject relative = resultObject.getJSONObject("family");
//                                User user = User.getInstance();
//                                user.setType(type);
//
//                                // 老人
//                                if (resultObject != null){
//                                    user.setRoomid(resultObject.getInteger("roomId"));
//                                    user.setMarry(resultObject.getInteger("marry"));
//                                    user.setZu(resultObject.getInteger("zu"));
//                                    user.setBirth(resultObject.getString("birth"));
//                                    user.setCarelevel(resultObject.getInteger("careLevel"));
//                                    user.setOldmanid(resultObject.getInteger("id"));
//                                    user.setNameold(resultObject.getString("name"));
//                                    user.setPwd(resultObject.getString("pwd"));
//                                    user.setHeadurl(resultObject.getString("portrait"));
//                                }
//
//                                // 护工id
//                                if (help !=null){
//                                    user.setHelpid(help.getInteger("id"));
//                                    user.setNamehelp(help.getString("name"));
//                                }
//                                if (relative != null){
//                                    // 家属
//                                    user.setRelativeid(relative.getInteger("id"));
//                                    user.setNamefamily(relative.getString("name"));
//                                }
//
//                                AppSharedpreference.setisLogin(true);
//                                AppSharedpreference.setIDandPassword(id,password);
//                                MainContentActivity.start(getActivity());
//                            }
//                        })
//                        .error(new IError() {
//                            @Override
//                            public void onError(int id, String mesg) {
//                                Toast.makeText(getActivity(),"失败"+id,Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .build()
//                        .post();
//            }else if (type == OLDHELP){
//                RestCilent.builder()
//                        .url((String) AppCore.getConfigurations().get(ConfigType.API_HOST.name())+ "care/login")
//                        .raw(raw)
//                        .success(new ISuccess() {
//                            @Override
//                            public void onSuccess(String result) {
////                                Toast.makeText(getActivity(),"成功"+result, Toast.LENGTH_SHORT).show();
//                                String mesage = JSON.parseObject(result).getString("message");
//                                if (!mesage.equals("ok")){
//                                    Toast.makeText(getActivity(),mesage,Toast.LENGTH_SHORT);
//                                    return;
//                                }
//                                JSONObject resultObject =  JSON.parseObject(result).getJSONObject("result");
//                                JSONObject oldman = resultObject.getJSONObject("older");
//                                JSONObject famiy = resultObject.getJSONObject("family");
//
//                                User user = User.getInstance();
//                                user.setType(type);
//                                if (resultObject!=null){
//                                    user.setCarelevelhelp(resultObject.getInteger("careLevel"));
//                                    user.setCaretimehelp(resultObject.getInteger("careTime"));
//                                    user.setNamehelp(resultObject.getString("name"));
//                                    user.setHeadurl(resultObject.getString("portrait"));
//                                }
//
//                                // 设置老人;
//                                if (oldman != null){
//                                    user.setOldmanid(oldman.getInteger("id"));
//                                    user.setRoomid(oldman.getInteger("roomId"));
//                                    user.setMarry(oldman.getInteger("marry"));
//                                    user.setZu(oldman.getInteger("zu"));
//                                    user.setBirth(oldman.getString("birth"));
//                                    user.setCarelevel(oldman.getInteger("careLevel"));
//                                    user.setOldmanid(oldman.getInteger("id"));
//                                    user.setNameold(oldman.getString("name"));
//                                }
//
//                                if (famiy != null){
//                                    user.setRelativeid(famiy.getInteger("id"));
//                                    user.setNamefamily(famiy.getString("name"));
//                                }
//
//                                AppSharedpreference.setisLogin(true);
//                                AppSharedpreference.setIDandPassword(id,password);
//                                MainContentActivity.start(getActivity());
//                            }
//                        })
//                        .error(new IError() {
//                            @Override
//                            public void onError(int id, String mesg) {
//                                Toast.makeText(getActivity(),"失败"+id,Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .build()
//                        .post();
//
//            }else if (type == OLDFAMILY){
//                RestCilent.builder()
//                        .url(((String) AppCore.getConfigurations().get(ConfigType.API_HOST.name()))+ "family/login")
//                        .raw(raw)
//                        .success(new ISuccess() {
//                            @Override
//                            public void onSuccess(String result) {
////                                Toast.makeText(getActivity(),"成功"+result, Toast.LENGTH_SHORT).show();
//                                String mesage = JSON.parseObject(result).getString("message");
//                                if (!mesage.equals("ok")){
//                                    Toast.makeText(getActivity(),mesage,Toast.LENGTH_SHORT);
//                                    return;
//                                }
//                                JSONObject resultObject =  JSON.parseObject(result).getJSONObject("result");
//                                JSONObject oldman = resultObject.getJSONObject("older");
//                                JSONObject care = resultObject.getJSONObject("care");
//
//
//                                User user = User.getInstance();
//                                user.setType(type);
//
//                                if (resultObject != null){
//                                    // 家属
//                                    user.setNamefamily(resultObject.getString("name"));
//                                    user.setRelativeid(resultObject.getInteger("id"));
//                                    user.setHeadurl(resultObject.getString("portrait"));
//                                }
//
//                                if (oldman != null){
//                                    // 老人
//                                    user.setOldmanid(oldman.getInteger("id"));
//                                    user.setRoomid(oldman.getInteger("roomId"));
//                                    user.setMarry(oldman.getInteger("marry"));
//                                    user.setZu(oldman.getInteger("zu"));
//                                    user.setBirth(oldman.getString("birth"));
//                                    user.setCarelevel(oldman.getInteger("careLevel"));
//                                    user.setOldmanid(oldman.getInteger("id"));
//                                    user.setNameold(oldman.getString("name"));
//                                }
//
//                                // 护工
//                                if (care != null){
//                                    user.setHelpid(care.getInteger("id"));
//                                    user.setNamefamily(care.getString("name"));
//                                }
//
//                                AppSharedpreference.setisLogin(true);
//                                AppSharedpreference.setIDandPassword(id,password);
//                                MainContentActivity.start(getActivity());
//                            }
//                        })
//                        .error(new IError() {
//                            @Override
//                            public void onError(int id, String mesg) {
//                                Toast.makeText(getActivity(),"失败"+id,Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .build()
//                        .post();
//            }

            loginNet();
        }
    }


    private void loginNet(){

        String phone = te_phone.getText().toString();
        final String password = te_password.getText().toString();
        final int id = Integer.valueOf(phone.substring(phone.length()-4));
        UserLogin userLogin = new UserLogin();
        userLogin.setUserId(id);
        userLogin.setPassword(password);
        String raw = JSON.toJSONString(userLogin);


        RestCilent.builder()
                .url((String) AppCore.getConfigurations().get(ConfigType.API_HOST.name())+"login/login")
                .raw(raw)
                .addcontextandload(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String result) {
                        String mesage = JSON.parseObject(result).getString("message");
                        Toast.makeText(getActivity(),"成功"+mesage, Toast.LENGTH_SHORT).show();
                        if (!mesage.equals("ok")){
                            Toast.makeText(getActivity(),mesage,Toast.LENGTH_SHORT);
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


                        AppSharedpreference.setisLogin(true);
                        AppSharedpreference.setIDandPassword(id,password);
                        MainContentActivity.start(getActivity());

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



    private void choicetype(){
        rg_user_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (R.id.rb_help == i){
                    type = OLDHELP;
                }else if (R.id.rb_oldpeople == i){
                    type = OLDMAN;
                }else if (R.id.rb_relative == i){
                    type  = OLDFAMILY;
                }
            }
        });
    }


    private boolean check(){
        String pheone = te_phone.getText().toString();
        String password = te_password.getText().toString();
        boolean pass = true;
//        if (!pheone.matches("^[1][3,4,5,7,8][0-9]{9}$")){
//            te_phone.setError("手机号码格式错误");
//            pass =false;
//        }
        if (password.length() <4){
            te_password.setError("密码不能小于四位");
            pass =false;
        }

        if(te_phone.length() < 4){
            te_phone.setError("不能小于四位");
            pass =false;
        }
        return pass;
    }



    @Override
    public void bindview(Bundle savedInstance, View rootview) {
        choicetype();
        int lastid = AppSharedpreference.getID();
        if (lastid != -1){
            te_phone.setText(AppSharedpreference.getID()+"");
        }
        HashMap<String,Object> hashMap = new HashMap<>();

        hashMap.put("id",AppSharedpreference.getID());
        RestCilent.builder()
                .url((String) AppCore.getConfigurations().get(ConfigType.API_HOST.name())+"portrait/load")
                .params(hashMap)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String result) {
                        if (result != null){
                            String url = "http://192.168.115.59:8080/"+result;
                            Glide.with(getActivity()).load(url).into(civ_head);
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
    }

    @Override
    public Object setlayout(){
        return R.layout.fragment_login;
    }
}
