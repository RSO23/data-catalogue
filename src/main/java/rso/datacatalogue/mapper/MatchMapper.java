package rso.datacatalogue.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import rso.datacatalogue.dto.orianna.MatchDto;
import rso.datacatalogue.dto.orianna.ParticipantDto;
import rso.datacatalogue.dto.riotApi.MatchReferenceDto;
import rso.datacatalogue.entity.Match;
import rso.datacatalogue.entity.Participant;

public class MatchMapper
{
    private MatchMapper() {
        // empty
    }

    public static Match mapToEntity(MatchReferenceDto matchReferenceDto) {
        Match match = new Match();
        match.setChampion(matchReferenceDto.getChampion());
        match.setGameId(matchReferenceDto.getGameId());
        match.setLane(matchReferenceDto.getLane());
        match.setPlatformId(matchReferenceDto.getPlatformId());
        match.setQueue(matchReferenceDto.getQueue());
        match.setRole(matchReferenceDto.getRole());
        match.setSeason(matchReferenceDto.getSeason());
        match.setTimestamp(matchReferenceDto.getTimestamp());
        return match;
    }

    public static Match mapToEntity(MatchReferenceDto matchReferenceDto, MatchDto matchDto)
    {
        Match match = new Match();
        match.setChampion(matchReferenceDto.getChampion());
        match.setGameId(matchReferenceDto.getGameId());
        match.setLane(matchReferenceDto.getLane());
        match.setPlatformId(matchReferenceDto.getPlatformId());
        match.setQueue(matchReferenceDto.getQueue());
        match.setRole(matchReferenceDto.getRole());
        match.setSeason(matchReferenceDto.getSeason());
        match.setTimestamp(matchReferenceDto.getTimestamp());

        Set<Participant> participants = matchDto.getParticipants().stream()
                .map((ParticipantDto participantDto) -> ParticipantMapper.mapToEntity(participantDto, match))
                .collect(Collectors.toSet());

        match.setDuration(matchDto.getDuration());
        match.setParticipants(participants);
        return match;
    }

    public static MatchDto mapToDto(Match match) {
        MatchDto matchDto = new MatchDto();
        matchDto.setChampion(match.getChampion());
        matchDto.setGameId(match.getGameId());
        matchDto.setLane(match.getLane());
        matchDto.setPlatformId(match.getPlatformId());
        matchDto.setQueue(match.getQueue());
        matchDto.setRole(match.getRole());
        matchDto.setSeason(match.getSeason());
        matchDto.setTimestamp(match.getTimestamp());

        List<ParticipantDto> participantDtos = match.getParticipants().stream()
                .map(ParticipantMapper::mapToDto)
                .collect(Collectors.toList());

        matchDto.setParticipants(participantDtos);
        return matchDto;
    }

    public static MatchReferenceDto mapToMatchReferenceDto(Match match) {
        MatchReferenceDto matchReferenceDto = new MatchReferenceDto();
        matchReferenceDto.setChampion(match.getChampion());
        matchReferenceDto.setGameId(match.getGameId());
        matchReferenceDto.setLane(match.getLane());
        matchReferenceDto.setPlatformId(match.getPlatformId());
        matchReferenceDto.setQueue(match.getQueue());
        matchReferenceDto.setRole(match.getRole());
        matchReferenceDto.setSeason(match.getSeason());
        matchReferenceDto.setTimestamp(match.getTimestamp());
        return matchReferenceDto;
    }
}
