/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.ian.justjava;



         import android.content.Intent;
         import android.net.Uri;
         import android.os.Bundle;
         import android.support.v7.app.AppCompatActivity;
         import android.util.Log;
         import android.view.View;
         import android.widget.CheckBox;
         import android.widget.EditText;
         import android.widget.TextView;
         import android.widget.Toast;
         import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     *
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean whippedCreamBoxChecked = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean chocolateBox = chocolateCheckBox.isChecked();

        EditText namesText = (EditText) findViewById(R.id.nameView);
        String names = namesText.getText().toString();

        int price = calculatePrice(whippedCreamBoxChecked, chocolateBox);
        String priceMessage = createOrderSummary(price, whippedCreamBoxChecked, chocolateBox, names);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));  //can be handled by only email apps
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order for: " + names);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) !=null){
            startActivity(intent);
        }

        Toast.makeText(this, "Button Clicked!!", Toast.LENGTH_SHORT).show();

    }

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate){
        int basePrice = 5;
        if(addWhippedCream){
            basePrice = basePrice + 1;
        }

        if(addChocolate){
            basePrice = basePrice + 2;
        }

        int price = quantity * basePrice;
        return  price;
    }

    /**
     * method for create summary order
     * @param price
     * @return priceMessage
     */
    private String createOrderSummary(int price, boolean whippedCream, boolean chocolate, String userName){
        String priceMessage = userName;
        priceMessage = priceMessage + "\n" + "Whipped Cream Checked:? " + whippedCream;
        priceMessage = priceMessage + "\n" + "Chocolate Checked:? " + chocolate;
        priceMessage = priceMessage + "\n" + "Quantity: " + quantity;
        priceMessage = priceMessage + "\n" + "Total: $" + price;
        priceMessage = priceMessage + "\n" + "Thank You";
        Toast.makeText(this, "Button Clicked!!", Toast.LENGTH_SHORT).show();
        return priceMessage;


    }

    /**
     * This method is called when the + button is clicked.
     */
    public void incrementMethod(View view){
        if (quantity <= 100){
            quantity = quantity + 1;
            displayQuantity(quantity);
        } else {
            Toast.makeText(this, "Invalid number of coffee. Too much", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    /**
     * This method is called when the - button is clicked
     */
    public void decrementMethod(View view){
        if (quantity > 0){
            quantity = quantity - 1;
            displayQuantity(quantity);
        } else {
            Toast.makeText(this, "Invalid number of coffee", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberRenamed) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberRenamed);
    }


}