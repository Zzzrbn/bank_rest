# Система управления банковскими картами

В корне два SQL запроса для создания структуры и наполнения таблиц базы данных для проверки работоспособности приложения


После запуска обращения к БД осуществляются через GET HTTP запросы:

Для пользователей с ролью ADMIN

 http://localhost:8080/admin/cards - просмотр карт в таблице

 http://localhost:8080/admin/cards/{id} - просмотр карты с {id}

 http://localhost:8080/admin/newcard/{number}/{holder}/{expdate}/{status}/{balance} - создание и добавление в таблицу новой карты
 number - должен состоять из 16 цифр
 holder - фамилия держателя. По фамилии проверяется принадлежность к пользователю.
 expdate - срок действия карты
 status -     ACTIVE, BLOCKED или EXPIRED

 http://localhost:8080/admin/delete/{id} - удаление карты с {id}

 http://localhost:8080/admin/activate/{id} - активация карты с {id}

 http://localhost:8080/admin/block/{id} - блокировка карты с {id}

Для пользователей с ролью USER или ADMIN

 http://localhost:8080/user/cards - просмотр своих карт

 http://localhost:8080/user/cards/{id1}/{id2}/{amount} - перевод денежных средств {amount} со своей карты {id1} на свою карту {id2}

	

