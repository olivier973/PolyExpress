<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Espace livreur</title>
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
					title="" /></span>Espace livreur
			</div>
			<div class="feat_prod_box_details">
				<P>
					<c:out value="${message}" />
					<c:out value="${connexionLivreur.prenom}" />
					<c:out value="${connexionLivreur.nom}" />
				</p>
				<p>Vous pouvez ici consulter la liste des alertes</p>
				<table>
					<tr>
						<td>Numéro du message</td>
						<td>Contenu</td>
					</tr>
					<c:forEach var="message" items="${listemessages}">
						<tr>
							<td><c:out value="${message.id}" /></td>
							<td><c:out value="${message.msg}" /></td>
						</tr>
					</c:forEach>
				</table>
				<p>
					<a href="declarerIncidentServlet">Déclarer un incident</a>
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