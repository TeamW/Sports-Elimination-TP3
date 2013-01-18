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
				$divisions=array("American Central", "American East", "American West", "National Central","National East","National West");
				foreach ($divisions as &$division) {
					$query = "SELECT * FROM `{$division}` ORDER BY Points DESC;";
					$result = executeQuery("localhost", "teamw", "algorithms", "teamw", $query);
					echo("<h3>{$division}</h3>" . PHP_EOL);
					echo("<table cellpadding=5em><tr><th>Team</th><th>Points</th><th>Games Played</th><th>Elimination Status</th></tr>" . PHP_EOL);
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
					echo("</table>" . PHP_EOL);
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
