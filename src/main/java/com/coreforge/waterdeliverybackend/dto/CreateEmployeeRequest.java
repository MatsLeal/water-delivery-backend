package com.coreforge.waterdeliverybackend.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateEmployeeRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "RUD is required")
    private String rud;

    @NotBlank(message = "Area is required")
    private String area;

    public CreateEmployeeRequest() {
    }

    public CreateEmployeeRequest(String name, String rud, String area) {
        this.name = name;
        this.rud = rud;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public String getRud() {
        return rud;
    }

    public String getArea() {
        return area;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRud(String rud) {
        this.rud = rud;
    }

    public void setArea(String area) {
        this.area = area;
    }
}