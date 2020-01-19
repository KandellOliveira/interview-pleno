package br.com.brainweb.interview.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity(name = "power_stats")
public class PowerStats implements Serializable, Comparable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private UUID id;

    @Column(nullable = false)
    private Short strength;

    @Column(nullable = false)
    private Short agility;

    @Column(nullable = false)
    private Short dexterity;

    @Column(nullable = false)
    private Short intelligence;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Override
    public int compareTo(Object o) {
        return compareTo(o);
    }

    public HashMap compareTo(PowerStats o) {
        HashMap diferencaPowerStatsc = new HashMap();

        diferencaPowerStatsc.put("herios", this.getId()+","+o.getId());

        if (this.strength < o.strength) {
            diferencaPowerStatsc.put("strength", (this.strength - o.strength));
        }else{
            diferencaPowerStatsc.put("strength", (this.strength - o.strength));
        }

        if (this.agility < o.agility) {
            diferencaPowerStatsc.put("strength", (this.agility - o.agility));
        }else{
            diferencaPowerStatsc.put("strength", (this.agility - o.agility));
        }

        if (this.dexterity < o.dexterity) {
            diferencaPowerStatsc.put("strength", (this.dexterity - o.dexterity));
        }else{
            diferencaPowerStatsc.put("strength", (this.dexterity - o.dexterity));
        }

        if (this.strength < o.strength) {
            diferencaPowerStatsc.put("strength", (this.intelligence - o.intelligence));
        }else{
            diferencaPowerStatsc.put("strength", (this.intelligence - o.intelligence));
        }

        return diferencaPowerStatsc;
    }
}
