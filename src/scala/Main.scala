package scala

import java.io.{File, PrintWriter}

import scala.io.Source
import scala.io.StdIn.readLine

object Main {

  def main(args: Array[String]): Unit = {
    test()
  }

  def generate_key(): String = {
    val x = scala.util.Random
    val r: Int = x.nextInt(15)
    r.toString
  }

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

  class CList() {

    var iter: CListIterator = new CListIterator(null, null, null)

    def create(number: Int): Unit = {
      if (iter.head != null) {
        var prev: CListContainer = iter.tail
        for (_ <- 0 until number - 1) {
          val temp: CListContainer = new CListContainer(null, generate_key())
          prev.set_next(temp)
          prev = temp
        }
        prev.set_next(iter.head)
        iter.head = prev
      } else {
        var prev: CListContainer = new CListContainer(null, generate_key())
        iter.head = prev
        iter.current = prev
        for (_ <- 0 until number - 1) {
          val temp: CListContainer = new CListContainer(null, generate_key())
          prev.set_next(temp)
          prev = temp
        }
        prev.set_next(iter.head)
        iter.head = prev
      }
      for (i <- iter) {
        iter.tail = i
      }
    }

    def next(): Unit = {
      iter.next()
    }

    def print_element(): Unit = {
      println(iter.get_key())
    }

    def get_element(): String = {
      iter.get_key()
    }

    def print_all(): Unit = {
      var temp: Int = 0
      for (i <- iter) {
        print("\t[" + temp + "] ")
        println(i.get_key())
        temp = temp + 1
      }
    }

    def get_all(): String = {
      var res: String = ""
      var temp: Int = 0
      for (i <- iter) {
        res = res + "\t[" + temp + "] " + i.get_key() + "\n"
        temp = temp + 1
      }
      res
    }

    def clear(): Unit = {
      iter.clear()
    }

    def go_to_head(): Unit = {
      iter.go_to_head()
    }

    def find_by_key(key: String): String = {
      var res: String = ""
      var temp: Int = 0
      for (i <- iter) {
        if (i.get_key() == key) {
          res = res + "ключ " + key + " найден с индексом [" + temp + "]"
        }
        temp = temp + 1
      }
      res
    }

    def find_by_index(index: Int): String = {
      var res: String = ""
      var temp: Int = 0
      for (i <- iter) {
        if (temp == index) {
          res = "ключ " + i.get_key() + " найден с индексом [" + temp + "]"
        }
        temp = temp + 1
      }
      res
    }

    def change_element_by_index(index: Int, new_key: String): String = {
      var res: String = ""
      var temp: Int = 0
      for (i <- iter) {
        if (temp == index) {
          res = "Элемент c ключом " + i.get_key() + " заменен на ключ " + new_key + " с индексом [" + temp + "]"
          i.set_key(new_key)
        }
        temp = temp + 1
      }
      res
    }

    def len(): Int = {
      var res: Int = 0
      for (_ <- iter) {
        res = res + 1
      }
      res
    }

    def sort(f: (String, String) => Boolean): Unit = {
      val len: Int = this.len()
      for (_ <- 0 until len) {
        for (_ <- 0 until len - 1) {
          val key1: String = iter.get_key()
          val key2: String = iter.current.next_element.get_key()
          if (f(key1, key2)) {
            iter.set_key(key2)
            iter.current.next_element.set_key(key1)
          }
          iter.next()
        }
        iter.next()
      }
    }

    def input_by_index(index: Int, string: String): Unit = {
      if (index != 0) {
        var temp: Int = 0
        for (i <- iter) {
          if (temp == index - 1) {
            val new_elem: CListContainer = new CListContainer(i.get_next(), string)
            i.set_next(new_elem)
          }
          temp = temp + 1
        }
        if (iter.tail.get_next() != iter.head) {
          for (i <- iter)
            iter.tail = i
        }
      } else {
        val new_elem: CListContainer = new CListContainer(iter.head, string)
        iter.tail.set_next(new_elem)
        iter.head = new_elem
      }
    }

    def input_in_end(string: String): Unit = {
      val new_elem = new CListContainer(iter.head, string)
      iter.tail.set_next(new_elem)
      iter.tail = new_elem
    }

    def delete_by_index(index: Int): Unit = {
      if (index != 0 && index <= this.len() - 1) {
        var temp: Int = 0
        for (i <- iter) {
          if (temp == index - 1) {
            i.set_next(i.get_next().get_next())
          }
          iter.tail = i
          temp = temp + 1
        }
      } else {
        iter.tail.set_next(iter.head.get_next())
        iter.head = iter.head.get_next()
      }
    }

    def save_in_file(path: String): Unit = {
      val filePath = path + "Clist.txt"
      val writer = new PrintWriter(new File(filePath))
      for (i <- iter) {
        writer.write(i.get_key() + "\n")
      }
      writer.close()
    }

    def load_from_file(path: String): Unit = {
      var fSource = Source.fromFile(path)
      val mass: Array[String] = fSource
        .getLines()
        .mkString("\n")
        .split("\n")
        .reverse
      var prev: CListContainer = new CListContainer(null, "____")
      iter.tail = prev
      for (i <- 0 until mass.length) {
        val temp: CListContainer = new CListContainer(prev, mass(i))
        prev = temp
      }
      iter.head = prev
      iter.tail.set_next(iter.head)
      iter.current = prev
      fSource.close()
      this.delete_by_index(this.len() - 1)
    }
  }

  def test(): Unit = {
    val test: CList = new CList()
    println("Создаем список из 10 элементов:")
    test.create(5)
    test.print_all()
    println("Вывод элемента на который указывает итератор:")
    test.print_element()
    println("Поиск по ключу: " + test.iter.current.get_key())
    var k = test.find_by_key(test.iter.current.get_key())
    println(k)
    println("Поиск по индексу : 4")
    k = test.find_by_index(4)
    println(k)
    println("Замена ключей элементов с индексами 0,3,4 на их индексы:")
    k = test.change_element_by_index(0, "2")
    k = test.change_element_by_index(3, "3")
    k = test.change_element_by_index(4, "4")
    test.print_all()
    System.out.println("Длинна списка:")
    println("Длинна списка:")
    println(test.len())

    //функция -> правило сравнения двух ключей
    def f(string1: String, string2: String): Boolean = {
      string1 >= string2
    }
    println("Сортировка списка:")
    test.sort(f)
    test.print_all()
    println("Вставка элемента на 2 место:")
    test.input_by_index(2, "sd")
    test.print_all()
    println("Вставка элемента в конец")
    test.input_in_end("new end")
    test.print_all()
    println("Удаление элемента с индексом 2:")
    test.delete_by_index(2)
    test.print_all()
    println("Сохранение списка и загрузка из файла:")
    test.save_in_file("")
    test.load_from_file("Clist.txt")
    test.print_all()
  }
}
