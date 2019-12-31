/* 已知有两个数组，每个数组中包含若干个随机数字，求两个数组中数字的交集
 * 仅能使用数组结构 */
object CommonNumsInTwoArray {
  private def intersect(sortedArrayA: Array[Int], sortedArrayB: Array[Int]): Array[Int] = {
    val bldr = Array.newBuilder[Int]
    var i, j = 0
    var lastSelected = false
    var lastSelectNum = 0

    while (i < sortedArrayA.length && j < sortedArrayB.length) {
      val numA = sortedArrayA(i)
      val numB = sortedArrayB(j)

      if (numA < numB) {
        i += 1
      } else if (numA > numB) {
        j += 1
      } else { // 因为是已经排过序的数组，所以直接跟上一个被选出来的元素比对，就可以实现去重
        lazy val isDistinct = !lastSelected || lastSelectNum != numA

        if (numA == numB && isDistinct) {
          bldr += numA
          lastSelected = true
          lastSelectNum = numA
          i += 1
        }
      }
    }

    bldr.result()
  }

  def getCommonNums(arrayA: Array[Int], arrayB: Array[Int]): Array[Int] = {
    val sortedArrayA = QuickSort.quickSort(arrayA)
    val sortedArrayB = QuickSort.quickSort(arrayB)
    intersect(sortedArrayA, sortedArrayB)
  }
}
