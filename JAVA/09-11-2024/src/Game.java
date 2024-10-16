public class Game {
    public enum Genre {ACTION, SPORT, RACE}

    private final String name;        // Название игры (final)
    private final Genre genre;        // Жанр игры (final)
    private final Type type;          // Тип носителя (final)

    public enum Type {VIRTUAL, PHYSICAL}

    // Приватный конструктор
    private Game(String name, Genre genre, Type type) {
        this.name = name;
        this.genre = genre;
        this.type = type;
    }

    // Геттеры для полей
    public String getName() {
        return name;
    }

    public Genre getGenre() {
        return genre;
    }

    public Type getType() {
        return type;
    }

    // Вложенный статический класс GameDisk
    public static class GameDisk {
        private final String description; // Описание игры (final)
        private final Game data;         // Данные об игре (final)

        // Приватный конструктор
        private GameDisk(String name, Genre genre, String description) {
            this.description = description;
            this.data = new Game(name, genre, Type.PHYSICAL);
        }

        // Геттеры
        public String getDescription() {
            return description;
        }

        public Game getData() {
            return data;
        }

        @Override
        public String toString() {
            return "GameDisk{" +
                    "description='" + description + '\'' +
                    ", data=" + data.getName() +
                    ", genre=" + data.getGenre() +
                    ", type=" + data.getType() +
                    '}';
        }
    }

    // Вложенный статический класс VirtualGame
    public static class VirtualGame {
        private final int rating;     // Рейтинг игры (final)
        private final Game data;      // Данные об игре (final)

        // Приватный конструктор
        private VirtualGame(String name, Genre genre, int rating) {
            this.rating = rating;
            this.data = new Game(name, genre, Type.VIRTUAL);
        }

        // Геттеры и сеттеры
        public int getRating() {
            return rating;
        }

        public Game getData() {
            return data;
        }

        @Override
        public String toString() {
            return "VirtualGame{" +
                    "rating=" + rating +
                    ", data=" + data.getName() +
                    ", genre=" + data.getGenre() +
                    ", type=" + data.getType() +
                    '}';
        }
    }

    // Статический метод для получения экземпляра GameDisk (Static Factory)
    public static GameDisk getDisk(String name, Genre genre, String description) {
        return new GameDisk(name, genre, description);
    }

    // Статический метод для получения экземпляра VirtualGame (Static Factory)
    public static VirtualGame getVirtualGame(String name, Genre genre, int rating) {
        return new VirtualGame(name, genre, rating);
    }

    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", genre=" + genre +
                ", type=" + type +
                '}';
    }
}
