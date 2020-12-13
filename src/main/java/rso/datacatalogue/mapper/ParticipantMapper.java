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
        participant.setProfileIcon(participantDto.getProfileIcon());
        participant.setChampion(participantDto.getChampion());
        participant.setWin(participantDto.isWin());
        participant.setKills(participantDto.getKills());
        participant.setDeaths(participantDto.getDeaths());
        participant.setAssists(participantDto.getAssists());
        participant.setLargestMultiKill(participantDto.getLargestMultiKill());
        participant.setMatch(match);
        return participant;
    }

    public static ParticipantDto mapToDto(Participant participant) {
        ParticipantDto participantDto = new ParticipantDto();
        participantDto.setUsername(participant.getUsername());
        participantDto.setAccountId(participant.getAccountId());
        participantDto.setProfileIcon(participant.getProfileIcon());
        participantDto.setChampion(participant.getChampion());
        participantDto.setWin(participant.isWin());
        participantDto.setKills(participant.getKills());
        participantDto.setDeaths(participant.getDeaths());
        participantDto.setAssists(participant.getAssists());
        participantDto.setLargestMultiKill(participant.getLargestMultiKill());
        return participantDto;
    }

}
