import java.util
import scala.util.control.Breaks

// 快排算法的递归版本及非递归版本的实现
object QuickSort {
  // 根据Index交换数组中的两个元素
  private def swap[T](nums: Array[T], i: Int, j: Int): Array[T] = {
    val temp = nums(i)
    nums(i) = nums(j)
    nums(j) = temp
    nums
  }

  /* 一轮对数组中的元素进行调整后返回comparedNum的位置
   * 我们通过调整数组中元素的位置，找到一个合适的位置
   * 当我们将comparedNum放在这个位置的时候，确保数组在其左边的元素都小于它，数据在其右边的元素都大于它 */
  private def sort(nums: Array[Int], start: Int, end: Int): Int = {
    var i = start + 1
    var j = end
    val comparedNum = nums(start)

    Breaks.breakable {
      while (true) {
        var numA = nums(i)
        var numB = nums(j)

        // 从左边开始找到第一个大于base的数字对应的index
        while (numA <= comparedNum & i < j) {
          i += 1
          numA = nums(i)
        }

        // 从右边开始找到第一个小于base的数字对应的index
        while (numB > comparedNum && j > i) {
          j -= 1
          numB = nums(j)
        }

        if (i != j) {
          swap(nums, i, j)
        } else {
          swap(nums, start, i - 1)
          Breaks.break()
        }
      }
    }

    i
  }

  // 快排算法的非递归实现
  def quickSort(nums: Array[Int]): Array[Int] = {
    val records = new util.Stack[Int]()
    records.push(0)
    records.push(nums.length - 1)

    while (!records.isEmpty) {
      val end = records.pop()
      val start = records.pop()

      if (start < end) {
        val offset = sort(nums, start, end)
        records.push(start)
        records.push(offset - 1)

        records.push(offset)
        records.push(end)
      }
    }

    nums
  }

  // 快排算法的递归实现
  def quickSort(nums: Array[Int], start: Int, end: Int): Array[Int] = {
    val offset = sort(nums, start, end)

    if (offset > start + 1) { // 此时代表左边还没有排好序
      quickSort(nums, start, offset - 1)
    }

    if (offset < end) { // 此时代表右边还没有排好序
      quickSort(nums, offset, end)
    }

    nums
  }
}

