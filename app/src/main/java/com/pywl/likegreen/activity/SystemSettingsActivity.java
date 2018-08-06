package com.pywl.likegreen.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.pywl.likegreen.R;
import com.pywl.likegreen.view.ToPRomoteDialog;

public class SystemSettingsActivity extends AppCompatActivity implements View.OnClickListener {
   private  View mMyToPromote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_settings);
        initView();
        initData();
    }



    private void initView() {
        //我要推广
        mMyToPromote = findViewById(R.id.my_to_promote);
        mMyToPromote.setOnClickListener(this);
    }
    private void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.my_to_promote:
                showDialog();
                break;
        }
    }
    private void showDialog(){
        ToPRomoteDialog toPRomoteDialog = new ToPRomoteDialog(this);
        toPRomoteDialog.show();
    }
}
