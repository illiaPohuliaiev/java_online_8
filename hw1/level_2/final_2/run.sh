javac -sourcepath ./src -d build/classes -cp ./lib/commons-math3-3.6.1.jar ./src/ua/com/level/dokakoka.java
cd lib
jar xf commons-math3-3.6.1.jar
cp -rf ./org ../build/classes
cd ../
jar cvfm build/jar/dokakoka.jar resources/MANIFEST.MF -C build/classes .
java -jar build/jar/dokakoka.jar