package com.example.android.justjava;

import android.content.Context;
import android.content.Intent;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/*
* This app displays an order form to display coffee
* */

public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    String message = "";

    // you need this to render the 'activity_main.xml'
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    * Calculate the price of the order
    * @param quantity is the number of coffee ordered
    * */
    private int calculatePrice(int quantity, boolean addWhipCream, boolean addChocolate) {

        int basePrice = 5;

        if (addWhipCream) {
            basePrice += 1;
        }

        if (addChocolate) {
            basePrice += 2;
        }

         return quantity * basePrice;

    }

    /*
    * This method is called when the 'order button' is clicked
    * why do you need to pass in 'View'????
    * */
    public void submitOrder(View view) {

        EditText customer_name = (EditText) findViewById(R.id.name_for_order);
        String name1 = customer_name.getText().toString();

        CheckBox topping_whip_cream = (CheckBox) findViewById(R.id.topping_whip_cream);
        boolean whippedCreamValue = topping_whip_cream.isChecked();

        CheckBox topping_chocolate = (CheckBox) findViewById(R.id.topping_chocolate);
        boolean chocolateValue = topping_chocolate.isChecked();

        int price = calculatePrice(quantity, whippedCreamValue, chocolateValue);
        String priceMessage = "Total: $" + price;
        priceMessage = priceMessage + "\nThank you!";

        message = createOrderSummary(name1, price, whippedCreamValue, chocolateValue);
//        displayMessage(message);

        composeEmail(name1, message);

    }

    // intent to show a map
    public void showMap() {
        // throwing a ball to a map program
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:47.6, -122.3"));

        // making sure, there is a program that can use a map
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    // intent to email a coffee order
    public void composeEmail(String name, String message) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void showToastZero() {
        Context context = getApplicationContext();
        CharSequence text = "Quantity must be greater than 1";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void showToastHundred() {
        Context context = getApplicationContext();
        CharSequence text = "Quantity must be less than 100";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public String createOrderSummary(String name, int price, boolean whippedCream, boolean chocolateValue) {

        message = name;
        message += "\nAdd Whipped Cream? " + whippedCream;
        message += "\nAdd Chocolate? " + chocolateValue;
        message += "\nQuantity: " + quantity;
        message += "\nTotal: $" + price;
        message += "\nThank you!";

        return message;
    }

    public void increment(View view) {
        if (quantity == 100) {
            showToastHundred();
            return;
        }

        quantity += 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1) {
            showToastZero();
            return;
        }

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
