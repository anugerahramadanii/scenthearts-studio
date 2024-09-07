package com.scentheartsstudio.scentheartsstudio.feignclient;

import com.scentheartsstudio.scentheartsstudio.dto.MidtransRequestDTO;
import com.scentheartsstudio.scentheartsstudio.dto.MidtransResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "midtrans-client", url = "${midtrans.api.url}")
public interface MidtransClient {

	@PostMapping(value = "/snap/v1/transactions", consumes = "application/json")
	MidtransResponseDTO createTransaction(@RequestHeader("Authorization") String authHeader,
	                                      @RequestBody MidtransRequestDTO midtransRequestDTO);
}
