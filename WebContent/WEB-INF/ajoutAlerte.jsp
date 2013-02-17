<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ajouter une alerte</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
	<div id="wrap">
		<div class="header">
			<div class="logo">
				<a href="acceuil"><h1>PolyExpress</h1></a>
			</div>
			<div id="menu">
				<ul>
					<li class="selected"><a href="acceuil">accueil</a></li>
					<li class="divider"></li>
					<li><a href="authentificationServlet">mon compte</a></li>
					<li class="divider"></li>
					<li><a href="listeCommandesLivreurServlet">commandes</a></li>
					<li class="divider"></li>
					<li><a href="listeMesCommandesLivreurServlet">mes commandes</a></li>
					<li class="divider"></li>
					<li><a href="deconnexion">déconnexion</a></li>
					<li class="divider"></li>
				</ul>
			</div>
		</div>
		<div class="center_content">
			<div class="title">
				<span class="title_icon"><img src="images/bullet1.gif" alt=""
					title="" /></span>Ajouter une alerte
			</div>
			<div class="feat_prod_box_details">
				<form name="register" action="alertesucces" method="post">
					<fieldset>
						<!-- legend>Produit</legend -->
						<p>Vous pouvez ajouter une alerte via ce formulaire.</p>
						<p>
							<a href="authentificationServlet">Retourner à la liste des alertes</a>
						</p>

						<div>
							<label for="texte">Description</label> <input type="text"
								id="texte" name="texte" size="30" maxlength="100" value="${message_alerte}" />
						</div>

						<div>
							<input type="submit" class="register" value="Ajouter" />
						</div>
					</fieldset>
				</form>
			</div>
		</div>
		<div class="footer">
			<div class="left_footer">
				<p>CYRILLE-NEZAN</p>
			</div>
		</div>
	</div>
</body>
</html>