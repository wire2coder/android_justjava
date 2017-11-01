package com.example.android.justjava;

import android.icu.text.NumberFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/*
* This app displays an order form to display coffee
* */

public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    // you need this to render the 'activity_main.xml'
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    * This method is called when the 'order button' is clicked
    * why do you need to pass in 'View'????
    * */
    public void submitOrder(View view) {

        int price = (quantity * 5);

        String priceMessage = "Amount due $ " + price;
        String priceM2 = "That would be $" + price + " please. \n Thank you!";
        String priceM3 = quantity + " dollars for " + price + " of coffee. Pay up.";
        String priceM4 = "Total: " + (quantity*5);

        displayMessage(priceM2);

    }

    public void increment(View view) {
        quantity += 1;

        displayQuantity(quantity);
    }

    public void decrement(View view) {
        quantity -= 1;

        displayQuantity(quantity);
    }

    /* This method display message on id price_text_view */
    private void displayMessage(String message) {
        // get the id for price_text_view
        TextView v1 = (TextView) findViewById(R.id.price_text_view);
        v1.setText(message);
    }

    /*
    * This method displays the price of coffee on the screen
    * */
    private void displayPrice(int number) {
        // we are converting 'id' into TextView type
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText( NumberFormat.getCurrencyInstance().format(number) );
        // so you need an empty string inside the method
        // if you only use 'number' the program will crashed.
//        priceTextView.setText("" + number);
    }



    /*
    * This method displays the given quantity on the screen
    * */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

}