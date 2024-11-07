

package org.iesvdm.kata1;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.abs;


public class  Exercise4Test extends PetDomainForKata
{
    @Test
    @Tag("KATA")
    public void getAgeStatisticsOfPets()
    {

        //TODO
        // Replace by stream of petAges
        var petAges = people.stream().flatMap(person -> person.getPets()
                        .stream())
                .map(Pet::getAge);

        var uniqueAges = petAges.collect(Collectors.toSet());

        var expectedSet = Set.of(1, 2, 3, 4);
        Assertions.assertEquals(expectedSet, uniqueAges);

        //TODO
        // Replace by stream
        // IntSummaryStatistics is a class in JDK 8 use it over petAges
        IntSummaryStatistics stats = people.stream()
                .flatMap(person -> person.getPets()
                        .stream())
                .mapToInt(Pet::getAge)
                        .summaryStatistics();

        //TODO
        // Replace 0 by stream over petAges
        Assertions.assertEquals(stats.getMin(), 0);
        Assertions.assertEquals(stats.getMax(), 0);
        Assertions.assertEquals(stats.getSum(), 0);
        Assertions.assertEquals(stats.getAverage(), 0.0, 0.0);
        Assertions.assertEquals(stats.getCount(), 0);



        //TODO
        // Replace by correct stream
        // All age > 0
        //Assertions.assertTrue(petAges.allMatch(p -> p > 0));
        //TODO
        // No one ages == 0
        //Assertions.assertFalse(petAges.allMatch(integer -> integer==0));
        //TODO
        // No one age < 0
        Assertions.assertTrue(petAges.allMatch(integer -> integer<0));
    }

    @Test
    @Tag("KATA")
    @DisplayName("bobSmithsPetNamesAsString - 🐱 🐶")
    public void bobSmithsPetNamesAsString()
    {

        //TODO
        // find Bob Smith
        Person person = people.stream().filter(person1 -> person1.named("Bob Smith")).findFirst().orElse(null);

        //TODO
        // get Bob Smith's pets' names
        String names = person.getPets().stream().map(Pet::getName).collect(Collectors.joining(", "));
        Assertions.assertEquals("Dolly & Spot", names);
    }

    @Test
    @Tag("KATA")
    public void immutablePetCountsByEmoji()
    {

        //TODO
        // Unmodificable map of counts
        Map<String, Long> countsByEmoji = people.stream().
        flatMap(person -> person.getPets().stream().map(Pet::getType)).collect(Collectors.groupingBy(PetType::toString, Collectors.counting()));


        Assertions.assertEquals(
                Map.of("🐱", 2L, "🐶", 2L, "🐹", 2L, "🐍", 1L, "🐢", 1L, "🐦", 1L),
                countsByEmoji
        );
    }

    /**
     * The purpose of this test is to determine the top 3 pet types.
     */
    @Test
    @Tag("KATA")
    @DisplayName("topThreePets - 🐱 🐶 🐹")
    public void topThreePets()
    {

        //TODO
        // Obtain three top pets
        var favorites = people.stream().flatMap(person -> person.getPets().stream())
                .collect(Collectors.groupingBy(Pet::getType,Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry
                        .comparingByValue(
                                Comparator
                                        .reverseOrder()))
                .limit(3).toList();

        Assertions.assertEquals(3, favorites.size());

        Assertions.assertTrue(favorites.contains(new AbstractMap.SimpleEntry<>(PetType.CAT, Long.valueOf(2))));
        Assertions.assertTrue(favorites.contains(new AbstractMap.SimpleEntry<>(PetType.DOG, Long.valueOf(2))));
        Assertions.assertTrue(favorites.contains(new AbstractMap.SimpleEntry<>(PetType.HAMSTER, Long.valueOf(2))));

    }

    @Test
    @Tag("KATA")
    public void getMedianOfPetAges()
    {

        //TODO
        // Obtain pet ages
        var petAges = people.stream().flatMap(person -> person.getPets().stream())
                .map(Pet::getAge);

        //TODO
        // sort pet ages
        var sortedPetAges = petAges.sorted().toList();

        double median;
        if (0 == sortedPetAges.size() % 2)
        {
            //TODO
            //
            // The median of a list of even numbers is the average of the two middle items
            median = (double) (sortedPetAges.get
                    (sortedPetAges.size() / 2) + sortedPetAges.get((sortedPetAges.size() / 2) + 1)) /2;
        }
        else
        {
            // The median of a list of odd numbers is the middle item
            median = sortedPetAges.get(abs(sortedPetAges.size() / 2)).doubleValue();
        }

        Assertions.assertEquals(2.0, median, 0.0);
    }
}
