# Как использовать программу
## Клонирование репозитория
```shell
cd $HOME
git clone ...
cd $HOME/
```
## Установка программы и ее зависимостей
```shell
mvn clean install
java -jar ./target/demo-0.0.1-SNAPSHOT.jar
```
## Создание пользователей - администратора и пользователя
```shell
curl -X POST http://localhost:7070/api/create-users 
```
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
```shell

```