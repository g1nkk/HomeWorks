using System;
using System.IO;
using System.Text.Json;
using System.Windows;

namespace NetworkProgram;

// Способ получить значения из JSON-файла
public partial class App : Application
{
    private static readonly string configFilename = "email-settings.json";
    private static JsonElement? settings;
    private static readonly Random r = new();

    // В параметре указываем (например: "smtp:email")
    public static string? GetConfiguration(string name)
    {
        if (settings is null)
            if (!File.Exists(configFilename)) // существует ли файл json
            {
                MessageBox.Show($"Файл конфигурации '{configFilename}' не найденно...",
                    "Операция не может быть выполнена", MessageBoxButton.OK, MessageBoxImage.Error);
                return null;
            }

        // преобразовываем весь текст из json в тип JsonElement
        try
        {
            settings ??= JsonSerializer.Deserialize<JsonElement>(File.ReadAllText(configFilename));
        }
        catch
        {
            MessageBox.Show($"Файл конфигурации '{configFilename}' повреждён и не может быть прочитан...",
                "Операция не может быть выполнена", MessageBoxButton.OK, MessageBoxImage.Error);
            return null;
        }

        var jsonElement = settings;
        if (settings is not null)
        {
            try
            {
                foreach (var key in name.Split(':')) // цикл, потому что в json может быть вложенность ("smtp:host")
                    jsonElement = jsonElement?.GetProperty(key);
                
                return jsonElement?.ToString();

            }
            catch(Exception ex)
            {
                MessageBox.Show("Ошибка чтения: " + ex.Message);
            }

        }

        return null;
    }

    public static int GetRandomNumber(int from, int to)
    {
        return r.Next(from, to);
    }
}