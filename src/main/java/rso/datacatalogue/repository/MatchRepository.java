package rso.datacatalogue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rso.datacatalogue.entity.Match;

public interface MatchRepository extends JpaRepository<Match, Long>
{
    List<Match> getAllByTimestampAfter(long timestamp);

    @Query("SELECT match FROM Match match "
                   + "LEFT JOIN FETCH match.participants p "
                   + "WHERE p.accountId = :accountId ")
    List<Match> getAllByAccountId(String accountId);

}
