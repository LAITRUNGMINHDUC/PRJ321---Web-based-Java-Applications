USE [master]
GO

/****** Object:  Database [JAVA]    Script Date: 2/13/2017 11:51:47 PM ******/
CREATE DATABASE [JAVA]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'JAVA', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL13.SQLEXPRESS\MSSQL\DATA\JAVA.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'JAVA_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL13.SQLEXPRESS\MSSQL\DATA\JAVA_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO

ALTER DATABASE [JAVA] SET COMPATIBILITY_LEVEL = 130
GO

IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [JAVA].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO

ALTER DATABASE [JAVA] SET ANSI_NULL_DEFAULT OFF 
GO

ALTER DATABASE [JAVA] SET ANSI_NULLS OFF 
GO

ALTER DATABASE [JAVA] SET ANSI_PADDING OFF 
GO

ALTER DATABASE [JAVA] SET ANSI_WARNINGS OFF 
GO

ALTER DATABASE [JAVA] SET ARITHABORT OFF 
GO

ALTER DATABASE [JAVA] SET AUTO_CLOSE OFF 
GO

ALTER DATABASE [JAVA] SET AUTO_SHRINK OFF 
GO

ALTER DATABASE [JAVA] SET AUTO_UPDATE_STATISTICS ON 
GO

ALTER DATABASE [JAVA] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO

ALTER DATABASE [JAVA] SET CURSOR_DEFAULT  GLOBAL 
GO

ALTER DATABASE [JAVA] SET CONCAT_NULL_YIELDS_NULL OFF 
GO

ALTER DATABASE [JAVA] SET NUMERIC_ROUNDABORT OFF 
GO

ALTER DATABASE [JAVA] SET QUOTED_IDENTIFIER OFF 
GO

ALTER DATABASE [JAVA] SET RECURSIVE_TRIGGERS OFF 
GO

ALTER DATABASE [JAVA] SET  DISABLE_BROKER 
GO

ALTER DATABASE [JAVA] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO

ALTER DATABASE [JAVA] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO

ALTER DATABASE [JAVA] SET TRUSTWORTHY OFF 
GO

ALTER DATABASE [JAVA] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO

ALTER DATABASE [JAVA] SET PARAMETERIZATION SIMPLE 
GO

ALTER DATABASE [JAVA] SET READ_COMMITTED_SNAPSHOT OFF 
GO

ALTER DATABASE [JAVA] SET HONOR_BROKER_PRIORITY OFF 
GO

ALTER DATABASE [JAVA] SET RECOVERY SIMPLE 
GO

ALTER DATABASE [JAVA] SET  MULTI_USER 
GO

ALTER DATABASE [JAVA] SET PAGE_VERIFY CHECKSUM  
GO

ALTER DATABASE [JAVA] SET DB_CHAINING OFF 
GO

ALTER DATABASE [JAVA] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO

ALTER DATABASE [JAVA] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO

ALTER DATABASE [JAVA] SET DELAYED_DURABILITY = DISABLED 
GO

ALTER DATABASE [JAVA] SET QUERY_STORE = OFF
GO

USE [JAVA]
GO

ALTER DATABASE SCOPED CONFIGURATION SET MAXDOP = 0;
GO

ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET MAXDOP = PRIMARY;
GO

ALTER DATABASE SCOPED CONFIGURATION SET LEGACY_CARDINALITY_ESTIMATION = OFF;
GO

ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET LEGACY_CARDINALITY_ESTIMATION = PRIMARY;
GO

ALTER DATABASE SCOPED CONFIGURATION SET PARAMETER_SNIFFING = ON;
GO

ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET PARAMETER_SNIFFING = PRIMARY;
GO

ALTER DATABASE SCOPED CONFIGURATION SET QUERY_OPTIMIZER_HOTFIXES = OFF;
GO

ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET QUERY_OPTIMIZER_HOTFIXES = PRIMARY;
GO

ALTER DATABASE [JAVA] SET  READ_WRITE 
GO
