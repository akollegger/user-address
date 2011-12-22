<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"> <!--<![endif]-->
<head>

	<!-- Basic Page Needs
  ================================================== -->
	<meta charset="utf-8">
	<title>SDN Todos</title>
	<meta name="description" content="An SDN Trial">
	<meta name="author" content="Andreas Kollegger">
	<!--[if lt IE 9]>
		<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

	<!-- Mobile Specific Metas
  ================================================== -->
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

	<!-- CSS
  ================================================== -->
	<link rel="stylesheet" href="stylesheets/base.css">
	<link rel="stylesheet" href="stylesheets/skeleton.css">
	<link rel="stylesheet" href="stylesheets/layout.css">

	<!-- Favicons
	================================================== -->
	<link rel="shortcut icon" href="images/favicon.ico">
	<link rel="apple-touch-icon" href="images/apple-touch-icon.png">
	<link rel="apple-touch-icon" sizes="72x72" href="images/apple-touch-icon-72x72.png">
	<link rel="apple-touch-icon" sizes="114x114" href="images/apple-touch-icon-114x114.png">

</head>
<body>



	<!-- Primary Page Layout
	================================================== -->

	<!-- Delete everything in this .container and get started on your own site! -->

	<div class="container">
		<div class="sixteen columns">
			<h1 class="remove-bottom" style="margin-top: 40px">SDN Trial</h1>
			<h5>case: user address</h5>
			<hr />
		</div>
		<div class="row">
			<div class="one-third column">
				<h3>About SDN Trials</h3>
				<p>SDN Trials are bite-sized applications which attempt to (re-)create a particular use case scenario. Typically, this
                    is for debugging purposes, but these can also be for illustrative how-to coding.
				</p>
			</div>
			<div class="one-third column">
				<h3>This Trial</h3>
				<p>A possible issue with data fetching.</p>
				<ul class="square">
					<li><strong>Models</strong>: @NodeEntity User, TaskUser extends User, @NodeEntity Address</li>
				</ul>
			</div>
			<div class="one-third column">
				<h3>Docs &amp; Support</h3>
                <p>
                  Check the <a href="http://groups.google.come/group/neo4j">Neo4j Google Group</a>.
                </p>
			</div>
		</div>
				
		<!-- UI -->
		<div class="row">
			<h5>Todos</h5>
			
			<div id="todos">
			</div>
		
			<hr />
		</div>
		
		<!--  footer  -->
		<div class="row">
			<div class="eight columns">
			<a href="http://neo4j.org"><image src="images/icon_neo4j.png"/></a>
			</div>
			<div class="eight columns">
			<a href="http://spring.neo4j.org"><image style="float:right" src="images/icon_sdn.png"/></a>
			</div>
		</div>
		
	</div><!-- container -->



	<!-- JS
	================================================== -->
	<script src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
	<script src="javascripts/sdn-todos.js"></script>

<!-- End Document
================================================== -->
</body>
</html>