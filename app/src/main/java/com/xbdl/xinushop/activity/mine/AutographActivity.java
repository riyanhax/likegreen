package com.xbdl.xinushop.activity.mine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.xbdl.xinushop.R;
/*
* 个性签名
* */
public class AutographActivity extends AppCompatActivity implements View.OnClickListener {
    private  int personalDataActivity;
    private TextView title;
    private EditText inputText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autograph);
        Intent intent = getIntent();
        personalDataActivity= intent.getIntExtra("PersonalDataActivity", 0);
        initView();
        initData();
    }



    private void initView() {
        title= (TextView)findViewById(R.id.tv_title);
        findViewById(R.id.tv_commit).setOnClickListener(this);
        findViewById(R.id.iv_return).setOnClickListener(this);
        inputText=(EditText) findViewById(R.id.et_inputtext);

    }
    private void initData() {
        if (personalDataActivity==0){
            //个性签名
            title.setText(R.string.sagnetrue);
            inputText.setHint("  个性签名头脑风暴快快来临");
        }else {
            //用户名
            title.setText(R.string.nichen);
            inputText.setHint("请设置用户名");
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_return:
                finish();
                break;
            case R.id.tv_commit://保存
                String inputMsg= inputText.getText().toString();
                if (inputMsg!=null){
                    Intent intent=new Intent();
                    if (personalDataActivity==0){
                        intent.putExtra("sagnetrue",inputMsg);
                    }else {
                        intent.putExtra("nichen",inputMsg);
                    }

                    setResult(RESULT_OK,intent);
                    finish();
                }
                break;
        }
    }
}
