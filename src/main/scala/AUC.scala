// 根据AUC的定义：将正样本排在负样本前的概率，计算AUC；不使用通过计算ROC曲线面积的方式计算AUC

import scala.util.control.Breaks

object AUC {
  // data: Sequence of (label, predicted ctr)
  def calculate(data: Seq[(Int, Double)]): Double = {
    val posBldr, negBldr = Array.newBuilder[Double]

    // 划分正负样本
    data.foreach { case (label, pctr) =>
      if (label > 0) posBldr += pctr else negBldr += pctr
    }

    // 正负样本根据pctr从小到大排序, 减少遍历次数
    val posPctr = posBldr.result().sorted
    val negPctr = negBldr.result().sorted

    var count = 0D // 记录根据pctr判断, 有多少正样本排列在负样本前面
    var countByItr = 0D
    var idx = 0

    posPctr.foreach { pctr =>
      Breaks.breakable {
        while (idx < negPctr.length) {
          val _pctr = negPctr(idx)

          if (pctr > _pctr) {
            countByItr += 1D
          } else if (pctr == _pctr) {
            countByItr += 0.5
          } else {
            Breaks.break()
          }

          idx += 1
        }
      }

      count += countByItr
    }

    count / (posPctr.length * negPctr.length)
  }
}
