<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Home Server - Install</title>
<!-- Normalize -->
<link rel="stylesheet" href="https://necolas.github.io/normalize.css/5.0.0/normalize.css">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="css/bootstrap-theme.min.css">

<script src="js/jquery.slim.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<!-- MHS style sheet -->
<link rel="stylesheet" href="css/styles.css">
<script type="text/javascript">
	$(document).ready(function() {
		$("button.btn-next").click(function() {
			var li = $(".nav-pills>li");
			$(li).each(function() {
				$(this)[0].className = "disabled"; // Deactivate all <li> 
				$(".well").hide();
			});
			var tabId = $(this).attr("data-next");
			console.debug($(".nav-pills>li[data-id=" + tabId + "]"));
			//  			[0].className = "active";
			$(".nav-pills>li[data-id=" + tabId + "]")[0].className = "active";
			$(".well[data-id=" + tabId + "]").show('slow');
		});
		$("button.btn-previous").click(function() {
			var li = $(".nav-pills>li");
			$(li).each(function() {
				$(this)[0].className = "disabled"; // Deactivate all <li> 
				$(".well").hide();
			});
			var tabId = $(this).attr("data-previous");
			//console.debug($(".nav-pills>li[data-id=" + tabId + "]"));
			$(".nav-pills>li[data-id=" + tabId + "]")[0].className = "active";
			$(".well[data-id=" + tabId + "]").show('slow');
		});
	});
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<ul class="nav nav-pills nav-justified thumbnail">
					<li data-id="1" class="active"><a href="#">
							<h4 class="list-group-item-heading">Step 1</h4>
							<p class="list-group-item-text">Welcome</p>
					</a></li>
					<li data-id="2" class="disabled"><a href="#">
							<h4 class="list-group-item-heading">Step 2</h4>
							<p class="list-group-item-text">Disk partitionning</p>
					</a></li>
					<li data-id="3" class="disabled"><a href="#">
							<h4 class="list-group-item-heading">Step 3</h4>
							<p class="list-group-item-text">Disk partitionning</p>
					</a></li>
					<li data-id="4" class="disabled"><a href="#">
							<h4 class="list-group-item-heading">Step 4</h4>
							<p class="list-group-item-text">Network settings</p>
					</a></li>
					<li data-id="5" class="disabled"><a href="#">
							<h4 class="list-group-item-heading">Step 5</h4>
							<p class="list-group-item-text">Installation</p>
					</a></li>
					<li data-id="6" class="disabled"><a href="#">
							<h4 class="list-group-item-heading">Step 6</h4>
							<p class="list-group-item-text">Restart</p>
					</a></li>
				</ul>
				<div class="wells">
					<div class="well well-sm description" data-id="1">
						<table>
							<tbody>
								<tr>
									<td><img class="picture" src="images/mhs.png" /></td>
									<td><p>
											<strong>Welcome to the <i>My Home Server install</i></strong> !
										</p>
										<p>This wizard will help you to install <i>My Home Server</i> on your hardware server. All the data on the hardware server will be deleted.</p>
										<p>To perform this install, this server must have enough space to install the operating system, the swap
											partition, the extensions and the user space for the extensions. The minimum required is 5GiB for the
											storage.</p>
<!-- 										<div class="alert alert-warning" role="alert"> -->
<!-- 											<strong>Warning!</strong> -->
<!-- 											<p> -->
<!-- 												Do not connect your hardware server to Internet without a router/firewall: <i>My Home Server</i> is not a -->
<!-- 												router/firewall. -->
<!-- 											</p> -->
<!-- 											<p></p> -->
<!-- 										</div> -->
									</td>
								</tr>
							</tbody>
						</table>
						<nav>
							<button type="button" class="btn btn-success btn-next" data-next="2">Next step</button>
						</nav>
					</div>
					<div class="well well-sm description" style="display: none;" data-id="2">
						<div>
							<p>This install will create automatically the partitions. Please, select the storage
								where to install the operating system, the swap partition and the boot sector:</p>
							<div class="list-group">
								<a class="list-group-item">
									<table class="dd-info">
										<tbody>
											<tr>
												<td><image src="images/harddrive.png" width="32" height="32"></td>
												<td>/dev/sda</td>
												<td>SEAGATE</td>
												<td>1GiB</td>
											</tr>
										</tbody>
									</table>
								</a>
								<a class="list-group-item">
									<table class="dd-info">
										<tbody>
											<tr>
												<td><image src="images/harddrive.png" width="32" height="32"></td>
												<td>/dev/sdb</td>
												<td>SEAGATE</td>
												<td>1GiB</td>
											</tr>
										</tbody>
									</table>
								</a>
								<a class="list-group-item">
									<table class="dd-info">
										<tbody>
											<tr>
												<td><image src="images/harddrive.png" width="32" height="32"></td>
												<td>/dev/sdc</td>
												<td>SEAGATE</td>
												<td>1GiB</td>
											</tr>
										</tbody>
									</table>
								</a>
							</div>
							<h4>Before</h4>
							<h4>After</h4>
						</div>
						<nav>
							<button type="button" class="btn btn-success btn-previous" data-previous="1">Previous step</button>
							<button type="button" class="btn btn-success btn-next" data-next="3">Next step</button>
						</nav>
					</div>
					<div class="well well-sm description" style="display: none;" data-id="3">Content 3...</div>
					<div class="well well-sm description" style="display: none;" data-id="4">Content 3...</div>
					<div class="well well-sm description" style="display: none;" data-id="5">Content 3...</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>