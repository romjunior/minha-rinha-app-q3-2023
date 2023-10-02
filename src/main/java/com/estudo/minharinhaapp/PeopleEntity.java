package com.estudo.minharinhaapp;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Table("PESSOAS")
public class PeopleEntity implements Persistable<UUID> {

    @Id
    @Column("ID")
    private UUID id;
    @Column("APELIDO")
    private String surname;
    @Column("NOME")
    private String name;
    @Column("NASCIMENTO")
    private LocalDate birthDate;
    @Column("STACK")
    private List<String> stack;

    @Column("SEARCHTEXT")
    private String searchText;

    public PeopleEntity() {
    }

     public PeopleEntity(UUID id, String surname, String name, LocalDate birthDate, List<String> stack) {
         this.id = id;
         this.surname = surname;
         this.name = name;
         this.birthDate = birthDate;
         this.stack = stack;
     }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return true;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<String> getStack() {
        return stack;
    }

    public void setStack(List<String> stack) {
        this.stack = stack;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeopleEntity that = (PeopleEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname);
    }
}
