using Microsoft.Data.SqlClient;
using System;
using System.Collections.ObjectModel;
using System.Windows;

namespace ADO_P12
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private SqlConnection connection;
        public ObservableCollection<String> columns { get; set; } = new();

        private string[] names = new[] { "Катя", "Соня", "Василиса", "Павел", "Денис", "Олег", "Наташа", "Ярослав" };

        public MainWindow()
        {
            InitializeComponent();
            connection = null!;
            this.DataContext = this;
        }

        public void InsertRandomPerson(object sender, RoutedEventArgs e)
        {
            string name = names[new Random().Next(names.Length)];

            using SqlCommand command = new();
            command.Connection = connection;
            command.CommandText =
                @"INSERT INTO table1
	                (name, email)
                    VALUES
                    (@Name, @Email)";

            command.Parameters.AddWithValue("@Name", name);
            command.Parameters.AddWithValue("@Email", name + "@gmail.com");

            try
            {
                command.ExecuteNonQuery();
                MessageBox.Show("New Random Name Was Inserted");
                UpdGroups();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Insert error",
                    MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }

        private void Window_Loaded(object sender, RoutedEventArgs e)
        {
            try
            {
                connection = new(App.ConnectionString);
                connection.Open();
                UpdGroups();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
                Close();
            }
        }

        private void UpdGroups()
        {
            using SqlCommand command = new();
            command.Connection = connection;
            command.CommandText = "SELECT * FROM table1";
            try
            {
                using SqlDataReader reader = command.ExecuteReader();
                columns.Clear();

                while (reader.Read()) // get result's one row
                {
                    columns.Add(
                        $"Id: {reader.GetGuid(0).ToString()[..4]}..., Name: {reader.GetString(1)}, Email: {reader.GetString(2)}"
                    );
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Query error",
                    MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }

        private void Window_Closed(object sender, EventArgs e)
        {
            connection?.Dispose();
        }

        private void CreateGroup_Click(object sender, RoutedEventArgs e)
        {
            using SqlCommand command = new();
            command.Connection = connection;
            command.CommandText =
                @"CREATE TABLE table1 (
	                id			UNIQUEIDENTIFIER NOT NULL PRIMARY KEY DEFAULT NEWID(),
	                name		NVARCHAR(50)     NULL,
	                email       NVARCHAR(50)     NULL
                )";
            try
            {
                command.ExecuteNonQuery();
                MessageBox.Show("Table Created");
                UpdGroups();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Create error",
                    MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }

        private void InsertGroup_Click(object sender, RoutedEventArgs e)
        {
            using SqlCommand command = new();
            command.Connection = connection;
            command.CommandText =
                @"INSERT INTO table1
	                ( id, name,	email )
                VALUES
                ( '089015F4-31B5-4F2B-BA05-A813B5419285', N'Денис',     N'denis@gmail.com'),
                ( 'A6D7858F-6B75-4C75-8A3D-C0B373828558', N'Дмитрий',   N'dima12231@mail.ru'),
                ( 'A6D7858F-6B75-4C75-8A3D-C0B373828552', N'Олег',  N'oleg@student.itstep.org'),
                ( 'A6D7858F-6B75-4C75-8A3D-C0B373828553', N'Татьяна', N'sdfsdfsfdsd@gmail.com'),
                ( 'A6D7858F-6B75-4C75-8A3D-C0B373828554', N'Ирина', N'123123123123@gmail.com')";
            try
            {
                command.ExecuteNonQuery();
                MessageBox.Show("Data inserted");
                UpdGroups();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Insertation error",
                    MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }

        private void GroupCount_Click(object sender, RoutedEventArgs e)
        {
            using SqlCommand command = new();
            command.Connection = connection;
            command.CommandText = "SELECT COUNT(*) FROM table1";
            try
            {
                int cnt = Convert.ToInt32(
                    command.ExecuteScalar());
                MessageBox.Show($"Table has {cnt} rows");
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Query error",
                    MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }
    }
}