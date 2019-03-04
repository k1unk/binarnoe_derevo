public class Tree {

    private class Node {
        private Node parent;
        private Node left;
        private Node right;
        private int number;

        Node(int number, Node parent, Node left, Node right) {
            this.number = number;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }

    private Node mainParent;

    //добавление первого элемента в дерево
    public Node addInTree(int number) {
        if (mainParent == null) {
            mainParent = new Node(number, null, null, null);
            return mainParent;
        } else {
            return addOthers(number, mainParent);
        }
    }

    //добавление остальных элементов
    public Node addOthers(int number, Node root) {
        if (root.number > number) {
            if (root.left == null) {
                root.left = new Node(number, root, null, null);
                return root.left;
            } else {
                addOthers(number, root.left);
            }
        } else {
            if (root.right == null) {
                root.right = new Node(number, root, null, null);
                return root.right;
            } else {
                addOthers(number, root.right);
            }
        }
        return null;
    }

    // поиск элемента в дереве
    public Node searchRoot(int number) {
        int numberOfRoot = mainParent.number;
        Node root = mainParent;

        while (number != numberOfRoot) {
            if (number > numberOfRoot) {
                root = root.right;
                if (root == null) throw new IllegalArgumentException();
                numberOfRoot = root.number;
            } else {
                root = root.left;
                if (root == null) throw new IllegalArgumentException();
                numberOfRoot = root.number;
            }
        }
        return root;
    }

    //поиск всех соседних корней данного элемента
    public Node searchParent(int number) {
        return searchRoot(number).parent;
    }

    public Node searchLeft(int number) {
        return searchRoot(number).left;
    }

    public Node searchRight(int number) {
        return searchRoot(number).right;
    }

    //удаление корня дерева
    public void delete(int number) {
        Node root = searchRoot(number);

        if (root.left == null && root.right == null) {                  //нет "детей"
            if (root == mainParent) {
                mainParent = null;
            } else {
                if (root.parent.right == root) {
                    root.parent.right = null;
                } else {
                    root.parent.left = null;
                }
            }
        } else {
            if (root.left != null && root.right == null) {              //только левый ребенок
                if (root == mainParent) {
                    mainParent = root.left;
                    root.left = null;
                } else {
                    if (root.parent.right == root) {
                        root.parent.right = root.left;
                        root.left.parent = root.parent;
                    } else {
                        root.parent.left = root.left;
                        root.left.parent = root.parent;
                    }
                }
            } else {
                if (root.left == null && root.right != null) {          //только правый ребенок
                    if (root == mainParent) {
                        mainParent = root.right;
                        root.right = null;
                    } else {
                        if (root.parent.right == root) {
                            root.parent.right = root.right;
                            root.right.parent = root.parent;
                        } else {
                            root.parent.left = root.right;
                            root.right.parent = root.parent;
                        }
                    }
                } else {                                                //оба ребенка
                    if (root.number - root.left.number > root.right.number - root.number) {
                        Node rootInSearchingNull = root.right;
                        while (rootInSearchingNull.left != null) {
                            rootInSearchingNull = rootInSearchingNull.left;
                        }
                        rootInSearchingNull.left = root.left;
                        root.left.parent = rootInSearchingNull;
                        mainParent = searchRoot(root.right.number);
                        mainParent.parent = null;
                    } else {
                        Node rootInSearchingNull = root.left;
                        while (rootInSearchingNull.right != null) {
                            rootInSearchingNull = rootInSearchingNull.right;
                        }
                        rootInSearchingNull.right = root.right;
                        root.right.parent = rootInSearchingNull;
                        mainParent = searchRoot(root.left.number);
                        mainParent.parent = null;
                    }
                }
            }
        }
    }

    public void deleteRecurse(int number) {
        deleteRecurse2(number, mainParent);
    }

    public Node deleteRecurse2(int number, Node root) {
        if (number < root.number) {
            root.left = deleteRecurse2(number, root.left);
        } else {
            if (number > root.number) {
                root.right = deleteRecurse2(number, root.right);
            } else {
                if (root.left != null && root.right != null) {
                    root.number = minimum(root.right).number;
                    root.right = deleteRecurse2(root.number, root.right);
                } else {
                    if (root.left != null) {
                        Node x = root.parent;
                        root = root.left;
                        root.parent = x;
                    } else {
                        Node x = root.parent;
                        root = root.right;
                        root.parent = x;
                    }
                }
            }
        }
        return root;
    }


    public Node minimum(Node root) {
        if (root.left == null) return root;
        else return minimum(root.left);
    }

    //выводит строку вида "Номер данного элемента, номер элемента родителя, номер левого ребенка, номер правого ребенка"
    public String toString(int number) {
        if (searchRoot(number) != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Number: ");
            sb.append(number);
            sb.append(", parent: ");
            if (searchParent(number) != null) {
                sb.append(searchParent(number).number);
            } else {
                sb.append("null");
            }
            sb.append(", left: ");
            if (searchLeft(number) != null) {
                sb.append(searchLeft(number).number);
            } else {
                sb.append("null");
            }
            sb.append(", right: ");
            if (searchRight(number) != null) {
                sb.append(searchRight(number).number);
            } else {
                sb.append("null");
            }
            return sb.toString();
        } else throw new IllegalArgumentException("Данного числа нет в дереве");
    }

}