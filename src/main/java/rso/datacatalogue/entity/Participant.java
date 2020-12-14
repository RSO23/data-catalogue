package rso.datacatalogue.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import rso.datacatalogue.id.ParticipantId;

@Entity
@Getter
@Setter
@IdClass(ParticipantId.class)
public class Participant
{
    @Id
    private String accountId;

    @Id
    private Long matchId;

    private String username;

    private String profileIconUrl;

    private String champion;

    private String championIconUrl;

    private boolean win;

    private int kills;

    private int deaths;

    private int assists;

    private int largestMultiKill;

    private int teamId;

    @ManyToOne(targetEntity = Match.class, optional = false)
    private Match match;

}
