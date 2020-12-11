package rso.datacatalogue.dto.championRates;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import rso.datacatalogue.enums.RoleEnum;

@Data
@AllArgsConstructor
public class ChampionRatesDto
{
    private Integer championId;

    private Map<RoleEnum, ChampionRatesRoleDto> championRatesByRole;
}
