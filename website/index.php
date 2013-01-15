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
				echo exec("java -jar test.jar --web", $output);
				foreach ($output as &$value) {
					if ($value == "") {
						continue;
					}
					$splitLine = explode("-", $value);
					$elements = count($splitLine);
					if ($elements != 1) {
						echo("<p>");
					}
					foreach ($splitLine as &$splitValue) {
						if ($elements == 1) {
							echo ("<h3>" . $splitValue . "</h3>" . PHP_EOL);
						} else {
							   echo ($splitValue . " ");
						}
					}
					if ($elements != 1) {
						echo("</p>" . PHP_EOL);
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
