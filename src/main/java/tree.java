public class tree {

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

    private Node MainParent;

    //добавление первого элемента в дерево
    public Node addInTree(int number) {
        if (MainParent == null) {
            MainParent = new Node(number, null, null, null);
            return MainParent;
        } else {
            return addOthers(number, MainParent);
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
    public Node SearchRoot(int number) {
        int numberOfRoot = MainParent.number;
        Node root = MainParent;

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
    public Node SearchParent(int number) {
        return SearchRoot(number).parent;
    }

    public Node SearchLeft(int number) {
        return SearchRoot(number).left;
    }

    public Node SearchRight(int number) {
        return SearchRoot(number).right;
    }

    //удаление корня дерева
    public boolean Delete(int number) {
        Node root = SearchRoot(number);

        if (root.left == null && root.right == null) {                  //нет "детей"
            if (root == MainParent) {
                MainParent = null;
                return true;
            } else {
                if (root.parent.right == root) {
                    root.parent.right = null;
                    return true;
                } else {
                    root.parent.left = null;
                    return true;
                }
            }
        } else {
            if (root.left != null && root.right == null) {              //только левый ребенок
                if (root == MainParent) {
                    MainParent = root.left;
                    root.left = null;
                    return true;
                } else {
                    if (root.parent.right == root) {
                        root.parent.right = root.left;
                        root.left.parent = root.parent;
                        return true;
                    } else {
                        root.parent.left = root.left;
                        root.left.parent = root.parent;
                        return true;
                    }
                }
            } else {
                if (root.left == null && root.right != null) {          //только правый ребенок
                    if (root == MainParent) {
                        MainParent = root.right;
                        root.right = null;
                        return true;
                    } else {
                        if (root.parent.right == root) {
                            root.parent.right = root.right;
                            root.right.parent = root.parent;
                            return true;
                        } else {
                            root.parent.left = root.right;
                            root.right.parent = root.parent;
                            return true;
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
                        MainParent = SearchRoot(root.right.number);
                        MainParent.parent = null;
                        return true;
                    } else {
                        Node rootInSearchingNull = root.left;
                        while (rootInSearchingNull.right != null) {
                            rootInSearchingNull = rootInSearchingNull.right;
                        }
                        rootInSearchingNull.right = root.right;
                        root.right.parent = rootInSearchingNull;
                        MainParent = SearchRoot(root.left.number);
                        MainParent.parent = null;
                        return true;
                    }
                }
            }
        }
    }

    //выводит строку вида "Номер данного элемента, номер элемента родителя, номер левого ребенка, номер правого ребенка"
    public String toString(int number) {
        if (SearchRoot(number) != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Number: ");
            sb.append(number);
            sb.append(", parent: ");
            if (SearchParent(number) != null) {
                sb.append(SearchParent(number).number);
            } else {
                sb.append("null");
            }
            sb.append(", left: ");
            if (SearchLeft(number) != null) {
                sb.append(SearchLeft(number).number);
            } else {
                sb.append("null");
            }
            sb.append(", right: ");
            if (SearchRight(number) != null) {
                sb.append(SearchRight(number).number);
            } else {
                sb.append("null");
            }
            return sb.toString();
        } else throw new IllegalArgumentException("Данного числа нет в дереве");
    }

}