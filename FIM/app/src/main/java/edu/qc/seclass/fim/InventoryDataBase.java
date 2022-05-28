package edu.qc.seclass.fim;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class InventoryDataBase extends SQLiteOpenHelper {

    // database values
    private static final String DATABASE_NAME = "inventory.db";
    public static final String INVENTORY_TABLE = "INVENTORY_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_STORE_ID = "STORE_ID";
    public static final String COLUMN_FLOOR_CATEGORY = "FLOOR_CATEGORY";
    public static final String COLUMN_FLOOR_TYPE = "FLOOR_TYPE";
    public static final String COLUMN_FLOOR_COLOR = "FLOOR_COLOR";
    public static final String COLUMN_FLOOR_SIZE = "FLOOR_SIZE";
    public static final String COLUMN_FLOOR_BRAND = "FLOOR_BRAND";
    public static final String COLUMN_FLOOR_PRICE = "FLOOR_PRICE";
    public static final String COLUMN_FLOOR_SPECIES = "FLOOR_SPECIES";
    public static final String COLUMN_FLOOR_QUANTITY = "FLOOR_QUANTITY";
    private static final int DATA_VERSION = 2;




    public InventoryDataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATA_VERSION);
    }

    // this is called the first time a database is accessed. Create new database here.
    // here is where we create our SQLite tables, I have still to create the table with all the columns we need.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE "
                + INVENTORY_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_FLOOR_CATEGORY + " TEXT, "
                + COLUMN_FLOOR_TYPE + " TEXT, "
                + COLUMN_FLOOR_PRICE + " INT, "
                + COLUMN_FLOOR_SPECIES + " TEXT, "
                + COLUMN_FLOOR_COLOR + " TEXT, "
                + COLUMN_FLOOR_BRAND + " TEXT, "
                + COLUMN_FLOOR_SIZE + " INT, "
                + COLUMN_FLOOR_QUANTITY + " INT, "
                + COLUMN_STORE_ID + " INT" + ")";

        db.execSQL(createTableStatement);
        loadDatabase();
    }
    // this is called if the database version number changes. It prevents previous users apps from breaking when you change the database design.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public boolean addOne(FloorModel floorModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_FLOOR_CATEGORY, floorModel.getFloorCategory());
        cv.put(COLUMN_FLOOR_PRICE, floorModel.getFloorPrice());
        cv.put(COLUMN_FLOOR_TYPE, floorModel.getFloorType());
        cv.put(COLUMN_FLOOR_SPECIES, floorModel.getFloorSpecies());
        cv.put(COLUMN_FLOOR_COLOR, floorModel.getFloorColor());
        cv.put(COLUMN_FLOOR_BRAND, floorModel.getFloorBrand());
        cv.put(COLUMN_FLOOR_SIZE, floorModel.getFloorSize());
        cv.put(COLUMN_FLOOR_QUANTITY, floorModel.getFloorQuantity());
        cv.put(COLUMN_STORE_ID, floorModel.getStoreID());

        long insert = db.insert(INVENTORY_TABLE, null, cv);

        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateInventory(int floorID, String newCategory, int newPrice, String floorType,
                                   String floorSpecies, String floorColor, String floorBrand,
                                   int floorSize, int floorQuantity, int storeID) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FLOOR_CATEGORY, newCategory);
        contentValues.put(COLUMN_FLOOR_PRICE, newPrice);
        contentValues.put(COLUMN_FLOOR_TYPE, floorType);
        contentValues.put(COLUMN_FLOOR_SPECIES, floorSpecies);
        contentValues.put(COLUMN_FLOOR_COLOR, floorColor);
        contentValues.put(COLUMN_FLOOR_BRAND, floorBrand);
        contentValues.put(COLUMN_FLOOR_SIZE, floorSize);
        contentValues.put(COLUMN_FLOOR_QUANTITY, floorQuantity);
        contentValues.put(COLUMN_STORE_ID, storeID);

        db.update(INVENTORY_TABLE, contentValues, "ID = ?", new String [] {
                String.valueOf(floorID)
        });
        return true;
    }


    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(INVENTORY_TABLE, "ID = ?", new String[] { id });
    }

    public Cursor getData(String queryString) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }

    public List<FloorModel> selectAll() {
        // return list containing what is explicitly added to it.
        List<FloorModel> returnList = new ArrayList<>();

        // get data from database.
        String queryString = "SELECT * FROM " + INVENTORY_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through results and create new inventory objects. Place them into a return list.
            do {
                int inventoryID = cursor.getInt(0);
                int floorPrice = cursor.getInt(2);
                int floorSize = cursor.getInt(7);
                int floorQuantity = cursor.getInt(8);
                int storeID = cursor.getInt(9);

                String floorCategory = cursor.getString(1);
                String floorType = cursor.getString(3);
                String floorSpecies = cursor.getString(4);
                String floorColor = cursor.getString(5);
                String floorBrand = cursor.getString(6);

                FloorModel newFloor = new FloorModel(inventoryID,floorCategory, floorPrice, floorType,
                        floorSpecies, floorColor, floorBrand, floorSize, floorQuantity, storeID);
                returnList.add(newFloor);

            } while (cursor.moveToNext());

        } else {
            //failure. do not add anything to the list.
        }
        // close both the cursor and the db when done.
        cursor.close();
        db.close();
        return returnList;
    }

    public boolean loadDatabase() {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_FLOOR_CATEGORY,"Laminate" );
        cv.put(COLUMN_FLOOR_PRICE, 20);
        cv.put(COLUMN_FLOOR_TYPE, "Regular laminate");
        cv.put(COLUMN_FLOOR_SPECIES, "Not Applicable");
        cv.put(COLUMN_FLOOR_COLOR, "Black");
        cv.put(COLUMN_FLOOR_BRAND, "Armstrong");
        cv.put(COLUMN_FLOOR_SIZE, 15);
        cv.put(COLUMN_FLOOR_QUANTITY, 2);
        cv.put(COLUMN_STORE_ID, 1);
        long insert = db.insert(INVENTORY_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }
}