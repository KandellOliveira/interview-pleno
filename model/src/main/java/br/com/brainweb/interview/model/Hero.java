package br.com.brainweb.interview.model;

import br.com.brainweb.interview.enums.Race;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity(name="hero")
public class Hero implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private UUID id;

    @Column(length = 255, nullable = false, unique = true)
    private String name;

    @Column(length = 255, nullable = false)
    @Enumerated(EnumType.STRING)
    private Race race;

    @OneToOne
    @JoinColumn(name = "power_stats_id", nullable = false)
    private PowerStats powerStats;

    @Type(type = "true_false")
    private Boolean enabled;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

}
