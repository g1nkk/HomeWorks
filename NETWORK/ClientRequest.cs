namespace NetworkProgram
{
    public class ClientRequest
    {
        public string Command { get; set; } = null!;  
        public ChatMessage ChatMessage { get; set; } = null!;
    }
}
