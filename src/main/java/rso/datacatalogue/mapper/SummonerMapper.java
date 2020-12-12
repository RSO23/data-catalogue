package rso.datacatalogue.mapper;

import rso.datacatalogue.dto.orianna.SummonerDto;
import rso.datacatalogue.entity.Summoner;

public class SummonerMapper
{
    public static SummonerDto mapToDto(Summoner summoner) {
        SummonerDto summonerDto = new SummonerDto();
        summonerDto.setId(summoner.getId());
        summonerDto.setAccountId(summoner.getAccountId());
        summonerDto.setUsername(summoner.getUsername());
        summonerDto.setProfileIconId(summoner.getProfileIconId());
        summonerDto.setPuuid(summoner.getPuuid());
        summonerDto.setSummonerLevel(summoner.getSummonerLevel());
        return summonerDto;
    }

    public static Summoner mapToEntity(SummonerDto summonerDto) {
        Summoner summoner = new Summoner();
        summoner.setId(summonerDto.getId());
        summoner.setAccountId(summonerDto.getAccountId());
        summoner.setUsername(summonerDto.getUsername());
        summoner.setProfileIconId(summonerDto.getProfileIconId());
        summoner.setPuuid(summonerDto.getPuuid());
        summoner.setSummonerLevel(summonerDto.getSummonerLevel());
        return summoner;
    }

    public static Summoner updateEntity(Summoner summoner, Summoner updatedSummoner) {
        summoner.setUsername(updatedSummoner.getUsername());
        summoner.setAccountId(updatedSummoner.getAccountId());
        summoner.setProfileIconId(updatedSummoner.getProfileIconId());
        summoner.setPuuid(updatedSummoner.getPuuid());
        summoner.setSummonerLevel(updatedSummoner.getSummonerLevel());
        return summoner;
    }
}