package com.example.demo3.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TEST_TABLE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "test_id", nullable = false)
    private Long id;

    @Column(name = "test_text")
    private String text;

}
