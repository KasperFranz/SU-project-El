/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Mikkel
 */
public class Product {

    private int EANNR;
    private String productName;
    private String productDescription;
    private int storageCount;

    public Product(int EANNR, String productName, String productDescription, int storageCount) {
        this.EANNR = EANNR;
        this.productName = productName;
        this.productDescription = productDescription;
        this.storageCount = storageCount;
    }

    public int getEANNR() {
        return EANNR;
    }

    public void setEANNR(int EANNR) {
        this.EANNR = EANNR;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getStorageCount() {
        return storageCount;
    }

    public void setStorageCount(int storageCount) {
        this.storageCount = storageCount;
    }
}
