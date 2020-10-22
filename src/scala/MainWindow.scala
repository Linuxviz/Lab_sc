package scala

import java.awt.event.{ActionEvent, ActionListener, KeyEvent, KeyListener}
import java.awt.{BorderLayout, Container, GridLayout}
import java.util
import javax.swing.{JButton, JScrollPane, JTextArea, JTextField, JFrame}

class MainWindow(title: String) extends JFrame(title: String) {
  //Выделение памяти
  //Элементы консоли
  private val LeftInnerContainer: Container = new Container()
  private val RightInnerContainer: Container = new Container()
  private val OutputText = new JTextArea()
  OutputText.setEditable(false)
  private val ScrollPane = new JScrollPane(OutputText)
  private val TextField = new JTextField()
  //Интерактивные элементы
  private val Filler = new JButton("Заполнить список / добавть элементы")
  private val MakeEmpty = new JButton("Очистить список")
  private val ShowData = new JButton("Вывести список")
  private val SearchKey = new JButton("Найти элемент по ключу")
  private val SearchID = new JButton("Найти элемент по индексу")
  private val Remove = new JButton("Удалить элемент по индексу")
  private val Insert = new JButton("Вставить элемет по индексу")
  private val Edit = new JButton("Изменить элемент по индексу")
  private val AddInEnd = new JButton("Добавить элемент в конец")
  private val NextElement = new JButton("Переместить итератор на следующий элемент")
  private val PrintCurrentElement = new JButton("Вывести элемент на который указывает итератор")
  private val IterInStart = new JButton("Перевести итератор на первый элемент")
  private val Sort = new JButton("Сортировать список")
  private val Clear = new JButton("Очистить консоль")
  private val Save = new JButton("Сохранить список")
  private val Read = new JButton("Загрузить список")
  //Список и его итератор
  private val theList = new CList()
//  private var theItr = new CListIterator()
  //Флаги действий
  private val flags = Array(false, false, false, false, false, false, false)
  //Установка положения
  add(RightInnerContainer)
  add(LeftInnerContainer)
  LeftInnerContainer.setLayout(new BorderLayout)
  RightInnerContainer.setLayout(new GridLayout(8, 2))

  RightInnerContainer.add(Filler)
  RightInnerContainer.add(MakeEmpty)
  RightInnerContainer.add(ShowData)
  RightInnerContainer.add(SearchKey)
  RightInnerContainer.add(SearchID)
  RightInnerContainer.add(Remove)
  RightInnerContainer.add(Insert)
  RightInnerContainer.add(Edit)
  RightInnerContainer.add(AddInEnd)
  RightInnerContainer.add(NextElement)
  RightInnerContainer.add(PrintCurrentElement)
  RightInnerContainer.add(IterInStart)
  RightInnerContainer.add(Sort)
  RightInnerContainer.add(Clear)
  RightInnerContainer.add(Save)
  RightInnerContainer.add(Read)

  LeftInnerContainer.add(ScrollPane, BorderLayout.CENTER)
  LeftInnerContainer.add(TextField, BorderLayout.SOUTH)

  //События нажанит кнопок Enter
  val checker: ActionListener = new Handler()
  val keyChecker: KeyListener = new InputHandler()

  Filler.addActionListener(checker)
  MakeEmpty.addActionListener(checker)
  ShowData.addActionListener(checker)
  SearchKey.addActionListener(checker)
  SearchID.addActionListener(checker)
  Remove.addActionListener(checker)
  Insert.addActionListener(checker)
  Edit.addActionListener(checker)
  AddInEnd.addActionListener(checker)
  NextElement.addActionListener(checker)
  PrintCurrentElement.addActionListener(checker)
  IterInStart.addActionListener(checker)
  Sort.addActionListener(checker)
  Clear.addActionListener(checker)
  Save.addActionListener(checker)
  Read.addActionListener(checker)

  TextField.addKeyListener(keyChecker)

  def consoleOut(newStr: String): Unit = {
    val pastText: String = OutputText.getText()
    OutputText.setText(pastText + newStr + "\n")
  }

  private class Handler extends ActionListener {
    override def actionPerformed(e: ActionEvent): Unit = {
      if (e.getSource == SearchKey) {
        consoleOut("Введите ключ:")
        util.Arrays.fill(flags, false)
        flags(0) = true
      }

      if (e.getSource == SearchID) {
        consoleOut("Введите индекс:")
        util.Arrays.fill(flags, false)
        flags(1) = true
      }

      if (e.getSource == MakeEmpty) {
        theList.clear()
        consoleOut("Список очищен")
      }

      if (e.getSource == ShowData) {
        val listSize = theList.len()
        if (listSize == 0) {
          consoleOut("Пустой список")
        } else {
          consoleOut(theList.get_all())
          consoleOut("Размер списка: " + listSize + "\n")
        }
      }

      if (e.getSource == Filler) {
        consoleOut("Введите количество элементов:")
        util.Arrays.fill(flags, false)
        flags(5) = true
      }

      if (e.getSource == Remove) {
        consoleOut("Введите индекс удаляемого элемента:")
        util.Arrays.fill(flags, false)
        flags(2) = true
      }

      if (e.getSource == Insert) {
        consoleOut("Введите индекс под которым нужно добавть ключ и (через пробел) содержимое элемента:")
        util.Arrays.fill(flags, false)
        flags(3) = true
      }

      if (e.getSource == Edit) {
        consoleOut("Введите индекс изменяемого элемента и (через пробел) содержимое изменённого элемента:")
        util.Arrays.fill(flags, false)
        flags(4) = true
      }

      if (e.getSource == AddInEnd) {
        consoleOut("Введите ключ элемента")
        util.Arrays.fill(flags, false)
        flags(6) = true
      }

      if (e.getSource == NextElement) {
        theList.next()
        consoleOut("Итератор перенесен на следующий элемент")
      }

      if (e.getSource == PrintCurrentElement) {
        val key: String = theList.get_element()
        consoleOut("Элемент на который указывает итератор: " + key)
      }

      if (e.getSource == IterInStart) {
        theList.go_to_head()
        consoleOut("Итератор указывает на первый элемент")
      }

      if (e.getSource == Sort) {
        theList.sort((str1: String, str2: String) => str1 >= str2)
        consoleOut("Список отсортирован")
      }

      if (e.getSource == Clear) {
        OutputText.setText("")
      }

      if (e.getSource == Save) {
        theList.save_in_file()
        consoleOut("Список сохранен")
      }

      if (e.getSource == Read) {
        theList.load_from_file()
        consoleOut("Список загружен")
      }
    }
  }

  private class InputHandler extends KeyListener {
    override def keyPressed(e: KeyEvent): Unit = {
      if (e.getKeyCode == KeyEvent.VK_ENTER) {
        if (flags(0)) {
          flags(0) = false
          val searchKey: String = TextField.getText()
          TextField.setText("")
          consoleOut(searchKey)
          val res: String = theList.find_by_key(searchKey)
          if (res == "") {
            consoleOut("Элемент не найден")
          } else {
            consoleOut(res)
          }
        } else if (flags(1)) {
          flags(1) = false
          val searchKey: String = TextField.getText()
          TextField.setText("")
          consoleOut(searchKey)
          try {
            val searchIDint: Int = searchKey.toInt
            val res: String = theList.find_by_index(searchIDint)
            if (res == "") {
              consoleOut("Элемент не найден")
            } else {
              consoleOut(res)
            }
          } catch {
            case _: Exception => consoleOut("Ошибка, введены неверные данные. Ожидалось число")
          }
        } else if (flags(2)) {
          flags(2) = false
          val searchKey: String = TextField.getText()
          TextField.setText("")
          consoleOut(searchKey)
          try {
            val removeInt: Int = searchKey.toInt
            if (theList.len() > removeInt) {
              theList.delete_by_index(removeInt)
//              consoleOut("Ключ " + theItr.getElement() + " под индексом " + removeInt + " удалён");
            } else {
              consoleOut("Элемент не найден")
            }
          } catch {
            case _: Exception => consoleOut("Ошибка, введены неверные данные. Ожидалось число")
          }
        } else if (flags(3)) {
          flags(3) = false
          val inputText: String = TextField.getText()
          TextField.setText("")
          consoleOut(inputText)
          try {
            val delimiter: String = " " // Разделитель
            val subStr = inputText.split(delimiter) // Разделение строки str с помощью метода split()
            val insertInt: Int = subStr(0).toInt
            if (theList.len() + 1 > insertInt) {
              theList.input_by_index(insertInt, subStr(1))
              consoleOut("Ключ " + subStr(1) + " добавлен под номером " + insertInt)
            } else {
              consoleOut("Невозможно добавить элемент по заданной позиции")
            }
          } catch {
            case _: Exception => consoleOut("Ошибка, введены неверные данные")
          }
        } else if (flags(4)) {
          flags(4) = false
          val inputText: String = TextField.getText()
          TextField.setText("")
          consoleOut(inputText)
          try {
            val delimiter: String = " " // Разделитель
            val subStr = inputText.split(delimiter) // Разделение строки str с помощью метода split()
            val insertInt: Int = subStr(0).toInt
            if (theList.len() > insertInt) {
              val res: String = theList.change_element_by_index(insertInt, subStr(1))
              consoleOut(res)
            } else {
              consoleOut("Элемент не найден")
            }
          } catch {
            case _: Exception => consoleOut("Ошибка, введены неверные данные.")
          }
        } else if (flags(5)) {
          flags(5) = false
          val inputText: String = TextField.getText()
          TextField.setText("")
          consoleOut(inputText)
          try {
            val number: Int = inputText.toInt
            theList.create(number)
            consoleOut("Список заполнен")
          } catch {
            case _: Exception => consoleOut("Неверные данные")
          }
        } else if (flags(6)) {
          flags(6) = false
          try {
            val key: String = TextField.getText()
            TextField.setText("")
            consoleOut(key)
            theList.input_in_end(key)
            consoleOut("Элемент добавлен в конец списка")
          } catch {
            case _: Exception => consoleOut("Введены неверные данные")
          }
        } else {
          val inputText: String = TextField.getText()
          TextField.setText("")
          consoleOut(inputText)
          consoleOut("Неверная команда")
        }
      }
    }
    override def keyReleased(e: KeyEvent): Unit = {}
    override def keyTyped(e: KeyEvent): Unit = {}
  }
}
