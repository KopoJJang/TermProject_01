package com.example.termproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Member extends BaseEntity{

    // 기본키(Primary-key) 설정
    @Id
    private String email;
    private String password;
    private String name;
}