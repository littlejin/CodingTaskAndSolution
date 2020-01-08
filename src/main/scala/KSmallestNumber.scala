import scala.collection.mutable

// 从一个无序数组中选出k个最小值, 仅能使用数组结构
object KSmallestNumber {
  private def findKSmallestNumber(nums: Array[Int],
                                  start: Int,
                                  end: Int,
                                  k: Int,
                                  bldr: mutable.Builder[Int, Array[Int]]): Unit = {
    /* 一轮排序后得到offset，保证offset是对应的数字位于完整排序后所属的位置
     * 同时位于左边的数字都小于nums(offset)，位于右边的数字都大于nums(offset) */
    val offset = QuickSort.sort(nums, start, end)
    val CountFromStartToOffset = offset - start + 1 // 左边的数字一共有多少个

    if (CountFromStartToOffset <= k) { // 此时代表offset左边位置的数字, 都是包含在top k最小值中的
      start.to(offset).foreach { idx =>
        bldr += nums(idx)
      }

      if (CountFromStartToOffset != k) { // 此时代表还没有选够k个，从右边的数字中继续挑选
        findKSmallestNumber(nums, offset + 1, end, k - CountFromStartToOffset, bldr)
      }
    } else { // 此时代表左边的数字个数超过k个，从左边的数字中筛选
      findKSmallestNumber(nums, start, offset, k, bldr)
    }
  }

  def getTopKSmallestNumber(nums: Array[Int], k: Int): Array[Int] = {
    val bldr = Array.newBuilder[Int]
    bldr.sizeHint(k)
    findKSmallestNumber(nums, 0, nums.length - 1, k, bldr)
    bldr.result()
  }
}
