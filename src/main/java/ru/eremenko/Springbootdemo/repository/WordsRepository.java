package ru.eremenko.Springbootdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.eremenko.Springbootdemo.model.NewWords;

public interface WordsRepository extends JpaRepository<NewWords, Long> {

}
