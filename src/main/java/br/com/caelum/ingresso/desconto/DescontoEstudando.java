package br.com.caelum.ingresso.desconto;

import java.math.BigDecimal;

public class DescontoEstudando implements Desconto {
	
	private BigDecimal metade = new BigDecimal("2.0");
	
	@Override
	public BigDecimal apicarDescontoSobre(BigDecimal precoOriginal){
		return precoOriginal.divide(metade);
	}

}
