using System.Collections.Generic;

namespace NetworkProgram
{
    public class ServerResponse
    {
        public string Status { get; set; } = null!;
        public IEnumerable<ChatMessage>? Messages { get; set; } = null!;  // коллекция сообщений пользователей
    }
}
