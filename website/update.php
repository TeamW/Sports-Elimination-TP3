<?php require_once($_SERVER['DOCUMENT_ROOT'] . "/teamw/content/php/includes/functions.php");?>
<!doctype html>
<html xml:lang="en" lang="en" xmlns="http://www.w3.org/1999/xhtml" class="no-js">
<?php require_once($_SERVER['DOCUMENT_ROOT'] . "/teamw/content/php/includes/head.php");?>
<body>
<div class="container">
    <header>
        <a href="index.php"><h1>Team W</h1></a>
    </header>
    <section>
        <h2 class="for-outline-algorithm-only">Main page content</h2>
		<?php require_once($_SERVER['DOCUMENT_ROOT'] . "/teamw/content/php/includes/links.php");?>
        <article>
            <h3>Home</h3>
            <?php
				$connection = executeQuery($server, $user, $password, $database, $query);  	//attempt to connect to database
				$tableName = "";
				echo exec("java -jar test.jar --web", $output); //extract information from .jar
				foreach($output as $line) {			//loop through each line from jar
					$lineSplit = explode("-", $line);	//split into seperate elements
					$elements = count($lineSplit);		//count number of tokens
					if($elements < 1)         		//if less than one, end
						echo("<p>");
					if($elements == 1) {		//if its 1, currently at league name
						$tableName = $lineSplit[0];	//grab the league name and store it
						continue;			//next line
					}
					mysql_query("INSERT INTO $tableName (Team, Points, Games Played, Eliminated)" .	//populate row in table
					"VALUES (lineSplit[0], lineSplit[1], lineSplit[2], lineSplit[3])");		//with these values
				}
			?>
        </article>
    </section>
    <footer>
        <p>Team Project 3 - Algorithms for Sports Elimination.</p>
    </footer>
</div>
</body>
</html>
