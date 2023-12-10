using System.Text;
using System.Text.RegularExpressions;

namespace App;

public class Helper
{
    public bool ContainsAttributes(String html)
    {
        string pattern = @"<(\w+\s+[^=>])*(\w+=[^>]+)+>";
        Regex regex = new Regex(pattern, RegexOptions.IgnoreCase);
        return regex.IsMatch(html);
    }

    public string EscapeHtml(string html, Dictionary<string, string>? map = null)
    {
        if (html is null || html.Length == 0)
        {
            return "";
        }

        Dictionary<string, string> htmlSpecSymbols = map ?? new()
        {
            { "&", "&amp;" },
            { "<", "&lt;" },
            { ">", "&gt;" }
        };
        foreach (var pair in htmlSpecSymbols)
        {
            html = html.Replace(pair.Key, pair.Value);
        }

        return html;
    }

    public string Ellipsis(string input, int len)
    {
        if (input is null || len < 3 || len > input.Length)
        {
            return "";
        }

        return input[..(len - 3)] + "...";
    }

    public string Finalize(string input)
    {
        char[] chars = { '!', '?', '.', ',' };
        int len = input.Length;
        return len > 0 && !chars.Contains(input[len - 1]) ? input + "." : input;
    }

    public string CombineUrl(params string[] parts)
    {
        if (parts == null || parts.Length == 0)
        {
            return "";
        }

        var result = new StringBuilder();
        bool wasNull = false;

        foreach (var part in parts.Where(p => p != null))
        {
            if (wasNull)
            {
                throw new ArgumentException("Non-Null argument after Null one");
            }

            if (part == "..")
            {
                continue;
            }

            var temp = "/" + part;

            if (parts.SkipWhile(p => p != part).Skip(1).FirstOrDefault() == "..")
            {
                continue;
            }

            result.Append(temp);
        }

        if (result.Length == 0)
        {
            return "";
        }

        return result.ToString();
    }
}