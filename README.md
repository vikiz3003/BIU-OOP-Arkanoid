# Arkanoid (Java)

An advanced **Arkanoid-style game** built in **Java** as part of an **Object-Oriented Programming (OOP)** course.  
The project demonstrates the use of **design patterns**, clean architecture, and interactive gameplay mechanics.

---

##  Screenshots

<p align="center">
  <img width="300" alt="Image1" src="https://github.com/user-attachments/assets/29fdecc3-4118-4d50-b326-275691f61fbe" />
  <img src="screenshots/screen2.png" width="250" />
  <img src="screenshots/screen3.png" width="250" />
</p>

---

## Game Overview

The game starts with three balls, each bouncing across the screen.  
A ball can destroy a block only if its color is different from the block’s color.  
After hitting and destroying a block, the ball changes its color to match the block it just hit.  
If the balls reach the bottom of the screen, they disappear.  
The game ends when **all balls are lost**.

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

- **← Left Arrow** - Move paddle left  
- **→ Right Arrow** - Move paddle right  

The paddle’s movement direction and the hit position determine the **ball’s reflection angle**.

---


