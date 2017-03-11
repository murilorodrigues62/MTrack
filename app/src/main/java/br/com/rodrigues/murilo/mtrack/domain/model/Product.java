package br.com.rodrigues.murilo.mtrack.domain.model;

/**
 * Created by root on 13/02/17.
 */
public class Product {
    private int id;
    private String code;
    private String name;

    public Product() {
    }

    public Product(int id, String code, String name) {
        this.id = id;
        this.name = name;
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id == product.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return code +
               " - " + name;
    }
}
