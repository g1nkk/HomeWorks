import enums.Brand;
import exceptions.InactivityException;

import java.util.Arrays;
import java.util.Comparator;

// PlayRoom.java
public class PlayRoom {
    public static void main(String[] args) {
        // Создание массивов игр на физических носителях и виртуальных играх
        Game.GameDisk[] physicalGames = new Game.GameDisk[]{
                Game.getDisk("The Witcher 3", Game.Genre.ACTION, "An action role-playing game"),
                Game.getDisk("Forza Horizon 4", Game.Genre.RACE, "A racing game"),
                Game.getDisk("Uncharted 4", Game.Genre.ACTION, "An action-adventure game")
        };

        Game.VirtualGame[] virtualGames = new Game.VirtualGame[]{
                Game.getVirtualGame("Cyberpunk 2077", Game.Genre.ACTION, 4),
                Game.getVirtualGame("Rocket League", Game.Genre.RACE, 5),
                Game.getVirtualGame("NBA 2K21", Game.Genre.SPORT, 3),
                Game.getVirtualGame("Overwatch", Game.Genre.ACTION, 5)
        };

        // Сортировка физ. игр по жанру с использованием анонимного Comparator
        Arrays.sort(physicalGames, new Comparator<Game.GameDisk>() {
            @Override
            public int compare(Game.GameDisk o1, Game.GameDisk o2) {
                return o1.getData().getGenre().compareTo(o2.getData().getGenre());
            }
        });

        // Сортировка виртуальных игр по рейтингу с использованием анонимного Comparator
        Arrays.sort(virtualGames, new Comparator<Game.VirtualGame>() {
            @Override
            public int compare(Game.VirtualGame o1, Game.VirtualGame o2) {
                return Integer.compare(o1.getRating(), o2.getRating());
            }
        });

        // Вывод отсортированных игр
        System.out.println("Отсортированные физические игры по жанру:");
        for (Game.GameDisk gd : physicalGames) {
            System.out.println(gd);
        }

        System.out.println("\nОтсортированные виртуальные игры по рейтингу:");
        for (Game.VirtualGame vg : virtualGames) {
            System.out.println(vg);
        }

        // Создание экземпляра GameConsole
        GameConsole console = new GameConsole(Brand.MICROSOFT, "XBOX 360", "XC123QeWR");
        System.out.println("\nСозданная консоль: " + console);

        // Включение первого джойстика (это автоматически включает консоль)
        console.activateGamepad(1);

        // Загрузка и запуск игры
        Game.VirtualGame virtualGame = Game.getVirtualGame("Halo Infinite", Game.Genre.ACTION, 5);
        console.loadGame(virtualGame.getData());
        console.playGame();

        // Повторный запуск игры для демонстрации уменьшения заряда
        console.playGame();

        // Отключение джойстиков
        console.deactivateGamepad(1);
        console.deactivateGamepad(2); // Второй джойстик уже выключен

        // Попытка игры без активных джойстиков, чтобы увеличить счетчик ожидания
        try {
            for (int i = 0; i < 6; i++) {
                console.playGame();
            }
        } catch (InactivityException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

        // Вывод состояния консоли
        System.out.println("\nТекущее состояние консоли:");
        System.out.println(console);
    }
}
