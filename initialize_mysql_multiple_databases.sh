# initialize_mysql_multiple_databases.sh

if [ -n "$MYSQL_MULTIPLE_DATABASES" ]; then
  for dbname in $(echo $MYSQL_MULTIPLE_DATABASES | tr ',' ' '); do
	  echo $dbname: $MYSQL_USER
	  mysql -u root -p$MYSQL_ROOT_PASSWORD <<-EOSQL
	    CREATE DATABASE $dbname;
		GRANT ALL PRIVILEGES ON $dbname.* TO 'user'@'%';		
		EOSQL
  done
fi

