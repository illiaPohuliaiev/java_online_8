javac -sourcepath ./src -d build/classes -cp ./libs/commons-lang3-3.12.0.jar ./src/ua/com/alevel/HelloTwo.java
jar cvfm build/jar/hello.jar resources/MANIFEST.MF -C build/classes .
java -jar build/jar/hello.jar