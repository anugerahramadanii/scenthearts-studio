package com.scentheartsstudio.scentheartsstudio.services;

import com.scentheartsstudio.scentheartsstudio.dto.MidtransRequestDTO;
import com.scentheartsstudio.scentheartsstudio.dto.MidtransResponseDTO;
import com.scentheartsstudio.scentheartsstudio.feignclient.MidtransClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class MidtransService {

	@Autowired
	private MidtransClient mc;

	@Value("${midtrans.server.key}")
	private String serverKey;

	@Value("${midtrans.is-production}")
	private boolean isProduction;

	public MidtransResponseDTO createTransaction(MidtransRequestDTO midtransRequestDTO) {
		com.midtrans.Midtrans.isProduction = isProduction;
		String authHeader = "Basic " + Base64.getEncoder().encodeToString((serverKey + ":").getBytes());
		try{
			return mc.createTransaction(authHeader, midtransRequestDTO);
		} catch (Exception e){
			throw new RuntimeException("Failed to create transaction", e);
		}
	}
}
