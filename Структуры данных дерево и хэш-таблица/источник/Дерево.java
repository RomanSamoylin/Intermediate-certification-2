

import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.function.Consumer;

public class Дерево {
    private class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public Node root;
    public int childrenCount = 0;

    public void add(int value) {
        root = appendNode(root, value);
    }

    private Node appendNode(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (current.value > value) {
            current.left = appendNode(current.left, value);
        } else if (current.value < value) {
            current.right = appendNode(current.right, value);
        }
        return current;
    }

    public boolean contains(int value) {
        return findNode(root, value) != null;
    }

    private Node findNode(Node current, int value) {
        if (current == null) {
            return null;
        }

        if (current.value > value) {
            return findNode(current.left, value);
        } else if (current.value < value) {
            return findNode(current.right, value);
        }
        return current;
    }

    public void remove(int value) {
        root = removeNode(root, value);
    }

    private Node removeNode(Node current, int value) {
        if (current == null) {
            return null;
        }

        if (current.value > value) {
            current.left = removeNode(current.left, value);
            return current;
        } else if (current.value < value) {
            current.right = removeNode(current.right, value);
            return current;
        }

        // Нужно удалить текущий узел.
        // 3 случая:
        // 1. Нет дочерних узлов
        if (current.left == null && current.right == null) {
            return null;
        }

        // 2. Есть только 1 дочерний узел
        if (current.left == null) { //  && current.right != null
            return current.right;
        }
        if (current.right == null) { // && current.left != null
            return current.left;
        }

        // 3. Есть оба дочерних узла
        // Нужно найти минимальный элемент в правом поддереве
        Node smallestNodeOnTheRight = findFirst(current.right);
        int smallestValueOnTheRight = smallestNodeOnTheRight.value;
        // Вставить его значение в текущий узел
        current.value = smallestValueOnTheRight;
        // И удалить этот найденный минимальный узел у правого поддерева
        current.right = removeNode(current.right, smallestValueOnTheRight);
        return current;
    }

    public int findFirst() {
        if (root == null) {
            throw new NoSuchElementException();
        }

        return findFirst(root).value;
    }

    private Node findFirst(Node current) {
        if (current.left != null) {
            return findFirst(current.left);
        }
        return current;
    }

    public void dfs(Consumer<Integer> valueConsumer) {
        dfsInternal(root, valueConsumer);
    }

    private void dfsInternal(Node current, Consumer<Integer> valueConsumer) {
        if (current != null) {
            dfsInternal(current.left, valueConsumer);
            valueConsumer.accept(current.value);
            dfsInternal(current.right, valueConsumer);
        }
    }

    public void bfs(Consumer<Integer> valueConsumer) {
        bfsInternal(valueConsumer);
    }

    private void bfsInternal(Consumer<Integer> valueConsumer) {
        if (root == null) {
            return;
        }

        // FIFO
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            valueConsumer.accept(node.value);

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public int size() {
        final int[] counter = new int[1];
        // или dfs
        bfs(n -> counter[0]++);
        return counter[0];
    }

    /**
     * Поиск максимального элемента в дереве
     * @return
     */
    public int findLast() {
        if (root == null) {
            throw new NoSuchElementException();
        }
        return findLast(root).value;
    }

    private Node findLast(Node current) {
        if (current.right != null) {
            return findLast(current.right);
        }
        return current;
    }

    /**
     * Подсчет количества листьев дерева
     * @return
     */
    public int getChildrenCount(){
        return getChildrenCount(root);
    }

    private int getChildrenCount(Node current){
        if (root == null) {
            throw new NoSuchElementException();
        }
        if (current.left != null) {
            //System.out.println("now - Node " + current.value + " / left Node " + current.left.value);
            getChildrenCount(current.left);
        }
        else if (current.right == null) {
            childrenCount ++;
            //System.out.println(current.value +" - " +childrenCount);
            }
        if (current.right != null) {
            //System.out.println("now - Node " + current.value + " / right Node " + current.right.value);
            getChildrenCount(current.right);
        }
        return childrenCount;
    }
    
    /**
     * Проверка дерева на сбалансированность
     * @return
     */
    public boolean isBalanced(){
        if (root == null)
            throw new NoSuchElementException();
        return isBalanced(root.left) && isBalanced(root.right) && Math.abs(height(root.left) - height(root.right)) <= 1;
    }

    private boolean isBalanced(Node current) {
        if (current == null)
            throw new NoSuchElementException();
        return Math.abs(height(root.left) - height(root.right)) <= 1;        
    }

    private int height(Node current) {
        if (current == null)
            throw new NoSuchElementException();
        int lHeight = 0;
        int rHeight = 0;
        if (current.left == null && current.right == null) return 1;
        if (current.left != null) {
            lHeight = height(current.left);  
        } 
        if (current.right != null) {
            rHeight = height(current.right); 
        } 
        return Math.max(lHeight, rHeight)+1;
        }




}
