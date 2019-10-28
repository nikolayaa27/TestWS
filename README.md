# Как использовать программу
## Клонирование репозитория
```shell
cd $HOME
git clone https://github.com/nikolayaa27/TestWS.git
cd $HOME/TestWS
```
## Установка программы и ее зависимостей
Для работы программы необходимо иметь установленную версию MySQL/Postres со следующими настройками в файле `application.properties`.

Для работы с MySQL
```properties
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost/sampleDB
# please change user accordingly
spring.datasource.username=
# please change password accordingly
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect
```
Для работы с Postgres:
```properties
spring.datasource.url=jdbc:postgresql://localhost/sampleDB
# please change user accordingly
spring.datasource.username=
# please change password accordingly
spring.datasource.password=
spring.jpa.generate-ddl=true
```
Также необходимо иметь предусмтановленный `Maven`, не ниже версии `3.5` и `curl` для возможности отправлять запросы на сервер. Вместо `curl` удобнее будет использовать `Postman`.

После установки всех зависимостей, можно запустить программу:
```shell
cd $HOME/TestWS
mvn clean install
java -jar ./target/demo-0.0.1-SNAPSHOT.jar
```
После вывода программой сообщения `Started DemoApplication in ... seconds (JVM running for ...)` можно приступать с работе с ней.
## Создание пользователей - администратора и пользователя
В первую очередь необходимо создать пользователей для последующей работы. Для данного примера мы создадим двух пользователей с разными правами - администратора и обычного пользователя.
```shell
curl -X POST http://localhost:7070/api/create-users 
```
После этого, можно приступать к непосредственной работе с погодой.
## Внесение данных о погоде по городу на определенную дату
```shell
curl -X POST http://localhost:7070/api/save \
  -H 'Content-Type: application/json' \
  -d '{
    "date": "2019-10-27",
    "city": "Rome",
    "user": "admin", 
    "pass": "pass",
    "conditions": {
        "humidity": 22,
        "temperature": 7
    }
}'
```
## Получение данных о погоде на определенную дату
Для запроса данных необходимо послать `GET` запрос по адресу `http://localhost:7070/api/retrieve` и в качестве параметра HTTP запроса с именем `req` необходимо отправить следующий JSON объект:
```json
{
  "date": "2019-10-27",
  "city": "Rome",
  "user": "admin",
  "pass": "pass"
}
```
или использовать `curl`:
```shell
curl -X GET http://localhost:7070/api/retrieve/\?req\=%7B%22date%22%3A%222019-10-27%22%2C%22city%22%3A%22Rome%22%2C%22user%22%3A%22admin%22%2C%22pass%22%3A%22pass%22%7D
```
## Обработка ошибок
В случае ошибки сервис вернет соответствующий HTTP код и выдаст сообщение. Ниже перечислены сообщения об ошибках, которые обрабатываются сервисом:
```
Bad Input Data
Check your user credentials
Sorry but you suppose to be an admin
Data not found
Data Saved!
``` 