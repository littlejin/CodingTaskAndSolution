package tree

// 定义二叉树的节点
class Node[T](val data: T,
              var left: Option[Node[T]] = None,
              var right: Option[Node[T]] = None) {
  override def toString = {
    s"data:$data, left:$left, right:$right"
  }
}

object Node {
  // 先处理根节点, 再处理左节点，最后处理右节点
  def preTraverse[T, U](node: Option[Node[T]])(handleNodeFunc: Node[T] => U): Unit = {
    node.foreach { _node =>
      handleNodeFunc(_node)
      preTraverse(_node.left)(handleNodeFunc)
      preTraverse(_node.right)(handleNodeFunc)
    }
  }

  // 先处理左节点, 再处理根节点，最后处理右节点
  def inTraverse[T, U](node: Option[Node[T]])(handleNodeFunc: Node[T] => U): Unit = {
    node.foreach { _node =>
      preTraverse(_node.left)(handleNodeFunc)
      handleNodeFunc(_node)
      preTraverse(_node.right)(handleNodeFunc)
    }
  }

  // 先处理左节点, 再处理右节点，最后处理根节点
  def posTraverse[T, U](node: Option[Node[T]])(handleNodeFunc: Node[T] => U): Unit = {
    node.foreach { _node =>
      preTraverse(_node.left)(handleNodeFunc)
      preTraverse(_node.right)(handleNodeFunc)
      handleNodeFunc(_node)
    }
  }

}
