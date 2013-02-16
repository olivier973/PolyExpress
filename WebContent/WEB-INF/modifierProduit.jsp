<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modifier un produit</title>
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
					<li><a href="deconnexion">déconnexion</a></li>
					<li class="divider"></li>
				</ul>
			</div>
		</div>
		<div class="center_content">
			<div class="title">
				<span class="title_icon"><img src="images/bullet1.gif" alt=""
					title="" /></span>Modifier un produit
			</div>
			<div class="feat_prod_box_details">
				<form name="register" action="produitsuccesmodification"
					method="post">
					<input type="hidden" id="reference" name="reference" size="30"
						maxlength="100" value="${produit.id}" />
					<fieldset>
						<!-- legend>Produit</legend -->
						<p>Vous pouvez modifier un produit via ce formulaire.</p>
						<div>
							<label for="nom">Nom</label> <input type="text" id="nom"
								name="nom" size="30" maxlength="100" value="${produit.nom}" />
						</div>

						<div>
							<label for="prix">Prix</label> <input type="text" id="prix"
								name="prix" size="30" maxlength="100" value="${produit.prix}" />
						</div>

						<div>
							<label for="quantite">Quantite</label> <input type="text"
								id="quantite" name="quantite" size="30" maxlength="100"
								value="${produit.quantite}" />
						</div>

						<div>
							<label for="description">Description</label> <input type="text"
								id="description" name="description" size="30" maxlength="100"
								value="${produit.description}" />
						</div>

						<div>
							<input type="submit" class="register" value="Modifier" />
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