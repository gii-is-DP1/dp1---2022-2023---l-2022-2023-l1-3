# Xtreme Parchis&Oca

Diseño, pruebas e implementación online de los tradicionales juegos de mesa parchís y oca.

## Instrucciones de los juegos

### Parchís

El parchís es un juego de “carreras” en el que participan 4 jugadores, cada uno representando un color diferente: amarillo, azul, verde y rojo. Se utiliza un dado para determinar el avance que las fichas realizan a través de un recorrido en forma de cruz. Se emplean 4 fichas de cada color y el objetivo es completar el recorrido con todas las fichas.

<div align="center"><img src="https://www.65ymas.com/uploads/s1/15/68/71/el-parchis-tambien-se-juega-en-app-sabes-como.jpeg" alt="tablero_parchis" width="300" height="300"/></div>

#### Tablero

El tablero del parchís tiene varias zonas diferenciadas:

- La casilla de salida, situadas en las esquinas del tablero.
- Un recorrido común de 68 casillas.
- Pasillo y casilla final de los 4 colores, que convergen en el centro del tablero.

#### Movimiento de fichas

Inicialmente cada jugador cuenta con un par de fichas en la casilla inicial del recorrido, y el resto de fichas situadas en casa. 

Los participantes en su turno deben tirar el dado y avanzar con una de sus fichas el número de casillas indicado por el número obtenido, teniendo en cuenta las siguientes reglas: 

- En caso de tirar un 5 y tener fichas en casa, una de ellas entra en juego obligatoriamente situándose en la casilla de salida. 
- Las fichas avanzan siguiendo el recorrido en sentido inverso a las agujas del reloj.
- Cuando una ficha completa una vuelta al tablero y llega a la casilla que conecta con el pasillo de su color, se desvía hacia la casilla destino (hacia el centro del tablero).
- En caso de tirar un 6 y tener todas las fichas del mismo color fuera de casa, se avanzan 7 casillas.
- El jugador que ha obtenido un 6 juega de nuevo.
- En cada casilla del recorrido puede haber un máximo de dos fichas. Una ficha no puede jugarse si el movimiento le llevara a una casilla en la que ya hay dos fichas (barrera).
- Una ficha no puede moverse si para completar el avance tuviera que atravesar una barrera. Las barreras son pares de fichas de un mismo color situadas en la misma casilla.
- Para alcanzar el final del recorrido es necesario avanzar el número exacto de casillas que restan hasta completarlo. No es posible mover la ficha si el número del dado es superior.
- Cuando una ficha completa su recorrido, el jugador debe avanzar 10 casillas con otra de sus fichas.
- Puede darse el caso de que todas las fichas estén bloqueadas (por estar en casa, o tras una barrera, o en el final del recorrido). En este caso, simplemente no se realiza ningún movimiento.

#### Capturas

Una ficha “come” a otra de diferente color si finaliza su avance en la casilla ocupada por esta última.

- Las casillas de salida y las casillas marcadas con un círculo son seguros, donde las capturas no son posibles. Por tanto, en los seguros pueden coincidir dos fichas de diferente color.
- Si en la casilla de salida se encuentran dos fichas de diferente color y una nueva ficha sale de su casa, la ficha de diferente color (o si ambas lo son, la última ficha que hubiera llegado a la casilla) resulta capturada.
- Las fichas comidas vuelven a su casa, de manera que vuelven a entrar en juego cuando el jugador obtiene un 5 en su tirada.
- Quien come una ficha tiene el premio de avanzar 20 casillas con cualquiera de sus fichas.
- Si un jugador obtiene un 6 tres veces consecutivas, el tercer movimiento no se realiza, y la ficha movida con el segundo 6 vuelve a su casa, salvo que ya hubiese alcanzado el pasillo final del recorrido.

### Oca

La oca es un juego de mesa en el que participa un mínimo de 2 jugadores y un máximo de 4. El objetivo es llegar a la casilla central, saltando posiciones, según la tirada del dado y sometido a unas reglas de juego, establecidas por cada casilla. 

<div align="center"><img src="https://juegosmesa-14eb7.kxcdn.com/wp-content/uploads/2020/02/instrucciones-como-jugar-a-la-oca.jpg.webp" alt="tablero_oca" width="300" height="300"/></div>

#### Casillas especiales

- Oca: Casillas 5, 9, 14, 18, 23, 27, 32, 36, 41, 45, 50, 54 y 59. Si se cae en una de estas casillas, se puede avanzar hasta la siguiente casilla en la que hay una oca y volver a tirar.
- Puente: Casilla 6 y 12. Si se cae en estas casillas se salta a la casilla 19 (la Posada) y se pierde un turno. En algunos tableros, solo figura como puente la casilla 6.
- Posada: Casilla 19. Si se cae en esta casilla se pierde un turno.
- Pozo: Casilla 31. Si se cae en esta casilla, NO se puede volver a jugar hasta que no pase otro jugador por esa casilla.
- Laberinto: Casilla 42. Si se cae en esta casilla, se está obligado a retroceder a la casilla 30.
- Cárcel: Casilla 56. Si se cae en esta casilla, hay que permanecer dos turnos sin jugar.
- Dados: Casillas 26 y 53. Si se cae en estas casillas, se suma la marcación de la casilla de los dados (26 o 53) y se avanza tanto como resulte.
- Calavera: Casilla 58. Si se cae en esta casilla, hay que volver a la Casilla 1.
- Meta: Es necesario sacar los puntos justos para entrar, en caso de exceso se retroceden tantas casillas como puntos sobrantes.
