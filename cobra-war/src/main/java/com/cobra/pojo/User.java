package com.cobra.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class User
{
    @Id
    private Integer id;
    private Integer age;
    private String name;
    private String address;
    private String city;
}


