public class Main {
    public static void main(String[] args) {
        //в этом списке методы получения, удаления и добавления
        MyLinkedList list = new MyLinkedList();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        list.remove(2);

        System.out.println(list.toString());
        System.out.println(list.get(3));
    }
}