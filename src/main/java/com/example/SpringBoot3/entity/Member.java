package com.example.SpringBoot3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    public void patch(Member memberEntity) {
        if (memberEntity.email != null) {
            this.email = memberEntity.email;
        }
        if (memberEntity.password != null) {
            this.password = memberEntity.password;
        }
    }
}
