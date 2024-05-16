package com.example.expensify;

import static java.util.regex.Pattern.matches;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import org.junit.Rule;
import org.junit.Test;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText editTextDate;
    EditText editTextExpenseName;
    EditText editTextExpenseAmount;
    EditText editTextLocation;
    Spinner spinnerExpenseCategory;
    SwitchCompat switchExpensePaid;
    Button buttonAddExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Ánh xạ các thành phần trong layout
        editTextDate = findViewById(R.id.editTextDate);
        editTextExpenseName = findViewById(R.id.editTextExpenseName);
        editTextExpenseAmount = findViewById(R.id.editTextExpenseAmount);
        spinnerExpenseCategory = findViewById(R.id.spinnerExpenseCategory);
        editTextLocation = findViewById(R.id.editTextLocation);
        switchExpensePaid = findViewById(R.id.switchExpensePaid);
        buttonAddExpense = findViewById(R.id.buttonAddExpense);
        // Đặt sự kiện click cho nút "Thêm"
        buttonAddExpense.setOnClickListener(view -> {
            // Lấy thông tin từ các trường nhập liệu
            String date = editTextDate.getText().toString();
            String expenseName = editTextExpenseName.getText().toString();
            String expenseAmount = editTextExpenseAmount.getText().toString();
            String expenseLocation = editTextLocation.getText().toString();
            String expenseCategory = spinnerExpenseCategory.getSelectedItem().toString();
            boolean expensePaid = switchExpensePaid.isChecked();

            // Xử lý dữ liệu theo nhu cầu của ứng dụng
            // (ví dụ: lưu vào cơ sở dữ liệu, thực hiện các thao tác khác)
            if (expenseAmount.isEmpty() || date.isEmpty() || expenseLocation.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                // Hiển thị thông báo hoặc thực hiện các hành động khác
                Toast.makeText(MainActivity.this, "Đã thêm khoản chi phí: " + expenseName+ "tại" + expenseLocation, Toast.LENGTH_SHORT).show();
            }

        });

        ImageButton buttonSelectDate = findViewById(R.id.buttonSelectDate);
        buttonSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                                editTextDate.setText(selectedDate);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        public class AddExpenseActivityTest {
            @Rule
            public ActivityScenarioRule<MainActivity> activityRule =
                    new ActivityScenarioRule<>(MainActivity.class);

            @
            public void testSelectDate() {
                onView(withId(R.id.buttonSelectDate)).perform(click());
                onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                        .perform(PickerActions.setDate(2023, 5, 16));
                onView(withId(android.R.id.button1)).perform(click());
                onView(withId(R.id.editTextDate)).check(matches(withText("16/5/2023")));
            }
        }
    }
}
