package com.example.android.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
        int baseprice=5;
        int quantity=2;  //Global variable
        boolean z=false;
        boolean n=false;
        String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        if(z==true)
            baseprice=baseprice+1;
        if(n==true)
            baseprice=baseprice+2;
        int price=baseprice*quantity;
        EditText ed=(EditText)findViewById(R.id.plain_text_input);
        name=ed.getText().toString();
        //displayMessage(createOrderSummary(price));
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("plain/text");
        intent.setData(Uri.parse("mailto:"));
        //intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "just Java App");
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(price));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
            }
    /* This method is used to increment quantity*/
    public void increment(View view){
        TextView mp=(TextView) findViewById(R.id.quantity_text_view);
        int p=Integer.parseInt(mp.getText().toString());
        if(p<100) {
            quantity = quantity + 1;
            display(quantity);
        }
        else {
            Toast.makeText(getApplicationContext(), "No. of coffee cannot exceed to 100",
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void decrement(View view){
        TextView mp=(TextView) findViewById(R.id.quantity_text_view);
        int p=Integer.parseInt(mp.getText().toString());
        if(p>1) {
            quantity = quantity - 1;
            display(quantity);
        }
        else {
            Toast.makeText(getApplicationContext(), "No. of coffee cannot less than 0",
                    Toast.LENGTH_SHORT).show();
        }    }
    /**
     * This method displays the given text on the screen.
     */
    private String createOrderSummary(int p)
    {
        return "Name: " +name+ "\n" +
                "Add wipped Cream?" +z+"\n"+
                "Add Choclate?"+n+"\n"+
                "Quantity:"+baseprice+"\n " +
                "Total:"+p+"\n " +
                "Thank You!";
    }
    private void displayMessage(String message)
    {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
   public void itemChecked(View view)
   {
       CheckBox checkitem = (CheckBox) findViewById(R.id.Whip_cream);
       if(checkitem.isChecked()){
           z=true;
   }
   else
    z=false;
   }
   public void choclatecheck(View view)
   {
       CheckBox checkitem=(CheckBox) findViewById(R.id.Choclate);
       if(checkitem.isChecked()){
           n=true;
   }
   else
       n=false;
   }

    /**
     * This method displays the given price on the screen.
     */
   /* private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    */
}