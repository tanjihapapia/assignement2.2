package com.example.assignement22;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RadioButton radioHomeDelivery, radioPickup;
    private CheckBox checkboxGiftWrap, checkboxExpressDelivery;
    private SeekBar quantitySeekBar;
    private RatingBar ratingBar;
    private TextView quantityValue;
    private Button submitOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        radioHomeDelivery = findViewById(R.id.radioHomeDelivery);
        radioPickup = findViewById(R.id.radioPickup);
        checkboxGiftWrap = findViewById(R.id.checkboxGiftWrap);
        checkboxExpressDelivery = findViewById(R.id.checkboxExpressDelivery);
        quantitySeekBar = findViewById(R.id.quantitySeekBar);
        ratingBar = findViewById(R.id.ratingBar);
        quantityValue = findViewById(R.id.quantityValue);
        submitOrderButton = findViewById(R.id.submitOrderButton);

        // Set quantity seek bar listener
        quantitySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                quantityValue.setText("Quantity: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        // Submit button action
        submitOrderButton.setOnClickListener(view -> {
            String selectedDeliveryMethod = radioHomeDelivery.isChecked() ? "Home Delivery" : "Pickup";
            boolean isGiftWrapped = checkboxGiftWrap.isChecked();
            boolean isExpressDelivery = checkboxExpressDelivery.isChecked();
            int selectedQuantity = quantitySeekBar.getProgress();
            float selectedRating = ratingBar.getRating();

            // Build the order summary string
            String orderSummary = "Delivery Method: " + selectedDeliveryMethod + "\n" +
                    "Gift Wrap: " + (isGiftWrapped ? "Yes" : "No") + "\n" +
                    "Express Delivery: " + (isExpressDelivery ? "Yes" : "No") + "\n" +
                    "Quantity: " + selectedQuantity + "\n" +
                    "Rating: " + selectedRating;

            // Show the order summary dialog
            showOrderSummaryDialog(orderSummary);
        });
    }

    // Method to show the order summary in a dialog
    private void showOrderSummaryDialog(String orderSummary) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Order Summary");
        builder.setMessage(orderSummary);

        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
