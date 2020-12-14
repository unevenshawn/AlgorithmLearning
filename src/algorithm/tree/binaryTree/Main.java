package algorithm.tree.binaryTree;

import javax.xml.namespace.QName;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
/*        BinarySearchTree bst1=new BinarySearchTree();
        bst1.add(new Person("a",12));
        bst1.add(new Person("b",6));
        bst1.add(new Person("c",36));
        System.out.println("先序遍历");
        System.out.println("年龄从小到大");
        bst1.printBST("pre");
        BinarySearchTree bst2=new BinarySearchTree(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o2.getAge()-o1.getAge();
            }
        });
        bst2.add(new Person("a",12));
        bst2.add(new Person("b",6));
        bst2.add(new Person("c",36));
        System.out.println("年龄从大到小");
        bst2.printBST("pre");
        System.out.println("-----------------");
        System.out.println("中序遍历");
        bst1.printBST("mid");
        bst2.printBST("mid");

        System.out.println("-----------------");
        System.out.println("后序遍历");
        bst1.printBST("post");
        bst2.printBST("post");*/

        BinarySearchTree test = new BinarySearchTree();
        List<Integer> integers = List.of(7, 4, 2, 1, 3, 5, 9, 8, 11, 10, 12);
        for (int i = 0; i < integers.size(); i++) {
            test.add(integers.get(i));
        }
//       test.printBST("pre");
//        System.out.println("-----------------");
//        test.printBST("mid");
//        System.out.println("size=" + test.size());
//        System.out.println("-----------------");
//        System.out.println("层次遍历");
//        test.printBST("level");
//        test.levelTraverse(new BinarySearchTree.Visitor<Integer>() {
//            @Override
//            public boolean visit(Integer integer) {
//                System.out.print("_" + integer + "_");
//                return false;
//            }
//        });
//        System.out.println();
//        System.out.println("contains?=>" + test.contains(7));
//
        test.postTraverse(new Visitor<Integer>() {

            @Override
            boolean visit(Integer integer) {
                System.out.print("_" + integer + "_");
//                if (integer.equals(9)) return true;
//                return false;
                return integer == 9 ? true : false;
            }
        });
        System.out.println();

        System.out.println(test.toString());

        System.out.println("递归树高度=> "+test.treeHeight());

        System.out.println("层序遍历得到高度=》"+test.getHeight());
        test.clear();
        System.out.println("层序遍历得到高度=》"+test.getHeight());
        test.add(1);
        test.add(2);
        System.out.println("层序遍历得到高度=》"+test.getHeight());

    }
}


class Person implements Comparable<Person> {


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int age;
    private String name;

    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Person o) {
        return this.age - o.getAge();
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }
}
