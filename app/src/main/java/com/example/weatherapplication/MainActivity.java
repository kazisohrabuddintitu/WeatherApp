package com.example.weatherapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static TextView place;
    static TextView temp;
    static TextView details;
    static TextView humidityHere;
    static TextView country;
    static TextView windHere;
    private EditText city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        place=(TextView) findViewById(R.id.place);
        temp=(TextView) findViewById(R.id.temp);
        details=(TextView) findViewById(R.id.details);
        city = (EditText)findViewById(R.id.etNewCity);
        humidityHere=(TextView)findViewById(R.id.humidityHere);
        country=(TextView)findViewById(R.id.country);
        windHere=(TextView)findViewById(R.id.windHere);
        Button resetBtn=(Button) findViewById(R.id.resetBtn);

        Weather getData=new Weather();
        getData.execute("https://api.openweathermap.org/data/2.5/" + "forecast?q=Dhaka&units=metric&appid={your-token}");



        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!city.getText().toString().isEmpty()) {
                    String cityName=city.getText().toString();
                    Toast toast=Toast.makeText(MainActivity.this, "Showing Weather Report", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                    temp.setText("");
                    place.setBackgroundResource(R.drawable.gradient);
                    humidityHere.setText("Every 3 Hours");
                    windHere.setText("Every 3 hours");
                    details.setVisibility(View.VISIBLE);
                    closeKeyboard();
                    Weather getData=new Weather();
                    getData.execute("https://api.openweathermap.org/data/2.5/" + "forecast?q="+cityName+"&units=metric&appid=bcb3bda9a7b598abac00428bbd4956a1");
                }

                else{
                    city.setError("Enter City name");

                }

            }
        });

    }
    private void closeKeyboard()
    {
        View view = this.getCurrentFocus();

        if (view != null) {
            InputMethodManager manager
                    = (InputMethodManager)
                    getSystemService(
                            Context.INPUT_METHOD_SERVICE);
            manager
                    .hideSoftInputFromWindow(
                            view.getWindowToken(), 0);
        }
    }

}