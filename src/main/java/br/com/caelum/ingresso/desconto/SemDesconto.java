package br.com.caelum.ingresso.desconto;

import java.math.BigDecimal;

public class SemDesconto implements Desconto {
	
	
	
	@Override 
	public BigDecimal apicarDescontoSobre(BigDecimal precoOriginal){
		return precoOriginal;
	}

}
