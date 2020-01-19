object BigNumber {
  // 处理结果中的十进制进位问题
  private def fold(result: Array[Int]): Array[Int] = {
    var idx = result.length - 1
    while (idx > 0) {
      val num = result(idx)
      result(idx) %= 10
      result(idx - 1) += (num / 10)
      idx -= 1
    }

    result
  }

  // 大数相乘
  def multiple(numA: Array[Int], numB: Array[Int]): Array[Int] = {
    val result = new Array[Int](numA.length + numB.length)

    // 先不考虑进位，两个数字每一位分别相乘
    numA.indices.foreach { i =>
      numB.indices.foreach { j =>
        val idx = i + j + 1  // 赋值的时候先往后移动1位，预留做进位使用
        result(idx) += numA(i) * numB(j)
      }
    }

    fold(result)
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

      idx -= 1
      idxA -= 1
      idxB -= 1
    }

    fold(result)
  }

}
