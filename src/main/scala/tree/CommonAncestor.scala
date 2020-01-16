package tree

// 在一个二叉树中，给定任意两个子节点，寻找最近公共父节点
object CommonAncestor {
  def find[T](root: Option[Node[T]], nodeA: Node[T], nodeB: Node[T]): Option[Node[T]] = {
    if (root.isEmpty) return None
    if (root.get == nodeA || root.get == nodeB) return root

    // 基于当前的节点, 判断nodeA或者nodeB是否存在该节点的左边子节点中
    val left = find(root.get.left, nodeA, nodeB)

    // 基于当前的节点, 判断nodeA或者nodeB是否存在该节点的右边子节点中
    val right = find(root.get.right, nodeA, nodeB)

    if (left.isDefined && right.isDefined) {
      // 两个子节点，一个在当前节点左边，一个在当前节点右边，此时当前节点就是最近公共父节点
      root
    } else if (left.isDefined) {
      // 两个子节点，都在当前节点左边，此时在左边找到的第一个命中的节点就是最近公共父节点
      left
    } else {
      // 两个子节点，都在当前节点右边，此时在右边找到的第一个命中的节点就是最近公共父节点
      right
    }
  }

}
