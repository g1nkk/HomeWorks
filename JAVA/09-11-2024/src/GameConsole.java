// GameConsole.java
public class GameConsole implements Powered {
    private final Brand brand;                  // Название производителя (final)
    private final String model;                 // Название модели (final)
    private final String serial;                // Серийный номер приставки (final)
    private final Gamepad firstGamepad;         // Первый джойстик (final)
    private final Gamepad secondGamepad;        // Второй джойстик (final)
    private boolean isOn;                       // Флаг состояния приставки

    private Game activeGame;           // Активная игра
    private int waitingCounter;        // Счетчик ожидания

    // Конструктор для GameConsole
    public GameConsole(Brand brand, String model, String serial) {
        this.brand = brand;
        this.model = model;
        this.serial = serial;
        this.firstGamepad = new Gamepad(brand, 1);
        this.secondGamepad = new Gamepad(brand, 2);
        this.isOn = false; // Приставка изначально выключена
    }

    // Геттеры для final полей
    public Brand getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getSerial() {
        return serial;
    }

    public Gamepad getFirstGamepad() {
        return firstGamepad;
    }

    public Gamepad getSecondGamepad() {
        return secondGamepad;
    }

    public boolean isOn() {
        return isOn;
    }

    // Сеттер для isOn
    public void setOn(boolean on) {
        isOn = on;
    }

    // Внутренний (нестатический) класс Gamepad
    public class Gamepad {
        private final Brand brand;                  // Название производителя (final)
        private final String consoleSerial;         // Серийный номер консоли (final)
        private final int connectedNumber;          // Порядковый номер джойстика (final)
        private final Color color;                  // Цвет джойстика (final)
        private double chargeLevel;                 // Заряд джойстика (%)
        private boolean isOn;                       // Флаг состояния джойстика

        // Конструктор для Gamepad
        public Gamepad(Brand brand, int connectedNumber) {
            this.brand = brand;
            this.consoleSerial = GameConsole.this.serial; // Присваиваем серийный номер консоли
            this.connectedNumber = connectedNumber;
            this.color = Color.BLACK;                      // Можно сделать параметром или по умолчанию
            this.chargeLevel = 100.0;                      // По умолчанию 100%
            this.isOn = false;                             // Джойстик изначально выключен
        }

        // Геттеры для final полей
        public Brand getBrand() {
            return brand;
        }

        public String getConsoleSerial() {
            return consoleSerial;
        }

        public int getConnectedNumber() {
            return connectedNumber;
        }

        public Color getColor() {
            return color;
        }

        // Геттеры и сеттеры для изменяемых полей
        public double getChargeLevel() {
            return chargeLevel;
        }

        public void setChargeLevel(double chargeLevel) {
            this.chargeLevel = chargeLevel;
        }

        public boolean isOn() {
            return isOn;
        }

        public void setOn(boolean on) {
            isOn = on;
        }

        @Override
        public String toString() {
            return "Gamepad{" +
                    "brand=" + brand +
                    ", consoleSerial='" + consoleSerial + '\'' +
                    ", connectedNumber=" + connectedNumber +
                    ", color=" + color +
                    ", chargeLevel=" + chargeLevel +
                    ", isOn=" + isOn +
                    '}';
        }
    }

    // Реализация методов интерфейса Powered
    @Override
    public void powerOn() {
        if (!isOn) {
            isOn = true;
            System.out.println("Консоль включена.");
        }
    }

    @Override
    public void powerOff() {
        if (isOn) {
            isOn = false;
            System.out.println("Консоль выключена.");
        }
    }

    // Метод для загрузки игры
    public void loadGame(Game game) {
        this.activeGame = game;
        System.out.println("Игра " + game.getName() + " загружается.");
    }

    // Метод для игры
    public void playGame() {
        if (activeGame == null) {
            System.out.println("Не загружена никакая игра.");
            return;
        }
        if (!isOn) {
            System.out.println("Консоль выключена. Включите консоль для игры.");
            return;
        }
        System.out.println("Играем в " + activeGame.getName());

        // Уменьшаем заряд активных джойстиков на 10%
        if (firstGamepad.isOn()) {
            double newCharge = firstGamepad.getChargeLevel() - 10.0;
            firstGamepad.setChargeLevel(newCharge);
            System.out.println("Заряд первого джойстика: " + firstGamepad.getChargeLevel() + "%");
            if (firstGamepad.getChargeLevel() <= 0) {
                firstGamepad.setOn(false);
                System.out.println("Первый джойстик разряжен и выключен.");
            }
        }

        if (secondGamepad.isOn()) {
            double newCharge = secondGamepad.getChargeLevel() - 10.0;
            secondGamepad.setChargeLevel(newCharge);
            System.out.println("Заряд второго джойстика: " + secondGamepad.getChargeLevel() + "%");
            if (secondGamepad.getChargeLevel() <= 0) {
                secondGamepad.setOn(false);
                System.out.println("Второй джойстик разряжен и выключен.");
            }
        }

        // Проверка состояния
        checkStatus();
    }

    // Приватный метод для проверки состояния
    private void checkStatus() {
        if (!firstGamepad.isOn() && !secondGamepad.isOn()) {
            waitingCounter++;
            System.out.println("Подключите джойстик. Счетчик ожидания: " + waitingCounter);
            if (waitingCounter > 5) {
                powerOff();
                throw new InactivityException("Приставка завершает работу из-за отсутствия активности.");
            }
        } else {
            waitingCounter = 0;
        }
    }

    // Метод для включения джойстика
    public void activateGamepad(int number) {
        if (number == 1) {
            firstGamepad.setOn(true);
            powerOn(); // Включение консоли при включении джойстика
            System.out.println("Первый джойстик включен.");
        } else if (number == 2) {
            secondGamepad.setOn(true);
            powerOn(); // Включение консоли при включении джойстика
            System.out.println("Второй джойстик включен.");
        } else {
            System.out.println("Некорректный номер джойстика.");
        }
    }

    // Метод для деактивации джойстика
    public void deactivateGamepad(int number) {
        if (number == 1) {
            firstGamepad.setOn(false);
            System.out.println("Первый джойстик выключен.");
            // Если первый джойстик выключен, можно сделать второй активным, если он включен
            if (!secondGamepad.isOn()) {
                System.out.println("Второй джойстик теперь активен.");
            }
        } else if (number == 2) {
            secondGamepad.setOn(false);
            System.out.println("Второй джойстик выключен.");
            // Если второй джойстик выключен, можно сделать первый активным, если он включен
            if (!firstGamepad.isOn()) {
                System.out.println("Первый джойстик теперь активен.");
            }
        } else {
            System.out.println("Некорректный номер джойстика.");
        }
    }

    // Метод для вывода информации о заряде джойстиков
    public void displayGamepadsCharge() {
        System.out.println("Заряд первого джойстика: " + firstGamepad.getChargeLevel() + "%");
        System.out.println("Заряд второго джойстика: " + secondGamepad.getChargeLevel() + "%");
    }

    @Override
    public String toString() {
        return "GameConsole{" +
                "brand=" + brand +
                ", model='" + model + '\'' +
                ", serial='" + serial + '\'' +
                ", firstGamepad=" + firstGamepad +
                ", secondGamepad=" + secondGamepad +
                ", isOn=" + isOn +
                ", activeGame=" + (activeGame != null ? activeGame.getName() : "None") +
                ", waitingCounter=" + waitingCounter +
                '}';
    }
}
