package br.com.rodrigues.murilo.mtrack.domain.model;

public class Order {
    private long id;
    private String charge;
    private String client;

    public Order(long id, String charge, String client) {
        this.id = id;
        this.charge = charge;
        this.client = client;
    }

    @Override
    public String toString() {
        return "Order " + id;
    }

    public Order() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
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

        return id == order.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
