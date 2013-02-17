<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Espace client</title>
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
					<li><a href="catalogueServlet">catalogue</a></li>
					<li class="divider"></li>
					<li><a href="deconnexion">d�connexion</a></li>
					<li class="divider"></li>
				</ul>
			</div>
		</div>
		<div class="center_content">
			<div class="title">
				<span class="title_icon"><img src="images/bullet1.gif" alt=""
					title="" /></span>Espace Client
			</div>
			<div class="feat_prod_box_details">
				<p>${message} ${connexionClient.prenom} ${connexionClient.nom}</p>
				<p>Vous pouvez ici consulter vos commandes pr�cedemment
					effectu�es</p>
				<table>
					<tr>
						<td>Num�ro de la commande</td>
						<td>Montant</td>
						<td>Etat</td>
						<td>Adresse de livraison</td>
					</tr>
					<c:forEach var="commande" items="${listecommandes}">
						<tr>
							<td><c:out value="${commande.id}" /></td>
							<td><c:out value="${commande.montant}" /></td>
							<td><c:out value="${commande.etat}" /></td>
							<td><c:out value="${commande.adresse_livraison}" /></td>
						</tr>
					</c:forEach>
				</table>
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