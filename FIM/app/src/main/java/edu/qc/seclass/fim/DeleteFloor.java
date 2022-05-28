package edu.qc.seclass.fim;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import edu.qc.seclass.fim.R;
import com.google.firebase.auth.FirebaseAuth;

public class DeleteFloor extends AppCompatActivity {
    public Button logout, view_all_button, delete_button;
    EditText floor_id;
    InventoryDataBase inventoryDataBase = new InventoryDataBase(DeleteFloor.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_floor);

        logout = findViewById(R.id.logoutBtn);
        view_all_button = findViewById(R.id.button_view_all);
        delete_button = findViewById(R.id.deleteBtn);
        floor_id = findViewById(R.id.floor_id);


        view_all_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String queryString = "SELECT * FROM INVENTORY_TABLE";
                Cursor res = inventoryDataBase.getData(queryString);
                if (res.getCount() == 0) {
                    Toast.makeText(DeleteFloor.this, "No Floor Exists", Toast.LENGTH_SHORT).show();
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
                        buffer.append("Floor Quantity: " + res.getString(8) + "\n\n");

                    } else {
                        buffer.append("Floor ID: " + res.getString(0) + "\n");
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

                AlertDialog.Builder builder = new AlertDialog.Builder(DeleteFloor.this);
                builder.setCancelable(true);
                builder.setTitle("Floor Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                // after pressing log out button, it will take user to InitialActivity page
                startActivity(new Intent(getApplicationContext(), InitialActivity.class));
                finish();
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedRows = inventoryDataBase.deleteData(floor_id.getText().toString());
                if (deletedRows > 0) {
                    Toast.makeText(DeleteFloor.this, "Data has been deleted", Toast.LENGTH_SHORT).show();
                    //toastMessage("Data has been deleted");
                    //ShowInventoryOnListView(inventoryDataBase);
                } else {
                    Toast.makeText(DeleteFloor.this, "No data has been selected", Toast.LENGTH_SHORT).show();
                    //toastMessage("Data not deleted");
                }
            }
        });
    }
}
