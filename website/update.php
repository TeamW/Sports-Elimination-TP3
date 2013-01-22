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
				$tableName = "";
				echo exec("java -jar test.jar --web", $output); //extract information from .jar into output
				foreach($output as $line) {
					$lineSplit = explode("-", $line);	//split into seperate elements seperated by hyphens
					$elements = count($lineSplit);		//count number of tokens
					if ($elements == 1) {			//currently at league name
						$tableName = $lineSplit[0];	//store it
					} else if ($elements == 4) {					
						$result = executeQuery("localhost", "teamw", "algorithms", "teamw", "SELECT * FROM `{$tableName}` WHERE Team = '{$lineSplit[0]}'");
						if(mysql_num_rows($result) == 0){
							$insertDB = "INSERT INTO `{$tableName}` (Team, Points, `Games Played`, Eliminated) VALUES ('{$lineSplit[0]}', {$lineSplit[1]}, {$lineSplit[2]}, {$lineSplit[3]});";
							executeQuery("localhost", "teamw", "algorithms", "teamw", $insertDB);
				    			echo("<p>{$tableName} - {$lineSplit[0]} inserted.</p>");
						}else{
							executeQuery("localhost", "teamw", "algorithms", "teamw", "UPDATE `{$tableName}` SET Points = {$lineSplit[1]}, `Games Played` = {$lineSplit[2]}, Eliminated = {$lineSplit[3]} WHERE Team = '{$lineSplit[0]}'");
				    			echo("<p>{$tableName} - {$lineSplit[0]} updated.</p>");
						}

					} else {
						echo("<p>Database output error.</p>");
					}
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
