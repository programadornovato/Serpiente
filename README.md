# Juego de la Serpiente

Este es un juego simple de la serpiente implementado en Java utilizando Swing para la interfaz gráfica. El juego es controlado por el usuario mediante entradas del teclado.

## Características
- La serpiente se mueve dentro de una cuadrícula predefinida.
- El juego muestra un borde y comida para la serpiente.
- La serpiente crece en tamaño cuando come la comida.
- El juego se reinicia si la serpiente choca contra las paredes o contra sí misma.

## Comenzando

### Prerrequisitos
- Java Development Kit (JDK) 8 o superior
- Un IDE de Java o un editor de texto (por ejemplo, IntelliJ IDEA, Eclipse, VSCode)

### Instalación
1. Clona el repositorio:
    ```sh
    git clone https://github.com/yourusername/snake-game.git
    ```
2. Navega al directorio del proyecto:
    ```sh
    cd snake-game
    ```

### Ejecutando el Juego
1. Abre el proyecto en tu IDE preferido.
2. Ubica el archivo `NewJFrame.java` y ejecútalo.
3. Aparecerá la ventana del juego. Presiona el botón "Comenzar" para iniciar el juego.
4. Utiliza las siguientes teclas para controlar la serpiente:
    - `W` o `8`: Mover hacia arriba
    - `S` o `5`: Mover hacia abajo
    - `A` o `4`: Mover hacia la izquierda
    - `D` o `6`: Mover hacia la derecha

### Mecánicas del Juego
- La serpiente comenzará a moverse en la dirección especificada.
- El objetivo es comer la comida (representada por `O`) que aparece aleatoriamente en la cuadrícula.
- Cada vez que la serpiente come la comida, crece en longitud.
- El juego termina si la serpiente choca contra la pared o contra sí misma, y se muestra un mensaje de "PERDISTE".

## Estructura del Código
- `HiloJuego.java`: Contiene la lógica del juego y el bucle principal del juego.
- `NewJFrame.java`: La implementación de la interfaz gráfica del juego.

### Clases y Métodos Principales
- `HiloJuego`: Implementa `Runnable` para ejecutar la lógica del juego en un hilo separado.
    - `run()`: El bucle principal del juego.
    - `posisionMatrizSerpiente()`: Actualiza la posición de la serpiente en la cuadrícula.
    - `seMueve(String ultima)`: Determina la dirección del movimiento según la entrada del usuario.
    - `limpiSerpiente()`: Limpia la posición de la serpiente.
    - `limpiaComida()`: Limpia la posición de la comida.
    - `posisionAleatoriaComida()`: Coloca la comida aleatoriamente en la cuadrícula.
    - `ultimaLetra(String texto)`: Obtiene el último carácter del campo de entrada.
    - `pausa(long sleeptime)`: Pausa el bucle del juego durante un tiempo especificado.

- `NewJFrame`: Extiende `javax.swing.JFrame` para crear la ventana del juego.
    - `initComponents()`: Inicializa los componentes de la interfaz gráfica.
    - `btnComenzarActionPerformed(java.awt.event.ActionEvent evt)`: Inicia el juego cuando se presiona el botón "Comenzar".

## Contribuyendo
¡Las contribuciones son bienvenidas! Por favor, bifurca el repositorio y crea un pull request con tus cambios.

## Licencia
Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.

## Agradecimientos
- Java Swing por los componentes de la interfaz gráfica.
- FlatLaf por el tema oscuro.

## Contacto
Eugenio Chaparro Maya - [info@programadornovato.com](mailto:info@programadornovato.com)

Enlace del proyecto: [https://github.com/yourusername/snake-game](https://github.com/yourusername/snake-game)
