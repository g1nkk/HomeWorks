using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Maze
{
    public class Enemy
    {
        private enum directions {Left, Right, Up, Down}
        private directions Direction;
        private int posX;
        private int posY;

        public Enemy(int x, int y)
        {
            Direction = (directions)Labirint.r.Next(4);
            posX = x; posY = y;
        }

        public void Move(Labirint maze)
        {
            if (isNextTileClear(maze))
            {
                //update old pos
                maze.maze[posY, posX].type = MazeObject.MazeObjectType.HALL;
                maze.images[posY, posX].BackgroundImage = new MazeObject(MazeObject.MazeObjectType.HALL).texture; 

                switch (Direction)
                {
                    case directions.Left:
                        posX--;
                        break;
                    case directions.Right:
                        posX++;
                        break;
                    case directions.Up:
                        posY--;
                        break;
                    case directions.Down:
                        posY++;
                        break;
                }

                //update new pos
                playerOrMedalCheck(maze);
                maze.maze[posY, posX].type = MazeObject.MazeObjectType.ENEMY;
                maze.images[posY, posX].BackgroundImage = new MazeObject(MazeObject.MazeObjectType.ENEMY).texture;
            }
            else
            { 
                FindNewDirection(maze);
                Move(maze); 
            }
        }

        void playerOrMedalCheck(Labirint maze)
        {
            if (maze.maze[posY, posX].type == MazeObject.MazeObjectType.CHAR)
            {
                maze.PlayerDamage();
            }
            else if (maze.maze[posY, posX].type == MazeObject.MazeObjectType.MEDAL)
            {
                maze.totalMedals--;
            }
        }

        bool isNextTileClear(Labirint maze)
        {
            try
            {
                switch (Direction)
                {
                    case directions.Left:
                        if (maze.maze[posY, posX - 1].type != MazeObject.MazeObjectType.WALL
                            && maze.maze[posY, posX - 1].type != MazeObject.MazeObjectType.ENEMY)
                        {
                            return true;
                        }
                        else return false;
                    case directions.Right:
                        if (maze.maze[posY, posX + 1].type != MazeObject.MazeObjectType.WALL
                            && maze.maze[posY, posX + 1].type != MazeObject.MazeObjectType.ENEMY)
                        {
                            return true;
                        }
                        else return false;
                    case directions.Up:
                        if (maze.maze[posY - 1, posX].type != MazeObject.MazeObjectType.WALL
                            && maze.maze[posY - 1, posX].type != MazeObject.MazeObjectType.ENEMY)
                        {
                            return true;
                        }
                        else return false;
                    case directions.Down:
                        if (maze.maze[posY + 1, posX].type != MazeObject.MazeObjectType.WALL
                            && maze.maze[posY + 1, posX].type != MazeObject.MazeObjectType.ENEMY)
                        {
                            return true;
                        }
                        else return false;
                }
                return false;
            }
            catch { return false; }
        }

        private void FindNewDirection(Labirint maze)
        {
            var oldDirection = Direction;

            Direction = (directions)Labirint.r.Next(4);

            if(Direction == oldDirection)
            {
                FindNewDirection(maze);
            }
            else if(!isNextTileClear(maze))
            {
                FindNewDirection(maze);
            }
        }
    }
}
