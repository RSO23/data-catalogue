package rso.datacatalogue.mapper;

import rso.datacatalogue.dto.orianna.ParticipantDto;
import rso.datacatalogue.entity.Match;
import rso.datacatalogue.entity.Participant;

public class ParticipantMapper
{
    private ParticipantMapper()
    {
        // empty
    }

    public static Participant mapToEntity(ParticipantDto participantDto, Match match) {
        Participant participant = new Participant();
        participant.setMatchId(match.getGameId());
        participant.setAccountId(participantDto.getAccountId());
        participant.setUsername(participantDto.getUsername());
        participant.setProfileIconUrl(participantDto.getProfileIconUrl());
        participant.setChampion(participantDto.getChampion());
        participant.setChampionIconUrl(participantDto.getChampionIconUrl());
        participant.setWin(participantDto.isWin());
        participant.setKills(participantDto.getKills());
        participant.setDeaths(participantDto.getDeaths());
        participant.setAssists(participantDto.getAssists());
        participant.setLargestMultiKill(participantDto.getLargestMultiKill());
        participant.setTeamId(participantDto.getTeamId());



        participant.setMatch(match);
        return participant;
    }

    public static ParticipantDto mapToDto(Participant participant) {
        ParticipantDto participantDto = new ParticipantDto();
        participantDto.setUsername(participant.getUsername());
        participantDto.setAccountId(participant.getAccountId());
        participantDto.setProfileIconUrl(participant.getProfileIconUrl());
        participantDto.setChampion(participant.getChampion());
        participantDto.setChampionIconUrl(participant.getChampionIconUrl());
        participantDto.setWin(participant.isWin());
        participantDto.setKills(participant.getKills());
        participantDto.setDeaths(participant.getDeaths());
        participantDto.setAssists(participant.getAssists());
        participantDto.setLargestMultiKill(participant.getLargestMultiKill());
        participantDto.setTeamId(participant.getTeamId());

        return participantDto;
    }

}
