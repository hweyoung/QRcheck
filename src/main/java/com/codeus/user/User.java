package com.codeus.user;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class User {

    //entity는 카멜케이스로 작성
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(nullable = false)
    private String pwd;

//    @Column(nullable = false)
    private String name;

    @Column(name = "UUID")
    private String UUID;
}
