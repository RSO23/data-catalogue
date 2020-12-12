package rso.datacatalogue.id;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class ParticipantId implements Serializable
{
    private Long matchId;
    private String accountId;
}
