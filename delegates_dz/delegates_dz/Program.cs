class Program
{
    delegate void MenuAction();


    static void Main(string[] args)
    {
        MenuAction[] menuActions = new MenuAction[]
        {
            NewGame,
            LoadGame,
            ShowRules,
            ShowAuthor,
            Exit
        };

        while (true)
        {
            Console.Clear();
            ShowMenu();
            int choice = GetUserChoice();

            if (choice >= 0 && choice < menuActions.Length)
            {
                MenuAction selectedAction = menuActions[choice];
                selectedAction.Invoke();
            }
            else
            {
                Console.WriteLine("Wrong number. Pick again.");
                Console.ReadLine();
            }
        }
    }

    static void ShowMenu()
    {
        Console.WriteLine("Menu:");
        Console.WriteLine("1. New game");
        Console.WriteLine("2. Load game");
        Console.WriteLine("3. Rules");
        Console.WriteLine("4. About");
        Console.WriteLine("0. Exit");
    }

    static int GetUserChoice()
    {
        Console.Write("Введите номер пункта меню: ");
        string input = Console.ReadLine();
        int choice;
        int.TryParse(input, out choice);
        return choice;
    }

    static void NewGame()
    {
        Console.WriteLine("New game");
    }

    static void LoadGame()
    {
        Console.WriteLine("Load game");
        Console.ReadLine();
    }

    static void ShowRules()
    {
        Console.WriteLine("Rules");
        Console.ReadLine();
    }

    static void ShowAuthor()
    {
        Console.WriteLine("About");
        Console.ReadLine();
    }

    static void Exit()
    {
        Console.WriteLine("Exit.");
        Environment.Exit(0);
    }
}