package ru.eremenko.Springbootdemo.model;

import lombok.Data;


import javax.persistence.*;

@Data
@Entity
@Table(name = "new_words")
public class NewWords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name = "word")
    public String words;
    @Column(name = "translate")
    public String translate;
    @Column(name = "count")
    public Integer count;
}
