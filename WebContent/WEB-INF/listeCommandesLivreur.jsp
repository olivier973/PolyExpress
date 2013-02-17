<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des commandes en cours de traitement</title>
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
					title="" /></span>Liste des commandes
			</div>
			<div class="feat_prod_box_details">
				<p>Vous pouvez ici consulter la liste des commandes en cours de traitement</p>
				<table>
					<tr>
						<td>Numéro commande</td>
						<td>Montant</td>
						<td>Adresse de livraison</td>
						<td>Livreur affecté</td>
					</tr>
					<c:forEach var="commande" items="${listecommandes}">
						<tr>
							<td><c:out value="${commande.id}" /></td>
							<td><c:out value="${commande.montant}" /></td>
							<td><c:out value="${commande.adresse_livraison}" /></td>
							<c:choose>
								<c:when test="${commande.id_livreur==0}">
									<td><a href="attributionLivraisonServlet?id=${commande.id_pensebete}">S'occuper de cette livraison</a></td>
								</c:when>
								<c:otherwise>
									<td><c:out value="${commande.id_livreur}" /></td>
								</c:otherwise>
							</c:choose>
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