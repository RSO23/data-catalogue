package rso.datacatalogue.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Match
{
    @Id
    private Long gameId;

    private String platformId;

    private Integer champion;

    private Integer queue;

    private Integer season;

    private Long timestamp;

    private String role;

    private String lane;

    private Long duration;

    @OneToMany(
            targetEntity = Participant.class,
            cascade = CascadeType.ALL,
            mappedBy = "match",
            fetch = FetchType.LAZY
    )
    private Set<Participant> participants;

}
