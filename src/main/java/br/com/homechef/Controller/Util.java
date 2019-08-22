package br.com.homechef.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;


public class Util {
	
	public static boolean fazerUploadImagem(MultipartFile imagem) {
		boolean sucessoUpload = false;
		if (!imagem.isEmpty()) {
		String nomeArquivo = imagem.getOriginalFilename();
		try {
		// Criando o diretório para armazenar o arquivo
		String workspaceProjeto = "/home/neo/Documentos/PP2-HomeChef/homechef/src/main/resources/static/photos";
		File dir = new File(workspaceProjeto);
		if (!dir.exists()) {
		dir.mkdirs();
		}
		// Criando o arquivo no diretório
		File serverFile = new File(dir.getAbsolutePath() + File.separator + nomeArquivo);
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		stream.write(imagem.getBytes());
		stream.close();
		System. out .println("Arquivo armazenado em:" + serverFile.getAbsolutePath());
		System. out .println("Você fez o upload do arquivo " + nomeArquivo + " com sucesso");
		sucessoUpload = true;
		} catch (Exception e) {
		System. out .println("Você falhou em carregar o arquivo " + nomeArquivo + " => " + e.getMessage());
		}
		} else {
		System. out .println("Você falhou em carregar o arquivo porque ele está vazio ");
		}
		return sucessoUpload;
		}
	
	public static String createPayment(String sum, Integer usuario){

		 String redirectUrl = "";

		String clientId = "AQVCf312ixS7kE9bu-CTcCqA2VhCO_lH0mH0S1hxLP7tEkwpLgedEX69g55FI1YE1L6OHVwVcwCPAA2c";

		String clientSecret = "ECRKzpgc0NdPVOFhpYhSYgMwa9m8hDsCUhWGnzEGGdsD7AnSKLP_FcBKqWOawV1nriIYPRDUZyN4EVJf";

        Map<String, Object> response = new HashMap<String, Object>();

        Amount amount = new Amount();

        amount.setCurrency("BRL");

        amount.setTotal(sum);

        Transaction transaction = new Transaction();

        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<Transaction>();

        transactions.add(transaction);



        Payer payer = new Payer();

        payer.setPaymentMethod("paypal");



        Payment payment = new Payment();

        payment.setIntent("sale");

        payment.setPayer(payer);

        payment.setTransactions(transactions);



        RedirectUrls redirectUrls = new RedirectUrls();

        redirectUrls.setCancelUrl("http://localhost:8080/cancel");

        redirectUrls.setReturnUrl("http://localhost:8080/compraSucesso?usuario="+ usuario + "&valor=" + sum);

        payment.setRedirectUrls(redirectUrls);

        Payment createdPayment;

        try {


            APIContext context = new APIContext(clientId, clientSecret, "sandbox");

            createdPayment = payment.create(context);

            if(createdPayment!=null){

                List<Links> links = createdPayment.getLinks();

                for (Links link:links) {

                    if(link.getRel().equals("approval_url")){

                        redirectUrl = link.getHref();

                        System.out.println(redirectUrl);

                        break;

                    }

                }

                response.put("status", "success");

                response.put("redirect_url", redirectUrl);

            }

        } catch (PayPalRESTException e) {

            System.out.println("Error happened during payment creation!");

        }

        return redirectUrl;

    }
	
	
//	=================== HASH ==============
	
	public static String md5(String senha) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		BigInteger hash = new BigInteger(1, messageDigest.digest(senha.getBytes()));
		return hash.toString(16);
	}
	
//	======= obter momento atual ===========
	public static String obterMomentoAtual() {
		Calendar c = Calendar.getInstance();
		int ano = c.get(Calendar.YEAR);
		int mes = c.get(Calendar.MONTH);
		int dia = c.get(Calendar.DAY_OF_MONTH);
		int hora = c.get(Calendar.HOUR_OF_DAY);
		int minuto = c.get(Calendar.MINUTE);
		int segundo = c.get(Calendar.SECOND);
		
		
 		String momentoUpload = ano +"-"+ (mes+1) +"-"+ dia + "-" + hora +"-"+ minuto +"-"+ segundo;
		return momentoUpload;
		}


}
