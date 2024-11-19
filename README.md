docker pull mariadb:10.6.20

docker run --detach --name mariadb -p 3306:3306 --env MARIADB_ROOT_PASSWORD=password mariadb:10.6.20


Descarga el conector JDBC de MariaDB desde el sitio oficial: MariaDB Connector/J.

Copia el archivo JAR (mariadb-java-client-x.x.x.jar) en el directorio modules de WildFly para que WildFly pueda acceder a él.

La ruta típica en WildFly es:

<WILDFLY_HOME>/modules/system/layers/base/org/mariadb/main/

Si no existe la carpeta org/mariadb/main/, créala.

Una vez en la ruta mencionada, coloca el archivo mariadb-java-client-x.x.x.jar.


Paso 2: Crear el archivo module.xml

Crea un archivo module.xml en el directorio main/ con el siguiente contenido para que WildFly reconozca el conector JDBC de MariaDB:

<module xmlns="urn:jboss:module:1.1" name="org.mariadb">
    <resources>
        <resource-root path="mariadb-java-client-x.x.x.jar"/>
    </resources>
    <dependencies>
        <module name="javax.api"/>
        <module name="javax.transaction.api"/>
    </dependencies>
</module>


aso 3: Configurar el DataSource en WildFly

    Edita el archivo de configuración de WildFly (generalmente standalone.xml o domain.xml, dependiendo de tu configuración).

    Añadir el DataSource al archivo de configuración de WildFly:

    Busca el <subsystem xmlns="urn:jboss:domain:datasources:4.0"> dentro del archivo standalone.xml y agrega la siguiente configuración para tu DataSource MariaDB:

<datasources>
    <datasource jndi-name="java:/jdbc/MariaDBDS" pool-name="MariaDBPool" enabled="true" use-ccm="true">
        <connection-url>jdbc:mariadb://localhost:3306/testdb</connection-url>
        <driver>mariadb</driver>
        <security>
            <user-name>root</user-name>
            <password>rootpassword</password>
        </security>
        <validation>
            <valid-connection-checker class-name="org.mariadb.jdbc.MariaDbConnectionChecker"/>
            <validate-on-match>true</validate-on-match>
            <background-validation>false</background-validation>
        </validation>
        <timeout>
            <idle-timeout-minutes>5</idle-timeout-minutes>
            <set-tx-query-timeout>false</set-tx-query-timeout>
        </timeout>
    </datasource>
    <drivers>
        <driver name="mariadb" module="org.mariadb">
            <xa-datasource-class>org.mariadb.jdbc.MariaDbDataSource</xa-datasource-class>
        </driver>
    </drivers>
</datasources>

Explicación:

    jndi-name: El nombre JNDI que se usará para buscar el DataSource en la aplicación.
    connection-url: La URL de conexión JDBC. En este caso, localhost:3306 es la IP y el puerto donde MariaDB está corriendo en Docker. Asegúrate de que el contenedor Docker de MariaDB esté accesible.
    driver: El nombre del controlador, que debe coincidir con el nombre del módulo mariadb.
    security: Define el usuario y la contraseña de la base de datos.
    validation: Configuración opcional para verificar la validez de las conexiones.

Reinicia WildFly para que los cambios surtan efecto:

./standalone.sh -c standalone.xml