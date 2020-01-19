package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.exception.NotFoundException;
import br.com.brainweb.interview.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HeroService {

    @Autowired private HeroRepository heroRepository;

    //save
    public Hero save(Hero hero) {

        hero.setCreatedAt(new Date());
        Hero createdHero = heroRepository.save(hero);
        return createdHero;
    }

    //update
    public Hero update(Hero hero) {
        hero.setUpdatedAt(new Date());
        Hero updatedHero = heroRepository.save(hero);
        return updatedHero;
    }

    //delete
    public void delete(Hero hero){
        heroRepository.delete(hero);
    }

    //get
    public Hero getById(UUID id) {
        Hero hero = heroRepository.findById(id);
        return hero;
    }

    public List<Hero> findAllByNome(String nome){
        List<Hero> heros = heroRepository.findAllByName(nome);
        return heros;
    }


}
