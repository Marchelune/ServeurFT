<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="donnees.User" %>
<%@ page import="com.google.appengine.api.blobstore.*" %>

<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>

<!DOCTYPE html>
<html class="html">
 <head>

  <meta http-equiv="Content-type" content="text/html;charset=UTF-8"/>
  <meta name="description" content="Fit Together"/>
  <meta name="generator" content="7.0.314.244"/>
  <title>Fit Together</title>
  <!-- CSS -->
  <link rel="stylesheet" type="text/css" href="css/site_global.css?417434784"/>
  <link rel="stylesheet" type="text/css" href="css/index.css?400654005" id="pagesheet"/>
  <!-- Other scripts -->
  <script type="text/javascript">
   document.documentElement.className += ' js';
var __adobewebfontsappname__ = "muse";
</script>
  <!-- JS includes -->
  <script type="text/javascript">
   document.write('\x3Cscript src="' + (document.location.protocol == 'https:' ? 'https:' : 'http:') + '//webfonts.creativecloud.com/droid-sans:n4:all.js" type="text/javascript">\x3C/script>');
</script>
   </head>
 <body>

  <div class="clearfix" id="page"><!-- column -->
   <div class="position_content" id="page_position_content">
    <div class="browser_width colelem" id="u81"><!-- group -->
     <div class="clearfix" id="u81_align_to_page">
      <div class="clip_frame grpelem" id="u118"><!-- image -->
       <img class="block" id="u118_img" src="images/banni%c3%a8re.png" alt="" width="883" height="214"/>
      </div>
     </div>
    </div>
    <img class="colelem" id="u168-7" src="images/u168-7.png" alt="La vie en entreprise n’est pas de tout repos : pression sur les employés, stress, hygiène de vie et santé en danger ... C’est pourquoi nous voulons aider les employés de ces grandes entreprises à avoir un mode de vie plus sain. Des employés motivés et en bonne santé ne peuvent qu’améliorer la productivité de l’entreprise. C’est dans ce contexte que nous avons décidé de lancer le projet FitTogether." width="920" height="89"/><!-- rasterized frame -->
    <img class="colelem" id="u172-4" src="images/u172-4.png" alt="Inscrivez&#45;vous :" width="251" height="29"/><!-- rasterized frame -->
    <div class="colelem" id="u251"><!-- custom html -->
     <form action="<%= blobstoreService.createUploadUrl("/") %>" method="post" enctype="multipart/form-data">
			
			<p>
				<label>Votre prénom : <input type="text" name="prenom" required /></label>
			</p>
<br />
			<p>
				<label>Votre nom : <input type="text" name="nom" required /></label>
			</p>
<br />
			<p>
				<label>Votre email : <input type="email" name="id" required /></label>
			</p>
<br />
			<p>
				<label>Choisissez une photo de profil : <input type="file" name="photo" /></label>
			</p>
<br />
			<p>
				<label>Votre mot de passe : <input type="password" name="password" required /></label>
			</p>
<br />
			<p>
				<input type="submit" />
			</p>
		</form>

</div>
    <img class="colelem" id="u253-4" src="images/u253-4.png" alt="Ils sont inscrits :" width="252" height="29"/><!-- rasterized frame -->
    <div class="shadow clearfix colelem" id="u305-7"><!-- content -->
     <p id="u305-3"><span class="actAsDiv normal_text" id="u316"><!-- content --><span class="actAsDiv clip_frame" id="u311"><!-- image --><img class="block" id="u311_img" src="images/dupond.jpg" alt="" width="160" height="203"/></span></span><span id="u305-2">Un mec lambda, qui a Sportcoins et id</span></p>
     <p id="u305-5">Son dernier sport était yoga</p>
    </div>
    <div class="verticalspacer"></div>
    <div class="browser_width colelem" id="u112"><!-- group -->
     <div class="clearfix" id="u112_align_to_page">
      <div class="clearfix grpelem" id="u171-4"><!-- content -->
       <p>Conçu par Rémi Sormain</p>
      </div>
     </div>
    </div>
   </div>
  </div>
  <!-- JS includes -->
  <script type="text/javascript">
   if (document.location.protocol != 'https:') document.write('\x3Cscript src="http://musecdn.businesscatalyst.com/scripts/4.0/jquery-1.8.3.min.js" type="text/javascript">\x3C/script>');
</script>
  <script type="text/javascript">
   window.jQuery || document.write('\x3Cscript src="scripts/jquery-1.8.3.min.js" type="text/javascript">\x3C/script>');
</script>
  <script src="scripts/museutils.js?3865766194" type="text/javascript"></script>
  <script src="scripts/jquery.tobrowserwidth.js?3842421675" type="text/javascript"></script>
  <script src="scripts/jquery.watch.js?4068933136" type="text/javascript"></script>
  <!-- Other scripts -->
  <script type="text/javascript">
   $(document).ready(function() { try {
Muse.Utils.transformMarkupToFixBrowserProblemsPreInit();/* body */
$('.browser_width').toBrowserWidth();/* browser width elements */
Muse.Utils.prepHyperlinks(true);/* body */
Muse.Utils.fullPage('#page');/* 100% height page */
Muse.Utils.showWidgetsWhenReady();/* body */
Muse.Utils.transformMarkupToFixBrowserProblems();/* body */
} catch(e) { Muse.Assert.fail('Error calling selector function:' + e); }});
</script>
   </body>
</html>
