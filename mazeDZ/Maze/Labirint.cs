﻿using System;
using System.Windows.Forms;
using System.Drawing;
using System.Collections.Generic;

namespace Maze
{
    public class Labirint
    {
        public int height; // высота лабиринта (количество строк)
        public int width; // ширина лабиринта (количество столбцов в каждой строке)

        private int playerMoves=0;
        private int playerX = 1;
        private int playerY = 1;

        private int health = 100;

        private int collectedMedals;
        public int totalMedals = 0;

        public MazeObject[,] maze;
        public PictureBox[,] images;

        private List<Enemy> enemies = new List<Enemy>();

        public static Random r = new Random();
        public Form parent;

        public Labirint(Form parent, int width, int height)
        {
            this.width = width;
            this.height = height;
            this.parent = parent;

            maze = new MazeObject[height, width];
            images = new PictureBox[height, width];

            Generate();
            UpdatePanel();
        }

        private void Generate()
        {
            for (int y = 0; y < height; y++)
            {
                for (int x = 0; x < width; x++)
                {
                    MazeObject.MazeObjectType current = MazeObject.MazeObjectType.HALL;

                    // в 1 случае из 7 - ставим стену
                    if (r.Next(7) == 0)
                    {
                        current = MazeObject.MazeObjectType.WALL;
                    }

                    // в 1 случае из 150 - кладём медальку
                    if (r.Next(150) == 0)
                    {
                        current = MazeObject.MazeObjectType.MEDAL;
                        totalMedals+=1;
                    }

                    // в 1 случае из 250 - размещаем врага
                    if (r.Next(250) == 0)
                    {
                        current = MazeObject.MazeObjectType.ENEMY;
                        var enemy = new Enemy(x, y);
                        enemies.Add(enemy);
                    }

                    // лекарство
                    if (r.Next(100) == 0)
                    {
                        current = MazeObject.MazeObjectType.MEDICINE;
                    }

                    // стены по периметру обязательны
                    if (y == 0 || x == 0 || y == height - 1 | x == width - 1)
                    {
                        current = MazeObject.MazeObjectType.WALL;
                    }

                    // наш персонажик
                    if (x == playerX && y == playerY)
                    {
                        current = MazeObject.MazeObjectType.CHAR;
                    }

                    // есть выход, и соседняя ячейка справа всегда свободна
                    if (x == playerX + 1 && y == playerY || x == width - 1 && y == height - 3)
                    {
                        current = MazeObject.MazeObjectType.HALL;
                    }

                    maze[y, x] = new MazeObject(current);
                    images[y, x] = new PictureBox
                    {
                        Location = new Point(x * maze[y, x].width, y * maze[y, x].height),
                        Parent = parent,
                        Width = maze[y, x].width,
                        Height = maze[y, x].height,
                        BackgroundImage = maze[y, x].texture,
                        Visible = false
                    };
                }
            }
        }

        public void Show()
        {
            for (int y = 0; y < height; y++)
            {
                for (int x = 0; x < width; x++)
                {
                    images[y, x].Visible = true;
                }
            }
        }

        public void KeyPressed(Keys key) 
        {
            MoveEnemies();
            MovePlayer(key);
        }

        public void MovePlayer(Keys key)
        {
            int newX = playerX;
            int newY = playerY;

            switch (key)
            {
                case Keys.Left:
                    newX--;
                    break;
                case Keys.Right:
                    newX++;
                    break;
                case Keys.Up:
                    newY--;
                    break;
                case Keys.Down:
                    newY++;
                    break;
            }

            if (maze[newY, newX].type != MazeObject.MazeObjectType.WALL)
            {
                playerMoves++;

                if(playerMoves % 10 ==0)
                {
                    SpawnEnemy();
                }

                images[playerY, playerX].BackgroundImage = new MazeObject(MazeObject.MazeObjectType.HALL).texture;

                playerX = newX;
                playerY = newY;

                images[playerY, playerX].BackgroundImage = new MazeObject(MazeObject.MazeObjectType.CHAR).texture;

                if(enemies.Count==0)
                {
                    MessageBox.Show("Победа - врагов не осталось!");
                    parent.Close();
                }
                else if (playerX == width - 1)
                {
                    MessageBox.Show("Победа - найден выход!");
                    parent.Close();
                }
                else if (maze[playerY, playerX].type == MazeObject.MazeObjectType.MEDAL)
                {
                    maze[playerY, playerX].type = MazeObject.MazeObjectType.HALL;
                    collectedMedals++;
                    UpdatePanel();

                    if (collectedMedals == totalMedals)
                    {
                        MessageBox.Show("Победа - все медали собраны!");
                        parent.Close();
                    }
                }
                else if (maze[playerY, playerX].type == MazeObject.MazeObjectType.ENEMY)
                {
                    maze[playerY, playerX].type = MazeObject.MazeObjectType.HALL;
                    PlayerDamage();
                }
                else if (maze[playerY, playerX].type == MazeObject.MazeObjectType.MEDICINE)
                {
                    maze[playerY, playerX].type = MazeObject.MazeObjectType.HALL;
                    health += 5;
                    if (health > 100) { health = 100; }
                    UpdatePanel();
                }
            }
        }
        private void SpawnEnemy()
        {

            int posX = r.Next(width);
            int posY = r.Next(height);

            if (maze[posY, posX].type == MazeObject.MazeObjectType.HALL)
            {
                var enemy = new Enemy(posX, posY);
                enemies.Add(enemy);
            }
            else SpawnEnemy();
        }

        public void PlayerDamage()
        {
            int damage = r.Next(20, 26);
            health -= damage;

            if (health <= 0)
            {
                MessageBox.Show("Поражение - закончилось здоровье!");
                parent.Close();
            }
        }

        public void MoveEnemies()
        {
            foreach (var enemy in enemies)
            {
                enemy.Move(this);
            }
        }

        public void UpdatePanel()
        {
            parent.Text = "Собрано медалей: " + collectedMedals + "/" + totalMedals + " | Здоровье: " + health + "%" + " | Враги: " + enemies.Count;
        }
    }
}
