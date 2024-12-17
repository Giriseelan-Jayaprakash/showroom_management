package com.showroommanagement.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "sales")
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name = "sales_date")
    private Date salesDate;
    @Column(name = "sales_price")
    private Date salesPrice;

    @ManyToOne()
    @JoinColumn(name = "showroom_id")
    private Showroom showroom;

    @ManyToOne()
    @JoinColumn(name = "salesman_id")
    private Salesman salesman;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne()
    @JoinColumn(name = "bike_id")
    private Bike bike;

    public Date getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(Date salesDate) {
        this.salesDate = salesDate;
    }

    public Date getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(Date salesPrice) {
        this.salesPrice = salesPrice;
    }

    public Showroom getShowroom() {
        return showroom;
    }

    public void setShowroom(Showroom showroom) {
        this.showroom = showroom;
    }

    public Salesman getSalesman() {
        return salesman;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }
}
