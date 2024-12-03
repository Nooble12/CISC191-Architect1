package edu.sdccd.cisc191.template.Repository;

import edu.sdccd.cisc191.template.PlayerData.PlayerStatistics;
import org.springframework.data.repository.CrudRepository;

public interface PlayerStatisticRepository extends CrudRepository<PlayerStatistics, Long>
{

}
