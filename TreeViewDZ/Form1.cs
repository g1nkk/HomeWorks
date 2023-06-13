using System.Resources;

namespace TreeViewDZ
{
    public partial class Form1 : Form
    {
        public ImageList imagelist = new();

        public Form1()
        {
            InitializeComponent();

            ResourceManager resourceManager = new(typeof(Form1));
            var fileIcon = (Image)resourceManager.GetObject("fileIcon");
            var folderIcon = (Image)resourceManager.GetObject("folderIcon");

            imagelist.Images.Add(fileIcon);
            imagelist.Images.Add(folderIcon);
            treeView.ImageList = imagelist;
        }
        private void MainForm_Load(object sender, EventArgs e)
        {
            string rootPath = @"C:\1\";

            DirectoryInfo rootDirectory = new DirectoryInfo(rootPath);

            TreeNode rootNode = new TreeNode(rootDirectory.Name);
            rootNode.Tag = rootDirectory.FullName;
            treeView.Nodes.Add(rootNode);

            LoadDirectory(rootDirectory, rootNode);
        }

        private void LoadDirectory(DirectoryInfo directory, TreeNode parentNode)
        {
            try
            {
                // Проходим по каждой поддиректории и добавляем ее в TreeView
                foreach (DirectoryInfo subDirectory in directory.GetDirectories())
                {
                    // Новый узел для поддиректории
                    TreeNode directoryNode = new TreeNode(subDirectory.Name)
                    {
                        Tag = subDirectory.FullName,
                        ImageIndex = 0,
                        SelectedImageIndex = 0
                    };

                    // Рекурсивно загружаем поддиректории и файлы для текущей поддиректории
                    LoadDirectory(subDirectory, directoryNode);

                    // Добавляем узел поддиректории к родительскому узлу
                    parentNode.Nodes.Add(directoryNode);
                }

                // Проходим по каждому файлу в текущей директории и добавляем его в TreeView
                foreach (FileInfo file in directory.GetFiles())
                {
                    // Новый узел для файла
                    TreeNode fileNode = new TreeNode(file.Name)
                    {
                        Tag = file.FullName,
                        ImageIndex = 1,
                        SelectedImageIndex = 1
                    };

                    parentNode.Nodes.Add(fileNode);
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error occured: " + ex.Message);
            }
        }
    }
}