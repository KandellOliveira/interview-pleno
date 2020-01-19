package br.com.brainweb.interview.core.dto;

import br.com.brainweb.interview.enums.Race;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Herodto {

    @NotNull(message = "Name required")
    private String name;

    @NotNull(message = "Race required")
    private Race race;

    @NotNull(message = "PowerStats required")
    private PowerStats powerStats;

    @NotNull(message = "Enabled required")
    private Boolean enabled;

    public Hero transformToHero(){
        Hero hero = new Hero(null,this.name, this.race, this.powerStats, this.enabled, null, null);
        return hero;
    }
}
