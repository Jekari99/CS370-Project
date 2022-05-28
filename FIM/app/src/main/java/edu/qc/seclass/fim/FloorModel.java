package edu.qc.seclass.fim;

public class FloorModel {

    private String floorCategory, floorType, floorSpecies, floorColor, floorBrand;
    private int floorPrice, floorSize, floorQuantity;
    private int floorID, storeID;



    public FloorModel(int floorID, String floorCategory, int floorPrice, String floorType,
                      String floorSpecies, String floorColor, String floorBrand,
                      int floorSize, int floorQuantity, int storeID) {
        this.floorID = floorID;
        this.floorCategory = floorCategory;
        this.floorPrice = floorPrice;
        this.floorType = floorType;
        this.floorSpecies = floorSpecies;
        this.floorColor = floorColor;
        this.floorBrand = floorBrand;
        this.floorSize = floorSize;
        this.floorQuantity = floorQuantity;
        this.storeID = storeID;
    }


    public int getStoreID() { return storeID; }

    public String getFloorCategory() { return floorCategory; }

    public String getFloorType () { return floorType; }

    public String getFloorSpecies() { return floorSpecies; }

    public String getFloorColor() { return floorColor; }

    public String getFloorBrand() { return floorBrand; }

    public int getFloorPrice() {
        return floorPrice;
    }

    public int getFloorID() { return floorID; }

    public int getFloorQuantity() { return floorQuantity; }

    public int getFloorSize() { return floorSize; }

    public void setStoreID(int storeID) { this.storeID = storeID; }

    public void setFloorBrand(String floorBrand) { this.floorBrand = floorBrand; }

    public void setFloorColor(String floorColor) { this.floorColor = floorColor; }

    public void setFloorSize(int floorSize) { this.floorSize = floorSize; }

    public void setFloorSpecies(String floorSpecies) { this.floorSpecies = floorSpecies; }

    public void setFloorQuantity(int floorQuantity) { this.floorQuantity = floorQuantity; }

    public void setFloorCategory(String floorCategory) {
        this.floorCategory = floorCategory;
    }

    public void setFloorPrice(int floorPrice) {
        this.floorPrice = floorPrice;
    }

    public void setFloorID(int floorID) { this.floorID = floorID;}

    public void setFloorType(String floorType) { this.floorType = floorType; }

    @Override
    public String toString() {
        return "FloorModel{" +
                "floorID=" + floorID +
                ", floorCategory='" + floorCategory + '\'' +
                ", floorPrice=" + floorPrice + '\'' +
                ", floorType=" + floorType + '\'' +
                ", floorSpecies=" + floorSpecies + '\'' +
                ", floorColor=" + floorColor + '\'' +
                ", floorBrand=" + floorBrand + '\'' +
                ", floorSize=" + floorSize + '\'' +
                ", floorQuantity=" + floorQuantity + '\'' +
                '}';
    }
}
