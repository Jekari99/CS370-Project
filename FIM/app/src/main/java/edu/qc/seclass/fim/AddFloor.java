package edu.qc.seclass.fim;

import android.content.Context;
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

public class AddFloor extends AppCompatActivity {

    public Button logout, add_button, view_all_button;
    public RadioButton laminate;
    public RadioButton stone;
    public RadioButton wood;
    public RadioButton vinyl;
    public RadioButton tile;
    public RadioButton engineered;
    public RadioButton bamboo;
    public RadioButton solid;
    public RadioButton porcelain;
    public RadioButton ceramic;
    public RadioButton resin;
    public RadioButton marble;
    public RadioButton pebble;
    public RadioButton slate;
    public RadioButton regularLaminate;
    public RadioButton waterResistant;
    public RadioButton waterProof;
    public RadioButton oak;
    public RadioButton hickory;
    public RadioButton maple;
    public RadioButton notApplicable;
    public RadioButton black;
    public RadioButton white;
    public RadioButton gray;
    public RadioButton brown;
    public RadioButton b1;
    public RadioButton b2;
    public RadioButton b3;
    public RadioButton radioButtonCategory;
    public RadioButton radioButtonType;
    public RadioButton radioButtonSpecies;
    public RadioButton radioButtonColor;
    public RadioButton radioButtonBrand;
    public RadioGroup radioGroupCategory, radioGroupType, radioGroupSpecies, radioGroupColor, radioGroupBrand ;
    TextView category, type, species, color, brand, size, price, quantity, storeID;
    EditText floor_price, floor_size, floor_quantity, store_ID;
    InventoryDataBase inventoryDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inventoryDataBase = new InventoryDataBase(AddFloor.this);

        setContentView(R.layout.activity_add_floor);
        radioGroupCategory = findViewById(R.id.radioGroupCategory);
        radioGroupType = findViewById(R.id.radioGroupType);
        radioGroupSpecies = findViewById(R.id.radioGroupSpecies);
        radioGroupColor = findViewById(R.id.radioGroupColor);
        radioGroupBrand = findViewById(R.id.radioGroupBrand);

        logout = findViewById(R.id.logoutBtn);
        add_button = findViewById(R.id.addBtn);
        view_all_button = findViewById(R.id.button_view_all);

        category = findViewById(R.id.category);
        laminate = findViewById(R.id.laminate);
        stone = findViewById(R.id.stone);
        wood = findViewById(R.id.wood);
        vinyl = findViewById(R.id.vinyl);
        tile = findViewById(R.id.tile);

        type = findViewById(R.id.type);
        //type.setVisibility(View.GONE);
        engineered = findViewById(R.id.engineered);
        //engineered.setVisibility(View.GONE);
        bamboo = findViewById(R.id.bamboo);
        //bamboo.setVisibility(View.GONE);
        solid = findViewById(R.id.solid);
        //solid.setVisibility(View.GONE);
        porcelain = findViewById(R.id.porcelain);
        //porcelain.setVisibility(View.GONE);
        ceramic = findViewById(R.id.ceramic);
        //ceramic.setVisibility(View.GONE);
        resin = findViewById(R.id.resin);
        //resin.setVisibility(View.GONE);
        marble = findViewById(R.id.marble);
        //marble.setVisibility(View.GONE);
        pebble = findViewById(R.id.pebble);
        //pebble.setVisibility(View.GONE);
        slate = findViewById(R.id.slate);
        //slate.setVisibility(View.GONE);
        regularLaminate = findViewById(R.id.regularLaminate);
        //regularLaminate.setVisibility(View.GONE);
        waterResistant = findViewById(R.id.waterResistant);
        //waterResistant.setVisibility(View.GONE);
        waterProof = findViewById(R.id.waterProof);
        //waterProof.setVisibility(View.GONE);

        species = findViewById(R.id.species);
        //species.setVisibility(View.GONE);
        oak = findViewById(R.id.oak);
        //oak.setVisibility(View.GONE);
        hickory = findViewById(R.id.hickory);
        //hickory.setVisibility(View.GONE);
        maple = findViewById(R.id.maple);
        //maple.setVisibility(View.GONE);
        notApplicable = findViewById(R.id.notApplicable);


        color = findViewById(R.id.color);
        //color.setVisibility(View.GONE);
        black = findViewById(R.id.black);
        //black.setVisibility(View.GONE);
        white = findViewById(R.id.white);
        //white.setVisibility(View.GONE);
        gray = findViewById(R.id.gray);
        //gray.setVisibility(View.GONE);
        brown = findViewById(R.id.brown);
        //brown.setVisibility(View.GONE);

        brand = findViewById(R.id.brand);
        // brand.setVisibility(View.GONE);
        b1 = findViewById(R.id.b1);
        //b1.setVisibility(View.GONE);
        b2 = findViewById(R.id.b2);
        //b2.setVisibility(View.GONE);
        b3 = findViewById(R.id.b3);
        //b3.setVisibility(View.GONE);

        price = findViewById(R.id.price);
        //price.setVisibility(View.GONE);
        size = findViewById(R.id.size);
        //size.setVisibility(View.GONE);
        quantity = findViewById(R.id.quantity);
        //quantity.setVisibility(View.GONE);
        floor_price = findViewById(R.id.floor_price);
        //floor_price.setVisibility(View.GONE);
        floor_size = findViewById(R.id.floor_size);
        //floor_size.setVisibility(View.GONE);
        floor_quantity = findViewById(R.id.floor_quantity);
        //floor_quantity.setVisibility(View.GONE);

        storeID = findViewById(R.id.storeID);
        store_ID = findViewById(R.id.store_ID);

        hideAll();

        //tile.setVisibility(View.GONE);
        //radioButtonSpecies.setVisibility(View.GONE);
        //radioButtonColor.setVisibility(View.GONE);
        //radioButtonBrand.setVisibility(View.GONE);

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

        add_button.setOnClickListener(new View.OnClickListener() {

            FloorModel floorModel = null;

            @Override
            public void onClick(View view) {

                int radioIdCategory = radioGroupCategory.getCheckedRadioButtonId();
                int radioIdType = radioGroupType.getCheckedRadioButtonId();
                int radioIdSpecies = radioGroupSpecies.getCheckedRadioButtonId();
                int radioIdColor = radioGroupColor.getCheckedRadioButtonId();
                int radioIdBrand = radioGroupBrand.getCheckedRadioButtonId();

                // we need validations in case user does not make a selection for all radioGroup buttons



                // set button Not Applicable as true if no button for species is selected.
                // this applies for floors that don't have a species, db is not accepting null values.
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
                    floorModel = new FloorModel(-1, radioButtonCategory.getText().toString(),
                            Integer.parseInt(floor_price.getText().toString()),
                            radioButtonType.getText().toString(),
                            radioButtonSpecies.getText().toString(),
                            radioButtonColor.getText().toString(),
                            radioButtonBrand.getText().toString(),
                            Integer.parseInt(floor_size.getText().toString()),
                            Integer.parseInt(floor_quantity.getText().toString()),
                            Integer.parseInt(store_ID.getText().toString()));

                } catch (Exception e) {
                    toastMessage("Error Adding Floor");
                }

                InventoryDataBase inventoryDataBase = new InventoryDataBase(AddFloor.this);
                boolean success = inventoryDataBase.addOne(floorModel);
                toastMessage("Success");
            }
        });

        view_all_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String queryString = "SELECT * FROM INVENTORY_TABLE";
                Cursor res = inventoryDataBase.getData(queryString);
                if (res.getCount() == 0) {
                    Toast.makeText(AddFloor.this, "No Floor Exists", Toast.LENGTH_SHORT).show();
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

                AlertDialog.Builder builder = new AlertDialog.Builder(AddFloor.this);
                builder.setCancelable(true);
                builder.setTitle("Floor Entries");
                builder.setMessage(buffer.toString());
                if (buffer == null || buffer.length() == 0){
                    toastMessage("No results found");
                } else {
                    builder.show();
                }            }
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
                add_button.setVisibility(View.VISIBLE);
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
                add_button.setVisibility(View.VISIBLE);
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
                add_button.setVisibility(View.VISIBLE);
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
                storeID.setVisibility(View.VISIBLE);
                store_ID.setVisibility(View.VISIBLE);
                add_button.setVisibility(View.VISIBLE);
                storeID.setVisibility(View.VISIBLE);
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
                store_ID.setVisibility(View.VISIBLE);
                storeID.setVisibility(View.VISIBLE);
                add_button.setVisibility(View.VISIBLE);
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
        add_button.setVisibility(View.GONE);
    }
}