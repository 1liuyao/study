package tree.algorithm;
/*
    【450 删除二叉搜索树中的节点】给定一个【二叉搜索树】的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，
                             并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
                             一般来说，删除节点可分为两个步骤：首先找到需要删除的节点；如果找到了，删除它。
    【示例 1】
            输入：root = [5,3,6,2,4,null,7], key = 3
            输出：[5,4,6,2,null,null,7]
            解释：给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
            一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
            另一个正确答案是 [5,2,6,null,4,null,7]。
    【示例 2】
            输入: root = [5,3,6,2,4,null,7], key = 0
            输出: [5,3,6,2,4,null,7]
            解释: 二叉树不包含值为 0 的节点
    【示例 3】
            输入: root = [], key = 0
            输出: []

    ===============================================================================================
    【解题思路】1、首先需要查找被删除的结点：利用二叉搜索树的特性，去搜索目标结点
              2、删除结点后如何调整二叉树使得仍满足二叉搜索树的特性
                （1）未找到要删除的结点：无需对二叉搜索树的结构进行调整，直接返回根结点
                （2）如果找到要删除的结点，且目标结点为叶子结点，则直接将结点至为空，返回给上层结点
                （3）如果找到要删除的结点，没有左孩子，则直接将目标结点的右孩子做为新的根节点
                （4）如果找到要删除的结点，没有右孩子，则直接将目标结点的左孩子左为新的根节点
                （5）如果找到要删除的结点，并且左右孩子皆不为空，则可以选择左孩子或者右孩子作为新的根节点

   【情况1】：未找到要删除的结点：无需对二叉搜索树的结构进行调整，直接返回根结点
                5
              /   \    举例：删除 8， （1）8 比 5 大，向右搜索        （4）搜索到 null ，表示未找到，则向 7 返回null
             3     6                （2）8 比 6 大，继续向右搜索     （5）继续向上返回，向 6 返回 7
           /   \    \               （3）8 比 7 大，继续向右搜索     （6）继续向上返回，向 5 返回 6， 最终返回二叉搜索树 root 5
          2     4    7
   【情况2】：找到要删除的结点，且目标结点为叶子结点，则直接将结点至为空，返回给上层结点
                5
              /   \    举例：删除 2， （1）2 比 5 小，向左搜索        （4）将叶子结点置 null，返回给 3 （相当于 3.left = null）
             3     6                （2）2 比 3 小，继续向左搜索     （5）继续向上返回，向 5 返回 3
           /   \    \               （3）2 等于 叶子结点 2，找到要被删除的元素
          2     4    7
   【情况3】：如果找到要删除的结点，没有左孩子，则直接将目标结点的右孩子做为新的根节点
                5
              /   \    举例：删除 6， （1）6 比 5 大，向右搜索
             3     6                （2）6 等于 分支结点 6，找到待删元素
           /   \    \               （3）把 6 的右孩子 7 作为新的根节点，返回给 5
          2     4    7
   【情况4】：如果找到要删除的结点，没有右孩子，则直接将目标结点的左孩子做为新的根节点（和情况3类似）
   【情况5】：找到要删除的结点，并且左右孩子皆不为空，则可以选择右孩子作为新的根节点
                7                                                                      7                            7
              /   \    举例：删除 2， （1）2 比 7 小，向左搜索                             /   \                        /   \
             2      8               （2）2 等于 分支结点 2，找到待删元素                  3     8                      1     8
           /   \     \              （3）把 2 的右孩子 3 作为新的根节点，返回给 7         / \     \    或者取左孩子作为根          \
          1     3     9             （4）更新 3 的左孩子，将 1放在最左侧                4   5     9                            3
               / \                                                               /                                      /  \
              4   5                                                             1                                      4    5

  */
public class DeleteNode {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode cur = null;
        // 找到要删除的结点，本质上递归就结束了，所以本题终止条件的判断是复杂的
        // 【情况1】：未找到要删除的结点：无需对二叉搜索树的结构进行调整，直接返回根结点
        if (root == null)
            return null;
        if (root.val == key){
            // 【情况2】：找到要删除的结点，且目标结点为叶子结点，则直接将结点至为空，返回给上层结点
            if (root.left == null && root.right == null)
                return null;
            // 【情况3】：如果找到要删除的结点，没有左孩子，则直接将目标结点的右孩子做为新的根节点
            if (root.left == null && root.right != null)
                return root.right;
            // 【情况4】：如果找到要删除的结点，没有右孩子，则直接将目标结点的左孩子做为新的根节点（和情况3类似）
            if (root.left != null && root.right == null)
                return root.left;
            // 【情况5】：找到要删除的结点，并且左右孩子皆不为空，则可以选择右孩子作为新的根节点
            if (root.left != null && root.right != null)
                // 将右孩子作为新的根节点，并将左孩子接在右孩子的最左侧
                cur = root.right;
                while (cur.left != null)
                    cur = cur.left;
                cur.left = root.left;
                return root.right;
        }
        // 左搜索找删除结点
        if (root.val > key){
            TreeNode leftNode = deleteNode(root.left, key);
            root.left = leftNode;
        }
        if (root.val < key){
            TreeNode rightNode = deleteNode(root.right, key);
            root.right = rightNode;
        }
        return root;
    }
}
