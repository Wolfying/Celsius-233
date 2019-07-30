# Celsius-233

### Instalacion por cmd/terminal
```sh
$ mvn compile & mvn install
$ java -jar target/Celsius-1.0-SNAPSHOT.jar
```

### Instalación manual de dependencias (IDE Eclipse)
Si se ejecuta a través de STS entonces se deben instalar las dependencias de forma manual.

1.- Salir de STS
2.-  Ir a ~\.m2\repository\org\projectlombok\lombok\1.16.20 (Ej: C:\Users\nombre\.m2\repository\org\projectlombok\lombok\1.16.20) y ejecutar:

```sh
$ java -jar lombok-1.16.20.jar
```

3.- Actualizar el proyecto: _click derecho en proyecto &rarr; Maven &rarr; Update Project_