package scala

class CListIterator(cur: CListContainer, he: CListContainer, ta: CListContainer) {
  var current: CListContainer = cur
  var head: CListContainer = he
  var tail: CListContainer = ta

  def next(): Unit = {
    if (has_next()) {
      current = current.get_next()
    }
  }

  def get_key(): String = {
    if (current != null) {
      current.get_key()
    } else {""}
  }

  def set_key(key: String): Unit = {
    current.set_key(key)
  }

  def clear(): Unit = {
    current = null
    head = null
    tail = null
  }

  def has_next(): Boolean = {
    if (current != null) {
      if (current.get_next() != null) {
        true
      } else {
        false
      }
    } else {
      false
    }
  }

  def go_to_head(): Unit = {
    current = head
  }

  def foreach(f: CListContainer => Unit): Unit = {
    current = head
    f(current)
    this.next()
    while (current != head) {
      f(current)
      this.next()
    }
  }
}
