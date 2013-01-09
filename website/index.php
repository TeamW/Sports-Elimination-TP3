<!doctype html>
<html xml:lang="en" lang="en" xmlns="http://www.w3.org/1999/xhtml" class="no-js">
<head>
    <meta charset="utf-8" />
    <title>Gordon Reid</title>
    <link rel="stylesheet" type="text/css" href="/content/css/template.css" />
    <!--<script src="/content/js/jQuery.js"></script>
    <script src="/content/js/jQueryUI.js"></script>
    <script src="/content/js/styling.js"></script>-->
    <script src="/content/js/googleAnalytics.js"></script>
    <!--[if lt IE 9]>
    <script src="/content/js/html5shiv.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <header>
        <a href="index.php"><h1>Team W</h1></a>
    </header>
    <section>
        <h2 class="for-outline-algorithm-only">Main page content</h2>
        <nav>
            <h3 class="navigation">Navigation</h3>
            <ul>
                <li><a href="index.php">Home</a></li>
            </ul>
        </nav>
        <article>
            <h3>Home</h3>
            <?php
				echo exec("java -jar test.jar --web", $output);
				foreach ($output as &$value)
    				echo ($value . "</br>" . PHP_EOL);
			?>
        </article>
    </section>
    <footer>
        <p>Team Project 3 - Algorithms for Sports Elimination.</p>
    </footer>
</div>
</body>
</html>
