package tree

// 给定一个数组，动态创建二叉查找树
class BinarySearchTree(val root: Node[Int]) {
  override def toString: String = {
    root.toString
  }
}

object BinarySearchTree {
  private def addNumToTree(tree: BinarySearchTree, num: Int): Unit = {
    var node: Option[Node[Int]] = Some(tree.root)

    while (node.nonEmpty) {
      val _node = node.get

      if (num > _node.data) { // 此时放在节点右边
        // 节点不存在直接插入，如果存在则跟节点的值对比
        if (_node.right.isEmpty) {
          _node.right = Some(new Node(num))
          return
        } else {
          node = _node.right
        }
      } else if (num < _node.data) { // 此时放在节点左边
        // 节点不存在直接插入，如果存在则跟节点的值对比
        if (_node.left.isEmpty) {
          _node.left = Some(new Node(num))
          return
        } else {
          node = _node.left
        }
      }
    }
  }

  def get(nums: Array[Int]): BinarySearchTree = {
    val root = new Node(nums.head)
    val tree = new BinarySearchTree(root)
    1.until(nums.length).foreach { idx =>
      addNumToTree(tree, nums(idx))
    }
    tree
  }
}
