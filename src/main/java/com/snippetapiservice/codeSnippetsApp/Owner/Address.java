package com.snippetapiservice.codeSnippetsApp.Owner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    private String addr1;
    private String addr2;
    private String addr3;
    private String city;
    private String state;
    private String zip;

}
