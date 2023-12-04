package dev.example.db.domain.account;

import dev.example.db.domain.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

}
