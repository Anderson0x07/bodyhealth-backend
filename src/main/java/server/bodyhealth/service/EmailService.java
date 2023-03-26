package server.bodyhealth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import server.bodyhealth.entity.Usuario;
import server.bodyhealth.exception.NotFoundException;
import server.bodyhealth.repository.UsuarioRepository;
import server.bodyhealth.util.MessageUtil;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.Locale;
import java.util.Optional;

@Service
@Transactional
public class EmailService {
    @Autowired
    JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String email;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MessageUtil messageUtil;

    public String HtmlConfirmacion(String nombre, int id){
         String htmlEmail = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                 "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" style=\"width:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\">\n" +
                 "<head>\n" +
                 "<meta charset=\"UTF-8\">\n" +
                 "<meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\n" +
                 "<meta name=\"x-apple-disable-message-reformatting\">\n" +
                 "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                 "<meta content=\"telephone=no\" name=\"format-detection\">\n" +
                 "<title>Trigger newsletter</title><!--[if (mso 16)]>\n" +
                 "<style type=\"text/css\">\n" +
                 "a {text-decoration: none;}\n" +
                 "</style>\n" +
                 "<![endif]--><!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]--><!--[if gte mso 9]>\n" +
                 "<xml>\n" +
                 "<o:OfficeDocumentSettings>\n" +
                 "<o:AllowPNG></o:AllowPNG>\n" +
                 "<o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                 "</o:OfficeDocumentSettings>\n" +
                 "</xml>\n" +
                 "<![endif]--><!--[if !mso]><!-- -->\n" +
                 "<link href=\"https://fonts.googleapis.com/css?family=Lato:400,400i,700,700i\" rel=\"stylesheet\"><!--<![endif]-->\n" +
                 "<style type=\"text/css\">\n" +
                 "#outlook a {\n" +
                 "padding:0;\n" +
                 "}\n" +
                 ".ExternalClass {\n" +
                 "width:100%;\n" +
                 "}\n" +
                 ".ExternalClass,\n" +
                 ".ExternalClass p,\n" +
                 ".ExternalClass span,\n" +
                 ".ExternalClass font,\n" +
                 ".ExternalClass td,\n" +
                 ".ExternalClass div {\n" +
                 "line-height:100%;\n" +
                 "}\n" +
                 ".es-button {\n" +
                 "mso-style-priority:100!important;\n" +
                 "text-decoration:none!important;\n" +
                 "}\n" +
                 "a[x-apple-data-detectors] {\n" +
                 "color:inherit!important;\n" +
                 "text-decoration:none!important;\n" +
                 "font-size:inherit!important;\n" +
                 "font-family:inherit!important;\n" +
                 "font-weight:inherit!important;\n" +
                 "line-height:inherit!important;\n" +
                 "}\n" +
                 ".es-desk-hidden {\n" +
                 "display:none;\n" +
                 "float:left;\n" +
                 "overflow:hidden;\n" +
                 "width:0;\n" +
                 "max-height:0;\n" +
                 "line-height:0;\n" +
                 "mso-hide:all;\n" +
                 "}\n" +
                 "@media only screen and (max-width:600px) {p, ul li, ol li, a { line-height:150%!important } h1, h2, h3, h1 a, h2 a, h3 a { line-height:120%!important } h1 { font-size:30px!important; text-align:center } h2 { font-size:26px!important; text-align:center } h3 { font-size:20px!important; text-align:center } .es-header-body h1 a, .es-content-body h1 a, .es-footer-body h1 a { font-size:30px!important } .es-header-body h2 a, .es-content-body h2 a, .es-footer-body h2 a { font-size:26px!important } .es-header-body h3 a, .es-content-body h3 a, .es-footer-body h3 a { font-size:20px!important } .es-menu td a { font-size:16px!important } .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a { font-size:16px!important } .es-content-body p, .es-content-body ul li, .es-content-body ol li, .es-content-body a { font-size:16px!important } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size:16px!important } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a { font-size:12px!important } *[class=\"gmail-fix\"] { display:none!important } .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 { text-align:center!important } .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 { text-align:right!important } .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3 { text-align:left!important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display:inline!important } .es-button-border { display:block!important } a.es-button, button.es-button { font-size:20px!important; display:block!important; border-width:15px 25px 15px 25px!important } .es-btn-fw { border-width:10px 0px!important; text-align:center!important } .es-adaptive table, .es-btn-fw, .es-btn-fw-brdr, .es-left, .es-right { width:100%!important } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width:100%!important; max-width:600px!important } .es-adapt-td { display:block!important; width:100%!important } .adapt-img { width:100%!important; height:auto!important } .es-m-p0 { padding:0px!important } .es-m-p0r { padding-right:0px!important } .es-m-p0l { padding-left:0px!important } .es-m-p0t { padding-top:0px!important } .es-m-p0b { padding-bottom:0!important } .es-m-p20b { padding-bottom:20px!important } .es-mobile-hidden, .es-hidden { display:none!important } tr.es-desk-hidden, td.es-desk-hidden, table.es-desk-hidden { width:auto!important; overflow:visible!important; float:none!important; max-height:inherit!important; line-height:inherit!important } tr.es-desk-hidden { display:table-row!important } table.es-desk-hidden { display:table!important } td.es-desk-menu-hidden { display:table-cell!important } .es-menu td { width:1%!important } table.es-table-not-adapt, .esd-block-html table { width:auto!important } table.es-social { display:inline-block!important } table.es-social td { display:inline-block!important } .es-desk-hidden { display:table-row!important; width:auto!important; overflow:visible!important; max-height:inherit!important } }\n" +
                 "</style>\n" +
                 "</head>\n" +
                 "<body style=\"width:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;padding:0;Margin:0\">\n" +
                 "<div class=\"es-wrapper-color\" style=\"background-color:#F4F4F4\"><!--[if gte mso 9]>\n" +
                 "<v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\n" +
                 "<v:fill type=\"tile\" color=\"#f4f4f4\"></v:fill>\n" +
                 "</v:background>\n" +
                 "<![endif]-->\n" +
                 "<table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;background-color:#F4F4F4\">\n" +
                 "<tr class=\"gmail-fix\" height=\"0\" style=\"border-collapse:collapse\">\n" +
                 "<td style=\"padding:0;Margin:0\">\n" +
                 "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:600px\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"padding:0;Margin:0;line-height:1px;min-width:600px\" height=\"0\"><img src=\"https://akhpyb.stripocdn.email/content/guids/CABINET_837dc1d79e3a5eca5eb1609bfe9fd374/images/41521605538834349.png\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;max-height:0px;min-height:0px;min-width:600px;width:600px\" alt width=\"600\" height=\"1\"></td>\n" +
                 "</tr>\n" +
                 "</table></td>\n" +
                 "</tr>\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td valign=\"top\" style=\"padding:0;Margin:0\">\n" +
                 "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                 "<table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td align=\"left\" style=\"Margin:0;padding-left:10px;padding-right:10px;padding-top:15px;padding-bottom:15px\"><!--[if mso]><table style=\"width:580px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:282px\" valign=\"top\"><![endif]-->\n" +
                 "<table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td align=\"left\" style=\"padding:0;Margin:0;width:282px\">\n" +
                 "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td class=\"es-infoblock es-m-txt-c\" align=\"left\" style=\"padding:0;Margin:0;line-height:14px;font-size:12px;color:#CCCCCC\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica\\ neue', helvetica, sans-serif;line-height:14px;color:#CCCCCC;font-size:12px\">Put your preheader text here<br></p></td>\n" +
                 "</tr>\n" +
                 "</table></td>\n" +
                 "</tr>\n" +
                 "</table><!--[if mso]></td><td style=\"width:20px\"></td><td style=\"width:278px\" valign=\"top\"><![endif]-->\n" +
                 "<table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td align=\"left\" style=\"padding:0;Margin:0;width:278px\">\n" +
                 "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td align=\"right\" class=\"es-infoblock es-m-txt-c\" style=\"padding:0;Margin:0;line-height:14px;font-size:12px;color:#CCCCCC\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:14px;color:#CCCCCC;font-size:12px\"><a href=\"https://viewstripo.email\" class=\"view\" target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#CCCCCC;font-size:12px;font-family:arial, 'helvetica neue', helvetica, sans-serif\">View in browser</a></p></td>\n" +
                 "</tr>\n" +
                 "</table></td>\n" +
                 "</tr>\n" +
                 "</table><!--[if mso]></td></tr></table><![endif]--></td>\n" +
                 "</tr>\n" +
                 "</table></td>\n" +
                 "</tr>\n" +
                 "</table>\n" +
                 "<table class=\"es-header\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:#FFA73B;background-repeat:repeat;background-position:center top\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                 "<table class=\"es-header-body\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td align=\"left\" style=\"Margin:0;padding-bottom:10px;padding-left:10px;padding-right:10px;padding-top:20px\">\n" +
                 "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:580px\">\n" +
                 "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td align=\"center\" style=\"Margin:0;padding-left:10px;padding-right:10px;padding-top:25px;padding-bottom:25px;font-size:0px\"><img src=\"https://i.ibb.co/G7P02Js/Logo-Body-Health.jpg\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" height=\"156\"></td>\n" +
                 "</tr>\n" +
                 "</table></td>\n" +
                 "</tr>\n" +
                 "</table></td>\n" +
                 "</tr>\n" +
                 "</table></td>\n" +
                 "</tr>\n" +
                 "</table>\n" +
                 "<table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td style=\"padding:0;Margin:0;background-color:#ffa73b\" bgcolor=\"#ffa73b\" align=\"center\">\n" +
                 "<table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td align=\"left\" style=\"padding:0;Margin:0\">\n" +
                 "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:600px\">\n" +
                 "<table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;background-color:#ffffff;border-radius:4px\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" role=\"presentation\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td align=\"center\" style=\"Margin:0;padding-bottom:5px;padding-left:30px;padding-right:30px;padding-top:35px\"><h1 style=\"Margin:0;line-height:58px;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;font-size:48px;font-style:normal;font-weight:normal;color:#111111\">¡Bienvenido/a!</h1></td>\n" +
                 "</tr>\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td bgcolor=\"#ffffff\" align=\"center\" style=\"Margin:0;padding-top:5px;padding-bottom:5px;padding-left:20px;padding-right:20px;font-size:0\">\n" +
                 "<table width=\"100%\" height=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td style=\"padding:0;Margin:0;border-bottom:1px solid #ffffff;background:#FFFFFF none repeat scroll 0% 0%;height:1px;width:100%;margin:0px\"></td>\n" +
                 "</tr>\n" +
                 "</table></td>\n" +
                 "</tr>\n" +
                 "</table></td>\n" +
                 "</tr>\n" +
                 "</table></td>\n" +
                 "</tr>\n" +
                 "</table></td>\n" +
                 "</tr>\n" +
                 "</table>\n" +
                 "<table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                 "<table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td align=\"left\" style=\"padding:0;Margin:0\">\n" +
                 "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:600px\">\n" +
                 "<table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;border-radius:4px;background-color:#ffffff\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" role=\"presentation\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td class=\"es-m-txt-l\" bgcolor=\"#ffffff\" align=\"left\" style=\"Margin:0;padding-top:20px;padding-bottom:20px;padding-left:30px;padding-right:30px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:27px;color:#666666;font-size:18px\">" + nombre +" estamos emocionados de que te unas a la familia BodyHealth. Primero, necesitas confirmar tu cuenta. Simplemente presione el botón de abajo.</p></td>\n" +
                 "</tr>\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td align=\"center\" style=\"Margin:0;padding-left:10px;padding-right:10px;padding-top:35px;padding-bottom:35px\"><!--[if mso]><a href=\"http://localhost:8080/usuario/"+id +"\" target=\"_blank\" hidden>\n" +
                 "<v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" esdevVmlButton href=\"http://localhost:8080/usuario/"+id+"\"\n" +
                 "style=\"height:54px; v-text-anchor:middle; width:214px\" arcsize=\"4%\" stroke=\"f\" fillcolor=\"#ffa73b\">\n" +
                 "<w:anchorlock></w:anchorlock>\n" +
                 "<center style='color:#ffffff; font-family:helvetica, \"helvetica neue\", arial, verdana, sans-serif; font-size:20px; font-weight:400; line-height:20px; mso-text-raise:1px'>Confirmar cuenta</center>\n" +
                 "</v:roundrect></a>\n" +
                 "<![endif]--><!--[if !mso]><!-- --><span class=\"msohide es-button-border\" style=\"border-style:solid;border-color:#FFA73B;background:1px;border-width:1px;display:inline-block;border-radius:2px;width:auto;mso-border-alt:10px;mso-hide:all\"><a href=\"http://localhost:8080/usuario/"+id +"\" class=\"es-button\" target=\"_blank\" style=\"mso-style-priority:100 !important;text-decoration:none;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;color:#FFFFFF;font-size:20px;display:inline-block;background:#FFA73B;border-radius:2px;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-weight:normal;font-style:normal;line-height:24px;width:auto;text-align:center;padding:15px 30px\">Confirmar cuenta</a></span><!--<![endif]--></td>\n" +
                 "</tr>\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td class=\"es-m-txt-l\" align=\"left\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:30px;padding-right:30px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:27px;color:#666666;font-size:18px\">Si eso no funciona, copie y pegue el siguiente enlace en su navegador:</p></td>\n" +
                 "</tr>\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td class=\"es-m-txt-l\" align=\"left\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:30px;padding-right:30px\"><a target=\"_blank\" href=\"http://localhost:8080/usuario/"+id +"\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#FFA73B;font-size:18px\">http://localhost:8080/usuario/"+id +"</a></td>\n" +
                 "</tr>\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td class=\"es-m-txt-l\" align=\"left\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:30px;padding-right:30px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:27px;color:#666666;font-size:18px\">Si tiene alguna pregunta, simplemente responda a este correo electrónico; siempre estaremos encantados de ayudarle.</p></td>\n" +
                 "</tr>\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td class=\"es-m-txt-l\" align=\"left\" style=\"Margin:0;padding-top:20px;padding-left:30px;padding-right:30px;padding-bottom:40px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:27px;color:#666666;font-size:18px\">Saludos,</p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:27px;color:#666666;font-size:18px\">Equipo BodyHealth.</p></td>\n" +
                 "</tr>\n" +
                 "</table></td>\n" +
                 "</tr>\n" +
                 "</table></td>\n" +
                 "</tr>\n" +
                 "</table></td>\n" +
                 "</tr>\n" +
                 "</table>\n" +
                 "<table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                 "<table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td align=\"left\" style=\"padding:0;Margin:0\">\n" +
                 "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:600px\">\n" +
                 "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td align=\"center\" style=\"Margin:0;padding-top:10px;padding-bottom:20px;padding-left:20px;padding-right:20px;font-size:0\">\n" +
                 "<table width=\"100%\" height=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td style=\"padding:0;Margin:0;border-bottom:1px solid #f4f4f4;background:#FFFFFF none repeat scroll 0% 0%;height:1px;width:100%;margin:0px\"></td>\n" +
                 "</tr>\n" +
                 "</table></td>\n" +
                 "</tr>\n" +
                 "</table></td>\n" +
                 "</tr>\n" +
                 "</table></td>\n" +
                 "</tr>\n" +
                 "</table></td>\n" +
                 "</tr>\n" +
                 "</table>\n" +
                 "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-footer\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td align=\"center\" bgcolor=\"#ffa73b\" style=\"padding:0;Margin:0;background-color:#ffa73b\">\n" +
                 "<table bgcolor=\"#ffffff\" class=\"es-footer-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td align=\"left\" bgcolor=\"#020202\" style=\"Margin:0;padding-top:10px;padding-bottom:10px;padding-left:20px;padding-right:20px;background-color:#020202\"><!--[if mso]><table style=\"width:560px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:270px\" valign=\"top\"><![endif]-->\n" +
                 "<table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:270px\">\n" +
                 "<table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-position:center top\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td class=\"es-m-txt-c\" align=\"left\" style=\"padding:0;Margin:0;padding-bottom:10px;padding-top:15px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:18px;color:#e7e1e1;font-size:12px\">BodyHealth</p></td>\n" +
                 "</tr>\n" +
                 "</table></td>\n" +
                 "</tr>\n" +
                 "</table><!--[if mso]></td><td style=\"width:20px\"></td><td style=\"width:270px\" valign=\"top\"><![endif]-->\n" +
                 "<table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td align=\"left\" style=\"padding:0;Margin:0;width:270px\">\n" +
                 "<table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-position:center top\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td class=\"es-m-txt-c\" align=\"right\" style=\"padding:0;Margin:0;padding-top:5px;padding-bottom:5px;font-size:0\">\n" +
                 "<table class=\"es-table-not-adapt es-social\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0\"><a target=\"_blank\" href=\"https://www.instagram.com/bodyhealthcucuta/?hl=es\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#111111;font-size:14px\"><img title=\"Instagram\" src=\"https://akhpyb.stripocdn.email/content/assets/img/social-icons/logo-white/instagram-logo-white.png\" alt=\"Inst\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td>\n" +
                 "</tr>\n" +
                 "</table></td>\n" +
                 "</tr>\n" +
                 "</table></td>\n" +
                 "</tr>\n" +
                 "</table><!--[if mso]></td></tr></table><![endif]--></td>\n" +
                 "</tr>\n" +
                 "</table></td>\n" +
                 "</tr>\n" +
                 "</table>\n" +
                 "<table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td align=\"center\" bgcolor=\"#ffa73b\" style=\"padding:0;Margin:0;background-color:#ffa73b\">\n" +
                 "<table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td align=\"left\" style=\"Margin:0;padding-left:20px;padding-right:20px;padding-top:30px;padding-bottom:30px\">\n" +
                 "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:560px\">\n" +
                 "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                 "<tr style=\"border-collapse:collapse\">\n" +
                 "<td class=\"es-infoblock made_with\" align=\"center\" style=\"padding:0;Margin:0;line-height:0px;font-size:0px;color:#CCCCCC\"><a target=\"_blank\" href=\"https://i.ibb.co/G7P02Js/Logo-Body-Health.jpg\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#CCCCCC;font-size:12px\"><img src=\"https://i.ibb.co/G7P02Js/Logo-Body-Health.jpg\" alt width=\"125\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td>\n" +
                 "</tr>\n" +
                 "</table></td>\n" +
                 "</tr>\n" +
                 "</table></td>\n" +
                 "</tr>\n" +
                 "</table></td>\n" +
                 "</tr>\n" +
                 "</table></td>\n" +
                 "</tr>\n" +
                 "</table>\n" +
                 "</div>\n" +
                 "</body>\n" +
                 "</html>";

         return htmlEmail;
    }

    public String htmlConfirmado(String nombre){
        String html = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" style=\"width:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\">\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\n" +
                "<meta name=\"x-apple-disable-message-reformatting\">\n" +
                "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "<meta content=\"telephone=no\" name=\"format-detection\">\n" +
                "<title>Copia de Trigger newsletter</title><!--[if (mso 16)]>\n" +
                "<style type=\"text/css\">\n" +
                "a {text-decoration: none;}\n" +
                "</style>\n" +
                "<![endif]--><!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]--><!--[if gte mso 9]>\n" +
                "<xml>\n" +
                "<o:OfficeDocumentSettings>\n" +
                "<o:AllowPNG></o:AllowPNG>\n" +
                "<o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                "</o:OfficeDocumentSettings>\n" +
                "</xml>\n" +
                "<![endif]--><!--[if !mso]><!-- -->\n" +
                "<link href=\"https://fonts.googleapis.com/css?family=Lato:400,400i,700,700i\" rel=\"stylesheet\"><!--<![endif]-->\n" +
                "<style type=\"text/css\">\n" +
                "#outlook a {\n" +
                "padding:0;\n" +
                "}\n" +
                ".ExternalClass {\n" +
                "width:100%;\n" +
                "}\n" +
                ".ExternalClass,\n" +
                ".ExternalClass p,\n" +
                ".ExternalClass span,\n" +
                ".ExternalClass font,\n" +
                ".ExternalClass td,\n" +
                ".ExternalClass div {\n" +
                "line-height:100%;\n" +
                "}\n" +
                ".es-button {\n" +
                "mso-style-priority:100!important;\n" +
                "text-decoration:none!important;\n" +
                "}\n" +
                "a[x-apple-data-detectors] {\n" +
                "color:inherit!important;\n" +
                "text-decoration:none!important;\n" +
                "font-size:inherit!important;\n" +
                "font-family:inherit!important;\n" +
                "font-weight:inherit!important;\n" +
                "line-height:inherit!important;\n" +
                "}\n" +
                ".es-desk-hidden {\n" +
                "display:none;\n" +
                "float:left;\n" +
                "overflow:hidden;\n" +
                "width:0;\n" +
                "max-height:0;\n" +
                "line-height:0;\n" +
                "mso-hide:all;\n" +
                "}\n" +
                "@media only screen and (max-width:600px) {p, ul li, ol li, a { line-height:150%!important } h1, h2, h3, h1 a, h2 a, h3 a { line-height:120%!important } h1 { font-size:30px!important; text-align:center } h2 { font-size:26px!important; text-align:center } h3 { font-size:20px!important; text-align:center } .es-header-body h1 a, .es-content-body h1 a, .es-footer-body h1 a { font-size:30px!important } .es-header-body h2 a, .es-content-body h2 a, .es-footer-body h2 a { font-size:26px!important } .es-header-body h3 a, .es-content-body h3 a, .es-footer-body h3 a { font-size:20px!important } .es-menu td a { font-size:16px!important } .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a { font-size:16px!important } .es-content-body p, .es-content-body ul li, .es-content-body ol li, .es-content-body a { font-size:16px!important } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size:16px!important } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a { font-size:12px!important } *[class=\"gmail-fix\"] { display:none!important } .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 { text-align:center!important } .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 { text-align:right!important } .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3 { text-align:left!important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display:inline!important } .es-button-border { display:block!important } a.es-button, button.es-button { font-size:20px!important; display:block!important; border-width:15px 25px 15px 25px!important } .es-btn-fw { border-width:10px 0px!important; text-align:center!important } .es-adaptive table, .es-btn-fw, .es-btn-fw-brdr, .es-left, .es-right { width:100%!important } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width:100%!important; max-width:600px!important } .es-adapt-td { display:block!important; width:100%!important } .adapt-img { width:100%!important; height:auto!important } .es-m-p0 { padding:0px!important } .es-m-p0r { padding-right:0px!important } .es-m-p0l { padding-left:0px!important } .es-m-p0t { padding-top:0px!important } .es-m-p0b { padding-bottom:0!important } .es-m-p20b { padding-bottom:20px!important } .es-mobile-hidden, .es-hidden { display:none!important } tr.es-desk-hidden, td.es-desk-hidden, table.es-desk-hidden { width:auto!important; overflow:visible!important; float:none!important; max-height:inherit!important; line-height:inherit!important } tr.es-desk-hidden { display:table-row!important } table.es-desk-hidden { display:table!important } td.es-desk-menu-hidden { display:table-cell!important } .es-menu td { width:1%!important } table.es-table-not-adapt, .esd-block-html table { width:auto!important } table.es-social { display:inline-block!important } table.es-social td { display:inline-block!important } .es-desk-hidden { display:table-row!important; width:auto!important; overflow:visible!important; max-height:inherit!important } }\n" +
                "</style>\n" +
                "</head>\n" +
                "<body style=\"width:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;padding:0;Margin:0\">\n" +
                "<div class=\"es-wrapper-color\" style=\"background-color:#F4F4F4\"><!--[if gte mso 9]>\n" +
                "<v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\n" +
                "<v:fill type=\"tile\" color=\"#f4f4f4\"></v:fill>\n" +
                "</v:background>\n" +
                "<![endif]-->\n" +
                "<table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;background-color:#F4F4F4\">\n" +
                "<tr class=\"gmail-fix\" height=\"0\" style=\"border-collapse:collapse\">\n" +
                "<td style=\"padding:0;Margin:0\">\n" +
                "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:600px\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"padding:0;Margin:0;line-height:1px;min-width:600px\" height=\"0\"><img src=\"https://akhpyb.stripocdn.email/content/guids/CABINET_837dc1d79e3a5eca5eb1609bfe9fd374/images/41521605538834349.png\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;max-height:0px;min-height:0px;min-width:600px;width:600px\" alt width=\"600\" height=\"1\"></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td valign=\"top\" style=\"padding:0;Margin:0\">\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "<table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td align=\"left\" style=\"Margin:0;padding-left:10px;padding-right:10px;padding-top:15px;padding-bottom:15px\"><!--[if mso]><table style=\"width:580px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:282px\" valign=\"top\"><![endif]-->\n" +
                "<table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td align=\"left\" style=\"padding:0;Margin:0;width:282px\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td class=\"es-infoblock es-m-txt-c\" align=\"left\" style=\"padding:0;Margin:0;line-height:14px;font-size:12px;color:#CCCCCC\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica\\ neue', helvetica, sans-serif;line-height:14px;color:#CCCCCC;font-size:12px\">Put your preheader text here<br></p></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table><!--[if mso]></td><td style=\"width:20px\"></td><td style=\"width:278px\" valign=\"top\"><![endif]-->\n" +
                "<table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td align=\"left\" style=\"padding:0;Margin:0;width:278px\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td align=\"right\" class=\"es-infoblock es-m-txt-c\" style=\"padding:0;Margin:0;line-height:14px;font-size:12px;color:#CCCCCC\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:14px;color:#CCCCCC;font-size:12px\"><a href=\"https://viewstripo.email\" class=\"view\" target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#CCCCCC;font-size:12px;font-family:arial, 'helvetica neue', helvetica, sans-serif\">View in browser</a></p></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table><!--[if mso]></td></tr></table><![endif]--></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<table class=\"es-header\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:#FFA73B;background-repeat:repeat;background-position:center top\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "<table class=\"es-header-body\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td align=\"left\" style=\"Margin:0;padding-bottom:10px;padding-left:10px;padding-right:10px;padding-top:20px\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:580px\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td align=\"center\" style=\"Margin:0;padding-left:10px;padding-right:10px;padding-top:25px;padding-bottom:25px;font-size:0px\"><img src=\"https://i.ibb.co/G7P02Js/Logo-Body-Health.jpg\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" height=\"156\"></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td style=\"padding:0;Margin:0;background-color:#ffa73b\" bgcolor=\"#ffa73b\" align=\"center\">\n" +
                "<table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td align=\"left\" style=\"padding:0;Margin:0\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:600px\">\n" +
                "<table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;background-color:#ffffff;border-radius:4px\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" role=\"presentation\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td align=\"center\" style=\"Margin:0;padding-bottom:5px;padding-left:30px;padding-right:30px;padding-top:35px\"><h1 style=\"Margin:0;line-height:58px;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;font-size:48px;font-style:normal;font-weight:normal;color:#111111\">¡Confirmación Exitosa!</h1></td>\n" +
                "</tr>\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td bgcolor=\"#ffffff\" align=\"center\" style=\"Margin:0;padding-top:5px;padding-bottom:5px;padding-left:20px;padding-right:20px;font-size:0\">\n" +
                "<table width=\"100%\" height=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td style=\"padding:0;Margin:0;border-bottom:1px solid #ffffff;background:#FFFFFF none repeat scroll 0% 0%;height:1px;width:100%;margin:0px\"></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "<table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td align=\"left\" style=\"padding:0;Margin:0\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:600px\">\n" +
                "<table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;border-radius:4px;background-color:#ffffff\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" role=\"presentation\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td class=\"es-m-txt-l\" bgcolor=\"#ffffff\" align=\"left\" style=\"Margin:0;padding-top:20px;padding-bottom:20px;padding-left:30px;padding-right:30px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:27px;color:#666666;font-size:18px\">" + nombre +" se ha confirmado tu cuenta exitosamente. Ahora puedes adquirir un plan y comprar por la página web. ¿Qué esperas?, ¡entra ya y disfruta de muchas promociones!.&nbsp;</p></td>\n" +
                "</tr>\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td class=\"es-m-txt-l\" align=\"left\" style=\"Margin:0;padding-top:20px;padding-left:30px;padding-right:30px;padding-bottom:40px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:27px;color:#666666;font-size:18px\">Saludos,</p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:27px;color:#666666;font-size:18px\">Equipo BodyHealth.</p></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "<table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td align=\"left\" style=\"padding:0;Margin:0\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:600px\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td align=\"center\" style=\"Margin:0;padding-top:10px;padding-bottom:20px;padding-left:20px;padding-right:20px;font-size:0\">\n" +
                "<table width=\"100%\" height=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td style=\"padding:0;Margin:0;border-bottom:1px solid #f4f4f4;background:#FFFFFF none repeat scroll 0% 0%;height:1px;width:100%;margin:0px\"></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-footer\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td align=\"center\" bgcolor=\"#ffa73b\" style=\"padding:0;Margin:0;background-color:#ffa73b\">\n" +
                "<table bgcolor=\"#ffffff\" class=\"es-footer-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td align=\"left\" bgcolor=\"#020202\" style=\"Margin:0;padding-top:10px;padding-bottom:10px;padding-left:20px;padding-right:20px;background-color:#020202\"><!--[if mso]><table style=\"width:560px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:270px\" valign=\"top\"><![endif]-->\n" +
                "<table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:270px\">\n" +
                "<table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-position:center top\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td class=\"es-m-txt-c\" align=\"left\" style=\"padding:0;Margin:0;padding-bottom:10px;padding-top:15px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:18px;color:#e7e1e1;font-size:12px\">BodyHealth</p></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table><!--[if mso]></td><td style=\"width:20px\"></td><td style=\"width:270px\" valign=\"top\"><![endif]-->\n" +
                "<table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td align=\"left\" style=\"padding:0;Margin:0;width:270px\">\n" +
                "<table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-position:center top\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td class=\"es-m-txt-c\" align=\"right\" style=\"padding:0;Margin:0;padding-top:5px;padding-bottom:5px;font-size:0\">\n" +
                "<table class=\"es-table-not-adapt es-social\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0\"><a target=\"_blank\" href=\"https://www.instagram.com/bodyhealthcucuta/?hl=es\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#111111;font-size:14px\"><img title=\"Instagram\" src=\"https://akhpyb.stripocdn.email/content/assets/img/social-icons/logo-white/instagram-logo-white.png\" alt=\"Inst\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table><!--[if mso]></td></tr></table><![endif]--></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td align=\"center\" bgcolor=\"#ffa73b\" style=\"padding:0;Margin:0;background-color:#ffa73b\">\n" +
                "<table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td align=\"left\" style=\"Margin:0;padding-left:20px;padding-right:20px;padding-top:30px;padding-bottom:30px\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:560px\">\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "<tr style=\"border-collapse:collapse\">\n" +
                "<td class=\"es-infoblock made_with\" align=\"center\" style=\"padding:0;Margin:0;line-height:0px;font-size:0px;color:#CCCCCC\"><a target=\"_blank\" href=\"https://i.ibb.co/G7P02Js/Logo-Body-Health.jpg\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#CCCCCC;font-size:12px\"><img src=\"https://i.ibb.co/G7P02Js/Logo-Body-Health.jpg\" alt width=\"125\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";

        return  html;
    }

    public void emailRegistro(String emailTo,String nombre, int id){
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            String htmlEmail = HtmlConfirmacion(nombre, id);
            sendListEmail(emailTo,"¡Confirma tu cuenta BodyHealth!",htmlEmail);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void sendListEmail(String emailTo, String subject,String html){
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(email);
            helper.setTo(emailTo);
            helper.setSubject(subject);
            helper.setText(html,true);
            javaMailSender.send(message);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void confirmarCorreo(int documento) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByDocumento(documento);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setConfirmado(true);
            usuarioRepository.save(usuario);
            String html =  htmlConfirmado(optionalUsuario.get().getNombre());
            sendListEmail(optionalUsuario.get().getEmail(),"Registro exitoso a BodyHealth",html);
        } else {
            throw new NotFoundException(messageUtil.getMessage("usuarioNotFound",null, Locale.getDefault()));
        }
    }
}
