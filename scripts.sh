## 개발용 DB
docker run -p 5432:5432 --name class-db -e POSTGRES_USER=class -e POSTGRES_PASSWORD=class -e POSTGRES_DB=class -d postgres

## 테스트용 DB
docker run -p 15432:5432 --name class-testdb -e POSTGRES_USER=studytest -e POSTGRES_PASSWORD=studytest -e POSTGRES_DB=studytest -d postgres