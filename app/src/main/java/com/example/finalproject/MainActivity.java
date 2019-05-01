package com.example.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;



public class MainActivity extends AppCompatActivity {
    private final String TAG = "ExchangeRate";

    private RequestQueue requestQueue;

    private String countryConversion;

    private JSONObject exchangeRate;

    private TextView input;

    private TextView output;

    private double currency;

    private double conversion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner mySpinner1 = findViewById(R.id.spinner1);
        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner1.setAdapter(myAdapter1);

        requestQueue = Volley.newRequestQueue(this);

        input = findViewById(R.id.Input);

        output = findViewById(R.id.Output);

        final Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            countryConversion = mySpinner1.getSelectedItem().toString();
            Log.d(TAG, "conversion");
            startAPICall(countryConversion);
            if (input == null) {
                Log.d(TAG, "Try Again");
            }
            conversion = Double.parseDouble(input.getText().toString());
        });
    }
    void startAPICall(String value) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://free.currencyconverterapi.com/api/v6/convert?q=USD_"+countryConversion+"&compact=ultra&apiKey=052dfd7a3ad1bb7867a9",
                    null,
                    response -> {
                        Log.d(TAG, response.toString());
                        try {
                            //Log.d(TAG, exchangeRate.toString());
                            currency = response.getDouble("USD_" + value);
                            Log.d(TAG, String.valueOf(exchangeRate));
                            output.setText(String.valueOf(currency * conversion));
                        } catch (JSONException e) {
                            Log.e(TAG, "Error");
                        }
                    }, error -> Log.w(TAG, error.toString()));
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
