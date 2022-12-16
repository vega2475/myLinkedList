import java.util.Arrays;

/**одновязный список и не параметризованный (тока целые числа)
 * @author Nikolay Menshikov FCS VSU 1-st Course Google L5 Lead
 * */
public class MyLinkedList {

    private Node head;//изначально при вызове объекта MLL тут будет лежать NULL
    public int size;//default = 0

    public void add (int value){
        //если это первое добавление в список
        if (head == null){
            //то здесь будет какая-то логика
            this.head = new Node(value);//передаем значение переменной в хеад нашего списка, передаем в новый нод(головной узел) значение
        }else{
            Node temp = head;//создание временного нода

            while (temp.getNext() != null){
                temp = temp.getNext();
            }

            temp.setNext(new Node(value));//передаем временному ноду значение нового добавленного нода
        }

        size++;
    }

    public int get(int index){
        int currentIndex = 0;
        Node temp = head;
        while(temp != null){//проходимся по списку
            if(currentIndex == index){//если настоящий индекс = искомому тогда ретерним его(логично)
                return temp.getValue();
            }else {//если не нашли искомый то тогда временному ноду назначаем ссылку на некст нод
                temp = temp.getNext();
                currentIndex++;//и увеличиваем индекс
            }
        }
        throw new IllegalArgumentException();//если мы не ретурнули искомый элемент то тогда возвращаем (выкидываем) исключение что такого элемента не существует
    }

    /**
     * Удаление списка происходит с конца (точнее отсчет идет с ласт элемента)
     * потому что если мы пойдем с нулевого элемента и дойдем до нужного тогда мы удалим ссылку на
     * на нужный нам элемент, так как ссылка на него есть тока у предыдущего нода
     * находясь в нужном элементе мы имеем ссылку тока на некст элемент
     * что бы удалить нужный элемент нам надо дойти до предыдущего
     * и ссылку указать ссылку не на текущий элемент(который мы хотим удалить)
     * а на некст элемент
     * [1] -> [2] -> [3] -> null
     * идем с ласт элемента дохоим до нужного (пусть до 2)
     * удаляем ссылку на эту двойку и саму двойку
     * если бы мы шли с первого так бы не получилось потому что ссылка указывающая на двойку тока у единицы
     * и находясь в двойке мы имеем ссылку тока на тройку
     * поэтому надо дойти до единицы и удалить ссылку на двойку
     * сори за мое объяснение нужно было попросить сделать это @DavidMSKS он бы лучше справился я думаю
     *
     * @author Nikolay Menshikov FCS VSU 1-st Course Google L5 Lead
     */

    public void remove(int index){//тот метод СС который имеет преимущество над МС, удаление пойдет с конца
        if (index == 0){//частный случай для 1-го элемента (просто меняем хед тем самым удаляем первоначальный хед)
            head = head.getNext();
            size--;
            return;
        }

        int currentIndex = 0;
        Node temp = head;

        while (temp != null){
            if(currentIndex  == index - 1){//в отличии от методе гет мы попадаем в этот иф когда мы находимся в предшествующем элементе, а не в самом этом элементе
                //процесс удалния...
                /**
                 * тут прекол в том что (как я понял) (например мы хотим удалить элемент 1-й индекс значение 2
                 * в нашем случае.
                 * если кратко мы ноду под индексом 1 с значением два присваиваем нод под индексом 2 со значением 3
                 * [1] -> [2] -> [3] -> null
                 * @author Nikolay Menshikov FCS VSU 1-st Course Google L5 Lead
                 */
                temp.setNext(temp.getNext().getNext());//такая тема на лк была как раз DavidMSK ее не мог понять ахахах
                size--;//после каждого удаления уменьшаем список на 1 (декрементируем)
                return;//после удаления элемента минусуем размер на 1 и выходим из метода
            }else {
                temp = temp.getNext();
                currentIndex++;
            }
        }
    }

    @Override
    public String toString(){//переопределенный позорный тустринг
        int[] result = new int[size];
        int index = 0;
        Node temp = head;//временный нод который будет указывать на начальный элемент списка

        while (temp != null){
            result[index++] = temp.getValue();//добавляем в массив новое значение текущего узла(который в свою очередь равен некст ноду)
            temp = temp.getNext();//записали в темп следующий нод
        }
        return Arrays.toString(result);
    }

    private static class Node{
        private int value; //значение которое хранится в текущем узле
        private Node next; //ссылка на следующий элемент

        public Node(int value) {//конструктор принимающий на вход текущее значение
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
