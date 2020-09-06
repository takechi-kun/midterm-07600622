package th.ac.su.cp.speedcalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import th.ac.su.cp.speedcalculator.check.Check;

public class MainActivity extends AppCompatActivity {
    Check c;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c = new Check();

        final Button calButton = findViewById(R.id.cal_button);
        calButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText TextMeter = findViewById(R.id.text_meter);
                String text_meter = TextMeter.getText().toString();
                EditText TextSecond = findViewById(R.id.text_second);
                String text_second = TextSecond.getText().toString();
                Check.ResultCheck result = c.check(text_meter,text_second);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                TextView resultTextView = findViewById(R.id.show_speed);
                Toast t;
                switch (result) {
                    case pleaseInsert:
                        t = Toast.makeText(MainActivity.this, R.string.insert_number, Toast.LENGTH_LONG);
                        t.show();
                        break;
                    case nonzero:
                        t = Toast.makeText(MainActivity.this, R.string.more_zero, Toast.LENGTH_LONG);
                        t.show();
                        break;
                    case speedover:
                        dialog.setTitle("SPEED CALCULATOR");
                        dialog.setMessage(R.string.speed_over);
                        dialog.setPositiveButton("OK", null);
                        dialog.show();
                        break;
                    case speeddefault:
                        double myDouble1 = Double.parseDouble(text_meter);
                        double myDouble2 = Double.parseDouble(text_second);
                        double calculate  =  ((myDouble1/myDouble2)*18)/5;
                        String str = String.format(
                                Locale.getDefault(), "%.2f", calculate
                        );
                        resultTextView.setText(str);
                        break;
                }

            }
        });
        Button clearButton = findViewById(R.id.clear_button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText TextMeter = findViewById(R.id.text_meter);
                EditText TextSecond = findViewById(R.id.text_second);
                TextView resultTextView = findViewById(R.id.show_speed);
                TextMeter.setText("");
                TextSecond.setText("");
                resultTextView.setText("");
            }
        });
    }
}