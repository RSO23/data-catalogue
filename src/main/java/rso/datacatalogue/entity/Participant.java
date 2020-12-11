package rso.datacatalogue.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Participant
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String accountId;

    private int profileIcon;

    private int championId;

    private boolean win;

    private int kills;

    private int deaths;

    private int assists;

    private int largestMultiKill;

    @ManyToOne(targetEntity = Match.class, optional = false)
    private Match match;

}
