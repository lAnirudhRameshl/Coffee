/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int Price=calculatePrice();
        String quantityDisplay="please pay ₹ " + Price;
        createOrderSummary(Price);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void increment(View view) {
        quantity=quantity+1;
        display(quantity);
    }

    public void decrement(View view){
        if(quantity>0) {
            quantity = quantity - 1;
            display(quantity);
        }
        if(quantity==0){
            display(quantity);
        }
    }

    /**
     * This method is to calculate the price
     */
    private int calculatePrice(){
        int price = quantity * 20;
        CheckBox hasWhippedCream=(CheckBox) findViewById(R.id.select_whipped_cream);
        CheckBox hasChocolate= (CheckBox) findViewById(R.id.select_chocolate);
        if(hasWhippedCream.isChecked())
            price+= quantity * 3;
        if(hasChocolate.isChecked())
            price+= quantity * 5;
        return price;
    }

    /**
     * This method displays order summary
     */
    private void createOrderSummary(int price){
        CheckBox hasWhippedCream=(CheckBox) findViewById(R.id.select_whipped_cream);
        CheckBox hasChocolate= (CheckBox) findViewById(R.id.select_chocolate);
        EditText name= (EditText) findViewById(R.id.get_name);
        String message="Name: "+ name.getText();
        if(hasWhippedCream.isChecked()) {
            message+="\nWhipped cream - ₹" + quantity * 3;
        }
        else {
            message+="\nWhipped cream - ₹" + 0;
        }
        if(hasChocolate.isChecked()) {
            message+="\nChocolate - ₹" + quantity * 5;
        }
        else{
            message+="\nChocolate - ₹" + 0;
        }
        message+="\nQuantity: " + quantity;
        message+="\nTotal: ₹" + price;
        message+="\nThank you!!!!";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"Order summary");
        intent.putExtra(Intent.EXTRA_TEXT,message);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

}