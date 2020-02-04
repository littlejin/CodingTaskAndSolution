object BigNumber {
  // 处理结果中的十进制进位问题
  private def fold(result: Array[Int], idx: Int): Unit = {
    val num = result(idx)
    result(idx) %= 10
    result(idx - 1) += (num / 10)
  }

  // 大数相乘
  def multiple(numA: Array[Int], numB: Array[Int]): Array[Int] = {
    val result = new Array[Int](numA.length + numB.length)

    numA.indices.reverse.foreach { i =>
      numB.indices.reverse.foreach { j =>
        val idx = i + j + 1 // 预留一位用于十进制进位
        result(idx) += numA(i) * numB(j)
        fold(result, idx)
      }
    }

    result
  }

  // 大数相加
  def add(numA: Array[Int], numB: Array[Int]): Array[Int] = {
    // 多1位预留做进位使用
    val size = Math.max(numA.length, numB.length) + 1
    val result = new Array[Int](size)

    // 分别从两个数字的最后一位开始相加
    var idxA = numA.length - 1
    var idxB = numB.length - 1
    var idx = size - 1

    while (idx > 0) {
      val a = if (idxA >= 0) numA(idxA) else 0
      val b = if (idxB >= 0) numB(idxB) else 0
      result(idx) += (a + b)
      fold(result, idx)

      idx -= 1
      idxA -= 1
      idxB -= 1
    }

    result
  }
}
