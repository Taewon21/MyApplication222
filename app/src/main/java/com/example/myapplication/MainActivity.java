package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //위젯하고 xml
    ImageView img_product_main;
    EditText edit_count;
    TextView txt_price , txt_delivery,txt_pay;
    CheckBox chk_agree;
    //계산에 필요한 변수 선언
    int val_price;
    int val_delivery;
    int val_pay;

    int selected_price =1500;
    int selected_count =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img_product_main = findViewById(R.id.img_product_main);
        edit_count = findViewById(R.id.edit_count);
        txt_price = findViewById(R.id.txt_price);
        txt_pay = findViewById(R.id.txt_pay);
        txt_delivery = findViewById(R.id.txt_delivery);
        chk_agree = findViewById(R.id.chk_agree);

        findViewById(R.id.radio1).setOnClickListener(this);
        findViewById(R.id.radio2).setOnClickListener(this);
        findViewById(R.id.radio3).setOnClickListener(this);
        findViewById(R.id.btn_minus).setOnClickListener(this);
        findViewById(R.id.btn_plus).setOnClickListener(this);
        findViewById(R.id.btn_pay).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this,"클릭",Toast.LENGTH_LONG).show();
        int count;
        switch (v.getId()){
            case R.id.radio1:
                img_product_main.setImageResource(R.drawable.product1);
                selected_price =1500;
                sumTotal();
                break;
            case R.id.radio2:
                img_product_main.setImageResource(R.drawable.product2);
                selected_price =2000;
                sumTotal();
                break;
            case R.id.radio3:
                img_product_main.setImageResource(R.drawable.product3);
                selected_price =3000;
                sumTotal();
                break;

            case R.id.btn_plus:
                count =Integer.parseInt(edit_count.getText().toString());
                if(count == 99){
                    Toast.makeText(this,"최대 수량은 100개를 넘기지 못합니다",Toast.LENGTH_LONG).show();
                }else{
                    ++count;
                    edit_count.setText(Integer.toString(count));
                }
                sumTotal();
                break;
            case R.id.btn_minus:
                count =Integer.parseInt(edit_count.getText().toString());
                if(count==1){
                    Toast.makeText(this, "최소 수량은 1개입니다",Toast.LENGTH_LONG).show();
                }else{
                    --count;
                    edit_count.setText(Integer.toString(count));
                }
                sumTotal();
                break;
            case R.id.btn_pay:
                if (chk_agree.isChecked()){
                    Toast.makeText(this,val_pay+"을 결제 하겠습니다",Toast.LENGTH_LONG).show();
                    Intent intent =new Intent(MainActivity.this,SubActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this,"결제 동의에 체크하세요",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void sumTotal() {
        //상품 수량
        selected_count = Integer.parseInt(edit_count.getText().toString());

        //단가 * 수량
        val_price = selected_price * selected_count;

        if(val_price>=10000){
            val_delivery =0;
        }else{
            val_delivery=2500;
        }
        val_pay = val_price+val_delivery;

        txt_price.setText(val_price+"원");
        txt_delivery.setText(val_delivery+"원");
        txt_pay.setText(val_pay+"원");

    }
}