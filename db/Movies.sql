USE [master]
GO

IF DB_ID('MOVIES') IS NULL BEGIN
  CREATE DATABASE [MOVIES]
    CONTAINMENT = NONE
    ON PRIMARY
    ( NAME = N'MOVIES'
    , FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\MOVIES.mdf'
    , SIZE = 8192KB
    , MAXSIZE = UNLIMITED
    , FILEGROWTH = 65536KB )
    LOG ON
    ( NAME = N'MOVIES_log'
    , FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\MOVIES_log.ldf'
    , SIZE = 8192KB
    , MAXSIZE = 2048GB
    , FILEGROWTH = 65536KB )
    WITH CATALOG_COLLATION = DATABASE_DEFAULT

  IF (FULLTEXTSERVICEPROPERTY('IsFullTextInstalled') = 1) BEGIN
    EXEC [MOVIES].[dbo].[sp_fulltext_database] @action = 'enable'
  END

  ALTER DATABASE [MOVIES] SET ANSI_NULL_DEFAULT OFF 
  ALTER DATABASE [MOVIES] SET ANSI_NULLS OFF 
  ALTER DATABASE [MOVIES] SET ANSI_PADDING OFF 
  ALTER DATABASE [MOVIES] SET ANSI_WARNINGS OFF 
  ALTER DATABASE [MOVIES] SET ARITHABORT OFF 
  ALTER DATABASE [MOVIES] SET AUTO_CLOSE OFF 
  ALTER DATABASE [MOVIES] SET AUTO_SHRINK OFF 
  ALTER DATABASE [MOVIES] SET AUTO_UPDATE_STATISTICS ON 
  ALTER DATABASE [MOVIES] SET CURSOR_CLOSE_ON_COMMIT OFF 
  ALTER DATABASE [MOVIES] SET CURSOR_DEFAULT  GLOBAL 
  ALTER DATABASE [MOVIES] SET CONCAT_NULL_YIELDS_NULL OFF 
  ALTER DATABASE [MOVIES] SET NUMERIC_ROUNDABORT OFF 
  ALTER DATABASE [MOVIES] SET QUOTED_IDENTIFIER OFF 
  ALTER DATABASE [MOVIES] SET RECURSIVE_TRIGGERS OFF 
  ALTER DATABASE [MOVIES] SET  DISABLE_BROKER 
  ALTER DATABASE [MOVIES] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
  ALTER DATABASE [MOVIES] SET DATE_CORRELATION_OPTIMIZATION OFF 
  ALTER DATABASE [MOVIES] SET TRUSTWORTHY OFF 
  ALTER DATABASE [MOVIES] SET ALLOW_SNAPSHOT_ISOLATION OFF 
  ALTER DATABASE [MOVIES] SET PARAMETERIZATION SIMPLE 
  ALTER DATABASE [MOVIES] SET READ_COMMITTED_SNAPSHOT OFF 
  ALTER DATABASE [MOVIES] SET HONOR_BROKER_PRIORITY OFF 
  ALTER DATABASE [MOVIES] SET RECOVERY FULL 
  ALTER DATABASE [MOVIES] SET MULTI_USER 
  ALTER DATABASE [MOVIES] SET PAGE_VERIFY CHECKSUM  
  ALTER DATABASE [MOVIES] SET DB_CHAINING OFF 
  ALTER DATABASE [MOVIES] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
  ALTER DATABASE [MOVIES] SET TARGET_RECOVERY_TIME = 60 SECONDS 
  ALTER DATABASE [MOVIES] SET DELAYED_DURABILITY = DISABLED 
  ALTER DATABASE [MOVIES] SET ACCELERATED_DATABASE_RECOVERY = OFF  
  ALTER DATABASE [MOVIES] SET QUERY_STORE = OFF
  ALTER DATABASE [MOVIES] SET READ_WRITE 
END

USE [MOVIES]
GO

/****************************************************
name = [dbo].[Users]
db_type = TABLE
identifiable = 1
manageable = 1

bl_fields = Username :: nvarchar(50)
          , PasswordHash :: nvarchar(512)
          , IsAdmin :: bit
****************************************************/
IF OBJECT_ID(N'MOVIES.dbo.Users', N'U') IS NULL BEGIN
  CREATE TABLE [dbo].[Users]
    ( [Id]         int              NOT NULL IDENTITY (1, 1)
    , [Guid]       uniqueidentifier NOT NULL 
        CONSTRAINT [DF_User_Guid]        DEFAULT NEWSEQUENTIALID()
    , [CreateDate] datetime         NOT NULL
        CONSTRAINT [DF_Users_CreateDate] DEFAULT GETDATE()
    , [CreatedBy]  int              NOT NULL
    , [UpdateDate] datetime         NOT NULL
        CONSTRAINT [DF_Users_UpdateDate] DEFAULT GETDATE()
    , [UpdatedBy]  int              NOT NULL
    , [DeleteDate] datetime         NULL
    , [DeletedBy]  int              NULL
    
    , [Username]     nvarchar(50)  NOT NULL
    , [PasswordHash] nvarchar(512) NOT NULL
    , [IsAdmin]      bit           NOT NULL
        CONSTRAINT [DF_Users_IsAdmin] DEFAULT 0
     
    , CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED ([Id] ASC)
    
    , CONSTRAINT [FK_Users_CreatedBy] FOREIGN KEY ([CreatedBy]) REFERENCES [dbo].[Users] ([Id])
    , CONSTRAINT [FK_Users_UpdatedBy] FOREIGN KEY ([UpdatedBy]) REFERENCES [dbo].[Users] ([Id])
    , CONSTRAINT [FK_Users_DeletedBy] FOREIGN KEY ([DeletedBy]) REFERENCES [dbo].[Users] ([Id]) )

  CREATE UNIQUE NONCLUSTERED INDEX [IX_Users_Guid]     ON [dbo].[Users] ([Guid] ASC)
  CREATE UNIQUE NONCLUSTERED INDEX [IX_Users_Username] ON [dbo].[Users] ([Username] ASC)
END
GO

/****************************************************
name = [dbo].[UserCreate]
db_type = STORED PROCEDURE
sp_type = CREATE

params = @Username
       , @PasswordHash
       , @IsAdmin
       , @CreatedBy (int identifier of CREATE initiator)

return_value = if return_code != INTERNAL_ERROR USER else ()
return_code = INTERNAL_ERROR (-1)  
            | SUCCESS (1)
            | UNIQUE_VIOLATION (2)
            | RECREATED (3)
****************************************************/
CREATE OR ALTER PROCEDURE [dbo].[UserCreate] ( @Username     AS nvarchar(50)
                                             , @PasswordHash AS nvarchar(512)
                                             , @IsAdmin      AS bit
                                             , @CreatedBy    AS int = 1 )
AS BEGIN
  DECLARE @Guid       AS uniqueidentifier
  DECLARE @DeleteDate AS datetime
  SELECT ALL TOP 1
      @Guid       = [Guid]
    , @DeleteDate = [DeleteDate]
  FROM [dbo].[Users]
  WHERE [Username] = @Username

  IF @Guid IS NULL BEGIN
    INSERT INTO [dbo].[Users]
    ( [CreatedBy]
    , [UpdatedBy]
    , [Username]
    , [PasswordHash]
    , [IsAdmin] )
    VALUES
    ( @CreatedBy
    , @CreatedBy
    , @Username
    , @PasswordHash
    , @IsAdmin )

    DECLARE @Id AS int = SCOPE_IDENTITY()
    SELECT ALL
        [Id]
      , [Guid]
      , [CreateDate]
      , [CreatedBy]
      , [UpdateDate]
      , [UpdatedBy]
      , [DeleteDate]
      , [DeletedBy]
      , [Username]
      , [PasswordHash]
      , [IsAdmin]
    FROM [dbo].[Users]
    WHERE [Id] = @Id

    RETURN 1
  END
  ELSE IF @Guid       IS NOT NULL
      AND @DeleteDate IS NOT NULL BEGIN
    UPDATE [dbo].[Users]
    SET [DeleteDate]   = NULL
      , [DeletedBy]    = NULL
      , [UpdateDate]   = GETDATE()
      , [UpdatedBy]    = @CreatedBy
      , [Username]     = @Username
      , [PasswordHash] = @PasswordHash
      , [IsAdmin]      = @IsAdmin
    WHERE [Guid] = @Guid

    SELECT ALL
        [Id]
      , [Guid]
      , [CreateDate]
      , [CreatedBy]
      , [UpdateDate]
      , [UpdatedBy]
      , [DeleteDate]
      , [DeletedBy]
      , [Username]
      , [PasswordHash]
      , [IsAdmin]
    FROM [dbo].[Users]
    WHERE [Guid] = @Guid

    RETURN 3
  END
  ELSE IF @Guid       IS NOT NULL
      AND @DeleteDate IS NULL BEGIN
    UPDATE [dbo].[Users]
    SET [UpdateDate] = GETDATE()
      , [UpdatedBy]  = @CreatedBy
    WHERE [Guid] = @Guid

    SELECT ALL
        [Id]
      , [Guid]
      , [CreateDate]
      , [CreatedBy]
      , [UpdateDate]
      , [UpdatedBy]
      , [DeleteDate]
      , [DeletedBy]
      , [Username]
      , [PasswordHash]
      , [IsAdmin]
    FROM [dbo].[Users]
    WHERE [Guid] = @Guid

    RETURN 2
  END
  ELSE BEGIN
    RETURN -1
  END
END
GO

/****************************************************
name = [dbo].[UsersDelete]
db_type = STORED PROCEDURE
sp_type = DELETE

params = @Guid (uniqueidentifier of USER to DELETE)
       , @DeletedBy (int identifier of SOFT DELETE initiator)

return_value = VOID
return_code = INTERNAL_ERROR (-1)  
            | SUCCESS (1)
            | NOT_EXISTS (2)
            | ALREADY_DELETED (3)
****************************************************/
CREATE OR ALTER PROCEDURE [dbo].[UserDelete] ( @Guid      AS uniqueidentifier
                                             , @DeletedBy AS int = 1 )
AS BEGIN
  DECLARE @Id         AS int
  DECLARE @DeleteDate AS datetime
  SELECT ALL TOP 1
      @Id         = [Id]
    , @DeleteDate = [DeleteDate]
  FROM [dbo].[Users]
  WHERE [Guid] = @Guid

  IF @Id IS NULL BEGIN
    RETURN 2
  END
  ELSE IF @Id IS NOT NULL 
      AND @DeleteDate IS NOT NULL BEGIN
    RETURN 3
  END

  UPDATE [dbo].[Users]
  SET [DeletedBy]  = @DeletedBy
    , [DeleteDate] = GETDATE()
  WHERE [Guid] = @Guid

  IF @@ROWCOUNT = 1 BEGIN
    RETURN 1
  END
  ELSE BEGIN
    RETURN -1
  END
END
GO

/****************************************************
name = [dbo].[UserRead]
db_type = STORED PROCEDURE
sp_type = READ

params = @Method = ALL (1)  
                 | ALL_AVAILABLE (2)
                 | ONE (3)
                 | ONE_AVAILABLE (4)
       , @Id (int identifier of USER case @Method ONE | ONE_AVAILABLE)

return_value = () | USER | USER_SET
return_code = VOID
****************************************************/
CREATE OR ALTER PROCEDURE [dbo].[UserRead] ( @Method AS int
                                           , @Id     AS int = NULL )
AS BEGIN
  IF @Method = 1 BEGIN
    SELECT ALL
        [Id]
      , [Guid]
      , [CreateDate]
      , [CreatedBy]
      , [UpdateDate]
      , [UpdatedBy]
      , [DeleteDate]
      , [DeletedBy]
      , [Username]
      , [PasswordHash]
      , [IsAdmin]
    FROM [dbo].[Users]
    WHERE [Id] <> 1
  END
  ELSE IF @Method = 2 BEGIN
    SELECT ALL
        [Id]
      , [Guid]
      , [CreateDate]
      , [CreatedBy]
      , [UpdateDate]
      , [UpdatedBy]
      , [DeleteDate]
      , [DeletedBy]
      , [Username]
      , [PasswordHash]
      , [IsAdmin]
    FROM [dbo].[Users]
    WHERE [DeleteDate] IS NULL
      AND [Id] <> 1
  END
  ELSE IF @Method = 3 BEGIN
    SELECT ALL
        [Id]
      , [Guid]
      , [CreateDate]
      , [CreatedBy]
      , [UpdateDate]
      , [UpdatedBy]
      , [DeleteDate]
      , [DeletedBy]
      , [Username]
      , [PasswordHash]
      , [IsAdmin]
    FROM [dbo].[Users]
    WHERE [Id] = @Id
      AND [Id] <> 1
  END
  ELSE IF @Method = 4 BEGIN
    SELECT ALL
        [Id]
      , [Guid]
      , [CreateDate]
      , [CreatedBy]
      , [UpdateDate]
      , [UpdatedBy]
      , [DeleteDate]
      , [DeletedBy]
      , [Username]
      , [PasswordHash]
      , [IsAdmin]
    FROM [dbo].[Users]
    WHERE [DeleteDate] IS NULL 
      AND [Id] = @Id
      AND [Id] <> 1
  END
END
GO

/****************************************************
name = [dbo].[UserUpdate]
db_type = STORED PROCEDURE
sp_type = UPDATE

params = @Guid
       , @Username
       , @PasswordHash
       , @IsAdmin
       , @UpdatedBy (int identifier of UPDATE initiator)

return_value = VOID
return_code = INTERNAL_ERROR (-1)  
            | SUCCESS (1)
            | NOT_EXISTS (2)
            | DELETED (3)
            | UNIQUE_VIOLATION (4)
****************************************************/
CREATE OR ALTER PROCEDURE [dbo].[UserUpdate] ( @Guid         AS uniqueidentifier
                                             , @Username     AS nvarchar(50)
                                             , @PasswordHash AS nvarchar(512)
                                             , @IsAdmin      AS bit
                                             , @UpdatedBy    AS int = 1 )
AS BEGIN
  DECLARE @Id         AS int
  DECLARE @DeleteDate AS datetime
  SELECT ALL TOP 1
      @Id         = [Id]
    , @DeleteDate = [DeleteDate]
  FROM [dbo].[Users]
  WHERE [Guid] = @Guid

  IF @Id IS NULL BEGIN
    RETURN 2
  END
  ELSE IF @Id IS NOT NULL 
      AND @DeleteDate IS NOT NULL BEGIN
    RETURN 3
  END

  SET @Id         = NULL
  SET @DeleteDate = NULL

  SELECT ALL TOP 1
    @Id         = [Id],
    @DeleteDate = [DeleteDate]
  FROM [dbo].[Users]
  WHERE [Username] = @Username
    AND [Guid] <> @Guid

  IF  @Id IS NOT NULL 
  AND @DeleteDate IS NULL BEGIN
    RETURN 4
  END
  ELSE IF @Id IS NOT NULL 
      AND @DeleteDate IS NOT NULL BEGIN
    RETURN 3
  END

  UPDATE [dbo].[Users]
  SET [UpdatedBy]    = @UpdatedBy
    , [UpdateDate]   = GETDATE()
    , [Username]     = @Username
    , [PasswordHash] = @PasswordHash
    , [IsAdmin]      = @IsAdmin
  WHERE [Guid] = @Guid

  IF @@ROWCOUNT = 1 BEGIN
    RETURN 1
  END
  ELSE BEGIN
    RETURN -1
  END
END
GO

/****************************************************
name = [dbo].[UserRegister]
db_type = STORED PROCEDURE
sp_type = CREATE

params = @Username
       , @Password (plain text password to hash)
       , @CreatedBy (int identifier of CREATE initiator)

return_value = if return_code == SUCCESS then USER else ()
return_code = INTERNAL_ERROR (-1)  
            | SUCCESS (1)
            | UNIQUE_VIOLATION (2)
            | RECREATED (3)
****************************************************/
CREATE OR ALTER PROCEDURE [dbo].[UserRegister] ( @Username  AS nvarchar(50)
                                               , @Password  AS nvarchar(512)
                                               , @CreatedBy AS int = 1 )
AS BEGIN
  DECLARE @Guid       AS uniqueidentifier
  DECLARE @DeleteDate AS datetime
  SELECT ALL TOP 1
      @Guid       = [Guid]
    , @DeleteDate = [DeleteDate]
  FROM [dbo].[Users]
  WHERE [Username] = @Username

  IF @Guid IS NULL BEGIN
    DECLARE @PasswordHash AS nvarchar(512) = CONVERT(nvarchar(512), HASHBYTES('SHA2_512', @Password), 2)

    INSERT INTO [dbo].[Users]
    ( [CreatedBy]
    , [UpdatedBy]
    , [Username]
    , [PasswordHash] )
    VALUES
    ( @CreatedBy
    , @CreatedBy
    , @Username
    , @PasswordHash )

    DECLARE @Id AS int = SCOPE_IDENTITY()
    SELECT ALL
        [Id]
      , [Guid]
      , [CreateDate]
      , [CreatedBy]
      , [UpdateDate]
      , [UpdatedBy]
      , [DeleteDate]
      , [DeletedBy]
      , [Username]
      , [PasswordHash]
      , [IsAdmin]
    FROM [dbo].[Users]
    WHERE [Id] = @Id

    RETURN 1
  END
  ELSE IF @Guid       IS NOT NULL
      AND @DeleteDate IS NOT NULL BEGIN
    RETURN 3
  END
  ELSE IF @Guid       IS NOT NULL
      AND @DeleteDate IS NULL BEGIN
    RETURN 2
  END
  ELSE BEGIN
    RETURN -1
  END
END
GO

/****************************************************
name = [dbo].[UserLogin]
db_type = STORED PROCEDURE
sp_type = READ

params = @Username
       , @Password (plain text password)

return_value = () | USER
return_code = VOID
****************************************************/
CREATE OR ALTER PROCEDURE [dbo].[UserLogin] ( @Username AS nvarchar(100)
                                            , @Password AS nvarchar(512) )
AS BEGIN
  DECLARE @PasswordHash AS nvarchar(512) = CONVERT(nvarchar(512), HASHBYTES('SHA2_512', @Password), 2)

  SELECT ALL TOP 1
      [Id]
    , [Guid]
    , [CreateDate]
    , [CreatedBy]
    , [UpdateDate]
    , [UpdatedBy]
    , [DeleteDate]
    , [DeletedBy]
    , [Username]
    , [PasswordHash]
    , [IsAdmin]
  FROM [dbo].[Users]
  WHERE [DeleteDate] IS NULL 
    AND [Username] = @Username
    AND [PasswordHash] = @PasswordHash
    AND [Id] <> 1
END
GO

/****************************************************
name = usermanager
db_type = USER RECORD
usage = default USER for managing USER_SET
****************************************************/
IF NOT EXISTS (SELECT ALL * FROM [dbo].[Users] WHERE [Username] = N'usermanager') BEGIN
  ALTER TABLE [Users] NOCHECK CONSTRAINT [FK_Users_CreatedBy]
  ALTER TABLE [Users] NOCHECK CONSTRAINT [FK_Users_UpdatedBy]
  ALTER TABLE [Users] NOCHECK CONSTRAINT [FK_Users_DeletedBy]

  DECLARE @Password AS nvarchar(512) = N'513e04f4e8e7da39578d165a06eb8129f80a2d2674acf598b12ef247fa445badc7b04806deee6e0cfd913d19227c4ba84713ba165be0458425bff06c053f3a35'

  INSERT INTO [dbo].[Users] 
  ( [CreatedBy]
  , [UpdatedBy]
  , [Username]
  , [PasswordHash]
  , [IsAdmin] )
  VALUES 
  ( 1
  , 1
  , N'usermanager'
  , CONVERT(nvarchar(512), HASHBYTES('SHA2_512', @Password), 2)
  , 1 )

  ALTER TABLE [Users] CHECK CONSTRAINT [FK_Users_CreatedBy]
  ALTER TABLE [Users] CHECK CONSTRAINT [FK_Users_UpdatedBy]
  ALTER TABLE [Users] CHECK CONSTRAINT [FK_Users_DeletedBy]
END
GO

/****************************************************
name = [dbo].[Movies]
db_type = TABLE
identifiable = 1
manageable = 1

bl_fields = Title :: nvarchar(256)
          , OriginalTitle :: nvarchar(256)
          , PublishedDate :: datetime
          , ShowingDate :: datetime
          , DurationMinutes :: int
          , Description :: nvarchar(2056)
          , WebPath :: nvarchar(512)
          , ImagePath :: nvarchar(512)
****************************************************/
IF OBJECT_ID(N'MOVIES.dbo.Movies', N'U') IS NULL BEGIN
  CREATE TABLE [dbo].[Movies]
    ( [Id]         int              NOT NULL IDENTITY (1, 1)
    , [Guid]       uniqueidentifier NOT NULL 
        CONSTRAINT [DF_User_Guid]        DEFAULT NEWSEQUENTIALID()
    , [CreateDate] datetime         NOT NULL
        CONSTRAINT [DF_Users_CreateDate] DEFAULT GETDATE()
    , [CreatedBy]  int              NOT NULL
    , [UpdateDate] datetime         NOT NULL
        CONSTRAINT [DF_Users_UpdateDate] DEFAULT GETDATE()
    , [UpdatedBy]  int              NOT NULL
    , [DeleteDate] datetime         NULL
    , [DeletedBy]  int              NULL
    
    , [Title]           nvarchar(256)  NOT NULL
    , [OriginalTitle]   nvarchar(256)  NOT NULL
    , [PublishedDate]   datetime       NOT NULL
    , [ShowingDate]     datetime       NOT NULL
    , [DurationMinutes] int            NOT NULL
    , [Description]     nvarchar(2056) NULL
    , [WebPath]         nvarchar(512)  NOT NULL
    , [ImagePath]       nvarchar(512)  NOT NULL
     
    , CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED ([Id] ASC)
    
    , CONSTRAINT [FK_Users_CreatedBy] FOREIGN KEY ([CreatedBy]) REFERENCES [dbo].[Users] ([Id])
    , CONSTRAINT [FK_Users_UpdatedBy] FOREIGN KEY ([UpdatedBy]) REFERENCES [dbo].[Users] ([Id])
    , CONSTRAINT [FK_Users_DeletedBy] FOREIGN KEY ([DeletedBy]) REFERENCES [dbo].[Users] ([Id]) )

  CREATE UNIQUE NONCLUSTERED INDEX [IX_Movies_Guid] ON [dbo].[Movies] ([Guid] ASC)
  CREATE UNIQUE NONCLUSTERED INDEX [IX_Movies_OriginalTitleAndPublishedDate] 
    ON [dbo].[Movies] ( [OriginalTitle] ASC
                      , [PublishedDate] ASC )
END
GO

/****************************************************
name = [dbo].[MovieCreate]
db_type = STORED PROCEDURE
sp_type = CREATE

params = @Title
       , @OriginalTitle
       , @PublishedDate
       , @ShowingDate
       , @DurationMinutes
       , @Description
       , @WebPath
       , @ImagePath
       , @CreatedBy (int identifier of CREATE initiator)

return_value = if return_code != INTERNAL_ERROR MOVIE else ()
return_code = INTERNAL_ERROR (-1)  
            | SUCCESS (1)
            | UNIQUE_VIOLATION (2)
            | RECREATED (3)
****************************************************/
CREATE OR ALTER PROCEDURE [dbo].[MovieCreate] ( @Title           AS nvarchar(256) 
                                              , @OriginalTitle   AS nvarchar(256) 
                                              , @PublishedDate   AS datetime      
                                              , @ShowingDate     AS datetime      
                                              , @DurationMinutes AS int           
                                              , @Description     AS nvarchar(2056)
                                              , @WebPath         AS nvarchar(512) 
                                              , @ImagePath       AS nvarchar(512)
                                              , @CreatedBy       AS int = 1 )
AS BEGIN
  DECLARE @Guid       AS uniqueidentifier
  DECLARE @DeleteDate AS datetime
  SELECT ALL TOP 1
      @Guid       = [Guid]
    , @DeleteDate = [DeleteDate]
  FROM [dbo].[Movies]
  WHERE [OriginalTitle] = @OriginalTitle
    AND [PublishedDate] = @PublishedDate

  IF @Guid IS NULL BEGIN
    INSERT INTO [dbo].[Movies]
    ( [CreatedBy]
    , [UpdatedBy]
    , [Title]
    , [OriginalTitle]
    , [PublishedDate]
    , [ShowingDate]
    , [DurationMinutes]
    , [Description]
    , [WebPath]
    , [ImagePath] )
    VALUES
    ( @CreatedBy
    , @CreatedBy
    , @Title
    , @OriginalTitle
    , @PublishedDate
    , @ShowingDate
    , @DurationMinutes
    , @Description
    , @WebPath
    , @ImagePath )

    DECLARE @Id AS int = SCOPE_IDENTITY()
    SELECT ALL
        [Id]
      , [Guid]
      , [CreateDate]
      , [CreatedBy]
      , [UpdateDate]
      , [UpdatedBy]
      , [DeleteDate]
      , [DeletedBy]
      , [Title]
      , [OriginalTitle]
      , [PublishedDate]
      , [ShowingDate]
      , [DurationMinutes]
      , [Description]
      , [WebPath]
      , [ImagePath]
    FROM [dbo].[Movies]
    WHERE [Id] = @Id

    RETURN 1
  END
  ELSE IF @Guid       IS NOT NULL
      AND @DeleteDate IS NOT NULL BEGIN
    UPDATE [dbo].[Movies]
    SET [DeleteDate]      = NULL
      , [DeletedBy]       = NULL
      , [UpdateDate]      = GETDATE()
      , [UpdatedBy]       = @CreatedBy
      , [Title]           = @Title
      , [OriginalTitle]   = @OriginalTitle
      , [PublishedDate]   = @PublishedDate
      , [ShowingDate]     = @ShowingDate
      , [DurationMinutes] = @DurationMinutes
      , [Description]     = @Description
      , [WebPath]         = @WebPath
      , [ImagePath]       = @ImagePath
    WHERE [Guid] = @Guid

    SELECT ALL
        [Id]
      , [Guid]
      , [CreateDate]
      , [CreatedBy]
      , [UpdateDate]
      , [UpdatedBy]
      , [DeleteDate]
      , [DeletedBy]
      , [Title]
      , [OriginalTitle]
      , [PublishedDate]
      , [ShowingDate]
      , [DurationMinutes]
      , [Description]
      , [WebPath]
      , [ImagePath]
    FROM [dbo].[Movies]
    WHERE [Id] = @Id

    RETURN 3
  END
  ELSE IF @Guid       IS NOT NULL
      AND @DeleteDate IS NULL BEGIN
    UPDATE [dbo].[Movies]
    SET [UpdateDate] = GETDATE()
      , [UpdatedBy]  = @CreatedBy
    WHERE [Guid] = @Guid

    SELECT ALL
        [Id]
      , [Guid]
      , [CreateDate]
      , [CreatedBy]
      , [UpdateDate]
      , [UpdatedBy]
      , [DeleteDate]
      , [DeletedBy]
      , [Title]
      , [OriginalTitle]
      , [PublishedDate]
      , [ShowingDate]
      , [DurationMinutes]
      , [Description]
      , [WebPath]
      , [ImagePath]
    FROM [dbo].[Movies]
    WHERE [Id] = @Id

    RETURN 2
  END
  ELSE BEGIN
    RETURN -1
  END
END
GO

/****************************************************
name = [dbo].[MovieDelete]
db_type = STORED PROCEDURE
sp_type = DELETE

params = @Guid (uniqueidentifier of MOVIE to DELETE)
       , @DeletedBy (int identifier of SOFT DELETE initiator)

return_value = VOID
return_code = INTERNAL_ERROR (-1)  
            | SUCCESS (1)
            | NOT_EXISTS (2)
            | ALREADY_DELETED (3)
****************************************************/
CREATE OR ALTER PROCEDURE [dbo].[MovieDelete] ( @Guid      AS uniqueidentifier
                                              , @DeletedBy AS int = 1 )
AS BEGIN
  DECLARE @Id         AS int
  DECLARE @DeleteDate AS datetime
  SELECT ALL TOP 1
      @Id         = [Id]
    , @DeleteDate = [DeleteDate]
  FROM [dbo].[Movies]
  WHERE [Guid] = @Guid

  IF @Id IS NULL BEGIN
    RETURN 2
  END
  ELSE IF @Id IS NOT NULL 
      AND @DeleteDate IS NOT NULL BEGIN
    RETURN 3
  END

  UPDATE [dbo].[Movies]
  SET [DeletedBy]  = @DeletedBy
    , [DeleteDate] = GETDATE()
  WHERE [Guid] = @Guid

  IF @@ROWCOUNT = 1 BEGIN
    RETURN 1
  END
  ELSE BEGIN
    RETURN -1
  END
END
GO

/****************************************************
name = [dbo].[MovieRead]
db_type = STORED PROCEDURE
sp_type = READ

params = @Method = ALL (1)  
                 | ALL_AVAILABLE (2)
                 | ONE (3)
                 | ONE_AVAILABLE (4)
       , @Id (int identifier of MOVIE case @Method ONE | ONE_AVAILABLE)

return_value = () | MOVIE | MOVIE_SET
return_code = VOID
****************************************************/
CREATE OR ALTER PROCEDURE [dbo].[MovieRead] ( @Method AS int
                                            , @Id     AS int = NULL )
AS BEGIN
  IF @Method = 1 BEGIN
    SELECT ALL
        [Id]
      , [Guid]
      , [CreateDate]
      , [CreatedBy]
      , [UpdateDate]
      , [UpdatedBy]
      , [DeleteDate]
      , [DeletedBy]
      , [Title]
      , [OriginalTitle]
      , [PublishedDate]
      , [ShowingDate]
      , [DurationMinutes]
      , [Description]
      , [WebPath]
      , [ImagePath]
    FROM [dbo].[Movies]
    WHERE [Id] <> 1
  END
  ELSE IF @Method = 2 BEGIN
    SELECT ALL
        [Id]
      , [Guid]
      , [CreateDate]
      , [CreatedBy]
      , [UpdateDate]
      , [UpdatedBy]
      , [DeleteDate]
      , [DeletedBy]
      , [Title]
      , [OriginalTitle]
      , [PublishedDate]
      , [ShowingDate]
      , [DurationMinutes]
      , [Description]
      , [WebPath]
      , [ImagePath]
    FROM [dbo].[Movies]
    WHERE [DeleteDate] IS NULL
      AND [Id] <> 1
  END
  ELSE IF @Method = 3 BEGIN
    SELECT ALL
        [Id]
      , [Guid]
      , [CreateDate]
      , [CreatedBy]
      , [UpdateDate]
      , [UpdatedBy]
      , [DeleteDate]
      , [DeletedBy]
      , [Title]
      , [OriginalTitle]
      , [PublishedDate]
      , [ShowingDate]
      , [DurationMinutes]
      , [Description]
      , [WebPath]
      , [ImagePath]
    FROM [dbo].[Movies]
    WHERE [Id] = @Id
      AND [Id] <> 1
  END
  ELSE IF @Method = 4 BEGIN
    SELECT ALL
        [Id]
      , [Guid]
      , [CreateDate]
      , [CreatedBy]
      , [UpdateDate]
      , [UpdatedBy]
      , [DeleteDate]
      , [DeletedBy]
      , [Title]
      , [OriginalTitle]
      , [PublishedDate]
      , [ShowingDate]
      , [DurationMinutes]
      , [Description]
      , [WebPath]
      , [ImagePath]
    FROM [dbo].[Movies]
    WHERE [DeleteDate] IS NULL 
      AND [Id] = @Id
      AND [Id] <> 1
  END
END
GO

/****************************************************
name = [dbo].[MovieUpdate]
db_type = STORED PROCEDURE
sp_type = UPDATE

params = @Guid
       , @Title
       , @OriginalTitle
       , @PublishedDate
       , @ShowingDate
       , @DurationMinutes
       , @Description
       , @WebPath
       , @ImagePath
       , @UpdatedBy (int identifier of UPDATE initiator)

return_value = VOID
return_code = INTERNAL_ERROR (-1)  
            | SUCCESS (1)
            | NOT_EXISTS (2)
            | DELETED (3)
            | UNIQUE_VIOLATION (4)
****************************************************/
CREATE OR ALTER PROCEDURE [dbo].[MovieUpdate] ( @Guid            AS uniqueidentifier
                                              , @Title           AS nvarchar(256) 
                                              , @OriginalTitle   AS nvarchar(256) 
                                              , @PublishedDate   AS datetime      
                                              , @ShowingDate     AS datetime      
                                              , @DurationMinutes AS int           
                                              , @Description     AS nvarchar(2056)
                                              , @WebPath         AS nvarchar(512) 
                                              , @ImagePath       AS nvarchar(512)
                                              , @UpdatedBy       AS int = 1 )
AS BEGIN
  DECLARE @Id         AS int
  DECLARE @DeleteDate AS datetime
  SELECT ALL TOP 1
      @Id         = [Id]
    , @DeleteDate = [DeleteDate]
  FROM [dbo].[Movies]
  WHERE [Guid] = @Guid

  IF @Id IS NULL BEGIN
    RETURN 2
  END
  ELSE IF @Id IS NOT NULL 
      AND @DeleteDate IS NOT NULL BEGIN
    RETURN 3
  END

  SET @Id         = NULL
  SET @DeleteDate = NULL

 SELECT ALL TOP 1
      @Guid       = [Guid]
    , @DeleteDate = [DeleteDate]
  FROM [dbo].[Movies]
  WHERE ( [OriginalTitle] = @OriginalTitle
      AND [PublishedDate] = @PublishedDate )
    AND [Guid] <> @Guid

  IF  @Id IS NOT NULL 
  AND @DeleteDate IS NULL BEGIN
    RETURN 4
  END
  ELSE IF @Id IS NOT NULL 
      AND @DeleteDate IS NOT NULL BEGIN
    RETURN 3
  END

  UPDATE [dbo].[Movies]
  SET [UpdatedBy]       = @UpdatedBy
    , [UpdateDate]      = GETDATE()
    , [Title]           = @Title
    , [OriginalTitle]   = @OriginalTitle
    , [PublishedDate]   = @PublishedDate
    , [ShowingDate]     = @ShowingDate
    , [DurationMinutes] = @DurationMinutes
    , [Description]     = @Description
    , [WebPath]         = @WebPath
    , [ImagePath]       = @ImagePath
  WHERE [Guid] = @Guid

  IF @@ROWCOUNT = 1 BEGIN
    RETURN 1
  END
  ELSE BEGIN
    RETURN -1
  END
END
GO