<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Authentification</title>
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
					<li class="selected"><a href="acceuil">home</a></li>
					<li class="divider"></li>
					<li><a href="authentificationServlet">mon compte</a></li>
					<li class="divider"></li>
					<li><a href="inscription">s'incrire</a></li>
					<li class="divider"></li>
					<li><a href="catalogueServlet">catalogue</a></li>
					<li class="divider"></li>
				</ul>
			</div>
		</div>
		<div class="center_content">
			<div class="title">
				<span class="title_icon"><img src="images/bullet1.gif" alt=""
					title="" /></span>Connexion
			</div>
			<div class="feat_prod_box_details">
				<form action="connexion" method="post">
					<p>Connectez-vous via ce formulaire</p>
					<label for="type">Type d'utilisateur</label> <select name="type"
						id="type">
						<option value="client">Client</option>
						<option value="commercant">Commerçant</option>
						<option value="livreur">Livreur</option>
					</select> <br /> <label for="login">Identifiant(email)</label> <input
						type="text" id="login" name="login" value="" size="20"
						maxlength="60" /> <br /> <label for="motdepasse">Mot de
						passe</label> <input type="password" id="motdepasse" name="motdepasse"
						value=""> <br /> <input type="submit" value="Connexion" />
					<br />
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