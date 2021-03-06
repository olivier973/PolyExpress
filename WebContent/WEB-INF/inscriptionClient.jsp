<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inscription Client</title>
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
					title="" /></span>Cr�ation d'un compte client
			</div>
			<div class="feat_prod_box_details">
				<form name="register" action="inscriptionsuccess" method="post">
					<fieldset>
						<!-- legend>Inscription</legend -->
						<p>Vous pouvez vous inscrire via ce formulaire.</p>

						<div>
							<label for="email">Email</label> <input type="text" id="email"
								name="email" size="40" maxlength="200" value="${client.email}"/>
						</div>

						<div>
							<label for="mdp">Mot de passe</label> <input type="password" id="mdp"
								name="mdp" size="30" maxlength="30" value=""/>
						</div>

						<div>
							<label for="nom">Nom</label> <input type="text" id="nom"
								name="nom" size="30" maxlength="100" value="${client.nom}"/>
						</div>

						<div>
							<label for="prenom">Prenom</label> <input type="text" id="prenom"
								name="prenom" size="30" maxlength="100" value="${client.prenom}"/>
						</div>

						<div>
							<label for="coordonnee">Coordonn�e</label> <input type="text"
								id="coordonnee" name="coordonnee" size="30" maxlength="100" value="${client.coordonnee}"/>
						</div>

						<div>
							<input type="submit" class="register" value="Inscription" />
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