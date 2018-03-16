@echo off
echo Publishing image
cd ..
call docker push jaimemorillo/contafin:latest
echo Done