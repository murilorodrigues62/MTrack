package br.com.rodrigues.murilo.mtrack.domain.model;

/**
 * Created by root on 13/02/17.
 */
public class Product {
    private int idProduct;
    private String productCode;
    private String productName;

    public Product() {
    }

    public Product(int idProduct, String productCode, String productName) {
        this.idProduct = idProduct;
        this.productName = productName;
        this.productCode = productCode;
    }
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;
        return idProduct == product.idProduct;
    }

    @Override
    public int hashCode() {
        return idProduct;
    }

    @Override
    public String toString() {
        return productCode +
               " - " + productName;
    }
}
