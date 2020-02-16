package com.gayan.allocationservice.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Data
@Entity
public class Allocation {

    /*
    When Hibernate loads objects from the DB, it returns proxied objects which look like your Advertisment or SessionT but have more "stuff" in them (to handle their relationship to the session, the internal state of lazy loaded collections etc.).

    This throws off the Jackson serializer since it relies on introspection to find our the properties of the objects.
     */

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer empId;
    private String startDate;
    private String endDate;
    private String projectCode;


}
