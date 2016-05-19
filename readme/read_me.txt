Instruction to launch

1.Create database mob_app_store_db;

	=>	create database mob_app_store_db;

2.Place mob_app_store_db_dump.sql to disc "C:\Downloads"

3.Import data 
	
	open cmd and enter to: "C:\Program Files\MySQL\MySQL Server 5.7\bin"
	execute sql command:

	=>	mysql -u root -p mob_app_store_db < C:\Downloads\mob_app_store_db_dump.sql