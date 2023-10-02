package com.estudo.minharinhaapp;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface PeopleMapper {

    @Mapping(target = "id", source = "uuidId")
    @Mapping(target = "surname", source = "people.apelido")
    @Mapping(target = "name", source = "people.nome")
    @Mapping(target = "birthDate", source = "people.nascimento")
    @Mapping(target = "searchText", ignore = true)
    PeopleEntity toEntity(UUID uuidId, People people);


    @Mapping(target = "apelido", source = "surname")
    @Mapping(target = "nome", source = "name")
    @Mapping(target = "nascimento", source = "birthDate")
    People toModel(PeopleEntity peopleEntity);

}
