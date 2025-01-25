import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        class Tree<T extends Comparable<T>>{
            class Node{
                Node left;
                Node right;
                final T value;
                Node(T val){ value = val; left = null; right = null;}
            }
            Node root;
            Tree(T val){
                try {
                    if (val != null) root = new Node(val);
                    else throw new IllegalAccessException("Root can not be null");
                } catch (IllegalAccessException ex){
                    System.out.println(ex.getMessage());
                }
            }
            public void nlr(){ nlrp(root);}
            public void lnr(){ lnrp(root);}
            public void lrn(){ lrnp(root);}
            private void nlrp(Node node ){
                if (node == null){
                    return;
                }
                System.out.println(node.value);
                nlrp(node.left);
                nlrp(node.right);
            }
            private void lnrp(Node node ){
                if (node == null){
                    return;
                }
                lnrp(node.left);
                System.out.println(node.value);
                lnrp(node.right);
            }
            private void lrnp(Node node ){
                if (node == null){
                    return;
                }
                lrnp(node.left);
                lrnp(node.right);
                System.out.println(node.value);
            }
            public boolean search(T val){
                Node curr = root;
                while (curr != null){
                    if (curr.value.compareTo(val) == 0){
                        return true;
                    }
                    if (curr.value.compareTo(val) > 0){
                        curr = curr.left;
                    }
                    else if (curr.value.compareTo(val) < 0){
                        curr = curr.right;
                    }
                }
                return false;
            }
            public void addNode(T val){
                if (root != null) {
                    Node curr = root;
                    while (true) {
                        if (curr.value.compareTo(val) > 0) {
                            if (curr.left == null) {
                                curr.left = new Node(val);
                                break;
                            }
                            curr = curr.left;
                        } else if (curr.value.compareTo(val) < 0) {
                            if (curr.right == null) {
                                curr.right = new Node(val);
                                break;
                            }
                            curr = curr.right;
                        }
                    }
                } else {
                    System.out.println("Can not add element in empty tree");
                }
            }

            public void deleteNode(T val) {
                if (search(val)) {
                    Node curr = root;
                    Node parent = root;
                    Node tmp;
                    if (root.value == val && root.left == null && root.right == null) {
                            System.out.println("Error: can not delete empty root");
                            return;
                    }
                    boolean flag = true;
                    while (true) {
                        if (curr.value.compareTo(val) == 0) {
                            if (curr.left == null && curr.right == null) tmp = null;
                            else if (curr.left != null && curr.right == null) tmp = curr.left;
                            else if (curr.left == null) tmp = curr.right;
                            else { // curr.left and curr.right != null
                                Node currParent = curr;
                                Node currLeft = curr.left;
                                Node currRight = curr.right;
                                curr = curr.right;
                                while (curr.left != null) {
                                    currParent = curr;
                                    curr = curr.left;
                                }
                                tmp = curr;
                                currParent.left = curr.right;
                                tmp.left = currLeft;
                                tmp.right = currRight;
                            }
                            if (root.value == val){
                                tmp.left = root.left;
                                root = tmp;
                                return;
                            }
                            if (flag) {
                                parent.right = tmp;
                            } else {
                                parent.left = tmp;
                            }
                            return;
                        } else if (curr.value.compareTo(val) > 0) {
                             parent = curr;
                             curr = curr.left;
                             flag = false;
                        } else if (curr.value.compareTo(val) < 0) {
                            parent = curr;
                            curr = curr.right;
                            flag = true;
                        }
                    }
                } else {
                    System.out.println("Error: can not delete non-existent node");
                }
            }
        }

        class Student implements Comparable<Student>{
            final String name;
            final String surname;
            final int birthday;
            final Double averageMark;
            Student (String name, String surname, int data, double averMark){
                this.name = name;
                this.surname = surname;
                birthday = data;
                averageMark = averMark;
            }
            public Double getAverageMark(){
                return averageMark;
            }
            @Override
            public int compareTo(Student st){
                return averageMark.compareTo(st.getAverageMark());
            }

            @Override
            public String toString() {
                return "Student: " +
                        "name='" + name + '\'' +
                        ", surname='" + surname + '\'' +
                        ", birthday=" + birthday +
                        ", averageMark=" + averageMark;
            }
        }

        Student artBlag = new Student("Artyom", "Blagodarniy", 2005, 8.0);
        Student nikBez = new Student("Nikolai", "Bezmen", 2004, 7.5);
        Student svetLev = new Student("Svetlana", "Levanuk", 2006, 8.5);
        Student darBog = new Student("Daria", "Boguk", 2005, 9.5);
        Student andStep = new Student("Andrey", "Stepanenko", 2005, 5.5);
        Student vanLev = new Student("Ivan", "Levchuk", 2003, 10);
        Student dimLesh = new Student("Dima", "Leshick", 2004, 9);
        Student vladKiv = new Student("Vlad", "Kivist", 2004, 7);
        Student arinKlem = new Student("Arina", "Klema", 2005, 6);
        Student lerAlex = new Student("Lera", "Alexeichick", 2006, 5.2);
        Student misSir = new Student("Misha", "Sirotuck", 2005, 5.7);
        Student alKon = new Student("Alina", "Konovalova", 2005, 5);
        Student zhenPys = new Student("Zhenya", "Pysan", 2004, 4);
        Student romDen = new Student("Roma", "Denisevich", 2004, 3);
        Student ladSed = new Student("Lada", "Sedan", 2005, 4.5);
        Student anBlag = new Student("Andrey", "Blag", 2005, 8.3);
        Student anKozel = new Student("Anton", "Kozlovskiy", 2005, 7.2);
        Tree<Student> treeStudents = new Tree<>(arinKlem);
        Student[] students = {alKon, svetLev, zhenPys, darBog, romDen, vanLev, andStep, nikBez,  ladSed, lerAlex,
                misSir, vladKiv, artBlag, dimLesh, anKozel};
        for (Student st: students ){
             treeStudents.addNode(st);
        }
        //System.out.println("treeStudents.addNode(anBlag);");
        treeStudents.deleteNode(arinKlem);
        treeStudents.deleteNode(vanLev);
        treeStudents.deleteNode(darBog);
        treeStudents.nlr();
        /*Tree<Student> treeSts = new Tree<>(arinKlem);
        treeSts.deleteNode(arinKlem);*/

    }
}