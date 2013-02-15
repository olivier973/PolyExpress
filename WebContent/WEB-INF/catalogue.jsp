<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Catalogue</title>
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
					<li><a href="deconnexion">déconnexion</a></li>
					<li class="divider"></li>
				</ul>
			</div>
		</div>
		<div class="center_content">
			<div class="title">
				<span class="title_icon"><img src="images/bullet1.gif" alt=""
					title="" /></span>Catalogue
			</div>
			<div class="feat_prod_box_details">
				<p>
					<c:out value="${message}" />
					<c:out value="${connexionClient.prenom}" />
					<c:out value="${connexionClient.nom}" />
				</p>
				<p>Vous pouvez ici consulter la liste des produits disponibles</p>
				<p>
					<a href="panierServlet">Visualiser le panier</a>
				</p>
				<div class="left_content">

					<div class="title">
						<span class="title_icon"><img src="images/bullet1.gif"
							alt="" title="" /></span>Produits
					</div>
					<c:forEach var="produit" items="${listeproduits}">
						<div class="feat_prod_box">
							<div class="prod_img">
								<img src="images/<c:out value="${produit.nom}" />.jpg" alt=""
									title="" border="0" />
							</div>
							<div class="prod_det_box">
								<div class="box_top"></div>
								<div class="box_center">
									<div class="prod_title">
										<c:out value="${produit.nom}" />
									</div>
									<p class="details">
										<c:out value="${produit.description}" />
									</p>
									<p class="details">
										Prix :
										<c:out value="${produit.prix}" />
										euro(s)
									</p>
									<p class="details">
										Quantité :
										<c:out value="${produit.quantite}" />
										unité(s)
									</p>
									<a href="panierServlet?id=${produit.id}&ch=ajouter"
										class="more">- Ajouter le produit -</a>
									<div class="clear"></div>
								</div>
								<div class="box_bottom"></div>
							</div>
							<div class="clear"></div>
						</div>
					</c:forEach>
				</div>
				<div class="clear"></div>
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