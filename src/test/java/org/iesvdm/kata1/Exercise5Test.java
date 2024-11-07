package org.iesvdm.kata1;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Exercise5Test extends PetDomainForKata
{
    @Test
    @Tag("KATA")
    public void partitionPetAndNonPetPeople()
    {
        //TODO
        // Obtain a partition over people with or without pets
        List<Person> partitionListPetPeople = people.stream().filter(person -> !person.getPets().isEmpty()).toList();
        List<Person> partitionListNotPetPeople = people.stream().filter(person -> person.getPets().isEmpty()).toList();

        Assertions.assertEquals(7, partitionListPetPeople.size());
        Assertions.assertEquals(1, partitionListNotPetPeople.size());
    }

    @Test
    @Tag("KATA")
    @DisplayName("getOldestPet - 🐶")
    public void getOldestPet()
    {
        //TODO
        // obtain the oldest pet
        Pet oldestPet = people.stream().flatMap(person -> person.getPets().stream())
                .max(Comparator.comparingInt(Pet::getAge)).orElse(null);
        Assertions.assertEquals(PetType.DOG, oldestPet.getType());
        Assertions.assertEquals(4, oldestPet.getAge());
    }

    @Test
    @Tag("KATA")
    public void getAveragePetAge()
    {
        //TODO
        // obtain the average age of the pets
        double averagePetAge = people.stream().flatMap(person -> person.getPets().stream())
                .mapToDouble(Pet::getAge).average().orElse(0.0);;
        Assertions.assertEquals(1.88888, averagePetAge, 0.00001);
    }

    @Test
    @Tag("KATA")
    public void addPetAgesToExistingSet()
    {
        //TODO
        // obtain the set of pet ages
        Set<Integer> petAges = people.stream().flatMap(person -> person.getPets().stream()).map(Pet::getAge).collect(Collectors.toSet());

        var expectedSet = Set.of(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedSet, petAges);
    }

    @Test
    @Tag("KATA")
    @DisplayName("findOwnerWithMoreThanOnePetOfTheSameType - 🐹 🐹")
    public void findOwnerWithMoreThanOnePetOfTheSameType()
    {
        //TODO
        // obtain owner with more than one pet of the same type
        Person petOwner = people.stream().filter(person -> !person.getPets().isEmpty())
                .filter(person -> person.getPets().stream().map(Pet::getType).toList().size()>
                        person.getPets().stream().map(Pet::getType).distinct().toList().size()).
                findFirst().orElse(null);

        Assertions.assertEquals("Harry Hamster", petOwner.getFullName());
        //TODO
        String petS = petOwner.getPets().stream()
                .map(pet -> pet.getType().toString())
                .collect(Collectors.joining(" "));

        Assertions.assertEquals("🐹 🐹", petS);

        Assertions.assertEquals("🐹 🐹", petS);
    }
}
