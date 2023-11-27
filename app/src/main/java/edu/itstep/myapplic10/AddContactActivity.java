package edu.itstep.myapplic10;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddContactActivity extends AppCompatActivity {

    private ContactDAO contactDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        contactDAO = App.getInstance().getContactDAO();

        EditText etFullName = findViewById(R.id.etFullName);
        EditText etPhone = findViewById(R.id.etPhone);
        EditText etAdress = findViewById(R.id.etAdress);
        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName = etFullName.getText().toString();
                String phone = etPhone.getText().toString();
                String adress = etAdress.getText().toString();

                Contact newContact = new Contact(fullName, phone, new Address(adress));
                contactDAO.insert(newContact);

                finish();
            }
        });
    }
}
