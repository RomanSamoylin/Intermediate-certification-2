package Структуры данных дерево и хэш-таблица.источник;

import java.util.ArrayList;
import java.util.List;  

public class App {
    public static void main(String[] args) throws Exception {
        Tree tree = new Tree();

        tree.add(7);
        tree.add(3);
        tree.add(9);
        tree.add(2);
        tree.add(1);
        tree.add(5);
        tree.add(4);
        tree.add(6);
        tree.add(8);
        tree.add(11);

        List<Integer> dfsOrderItems = new ArrayList<>();
        tree.dfs(dfsOrderItems::add);
//        tree.dfs(integer -> dfsOrderItems.add(integer));
        System.out.println(dfsOrderItems);

        List<Integer> bfsOrderItems = new ArrayList<>();
        tree.bfs(bfsOrderItems::add);
        System.out.println(bfsOrderItems);

        System.out.println(tree.size());

        System.out.println(tree.contains(5)); // true
        System.out.println(tree.contains(2)); // true
        System.out.println(tree.contains(10)); // true
        System.out.println(tree.contains(-1)); // false
        System.out.println(tree.contains(4)); // false

        System.out.println(tree.findFirst()); // 2
        tree.remove(2);
        System.out.println(tree.findFirst()); // 5
        tree.remove(5);
        System.out.println(tree.findFirst()); // 10
        tree.remove(10);

        System.out.println(tree.findFirst()); // exception

        // dfs depth-first-search  // поиск в глубину
        // bfs breath-first-search // поиск в ширину
        System.out.println(tree); // []
        System.out.println(tree.findLast());
        System.out.println(tree.getChildrenCount());
        System.out.println(tree.isBalanced());
    }


    // HASH TABLE
    // [null, null, null, null, null, null, null, null] size = 8
    // element, hash(element) = 18 -- пример
    // 18 % 8 -> 2 - остаток от деления на размер
    // [null, null, [element], null, null, null, null, null] size = 8
    
}
