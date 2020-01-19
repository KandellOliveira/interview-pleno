package br.com.brainweb.interview.core.dto;

import br.com.brainweb.interview.model.PowerStats;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PowerStatdto {

    @NotNull(message = "Strength required")
    private Short strength;

    @NotNull(message = "Agility required")
    private Short agility;

    @NotNull(message = "Dexterity required")
    private Short dexterity;

    @NotNull(message = "Intelligence required")
    private Short intelligence;

    public PowerStats transformToPowerStats(){
        PowerStats powerStats = new PowerStats(null, this.strength, this.agility, this.dexterity, this.intelligence, null, null);
        return powerStats;
    }

}
