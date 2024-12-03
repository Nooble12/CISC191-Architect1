package edu.sdccd.cisc191.template.PlayerData;

import javax.persistence.*;

@Entity
@Table(name = "player_statistics")
public class PlayerStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key for the entity

    @Column(name = "total_money_gained")
    private int totalMoneyGained;

    @Column(name = "total_money_spent")
    private int totalMoneySpent;

    public PlayerStatistics() {}

    public int getTotalMoneyGained() {
        return totalMoneyGained;
    }

    public int getTotalMoneySpent() {
        return totalMoneySpent;
    }

    public void addTotalMoneySpent(int inMoney) {
        totalMoneySpent += inMoney;
    }

    public void addTotalMoneyGained(int inMoney) {
        totalMoneyGained += inMoney;
    }

    public void setTotalMoneySpent(int inMoney) {
        totalMoneySpent = inMoney;
    }

    public void setTotalMoneyGained(int inMoney) {
        totalMoneyGained = inMoney;
    }

    @Override
    public String toString() {
        return "Total Money Gained: " + totalMoneyGained + "\n" + " Money Spent: " + totalMoneySpent;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
