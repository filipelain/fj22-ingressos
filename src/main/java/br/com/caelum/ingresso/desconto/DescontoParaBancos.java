package br.com.caelum.ingresso.desconto;

import java.math.BigDecimal;

public class DescontoParaBancos implements Desconto {
	
	private BigDecimal percentualDeDesconto = new BigDecimal("0.3");
	
	@Override
	public BigDecimal apicarDescontoSobre(BigDecimal precoOriginal){
		return precoOriginal.subtract(trintaPorcento(precoOriginal));
	}

	private BigDecimal trintaPorcento(BigDecimal precoOriginal) {
		return precoOriginal.multiply(percentualDeDesconto);
	}

}
