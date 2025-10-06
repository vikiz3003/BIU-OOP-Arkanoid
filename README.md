# Arkanoid (Java)

An advanced **Arkanoid-style game** built in **Java** as part of an **Object-Oriented Programming (OOP)** course.  
The project demonstrates the use of **design patterns**, clean architecture, and interactive gameplay mechanics.

---

## Game Overview

The game starts with three balls, each bouncing across the screen.  
A ball can destroy a block only if its color is different from the block’s color.  
After hitting and destroying a block, the ball changes its color to match the block it just hit.  

The score increases live and is displayed at the top of the game window.

---

## Core Features

-  **Color-based mechanics:**  
  Each ball and block has a color. The player must hit blocks using balls of a different color.  

-  **Dynamic color change:**  
  After a successful hit, the ball adopts the color of the destroyed block.

-  **Live score counter:**  
  The score updates in real time as the player breaks blocks.

-  **Multiple balls:**  
  The game begins with **3 balls**, all active simultaneously.

-  **Physics-based collision:**  
  The reflection angle depends on the exact impact point on the paddle.

-  **OOP & Design Patterns:**  
  The project demonstrates concepts such as encapsulation, polymorphism, inheritance, interfaces, and the use of **design patterns** such as **Observer**.

---

##  Controls

- **← Left Arrow** — Move paddle left  
- **→ Right Arrow** — Move paddle right  

The paddle’s movement direction and the hit position determine the **ball’s reflection angle**.

---

##  Screenshots

![Game Start](assets/screenshots/Screenshot1.png)
![In-Game](assets/screenshots/Screenshot2.png)
