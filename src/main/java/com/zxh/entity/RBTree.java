package com.zxh.entity;

/**
 * Created by zhengxh on 2017/7/5.
 * 红黑树节点
 */
public class RBTree<T extends Comparable<T>> {
    private RBNode<T> mNode;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    /**
     * 节点信息
     *
     * @param <T>
     */
    public class RBNode<T extends Comparable<T>> {
        private boolean color;
        private RBNode<T> right;
        private RBNode<T> left;
        private RBNode<T> parent;
        T key;

        public RBNode(boolean color, RBNode<T> right, RBNode<T> left, RBNode<T> parent, T key) {
            this.color = color;
            this.right = right;
            this.left = left;
            this.parent = parent;
            this.key = key;
        }
    }

    /**
     * 左旋
     *
     * @param rbNode
     */
    public void leftTurn(RBNode<T> rbNode) {
        RBNode<T> rightNode = rbNode.right;
        if (rbNode.left != null) {
            rbNode.left.parent = rbNode;
        }

        rbNode.right = rbNode.right.left;
        //如果此节点是根节点,则把此节点的右节点变成根节点
        if (rbNode.parent != null) {
            rightNode.parent = rbNode.parent;
            //如果是父节点左节点,就把父节点的左节点变成其右节点
            if (rbNode.parent.left == rbNode) {
                rbNode.parent.left = rightNode;
            }
            //如果是父节点右节点,就把父节点的右节点变成其右节点
            else {
                rbNode.parent.right = rightNode;
            }
        } else {
            this.mNode = rightNode;
        }
        //变换后此节点的父节点变成此节点的右节点,此节点的右节点的左节点变成此节点
        rightNode.left = rbNode;
        rbNode.parent = rightNode;
    }

    /**
     * 右旋
     *
     * @param rbNode
     */
    public void rightTurn(RBNode<T> rbNode) {
        RBNode<T> leftNode = rbNode.left;
        leftNode.parent = rbNode.parent;
        rbNode.left = leftNode.right;

        if (leftNode.right != null) {
            rbNode.right = leftNode.right;
        }
        //如果此节点是根节点,就把此节点的变成根节点
        if (rbNode.parent != null) {
            if (rbNode.parent.left == rbNode) {
                rbNode.parent.left = leftNode;
            } else {
                rbNode.parent.right = leftNode;
            }
        } else {
            this.mNode = leftNode;
        }

        leftNode.right = rbNode;
        rbNode.parent = leftNode;
    }

    /**
     * 插入新节点
     *
     * @param key
     */
    public void insert(T key) {
        RBNode<T> node = new RBNode<T>(RED, null, null, null, key);
        // 如果新建结点失败，则返回。
        if (node != null)
            insert(node);
    }

    /**
     * 新节点插入红黑树
     *
     * @param node
     */
    public void insert(RBNode node) {
        int cmp;
        RBNode<T> y = null;
        RBNode<T> x = this.mNode;

        // 1. 将红黑树当作一颗二叉查找树，将节点添加到二叉查找树中。
        while (x != null) {
            y = x;
            cmp = node.key.compareTo(x.key);
            if (cmp < 0)
                x = x.left;
            else
                x = x.right;
        }

        node.parent = y;
        if (y != null) {
            cmp = node.key.compareTo(y.key);
            if (cmp < 0)
                y.left = node;
            else
                y.right = node;
        } else {
            this.mNode = node;
        }

        // 2. 将它重新修正树
        insertFixUp(node);
    }

    /**
     * 插入红黑树后再排序
     *
     * @param node
     */
    private void insertFixUp(RBNode<T> node) {
        RBNode<T> parent, gparent;

        // 若“父节点存在，并且父节点的颜色是红色”
        while ((parent = node.parent) != null && node.parent.color == RED) {

            if (node.parent.parent != null) {
                gparent = node.parent.parent;
            } else {
                return;
            }

            //若“父节点”是“祖父节点的左孩子”
            if (parent == gparent.left) {
                // Case 1条件：叔叔节点是红色
                RBNode<T> uncle = gparent.right;

                //变色完之后,在把爷爷节点再递归一遍
                if ((uncle != null) && uncle.color) {
                    uncle.color = BLACK;
                    parent.color = BLACK;
                    gparent.color = RED;
                    node = gparent;
                    continue;
                }

                // Case 2条件：叔叔是黑色，且当前节点是右孩子
                if (parent.right == node) {
                    RBNode<T> tmp;
                    leftTurn(parent);
                    tmp = parent;
                    parent = node;
                    node = tmp;
                }

                // Case 3条件：叔叔是黑色，且当前节点是左孩子。
                parent.color = BLACK;
                gparent.color = RED;
                rightTurn(gparent);
            } else {    //若“z的父节点”是“z的祖父节点的右孩子”
                // Case 1条件：叔叔节点是红色
                RBNode<T> uncle = gparent.left;

                if ((uncle != null) && uncle.color == RED) {
                    uncle.color = BLACK;
                    parent.color = BLACK;
                    gparent.color = RED;
                    node = gparent;
                    continue;
                }

                // Case 2条件：叔叔是黑色，且当前节点是左孩子
                if (parent.left == node) {
                    RBNode<T> tmp;
                    rightTurn(parent);
                    tmp = parent;
                    parent = node;
                    node = tmp;
                }

                // Case 3条件：叔叔是黑色，且当前节点是右孩子。
                parent.color = BLACK;
                gparent.color = RED;
                leftTurn(gparent);
            }
        }

        // 将根节点设为黑色
        this.mNode.color = BLACK;
    }

    /**
     * 打印红黑树
     */
    public void print() {
        if (mNode != null)
            print(mNode, mNode.key, 0);
    }

    /*
      * 打印"红黑树"
      *
      * key        -- 节点的键值
      * direction  --  0，表示该节点是根节点;
      *               -1，表示该节点是它的父结点的左孩子;
      *                1，表示该节点是它的父结点的右孩子。
      */
    private void print(RBNode<T> tree, T key, int direction) {
        if (tree != null) {
            if (direction == 0)    // tree是根节点
                System.out.printf("%2d(B) is root\n", tree.key);
            else                // tree是分支节点
                System.out.printf("%2d(%s) is %2d's %6s child\n", tree.key, tree.color == RED ? "R" : "B", key, direction == 1 ? "right" : "left");

            print(tree.left, tree.key, -1);
            print(tree.right, tree.key, 1);
        }
    }

    /*
 * 删除结点(node)，并返回被删除的结点
 *
 * 参数说明：
 *     node 删除的结点
 */
    private void remove(RBNode<T> node) {
        RBNode<T> child, parent;
        boolean color;

        // 被删除节点的"左右孩子都不为空"的情况。
        if ((node.left != null) && (node.right != null)) {
            // 被删节点的后继节点。(称为"取代节点")
            // 用它来取代"被删节点"的位置，然后再将"被删节点"去掉。
            RBNode<T> replace = node;

            // 获取后继节点
            replace = replace.right;
            while (replace.left != null)
                replace = replace.left;

            // "node节点"不是根节点(只有根节点不存在父节点)
            if (node.parent != null) {
                if (node.parent.left == node)
                    node.parent.left = replace;
                else
                    node.parent.right = replace;
            } else {
                // "node节点"是根节点，更新根节点。
                this.mNode = replace;
            }

            // child是"取代节点"的右孩子，也是需要"调整的节点"。
            // "取代节点"肯定不存在左孩子！因为它是一个后继节点。
            child = replace.right;
            parent = replace.parent;
            // 保存"取代节点"的颜色
            color = replace.color;

            // "被删除节点"是"它的后继节点的父节点"
            if (parent == node) {
                parent = replace;
            } else {
                // child不为空
                if (child != null)
                    child.parent = parent;
                parent.left = child;

                replace.right = node.right;
                node.right.parent = replace;
            }

            replace.parent = node.parent;
            replace.color = node.color;
            replace.left = node.left;
            node.left.parent = replace;

            if (color == BLACK)
                removeFixUp(child, parent);

            node = null;
            return;
        }

        if (node.left != null) {
            child = node.left;
        } else {
            child = node.right;
        }

        parent = node.parent;
        // 保存"取代节点"的颜色
        color = node.color;

        if (child != null)
            child.parent = parent;

        // "node节点"不是根节点
        if (parent != null) {
            if (parent.left == node)
                parent.left = child;
            else
                parent.right = child;
        } else {
            this.mNode = child;
        }

        if (color == BLACK)
            removeFixUp(child, parent);
        node = null;
    }

    /*
     * 删除结点(z)，并返回被删除的结点
     *
     * 参数说明：
     *     tree 红黑树的根结点
     *     z 删除的结点
     */
    public void remove(T key) {
        RBNode<T> node;

        if ((node = search(mNode, key)) != null)
            remove(node);
    }

    /*
 * 红黑树删除修正函数
 *
 * 在从红黑树中删除插入节点之后(红黑树失去平衡)，再调用该函数；
 * 目的是将它重新塑造成一颗红黑树。
 *
 * 参数说明：
 *     node 待修正的节点
 */
    private void removeFixUp(RBNode<T> node, RBNode<T> parent) {
        RBNode<T> other;

        while ((node == null || node.color == BLACK) && (node != this.mNode)) {
            if (parent.left == node) {
                other = parent.right;
                if (other.color == RED) {
                    // Case 1: x的兄弟w是红色的
                    other.color = BLACK;
                    parent.color = RED;
                    leftTurn(parent);
                    other = parent.right;
                }

                if ((other.left == null || other.left.color == BLACK) &&
                        (other.right == null || other.right.color == BLACK)) {
                    // Case 2: x的兄弟w是黑色，且w的俩个孩子也都是黑色的
                    other.color = RED;
                    node = parent;
                    parent = node.parent;
                } else {

                    if (other.right == null || (other.right.color = BLACK)) {
                        // Case 3: x的兄弟w是黑色的，并且w的左孩子是红色，右孩子为黑色。
                        other.left.color = BLACK;
                        other.color = RED;
                        rightTurn(other);
                        other = parent.right;
                    }
                    // Case 4: x的兄弟w是黑色的；并且w的右孩子是红色的，左孩子任意颜色。
                    other.color = parent.color;
                    parent.color = BLACK;
                    other.right.color = BLACK;
                    leftTurn(parent);
                    node = this.mNode;
                    break;
                }
            } else {
                other = parent.left;
                if (other.color = RED) {
                    // Case 1: x的兄弟w是红色的
                    other.color = BLACK;
                    parent.color = RED;
                    rightTurn(parent);
                    other = parent.left;
                }

                if ((other.left == null || other.left.color==BLACK) &&
                        (other.right == null || other.right.color==BLACK)) {
                    // Case 2: x的兄弟w是黑色，且w的俩个孩子也都是黑色的
                    other.color=RED;
                    node = parent;
                    parent = node.parent;
                } else {

                    if (other.left == null || other.left.color==BLACK) {
                        // Case 3: x的兄弟w是黑色的，并且w的左孩子是红色，右孩子为黑色。
                        other.right.color=BLACK;
                        other.color=RED;
                        leftTurn(other);
                        other = parent.left;
                    }

                    // Case 4: x的兄弟w是黑色的；并且w的右孩子是红色的，左孩子任意颜色。
                    other.color=parent.color;
                    parent.color=BLACK;
                    other.left.color=BLACK;
                    rightTurn(parent);
                    node = this.mNode;
                    break;
                }
            }
        }

        if (node != null)
            node.color=BLACK;
    }

    /*
         * (递归实现)查找"红黑树x"中键值为key的节点
         */
    private RBNode<T> search(RBNode<T> x, T key) {
        if (x == null)
            return x;

        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return search(x.left, key);
        else if (cmp > 0)
            return search(x.right, key);
        else
            return x;
    }
}
