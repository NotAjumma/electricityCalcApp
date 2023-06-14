package com.example.basiclab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import android.text.style.URLSpan;
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnSubmit, btnClear;
    EditText etKwh, etRebate;
    TextView tvOutputCharge,tvOutputRebate,tvOutputBill;
    String stringKwh, stringRebate;
    double kwh, rebate, beforeRebate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnClear = findViewById(R.id.btnClear);
        etKwh = findViewById(R.id.etKwh);
        etKwh.setInputType(InputType.TYPE_CLASS_NUMBER);
        etRebate = findViewById(R.id.etRebate);
        etRebate.setInputType(InputType.TYPE_CLASS_NUMBER);
        btnSubmit.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        tvOutputCharge = findViewById((R.id.tvOutputCharge));
        tvOutputRebate = findViewById((R.id.tvOutputRebate));
        tvOutputBill = findViewById((R.id.tvOutputBill));
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate (R.menu.menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){

            case R.id.about :

                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;

            case R.id.instruction:

                intent = new Intent(this, InstructionActivity.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }





    @Override
    public void onClick(View view){
        if (view == btnSubmit){
            stringKwh = etKwh.getText().toString();
            stringRebate = etRebate.getText().toString();
            double totalCharge, totalBill, totalRebate, tempKwh, tempKwh2 ;
            kwh = 0.0;
            beforeRebate = 0.0;


            try{
                kwh = Double.parseDouble(stringKwh);
                beforeRebate = Double.parseDouble(stringRebate);
            }catch (Exception e){
                Toast.makeText(this,"Please enter a valid number for kWh and rebate",Toast.LENGTH_SHORT).show();
                return;
            }

            rebate = beforeRebate / 100.0;
            DecimalFormat decimalFormat = new DecimalFormat("#0.000");
            DecimalFormat decimalFormatTwo = new DecimalFormat("#0.00");


            if (kwh <= 200){
                totalCharge = kwh * 0.218;
                totalRebate = totalCharge * rebate;
                totalBill = totalCharge - totalRebate;
                String formattedTotalCharge = decimalFormat.format(totalCharge);
                String formattedTotalRebate = decimalFormat.format(totalRebate);
                String formattedTotalBill = decimalFormatTwo.format(totalBill);
                tvOutputCharge.setText("RM " + formattedTotalCharge);
                tvOutputRebate.setText("RM " + formattedTotalRebate);
                tvOutputBill.setText("RM " + formattedTotalBill);
            } else if (kwh >= 201 && kwh <= 300) {
                tempKwh = kwh-200;
                totalCharge = (200 * 0.218) + (tempKwh * 0.334);
                totalRebate = totalCharge * rebate;
                totalBill = totalCharge - totalRebate;
                String formattedTotalCharge = decimalFormat.format(totalCharge);
                String formattedTotalRebate = decimalFormat.format(totalRebate);
                String formattedTotalBill = decimalFormatTwo.format(totalBill);
                tvOutputCharge.setText("RM " + formattedTotalCharge);
                tvOutputRebate.setText("RM " + formattedTotalRebate);
                tvOutputBill.setText("RM " + formattedTotalBill);
            } else if (kwh >= 301) {
                tempKwh = kwh-200;
                tempKwh2 = tempKwh-100;
                totalCharge = (200 * 0.218) + (tempKwh * 0.334) + (tempKwh2 * 0.516);
                totalRebate = totalCharge * rebate;
                totalBill = totalCharge - totalRebate;
                String formattedTotalCharge = decimalFormat.format(totalCharge);
                String formattedTotalRebate = decimalFormat.format(totalRebate);
                String formattedTotalBill = decimalFormatTwo.format(totalBill);
                tvOutputCharge.setText("RM " + formattedTotalCharge);
                tvOutputRebate.setText("RM " + formattedTotalRebate);
                tvOutputBill.setText("RM " + formattedTotalBill);
            }
//            Toast.makeText(this, "I Love Aimie "+miles+"!", Toast.LENGTH_SHORT).show();
        }else if (view==btnClear){
            etKwh.setText(""); // Clear the kwh input field
            etRebate.setText(""); // Clear the rebate input field
            tvOutputCharge.setText(""); // Clear the charge output field
            tvOutputRebate.setText(""); // Clear the rebate output field
            tvOutputBill.setText(""); // Clear the bill output field
        }
    }
}