package sg.edu.rp.c346.id20001695.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etPhone;
    EditText etPax;
    DatePicker dp;
    TimePicker tp;
    CheckBox cb;
    Button btnConfirm;
    Button btnReset;
    TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextPersonName);
        etPhone = findViewById(R.id.editTextPhone);
        etPax = findViewById(R.id.editTextNumber);
        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        cb = findViewById(R.id.checkBoxSmoking);
        btnConfirm = findViewById(R.id.buttonConfirm);
        btnReset = findViewById(R.id.buttonReset);
        tvDisplay = findViewById(R.id.textViewDisplay);

        dp.updateDate(2020,  7 - 1, 1);
        tp.setIs24HourView(true);


        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etName.getText().toString().trim().length() != 0 && etPhone.getText().toString().trim().length() != 0 && etPax.getText().toString().trim().length() != 0) {
                    if (cb.isChecked()) {
                        String output = "Reservation made on " + dp.getDayOfMonth() + "/" + (dp.getMonth() + 1) + " at " + tp.getCurrentHour() + ":" + tp.getCurrentMinute()
                                + " by " + etName.getText() + ", " + etPhone.getText() + " for a table of " + etPax.getText() + " in smoking area";
                        tvDisplay.setText(output);
                    } else {
                        String output = "Reservation made on " + dp.getDayOfMonth() + "/" + (dp.getMonth() + 1) + " at " + tp.getCurrentHour() + ":" + tp.getCurrentMinute()
                                + " by " + etName.getText() + ", " + etPhone.getText() + " for a table of " + etPax.getText() + " in non-smoking area";
                        tvDisplay.setText(output);
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "You have not entered all the fields", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);
                dp.updateDate(2020, 5, 1);
                tvDisplay.setText("");
                etName.setText("");
                etPhone.setText("");
                etPax.setText("");
                cb.setChecked(false);
            }
        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

                if(hourOfDay <= 8) {
                    tp.setCurrentHour(8);
                    tp.setCurrentMinute(0);
                }
                else if(hourOfDay >= 21) {
                    tp.setCurrentHour(20);
                    tp.setCurrentMinute(59);
                }
            }
        });
    }
}