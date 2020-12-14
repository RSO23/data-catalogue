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
        summonerDto.setProfileIconUrl(summoner.getProfileIconUrl());
        summonerDto.setPuuid(summoner.getPuuid());
        summonerDto.setSummonerLevel(summoner.getSummonerLevel());
        summonerDto.setDivision(summoner.getDivision());
        summonerDto.setTier(summoner.getTier());
        summonerDto.setLeaguePoints(summoner.getLeaguePoints());
        summonerDto.setWins(summoner.getWins());
        summonerDto.setLosses(summoner.getLosses());
        return summonerDto;
    }

    public static Summoner mapToEntity(SummonerDto summonerDto) {
        Summoner summoner = new Summoner();
        summoner.setId(summonerDto.getId());
        summoner.setAccountId(summonerDto.getAccountId());
        summoner.setUsername(summonerDto.getUsername());
        summoner.setProfileIconUrl(summonerDto.getProfileIconUrl());
        summoner.setPuuid(summonerDto.getPuuid());
        summoner.setSummonerLevel(summonerDto.getSummonerLevel());
        summoner.setDivision(summonerDto.getDivision());
        summoner.setTier(summonerDto.getTier());
        summoner.setLeaguePoints(summonerDto.getLeaguePoints());
        summoner.setWins(summonerDto.getWins());
        summoner.setLosses(summonerDto.getLosses());
        return summoner;
    }

    public static Summoner updateEntity(Summoner summoner, Summoner updatedSummoner) {
        summoner.setUsername(updatedSummoner.getUsername());
        summoner.setAccountId(updatedSummoner.getAccountId());
        summoner.setProfileIconUrl(updatedSummoner.getProfileIconUrl());
        summoner.setPuuid(updatedSummoner.getPuuid());
        summoner.setSummonerLevel(updatedSummoner.getSummonerLevel());
        summoner.setDivision(summoner.getDivision());
        summoner.setTier(summoner.getTier());
        summoner.setLeaguePoints(summoner.getLeaguePoints());
        summoner.setWins(summoner.getWins());
        summoner.setLosses(summoner.getLosses());
        return summoner;
    }
}