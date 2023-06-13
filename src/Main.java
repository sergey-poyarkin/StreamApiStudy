import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

         long below18Count = persons.stream()
                .filter(s -> s.getAge() < 18)
                .count();

         System.out.println("Количество несовершеннолетних: " + below18Count);



        List<String> listOfRecruits = persons.stream()
                .filter(s -> s.getAge() > 18)
                .filter(d -> d.getAge() < 27)
                .map(Person::getFamily)
                .toList();

        List<Person> educated = persons.stream()
                .filter(f -> f.getAge() > 18)
                .filter(g ->
                    (g.getAge() < 60 && g.getSex() == Sex.WOMAN)
                            || (g.getAge() < 65 && g.getSex() == Sex.MAN))
                .sorted(Comparator.comparing(Person::getFamily))
                .toList();


        List<Person> sortedEducated = educated.stream()
                .sorted()
                .toList();


    }
}