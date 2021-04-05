# Реализация структуры двусвязный список, на языке Scala.
# Implementation of a doubly linked list structure in Scala.

*Задание:*  
Реализовать структуру данных двухсвязный список. Следующие операции должны потдерживаться: добавление в конец, получение, вставка и удаление по логическому номеру (индексу), итератор forEach, сортировка.Разработать графическое оконное приложение, работающее со структурой данных . Функции: отображение состояния, сохранение и загрузка файла.
*Классы и методы объектов:*

1) Класс: "clistcontainer" - класс-контейнер содержит ключ и ссылку на следующий элемент, последовательность таких контейнеров формирует список.  
- Параметры класса:
    - 1. "key", тип: string, ключ элемента передаваемый в конструктор
    - 2. “a0”. тип CListContainer, следующий элемент передаваемый в конструктор  
- Поля:
    - 1. "next_element", тип: clistcontainer, ключ элемента
    - 2. "key_of_element", тип: string, следующий элемент  
- Методы:
    - 1. "get_next", возвращает следующий элемент, тип возвр. знач.: clistcontainer
    - 2. "set_next», устанавливает следующий элемент на полученный в параметрах функции, параметры: new_next: clistcontainer, тип возвр. знач.: unit
    - 3. "get_key", возвращает ключ, содержащийся в контейнере, тип возвр. знач.: string
    - 4. "set_key», устанавливает ключ в контейнере на получаемый в параметрах функции, параметры: new_key: string, тип возвр. знач.: unit

2) Класс: "clistiterator" – итератор, который осуществляет управление списком.  
- Поля:
    - 1. "current", тип: clistcontainer, текущий выбранный элемент
    - 2. "head", тип: clistcontainer, начало списка
    - 3. "tail", тип: clistcontainer, конец списка  
- Методы:
    - 1. "next", переводит итератор на следующий элемент, тип возвр. знач.: unit
    - 2. "get_key", возвращает ключ выбранного элемента, тип возвр. знач.: string
    - 3. "set_key", устанавливает ключ выбранного элемента, параметры: key: string, тип возвр. знач.: unit
    - 4. "clear", устанавливает все поля на null, тип возвр. знач.: unit
    - 5. "has_next", проверка наличия следующего элемента (у циклического списка всегда есть следующий элемент, данный метод необходим для корректировки работы списка в случае, если все элементы были удалены или список еще не был создан), тип возвр. знач.: boolean
    - 6. "go_to_head", переводит итератор на начало списка, тип возвр. знач.: unit
    - 7. "foreach", метод расширяет применение foreach для данной структуры что позволяет обходить ее в цикле, в качестве параметра принимается функция которая будет применена ко всем элементам последовательности, параметры: f: clistcontainer => unit, тип возвр. знач.: unit

3) Класс: "clist" – класс замкнутого списка  
- Поля:
    - 1. "iter", тип: clistiterator, объект итератора, который будет контролировать состояние структуры.  
- Методы:
    - 2. "create", принимает число и либо дополняет список, либо создает новые элементы в количестве равном принятому числу, параметры: number: int, тип возвр. знач.: unit
    - 3. "next", в случае если следующий элемент есть переводит итератор на следующий элемент и возвращает true, если операция не удалась возвращает false, тип возвр. знач.: boolean
    - 4. "print_element», печатает выбранный элемент в консоль, тип возвр. знач.: unit
    - 5. "get_element", возвращает ключ выбранного элемента, тип возвр. знач.: string
    - 6. "print_all", печатает все элементы в консоль, тип возвр. знач.: unit
    - 7. "get_all" возвращает строку с индексами и ключами списка для вывода в графическом приложении, тип возвр. знач.: string
    - 8. "clear", очищает список, тип возвр. знач.: unit
    - 9. "go_to_head", переводит итератор на первый элемент, тип возвр. знач.: boolean
    - 10. "find_by_key", производит поиск элемента по ключу, параметры: key: string, тип возвр. знач.: string
    - 11. "find_by_index", производит поиск элемента пои индексу, параметры: index: int, тип возвр. знач.: string
    - 12. "change_element_by_index", заменяет элемент с полученным индексом на полученный ключ, параметры: index: int new_key: string, тип возвр. знач.: string
    - 13. "len", возвращает длину списка, тип возвр. знач.: int
    - 14. "sort», принимает функцию – правило сортировки и применяет ее к списку, в случае успешной сортировки возвращает правду, иначе возвращает ложь, параметры: f: (string string) => boolean, тип возвр. знач.: boolean
    - 15. "input_by_index», вставляет элемент с заданным ключом по индексу, параметры: index: int string: string, тип возвр. знач.: unit
    - 16. "input_in_end", вставляет элемент с заданным ключом в конец списка, параметры: string: string, тип возвр. знач.: unit
    - 17. "delete_by_index", удаляет элемент с полученным индексом, параметры: index: int, тип возвр. знач.: unit
    - 18. "save_in_file", сохраняет список в файл текущей директории проекта, параметры: path: string = "«, тип возвр. знач.: boolean
    - 19. "load_from_file",загружает файл из текущей директории проекта, параметры: path: string = "clist.txt», тип возвр. знач.: unit

Интерфейс программы аналогичен [интерфейсу данной программы](https://github.com/Linuxviz/singly-linked-loop-list-with-GUI-Java#реализация-структуры-двусвязный-список-на-языке-java).
