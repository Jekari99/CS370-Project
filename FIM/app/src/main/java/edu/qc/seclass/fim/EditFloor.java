package edu.qc.seclass.fim;

import static android.content.DialogInterface.*;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import edu.qc.seclass.fim.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;

public class EditFloor extends AppCompatActivity implements OnClickListener {

    public Button logout, view_all_button, enter_button, edit_button;
    EditText floor_ID, floor_price, floor_size, floor_quantity, store_ID;
    TextView category, type, species, color, brand, size, price, quantity, storeID;
    public RadioButton laminate, stone, wood, vinyl, tile, engineered, bamboo, solid, porcelain,
            ceramic, resin, marble, pebble, slate, regularLaminate, waterResistant, waterProof,
            oak, hickory, maple, notApplicable, black, white, gray, brown, b1, b2, b3, radioButtonCategory, radioButtonType,
            radioButtonSpecies, radioButtonColor, radioButtonBrand;
    RadioGroup radioGroupCategory, radioGroupType, radioGroupSpecies, radioGroupColor, radioGroupBrand ;
    Dialog dialog;
    ArrayList<String> arrayListCategory, arrayListType;
    InventoryDataBase inventoryDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_floor);

        category = findViewById(R.id.category);
        type = findViewById(R.id.type);
        species = findViewById(R.id.species);
        color = findViewById(R.id.color);
        brand = findViewById(R.id.brand);
        price = findViewById(R.id.price);
        size = findViewById(R.id.size);
        quantity = findViewById(R.id.quantity);


        radioGroupCategory = findViewById(R.id.radioGroupCategory);
        radioGroupType = findViewById(R.id.radioGroupType);
        radioGroupSpecies = findViewById(R.id.radioGroupSpecies);
        radioGroupColor = findViewById(R.id.radioGroupColor);
        radioGroupBrand = findViewById(R.id.radioGroupBrand);

        inventoryDataBase = new InventoryDataBase(EditFloor.this);
        laminate = findViewById(R.id.laminate);
        stone = findViewById(R.id.stone);
        wood = findViewById(R.id.wood);
        vinyl = findViewById(R.id.vinyl);
        tile = findViewById(R.id.tile);
        engineered = findViewById(R.id.engineered);
        bamboo = findViewById(R.id.bamboo);
        solid = findViewById(R.id.solid);
        porcelain = findViewById(R.id.porcelain);
        ceramic = findViewById(R.id.ceramic);
        resin = findViewById(R.id.resin);
        marble = findViewById(R.id.marble);
        pebble = findViewById(R.id.pebble);
        slate = findViewById(R.id.slate);
        regularLaminate = findViewById(R.id.regularLaminate);
        waterResistant = findViewById(R.id.waterResistant);
        waterProof = findViewById(R.id.waterProof);
        oak = findViewById(R.id.oak);
        hickory = findViewById(R.id.hickory);
        maple = findViewById(R.id.maple);
        notApplicable = findViewById(R.id.notApplicable);
        black = findViewById(R.id.black);
        white = findViewById(R.id.white);
        gray = findViewById(R.id.gray);
        brown = findViewById(R.id.brown);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        view_all_button = findViewById(R.id.button_view_all);
        logout = findViewById(R.id.logoutBtn);
        enter_button = findViewById(R.id.enter_btn);
        edit_button = findViewById(R.id.editBtn);
        floor_ID = findViewById(R.id.floor_id);
        floor_price = findViewById(R.id.floor_price);
        floor_size = findViewById(R.id.floor_size);
        floor_quantity = findViewById(R.id.floor_quantity);
        store_ID = findViewById(R.id.store_ID);
        storeID = findViewById(R.id.storeID);

        hideAll();

        arrayListCategory = new ArrayList<>();
        arrayListCategory.addAll(Arrays.asList("Tile", "Stone", "Wood", "Laminate", "Vinyl"));

        arrayListType = new ArrayList<>();
        arrayListType.addAll(Arrays.asList("Engineered", "Bamboo", "Solid", "Porcelain",
                "Ceramic", "Resin", "Marble", "Pebble", "Slate", "Oak", "Hickory", "Maple",
                "Regular Laminate", "Water Resistant", "Water Proof"));

        radioGroupCategory.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroupCategory, int checkedButtonId) {
                switch (checkedButtonId) {
                    case R.id.laminate:
                        hideAll();
                        showLaminate();
                        break;
                    case R.id.stone:
                        hideAll();
                        showStone();
                        break;
                    case R.id.wood:
                        hideAll();
                        showWood();
                        break;
                    case R.id.vinyl:
                        hideAll();
                        showVinyl();
                        break;
                    case R.id.tile:
                        hideAll();
                        showTile();
                        break;
                }
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

        view_all_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String queryString = "SELECT * FROM INVENTORY_TABLE";
                Cursor res = inventoryDataBase.getData(queryString);
                if (res.getCount() == 0) {
                    Toast.makeText(EditFloor.this, "No Floor Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    if (res.getString(4).equalsIgnoreCase("Not Applicable")){
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
                        buffer.append("Floor Quantity: " + res.getString(8) + "\n");
                        buffer.append("Floor Price: " + res.getString(3) + "\n");
                        buffer.append("Store ID: " + res.getString(9) + "\n\n");
                    }
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(EditFloor.this);
                builder.setCancelable(true);
                builder.setTitle("Floor Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        enter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InventoryDataBase inventoryDataBase = new InventoryDataBase(EditFloor.this);
                AddFloor addFloor = new AddFloor();
                String queryString = "SELECT * FROM INVENTORY_TABLE";
                Cursor res = inventoryDataBase.getData(queryString);
                StringBuffer buffer = new StringBuffer();
                dialog = new Dialog(EditFloor.this);

                if (res.getCount() == 0) {
                    toastMessage("No Floor Exists");
                    return;
                }
                while (res.moveToNext()){
                    if (floor_ID.getText().toString().equals(res.getString(0))){
                        switch (res.getString(1)) {
                            case "Laminate":
                                laminate.setChecked(true);
                                hideAll();
                                showLaminate();
                                break;
                            case "Stone":
                                stone.setChecked(true);
                                hideAll();
                                showStone();
                                break;
                            case "Wood":
                                wood.setChecked(true);
                                hideAll();
                                showWood();
                                break;
                            case "Vinyl":
                                vinyl.setChecked(true);
                                hideAll();
                                showVinyl();
                                break;
                            case "Tile":
                                tile.setChecked(true);
                                hideAll();
                                showTile();
                                break;
                        }
                        switch (res.getString(2)){
                            case "Engineered":
                                engineered.setChecked(true);
                                break;
                            case "Bamboo":
                                bamboo.setChecked(true);
                                break;
                            case "Solid":
                                solid.setChecked(true);
                                break;
                            case "Porcelain":
                                porcelain.setChecked(true);
                                break;
                            case "Ceramic":
                                ceramic.setChecked(true);
                                break;
                            case "Resin":
                                resin.setChecked(true);
                                break;
                            case "Marble":
                                marble.setChecked(true);
                                break;
                            case "Pebble":
                                pebble.setChecked(true);
                                break;
                            case "Slate":
                                slate.setChecked(true);
                                break;
                            case "Regular laminate":
                                regularLaminate.setChecked(true);
                                break;
                            case "Water resistant":
                                waterResistant.setChecked(true);
                                break;
                            case "Waterproof":
                                waterProof.setChecked(true);
                                break;
                        }
                        switch(res.getString(4)){
                            case "Oak":
                                oak.setChecked(true);
                                break;
                            case "Hickory":
                                hickory.setChecked(true);
                                break;
                            case "Maple":
                                maple.setChecked(true);
                                break;
                        }
                        switch(res.getString(5)){
                            case "Black":
                                black.setChecked(true);
                                break;
                            case "White":
                                white.setChecked(true);
                                break;
                            case "Gray":
                                gray.setChecked(true);
                                break;
                            case "Brown":
                                brown.setChecked(true);
                                break;
                        }
                        switch(res.getString(6)){
                            case "Brand 1":
                                b1.setChecked(true);
                                break;
                            case "Brand 2":
                                b2.setChecked(true);
                                break;
                            case "Brand 3":
                                b3.setChecked(true);
                                break;
                        }
                        floor_price.setText(res.getString(3));
                        floor_size.setText(res.getString(7));
                        floor_quantity.setText(res.getString(8));
                        store_ID.setText(res.getString(9));
                    }
                }

            }
        });

        edit_button.setOnClickListener(new View.OnClickListener() {

            FloorModel floorModel = null;

            @Override
            public void onClick(View view) {
                int radioIdCategory = radioGroupCategory.getCheckedRadioButtonId();
                int radioIdType = radioGroupType.getCheckedRadioButtonId();
                int radioIdSpecies = radioGroupSpecies.getCheckedRadioButtonId();
                int radioIdColor = radioGroupColor.getCheckedRadioButtonId();
                int radioIdBrand = radioGroupBrand.getCheckedRadioButtonId();

                // we need validations in case user does not make a selection for all radioGroup buttons
                if (radioIdCategory == -1){
                    toastMessage("Make a selection");
                }

                if (radioIdSpecies == -1) {
                    notApplicable.setChecked(true);
                    radioIdSpecies = radioGroupSpecies.getCheckedRadioButtonId();
                    radioButtonSpecies = findViewById(radioIdSpecies);
                }

                radioButtonCategory = findViewById(radioIdCategory);
                radioButtonType = findViewById(radioIdType);
                radioButtonSpecies = findViewById(radioIdSpecies);
                radioButtonColor = findViewById(radioIdColor);
                radioButtonBrand = findViewById(radioIdBrand);

                try{
                    inventoryDataBase.updateInventory(
                            Integer.parseInt(floor_ID.getText().toString()),
                            radioButtonCategory.getText().toString(),
                            Integer.parseInt(floor_price.getText().toString()),
                            radioButtonType.getText().toString(),
                            radioButtonSpecies.getText().toString(),
                            radioButtonColor.getText().toString(),
                            radioButtonBrand.getText().toString(),
                            Integer.parseInt(floor_size.getText().toString()),
                            Integer.parseInt(floor_quantity.getText().toString()),
                            Integer.parseInt(store_ID.getText().toString()));
                } catch (Exception e) {
                    toastMessage("Error updating floor");
                }

                //boolean success = inventoryDataBase.addOne(floorModel);
                toastMessage("Success, floor updated");
            }
        });

    }

    private void toastMessage (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void hideKeyboard () {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }

    public void showLaminate() {
        type.setVisibility(View.VISIBLE);
        regularLaminate.setVisibility(View.VISIBLE);
        waterResistant.setVisibility(View.VISIBLE);

        radioGroupType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                color.setVisibility(View.VISIBLE);
                black.setVisibility(View.VISIBLE);
                white.setVisibility(View.VISIBLE);
                gray.setVisibility(View.VISIBLE);
                brown.setVisibility(View.VISIBLE);
            }
        });

        radioGroupColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                brand.setVisibility(View.VISIBLE);
                b1.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
            }
        });

        radioGroupBrand.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                size.setVisibility(View.VISIBLE);
                floor_size.setVisibility(View.VISIBLE);
                price.setVisibility(View.VISIBLE);
                floor_price.setVisibility(View.VISIBLE);
                quantity.setVisibility(View.VISIBLE);
                floor_quantity.setVisibility(View.VISIBLE);
                storeID.setVisibility(View.VISIBLE);
                store_ID.setVisibility(View.VISIBLE);
                edit_button.setVisibility(View.VISIBLE);
                storeID.setVisibility(View.VISIBLE);
            }
        });
    }

    public void showStone() {
        type.setVisibility(View.VISIBLE);
        marble.setVisibility(View.VISIBLE);
        pebble.setVisibility(View.VISIBLE);
        slate.setVisibility(View.VISIBLE);

        radioGroupType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                color.setVisibility(View.VISIBLE);
                black.setVisibility(View.VISIBLE);
                white.setVisibility(View.VISIBLE);
                gray.setVisibility(View.VISIBLE);
                brown.setVisibility(View.VISIBLE);
            }
        });

        radioGroupColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                brand.setVisibility(View.VISIBLE);
                b1.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
            }
        });

        radioGroupBrand.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                size.setVisibility(View.VISIBLE);
                floor_size.setVisibility(View.VISIBLE);
                price.setVisibility(View.VISIBLE);
                floor_price.setVisibility(View.VISIBLE);
                quantity.setVisibility(View.VISIBLE);
                floor_quantity.setVisibility(View.VISIBLE);
                storeID.setVisibility(View.VISIBLE);
                store_ID.setVisibility(View.VISIBLE);
                edit_button.setVisibility(View.VISIBLE);
                storeID.setVisibility(View.VISIBLE);
            }
        });
    }

    public void showWood() {
        type.setVisibility(View.VISIBLE);
        solid.setVisibility(View.VISIBLE);
        engineered.setVisibility(View.VISIBLE);
        bamboo.setVisibility(View.VISIBLE);

        radioGroupType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                species.setVisibility(View.VISIBLE);
                oak.setVisibility(View.VISIBLE);
                hickory.setVisibility(View.VISIBLE);
                maple.setVisibility(View.VISIBLE);
                notApplicable.setVisibility(View.GONE);
            }
        });

        radioGroupSpecies.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                color.setVisibility(View.VISIBLE);
                black.setVisibility(View.VISIBLE);
                white.setVisibility(View.VISIBLE);
                gray.setVisibility(View.VISIBLE);
                brown.setVisibility(View.VISIBLE);
            }
        });

        radioGroupColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                brand.setVisibility(View.VISIBLE);
                b1.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
            }
        });

        radioGroupBrand.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                size.setVisibility(View.VISIBLE);
                floor_size.setVisibility(View.VISIBLE);
                price.setVisibility(View.VISIBLE);
                floor_price.setVisibility(View.VISIBLE);
                quantity.setVisibility(View.VISIBLE);
                floor_quantity.setVisibility(View.VISIBLE);
                storeID.setVisibility(View.VISIBLE);
                store_ID.setVisibility(View.VISIBLE);
                edit_button.setVisibility(View.VISIBLE);
                storeID.setVisibility(View.VISIBLE);
            }
        });
    }

    public void showVinyl() {
        type.setVisibility(View.VISIBLE);
        waterResistant.setVisibility(View.VISIBLE);
        waterProof.setVisibility(View.VISIBLE);

        radioGroupType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                color.setVisibility(View.VISIBLE);
                black.setVisibility(View.VISIBLE);
                white.setVisibility(View.VISIBLE);
                gray.setVisibility(View.VISIBLE);
                brown.setVisibility(View.VISIBLE);
            }
        });

        radioGroupColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                brand.setVisibility(View.VISIBLE);
                b1.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
            }
        });

        radioGroupBrand.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                size.setVisibility(View.VISIBLE);
                floor_size.setVisibility(View.VISIBLE);
                price.setVisibility(View.VISIBLE);
                floor_price.setVisibility(View.VISIBLE);
                quantity.setVisibility(View.VISIBLE);
                floor_quantity.setVisibility(View.VISIBLE);
                store_ID.setVisibility(View.VISIBLE);
                storeID.setVisibility(View.VISIBLE);
                edit_button.setVisibility(View.VISIBLE);
            }
        });
    }

    public void showTile() {
        type.setVisibility(View.VISIBLE);
        porcelain.setVisibility(View.VISIBLE);
        ceramic.setVisibility(View.VISIBLE);
        resin.setVisibility(View.VISIBLE);

        radioGroupType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                color.setVisibility(View.VISIBLE);
                black.setVisibility(View.VISIBLE);
                white.setVisibility(View.VISIBLE);
                gray.setVisibility(View.VISIBLE);
                brown.setVisibility(View.VISIBLE);
            }
        });

        radioGroupColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                brand.setVisibility(View.VISIBLE);
                b1.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
            }
        });

        radioGroupBrand.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                size.setVisibility(View.VISIBLE);
                floor_size.setVisibility(View.VISIBLE);
                price.setVisibility(View.VISIBLE);
                floor_price.setVisibility(View.VISIBLE);
                quantity.setVisibility(View.VISIBLE);
                floor_quantity.setVisibility(View.VISIBLE);
                storeID.setVisibility(View.VISIBLE);
                store_ID.setVisibility(View.VISIBLE);
                edit_button.setVisibility(View.VISIBLE);
            }
        });
    }

    public void hideAll() {
        type.setVisibility(View.GONE);
        engineered.setVisibility(View.GONE);
        bamboo.setVisibility(View.GONE);
        solid.setVisibility(View.GONE);
        porcelain.setVisibility(View.GONE);
        ceramic.setVisibility(View.GONE);
        resin.setVisibility(View.GONE);
        marble.setVisibility(View.GONE);
        pebble.setVisibility(View.GONE);
        slate.setVisibility(View.GONE);
        regularLaminate.setVisibility(View.GONE);
        waterResistant.setVisibility(View.GONE);
        waterProof.setVisibility(View.GONE);

        species.setVisibility(View.GONE);
        oak.setVisibility(View.GONE);
        hickory.setVisibility(View.GONE);
        maple.setVisibility(View.GONE);
        notApplicable.setVisibility((View.GONE));

        color.setVisibility(View.GONE);
        black.setVisibility(View.GONE);
        white.setVisibility(View.GONE);
        gray.setVisibility(View.GONE);
        brown.setVisibility(View.GONE);

        brand.setVisibility(View.GONE);
        b1.setVisibility(View.GONE);
        b2.setVisibility(View.GONE);
        b3.setVisibility(View.GONE);

        price.setVisibility(View.GONE);
        size.setVisibility(View.GONE);
        quantity.setVisibility(View.GONE);
        floor_price.setVisibility(View.GONE);
        floor_size.setVisibility(View.GONE);
        floor_quantity.setVisibility(View.GONE);
        store_ID.setVisibility(View.GONE);
        storeID.setVisibility(View.GONE);
        edit_button.setVisibility(View.GONE);
    }

}
