package fr.ensimag.ack.eggsale.web.paypal;
import java.awt.print.Paper;
import java.io.*;
import java.util.HashMap;
import java.util.Properties;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "PaiementPayPalServ", urlPatterns = {"/cart/paiementPayPal", "/cart/paiementPayPalOk", "/cart/paiementPayPalCancel"})
public class PaiementPayPalServ extends HttpServlet {
 
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws ServletException, IOException {
        
       
        String userPath = request.getServletPath();
        String token = request.getParameter("token");
        String amount = request.getParameter("amount");
        
        //Initialisation des objets
        PayPalFunctions ppf = new PayPalFunctions();
        //String baseURL = "/WEB-INF/view/utilisateur/paiementrCompte.jsp";
        String baseURL ="index.jsp";
        if(userPath.equals("/paiementPayPalOk") && token != null){
            //Tester token pour éviter le rejeu.
            HttpSession session = request.getSession(true);
            if(!session.getAttribute("token").equals(token)){
               
               request.setAttribute("error", "Aucune transaction de paiement de compte n'a été effectuée.");
               request.getRequestDispatcher(baseURL).forward(request, response);
               return;
            }
            session.setAttribute("token", "");
            //Fin protection contre le rejeu
            
            //Credit du compte
            //Details de la commande : HashMap infos = ppf.GetShippingDetails(token);
            Double amountDouble = Double.parseDouble(session.getAttribute("amount").toString());
            request.setAttribute("succes", "Votre compte a bien été crédité de "+session.getAttribute("amount").toString()+"&euro;.");
            request.getRequestDispatcher(baseURL).forward(request, response);
            
        }else if(userPath.equals("/cart/paiementPayPalCancel") && token != null){
            //Affichage de l'echec
            //request.setAttribute("client", client);
            request.setAttribute("error", "Votre compte n'a pas été crédité.");
            request.getRequestDispatcher(baseURL).forward(request, response);
            
        }else if(userPath.equals("/cart/paiementPayPal") && amount != null){
            HttpSession session = request.getSession(true);
            Double paymentAmount;
            // Tests sur la forme du amount entré.
            try{
                paymentAmount = Double.parseDouble(amount);
                if(paymentAmount<0){
                    throw new Exception();
                }
            }catch(Exception e){
                //request.setAttribute("client", client);
                request.setAttribute("error", "Le montant indiqué est invalide.");
                request.getRequestDispatcher(baseURL).forward(request, response);
                return;
            }

            /** PayPal Stuff...
             * - Fais un appel préliminaire Ã  PayPal pour vérifier le compte du vendeur etc.
             * - Redirige le client vers le site paypal (avec des url de retour si erreur ou si ok)
             */
            
            //InputStream inStream = Thread.currentThread().getContextClassLoader()
                // .getResourceAsStream("paypal.properties");

            HashMap nvp = ppf.CallShortcutExpressCheckout (paymentAmount.toString());
            String strAck = nvp.get("ACK").toString();
            if(strAck !=null && strAck.equalsIgnoreCase("Success"))
            {
                response.setContentType("text/html;charset=UTF-8");
                session.setAttribute("token", nvp.get("TOKEN").toString());
                session.setAttribute("amount", amount);
                // Redirect to paypal.com
                response.sendRedirect(response.encodeRedirectURL(ppf.getPaypalURL()+nvp.get("TOKEN").toString()));
                
            }
            else
            {
                response.setContentType("text/html;charset=UTF-8");
                //session.setAttribute("token", nvp.get("TOKEN").toString());
                session.setAttribute("amount", amount);
                // Redirect to paypal.com
                response.sendRedirect("/cart/Show.action");
            }
        }else{
           // request.setAttribute("client", client);
            request.setAttribute("error", "La paiement de compte est momentanément indisponible. <br /> Veuillez nous en excuser.");
            request.getRequestDispatcher(baseURL).forward(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
        throws ServletException, IOException {
      doGet(request, response);
    }
}

