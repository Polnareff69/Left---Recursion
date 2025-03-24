# 🎭 Left Recursion

**Juan Jose Muñoz Gomez**  
📌 *ID: 4381*  
💻 *Windows 10 - Java 23 - IntelliJ IDEA Community*

---

## 🚀 Cómo ejecutar el programa

Para compilar el código desde la terminal:
```sh
javac Main.java

Para ejecutar el programa:

java Main

O simplemente presiona el botón de Ejecutar desde el IDE.
```

Eliminación de recursión por la izquierda

Primero, reemplaza las producciones que comienzan con un no terminal anterior por sus equivalentes. Luego, si un no terminal tiene recursión inmediata, se crea un nuevo símbolo auxiliar para reformular las reglas sin recursión. Así, la gramática resultante no tendrá producciones que comiencen con el mismo no terminal.