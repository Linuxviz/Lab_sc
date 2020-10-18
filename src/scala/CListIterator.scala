package scala

class CListIterator(cur: CListContainer, he: CListContainer, ta: CListContainer) {
  var current: CListContainer = cur
  var head: CListContainer = he
  var tail: CListContainer = ta

  def next(): Unit = {
    current = current.get_next()
  }

  def get_key(): String = {
    current.get_key()
  }

  def set_key(key: String): Unit = {
    current.set_key(key)
  }

  def clear(): Unit = {
    current = null
    head = null
    tail = null
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
