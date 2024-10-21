package com.tiba.tiba.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "Users")
public class User {


    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String middleName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;



    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Patient patient;


}
/*spring.application.name=tiba

spring.datasource.username=postgres
spring.datasource.password=pksXDJtwPji5k8Er
spring.datasource.url=jdbc:postgresql://barely-mindful-suricate.data-1.use1.tembo.io:5432/postgres
spring.h2.console.enabled=true

spring.jpa.show-sql=true
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database=postgresql
spring.jpa.open-in-view=false
spring.jpa.properties.hibernate.format_sql=true

*/