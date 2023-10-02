package com.estudo.minharinhaapp;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface PeopleRepository extends CrudRepository<PeopleEntity, UUID> {

    @Query("SELECT * FROM \"PESSOAS\" p WHERE p.\"APELIDO\" = :t OR p.\"NOME\" = :t OR :t=any(p.\"STACK\") LIMIT :limit")
    List<PeopleEntity> findAllBySurnameOrNameOrStackInLimitCustomQuery(String t, long limit);

    @Query("SELECT * FROM \"PESSOAS\" p WHERE p.\"SEARCHTEXT\" ILIKE :t LIMIT :limit")
    List<PeopleEntity> findlAllByTermCustomQuery(StringBuilder t, long limit);


    List<PeopleEntity> findAllBySearchTextLike(String t);
}
