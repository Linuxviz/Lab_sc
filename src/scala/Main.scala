package scala

//import scala.swing._
//
//class UI extends MainFrame {
//  title = "GUI Program #1"
//  preferredSize = new Dimension(320, 240)
//  contents = new Label("Here is the contents!")
//}
//
//object GuiProgramOne {
//  def main(args: Array[String]) {
//    val ui = new UI
//    ui.visible = true
//    println("End of main function")
//  }
//}

object Main {

  def main(args: Array[String]) = {
    test()
  }

  def generate_key(): String = {
    val x = scala.util.Random
    val r: Int = x.nextInt(15)
    r.toString
  }

  //Это нужно скомпилить для первой лабы
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
