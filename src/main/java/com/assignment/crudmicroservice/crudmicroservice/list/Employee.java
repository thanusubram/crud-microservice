package com.assignment.crudmicroservice.crudmicroservice.list;

public class Employee {

    private Long id;
    private String name;
    private String address;
    public Employee() {
        super();
    }
    public Employee(Long id, String name, String address) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
    }
    public Employee(String name, String address) {
        super();
        this.name = name;
        this.address = address;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    @Override
    public String toString() {
        return String.format("Employee [id=%s, name=%s, address=%s]", id, name, address);
    }
}
