import Animal.Animal;
import FamilyTree.FamilyTree;
import Human.Human;
import Human.Gender;
import Service.FileHandler;
import Service.Rewritable;
import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FamilyTree familyTree = new FamilyTree();
        FamilyTree animalsFamilyTree = new FamilyTree();

        Human i = new Human();
        Human wife = new Human();
        Human father = new Human();
        Human mother = new Human();
        Human kidAndrey = new Human();
        Human kidOlga = new Human();

        Animal sharik = new Animal();
        Animal zhuchka = new Animal();
        Animal bobik = new Animal();
        Animal strelka = new Animal();
        Animal tuzik = new Animal();
        Animal belka = new Animal();

        i.setId(0);
        i.setName("Иванов Сергей Петрович");
        i.setGender(Gender.Male);
        i.setBirthDate(LocalDate.of(1983, 11, 15), null);
        i.setFather(father);
        i.setMother(mother);
        i.setSpouse(wife);
        i.setChildren(kidAndrey);
        i.setChildren(kidOlga);

        father.setId(0);
        father.setName("Иванов Петр Николаевич");
        father.setGender(Gender.Male);
        father.setBirthDate(LocalDate.of(1962, 7, 13), null);
        father.setChildren(i);

        mother.setId(0);
        mother.setName("Иванова Светлана Николаевна");
        mother.setGender(Gender.Female);
        mother.setBirthDate(LocalDate.of(1963, 7, 19), null);
        mother.setChildren(i);

        wife.setId(0);
        wife.setName("Иванова Надежда Сергеевна");
        wife.setGender(Gender.Female);
        wife.setBirthDate(LocalDate.of(1988, 9, 16), null);
        wife.setSpouse(i);
        wife.setChildren(kidAndrey);
        wife.setChildren(kidOlga);

        kidAndrey.setId(0);
        kidAndrey.setName("Иванов Андрей Сергеевич");
        kidAndrey.setGender(Gender.Male);
        kidAndrey.setBirthDate(LocalDate.of(2020, 1, 23), null);
        kidAndrey.setFather(i);
        kidAndrey.setMother(wife);

        kidOlga.setId(0);
        kidOlga.setName("Иванова Ольга Сергеевна");
        kidOlga.setGender(Gender.Female);
        kidOlga.setBirthDate(LocalDate.of(2023, 4, 25), null);
        kidOlga.setFather(i);
        kidOlga.setMother(wife);

        familyTree.setItem(i);
        familyTree.setItem(father);
        familyTree.setItem(mother);
        familyTree.setItem(wife);
        familyTree.setItem(kidAndrey);
        familyTree.setItem(kidOlga);

        sharik.setId(0);
        sharik.setName("Шарик");
        sharik.setGender(Gender.Male);
        sharik.setBirthDate(LocalDate.of(2015, 12, 1), null);
        sharik.setFather(bobik);
        sharik.setMother(strelka);
        sharik.setBreed("Овчарка");
        sharik.setChildren(tuzik);
        sharik.setChildren(belka);

        zhuchka.setId(0);
        zhuchka.setName("Жучка");
        zhuchka.setGender(Gender.Female);
        zhuchka.setBirthDate(LocalDate.of(2017, 10, 19), null);
        zhuchka.setBreed("Болонка");
        zhuchka.setChildren(tuzik);
        zhuchka.setChildren(belka);

        bobik.setId(0);
        bobik.setName("Бобик");
        bobik.setGender(Gender.Male);
        bobik.setBirthDate(LocalDate.of(2010, 9, 3), null);
        bobik.setChildren(sharik);
        bobik.setBreed("Такса");

        strelka.setId(0);
        strelka.setName("Стрелка");
        strelka.setGender(Gender.Female);
        strelka.setBirthDate(LocalDate.of(2011, 5, 27), null);
        strelka.setChildren(sharik);
        strelka.setBreed("Пудель");

        tuzik.setId(0);
        tuzik.setName("Тузик");
        tuzik.setGender(Gender.Male);
        tuzik.setBirthDate(LocalDate.of(2020, 11, 2), null);
        tuzik.setFather(sharik);
        tuzik.setMother(zhuchka);
        tuzik.setBreed("Спаниель");

        belka.setId(0);
        belka.setName("Белка");
        belka.setGender(Gender.Female);
        belka.setBirthDate(LocalDate.of(2023, 5, 5), null);
        belka.setFather(sharik);
        belka.setMother(zhuchka);
        belka.setBreed("Дог");

        animalsFamilyTree.setItem(sharik);
        animalsFamilyTree.setItem(zhuchka);
        animalsFamilyTree.setItem(belka);
        animalsFamilyTree.setItem(bobik);
        animalsFamilyTree.setItem(strelka);
        animalsFamilyTree.setItem(tuzik);


        System.out.println("Исходное дерево людей:");
        System.out.println(familyTree);
        System.out.println("Исходный список имен:");
        System.out.println(familyTree.getNameList());
        familyTree.sortByname();
        System.out.println("Отсортированный список имен:");;
        System.out.println(familyTree.getNameList());
        familyTree.sortByBirthDate();
        System.out.println("Отсортированное по дате рождения дерево:");
        System.out.println(familyTree);
        familyTree.sortByAge();
        System.out.println("Отсортированное по возрасту дерево:");
        System.out.println(familyTree);
//        FamilyTree.FamilyTree tree = load("familyTree.ser");
//        System.out.println(tree);


//        save(familyTree, "familyTree.ser");
//        System.out.println(loadedFamilyTree);

        System.out.println("Исходное дерево животных:");
        System.out.println(animalsFamilyTree);
        System.out.println("Исходный список кличек:");
        System.out.println(animalsFamilyTree.getNameList());
        animalsFamilyTree.sortByname();
        System.out.println("Отсортированный список кличек:");;
        System.out.println(animalsFamilyTree.getNameList());


    }

    private static void save(FamilyTree tree, String filename) {
        Rewritable rewritable = new FileHandler();
        rewritable.saveToFile(tree, filename);
    }

    private static FamilyTree load(String filename) {
        Rewritable rewritable = new FileHandler();
        return (FamilyTree) rewritable.loadFromFile(filename);
    }
}