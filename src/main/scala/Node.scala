// 定义二叉树的节点
class Node[T](val data: T,
              var left: Option[Node[T]] = None,
              var right: Option[Node[T]] = None) {
  override def toString = {
    s"data:$data, left:$left, right:$right"
  }
}

object Node {
//  // 先处理根节点, 再处理左节点，最后处理右节点
//  def preTraverse[T, U](node: Option[Node[T]])(handleNodeFunc: Node[T] => U): Unit = {
//    if (node.isDefined) {
//      val _node = node.get
//      preTraverse(node)(handleNodeFunc)
//      _node.left.foreach(node => )
//      _node.right.foreach(node => preTraverse(node)(handleNodeFunc))
//    }
//  }
//
//  // 先处理左节点, 再处理根节点，最后处理右节点
//  def inTraverse[T, U](node: Node[T])(handleNodeFunc: Node[T] => U): Unit = {
//    node.left.foreach(node => inTraverse(node)(handleNodeFunc))
//    handleNodeFunc(node)
//    node.right.foreach(node => inTraverse(node)(handleNodeFunc))
//  }
//
//  // 先处理左节点, 再处理右节点，最后处理根节点
//  def posTraverse[T, U](node: Node[T])(handleNodeFunc: Node[T] => U): Unit = {
//    node.left.foreach(node => posTraverse(node)(handleNodeFunc))
//    node.right.foreach(node => posTraverse(node)(handleNodeFunc))
//    handleNodeFunc(node)
//  }
}
