FROM openjdk
WORKDIR shortcut
ADD target/url_shortcut-0.0.1.jar app.jar
ENTRYPOINT java -jar app.jar
