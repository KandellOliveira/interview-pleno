package br.com.brainweb.interview.core.features.powerstats;

import br.com.brainweb.interview.core.exception.NotFoundException;
import br.com.brainweb.interview.model.PowerStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class PowerStatsService {

    @Autowired private PowerStatsRepository powerStatsRepository;

    //save
    public PowerStats save(PowerStats powerStats){
        powerStats.setCreatedAt(new Date());
        PowerStats createdPowerStats = powerStatsRepository.save(powerStats);
        return createdPowerStats;
    }

    //update
    public PowerStats update(PowerStats powerStats){
        powerStats.setUpdatedAt(new Date());
        PowerStats updatedPowerStats = powerStatsRepository.save(powerStats);
        return updatedPowerStats;
    }

    //delete
    public void delete(PowerStats powerStats){
        powerStatsRepository.delete(powerStats);
    }

    //get
    public PowerStats getById(UUID id){
        Optional<PowerStats> powerStats = Optional.ofNullable(powerStatsRepository.findById(id));
        return powerStats.orElseThrow(()-> new NotFoundException("There are not hero with id = "+ id));
    }
}
