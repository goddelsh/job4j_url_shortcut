[![Build Status](https://travis-ci.com/goddelsh/job4j_url_shortcut.svg?branch=main)](https://travis-ci.com/goddelsh/job4j_url_shortcut)
[![codecov](https://codecov.io/gh/goddelsh/job4j_url_shortcut/branch/main/graph/badge.svg)](https://codecov.io/gh/goddelsh/job4j_url_shortcut)

Запуск при помощи docker-compose

Зависимости
maven
docker
docker-compose

1. Подготовка базы
1.1 
docker run -d \
    --name postgres \
    -e POSTGRES_PASSWORD=пароль_от_бд \
    -e PGDATA=/var/lib/postgresql/data/pgdata \
    -p 5432:5432 \
    -v postgres_db:/var/lib/postgresql/data \
    postgres
    
1.2 docker exec -it postgres bash
1.3 psql -U postgres
1.4 
CREATE DATABASE tracker;
\c tracker;
CREATE TABLE items(id SERIAL PRIMARY KEY, name text); 
1.5
exit
exit

2. Сборка проекта
2.1 Установка пароля в docker-compose.yml для образов вместо password (и прочие настройки опционально)
2.2 в директории пероекта 
mvn clean install -Dmaven.test.skip=true
2.3 docker build -t shortcut .
2.4 docker-compose up

Проверка запуска docker ps

Проект по умолчанию требует свободных портов 5432 и 8086