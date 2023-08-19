USE [master]
GO
CREATE DATABASE [Product_2]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Product_2', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\Product_2.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Product_2_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\Product_2_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [Product_2] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Product_2].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Product_2] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Product_2] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Product_2] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Product_2] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Product_2] SET ARITHABORT OFF 
GO
ALTER DATABASE [Product_2] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Product_2] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Product_2] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Product_2] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Product_2] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Product_2] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Product_2] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Product_2] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Product_2] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Product_2] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Product_2] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Product_2] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Product_2] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Product_2] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Product_2] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Product_2] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Product_2] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Product_2] SET RECOVERY FULL 
GO
ALTER DATABASE [Product_2] SET  MULTI_USER 
GO
ALTER DATABASE [Product_2] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Product_2] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Product_2] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Product_2] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Product_2] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Product_2] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'Product_2', N'ON'
GO
ALTER DATABASE [Product_2] SET QUERY_STORE = ON
GO
ALTER DATABASE [Product_2] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [Product_2]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Address](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[street] [nvarchar](50) NULL,
	[id_city] [int] NULL,
 CONSTRAINT [PK_Address] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[City](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
	[id_region] [int] NULL,
 CONSTRAINT [PK_City] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Country](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
 CONSTRAINT [PK_Country] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Delivery](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[id_product] [int] NULL,
	[id_supplier] [int] NULL,
	[price] [int] NULL,
	[quantity] [int] NULL,
	[date_of_delivery] [date] NULL,
 CONSTRAINT [PK_Delivery] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Markup](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
	[percent] [int] NULL,
 CONSTRAINT [PK_Markup] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Measurement](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
	[description] [nvarchar](50) NULL,
 CONSTRAINT [PK_Measurement] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Producer](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
	[id_address] [int] NULL,
 CONSTRAINT [PK_Producer] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product_2](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
	[id_category] [int] NULL,
	[price] [int] NULL,
	[quantity] [int] NULL,
	[id_producer] [int] NULL,
	[id_measurment] [int] NULL,
	[id_markup] [int] NULL,
 CONSTRAINT [PK_Product_2] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Region](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
	[id_country] [int] NULL,
 CONSTRAINT [PK_Region] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Sale](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[id_product] [int] NULL,
	[price] [int] NULL,
	[quantity] [int] NULL,
	[date_of_sale] [date] NULL,
 CONSTRAINT [PK_Sale] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Supplier](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
	[id_address] [int] NULL,
 CONSTRAINT [PK_Supplier] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Address] ON 

INSERT [dbo].[Address] ([id], [street], [id_city]) VALUES (5, N'Ushakova', 6)
INSERT [dbo].[Address] ([id], [street], [id_city]) VALUES (6, N'Balukova', 7)
INSERT [dbo].[Address] ([id], [street], [id_city]) VALUES (7, N'Byrzi', 6)
INSERT [dbo].[Address] ([id], [street], [id_city]) VALUES (8, N'Odesskaya', 7)
INSERT [dbo].[Address] ([id], [street], [id_city]) VALUES (9, N'40_LET', 6)
SET IDENTITY_INSERT [dbo].[Address] OFF
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([id], [name]) VALUES (1, N'Milks')
INSERT [dbo].[Category] ([id], [name]) VALUES (2, N'Alcohol')
INSERT [dbo].[Category] ([id], [name]) VALUES (3, N'Meat')
INSERT [dbo].[Category] ([id], [name]) VALUES (4, N'Soda')
INSERT [dbo].[Category] ([id], [name]) VALUES (5, N'Bread')
SET IDENTITY_INSERT [dbo].[Category] OFF
GO
SET IDENTITY_INSERT [dbo].[City] ON 

INSERT [dbo].[City] ([id], [name], [id_region]) VALUES (6, N'Kherson', 2)
INSERT [dbo].[City] ([id], [name], [id_region]) VALUES (7, N'Kruykovshina', 3)
INSERT [dbo].[City] ([id], [name], [id_region]) VALUES (8, N'Kahovka', 2)
INSERT [dbo].[City] ([id], [name], [id_region]) VALUES (9, N'Vyshneve', 3)
INSERT [dbo].[City] ([id], [name], [id_region]) VALUES (10, N'Odessa', 4)
SET IDENTITY_INSERT [dbo].[City] OFF
GO
SET IDENTITY_INSERT [dbo].[Country] ON 

INSERT [dbo].[Country] ([id], [name]) VALUES (1, N'Ukraine')
INSERT [dbo].[Country] ([id], [name]) VALUES (2, N'Bolgaria')
INSERT [dbo].[Country] ([id], [name]) VALUES (3, N'Germany')
INSERT [dbo].[Country] ([id], [name]) VALUES (4, N'Poland')
INSERT [dbo].[Country] ([id], [name]) VALUES (5, N'Finland')
SET IDENTITY_INSERT [dbo].[Country] OFF
GO
SET IDENTITY_INSERT [dbo].[Delivery] ON 

INSERT [dbo].[Delivery] ([id], [id_product], [id_supplier], [price], [quantity], [date_of_delivery]) VALUES (1, 1, 1, 50, 215, CAST(N'2023-07-26' AS Date))
INSERT [dbo].[Delivery] ([id], [id_product], [id_supplier], [price], [quantity], [date_of_delivery]) VALUES (2, 2, 2, 70, 116, CAST(N'2023-07-21' AS Date))
INSERT [dbo].[Delivery] ([id], [id_product], [id_supplier], [price], [quantity], [date_of_delivery]) VALUES (4, 3, 4, 48, 88, CAST(N'2023-08-03' AS Date))
INSERT [dbo].[Delivery] ([id], [id_product], [id_supplier], [price], [quantity], [date_of_delivery]) VALUES (5, 4, 5, 31, 13, CAST(N'2023-07-19' AS Date))
INSERT [dbo].[Delivery] ([id], [id_product], [id_supplier], [price], [quantity], [date_of_delivery]) VALUES (6, 5, 6, 40, 44, CAST(N'2023-08-01' AS Date))
SET IDENTITY_INSERT [dbo].[Delivery] OFF
GO
SET IDENTITY_INSERT [dbo].[Markup] ON 

INSERT [dbo].[Markup] ([id], [name], [percent]) VALUES (1, N'First_dep', 20)
INSERT [dbo].[Markup] ([id], [name], [percent]) VALUES (2, N'Second_dep', 20)
INSERT [dbo].[Markup] ([id], [name], [percent]) VALUES (3, N'Third_dep', 20)
INSERT [dbo].[Markup] ([id], [name], [percent]) VALUES (4, N'Fourth_dep', 20)
INSERT [dbo].[Markup] ([id], [name], [percent]) VALUES (5, N'Five_dep', 20)
SET IDENTITY_INSERT [dbo].[Markup] OFF
GO
SET IDENTITY_INSERT [dbo].[Measurement] ON 

INSERT [dbo].[Measurement] ([id], [name], [description]) VALUES (1, N'First_meas', N'Packs')
INSERT [dbo].[Measurement] ([id], [name], [description]) VALUES (2, N'Second_meas', N'Bottles')
INSERT [dbo].[Measurement] ([id], [name], [description]) VALUES (3, N'Third_meas', N'Pieces')
INSERT [dbo].[Measurement] ([id], [name], [description]) VALUES (4, N'Fourth_meas', N'Boxes')
INSERT [dbo].[Measurement] ([id], [name], [description]) VALUES (5, N'Five_meas', N'Kg
')
SET IDENTITY_INSERT [dbo].[Measurement] OFF
GO
SET IDENTITY_INSERT [dbo].[Producer] ON 

INSERT [dbo].[Producer] ([id], [name], [id_address]) VALUES (1, N'Top_supplier', 5)
INSERT [dbo].[Producer] ([id], [name], [id_address]) VALUES (2, N'ATB', 6)
INSERT [dbo].[Producer] ([id], [name], [id_address]) VALUES (3, N'Silpo', 7)
INSERT [dbo].[Producer] ([id], [name], [id_address]) VALUES (4, N'Trash', 8)
INSERT [dbo].[Producer] ([id], [name], [id_address]) VALUES (5, N'Syvorov', 9)
SET IDENTITY_INSERT [dbo].[Producer] OFF
GO
SET IDENTITY_INSERT [dbo].[Product_2] ON 

INSERT [dbo].[Product_2] ([id], [name], [id_category], [price], [quantity], [id_producer], [id_measurment], [id_markup]) VALUES (1, N'Kolbasa', 3, 300, 215, 2, 3, 1)
INSERT [dbo].[Product_2] ([id], [name], [id_category], [price], [quantity], [id_producer], [id_measurment], [id_markup]) VALUES (2, N'Moloko Prost.', 1, 58, 116, 1, 2, 2)
INSERT [dbo].[Product_2] ([id], [name], [id_category], [price], [quantity], [id_producer], [id_measurment], [id_markup]) VALUES (3, N'Cheese Maliz', 1, 89, 88, 1, 3, 2)
INSERT [dbo].[Product_2] ([id], [name], [id_category], [price], [quantity], [id_producer], [id_measurment], [id_markup]) VALUES (4, N'Prosto Bread', 5, 24, 13, 4, 3, 3)
INSERT [dbo].[Product_2] ([id], [name], [id_category], [price], [quantity], [id_producer], [id_measurment], [id_markup]) VALUES (5, N'Vodka', 2, 156, 44, 3, 2, 4)
SET IDENTITY_INSERT [dbo].[Product_2] OFF
GO
SET IDENTITY_INSERT [dbo].[Region] ON 

INSERT [dbo].[Region] ([id], [name], [id_country]) VALUES (2, N'Kherson_obl', 1)
INSERT [dbo].[Region] ([id], [name], [id_country]) VALUES (3, N'Kiev_obl', 1)
INSERT [dbo].[Region] ([id], [name], [id_country]) VALUES (4, N'Odesska_obl', 1)
INSERT [dbo].[Region] ([id], [name], [id_country]) VALUES (5, N'Kharkov_obl', 1)
INSERT [dbo].[Region] ([id], [name], [id_country]) VALUES (6, N'Lvivska_obl', 1)
SET IDENTITY_INSERT [dbo].[Region] OFF
GO
SET IDENTITY_INSERT [dbo].[Sale] ON 

INSERT [dbo].[Sale] ([id], [id_product], [price], [quantity], [date_of_sale]) VALUES (1, 1, 240, 215, CAST(N'2023-08-04' AS Date))
INSERT [dbo].[Sale] ([id], [id_product], [price], [quantity], [date_of_sale]) VALUES (2, 2, 58, 116, NULL)
INSERT [dbo].[Sale] ([id], [id_product], [price], [quantity], [date_of_sale]) VALUES (3, 3, 71, 88, CAST(N'2023-07-29' AS Date))
INSERT [dbo].[Sale] ([id], [id_product], [price], [quantity], [date_of_sale]) VALUES (4, 4, 24, 13, NULL)
INSERT [dbo].[Sale] ([id], [id_product], [price], [quantity], [date_of_sale]) VALUES (5, 5, 99, 44, CAST(N'2023-09-13' AS Date))
SET IDENTITY_INSERT [dbo].[Sale] OFF
GO
SET IDENTITY_INSERT [dbo].[Supplier] ON 

INSERT [dbo].[Supplier] ([id], [name], [id_address]) VALUES (1, N'Perevozo4ka', 6)
INSERT [dbo].[Supplier] ([id], [name], [id_address]) VALUES (2, N'NovaPoshta', 5)
INSERT [dbo].[Supplier] ([id], [name], [id_address]) VALUES (4, N'UkrPoshta', 7)
INSERT [dbo].[Supplier] ([id], [name], [id_address]) VALUES (5, N'SilpoPerevoz', 5)
INSERT [dbo].[Supplier] ([id], [name], [id_address]) VALUES (6, N'ATBPerevoz', 8)
SET IDENTITY_INSERT [dbo].[Supplier] OFF
GO
ALTER TABLE [dbo].[Address]  WITH CHECK ADD  CONSTRAINT [FK_Address_City] FOREIGN KEY([id_city])
REFERENCES [dbo].[City] ([id])
GO
ALTER TABLE [dbo].[Address] CHECK CONSTRAINT [FK_Address_City]
GO
ALTER TABLE [dbo].[City]  WITH CHECK ADD  CONSTRAINT [FK_City_Region] FOREIGN KEY([id_region])
REFERENCES [dbo].[Region] ([id])
GO
ALTER TABLE [dbo].[City] CHECK CONSTRAINT [FK_City_Region]
GO
ALTER TABLE [dbo].[Delivery]  WITH CHECK ADD  CONSTRAINT [FK_Delivery_Product_2] FOREIGN KEY([id_product])
REFERENCES [dbo].[Product_2] ([id])
GO
ALTER TABLE [dbo].[Delivery] CHECK CONSTRAINT [FK_Delivery_Product_2]
GO
ALTER TABLE [dbo].[Delivery]  WITH CHECK ADD  CONSTRAINT [FK_Delivery_Supplier] FOREIGN KEY([id_supplier])
REFERENCES [dbo].[Supplier] ([id])
GO
ALTER TABLE [dbo].[Delivery] CHECK CONSTRAINT [FK_Delivery_Supplier]
GO
ALTER TABLE [dbo].[Producer]  WITH CHECK ADD  CONSTRAINT [FK_Producer_Address] FOREIGN KEY([id_address])
REFERENCES [dbo].[Address] ([id])
GO
ALTER TABLE [dbo].[Producer] CHECK CONSTRAINT [FK_Producer_Address]
GO
ALTER TABLE [dbo].[Product_2]  WITH CHECK ADD  CONSTRAINT [FK_Product_2_Category] FOREIGN KEY([id_category])
REFERENCES [dbo].[Category] ([id])
GO
ALTER TABLE [dbo].[Product_2] CHECK CONSTRAINT [FK_Product_2_Category]
GO
ALTER TABLE [dbo].[Product_2]  WITH CHECK ADD  CONSTRAINT [FK_Product_2_Markup] FOREIGN KEY([id_markup])
REFERENCES [dbo].[Markup] ([id])
GO
ALTER TABLE [dbo].[Product_2] CHECK CONSTRAINT [FK_Product_2_Markup]
GO
ALTER TABLE [dbo].[Product_2]  WITH CHECK ADD  CONSTRAINT [FK_Product_2_Measurement] FOREIGN KEY([id_measurment])
REFERENCES [dbo].[Measurement] ([id])
GO
ALTER TABLE [dbo].[Product_2] CHECK CONSTRAINT [FK_Product_2_Measurement]
GO
ALTER TABLE [dbo].[Product_2]  WITH CHECK ADD  CONSTRAINT [FK_Product_2_Producer] FOREIGN KEY([id_producer])
REFERENCES [dbo].[Producer] ([id])
GO
ALTER TABLE [dbo].[Product_2] CHECK CONSTRAINT [FK_Product_2_Producer]
GO
ALTER TABLE [dbo].[Region]  WITH CHECK ADD  CONSTRAINT [FK_Region_Country] FOREIGN KEY([id_country])
REFERENCES [dbo].[Country] ([id])
GO
ALTER TABLE [dbo].[Region] CHECK CONSTRAINT [FK_Region_Country]
GO
ALTER TABLE [dbo].[Sale]  WITH CHECK ADD  CONSTRAINT [FK_Sale_Product_2] FOREIGN KEY([id_product])
REFERENCES [dbo].[Product_2] ([id])
GO
ALTER TABLE [dbo].[Sale] CHECK CONSTRAINT [FK_Sale_Product_2]
GO
ALTER TABLE [dbo].[Supplier]  WITH CHECK ADD  CONSTRAINT [FK_Supplier_Address] FOREIGN KEY([id_address])
REFERENCES [dbo].[Address] ([id])
GO
ALTER TABLE [dbo].[Supplier] CHECK CONSTRAINT [FK_Supplier_Address]
GO
USE [master]
GO
ALTER DATABASE [Product_2] SET  READ_WRITE 
GO
