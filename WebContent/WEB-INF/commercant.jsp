<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Espace commercant</title>
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
					<li><a href="deconnexion">d�connexion</a></li>
					<li class="divider"></li>
				</ul>
			</div>
		</div>
		<div class="center_content">
			<div class="title">
				<span class="title_icon"><img src="images/bullet1.gif" alt=""
					title="" /></span>Espace Commer�ant
			</div>
			<div class="feat_prod_box_details">
				<p>
					<c:out value="${message}" />
					<c:out value="${connexionCommercant.prenom}" />
					<c:out value="${connexionCommercant.nom}" />
				</p>
				<p>Vous pouvez ici consulter la liste de vos produits</p>
				<table>
					<tr>
						<td>Nom</td>
						<td>Prix</td>
						<td>Quantit�</td>
						<td>Description</td>
					</tr>
					<c:forEach var="produit" items="${listeproduits}">
						<tr>
							<td><c:out value="${produit.nom}" /></td>
							<td><c:out value="${produit.prix}" /></td>
							<td><c:out value="${produit.quantite}" /></td>
							<td><c:out value="${produit.description}" /></td>
							<td><a href="supprimerProduitServlet?id=${produit.id}">Supprimer
									le produit</a></td>
							<td><a href="modifierProduitServlet?id=${produit.id}">Modifier
									le produit</a></td>
						</tr>
					</c:forEach>
				</table>
				<p>
					<a href="ajouterProduitServlet">Ajouter un produit</a>
				</p>
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