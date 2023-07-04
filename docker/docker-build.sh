#!bin/bash

# 버전 설정
VERSION='1.0.0'

#상위 디렉토리로 이동
cd ..

#gradle로 빌드 수행
./gradlew clean build -x test

ROOT_PATH=`pwd`
echo $ROOT_PATH

echo 'api Docker Image build...'
cd $ROOT_PATH/api && docker build -t api:$VERSION .
echo 'api Docker Image build... Done!!'

echo 'nginx Docker Image build...'
cd $ROOT_PATH/nginx && docker build -t nginxcustom:$VERSION .
echo 'nginx Docker Image build... Done!!'
