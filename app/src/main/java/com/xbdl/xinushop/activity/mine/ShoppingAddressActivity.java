package com.xbdl.xinushop.activity.mine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xbdl.xinushop.R;

public class ShoppingAddressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_address);
        initView();
    }

    private void initView() {
        findViewById(R.id.ll_add_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingAddressActivity.this, AddAddressActivity.class);
                startActivity(intent);
            }
        });
    }
}
