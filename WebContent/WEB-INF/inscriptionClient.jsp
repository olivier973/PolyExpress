<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
<title>Inscription</title>
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
			<li><a href="inscriptionServlet">s'incrire</a></li>
			<li class="divider"></li>
			<li><a href="details.html">catalogue</a></li>
			<li class="divider"></li>
            </ul>
        </div>    
            
            
       </div> 
       
       
       <div class="center_content">
       	<div class="left_content">
            <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>Register</div>
        
        	<div class="feat_prod_box_details">
            <p class="details">
             Venez vous inscrire
            </p>
            
              	<div class="contact_form">
                <div class="form_subtitle">creer nouveau compte</div>
                 <form name="register" action="incriptionClient" method="post">          
                    <fieldset>
					<legend>Inscription</legend>

					<p>Vous pouvez vous inscrire via ce formulaire.</p>

					<div class="form_row">
					<label for="email">Email</label> 
					<input type="text" id="email" name="email" value="" size="40" maxlength="200" /> 
					 </div> 
					 
					<div class="form_row">
					<label for="mdp">Mot de passe</label> 
					<input type="text" id="mdp" name="mdp" value="" size="30" maxlength="30" /> 
				 	</div> 
				 	
					<div class="form_row">
					<label for="nom">Nom</label> 
					<input type="text" id="nom" name="nom" value="" size="30" maxlength="100" /> 
					 </div> 
					 
					<div class="form_row">
					<label for="prenom">Prenom</label> 
					<input type="text" id="prenom" name="prenom" value="" size="30" maxlength="100" /> 
					 </div>  
					 
					

                    
                    <div class="form_row">
                    <input type="submit" class="register" value="inscription" />
                    </div>   
                  </form>     
                </div>  
            
          </div>	
            
              

            
<div class="footer">
       	<div class="left_footer"> <p>CYRILLE-NEZAN</p> </div>
  	 </div>
</div>
</body>
</html>