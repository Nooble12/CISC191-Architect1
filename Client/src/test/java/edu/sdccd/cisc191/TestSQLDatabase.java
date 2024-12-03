package edu.sdccd.cisc191;

import edu.sdccd.cisc191.template.PlayerData.BankAccount;
import edu.sdccd.cisc191.template.PlayerData.PlayerStatistics;
import edu.sdccd.cisc191.template.Repository.PlayerStatisticRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = TestMain.class)
@ActiveProfiles("test")
@EnableJpaRepositories(basePackages = {"edu.sdccd.cisc191.template.Repository"})
public class TestSQLDatabase {

    @Autowired
    private PlayerStatisticRepository repository;

    @BeforeEach
    public void cleanupDatabase() {
        // Delete all PlayerStatistics before each test
        repository.deleteAll();
    }

    @Test
    public void testSavePlayerData_whenDataIsUpdated() {
        // Attempt to find the player with ID 1
        PlayerStatistics stats = new PlayerStatistics();

        // Create a new BankAccount and set its values
        BankAccount account = new BankAccount();
        account.setTotalMoneyGained(69420);
        account.setTotalMoneySpent(69420);

        // Update stats with account data
        stats.setTotalMoneyGained(account.getTotalMoneyGained());
        stats.setTotalMoneySpent(account.getTotalMoneySpent());

        // Save the updated stats back to the repository
        repository.save(stats);

        // Reload the stats and verify the changes
        PlayerStatistics loadedStats = repository.findById(1L).orElse(null);
        assertEquals(69420, loadedStats.getTotalMoneySpent());
        assertEquals(69420, loadedStats.getTotalMoneyGained());
    }

}
