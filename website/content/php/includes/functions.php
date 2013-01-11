<?php
    function executeQuery($server, $user, $password, $database, $query) {
        // Attempt connection to MySQL database.
        $connection = mysql_connect($server, $user, $password);
        if(!isset($connection)) {
            die("Database connection failed: " . mysql_error());
        }
        // Attempt to connect to database.
        $db_select = mysql_select_db($database, $connection);
        if(!isset($db_select)) {
            die("Database selection failed: " . mysql_error());
        }
        // Execute query.
        $result = mysql_query($query, $connection);
        if(!isset($result)) {
            die("Database query failed: " . mysql_error());
        }
        // Close connection.
        mysql_close($connection);
        return $result;
    }
?>
