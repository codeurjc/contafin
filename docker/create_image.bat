@echo off
cd ..

echo Packaging app
call mvn clean package

echo Building docker image
call docker build -f docker/Dockerfile -t jaimemorillo/contafin:latest .
echo Done