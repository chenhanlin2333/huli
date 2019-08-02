package com.chen.app_ec.app_main_activity;


import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chen.app_core.app.AppCore;
import com.chen.app_core.app.BaseFragment;
import com.chen.app_core.app.ConfigType;
import com.chen.app_core.net.RestCilent;
import com.chen.app_core.net.RestCilentBuilder;
import com.chen.app_core.net.callback.IError;
import com.chen.app_core.net.callback.ISuccess;
import com.chen.app_ec.R;
import com.chen.app_ec.R2;
import com.chen.app_ec.loginandregister.FragmentRegister;
import com.chen.app_ec.loginandregister.javabean.UserFamily;
import com.chen.app_ec.loginandregister.javabean.UserOldman;
import com.chen.app_ec.loginandregister.javabean.Userhelp;
import com.chen.app_ec.user.User;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;

import javax.xml.validation.Validator;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentMe extends BaseFragment {

    private String birthday;
    private int huiligrade;
    private int marry;
    private int zu;

    private int helprade;
    private int helptime;

    private int realtion;


    @BindView(R2.id.tv_edit)
    TextView tv_edit;

    @BindView(R2.id.tv_name)
    TextView tv_name;

    @BindView(R2.id.tv_id_user)
    TextView tv_id_user;


    boolean isedit = false;

    @OnClick(R2.id.tv_edit)
    void edit(){
        isedit = !isedit;
        if (isedit){
            tv_edit.setText("保存");
            im_birday.setVisibility(View.VISIBLE);
            im_huigrade.setVisibility(View.VISIBLE);
            im_huili_grade_help.setVisibility(View.VISIBLE);
            im_marry.setVisibility(View.VISIBLE);
            im_relation.setVisibility(View.VISIBLE);
            im_time.setVisibility(View.VISIBLE);
            im_zu.setVisibility(View.VISIBLE);





        }else {
            tv_edit.setText("修改");
            im_birday.setVisibility(View.INVISIBLE);
            im_huigrade.setVisibility(View.INVISIBLE);
            im_huili_grade_help.setVisibility(View.INVISIBLE);
            im_marry.setVisibility(View.INVISIBLE);
            im_relation.setVisibility(View.INVISIBLE);
            im_time.setVisibility(View.INVISIBLE);
            im_zu.setVisibility(View.INVISIBLE);

            User user = User.getInstance();
            if (user.getType() == User.OLDMAN){

                UserOldman userOldman = new UserOldman();
                userOldman.setId(user.getID());
                userOldman.setName(user.getName());
                userOldman.setPwd(user.getPwd());
                userOldman.setBirth(birthday);
                userOldman.setCareLevel(huiligrade);
                userOldman.setMarry(marry);
                userOldman.setMore("");
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

            }else if (user.getType() == User.HELPPEOPLE){

                Userhelp userhelp = new Userhelp();
                userhelp.setId(user.getID());
                userhelp.setName(user.getName());
                userhelp.setPwd(user.getPwd());
                userhelp.setCareLevel(helprade);
                userhelp.setCareTime(helptime);
                userhelp.setUseInfo(0);

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


            }else if (user.getType() == User.RELATIVE_PEOPLE){

                UserFamily userFamily = new UserFamily();
                userFamily.setId(user.getID());
//                userFamily.setName(name);
                userFamily.setPwd(user.getPwd());
                userFamily.setOlderId(user.getOldmanid());
                userFamily.setRelation(realtion);


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

    private static final int TAKE_PHOTO_PERMISSION_REQUEST_CODE = 0; // 拍照的权限处理返回码
    private static final int WRITE_SDCARD_PERMISSION_REQUEST_CODE = 1; // 读储存卡内容的权限处理返回码

    private static final int TAKE_PHOTO_REQUEST_CODE = 3; // 拍照返回的 requestCode
    private static final int CHOICE_FROM_ALBUM_REQUEST_CODE = 4; // 相册选取返回的 requestCode
    private static final int CROP_PHOTO_REQUEST_CODE = 5; // 裁剪图片返回的 requestCode

    private Uri photoUri = null;
    private Uri photoOutputUri = null; // 图片最终的输出文件的 Uri



    @BindView(R2.id.cim_head)
    CircleImageView cim_head;

    @OnClick(R2.id.cim_head)
    void cim_head(){
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // 申请读写内存卡内容的权限
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_SDCARD_PERMISSION_REQUEST_CODE);
        }

        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //下面是对调用相机拍照权限进行申请
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA,}, TAKE_PHOTO_PERMISSION_REQUEST_CODE);
        } else {
            startCamera();
        }
    }


    /**
     * 拍照
     */
    private void startCamera() {
        /*
         * 设置拍照得到的照片的储存目录，因为我们访问应用的缓存路径并不需要读写内存卡的申请权限，
         * 因此，这里为了方便，将拍照得到的照片存在这个缓存目录中
         */
        File file = new File(getActivity().getExternalCacheDir(), "image.jpg");
        try {
            if(file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
         * 因 Android 7.0 开始，不能使用 file:// 类型的 Uri 访问跨应用文件，否则报异常，
         * 因此我们这里需要使用内容提供器，FileProvider 是 ContentProvider 的一个子类，
         * 我们可以轻松的使用 FileProvider 来在不同程序之间分享数据(相对于 ContentProvider 来说)
         */
        if(Build.VERSION.SDK_INT >= 24) {
            photoUri = FileProvider.getUriForFile(getActivity(), "com.zhi_dian.provider1", file);
        } else {
            photoUri = Uri.fromFile(file); // Android 7.0 以前使用原来的方法来获取文件的 Uri
        }
        // 打开系统相机的 Action，等同于："android.media.action.IMAGE_CAPTURE"
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 设置拍照所得照片的输出目录
        takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        startActivityForResult(takePhotoIntent, TAKE_PHOTO_REQUEST_CODE);
    }

    private void cropPhoto(Uri inputUri) {
        // 调用系统裁剪图片的 Action
        Intent cropPhotoIntent = new Intent("com.android.camera.action.CROP");
        // 设置数据Uri 和类型
        cropPhotoIntent.setDataAndType(inputUri, "image/*");
        // 授权应用读取 Uri，这一步要有，不然裁剪程序会崩溃
        cropPhotoIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        // 设置图片的最终输出目录
        cropPhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                photoOutputUri = Uri.parse("file:////sdcard/image_output.jpg"));
        startActivityForResult(cropPhotoIntent, CROP_PHOTO_REQUEST_CODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            // 通过返回码判断是哪个应用返回的数据
            switch (requestCode) {
                // 拍照
                case TAKE_PHOTO_REQUEST_CODE:
                    cropPhoto(photoUri);
                    break;
                // 相册选择
                case CHOICE_FROM_ALBUM_REQUEST_CODE:
                    cropPhoto(data.getData());
                    break;
                // 裁剪图片
                case CROP_PHOTO_REQUEST_CODE:
                    File file = new File(photoOutputUri.getPath());
                    if(file.exists()) {
                        Bitmap bitmap = BitmapFactory.decodeFile(photoOutputUri.getPath());
                        cim_head.setImageBitmap(bitmap);
                        HashMap<String,String> hashMap = new HashMap<>();
                        hashMap.put("id",User.getINSTANCE().getID()+"");
                        hashMap.put("type",User.getINSTANCE().getType()+"");

                        RestCilent.builder()
                                .url((String) AppCore.getConfigurations().get(ConfigType.API_HOST.name())+"portrait/upload")
                                .PARTMAP(hashMap)
                                .setFile(file)
                                .success(new ISuccess() {
                                    @Override
                                    public void onSuccess(String result) {

                                    }
                                })
                                .error(new IError() {
                                    @Override
                                    public void onError(int id, String mesg) {

                                    }
                                })
                                .build()
                                .postTextAndFile();

//                        file.delete(); // 选取完后删除照片
                    } else {
                        Toast.makeText(getActivity(), "找不到照片", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }




    @BindView(R2.id.et_roomid)
    TextView tv_roomid;

    @BindView(R2.id.tv_grade)
    TextView tv_grade;

    @BindView(R2.id.tv_marry_type)
    TextView tv_marryed;

    @BindView(R2.id.tv_birday_num)
    TextView tv_birthdy;

    @BindView(R2.id.tv_zu_type)
    TextView tv_zu;

    @BindView(R2.id.tv_grade_help)
    TextView tv_grade_help;

    @BindView(R2.id.tv_help_time)
    TextView tv_help_time;

    @BindView(R2.id.tv_id)
    TextView tv_oldman_id;

    @BindView(R2.id.tv_relation)
    TextView tv_relation;


    @BindView(R2.id.im_huiligrade)
    ImageView im_huigrade;
    @OnClick(R2.id.im_huiligrade)
    void huiligrade(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity())
                .setSingleChoiceItems(R.array.hu_li_grade, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String[] array = getResources().getStringArray(R.array.hu_li_grade);
                        String result = array[i];
                        String s = result.split("\\s+")[1];
                        tv_grade.setText(s);
                        huiligrade = Integer.valueOf(s);
                    }
                });
        alertDialog.show();
    }


    @BindView(R2.id.im_marry)
    ImageView im_marry;
    @OnClick(R2.id.im_marry)
    void marry(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity())
                .setSingleChoiceItems(R.array.marry, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String[] array = getResources().getStringArray(R.array.marry);
                        String result = array[i];
                        String s = result.split("\\s+")[1];
                        tv_marryed.setText(s);
                        marry = Integer.valueOf(s);
                    }
                });
        alertDialog.show();
    }

    DatePickerDialog mDatePickerDialog;
    @BindView(R2.id.im_birday)
    ImageView im_birday;
    @OnClick(R2.id.im_birday)
    void birth(){
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        final int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH);
        final int day = mCalendar.get(Calendar.DAY_OF_MONTH);
        mDatePickerDialog  =new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String date = FragmentRegister.getDate(i,i1,i2);
                tv_birthdy.setText(date);
                birthday = date;
            }
        },year,month,day);
        mDatePickerDialog.show();
    }

    @BindView(R2.id.im_zu)
    ImageView im_zu;
    @OnClick(R2.id.im_zu)
    void zu(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity())
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



    @BindView(R2.id.im_huiligrade_help)
    ImageView im_huili_grade_help;
    @OnClick(R2.id.im_huiligrade_help)
    void help(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity())
                .setSingleChoiceItems(R.array.hu_li_help_grade, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String[] array = getResources().getStringArray(R.array.hu_li_help_grade);
                        String result = array[i];
                        String s = result.split("\\s+")[1];
                        tv_grade_help.setText(s);
                        huiligrade = Integer.valueOf(s);
                    }
                });
        alertDialog.show();
    }


    @BindView(R2.id.im_time)
    ImageView im_time;
    @OnClick(R2.id.im_time)
    void time(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity())
                .setSingleChoiceItems(R.array.hu_li_help_richen, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String[] array = getResources().getStringArray(R.array.hu_li_help_richen);
                        String result = array[i];
                        String s = result.split("\\s+")[1];
                        tv_help_time.setText(s);
                        helptime = Integer.valueOf(s);
                    }
                });
        alertDialog.show();
    }

    @BindView(R2.id.im_relation)
    ImageView im_relation;
    @OnClick(R2.id.im_relation)
    void relation(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity())
                .setSingleChoiceItems(R.array.huli_relative, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String[] array = getResources().getStringArray(R.array.huli_relative);
                        String result = array[i];
                        String s = result.split("\\s+")[1];
                        tv_relation.setText(s);
                        realtion = Integer.valueOf(s);
                    }
                });
        alertDialog.show();
    }



    @BindView(R2.id.ll_oldpeople)
    LinearLayout ll_old;


    @BindView(R2.id.ll_helppeople)
    LinearLayout ll_help;

    @BindView(R2.id.ll_relativepeolpe)
    LinearLayout ll_relative;


    @Override
    public void bindview(Bundle savedInstance, View rootview) {
        init();
        User user = User.getInstance();
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
                            Glide.with(getActivity()).load(url).into(cim_head);
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

    private void init(){

        User user = User.getInstance();
        ll_help.setVisibility(View.GONE);
        ll_old.setVisibility(View.GONE);
        ll_relative.setVisibility(View.GONE);

        if (user.getType() == User.OLDMAN){
            ll_old.setVisibility(View.VISIBLE);
            tv_id_user.setText(user.getOldmanid()+"");
            tv_name.setText(user.getNameold());
            tv_roomid.setText(user.getRoomid()+"");

            String[] grade = getResources().getStringArray(R.array.hu_li_grade);
            tv_grade.setText(grade[user.getCarelevel()].split("\\s+")[1]);

            String[] marry = getResources().getStringArray(R.array.marry);
            tv_marryed.setText(marry[user.getMarry()].split("\\s+")[1]);
            tv_birthdy.setText(user.getBirth());
            String[] zu = getResources().getStringArray(R.array.zu);
            tv_zu.setText(zu[user.getZu()].split("\\s+")[1]);

        }else if (user.getType() == User.HELPPEOPLE){
            ll_help.setVisibility(View.VISIBLE);
            tv_id_user.setText(user.getHelpid()+"");
            tv_name.setText(user.getNamehelp());


            String[] gradeHelp = getResources().getStringArray(R.array.hu_li_help_grade);
            tv_grade_help.setText(gradeHelp[user.getCarelevelhelp()].split("\\s+")[1]);

            String[] helptime = getResources().getStringArray(R.array.hu_li_help_richen);
            tv_help_time.setText(helptime[user.getCaretimehelp()].split("\\s+")[1]);



        }else if (user.getType() == User.RELATIVE_PEOPLE){
            ll_relative.setVisibility(View.VISIBLE);
            tv_id_user.setText(user.getRelativeid()+"");
            tv_name.setText(user.getNamefamily());

            String[] relation = getResources().getStringArray(R.array.huli_relative);
            tv_relation.setText(relation[user.getRelation()].split("\\s+")[1]);
        }
    }


    @Override
    public Object setlayout() {
        return R.layout.frament_me;
    }
}
