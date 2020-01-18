### Initial configuration

Run SQL script for creating tables and adding data:

        mysql>source your directory\Shop\sql\db_create.sql
        
Set your username, password and url in context.xml for connecting to the Database:

        <Context>
            <Resource name="jdbc/aroma_shop" auth="Container"
                      type="javax.sql.DataSource" maxActive="100"
                      maxIdle="30" maxWait="10000"
                      username="root"
                      password="root"
                      driverClassName="com.mysql.cj.jdbc.Driver"
                      defaultAutoCommit="false"
                      url="jdbc:mysql://localhost:3306/aroma_shop"/>
        </Context>