package rso.datacatalogue.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Summoner
{
    @Id
    private String id;

    private String username;

    private String accountId;

    private String puuid;

    private String profileIconUrl;

    private Integer summonerLevel;

    private Long lastUpdated;

    private String division;

    private String tier;

    private Integer leaguePoints;

    private Integer wins;

    private Integer losses;

}
