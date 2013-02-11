<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
<title>AjoutProduit</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
<div id="wrap">

       <div class="header">
       		<div class="logo"><a href="index.html"><img src="images/logo.gif" alt="" title="" border="0" /></a></div>            
        <div id="menu">
            <ul>                                                                       
            <li class="selected"><a href="acceuil">home</a></li>
			<li class="divider"></li>
			<li><a href="authentificationServlet">mon compte</a></li>
			<li class="divider"></li>
			<li><a href="deconnexion">déconnexion</a></li>
			<li class="divider"></li>
            </ul>
        </div>    
            
            
       </div> 
       
       
       <div class="center_content">
      
            <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>Register</div>
        
        	<div class="feat_prod_box_details">
            
            
              	<div >
                <div >Ajouter nouveau produit</div>
                 <form name="register" action="produitsucces" method="post">          
                    <fieldset>
					<legend>Produit</legend>

					<p>Vous pouvez vous inscrire via ce formulaire.</p>

					<div >
					<label for="email">Email</label> 
					<input type="text" id="email" name="email"  size="40" maxlength="200" /> 
					 </div> 
					 
					<div >
					<label for="mdp">Mot de passe</label> 
					<input type="text" id="mdp" name="mdp"  size="30" maxlength="30" /> 
				 	</div> 
				 	
					<div >
					<label for="nom">Nom</label> 
					<input type="text" id="nom" name="nom"  size="30" maxlength="100" /> 
					 </div> 
					 
					<div >
					<label for="prix">Prix</label> 
					<input type="text" id="prix" name="prix"  size="30" maxlength="100" /> 
					 </div>  
					
					 <div >
					<label for="quantite">Quantite</label> 
					<input type="text" id="quantite" name="quantite"  size="30" maxlength="100" /> 
					 </div> 
					
					 <div >
					<label for="description">Description</label> 
					<input type="text" id="description" name="description"  size="30" maxlength="100" /> 
					 </div> 
					

                    
                    <div >
                    <input type="submit" class="register" value="ajout" />
                    </div>   
                  </form>     
              
            
          </div>	
            
              

            
<div class="footer">
       	<div class="left_footer"> <p>CYRILLE-NEZAN</p> </div>
  	 </div>
</div>
</body>
</html>