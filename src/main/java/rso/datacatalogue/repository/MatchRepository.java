package rso.datacatalogue.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rso.datacatalogue.entity.Match;

public interface MatchRepository extends JpaRepository<Match, Long>
{
    List<Match> getAllByTimestampAfter(long timestamp);

    @Query("SELECT DISTINCT match FROM Match match "
                   + "LEFT JOIN FETCH match.participants p "
                   + "WHERE match.gameId IN "
                   + "(SELECT match2 FROM Match match2 "
                   + "LEFT JOIN match2.participants p2 "
                   + "WHERE p2.accountId = :accountId )")
    List<Match> getAllByAccountId(String accountId);

    @Query(value = "SELECT DISTINCT match FROM Match match "
                   + "LEFT JOIN FETCH match.participants p "
                   + "WHERE match.gameId IN "
                   + "(SELECT match2 FROM Match match2 "
                   + "LEFT JOIN match2.participants p2 "
                   + "WHERE p2.accountId = :accountId )",
           countQuery = "SELECT DISTINCT count(match) FROM Match match "
                   + "LEFT JOIN match.participants p "
                   + "WHERE match.gameId IN "
                   + "(SELECT match2 FROM Match match2 "
                   + "LEFT JOIN match2.participants p2 "
                   + "WHERE p2.accountId = :accountId )")
    Page<Match> getPageOfMatchesByAccountId(String accountId, Pageable pageable);

}
