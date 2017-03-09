package br.com.rodrigues.murilo.mtrack.model;

/**
 * Created by root on 08/03/17.
 */
public class Order {
    private String id;
    private String order;
    private String client;

    public Order(String id, String order, String client) {
        this.id = id;
        this.order = order;
        this.client = client;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return id.equals(order.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
