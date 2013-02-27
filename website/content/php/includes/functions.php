<?php
	/*
	 * Modify these values to match your installation.
	 * This is very insecure however I did this to
     * simplify testing of the code. This should not be used
     * on a public or production environment.
     */
	$server = "localhost";
	$user = "teamw";
	$password = "algorithms";
	$database = "teamw";

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

	function showDivisions() {
		$divisions=array("American Central", "American East", "American West", "National Central","National East","National West");
		echo("<div id='accordion'>");
		foreach ($divisions as &$division) {
			$query = "SELECT * FROM `{$division}` ORDER BY Points DESC;";
			$result = executeQuery($server, $user, $password, $database, $query);
			echo("<h3><a href='#'>{$division}</a></h3>" . PHP_EOL);
			echo("<div><table cellpadding=5em><tr><th>Team</th><th>Points</th><th>Games Played</th><th>Elimination Status</th></tr>" . PHP_EOL);
        	while ($row = mysql_fetch_array($result)) {
				echo("<tr>");
				echo("<td>{$row['Team']}</td>");
				echo("<td>{$row['Points']}</td>");
				echo("<td>{$row['Games Played']}</td>");
				if($row['Eliminated'] == 1) {
					echo("<td>Eliminated</td>");
				} else {
					echo("<td>Not Eliminated</td>");
				}
				echo("</tr>" . PHP_EOL);
			}
			echo("</table></div>" . PHP_EOL);
		}
		echo("</div>");
	}

	function showDivisionsDate($date) {
		echo exec("java -jar elim.jar --web {$date}", $output);
		$first = 1;
		echo(PHP_EOL);
		echo("<div id='accordion'>". PHP_EOL);
		foreach($output as &$value) {
			if($value === "") {
				continue;
			}
			$splitLine = explode("-", $value);
			$elements = count($splitLine);
			if ($elements == 1) {
				if ($first == 0) {
					echo("</table></div>" . PHP_EOL);
				}
				$first = 0;
				echo("<h3><a href='#'>{$splitLine[0]}</a></h3>" . PHP_EOL);
				echo("<div><table cellpadding=5em><tr><th>Team</th><th>Points</th><th>Games Played</th><th>Elimination Status</th></tr>" . PHP_EOL);
			} else {
				echo("<tr>");
				foreach($splitLine as &$splitValue) {
					if($splitValue === 'true') {
						echo("<td>Eliminated</td>");
					} else if ($splitValue === 'false') {
						echo("<td>Not Eliminated</td>");
					} else {
						echo("<td>" . $splitValue . "</td>");
					}
				}
				echo("</tr>" . PHP_EOL);
			}
		}
		echo("</table></div></div>" . PHP_EOL);
	}

	function updateDivisions() {
		$tableName = "";
		echo exec("java -jar elim.jar --web", $output);
		foreach($output as $line) {
			$lineSplit = explode("-", $line);
			$elements = count($lineSplit);
			if ($elements == 1) {
				$tableName = $lineSplit[0];
			} else if ($elements == 4) {
				$select = "SELECT * FROM `{$tableName}` WHERE Team = '{$lineSplit[0]}'";				
				$result = executeQuery($server, $user, $password, $database, $select)
				if(mysql_num_rows($result) == 0){
					$insertDB = "INSERT INTO `{$tableName}` (Team, Points, `Games Played`, Eliminated) VALUES ('{$lineSplit[0]}', {$lineSplit[1]}, {$lineSplit[2]}, {$lineSplit[3]});";
					executeQuery($server, $user, $password, $database, $insertDB);
		    		echo("<p>{$tableName} - {$lineSplit[0]} inserted.</p>");
				} else {
					$updateDB = "UPDATE `{$tableName}` SET Points = {$lineSplit[1]}, `Games Played` = {$lineSplit[2]}, Eliminated = {$lineSplit[3]} WHERE Team = '{$lineSplit[0]}'";
					executeQuery($server, $user, $password, $database, $updateDB);
					echo("<p>{$tableName} - {$lineSplit[0]} updated.</p>");
				}
			} else {
				echo("<p>Database output error.</p>");
			}
		}
	}

$days = array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);

	function leapYearCheck($date) {
		global $days;
		// Leap year check
		if ($dateSplit[2] % 400 == 0) {
			$days[1] = 29;
		} else if ($dateSplit[2] % 100 != 0) {
			$days[1] = 28;
		} else if ($$dateSplit[2] % 4 == 0) {
			$days[1] = 29;
		} else {
			$days[1] = 28;
		}
	}

	function decrementDate($date) {
		global $days;
		$dateSplit = explode("-", $date);
		// Leap year check
		leapYearCheck($date);
		// Change date
		$dateSplit[0] = $dateSplit[0] - 1;
		if ($dateSplit[0] < 1) {
			$dateSplit[1] = $dateSplit[1] - 1;
			if ($dateSplit[1] < 1) {
				$dateSplit[1] = 12;
				$dateSplit[2] = $dateSplit[2] - 1;
			}
			$dateSplit[0] = $days[$dateSplit[1] - 1];
		}
		return $dateSplit[0] . "-" . $dateSplit[1] . "-" . $dateSplit[2];
	}

	function incrementDate($date) {
		global $days;
		$dateSplit = explode("-", $date);
		// Leap year check
		leapYearCheck($date);
		// Change date
		$dateSplit[0] = $dateSplit[0] + 1;
		if ($dateSplit[0] > $days[$dateSplit[1] - 1]) {
			$dateSplit[1] = $dateSplit[1] + 1;
			if ($dateSplit[1] > 12) {
				$dateSplit[1] = 1;
				$dateSplit[2] = $dateSplit[2] + 1;
			}
			$dateSplit[0] = 1;
		}
		return $dateSplit[0] . "-" . $dateSplit[1] . "-" . $dateSplit[2];
	}

	function chooseDate($date) {
		$prevDate = decrementDate($date);
		$nextDate = incrementDate($date);
		echo("<table><tr>");
		echo("<td><a href='index.php?page=showDivisions&date=" . $prevDate . "'>Previous Day</a></td>");
		echo("<td><a href='index.php?page=showDivisions&date=" . $nextDate . "'>Next Day</td></tr></table>");
	}

	function showPage() {
		if (isset($_GET['page'])) {
			$page = $_GET['page'];
		} else {
			$page = "showDivisions";
		}
		if (isset($_GET['date'])) {
			$date = $_GET['date'];
		}
		if ($page === "updateDivisions") {
			echo("<h3>Updated Divisions</h3>");
            updateDivisions();
		} else if (!isset($_GET['date'])) {
			echo("<h3>Division Standings</h3>");
			chooseDate("05-10-2012"); // bad bad bad
			echo("<p>Click division name to see standings.</p>");
            showDivisions();
		} else {
			echo("<h3>Division Standings at the start of {$date}</h3>");
			chooseDate($date);
			echo("<p>Click division name to see standings.</p>");
			showDivisionsDate($date);
		}
	}
?>
