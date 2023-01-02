package day20220923;

/**
 * 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
 * 在链表类中实现这些功能：
 * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val 的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 * 示例：
 * MyLinkedList linkedList = new MyLinkedList();
 * linkedList.addAtHead(1);
 * linkedList.addAtTail(3);
 * linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
 * linkedList.get(1);            //返回2
 * linkedList.deleteAtIndex(1);  //现在链表是1-> 3
 * linkedList.get(1);            //返回3
 *
 * @author hlam
 * @date 2022/9/23
 */
public class MyLinkedList {

    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(1,2);
        linkedList.get(1);
        linkedList.deleteAtIndex(1);
        linkedList.get(1);
    }

    class Node {
        Node prev, next;
        int var;

        Node(int var) {
            this.var = var;
        }
    }

    Node head = new Node(-1), tail = new Node(-1);
    int size = 0;

    public MyLinkedList() {
        head.next = tail;
        tail.prev = head;
    }

    public int get(int index) {
        Node node = getNode(index);
        return node == null ? -1 : node.var;
    }

    public void addAtHead(int val) {
        Node node = new Node(val);
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
        size++;
    }

    public void addAtTail(int val) {
        Node node = new Node(val);
        node.prev = tail.prev;
        node.next = tail;
        tail.prev.next = node;
        tail.prev = node;
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        if (index <= 0) {
            addAtHead(val);
        } else if (index == size) {
            addAtTail(val);
        } else {
            Node node = new Node(val), cur = getNode(index);
            node.next = cur;
            node.prev = cur.prev;
            cur.prev.next = node;
            cur.prev = node;
            size ++;
        }
    }

    public void deleteAtIndex(int index) {
        Node cur = getNode(index);
        if (cur == null) {
            return ;
        }
        cur.next.prev = cur.prev;
        cur.prev.next = cur.next;
        size --;
    }

    Node getNode(int index) {
        boolean isLeft = index < size / 2;
        if (!isLeft) {
            index = size - index - 1;
        }
        for (Node cur = isLeft ? head.next : tail.prev; cur != tail && cur != head; cur = isLeft ? cur.next : cur.prev) {
            if (index -- == 0) {
                return cur;
            }
        }
        return null;
    }

}
