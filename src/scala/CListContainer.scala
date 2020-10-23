package scala

class CListContainer(a0: CListContainer, val key: String) {
  var next_element: CListContainer = a0
  var key_of_element: String = key

  def get_next(): CListContainer = {
    next_element
  }

  def set_next(new_next: => CListContainer): Unit = {
    next_element = new_next
  }

  def get_key(): String = {
    key_of_element
  }

  def set_key(new_key: String): Unit = {
    key_of_element = new_key
  }
}
