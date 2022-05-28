package edu.qc.seclass.fim;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FloorModelTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        String floorCategory = "Wood";
        String floorType = "Engineered";
        String floorSpecies = "Oak";
        String floorColor = "Brown";
        String floorBrand = "Dream Home";
        int floorID = 1;
        int floorSize = 15;
        int floorQuantity = 2;
        int storeId = 2;
        int floorPrice = 20;

        FloorModel floorModel = new FloorModel(floorID, floorCategory, floorPrice,
                floorType, floorSpecies, floorColor, floorBrand, floorSize, floorQuantity,
                storeId);
        floorModel.toString();

        assertEquals("FloorModel{" +
                        "floorID=" + floorID +
                        ", floorCategory='" + floorCategory + '\'' +
                        ", floorPrice=" + floorPrice + '\'' +
                        ", floorType=" + floorType + '\'' +
                        ", floorSpecies=" + floorSpecies + '\'' +
                        ", floorColor=" + floorColor + '\'' +
                        ", floorBrand=" + floorBrand + '\'' +
                        ", floorSize=" + floorSize + '\'' +
                        ", floorQuantity=" + floorQuantity + '\'' +
                        '}',
                floorModel.toString());
    }
}