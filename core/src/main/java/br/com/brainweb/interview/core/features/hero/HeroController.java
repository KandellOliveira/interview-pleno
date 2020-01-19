package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.dto.Herodto;
import br.com.brainweb.interview.core.dto.PowerStatdto;
import br.com.brainweb.interview.core.features.powerstats.PowerStatsService;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "hero")
@RequiredArgsConstructor
public class HeroController {

    @Autowired private HeroService heroService;
    @Autowired private PowerStatsService powerStatsService;

    @RequestMapping("/")
    public String home(){
        return "Hello Sr. Wayne!";
    }

    @PostMapping
    public ResponseEntity<Hero> save(@RequestBody @Valid Herodto herodto, PowerStatdto powerStatdto){
        Hero hero = herodto.transformToHero();
        PowerStats powerStats = powerStatdto.transformToPowerStats();

        PowerStats createdPowerStats = powerStatsService.save(powerStats);

        hero.setPowerStats(createdPowerStats);

        Hero createdHero = heroService.save(hero);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHero);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hero> update(@PathVariable(name = "id") UUID id, @RequestBody @Valid Herodto heroUpdatedto, PowerStatdto powerStatdto){
        Hero hero = heroService.getById(id);

        if (Optional.ofNullable(hero).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Hero heroDto = heroUpdatedto.transformToHero();
        PowerStats powerStats = powerStatdto.transformToPowerStats();

        powerStatsService.save(powerStats);

        heroDto.setId(id);
        heroDto.setPowerStats(powerStats);

        Hero updatedHero = heroService.update(heroDto);
        return ResponseEntity.ok(updatedHero);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Hero> delete(@PathVariable(name = "id") UUID id){
        Hero hero = heroService.getById(id);

        if (Optional.ofNullable(hero).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        powerStatsService.delete(hero.getPowerStats());
        heroService.delete(hero);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hero> findById(@PathVariable(name = "id") UUID id){
        Hero hero = heroService.getById(id);

        if (Optional.ofNullable(hero).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(hero);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Hero>> listAllByNome(@PathVariable(name = "nome") String nome){
        List<Hero> heros = heroService.findAllByNome(nome);
        return ResponseEntity.ok(heros);
    }

    @GetMapping("/{idHerio}/{idHeroiComparado}")
    public ResponseEntity<Object> compararHerois(@PathVariable(name = "idHerio") UUID idHerio, @PathVariable(name = "idHeroiComparado") UUID idHeroiComparado){
        Hero hero = heroService.getById(idHerio);
        Hero heroiComparado = heroService.getById(idHeroiComparado);

        if (Optional.ofNullable(hero).isEmpty() || Optional.ofNullable(heroiComparado).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        PowerStats powerStatsHero = powerStatsService.getById(hero.getPowerStats().getId());
        PowerStats powerStatsheroiComparado = powerStatsService.getById(heroiComparado.getPowerStats().getId());

        HashMap diferencaPowerStats = powerStatsHero.compareTo(powerStatsheroiComparado);

        return ResponseEntity.ok(diferencaPowerStats);

    }

}
