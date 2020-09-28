import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Person {
    String name;
    List<Person> chilren = new ArrayList<>();

    Person(String name) {
        this.name = name;
    }
}

class ThroneInheritance {
    Person king;
    Map<String, Person> nameToPerson = new HashMap<>();
    Set<String> deadNames = new HashSet<>();

    public ThroneInheritance(String kingName) {
        king = new Person(kingName);
        nameToPerson.put(kingName, king);
    }

    public void birth(String parentName, String childName) {
        Person child = new Person(childName);
        nameToPerson.put(childName, child);

        nameToPerson.get(parentName).chilren.add(child);
    }

    public void death(String name) {
        deadNames.add(name);
    }

    public List<String> getInheritanceOrder() {
        List<String> inheritanceOrder = new ArrayList<>();
        search(inheritanceOrder, king);

        return inheritanceOrder;
    }

    private void search(List<String> inheritanceOrder, Person person) {
        if (!deadNames.contains(person.name)) {
            inheritanceOrder.add(person.name);
        }

        for (Person child : person.chilren) {
            search(inheritanceOrder, child);
        }
    }
}

// Your ThroneInheritance object will be instantiated and called as such:
// ThroneInheritance obj = new ThroneInheritance(kingName);
// obj.birth(parentName,childName);
// obj.death(name);
// List<String> param_3 = obj.getInheritanceOrder();
