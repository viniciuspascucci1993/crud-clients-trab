package com.vinicius.clients.crud.crudclients.dto;

import com.vinicius.clients.crud.crudclients.entities.Client;

import java.io.Serializable;
import java.time.Instant;

public class ClientDTO implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String cpf;
    private Double income;

    private Instant birthDate;

    private Integer children;

    public ClientDTO( Client entitie ) {
        this.id = entitie.getId();
        this.name = entitie.getName();
        this.cpf = entitie.getCpf();
        this.income = entitie.getIncome();
        this.birthDate = entitie.getBirthDate();
        this.children = entitie.getChildren();
    }


    /* Default Constructor*/
    public ClientDTO( ) { }

    /* Constructor using fields*/
    public ClientDTO(Long id, String name, String cpf, Double income, Instant birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Instant getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

}

