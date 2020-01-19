package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.powerstats.PowerStatsRepository;
import br.com.brainweb.interview.enums.Race;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import org.assertj.core.api.AbstractBooleanAssert;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class HeroRepositoryTest {

    @Autowired private HeroRepository heroRepository;
    @Autowired private PowerStatsRepository powerStatsRepository;
    @Autowired private JdbcTemplate jdbcTemplate;

    @Test
    public void saveHero(){
        Hero hero = new Hero(null, "batman", Race.HUMAN, null, Boolean.TRUE, new Date(), new Date());
        hero.setId(UUID.fromString("0609e93d-5b84-49f6-a99e-507f3160f5a8"));

        PowerStats powerStats = new PowerStats(null, Short.valueOf("1"), Short.valueOf("1"), Short.valueOf("1"), Short.valueOf("1"), new Date(), new Date());
        powerStats.setId(UUID.fromString("22c94aca-5086-4067-9ea3-d6a9a3dfa5e8"));
        PowerStats createdPowerStats = powerStatsRepository.save(powerStats);
        assertThat(createdPowerStats.getId()).isEqualTo(UUID.fromString("22c94aca-5086-4067-9ea3-d6a9a3dfa5e8"));

        hero.setPowerStats(powerStats);
        Hero createdHero = heroRepository.save(hero);
        assertThat(createdHero.getId()).isEqualTo(UUID.fromString("0609e93d-5b84-49f6-a99e-507f3160f5a8"));

    }

    @Test
    public void updateHero(){
        PowerStats powerStats = new PowerStats(UUID.fromString("22c94aca-5086-4067-9ea3-d6a9a3dfa5e8"), Short.valueOf("11"), Short.valueOf("1"), Short.valueOf("1"), Short.valueOf("1"), new Date(), new Date());
        PowerStats updatedPowerStats = powerStatsRepository.save(powerStats);
        assertThat(updatedPowerStats.getStrength()).isEqualTo(Short.valueOf("11"));

        Hero hero = new Hero(UUID.fromString("0609e93d-5b84-49f6-a99e-507f3160f5a8"), "batman x", Race.HUMAN, powerStats, Boolean.TRUE, new Date(), new Date());
        Hero updatedHero = heroRepository.save(hero);
        assertThat(updatedHero.getName()).isEqualTo("batman x");

    }

    @Test
    public void deleteHero(){

        Hero hero = heroRepository.findById(UUID.fromString("0609e93d-5b84-49f6-a99e-507f3160f5a8"));

        assertThat(hero.getId()).isEqualTo(UUID.fromString("0609e93d-5b84-49f6-a99e-507f3160f5a8"));

        powerStatsRepository.delete(hero.getPowerStats());
        heroRepository.delete(hero);

        assertThat(powerStatsRepository.findById(UUID.fromString("22c94aca-5086-4067-9ea3-d6a9a3dfa5e8"))).isNull();
        assertThat(heroRepository.findById(UUID.fromString("0609e93d-5b84-49f6-a99e-507f3160f5a8"))).isNull();
    }

    @After
    public void tearDown() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "power_stats");
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "hero");
    }

}
