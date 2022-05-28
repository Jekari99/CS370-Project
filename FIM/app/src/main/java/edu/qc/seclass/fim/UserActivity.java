package edu.qc.seclass.fim;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;

public class UserActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    Button logout, button_view_all2, search;
    ArrayList<String> arrayListCategory, arrayListType;
    TextView floor_spinner, floor_spinner_type;
    EditText searchText;
    Dialog dialog;
    InventoryDataBase inventoryDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_activity);
        logout = findViewById(R.id.logoutBtn);
        button_view_all2 = findViewById(R.id.button_view_all2);
        inventoryDataBase = new InventoryDataBase(UserActivity.this);

        searchText = findViewById(R.id.searchBox);
        search = findViewById(R.id.button_search);
        floor_spinner = findViewById(R.id.floorSpinner);
        floor_spinner_type = findViewById(R.id.floorSpinnerType);

        arrayListCategory = new ArrayList<>();
        arrayListCategory.addAll(Arrays.asList("Tile", "Stone", "Wood", "Laminate", "Vinyl"));

        arrayListType = new ArrayList<>();
        arrayListType.addAll(Arrays.asList("Engineered", "Bamboo", "Solid", "Porcelain",
                "Ceramic", "Resin", "Marble", "Pebble", "Slate", "Oak", "Hickory", "Maple",
                "Regular Laminate", "Water Resistant", "Water Proof"));

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        // hides log out button if no user is signed in
        if(fAuth.getCurrentUser() == null){
            logout.setVisibility(View.INVISIBLE);
        }

        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), InitialActivity.class));
                finish();
            }
        });

        floor_spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(UserActivity.this);
                dialog.setContentView(R.layout.dialog_searchable_spinner);
                dialog.getWindow().setLayout(1000,1500);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                EditText editText = dialog.findViewById(R.id.edit_text);
                ListView listView = dialog.findViewById(R.id.list_view);

                // Initialize array adapter
                ArrayAdapter<String> adapter=new ArrayAdapter<>(UserActivity.this, android.R.layout.simple_list_item_1, arrayListCategory);

                // set adapter
                listView.setAdapter(adapter);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);
                    }
                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String queryString = "SELECT * FROM INVENTORY_TABLE";
                        Cursor res = inventoryDataBase.getData(queryString);
                        String n = adapter.getItem(position).toString();
                        StringBuffer buffer = new StringBuffer();

                        if (res.getCount() == 0) {
                            toastMessage("No Floor Exists");
                            return;
                        }
                        while (res.moveToNext()) {
                            if (n.equalsIgnoreCase(res.getString(1))) {
                                if (res.getString(4).equalsIgnoreCase("Not Applicable")) {
                                    buffer.append("Floor ID: " + res.getString(0) + "\n");
                                    buffer.append("Floor Category: " + res.getString(1) + "\n");
                                    buffer.append("Floor Type: " + res.getString(2) + "\n");
                                    buffer.append("Floor Color: " + res.getString(5) + "\n");
                                    buffer.append("Floor Brand: " + res.getString(6) + "\n");
                                    buffer.append("Floor Size: " + res.getString(7) + "\n");
                                    buffer.append("Floor Quantity: " + res.getString(8) + "\n");
                                    buffer.append("Floor Price: " + res.getString(3) + "\n");
                                    buffer.append("Store ID: " + res.getString(9) + "\n\n");
                                } else {
                                    buffer.append("Floor ID: " + res.getString(0) + "\n");
                                    buffer.append("Floor Category: " + res.getString(1) + "\n");
                                    buffer.append("Floor Type: " + res.getString(2) + "\n");
                                    buffer.append("Floor Species: " + res.getString(4) + "\n");
                                    buffer.append("Floor Color: " + res.getString(5) + "\n");
                                    buffer.append("Floor Brand: " + res.getString(6) + "\n");
                                    buffer.append("Floor Size: " + res.getString(7) + "\n");
                                    buffer.append("Floor Quantity: " + res.getString(8) + "\n");
                                    buffer.append("Floor Price: " + res.getString(3) + "\n");
                                    buffer.append("Store ID: " + res.getString(9) + "\n\n");
                                }
                            }
                        }
                        AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                        builder.setCancelable(true);
                        builder.setTitle("Result ");
                        builder.setMessage(buffer.toString());
                        if (buffer == null || buffer.length() == 0){
                            toastMessage("No results found");
                        } else {
                            builder.show();
                        }
                        dialog.dismiss();
                    }
                });
            }
        });

        floor_spinner_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(UserActivity.this);
                dialog.setContentView(R.layout.dialog_searchable_spinner_floor_type);
                dialog.getWindow().setLayout(1000,1500);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                EditText editText = dialog.findViewById(R.id.edit_text);
                ListView listView = dialog.findViewById(R.id.list_view);

                // Initialize array adapter
                ArrayAdapter<String> adapter=new ArrayAdapter<>(UserActivity.this, android.R.layout.simple_list_item_1, arrayListType);

                // set adapter
                listView.setAdapter(adapter);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);
                    }
                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String queryString = "SELECT * FROM INVENTORY_TABLE";
                        Cursor res = inventoryDataBase.getData(queryString);
                        String n = adapter.getItem(position).toString();
                        StringBuffer buffer = new StringBuffer();

                        if (res.getCount() == 0) {
                            toastMessage("No Floor Exists");
                            return;
                        }
                        while (res.moveToNext()) {
                            if (n.equalsIgnoreCase(res.getString(2))) {
                                if (res.getString(4).equalsIgnoreCase("Not Applicable")) {
                                    buffer.append("Floor ID: " + res.getString(0) + "\n");
                                    buffer.append("Floor Category: " + res.getString(1) + "\n");
                                    buffer.append("Floor Type: " + res.getString(2) + "\n");
                                    buffer.append("Floor Color: " + res.getString(5) + "\n");
                                    buffer.append("Floor Brand: " + res.getString(6) + "\n");
                                    buffer.append("Floor Size: " + res.getString(7) + "\n");
                                    buffer.append("Floor Quantity: " + res.getString(8) + "\n");
                                    buffer.append("Floor Price: " + res.getString(3) + "\n");
                                    buffer.append("Store ID: " + res.getString(9) + "\n\n");
                                } else {
                                    buffer.append("Floor ID: " + res.getString(0) + "\n");
                                    buffer.append("Floor Category: " + res.getString(1) + "\n");
                                    buffer.append("Floor Type: " + res.getString(2) + "\n");
                                    buffer.append("Floor Species: " + res.getString(4) + "\n");
                                    buffer.append("Floor Color: " + res.getString(5) + "\n");
                                    buffer.append("Floor Brand: " + res.getString(6) + "\n");
                                    buffer.append("Floor Size: " + res.getString(7) + "\n");
                                    buffer.append("Floor Quantity: " + res.getString(8) + "\n");
                                    buffer.append("Floor Price: " + res.getString(3) + "\n");
                                    buffer.append("Store ID: " + res.getString(9) + "\n\n");
                                }
                            }
                        }
                        AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                        builder.setCancelable(true);
                        builder.setTitle("Result ");
                        builder.setMessage(buffer.toString());
                        if (buffer == null || buffer.length() == 0){
                            toastMessage("No results found");
                        } else {
                            builder.show();
                        }
                        dialog.dismiss();
                    }
                });
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();


                String n = searchText.getText().toString();
                StringBuffer buffer = new StringBuffer();
                String queryString = "SELECT * FROM INVENTORY_TABLE";
                Cursor res = inventoryDataBase.getData(queryString);

                if (res.getCount() == 0) {
                    toastMessage("No Floor Exists");
                    return;
                }

                while (res.moveToNext()) {
                    if (n.equalsIgnoreCase(res.getString(1))) {
                        if (res.getString(4).equalsIgnoreCase("Not Applicable")) {
                            buffer.append("Floor ID: " + res.getString(0) + "\n");
                            buffer.append("Floor Category: " + res.getString(1) + "\n");
                            buffer.append("Floor Type: " + res.getString(2) + "\n");
                            buffer.append("Floor Color: " + res.getString(5) + "\n");
                            buffer.append("Floor Brand: " + res.getString(6) + "\n");
                            buffer.append("Floor Size: " + res.getString(7) + "\n");
                            buffer.append("Floor Price: " + res.getString(3) + "\n");
                            buffer.append("Floor Quantity: " + res.getString(8) + "\n");
                            buffer.append("Store ID: " + res.getString(9) + "\n\n");
                        } else {
                            buffer.append("Floor ID: " + res.getString(0) + "\n");
                            buffer.append("Floor Category: " + res.getString(1) + "\n");
                            buffer.append("Floor Type: " + res.getString(2) + "\n");
                            buffer.append("Floor Species: " + res.getString(4) + "\n");
                            buffer.append("Floor Color: " + res.getString(5) + "\n");
                            buffer.append("Floor Brand: " + res.getString(6) + "\n");
                            buffer.append("Floor Size: " + res.getString(7) + "\n");
                            buffer.append("Floor Price: " + res.getString(3) + "\n");
                            buffer.append("Floor Quantity: " + res.getString(8) + "\n");
                            buffer.append("Store ID: " + res.getString(9) + "\n\n");
                        }
                    }

                    if (n.equalsIgnoreCase(res.getString(2))) {
                        if (res.getString(4).equalsIgnoreCase("Not Applicable")) {
                            buffer.append("Floor ID: " + res.getString(0) + "\n");
                            buffer.append("Floor Type: " + res.getString(2) + "\n");
                            buffer.append("Floor Category: " + res.getString(1) + "\n");
                            buffer.append("Floor Price: " + res.getString(3) + "\n");
                            buffer.append("Floor Color: " + res.getString(5) + "\n");
                            buffer.append("Floor Brand: " + res.getString(6) + "\n");
                            buffer.append("Floor Size: " + res.getString(7) + "\n");
                            buffer.append("Floor Quantity: " + res.getString(8) + "\n");
                            buffer.append("Store ID: " + res.getString(9) + "\n\n");
                        } else {
                            buffer.append("Floor ID: " + res.getString(0) + "\n");
                            buffer.append("Floor Type: " + res.getString(2) + "\n");
                            buffer.append("Floor Category: " + res.getString(1) + "\n");
                            buffer.append("Floor Price: " + res.getString(3) + "\n");
                            buffer.append("Floor Species: " + res.getString(4) + "\n");
                            buffer.append("Floor Color: " + res.getString(5) + "\n");
                            buffer.append("Floor Brand: " + res.getString(6) + "\n");
                            buffer.append("Floor Size: " + res.getString(7) + "\n");
                            buffer.append("Floor Quantity: " + res.getString(8) + "\n");
                            buffer.append("Store ID: " + res.getString(9) + "\n\n");
                        }
                    }

                    if (n.equalsIgnoreCase(res.getString(3))) {
                        if (res.getString(4).equalsIgnoreCase("Not Applicable")) {
                            buffer.append("Floor ID: " + res.getString(0) + "\n");
                            buffer.append("Floor Price: " + res.getString(3) + "\n");
                            buffer.append("Floor Category: " + res.getString(1) + "\n");
                            buffer.append("Floor Type: " + res.getString(2) + "\n");
                            buffer.append("Floor Color: " + res.getString(5) + "\n");
                            buffer.append("Floor Brand: " + res.getString(6) + "\n");
                            buffer.append("Floor Size: " + res.getString(7) + "\n");
                            buffer.append("Floor Quantity: " + res.getString(8) + "\n");
                            buffer.append("Store ID: " + res.getString(9) + "\n\n");
                        } else {
                            buffer.append("Floor ID: " + res.getString(0) + "\n");
                            buffer.append("Floor Price: " + res.getString(3) + "\n");
                            buffer.append("Floor Category: " + res.getString(1) + "\n");
                            buffer.append("Floor Type: " + res.getString(2) + "\n");
                            buffer.append("Floor Species: " + res.getString(4) + "\n");
                            buffer.append("Floor Color: " + res.getString(5) + "\n");
                            buffer.append("Floor Brand: " + res.getString(6) + "\n");
                            buffer.append("Floor Size: " + res.getString(7) + "\n");
                            buffer.append("Floor Quantity: " + res.getString(8) + "\n");
                            buffer.append("Store ID: " + res.getString(9) + "\n\n");
                        }
                    }

                    if (n.equalsIgnoreCase(res.getString(4))) {
                        if (res.getString(4).equalsIgnoreCase("Not Applicable")) {
                            buffer.append("Floor ID: " + res.getString(0) + "\n");
                            buffer.append("Floor Category: " + res.getString(1) + "\n");
                            buffer.append("Floor Type: " + res.getString(2) + "\n");
                            buffer.append("Floor Price: " + res.getString(3) + "\n");
                            buffer.append("Floor Color: " + res.getString(5) + "\n");
                            buffer.append("Floor Brand: " + res.getString(6) + "\n");
                            buffer.append("Floor Size: " + res.getString(7) + "\n");
                            buffer.append("Floor Quantity: " + res.getString(8) + "\n");
                            buffer.append("Store ID: " + res.getString(9) + "\n\n");
                        } else {
                            buffer.append("Floor ID: " + res.getString(0) + "\n");
                            buffer.append("Floor Species: " + res.getString(4) + "\n");
                            buffer.append("Floor Category: " + res.getString(1) + "\n");
                            buffer.append("Floor Type: " + res.getString(2) + "\n");
                            buffer.append("Floor Price: " + res.getString(3) + "\n");
                            buffer.append("Floor Color: " + res.getString(5) + "\n");
                            buffer.append("Floor Brand: " + res.getString(6) + "\n");
                            buffer.append("Floor Size: " + res.getString(7) + "\n");
                            buffer.append("Floor Quantity: " + res.getString(8) + "\n");
                            buffer.append("Store ID: " + res.getString(9) + "\n\n");
                        }
                    }

                    if (n.equalsIgnoreCase(res.getString(5))) {
                        if (res.getString(4).equalsIgnoreCase("Not Applicable")) {
                            buffer.append("Floor ID: " + res.getString(0) + "\n");
                            buffer.append("Floor Color: " + res.getString(5) + "\n");
                            buffer.append("Floor Category: " + res.getString(1) + "\n");
                            buffer.append("Floor Type: " + res.getString(2) + "\n");
                            buffer.append("Floor Price: " + res.getString(3) + "\n");
                            buffer.append("Floor Brand: " + res.getString(6) + "\n");
                            buffer.append("Floor Size: " + res.getString(7) + "\n");
                            buffer.append("Floor Quantity: " + res.getString(8) + "\n");
                            buffer.append("Store ID: " + res.getString(9) + "\n\n");
                        } else {
                            buffer.append("Floor ID: " + res.getString(0) + "\n");
                            buffer.append("Floor Color: " + res.getString(5) + "\n");
                            buffer.append("Floor Category: " + res.getString(1) + "\n");
                            buffer.append("Floor Type: " + res.getString(2) + "\n");
                            buffer.append("Floor Price: " + res.getString(3) + "\n");
                            buffer.append("Floor Species: " + res.getString(4) + "\n");
                            buffer.append("Floor Brand: " + res.getString(6) + "\n");
                            buffer.append("Floor Size: " + res.getString(7) + "\n");
                            buffer.append("Floor Quantity: " + res.getString(8) + "\n");
                            buffer.append("Store ID: " + res.getString(9) + "\n\n");
                        }
                    }

                    if (n.equalsIgnoreCase(res.getString(6))) {
                        if (res.getString(4).equalsIgnoreCase("Not Applicable")) {
                            buffer.append("Floor ID: " + res.getString(0) + "\n");
                            buffer.append("Floor Brand: " + res.getString(6) + "\n");
                            buffer.append("Floor Category: " + res.getString(1) + "\n");
                            buffer.append("Floor Type: " + res.getString(2) + "\n");
                            buffer.append("Floor Price: " + res.getString(3) + "\n");
                            buffer.append("Floor Color: " + res.getString(5) + "\n");
                            buffer.append("Floor Size: " + res.getString(7) + "\n");
                            buffer.append("Floor Quantity: " + res.getString(8) + "\n");
                            buffer.append("Store ID: " + res.getString(9) + "\n\n");
                        } else {
                            buffer.append("Floor ID: " + res.getString(0) + "\n");
                            buffer.append("Floor Brand: " + res.getString(6) + "\n");
                            buffer.append("Floor Category: " + res.getString(1) + "\n");
                            buffer.append("Floor Type: " + res.getString(2) + "\n");
                            buffer.append("Floor Price: " + res.getString(3) + "\n");
                            buffer.append("Floor Species: " + res.getString(4) + "\n");
                            buffer.append("Floor Color: " + res.getString(5) + "\n");
                            buffer.append("Floor Size: " + res.getString(7) + "\n");
                            buffer.append("Floor Quantity: " + res.getString(8) + "\n");
                            buffer.append("Store ID: " + res.getString(9) + "\n\n");
                        }
                    }

                    if (n.equalsIgnoreCase(res.getString(7))) {
                        if (res.getString(4).equalsIgnoreCase("Not Applicable")) {
                            buffer.append("Floor ID: " + res.getString(0) + "\n");
                            buffer.append("Floor Size: " + res.getString(7) + "\n");
                            buffer.append("Floor Category: " + res.getString(1) + "\n");
                            buffer.append("Floor Type: " + res.getString(2) + "\n");
                            buffer.append("Floor Price: " + res.getString(3) + "\n");
                            buffer.append("Floor Color: " + res.getString(5) + "\n");
                            buffer.append("Floor Brand: " + res.getString(6) + "\n");
                            buffer.append("Floor Quantity: " + res.getString(8) + "\n");
                            buffer.append("Store ID: " + res.getString(9) + "\n\n");
                        } else {
                            buffer.append("Floor ID: " + res.getString(0) + "\n");
                            buffer.append("Floor Size: " + res.getString(7) + "\n");
                            buffer.append("Floor Category: " + res.getString(1) + "\n");
                            buffer.append("Floor Type: " + res.getString(2) + "\n");
                            buffer.append("Floor Price: " + res.getString(3) + "\n");
                            buffer.append("Floor Species: " + res.getString(4) + "\n");
                            buffer.append("Floor Color: " + res.getString(5) + "\n");
                            buffer.append("Floor Brand: " + res.getString(6) + "\n");
                            buffer.append("Floor Quantity: " + res.getString(8) + "\n");
                            buffer.append("Store ID: " + res.getString(9) + "\n\n");
                        }
                    }

                    if (n.equalsIgnoreCase(res.getString(8))) {
                        if (res.getString(4).equalsIgnoreCase("Not Applicable")) {
                            buffer.append("Floor ID: " + res.getString(0) + "\n");
                            buffer.append("Floor Quantity: " + res.getString(8) + "\n");
                            buffer.append("Floor Category: " + res.getString(1) + "\n");
                            buffer.append("Floor Type: " + res.getString(2) + "\n");
                            buffer.append("Floor Price: " + res.getString(3) + "\n");
                            buffer.append("Floor Color: " + res.getString(5) + "\n");
                            buffer.append("Floor Brand: " + res.getString(6) + "\n");
                            buffer.append("Floor Size: " + res.getString(7) + "\n");
                            buffer.append("Store ID: " + res.getString(9) + "\n\n");
                        } else {
                            buffer.append("Floor ID: " + res.getString(0) + "\n");
                            buffer.append("Floor Quantity: " + res.getString(8) + "\n");
                            buffer.append("Floor Category: " + res.getString(1) + "\n");
                            buffer.append("Floor Type: " + res.getString(2) + "\n");
                            buffer.append("Floor Price: " + res.getString(3) + "\n");
                            buffer.append("Floor Species: " + res.getString(4) + "\n");
                            buffer.append("Floor Color: " + res.getString(5) + "\n");
                            buffer.append("Floor Brand: " + res.getString(6) + "\n");
                            buffer.append("Floor Size: " + res.getString(7) + "\n");
                            buffer.append("Store ID: " + res.getString(9) + "\n\n");
                        }
                    }

                    if (n.equalsIgnoreCase(res.getString(9))) {
                        if (res.getString(4).equalsIgnoreCase("Not Applicable")) {
                            buffer.append("Floor ID: " + res.getString(0) + "\n");
                            buffer.append("Store ID: " + res.getString(9) + "\n");
                            buffer.append("Floor Category: " + res.getString(1) + "\n");
                            buffer.append("Floor Type: " + res.getString(2) + "\n");
                            buffer.append("Floor Price: " + res.getString(3) + "\n");
                            buffer.append("Floor Color: " + res.getString(5) + "\n");
                            buffer.append("Floor Brand: " + res.getString(6) + "\n");
                            buffer.append("Floor Size: " + res.getString(7) + "\n");
                            buffer.append("Floor Quantity: " + res.getString(8) + "\n\n");
                        } else {
                            buffer.append("Floor ID: " + res.getString(0) + "\n");
                            buffer.append("Store ID: " + res.getString(9) + "\n");
                            buffer.append("Floor Category: " + res.getString(1) + "\n");
                            buffer.append("Floor Type: " + res.getString(2) + "\n");
                            buffer.append("Floor Price: " + res.getString(3) + "\n");
                            buffer.append("Floor Species: " + res.getString(4) + "\n");
                            buffer.append("Floor Color: " + res.getString(5) + "\n");
                            buffer.append("Floor Brand: " + res.getString(6) + "\n");
                            buffer.append("Floor Size: " + res.getString(7) + "\n");
                            buffer.append("Floor Quantity: " + res.getString(8) + "\n\n");
                        }
                    }
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Result ");
                builder.setMessage(buffer.toString());

                if (searchText.getText().toString().equalsIgnoreCase("")) {
                    toastMessage("Enter a proper search");
                } else {
                    if (buffer == null || buffer.length() == 0){
                        toastMessage("No results found");
                    } else {
                        builder.show();
                    }
                }
            }
        });

        button_view_all2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String queryString = "SELECT * FROM INVENTORY_TABLE";
                Cursor res = inventoryDataBase.getData(queryString);
                if (res.getCount() == 0) {
                    Toast.makeText(UserActivity.this, "No Floor Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    if (res.getString(4).equalsIgnoreCase("Not Applicable")){
                        buffer.append("Floor ID: " + res.getString(0) + "\n");
                        buffer.append("Floor Category: " + res.getString(1) + "\n");
                        buffer.append("Floor Type: " + res.getString(2) + "\n");
                        buffer.append("Floor Price: " + res.getString(3) + "\n");
                        buffer.append("Floor Color: " + res.getString(5) + "\n");
                        buffer.append("Floor Brand: " + res.getString(6) + "\n");
                        buffer.append("Floor Size: " + res.getString(7) + "\n");
                        buffer.append("Floor Quantity: " + res.getString(8) + "\n");
                        buffer.append("Store ID: " + res.getString(9) + "\n\n");

                    } else {
                        buffer.append("Floor ID: " + res.getString(0) + "\n");
                        buffer.append("Floor Category: " + res.getString(1) + "\n");
                        buffer.append("Floor Type: " + res.getString(2) + "\n");
                        buffer.append("Floor Price: " + res.getString(3) + "\n");
                        buffer.append("Floor Species: " + res.getString(4) + "\n");
                        buffer.append("Floor Color: " + res.getString(5) + "\n");
                        buffer.append("Floor Brand: " + res.getString(6) + "\n");
                        buffer.append("Floor Size: " + res.getString(7) + "\n");
                        buffer.append("Floor Quantity: " + res.getString(8) + "\n");
                        buffer.append("Store ID: " + res.getString(9) + "\n\n");
                    }
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Floor Entries");
                builder.setMessage(buffer.toString());
                if (buffer == null || buffer.length() == 0){
                    toastMessage("No results found");
                } else {
                    builder.show();
                }
            }
        });
    }

    private void toastMessage (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void hideKeyboard(){
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}