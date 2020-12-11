package rso.datacatalogue.dto.requests;

import java.util.Set;

import lombok.Data;

@Data
public class MatchesRegionDto
{
    private Set<Long> matchIds;
}
