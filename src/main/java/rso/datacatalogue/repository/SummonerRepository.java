package rso.datacatalogue.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import rso.datacatalogue.entity.Summoner;

public interface SummonerRepository extends JpaRepository<Summoner, String>
{
    Optional<Summoner> getByUsername(String username);
}
