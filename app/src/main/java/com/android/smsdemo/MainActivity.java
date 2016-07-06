package com.android.smsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class MainActivity extends AppCompatActivity {

    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerBtn = (Button) findViewById(R.id.register);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * 初始化SDK
                 */
                String appKey = "14a0a7a14c1fc";
                String appSecrete = "8c12046be7ec35cf47f46cf3c4d6ee15";
                SMSSDK.initSDK(MainActivity.this,appKey,appSecrete);

                /**
                 * 打开注册界面
                 */
                RegisterPage registerPage = new RegisterPage();
                registerPage.setRegisterCallback(new EventHandler(){
                    /**
                     * 解析注册结果
                     */
                    @Override
                    public void afterEvent(int event, int result, Object data) {
                        if(result == SMSSDK.RESULT_COMPLETE){
                            HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                            String country = (String) phoneMap.get("country");
                            String phone = (String) phoneMap.get("phone");

                            /**
                             * 提交注册信息
                             */
                            SMSSDK.submitUserInfo(null,null,null,country,phone);

                        }
                    }

                });

                /**
                 * 显示注册页面
                 */
                registerPage.show(MainActivity.this);

            }
        });
    }



}
