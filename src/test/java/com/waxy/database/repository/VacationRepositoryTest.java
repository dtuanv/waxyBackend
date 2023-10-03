package com.waxy.database.repository;

import com.waxy.database.entity.Vacation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class VacationRepositoryTest {
    @Autowired
    private VacationRepository vacationRepository;
    @Test
    public void saveVacationTest_ReturnSavedVacation(){
        // Arrange
        Vacation vacation = Vacation.builder().businessId(1).start("2023.11.11").toDate("2023.11.30").duration(19)
                .userInfoId(5).title("Test")
                .build();
        // Act
        Vacation savedVacation = vacationRepository.save(vacation);
        // Assert
        org.assertj.core.api.Assertions.assertThat(savedVacation).isNotNull();

        Assertions.assertThat(savedVacation.getId()).isGreaterThan(0);
    }

}