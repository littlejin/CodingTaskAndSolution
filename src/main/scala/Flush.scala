import scala.collection.mutable
import scala.collection.mutable.{ListBuffer, HashMap => MHashMap}

/* 给一组纸牌(排除大小王)，判断是否存在同花顺
 * 纸牌的数字: A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K
 * 纸牌的花色：B, C, D, E
 * 所以每张纸牌是例如: B2, CA, ...*/

object Flush {
  private def parsePostfix(postfix: String): Int = {
    postfix match {
      case "A" => 1
      case "J" => 11
      case "Q" => 12
      case "K" => 13
      case other => other.toInt
    }
  }

  // 判断是否存在顺子, cards需要按照数字排好序，从小到大
  private def isFlush(cards: Seq[Int]): Boolean = {
    var startIdx = 0
    var endIdx = startIdx + 4

    while (endIdx < cards.size) {
      if (cards(endIdx) - cards(startIdx) == 4) {
        return true
      }

      startIdx += 1
      endIdx = startIdx + 4
    }

    false
  }

  def existsFlush(cards: Array[String]): Boolean = {
    val cardsIndex = new MHashMap[Char, mutable.ArrayBuilder[Int]]()
    cardsIndex.sizeHint(4)

    cards.foreach { card =>
      val prefix = card.head
      val postfix = parsePostfix(card.tail)

      val cards = cardsIndex.getOrElseUpdate(prefix, Array.newBuilder[Int])
      cards += postfix

      if (postfix == 1) cards += 14 // 用于处理10, J, Q, K, A的情况
    }

    cardsIndex.foreach { case (_, cards) =>
      val _cards = cards.result()
      if (_cards.length >= 5 && isFlush(_cards.sorted)) {
        return true
      }
    }

    false
  }

}
