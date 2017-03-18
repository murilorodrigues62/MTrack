package br.com.rodrigues.murilo.mtrack.domain.model;

public class Customer {
    private int idCustomer;
    private String customerName;
    private String customerCode;

    public Customer(){}
    public Customer(int idCustomer, String customerName, String customerCode) {
        this.idCustomer = idCustomer;
        this.customerName = customerName;
        this.customerCode = customerCode;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return idCustomer == customer.idCustomer;

    }

    @Override
    public int hashCode() {
        return (int) (idCustomer ^ (idCustomer >>> 32));
    }

    @Override
    public String toString() {
        return customerName + " " + customerCode;
    }
}
