package FamilyTree;
import Human.Gender;
import Human.HumanComparatorByAge;
import Human.HumanComparatorByBirthDate;
import java.util.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.io.Serializable;


public class FamilyTree<E extends FamilyTreeItem<E>> implements Serializable, Iterable<E> {
    private Integer humansId = 1;
    private List<E> familyTree = new ArrayList<>();

    public void setItem(E human){
        if (human.getId() == 0){
            human.setId(humansId++);
            familyTree.add(human);
            if (human.getFather() != null && !human.getFather().getChildren().contains(human)){
                human.getFather().setChildren(human);
            }
            if (human.getMother() != null && !human.getMother().getChildren().contains(human)){
                human.getMother().setChildren(human);
            }
            for (E kid: human.getChildren()){
                if (human.getGender() == Gender.Male){
                    kid.setFather(human);
                }
                else{
                    kid.setMother(human);
                }
            }
            if (human.getSpouse() != null){
                human.getSpouse().setSpouse(human);
            }
        }
        else{
            System.out.println(human.getName() + " уже добавлен!");
        }

    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("В дереве " + this.familyTree.size() + " членов семьи" + "\n");
        for (E human: this.familyTree){
            stringBuilder.append("Имя: "+ human.getName() + ". Дата рождения: " + human.getBirthDate());
            stringBuilder.append(". Дата смерти: " + human.getDeathDate());
            stringBuilder.append(". Возраст: " + human.getAge() + " лет");
            if (human.getFather() != null){
                stringBuilder.append(". Отец: " + human.getFather().getName());
            }
            if (human.getMother() != null){
                stringBuilder.append(". Мать: " + human.getMother().getName());
            }
            if (human.getSpouse() != null){
                stringBuilder.append(". Супруг(а): " + human.getSpouse().getName());
            }
            for (E tmp: human.getChildren()) {
                stringBuilder.append(". Дети: " + tmp.getName());
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public String getNameList() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Список имен: \n");
        for (E human: familyTree) {
            stringBuilder.append(human.getName() + "\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new FamilyTreeIterator<>(familyTree);
    }

    public void sortByname() {
        Collections.sort(familyTree);
    }

    public void sortByBirthDate() {
        Collections.sort(familyTree,
                new HumanComparatorByBirthDate());
    }

    public void sortByAge() {
        Collections.sort(familyTree, new HumanComparatorByAge());
    }
}
